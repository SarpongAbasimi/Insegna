import sbt._
object Dependencies {
  private val http4sVersion            = "0.23.7"
  private val catsEffectVersion        = "3.3.1"
  private val cirisVersion             = "2.3.1"
  private val circeVersion             = "0.14.1"
  private val circeGenericExtraVersion = "0.14.1"
  private val f2sKafkaVersion          = "2.2.0"
  private val elasticSearchVersion     = "7.15.2"

  private def http4s(branch: String): ModuleID =
    "org.http4s" %% s"http4s-$branch" % http4sVersion

  val circe = Seq(
    "io.circe" %% "circe-core",
    "io.circe" %% "circe-generic",
    "io.circe" %% "circe-parser"
  ).map(_ % circeVersion)

  val http4sDsl         = http4s("dsl")
  val http4sBlazeServer = http4s("blaze-server")
  val http4sBlazeClient = http4s("blaze-client")
  val http4sCirce       = http4s("circe")
  val catsEffect        = "org.typelevel"   %% "cats-effect"          % catsEffectVersion
  val ciris             = "is.cir"          %% "ciris"                % cirisVersion
  val circeGenericExtra = "io.circe"        %% "circe-generic-extras" % circeGenericExtraVersion
  val kafka             = "com.github.fd4s" %% "fs2-kafka"            % f2sKafkaVersion
  val elasticSearchClient =
    "org.elasticsearch.client" % "elasticsearch-rest-high-level-client" % elasticSearchVersion

}
