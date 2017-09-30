
name := "Meerkat"

organization := "org.meerkat"

version := "0.1.0"

scalaVersion := "2.11.6"

parallelExecution in Test := false

logBuffered in Test := false

unmanagedSourceDirectories in Compile += baseDirectory.value / "src" / "macros"

libraryDependencies ++= Seq(
	"org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
	"junit" % "junit" % "4.11",
	"com.google.guava" % "guava-testlib" % "18.0",
	"commons-io" % "commons-io" % "2.4",
	"org.bitbucket.inkytonik.dsinfo" %% "dsinfo" % "0.4.0",
	"org.scala-graph" % "graph-core_2.11" % "1.11.4",
	"org.json4s" %% "json4s-jackson" % "3.4.2"
)
