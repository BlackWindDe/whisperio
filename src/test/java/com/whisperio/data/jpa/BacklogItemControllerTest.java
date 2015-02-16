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

import com.whisperio.data.entity.BacklogItem;
import com.whisperio.data.entity.Project;
import com.whisperio.data.entity.Release;
import com.whisperio.data.entity.Sprint;
import com.whisperio.data.entity.User;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for BacklogItemController.
 *
 * @author Maxime ESCOURBIAC
 */
public class BacklogItemControllerTest {

    private static User creator;
    private static Project project;
    private static Release release;
    private static Sprint sprint;
    private static UserController userController;
    private static ProjectController projectController;
    private static ReleaseController releaseController;
    private static SprintController sprintController;

    /**
     * Default constructor.
     */
    public BacklogItemControllerTest() {
    }

    /**
     * Init the test environment.
     */
    @BeforeClass
    public static void initContext() {
        Date date = new Date();
        userController = new UserController();
        creator = new User("creator@whisper.io", "Forename", "LastName");
        creator = userController.create(creator);

        projectController = new ProjectController();
        project = new Project("Test BacklogItem", "TBM", "Project BacklogItem test.", date);
        project = projectController.create(project);

        releaseController = new ReleaseController();
        release = new Release("Release Test Backlog Item", 1, date, date, 0, true, project);
        release = releaseController.create(release);

        sprintController = new SprintController();
        sprint = new Sprint("Sprint Test Backlog Item", 1, date, date, true, release);
        sprint = sprintController.create(sprint);
    }

    /**
     * Destroy the test environment.
     */
    @AfterClass
    public static void destroyContext() {
        sprintController.destroy(sprint);
        releaseController.destroy(release);
        projectController.destroy(project);
        userController.destroy(creator);
    }

    /**
     * refresh data after each test.
     */
    @After
    public void refresh() {
        creator = userController.refresh(creator);
        sprint = sprintController.refresh(sprint);
        release = releaseController.refresh(release);
        project = projectController.refresh(project);
    }

    /**
     * Test of create method, of class SprintController.
     */
    @Test
    public void testCreate() {
        System.out.println("BacklogItemController:Create");
        BacklogItemController backlogItemController = new BacklogItemController();
        Date creationDate = new Date();
        Date lastUpdateDate = new Date();
        String title = "Test Create BI";
        String description = "Test Create BI Description";
        BacklogItem backlogItem = new BacklogItem(title, description, creationDate,
                lastUpdateDate, project, release, sprint, creator);
        BacklogItem backlogItemResult = backlogItemController.create(backlogItem);

        //Check Sprint properties.
        assertNotNull(backlogItemResult.getId());
        assertEquals(title, backlogItemResult.getTitle());
        assertEquals(description, backlogItemResult.getDescription());
        assertEquals(creationDate, backlogItemResult.getCreationDate());
        assertEquals(lastUpdateDate, backlogItemResult.getLastUpdateDate());
        assertEquals(project, backlogItemResult.getProject());
        assertEquals(release, backlogItemResult.getRelease());
        assertEquals(sprint, backlogItemResult.getSprint());
        assertEquals(creator, backlogItemResult.getCreator());

        //Check project properties.
        project = projectController.refresh(project);
        assertTrue(project.getBacklogItems().contains(backlogItemResult));

        //Check release properties.
        release = releaseController.refresh(release);
        assertTrue(release.getBacklogItems().contains(backlogItemResult));

        //Check sprint properties.
        sprint = sprintController.refresh(sprint);
        assertTrue(sprint.getBacklogItems().contains(backlogItemResult));

        //Check creator properties.
        creator = userController.refresh(creator);
        assertTrue(creator.getBacklogItemsCreated().contains(backlogItemResult));

        backlogItemController.destroy(backlogItemResult);
    }

    /**
     * Test of destroy method, of class BacklogItemController.
     */
    @Test
    public void testDestroy() {
        System.out.println("BacklogItemController:Destroy");
        BacklogItemController backlogItemController = new BacklogItemController();
        Date creationDate = new Date();
        Date lastUpdateDate = new Date();
        String title = "Test Destroy BI";
        String description = "Test Destroy BI Description";
        BacklogItem backlogItem = new BacklogItem(title, description,
                creationDate, lastUpdateDate, project, release, sprint, creator);
        backlogItem = backlogItemController.create(backlogItem);
        assertTrue(backlogItemController.destroy(backlogItem));
    }
}
