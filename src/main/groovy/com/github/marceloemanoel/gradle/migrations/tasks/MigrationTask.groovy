package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.options.SelectedOptions
import org.apache.ibatis.migration.options.SelectedPaths
import org.gradle.api.DefaultTask
import org.gradle.api.logging.LogLevel

class MigrationTask extends DefaultTask {
    
    protected MigrationTask(){
        setGroup("Migration")
        logging.captureStandardOutput LogLevel.INFO
    }
    
    def File getBaseDir(){
        if(project.hasProperty("b")) {
            return project.file(project.b)
        }
        project.file(project.migrations.baseDir)
    }
    
    def String getEnvironment() {
        if(project.hasProperty("e")) {
            return project.e
        }
        if(project.hasProperty("env")) {
            return project.env
        }
        if(project.hasProperty("environment")) {
            return project.environment
        }
        project.migrations.environment
    }
    
    def Boolean getForce() {
        if(project.hasProperty("f")) {
            return project.f
        }
        project.migrations.force
    }
	
	def SelectedOptions getSelectedOptions() {
		def selectedOptions = new SelectedOptions();
		selectedOptions.environment = environment
		selectedOptions.force = force
		def paths = new SelectedPaths();
		paths.basePath = baseDir
		selectedOptions.paths = paths
		
		return selectedOptions
	}
}
