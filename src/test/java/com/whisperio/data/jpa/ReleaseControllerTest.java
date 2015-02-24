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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for ReleaseController.
 *
 * @author Maxime ESCOURBIAC
 */
public class ReleaseControllerTest {

    private static Project project;
    private static ProjectController projectController;

    /**
     * Default constructor.
     */
    public ReleaseControllerTest() {
    }

    /**
     * Init the test environment.
     */
    @BeforeClass
    public static void initContext() {
        projectController = new ProjectController();
        Date date = new Date();
        project = new Project("Test Release 1", "TR1", "Project Release 1 test.", date);
        project = projectController.create(project);
    }

    /**
     * Destroy the test environment.
     */
    @AfterClass
    public static void destroyContext() {
        projectController.destroy(project);
    }

    /**
     * Clean the users after each test.
     */
    @After
    public void refresh() {
        project = projectController.refresh(project);
    }

    /**
     * Test of create method, of class ReleaseController.
     */
    @Test
    public void testCreate() {
        System.out.println("ReleaseController:Create");
        ReleaseController releaseController = new ReleaseController();
        String name = "Test Create Release";
        int releaseNumber = 1;
        int numberOfSprint = 3;
        boolean isActive = true;
        Date startDate = new Date();
        Date endDate = new Date();
        Release release = new Release(name, releaseNumber, startDate, endDate, numberOfSprint, isActive, project);
        Release releaseResult = releaseController.create(release);

        //Check Release properties.
        assertNotNull(releaseResult.getId());
        assertEquals(name, releaseResult.getName());
        assertEquals(releaseNumber, releaseResult.getReleaseNumber());
        assertEquals(startDate, releaseResult.getStartDate());
        assertEquals(endDate, releaseResult.getEndDate());
        assertEquals(numberOfSprint, releaseResult.getNumberOfSprint());
        assertEquals(isActive, releaseResult.isActive());
        assertEquals(project, releaseResult.getProject());

        //Check project properties.
        project = projectController.refresh(project);
        assertTrue(project.getReleases().contains(releaseResult));

        releaseController.destroy(releaseResult);
    }

    /**
     * Test of updateStatistics method, of class ReleaseController.
     */
    @Test
    public void testUpdateStatistics() {
        System.out.println("ReleaseController:UpdateStatistics");
        ReleaseController releaseController = new ReleaseController();
        String name = "Test Update Stats Release";
        int releaseNumber = 1;
        int numberOfSprint = 3;
        boolean isActive = true;
        Date startDate = new Date();
        Date endDate = new Date();
        Release release = new Release(name, releaseNumber, startDate, endDate, numberOfSprint, isActive, project);
        release = releaseController.create(release);

        numberOfSprint = 5;
        isActive = false;
        endDate = new Date(0);
        release.setNumberOfSprint(numberOfSprint);
        release.setIsActive(isActive);
        release.setEndDate(endDate);
        release = releaseController.edit(release);
        assertEquals(name, release.getName());
        assertEquals(endDate, release.getEndDate());
        assertEquals(numberOfSprint, release.getNumberOfSprint());
        assertEquals(isActive, release.isActive());

        releaseController.destroy(release);
    }

    /**
     * Test of destroy method, of class ReleaseController.
     */
    @Test
    public void testDestroy() {
        System.out.println("ReleaseController:Destroy");
        ReleaseController releaseController = new ReleaseController();
        Date creationDate = new Date();
        Date endDate = new Date();
        Release release = releaseController.create(new Release("Test Destroy Release", 1, creationDate, endDate, 0, true, project));
        assertTrue(releaseController.destroy(release));
    }

    /**
     * Test of getReleaseClosedSprints method, of class ReleaseController.
     */
    @Test
    public void testGetReleaseClosedSprints() {
        System.out.println("ReleaseController:GetReleaseClosedSprints");
        ReleaseController releaseController = new ReleaseController();
        SprintController sprintController = new SprintController();

        Release release = releaseController.create(new Release("Test Get Closed Sprints", 1, new Date(), new Date(), 10, true, project));
        Sprint sprint1 = new Sprint("Test Get Close Sprint1", 1, new Date(), new Date(), false, true, release);
        Sprint sprint2 = new Sprint("Test Get Close Sprint2", 1, new Date(), new Date(), false, true, release);
        Sprint sprint3 = new Sprint("Test Get Close Sprint3", 1, new Date(), new Date(), true, false, release);
        Sprint sprint4 = new Sprint("Test Get Close Sprint4", 1, new Date(), new Date(), false, false, release);
        sprint1 = sprintController.create(sprint1);
        sprint2 = sprintController.create(sprint2);
        sprint3 = sprintController.create(sprint3);
        sprint4 = sprintController.create(sprint4);

        List<Sprint> closedSprints = releaseController.getReleaseClosedSprints(release);
        assertEquals(2, closedSprints.size());
        assertTrue(closedSprints.contains(sprint1));
        assertTrue(closedSprints.contains(sprint2));
        assertTrue(!closedSprints.contains(sprint3));
        assertTrue(!closedSprints.contains(sprint4));

        sprintController.destroy(sprint1);
        sprintController.destroy(sprint2);
        sprintController.destroy(sprint3);
        sprintController.destroy(sprint4);
        releaseController.destroy(release);
    }

    /**
     * Test of getReleaseAverageVelocity method, of class ReleaseController.
     */
    @Test
    public void testGetReleaseAverageVelocity() {
        System.out.println("ReleaseController:GetReleaseAverageVelocity");
        ReleaseController releaseController = new ReleaseController();
        SprintController sprintController = new SprintController();

        Release release = releaseController.create(new Release("Test Average Velocity", 1, new Date(), new Date(), 10, true, project));
        Sprint sprint1 = new Sprint("Test Average Velocity1", 1, new Date(), new Date(), false, true, release);
        sprint1.setVelocity(new BigDecimal(10));
        Sprint sprint2 = new Sprint("Test Average Velocity2", 1, new Date(), new Date(), false, true, release);
        sprint2.setVelocity(new BigDecimal(20));
        Sprint sprint3 = new Sprint("Test Average Velocity3", 1, new Date(), new Date(), true, false, release);
        Sprint sprint4 = new Sprint("Test Average Velocity4", 1, new Date(), new Date(), false, false, release);
        sprint1 = sprintController.create(sprint1);
        sprint2 = sprintController.create(sprint2);
        sprint3 = sprintController.create(sprint3);
        sprint4 = sprintController.create(sprint4);

        BigDecimal releaseAverageVelocity = releaseController.getReleaseAverageVelocity(release);
        assertEquals(new BigDecimal(15), releaseAverageVelocity);

        sprintController.destroy(sprint1);
        sprintController.destroy(sprint2);
        sprintController.destroy(sprint3);
        sprintController.destroy(sprint4);
        releaseController.destroy(release);
    }
}
