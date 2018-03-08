package com.markatta

import sbt.{Def, _}
import sbt.Keys._
import sbt.plugins.JvmPlugin

object Sbtiterm2integrationPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin

  val promptMark =  "\u001b]133;A\u0007"
  val endOfPrompt = "\u001b]133;B\u0007"

  object autoImport {
    val setIterm2ProfileOnLoad = settingKey[Option[String]]("Set iTerm2 profile to this name when sbt starts")
    val addIterm2PromptMarks = settingKey[Boolean]("Add iTerm2 marks for each prompt")
    val touchBarKeyLabels = settingKey[Map[String, String]]("iTerm2 touch bar key mappings")
  }

  import autoImport._

  @volatile private var hookAdded = false

  override lazy val projectSettings = Seq.empty
  override lazy val buildSettings = Seq.empty
  override lazy val globalSettings = Seq(
    addIterm2PromptMarks := true,
    setIterm2ProfileOnLoad := None,
    onLoad := {
      state: State => {
        print("\u001b]1337;ShellIntegrationVersion=6;shell=sbt\u0007")
        if (!hookAdded) {
          hookAdded = true
          setIterm2ProfileOnLoad.value.foreach { name =>
            print(s"\u001b]1337;SetProfile=${name}\u0007")
            // restore original profile if possible
            sys.env.get("ITERM_PROFILE").foreach { originalProfile =>
              java.lang.Runtime.getRuntime.addShutdownHook(new Thread(new Runnable() {
                override def run(): Unit = {
                  print(s"\u001b]1337;SetProfile=${originalProfile}\u0007")
                }
              }))
            }
          }
        }
        onLoad.value(state)
      }
    },
    shellPrompt := {
      val original = shellPrompt.value
      if (addIterm2PromptMarks.value) {
        (state: State) => {
          val string = original(state)
          promptMark + string + endOfPrompt
        }
      } else original
    }
  )
}