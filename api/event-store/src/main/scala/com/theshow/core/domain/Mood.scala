package com.theshow.core.domain

import io.circe.Codec
import io.circe.generic.extras.semiauto.deriveUnwrappedCodec

final case class Mood(
    mood: String
) extends AnyVal

object Mood {
  implicit val codec: Codec[Mood] = deriveUnwrappedCodec[Mood]
}
