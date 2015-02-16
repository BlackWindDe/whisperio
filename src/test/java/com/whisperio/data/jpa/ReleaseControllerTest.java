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
import java.math.BigDecimal;
import java.util.Date;
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
        int estimatedNumberOfSprintToEmpty = 10;
        BigDecimal estimatedRemainingPointEndOfRelease = new BigDecimal("45.5");
        release.setNumberOfSprint(numberOfSprint);
        release.setIsActive(isActive);
        release.setEndDate(endDate);
        release.setEstimatedNumberOfSprintToEmpty(estimatedNumberOfSprintToEmpty);
        release.setEstimatedRemainingPointEndOfRelease(estimatedRemainingPointEndOfRelease);
        release = releaseController.edit(release);
        assertEquals(name, release.getName());
        assertEquals(endDate, release.getEndDate());
        assertEquals(numberOfSprint, release.getNumberOfSprint());
        assertEquals(isActive, release.isActive());
        assertEquals(estimatedNumberOfSprintToEmpty, release.getEstimatedNumberOfSprintToEmpty());
        assertEquals(estimatedRemainingPointEndOfRelease, release.getEstimatedRemainingPointEndOfRelease());

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
}
