package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.BootstrapCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper;

class BootstrapTask extends MigrationTask {

    public BootstrapTask(){
        setDescription("Use an existing database structure as an starting point for migrations")
    }

    @TaskAction
    void bootstrap() {
        def command = new BootstrapCommand(selectedOptions)
        CommandHelper.updateDriverClassLoader(project, command)
        command.execute()
    }
}
