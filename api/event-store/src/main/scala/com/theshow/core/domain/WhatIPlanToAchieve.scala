package com.theshow.core.domain

import io.circe.Codec
import io.circe.generic.extras.semiauto.deriveUnwrappedCodec

final case class WhatIPlanToAchieve(
    whatIPlanToAchieve: String
) extends AnyVal

object WhatIPlanToAchieve {
  implicit val codec: Codec[WhatIPlanToAchieve] = deriveUnwrappedCodec[WhatIPlanToAchieve]
}
