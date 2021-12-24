package com.theshow.core

import cats.effect.{ExitCode, IO, IOApp}
import com.theshow.core.config.Config
import com.theshow.core.server.Server
import fs2.Stream

object Main extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = (for {
    config <- Stream.eval(Config.config[IO].load)
    server <- Server.stream[IO](config)
  } yield server).compile.drain.as(ExitCode.Success)
}
