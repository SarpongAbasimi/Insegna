package com.theshow.core.http.routes

import cats.effect.kernel.Async
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router

case class EventsRoutes[F[_]: Async]() extends Http4sDsl[F] {
  private[routes] val prefix = "/api/v1/event"
  private val routes: HttpRoutes[F] = HttpRoutes.of[F] { case GET -> Root =>
    Ok("Hello")
  }

  val router = Router(prefix -> routes)
}
