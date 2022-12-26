package com.theshow.core.kafka

import cats.effect.kernel.Async
import cats.effect.std.Console
import com.theshow.core.domain.Event
import fs2.kafka.{
  Acks,
  KafkaProducer,
  ProducerRecord,
  ProducerRecords,
  ProducerSettings,
  Serializer
}
import io.circe.syntax._
import cats.implicits._
import com.theshow.core.config.KafkaConfig

trait KafkaProducerAlgebra[F[_]] {
  def publish(event: Event): F[Unit]
}

abstract case class ProducerService[F[_]: Async: Console] private (
    kafkaConfig: KafkaConfig,
    producer: KafkaProducer.Metrics[F, Unit, Event]
) extends KafkaProducerAlgebra[F] {
  def publish(event: Event): F[Unit] = {
    producer
      .produce(ProducerRecords.one(ProducerRecord(kafkaConfig.topic.value, (), event)))
      .flatten
      .flatTap(_ => Console[F].println("Remember to transform me to a logger - Event published "))
      .void
  }
}

object ProducerService {
  def stream[F[_]: Async: Console](
      kafkaConfig: KafkaConfig
  ): fs2.Stream[F, ProducerService[F]] = {
    val valueSerializer: Serializer[F, Event] = Serializer[F, String]
      .contramap[Event](_.asJson.noSpaces)

    val producerSettings = ProducerSettings(
      keySerializer = Serializer[F, Unit],
      valueSerializer = valueSerializer
    ).withBootstrapServers(kafkaConfig.bootstrapServer.value)
      .withAcks(Acks.One)

    KafkaProducer
      .stream(producerSettings)
      .map(producer => new ProducerService[F](kafkaConfig, producer) {})
  }
}
