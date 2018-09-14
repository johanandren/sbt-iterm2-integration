# sbt-iterm2-integration

Plugin that integrates with iTerm2 on MacOS

## Usage

This plugin requires sbt 1.0.0+

As this is a machine/user-local plugin rather than one to add to a project it is best 
to enable it globally by adding the plugin in a `~/.sbt/1.0/plugins/iterm2.sbt` file:

```
addSbtPlugin("com.markatta" % "sbt-iterm2-integration" % "0.1")
```

### Prompt marks
By default prompt mark reporting to iTerm2 is enabled, this allows for scrolling between
prompts, alert on next mark etc. (see https://iterm2.com/documentation-shell-integration.html for more details)
It can be disabled by setting `addIterm2PromptMarks := false`

If a project has a custom prompt already this prompt marking will not work.

### Profile switching
Switching to a specific iTerm2 profile when sbt loads can be done by setting 
`setIterm2ProfileOnLoad in Global := Some("profile-name")`. When the sbt JVM exits 
the original profile is restored.

### Current project set as user var
The root project name is published as a iTerm2 user var named `currentProject`
making it possible to use in a badge like so: `\(user.currentProject)`
