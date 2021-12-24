package com.theshow.core.domain

case class Event(
    userName: UserName,
    message: Message,
    mood: Mood,
    whatIPlanToAchieve: WhatIPlanToAchieve,
    regrets: Option[Regret]
)
