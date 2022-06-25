lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .settings(
    name := """playExcelSpark""",
    organization := "com.PlayExcelSpark",
    version := "1.0-SNAPSHOT",
    scalaVersion := "2.12.10",
    libraryDependencies ++= Seq(
      guice,
      "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
    ),
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    ),
      libraryDependencies ++= Seq(
      "org.apache.spark" %% "spark-core" % "3.0.1",   //"org.apache.spark" %% "spark-core" % "3.1.3"  2.4.8 up
      "org.apache.spark" %% "spark-sql" % "3.0.1",    //
        "com.crealytics" %% "spark-excel" % "2.4.8_0.17.1",   //"com.crealytics" %% "spark-excel" % "3.2.1_0.17.0
        //"com.crealytics" %% "spark-excel" % "0.17.0",
        //"com.crealytics" %% "spark-excel" % "0.13.8"
      "mysql" % "mysql-connector-java" % "8.0.29",
        "com.typesafe.play" %% "play-json" % "2.9.2"
    )

  )
