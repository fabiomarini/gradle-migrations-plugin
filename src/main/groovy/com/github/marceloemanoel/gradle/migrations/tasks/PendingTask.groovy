package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.PendingCommand
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper


class PendingTask extends MigrationTask {

    public PendingTask(){
        setDescription("Executes pending migrations that have a timestamp lower than the last executed migration")
    }

    @TaskAction
    def executeMigrations() {
        def command = new PendingCommand(selectedOptions)
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute()
    }
}
