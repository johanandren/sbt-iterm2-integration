name := """sbt-iterm2-integration"""
organization := "com.markatta"
version := "0.1-SNAPSHOT"

sbtPlugin := true

bintrayPackageLabels := Seq("sbt","plugin")
bintrayVcsUrl := Some("""git@github.com:com.markatta/sbt-iterm2-integration.git""")

initialCommands in console := """import com.markatta._"""

// set up 'scripted; sbt plugin for testing sbt plugins
scriptedLaunchOpts ++=
  Seq("-Xmx1024M", "-Dplugin.version=" + version.value)
