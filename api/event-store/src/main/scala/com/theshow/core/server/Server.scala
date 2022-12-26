package com.theshow.core.server

import cats.effect.{ExitCode, IO}
import cats.effect.kernel.Async
import cats.effect.std.Console
import com.theshow.core.config.Config
import com.theshow.core.elasticsearch.EsAlgebra
import com.theshow.core.http.routes.EventsRoutes
import com.theshow.core.kafka.{KafkaConsumerAlgebra, ProducerService}
import com.theshow.core.service.IndexService
import org.http4s.blaze.server.BlazeServerBuilder
import fs2.Stream
import org.elasticsearch.client.RestHighLevelClient
import org.http4s.Method
import org.http4s.server.middleware.{CORS, CORSConfig}

object Server {
  def stream[F[_]: Async: Console](
      config: Config,
      restHighLevelClient: RestHighLevelClient
  ): fs2.Stream[F, ExitCode] = for {
    _               <- Stream.eval(Console[F].println("Starting the server 🚀"))
    producerService <- ProducerService.stream[F](config.kafkaConfig)

    kafkaConsumerAlgebra: KafkaConsumerAlgebra[F] = KafkaConsumerAlgebra
      .impl[F](config.kafkaConfig)
    esAlgebra: EsAlgebra[F]       = EsAlgebra.impl[F](config.esConfig, restHighLevelClient)
    indexService: IndexService[F] = IndexService.impl[F](kafkaConsumerAlgebra, esAlgebra)

    _ <- Stream.eval(esAlgebra.createIndex)

    corService = CORS(
      EventsRoutes[F](producerService).router,
      CORSConfig.default
      .withAllowedOrigins(Set("http://localhost:3000"))
        .withAllowedMethods(Some(Set(Method.POST)))
    )

    sever <- BlazeServerBuilder[F]
      .bindHttp(
        config.serverConfig.port.value,
        config.serverConfig.host.value
      )
      .withHttpApp(corService.orNotFound)
      .serve
      .concurrently(indexService.persist)
  } yield sever
}
