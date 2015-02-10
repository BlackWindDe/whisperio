/**
 * (C) Copyright 2014 Whisper.io.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Maxime ESCOURBIAC
 */
package com.whisperio.data.jpa;

import com.whisperio.data.entity.Project;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
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
     * Init the test environment.
     */
    @BeforeClass
    public static void initContext() {
    }

    /**
     * Destroy the test environment.
     */
    @AfterClass
    public static void destroyContext() {
    }

    /**
     * Clean the users after each test.
     */
    @After
    public void cleanData() {
    }

    /**
     * Test of create method, of class ProjectController.
     */
    @Test
    public void testCreate() {
        System.out.println("ProjectController:Create");
        ProjectController projectController = new ProjectController();
        Date creationDate = new Date();
        Project project = new Project("Test Create", "TCE", "Project creation test.", creationDate);
        Project projectResult = projectController.create(project);

        //Check Project properties.
        assertNotNull(projectResult.getId());
        assertEquals(project.getName(), projectResult.getName());
        assertEquals(project.getKeyName(), projectResult.getKeyName());
        assertEquals(project.getDescription(), projectResult.getDescription());
        assertEquals(project.getCreationDate(), projectResult.getCreationDate());

        projectController.destroy(project);
    }

    /**
     * Test of destroy method, of class ProjectController.
     */
    @Test
    public void testDestroy() {
        System.out.println("ProjectController:Destroy");
        ProjectController projectController = new ProjectController();
        Date creationDate = new Date();
        Project project = new Project("Test Destroy", "TDY", "Project destroy test.", creationDate);
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
        List result = instance.getProjects();
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
        Project project = new Project("Test Get Project By KeyName", "TGK", "Project Get Project By KeyName.", creationDate);
        ProjectController instance = new ProjectController();
        instance.create(project);

        //Check true case.
        Project projectResult = instance.getProjectByKeyName(project.getKeyName());
        assertEquals(project.getName(), projectResult.getName());
        assertEquals(project.getKeyName(), projectResult.getKeyName());
        assertEquals(project.getDescription(), projectResult.getDescription());
        assertEquals(project.getCreationDate(), projectResult.getCreationDate());
        instance.destroy(projectResult);

        //Check wrong case.
        projectResult = instance.getProjectByKeyName("FCE");
        assertNull(projectResult);
    }
}
