name := """sbt-iterm2-integration"""
organization := "com.markatta"

sbtPlugin := true

licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

enablePlugins(GitVersioning)
publishMavenStyle := false
bintrayRepository := "sbt-plugins"
bintrayOrganization in bintray := None
bintrayPackageLabels := Seq("sbt","plugin")
bintrayVcsUrl := Some("""git@github.com:johanandren/sbt-iterm2-integration.git""")

