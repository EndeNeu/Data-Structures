import sbt._

object Dependencies {

  private val playJson = "com.typesafe.play" %% "play-json" % "2.4.2" withSources()
  private val scalaTest = "org.scalatest" %% "scalatest" % "2.2.2" % "test" withSources()
  private val scalaz = "org.scalaz" %% "scalaz-core" % "7.1.1" withSources()

  val DSDependencies = Seq(
    playJson,
    scalaTest,
    scalaz
  )
}
