package com.theshow.core.domain
import io.circe.Codec
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.semiauto.deriveConfiguredCodec

case class Event(
    userName: UserName,
    message: Message,
    mood: Mood,
    whatIPlanToAchieve: WhatIPlanToAchieve,
    regrets: Option[Regret]
)

object Event {
  implicit val config: Configuration        = Configuration.default
  implicit val codec: Codec.AsObject[Event] = deriveConfiguredCodec[Event]
}
