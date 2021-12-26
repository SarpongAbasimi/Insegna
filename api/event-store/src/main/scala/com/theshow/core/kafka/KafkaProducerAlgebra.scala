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

object KafkaProducerAlgebra {
  def impl[F[_]: Async: Console](kafkaConfig: KafkaConfig): KafkaProducerAlgebra[F] =
    new KafkaProducerAlgebra[F] {
      def publish(event: Event): F[Unit] = {
        val valueSerializer: Serializer[F, Event] = Serializer[F, String]
          .contramap[Event](_.asJson.noSpaces)

        val producerSettings = ProducerSettings(
          keySerializer = Serializer[F, Unit],
          valueSerializer = valueSerializer
        ).withBootstrapServers(kafkaConfig.bootstrapServer.value)
          .withAcks(Acks.One)

        KafkaProducer
          .stream(producerSettings)
          .evalMapChunk { producer =>
            val record: ProducerRecord[Unit, Event] =
              ProducerRecord(kafkaConfig.topic.value, (), event)
            producer.produce(ProducerRecords.one(record)).flatten
          }
          .evalTapChunk(_ => Console[F].println(s"The event has been published"))
          .compile
          .drain
      }
    }
}
