package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.UpCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper

class UpTask extends SteppableMigrationTask {

    public UpTask(){
        setDescription("Apply any pending migration following creation order. Configurable params: steps")
    }

    @TaskAction
    def executeMigrations() {
        def command = new UpCommand(selectedOptions)
        CommandHelper.updateDriverClassLoader(getProject(), command)
        command.execute(steps)
    }
}