package com.theshow.core.service
import cats.effect.Async
import cats.effect.std.Console
import com.theshow.core.elasticsearch.EsAlgebra
import com.theshow.core.kafka.KafkaConsumerAlgebra
import fs2.Stream
import org.elasticsearch.action.index.IndexResponse

trait IndexService[F[_]] {
  def persist: Stream[F, IndexResponse]
}

object IndexService {
  def impl[F[_]: Async: Console](
      kafkaConsumerAlgebra: KafkaConsumerAlgebra[F],
      esAlgebra: EsAlgebra[F]
  ): IndexService[F] = new IndexService[F] {
    def persist: Stream[F, IndexResponse] = kafkaConsumerAlgebra.consume
      .evalMapChunk { consumedEvent =>
        esAlgebra.store(consumedEvent.record.value)
      }
      .evalTapChunk { (indexResponse) =>
        Console[F].println(s"The index status is  -> ${indexResponse.status()}")
      }
  }
}
