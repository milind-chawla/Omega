name := """javascript"""

version := "1.0"

//set scalaJSUseRhino in Global := false;

enablePlugins(ScalaJSPlugin)

scalaVersion := "2.11.8"

// Change this to another test framework if you prefer
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.9.0"

libraryDependencies += "be.doeraene" %%% "scalajs-jquery" % "0.9.0"

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"
