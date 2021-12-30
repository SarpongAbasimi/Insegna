package com.theshow.core.config

import cats.effect.kernel.Async
import ciris.{ConfigValue, Effect}
import ciris.env
import cats.implicits._
import com.theshow.core.domain.{Host, Index, Port, Scheme, ShardReplicas, Shards}

case class EsConfig(
    index: Index,
    host: Host,
    port: Port,
    scheme: Scheme,
    shards: Shards,
    replicas: ShardReplicas
)

object EsConfig {
  def esConfig[F[_]: Async]: ConfigValue[F, EsConfig] =
    (
      env("INDEX").default("event-index"),
      env("HOST").default("localhost"),
      env("PORT").as[Int].default(9200),
      env("SCHEME").default("Http"),
      env("NUMBER_OF_SHARDS").as[Int].default(3),
      env("NUMBER_OF_REPLICAS").as[Int].default(2)
    ).mapN((index, host, port, scheme, numberOfShards, replicas) =>
      EsConfig(
        Index(index),
        Host(host),
        Port(port),
        Scheme(scheme),
        Shards(numberOfShards),
        ShardReplicas(replicas)
      )
    )
}
