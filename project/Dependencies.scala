import sbt._

object Dependencies {

  private val playJson =        "com.typesafe.play" %% "play-json" % "2.4.2" withSources()
  private val scalaTest       = "org.scalatest"      %% "scalatest" % "2.2.2" % "it,test" withSources()

  val PFDSDependencies = Seq(
    playJson,
    scalaTest
  )
}
