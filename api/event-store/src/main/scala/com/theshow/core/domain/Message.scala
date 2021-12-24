package com.theshow.core.domain

import io.circe.Codec
import io.circe.generic.extras.semiauto.deriveUnwrappedCodec

final case class Message(
    message: String
) extends AnyVal

object Message {
  implicit val codec: Codec[Message] = deriveUnwrappedCodec[Message]
}
