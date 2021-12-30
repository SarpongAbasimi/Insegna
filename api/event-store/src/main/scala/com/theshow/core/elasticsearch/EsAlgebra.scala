package com.theshow.core.elasticsearch

import cats.effect.Async
import cats.effect.std.Console
import com.theshow.core.config.EsConfig
import com.theshow.core.domain.Event
import org.elasticsearch.client.{RequestOptions, RestHighLevelClient}
import org.elasticsearch.client.indices.{CreateIndexRequest, GetIndexRequest}
import org.elasticsearch.common.settings.Settings
import cats.implicits._
import org.elasticsearch.action.index.{IndexRequest, IndexResponse}
import io.circe.syntax._
import org.elasticsearch.common.xcontent.XContentType

trait EsAlgebra[F[_]] {
  def createIndex: F[Unit]
  def store(event: Event): F[IndexResponse]
}

object EsAlgebra {
  def impl[F[_]: Async: Console](
      esConfig: EsConfig,
      restHighLevelClient: RestHighLevelClient
  ): EsAlgebra[F] =
    new EsAlgebra[F] {
      def createIndex: F[Unit] = for {
        getIndex <- getIndexRequest
        indexExit <- Async[F].delay(
          restHighLevelClient.indices().exists(getIndex, RequestOptions.DEFAULT)
        )
        request <- createIndexRequest
        _ <- indexExit
          .pure[F]
          .ifM(
            Console[F].println("Index already exit 😅"),
            Async[F].delay(
              restHighLevelClient.indices().create(request, RequestOptions.DEFAULT)
            ) *> Console[F]
              .println(s"Index with the name ${esConfig.index.name} has been created 🚀")
          )
      } yield ()

      def store(event: Event): F[IndexResponse] = Async[F].delay {
        restHighLevelClient.index(
          new IndexRequest(esConfig.index.name)
            .source(event.asJson.noSpaces, XContentType.JSON),
          RequestOptions.DEFAULT
        )
      }

      private[elasticsearch] def createIndexRequest: F[CreateIndexRequest] = {
        Async[F].delay(
          new CreateIndexRequest(esConfig.index.name)
            .settings(
              Settings
                .builder()
                .put("index.number_of_shards", esConfig.shards.value)
                .put("index.number_of_replicas", esConfig.replicas.value)
            )
        )
      }
      private[elasticsearch] def getIndexRequest: F[GetIndexRequest] =
        Async[F].delay(new GetIndexRequest(esConfig.index.name))
    }
}
