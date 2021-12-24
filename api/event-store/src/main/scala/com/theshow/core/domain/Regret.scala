package com.theshow.core.domain

import io.circe.Codec
import io.circe.generic.extras.semiauto.deriveUnwrappedCodec

final case class Regret(
    regret: String
) extends AnyVal

object Regret {
  implicit val codec: Codec[Regret] = deriveUnwrappedCodec[Regret]
}
