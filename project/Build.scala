import java.text.SimpleDateFormat
import java.util.Date

import sbt.Keys._
import sbt._
import scoverage.ScoverageSbtPlugin.ScoverageKeys._
import com.typesafe.sbt.SbtScalariform._
import Dependencies._

import scala.util.Try

object Build extends Build {

  val parExec = sys.env.get("SBT_ALLOW_PARALLEL")
    .flatMap(s => Try(s.toBoolean).toOption)
    .getOrElse(false)

  val projectVersion = new SimpleDateFormat("YYYYMMddHHmmss").format(new Date)

  val PFDS = (project in file("."))
    .baseSettings("PFDS")
    .settings(libraryDependencies ++= PFDSDependencies)

  //.settings(libraryDependencies)

  /** Helper class for making all builds use one standard */
  implicit class RichProject(project: Project) {

    def baseSettings(projectName: String): Project =
      project
        .settings(
        name := projectName,
        organization := "com.ebusiello",
        version := projectVersion,
        scalacOptions := Seq(
          "-deprecation",
          "-explaintypes",
          "-feature",
          "-language:postfixOps",
          "-language:implicitConversions",
          "-target:jvm-1.8",
          "-unchecked",
          "-Xcheckinit",
          "-Xfatal-warnings",
          "-Xfuture",
          "-Xlint",
          "-Xverify",
          "-Ywarn-adapted-args",
          "-Ywarn-dead-code",
          "-Ywarn-inaccessible",
          "-Ywarn-infer-any",
          "-Ywarn-nullary-override",
          "-Ywarn-nullary-unit",
          "-Ywarn-unused",
          "-Ywarn-unused-import"
        ),
        scalaVersion := "2.11.7",
        resolvers ++= Seq[Resolver](
          "Typesafe repo" at "http://repo.typesafe.com/typesafe/releases/"
        ),
        testOptions in ThisBuild <+= (target in Test) map {
          t => Tests.Argument("-o", "-u", t + "/test-reports")
        },
        fork in run := true,
        parallelExecution in Global := parExec
      )
        .settings(scalariformSettings: _*)
        .settings(
          coverageMinimum := 90,
          coverageFailOnMinimum := true
        )
  }

}