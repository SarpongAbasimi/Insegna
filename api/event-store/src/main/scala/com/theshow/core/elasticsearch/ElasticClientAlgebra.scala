package com.theshow.core.elasticsearch

import cats.effect.{Async, Resource}
import org.apache.http.HttpHost
import org.elasticsearch.client.{RestClient, RestHighLevelClient}

trait ElasticClientAlgebra[F[_]] {
  def client: Resource[F, RestHighLevelClient]
}
object ElasticClientAlgebra {
  def impl[F[_]: Async]: ElasticClientAlgebra[F] = new ElasticClientAlgebra[F] {
    def client: Resource[F, RestHighLevelClient] = Resource.make(
      Async[F].delay(
        new RestHighLevelClient(
          RestClient.builder(
            new HttpHost("localhost", 9200, "http")
          )
        )
      )
    )(client => Async[F].delay(client.close()))
  }
}
