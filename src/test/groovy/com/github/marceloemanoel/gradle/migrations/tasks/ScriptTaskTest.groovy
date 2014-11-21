package com.github.marceloemanoel.gradle.migrations.tasks

import static org.junit.Assert.*

import org.gradle.api.Project
import org.gradle.cli.CommandLineArgumentException
import org.gradle.testfixtures.ProjectBuilder
import org.junit.Before
import org.junit.Test

import com.github.marceloemanoel.gradle.migrations.plugin.MigrationsPlugin

class ScriptTaskTest {

    def Project project
    def ScriptTask task

    @Before
    void setUp() {
        project = ProjectBuilder.builder().build()
        project.apply plugin: MigrationsPlugin
        task = project.migrateScript
    }

    @Test
    void ifNullVersionsAreGivenTheBuildFails() {
        task.v1 = null
		task.v2 = null
        expectCommandLineError()
    }
	
    @Test
    void ifEmptyVersionsIsGivenTheBuildFails() {
        task.v1 = ""
		task.v2 = ""
        expectCommandLineError()
    }
	
    private expectCommandLineError() {
        try {
            task.executeMigrations()
            fail()
        }
        catch(CommandLineArgumentException e){
            def msg = "Please provide the migration versions the v1 and v2 parameter:\n" +
					  "Usage: gradle migrateScript -Pv1=\"start version\" -Pv2=\"end version\""
            assertEquals(msg, e.message)
        }
    }
}
