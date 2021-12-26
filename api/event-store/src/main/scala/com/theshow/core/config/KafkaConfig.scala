package com.theshow.core.config

import cats.effect.kernel.Async
import ciris.{env, ConfigValue}
import cats.implicits._
import com.theshow.core.domain.{BootStrapServer, GroupId, Topic}

case class KafkaConfig(bootstrapServer: BootStrapServer, groupId: GroupId, topic: Topic)

object KafkaConfig {
  def kafkaConfig[F[_]: Async]: ConfigValue[F, KafkaConfig] = (
    env("BOOTSTRAP_SERVER").as[String].default("localhost:9092"),
    env("GROUP_ID").as[String].default("consumerGroup"),
    env("KAFKA_TOPIC").as[String].default("eventTopic")
  ).parMapN((port, groupId, topic) =>
    KafkaConfig(BootStrapServer(port), GroupId(groupId), Topic(topic))
  )
}
