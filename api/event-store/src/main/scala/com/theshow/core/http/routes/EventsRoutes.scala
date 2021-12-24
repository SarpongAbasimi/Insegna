package com.theshow.core.http.routes

import cats.effect.kernel.Async
import com.theshow.core.domain.Event
import org.http4s.HttpRoutes
import org.http4s.circe.jsonOf
import org.http4s.dsl.Http4sDsl
import org.http4s.server.Router

case class EventsRoutes[F[_]: Async]() extends Http4sDsl[F] {
  private[routes] val prefix = "/api/v1/event"

  private val routes: HttpRoutes[F] = HttpRoutes.of[F] { case req @ POST -> Root =>
    implicit val entityDecoder = jsonOf[F, Event]

    req
      .attemptAs[Event]
      .foldF(
        _ => BadRequest("And error occurred check the request body"),
        _ => Created("Created")
      )
  }

  val router = Router(prefix -> routes)
}
