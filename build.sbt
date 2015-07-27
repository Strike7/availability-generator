name := """play-getting-started"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws
)

libraryDependencies <+= scalaVersion("org.scala-lang" % "scala-compiler" % _ )



libraryDependencies ++= Seq(
     "com.typesafe.play" %% "play-slick" % "0.8.1"
    ,"mysql" % "mysql-connector-java" % "5.1.36"
    ,"com.typesafe.slick" %% "slick" % "2.1.0"
    ,"org.postgresql" % "postgresql" % "9.4-1201-jdbc41"
)
