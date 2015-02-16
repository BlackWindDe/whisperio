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
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for SprintController.
 *
 * @author Maxime ESCOURBIAC
 */
public class SprintControllerTest {

    private static Project project;
    private static Release release;
    private static ProjectController projectController;
    private static ReleaseController releaseController;

    /**
     * Default constructor.
     */
    public SprintControllerTest() {
    }

    /**
     * Init the test environment.
     */
    @BeforeClass
    public static void initContext() {

        projectController = new ProjectController();
        Date date = new Date();
        project = new Project("Test Sprint ", "TST", "Project Sprint test.", date);
        project = projectController.create(project);

        releaseController = new ReleaseController();
        release = new Release("Release Test Sprint", 1, date, date, 0, true, project);
        release = releaseController.create(release);
    }

    /**
     * Destroy the test environment.
     */
    @AfterClass
    public static void destroyContext() {
        releaseController.destroy(release);
        projectController.destroy(project);
    }

    /**
     * Refresh data after each test.
     */
    @After
    public void refresh() {
        project = projectController.refresh(project);
        release = releaseController.refresh(release);
    }

    /**
     * Test of create method, of class SprintController.
     */
    @Test
    public void testCreate() {
        System.out.println("SprintController:Create");
        SprintController sprintController = new SprintController();
        Date startDate = new Date();
        Date endDate = new Date();
        String name = "Test Create Sprint";
        int sprintNumber = 1;
        boolean isActive = true;
        Sprint sprint = new Sprint(name, sprintNumber, startDate, endDate, isActive, release);
        Sprint sprintResult = sprintController.create(sprint);

        //Check Sprint properties.
        assertNotNull(sprintResult.getId());
        assertEquals(name, sprintResult.getName());
        assertEquals(sprintNumber, sprintResult.getSprintNumber());
        assertEquals(startDate, sprintResult.getStartDate());
        assertEquals(endDate, sprintResult.getEndDate());
        assertEquals(isActive, sprintResult.isActive());
        assertEquals(release, sprintResult.getRelease());

        //Check release properties.
        release = releaseController.refresh(release);
        assertTrue(release.getSprints().contains(sprintResult));

        sprintController.destroy(sprint);
    }

    /**
     * Test of destroy method, of class SprintController.
     */
    @Test
    public void testDestroy() {
        System.out.println("SprintController:Destroy");
        SprintController sprintController = new SprintController();
        Date creationDate = new Date();
        Date stopDate = new Date();
        Sprint sprint = sprintController.create(new Sprint("Test Destroy Sprint", 1, creationDate, stopDate, true, release));
        assertTrue(sprintController.destroy(sprint));
    }
}
