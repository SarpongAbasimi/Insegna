package com.theshow.core.domain
import io.circe.Codec
import io.circe.generic.extras.semiauto.deriveUnwrappedCodec

final case class UserName(userName: String) extends AnyVal

object UserName {
  implicit val codec: Codec[UserName] = deriveUnwrappedCodec[UserName]
}
