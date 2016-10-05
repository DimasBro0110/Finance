lazy val root = (project in file("."))
  .settings(
    name := "Finance",
    version := "1.0",
    scalaVersion := "2.11.6",
    fork := true
  )

resolvers ++= Seq(
  "Akka Repository" at "http://repo.akka.io/releases/",
  "Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/",
  "ReactiveCouchbase repository" at "https://raw.github.com/ReactiveCouchbase/repository/master/snapshots",
  Resolver.sonatypeRepo("public")
)

libraryDependencies ++= Seq(
  "mysql" % "mysql-connector-java" % "5.1.30",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "joda-time" % "joda-time" % "2.4",
  "org.squeryl" %% "squeryl" % "0.9.5-7",
  "com.typesafe" % "config" % "1.3.1"
)