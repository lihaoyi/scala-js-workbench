import sbt._
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._

object Dependencies {

  // jvm dependencies
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.1.3"
  val akka = "com.typesafe.akka" %% "akka-actor" % "2.5.13"
  val akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.5.13"

  // js and shared dependencies
  val autowire = Def.setting("com.lihaoyi" %%% "autowire" % "0.2.6")
  val dom = Def.setting("org.scala-js" %%% "scalajs-dom" % "0.9.3")
  val upickle = Def.setting("com.lihaoyi" %%% "upickle" % "0.4.4")
}
