package com.theshow.core.kafka
import cats.effect.{kernel, Async}
import cats.effect.std.Console
import com.theshow.core.config.KafkaConfig
import com.theshow.core.domain.Event
import fs2.Stream
import fs2.kafka.{
  AutoOffsetReset,
  CommittableConsumerRecord,
  ConsumerSettings,
  Deserializer,
  KafkaConsumer
}
import io.circe.jawn.decodeByteArray
import cats.implicits._

trait KafkaConsumerAlgebra[F[_]] {
  def consume: Stream[F, CommittableConsumerRecord[F, Unit, Event]]
}

object KafkaConsumerAlgebra {
  def impl[F[_]: Async: Console](kafkaConfig: KafkaConfig): KafkaConsumerAlgebra[F] =
    new KafkaConsumerAlgebra[F] {
      def consume: Stream[F, CommittableConsumerRecord[F, Unit, Event]] = {

        val valueDeserializer: Deserializer[F, Event] =
          Deserializer.lift[F, Event](byteArray => decodeByteArray[Event](byteArray).liftTo[F])

        val consumerSettings = ConsumerSettings(
          keyDeserializer = Deserializer[F, Unit],
          valueDeserializer = valueDeserializer
        ).withAutoOffsetReset(AutoOffsetReset.Earliest)
          .withBootstrapServers(kafkaConfig.bootstrapServer.value)
          .withGroupId(kafkaConfig.groupId.value)

        KafkaConsumer
          .stream[F, Unit, Event](consumerSettings)
          .subscribeTo(kafkaConfig.topic.value)
          .records
          .evalTapChunk(consumedRecord =>
            Console[F].println(
              s"Record has been processes -> ${consumedRecord.record.value}"
            ) *> consumedRecord.offset.commit
          )
      }
    }
}
