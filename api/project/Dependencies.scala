import sbt._
object Dependencies {
  private val http4sVersion     = "0.23.7"
  private val catsEffectVersion = "3.3.1"
  private val cirisVersion      = "2.3.1"

  private def http4s(branch: String): ModuleID =
    "org.http4s" %% s"http4s-$branch" % http4sVersion

  val http4sDsl         = http4s("dsl")
  val http4sBlazeServer = http4s("blaze-server")
  val http4sBlazeClient = http4s("blaze-client")
  val catsEffect        = "org.typelevel" %% "cats-effect" % catsEffectVersion
  val ciris             = "is.cir"        %% "ciris"       % cirisVersion
}
