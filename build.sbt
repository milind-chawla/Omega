name := """Omega"""

version := "1.0"

scalaVersion := "2.11.7"

// seq(webSettings : _*)

// Change this to another test framework if you prefer
libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.7"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"

libraryDependencies += "com.h2database" % "h2" % "1.3.148"

libraryDependencies += "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
// libraryDependencies += "javax.persistence" % "persistence-api" % "1.0.2"
libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final"
libraryDependencies += "org.hibernate" % "hibernate-validator" % "5.1.0.Final"

libraryDependencies += "org.springframework" % "spring-test" % "4.1.9.RELEASE"
libraryDependencies += "org.springframework" % "spring-context" % "4.1.9.RELEASE"
libraryDependencies += "org.springframework" % "spring-jdbc" % "4.1.9.RELEASE"
libraryDependencies += "org.springframework" % "spring-tx" % "4.1.9.RELEASE"
libraryDependencies += "org.springframework" % "spring-orm" % "4.1.9.RELEASE"
libraryDependencies += "org.springframework" % "spring-expression" % "4.1.9.RELEASE"
libraryDependencies += "org.springframework" % "spring-webmvc" % "4.1.9.RELEASE"

// libraryDependencies += "org.thymeleaf" % "thymeleaf-spring4" % "2.1.4.RELEASE"

// libraryDependencies += "org.eclipse.jetty" % "jetty-webapp" % "9.2.15.v20160210" % "container, compile"
// libraryDependencies += "org.eclipse.jetty" % "jetty-jsp" % "9.2.15.v20160210" % "container"

enablePlugins(JettyPlugin)

// Uncomment to use Akka
//libraryDependencies += "com.typesafe.akka" %% "akka-actor" % "2.3.11"
