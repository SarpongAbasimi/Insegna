package com.theshow.core.server

import cats.effect.ExitCode
import cats.effect.kernel.Async
import cats.effect.std.Console
import com.theshow.core.config.Config
import com.theshow.core.http.routes.EventsRoutes
import com.theshow.core.kafka.{KafkaConsumerAlgebra, KafkaProducerAlgebra}
import org.http4s.blaze.server.BlazeServerBuilder
import fs2.Stream

object Server {
  def stream[F[_]: Async: Console](config: Config): fs2.Stream[F, ExitCode] = for {
    _ <- Stream.eval(Console[F].println("Starting the server 🚀"))

    kafkaProducerAlgebra: KafkaProducerAlgebra[F] = KafkaProducerAlgebra
      .impl[F](config.kafkaConfig)
    kafkaConsumerAlgebra: KafkaConsumerAlgebra[F] = KafkaConsumerAlgebra
      .impl[F](config.kafkaConfig)

    sever <- BlazeServerBuilder[F]
      .bindHttp(
        config.serverConfig.port.value,
        config.serverConfig.host.value
      )
      .withHttpApp(EventsRoutes[F](kafkaProducerAlgebra).router.orNotFound)
      .serve
      .concurrently(kafkaConsumerAlgebra.consume)
  } yield sever
}
