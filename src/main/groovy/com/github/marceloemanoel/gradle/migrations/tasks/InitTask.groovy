package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.InitializeCommand
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper

class InitTask extends MigrationTask {

    public InitTask(){
        setDescription("Create migrations repository structure")
    }

    @TaskAction
    void createDirectories() {
        if(baseDir.exists()) {
            if(force){
                logger.info "Deleting directory."
                baseDir.deleteDir()
            }
            else{
                logger.info "Migrations directory already exists: ${baseDir.absolutePath}"
                return
            }
        }

        new InitializeCommand(selectedOptions).execute()

        project.file(new File(baseDir, "drivers")).deleteDir()
        project.file(new File(baseDir, "README")).delete()
        
        logger.info "Directory created at '${baseDir.absolutePath}'."
    }
}