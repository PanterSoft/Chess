val scala3Version = "3.2.2-RC1-bin-20221101-d84007c-NIGHTLY"

import org.scoverage.coveralls.Imports.CoverallsKeys._

lazy val root = project
  .in(file("."))
  .settings(
    name := "Chess",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test"
  )
  .enablePlugins(CoverallsPlugin)
