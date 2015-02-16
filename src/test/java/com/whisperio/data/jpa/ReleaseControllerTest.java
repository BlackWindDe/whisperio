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

    private static Project project1;
    private static Project project2;
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
        project1 = new Project("Test Release 1", "TR1", "Project Release 1 test.", date);
        project1 = projectController.create(project1);
        project2 = new Project("Test Release 2", "TR2", "Project Release 2 test.", date);
        project2 = projectController.create(project2);
    }

    /**
     * Destroy the test environment.
     */
    @AfterClass
    public static void destroyContext() {
        projectController.destroy(project1);
        projectController.destroy(project2);
    }

    /**
     * Clean the users after each test.
     */
    @After
    public void cleanUsers() {
        project1 = projectController.refresh(project1);
        project2 = projectController.refresh(project2);
    }

    /**
     * Test of create method, of class ReleaseController.
     */
    @Test
    public void testCreate() {
        System.out.println("ReleaseController:Create");
        ReleaseController releaseController = new ReleaseController();
        Date creationDate = new Date();
        Date endDate = new Date();
        Release release = new Release("Test Create Release", 1, creationDate, endDate, 3, true, project1);
        Release releaseResult = releaseController.create(release);

        //Check Release properties.
        assertNotNull(releaseResult.getId());
        assertEquals(release.getName(), releaseResult.getName());
        assertEquals(release.getReleaseNumber(), releaseResult.getReleaseNumber());
        assertEquals(release.getStartDate(), releaseResult.getStartDate());
        assertEquals(release.getEndDate(), releaseResult.getEndDate());
        assertEquals(release.getNumberOfSprint(), releaseResult.getNumberOfSprint());
        assertEquals(release.isActive(), releaseResult.isActive());
        assertEquals(release.getProject(), releaseResult.getProject());

        //Check project properties.
        project1 = projectController.refresh(project1);
        assertTrue(project1.getReleases().contains(releaseResult));

        releaseController.destroy(release);
    }

    /**
     * Test of updateStatistics method, of class ReleaseController.
     */
    @Test
    public void testUpdateStatistics() {
        System.out.println("ReleaseController:UpdateStatistics");
        ReleaseController releaseController = new ReleaseController();
        Date creationDate = new Date();
        Date endDate = new Date();
        Release release = new Release("Test Update Stats Release", 1, creationDate, endDate, 3, true, project1);
        release = releaseController.create(release);

        int estimatedNumberOfSprintToEmpty = 10;
        BigDecimal estimatedRemainingPointEndOfRelease = new BigDecimal("45.5");
        release.setEstimatedNumberOfSprintToEmpty(estimatedNumberOfSprintToEmpty);
        release.setEstimatedRemainingPointEndOfRelease(estimatedRemainingPointEndOfRelease);
        release = releaseController.updateStatistics(release);
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
        Release release = releaseController.create(new Release("Test Destroy Release", 1, creationDate, endDate, 0, true, project1));
        assertTrue(releaseController.destroy(release));
    }
}
