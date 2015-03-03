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
import com.whisperio.data.entity.Release;
import com.whisperio.data.entity.Sprint;
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

    /**
     * Test of getActiveRelease method, of class ProjectController.
     */
    @Test
    public void testGetActiveRelease() {
        System.out.println("ProjectController:GetProjectActiveRelease");

        ProjectController projectController = new ProjectController();
        ReleaseController releaseController = new ReleaseController();

        Project project1 = new Project("Test Get Active Release 1", "TA1", "Project Get Active Release 1 test.", new Date());
        Project project2 = new Project("Test Get Active Release 2", "TA2", "Project Get Active Release 2 test.", new Date());
        Project project3 = new Project("Test Get Active Release 3", "TA3", "Project Get Active Release 3 test.", new Date());
        Project project4 = new Project("Test Get Active Release 4", "TA4", "Project Get Active Release 4 test.", new Date());
        project1 = projectController.create(project1);
        project2 = projectController.create(project2);
        project3 = projectController.create(project3);
        project4 = projectController.create(project4);

        //Configure Release.
        Release release1 = new Release("Test Get Active Release1", 1, new Date(), new Date(), 5, true, project3);
        release1 = releaseController.create(release1);
        Release release2 = new Release("Test Get Active Release2", 2, new Date(), new Date(), 5, true, project3);
        release2 = releaseController.create(release2);
        Release release3 = new Release("Test Get Active Release3", 3, new Date(), new Date(), 5, false, project2);
        release3 = releaseController.create(release3);
        Release release4 = new Release("Test Get Active Release4", 4, new Date(), new Date(), 5, false, project2);
        release4 = releaseController.create(release4);
        Release release5 = new Release("Test Get Active Release5", 5, new Date(), new Date(), 5, false, project3);
        release5 = releaseController.create(release5);
        Release release6 = new Release("Test Get Active Release6", 6, new Date(), new Date(), 5, true, project4);
        release6 = releaseController.create(release6);
        Release release7 = new Release("Test Get Active Release7", 7, new Date(), new Date(), 5, false, project4);
        release7 = releaseController.create(release7);
        Release release8 = new Release("Test Get Active Release8", 8, new Date(), new Date(), 5, false, project4);
        release8 = releaseController.create(release8);

        project1 = projectController.refresh(project1);
        project2 = projectController.refresh(project2);
        project3 = projectController.refresh(project3);
        project4 = projectController.refresh(project4);

        //Project with 0 release.
        Release releaseResult = projectController.getProjectActiveRelease(project1);
        assertNull(releaseResult);

        //Project with 0 active release.
        releaseResult = projectController.getProjectActiveRelease(project2);
        assertNull(releaseResult);

        //Project with more than 1 active release.
        releaseResult = projectController.getProjectActiveRelease(project3);
        assertNull(releaseResult);

        //Good project.
        releaseResult = projectController.getProjectActiveRelease(project4);
        assertEquals(release6, releaseResult);

        //Destroy releases.
        releaseController.destroy(release1);
        releaseController.destroy(release2);
        releaseController.destroy(release3);
        releaseController.destroy(release4);
        releaseController.destroy(release5);
        releaseController.destroy(release6);
        releaseController.destroy(release7);
        releaseController.destroy(release8);

        //Destroy project
        projectController.destroy(projectController.refresh(project1));
        projectController.destroy(projectController.refresh(project2));
        projectController.destroy(projectController.refresh(project3));
        projectController.destroy(projectController.refresh(project4));
    }

    /**
     * Test of getClosedSprints method, of class ProjectController.
     */
    @Test
    public void testGetClosedSprints() {
        System.out.println("ProjectController:GetClosedSprints");
        ProjectController projectController = new ProjectController();
        ReleaseController releaseController = new ReleaseController();
        SprintController sprintController = new SprintController();

        Project project1 = new Project("Test Get Closed Sprints 1", "TA1", "Project Get Closed Sprints test 1.", new Date());
        Project project2 = new Project("Test Get Closed Sprints 2", "TA2", "Project Get Closed Sprints test 2.", new Date());
        project1 = projectController.create(project1);
        project2 = projectController.create(project2);
        Release release1 = new Release("Test Get Closed Sprint 1", 1, new Date(), new Date(), 5, false, project1);
        Release release2 = new Release("Test Get Closed Sprint 2", 1, new Date(), new Date(), 5, true, project1);
        release1 = releaseController.create(release1);
        release2 = releaseController.create(release2);
        Sprint sprint1 = new Sprint("Test Get Closed Sprint 1", 1, new Date(), new Date(), false, false, release1);
        Sprint sprint2 = new Sprint("Test Get Closed Sprint 2", 2, new Date(), new Date(), false, true, release1);
        Sprint sprint3 = new Sprint("Test Get Closed Sprint 3", 1, new Date(), new Date(), false, true, release2);
        Sprint sprint4 = new Sprint("Test Get Closed Sprint 4", 2, new Date(), new Date(), true, false, release2);
        sprint1 = sprintController.create(sprint1);
        sprint2 = sprintController.create(sprint2);
        sprint3 = sprintController.create(sprint3);
        sprint4 = sprintController.create(sprint4);

        //Good Project.
        List<Sprint> projectClosedSprints = projectController.getProjectClosedSprints(project1);
        assertEquals(2, projectClosedSprints.size());
        assertTrue(projectClosedSprints.contains(sprint2));
        assertTrue(projectClosedSprints.contains(sprint3));

        //Project with no closed sprints.
        assertTrue(projectController.getProjectClosedSprints(project2).isEmpty());

        sprintController.destroy(sprint1);
        sprintController.destroy(sprint2);
        sprintController.destroy(sprint3);
        sprintController.destroy(sprint4);
        releaseController.destroy(release1);
        releaseController.destroy(release2);
        projectController.destroy(project1);
        projectController.destroy(project2);
    }
}
