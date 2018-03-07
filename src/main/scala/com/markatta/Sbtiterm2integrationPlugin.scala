package com.markatta

import sbt._
import sbt.Keys._
import sbt.plugins.JvmPlugin

object Sbtiterm2integrationPlugin extends AutoPlugin {

  override def trigger = allRequirements
  override def requires = JvmPlugin

  val promptMark =  "\033]133;A\007"
  val endOfPrompt = "\033]133;B\007"

  override lazy val projectSettings = Seq(
    onLoad := {
      state => {
        print("\033]1337;ShellIntegrationVersion=6;shell=sbt\007")
        onLoad.value(state)
      }
    },
    shellPrompt := {
      val original = shellPrompt.value
      (state: State) => {
        val string = original(state)
        promptMark + string + endOfPrompt
      }
    }

  )


  override lazy val buildSettings = Seq()

  override lazy val globalSettings = Seq()
}