name := "play-java-intro"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

libraryDependencies ++= Seq(
  javaJpa,
  javaWs,
  evolutions,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "org.hibernate" % "hibernate-entitymanager" % "4.3.7.Final"
)

libraryDependencies += "junit" % "junit" % "4.11"
libraryDependencies += "org.mockito" % "mockito-all" % "1.9.5"
// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
