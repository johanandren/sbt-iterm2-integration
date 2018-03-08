# sbt-iterm2-integration

Plugin that integrates with iTerm2 on MacOS

## Usage

This plugin requires sbt 1.0.0+

As this is a machine/user-local plugin rather than one to add to a project it is best 
to enable it globally by adding the plugin in a `~/.sbt/1.0/plugins/iterm2.sbt` file, 
the settings can also be defined globally by putting them one level above that file - 
`~/.sbt/plugins/iterm2.sbt`   

### Prompt marks
By default prompt mark reporting to iTerm2 is enabled, this allows for scrolling between
prompts, alert on next mark etc. (see https://iterm2.com/documentation-shell-integration.html for more details)
It can be disabled by setting `addIterm2PromptMarks := false`

### Profile switching
Switching to a specific iTerm2 profile when sbt loads can be done by setting 
`setIterm2ProfileOnLoad in Global := Some("profile-name")`. When the sbt JVM exits 
the original profile is restored.

