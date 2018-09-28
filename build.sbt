import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.rhdzmota",
      scalaVersion := "2.12.6",
      version      := "0.0.0"
    )),
    name := "google-vision-api",
    libraryDependencies ++= {
      val akkaHttpVersion = "10.1.1"
      val circeVersion = "0.9.3"
      val akkaVersion = "2.5.12"
      val configVersion = "1.3.1"
      Seq(
        "com.typesafe" % "config" % configVersion,
        // Akka toolkit
        "com.typesafe.akka" %% "akka-actor"     % akkaVersion,
        "com.typesafe.akka" %% "akka-http"      % akkaHttpVersion,
        "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
        // Circe
        "io.circe" %% "circe-core"    % circeVersion,
        "io.circe" %% "circe-generic" % circeVersion,
        "io.circe" %% "circe-parser"  % circeVersion,
        // Test
        "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
        scalaTest % Test
      )
    }
  )
