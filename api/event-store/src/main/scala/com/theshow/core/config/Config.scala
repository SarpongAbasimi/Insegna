package com.theshow.core.config

import cats.effect.kernel.Async
import ciris.{ConfigValue}
import cats.implicits._

final case class Config(
    serverConfig: ServerConfig,
    kafkaConfig: KafkaConfig,
    esConfig: EsConfig
)

object Config {
  def config[F[_]: Async]: ConfigValue[F, Config] =
    (ServerConfig.serverConfig[F], KafkaConfig.kafkaConfig[F], EsConfig.esConfig[F])
      .parMapN((serverConfig, kafkaConfig, esConfig) => Config(serverConfig, kafkaConfig, esConfig))
}
