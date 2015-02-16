/**
 * (C) Copyright 2014 Whisper.io.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Maxime ESCOURBIAC
 */
package com.whisperio.data.jpa;

import com.whisperio.data.entity.Project;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test class for ProjectController.
 *
 * @author Maxime ESCOURBIAC
 */
public class ProjectControllerTest {

    /**
     * Default constructor.
     */
    public ProjectControllerTest() {
    }

    /**
     * Test of create method, of class ProjectController.
     */
    @Test
    public void testCreate() {
        System.out.println("ProjectController:Create");
        ProjectController projectController = new ProjectController();
        String name = "Test Create";
        String keyName = "TCE";
        String description = "Project creation test.";
        Date creationDate = new Date();
        Project project = new Project(name, keyName, description, creationDate);
        Project projectResult = projectController.create(project);

        //Check Project properties.
        assertNotNull(projectResult.getId());
        assertEquals(name, projectResult.getName());
        assertEquals(keyName, projectResult.getKeyName());
        assertEquals(description, projectResult.getDescription());
        assertEquals(creationDate, projectResult.getCreationDate());

        projectController.destroy(projectResult);
    }

    /**
     * Test of destroy method, of class ProjectController.
     */
    @Test
    public void testDestroy() {
        System.out.println("ProjectController:Destroy");
        ProjectController projectController = new ProjectController();
        Date creationDate = new Date();
        String name = "Test Destroy";
        String keyName = "TDY";
        String description = "Project destroy test.";
        Project project = new Project(name, keyName, description, creationDate);
        project = projectController.create(project);
        assertTrue(projectController.destroy(project));
    }

    /**
     * Test of getProjects method, of class ProjectController.
     */
    @Test
    public void testGetProject() {
        System.out.println("ProjectController:GetProjects");
        ProjectController instance = new ProjectController();
        Date creationDate = new Date();
        Project project1 = new Project("Test Get Project 1", "TG1", "Project Get Project 1 test.", creationDate);
        Project project2 = new Project("Test Get Project 2", "TG2", "Project Get Project 2 test.", creationDate);
        Project project3 = new Project("Test Get Project 3", "TG3", "Project Get Project 3 test.", creationDate);
        project1 = instance.create(project1);
        project2 = instance.create(project2);
        project3 = instance.create(project3);
        List result = instance.getAllProjects();
        assertTrue(result.contains(project1));
        assertTrue(result.contains(project2));
        assertTrue(result.contains(project3));
        instance.destroy(project1);
        instance.destroy(project2);
        instance.destroy(project3);
    }

    /**
     * Test of getProjectByKeyName method, of class ProjectController.
     */
    @Test
    public void testGetProjectByKeyName() {
        System.out.println("ProjectController:GetProjectByKeyName");
        Date creationDate = new Date();
        String name = "Test Get Project By KeyName";
        String keyName = "TGK";
        String description = "Project Get Project By KeyName.";
        Project project = new Project(name, keyName, description, creationDate);
        ProjectController instance = new ProjectController();
        instance.create(project);

        //Check true case.
        Project projectResult = instance.getProjectByKeyName(keyName);
        assertEquals(name, projectResult.getName());
        assertEquals(keyName, projectResult.getKeyName());
        assertEquals(description, projectResult.getDescription());
        assertEquals(creationDate, projectResult.getCreationDate());
        instance.destroy(projectResult);

        //Check wrong case.
        projectResult = instance.getProjectByKeyName("FCE");
        assertNull(projectResult);
    }
}
