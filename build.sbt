
name := "Meerkat"

organization := "org.meerkat"

version := "0.1.0"

scalaVersion := "2.12.0"

parallelExecution in Test := false

logBuffered in Test := false

unmanagedSourceDirectories in Compile += baseDirectory.value / "src" / "macros"

libraryDependencies ++= Seq(
    "org.scalatest" % "scalatest_2.12.0-M1" % "2.2.5-M1" % "test",
	"junit" % "junit" % "4.11",
	"com.google.guava" % "guava-testlib" % "18.0",
	"commons-io" % "commons-io" % "2.4",
	"org.bitbucket.inkytonik.dsinfo" %% "dsinfo" % "0.4.0",
	"org.scala-graph" % "graph-core_2.12" % "1.11.4",
	"org.scala-graph" % "graph-dot_2.12" % "1.11.0"
)
