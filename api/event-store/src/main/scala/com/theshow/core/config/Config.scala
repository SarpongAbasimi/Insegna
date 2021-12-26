package com.theshow.core.config

import cats.effect.kernel.Async
import ciris.{ConfigValue}
import cats.implicits._

final case class Config(
    serverConfig: ServerConfig,
    kafkaConfig: KafkaConfig
)

object Config {
  def config[F[_]: Async]: ConfigValue[F, Config] =
    (ServerConfig.serverConfig[F], KafkaConfig.kafkaConfig[F])
      .parMapN((serverConfig, kafkaConfig) => Config(serverConfig, kafkaConfig))
}
