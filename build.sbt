name := "cronish"

organization := "com.frograms"

version := "0.1.5-FROGRAMS"

parallelExecution in Test := false

scalaVersion := "2.12.1"

crossScalaVersions := Seq(
  "2.12.1",
  "2.11.8",
  "2.10.3"
)

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

scalacOptions <++= scalaVersion map {
  case sv if sv startsWith "2.1" =>
    Seq("-feature", "-language:implicitConversions", "-language:postfixOps")
  case _ => Nil
}

libraryDependencies += ("com.frograms" %% "scalendar" % "0.1.5-FROGRAMS")

libraryDependencies <++= scalaVersion {
  case sv if (sv startsWith "2.11") || (sv startsWith "2.12") => Seq(
    "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.5",
    "com.typesafe.akka" %% "akka-actor" % "2.4.17",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test"
  )
  case _ => Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.1.0",
    "org.scalatest" %% "scalatest" % "1.9" % "test"
  )
}

publishTo <<= version { v =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { x => false }

pomExtra := (
  <url>https://github.com/frograms/cronish</url>
  <licenses>
    <license>
      <name>The MIT License</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:frograms/cronish.git</url>
    <connection>scm:git:git@github.com:frograms/cronish.git</connection>
  </scm>
  <developers>
    <developer>
      <id>frograms</id>
      <name>Frograms inc.</name>
      <url>http://www.frograms.com/</url>
    </developer>
  </developers>
)
