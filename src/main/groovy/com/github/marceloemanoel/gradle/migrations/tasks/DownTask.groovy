package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.DownCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper

class DownTask extends SteppableMigrationTask {

    public DownTask(){
        setDescription("Rewinds the database to a previous stage. Configurable params: steps")
    }

    @TaskAction
    void status() {
        def command = new DownCommand(selectedOptions)
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute(steps)
    }
}
