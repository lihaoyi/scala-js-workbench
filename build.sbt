import sbt.Keys._

val defaultSettings = Seq(
  scalacOptions ++= Seq("-feature", "-deprecation"),
  unmanagedSourceDirectories in Compile += baseDirectory.value / "shared" / "main" / "scala",
  unmanagedSourceDirectories in Test += baseDirectory.value / "shared" / "test" / "scala"
)

lazy val root = project.in(file(".")).settings(defaultSettings: _*).settings(
  name := "workbench",
  version := "0.4.4",
  organization := "com.sa",
  scalaVersion := "2.12.12",
  sbtPlugin := true,
  publishArtifact in Test := false,
  publishTo := Some("releases" at "https://nexus.s-art.co.nz/repository/maven-releases"),
  //  publishTo := Some("Nexus" at "http://nexus.financialplatforms.co.nz:8081/nexus/content/repositories/releases"),
  credentials += Credentials(Path.userHome / ".ivy2" / ".credentials.sa"),
  credentials += Credentials(Path.userHome / ".ivy2" / ".credentials.fp"),
  pomExtra :=
    <url>https://github.com/lihaoyi/workbench</url>
      <licenses>
        <license>
          <name>MIT license</name>
          <url>http://www.opensource.org/licenses/mit-license.php</url>
        </license>
      </licenses>
      <scm>
        <url>git://github.com/lihaoyi/workbench.git</url>
        <connection>scm:git://github.com/lihaoyi/workbench.git</connection>
      </scm>
      <developers>
        <developer>
          <id>lihaoyi</id>
          <name>Li Haoyi</name>
          <url>https://github.com/lihaoyi</url>
        </developer>
      </developers>
  ,
  (resources in Compile) += {
    (fullOptJS in(client, Compile)).value
    (artifactPath in(client, Compile, fullOptJS)).value
  },
  addSbtPlugin("org.scala-js" % "sbt-scalajs" % "1.1.1"),
  libraryDependencies ++= Seq(
    Dependencies.akkaHttp,
    Dependencies.akka,
    Dependencies.akkaStream,
    Dependencies.autowire.value,
    Dependencies.upickle.value
  )
)

lazy val client = project.in(file("client"))
  .enablePlugins(ScalaJSPlugin)
  .settings(defaultSettings: _*)
  .settings(
    unmanagedSourceDirectories in Compile += baseDirectory.value / ".." / "shared" / "main" / "scala",
    libraryDependencies ++= Seq(
      Dependencies.autowire.value,
      Dependencies.dom.value,
      Dependencies.upickle.value
    ),
    scalaJSLinkerConfig ~= { _.withSourceMap(false) }
  )
