val scala3Version = "3.2.2-RC1-bin-20221101-d84007c-NIGHTLY" // This version is needed on MacOS with Apple Silicon Chip

import org.scoverage.coveralls.Imports.CoverallsKeys._

lazy val root = project
  .in(file("."))
  .settings(
    name := "Chess",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",
    libraryDependencies += "org.scalafx" %% "scalafx" % "18.0.1-R28",
    libraryDependencies += "org.scala-lang.modules" %% "scala-swing" % "3.0.0",
    libraryDependencies += "com.google.inject" % "guice" % "5.1.0",
    libraryDependencies += ("net.codingwell" %% "scala-guice" % "5.1.0").cross(CrossVersion.for3Use2_13),
    libraryDependencies += ("com.typesafe.play" %% "play-json" % "2.9.3").cross(CrossVersion.for3Use2_13),
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1"
  )
  .enablePlugins(CoverallsPlugin)
