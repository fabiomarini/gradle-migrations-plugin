package com.github.marceloemanoel.gradle.migrations.tasks

import org.apache.ibatis.migration.commands.ScriptCommand
import org.gradle.api.tasks.TaskAction
import org.gradle.cli.CommandLineArgumentException

import com.github.marceloemanoel.gradle.migrations.helper.CommandHelper

class ScriptTask extends MigrationTask {
	
    public ScriptTask() {
        setDescription("Create a migration script file. Configurable params: v1, v2")
    }
    
    @TaskAction
    def executeMigrations() {
		if ((v1 == null || v1.isEmpty()) && (v2 == null || v2.isEmpty())) {
			def msg = "Please provide the migration versions the v1 and v2 parameter:\n" +
					  "Usage: gradle migrateScript -Pv1=\"start version\" -Pv2=\"end version\""
			throw new CommandLineArgumentException(msg)
		}
		
        def command = new ScriptCommand(selectedOptions)
        CommandHelper.updateDriverClassLoader(project, command)
		String range = v1 + " " + v2
        command.execute(range)
    }
	
	def String getV1() {
		if(project.hasProperty("v1")) {
			//Workaround - subtracts 1 to the v1 otherwise it is not selected
			return new BigDecimal(project.v1).subtract(BigDecimal.ONE).toPlainString()
		}
		""
	}

	def String getV2() {
		if(project.hasProperty("v2")) {
			return new BigDecimal(project.v2).toPlainString()
		}
		""
	}
}
