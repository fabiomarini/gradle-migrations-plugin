package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.StatusCommand
import org.apache.ibatis.migration.options.SelectedOptions
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper


class StatusTask extends MigrationTask {
    
    public StatusTask() {
        setDescription("Shows current database status")
    }

    @TaskAction
    void status() {
        def command = new StatusCommand(selectedOptions)
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute()
    }
}
