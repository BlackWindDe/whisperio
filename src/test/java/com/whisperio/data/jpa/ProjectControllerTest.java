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
import com.whisperio.data.entity.BacklogItemType;
import com.whisperio.data.entity.ProductBacklogBox;
import com.whisperio.data.entity.Project;
import com.whisperio.data.entity.Release;
import com.whisperio.data.entity.Sprint;
import com.whisperio.data.entity.StoryBusinessValue;
import com.whisperio.data.entity.StoryEstimation;
import com.whisperio.data.entity.User;
import java.math.BigDecimal;
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
    public void testGetProjects() {
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

    /**
     * Test of GetProductBacklogBoxItems methods, of class ProjectController.
     */
    @Test
    public void testGetProductBacklogBoxItems() {
        System.out.println("ProjectController:GetProductBacklogBoxItems");
        Date date = new Date();
        String name = "Test Get ProductBacklog Box Items";
        String keyName = "TGS";
        String description = "Test Get ProductBacklog Box Items.";
        Project project = new Project(name, keyName, description, date);
        ProjectController instance = new ProjectController();
        instance.create(project);

        UserController userController = new UserController();
        User creator = new User("creator@whisper.io", "Username", "Forename", "LastName");
        creator = userController.create(creator);

        StoryEstimationController storyEstimationController = new StoryEstimationController();
        StoryEstimation estimation = new StoryEstimation("0", BigDecimal.ZERO);
        estimation = storyEstimationController.create(estimation);

        StoryBusinessValueController storyBusinessValueController = new StoryBusinessValueController();
        StoryBusinessValue businessValue = new StoryBusinessValue("0", BigDecimal.ZERO);
        businessValue = storyBusinessValueController.create(businessValue);

        //Create PBI.
        BacklogItemController backlogItemController = new BacklogItemController();
        BacklogItem sandbox1 = new BacklogItem("SB1", "SB1", BacklogItemType.IDEA, ProductBacklogBox.SANDBOX,
                estimation, businessValue, date, date, project, null, null, creator);
        BacklogItem sandbox2 = new BacklogItem("SB1", "SB1", BacklogItemType.IDEA, ProductBacklogBox.SANDBOX,
                estimation, businessValue, date, date, project, null, null, creator);
        BacklogItem icebox1 = new BacklogItem("IB1", "IB1", BacklogItemType.IDEA, ProductBacklogBox.ICEBOX,
                estimation, businessValue, date, date, project, null, null, creator);
        BacklogItem icebox2 = new BacklogItem("IB1", "IB1", BacklogItemType.IDEA, ProductBacklogBox.ICEBOX,
                estimation, businessValue, date, date, project, null, null, creator);
        BacklogItem culturebox1 = new BacklogItem("CB1", "CB1", BacklogItemType.IDEA, ProductBacklogBox.CULTUREBOX,
                estimation, businessValue, date, date, project, null, null, creator);
        BacklogItem culturebox2 = new BacklogItem("CB1", "CB1", BacklogItemType.IDEA, ProductBacklogBox.CULTUREBOX,
                estimation, businessValue, date, date, project, null, null, creator);
        BacklogItem startbox1 = new BacklogItem("StB1", "StB1", BacklogItemType.IDEA, ProductBacklogBox.STARTBOX,
                estimation, businessValue, date, date, project, null, null, creator);
        BacklogItem startbox2 = new BacklogItem("StB1", "StB1", BacklogItemType.IDEA, ProductBacklogBox.STARTBOX,
                estimation, businessValue, date, date, project, null, null, creator);
        BacklogItem sprintbox1 = new BacklogItem("SpB1", "SpB1", BacklogItemType.IDEA, ProductBacklogBox.SPRINTBOX,
                estimation, businessValue, date, date, project, null, null, creator);
        BacklogItem sprintbox2 = new BacklogItem("SpB1", "SpB1", BacklogItemType.IDEA, ProductBacklogBox.SPRINTBOX,
                estimation, businessValue, date, date, project, null, null, creator);
        BacklogItem harvestbox1 = new BacklogItem("HB1", "HB1", BacklogItemType.IDEA, ProductBacklogBox.HARVESTBOX,
                estimation, businessValue, date, date, project, null, null, creator);
        BacklogItem harvestbox2 = new BacklogItem("HB1", "HB1", BacklogItemType.IDEA, ProductBacklogBox.HARVESTBOX,
                estimation, businessValue, date, date, project, null, null, creator);

        sandbox1 = backlogItemController.create(sandbox1);
        sandbox2 = backlogItemController.create(sandbox2);
        icebox1 = backlogItemController.create(icebox1);
        icebox2 = backlogItemController.create(icebox2);
        culturebox1 = backlogItemController.create(culturebox1);
        culturebox2 = backlogItemController.create(culturebox2);
        startbox1 = backlogItemController.create(startbox1);
        startbox2 = backlogItemController.create(startbox2);
        sprintbox1 = backlogItemController.create(sprintbox1);
        sprintbox2 = backlogItemController.create(sprintbox2);
        harvestbox1 = backlogItemController.create(harvestbox1);
        harvestbox2 = backlogItemController.create(harvestbox2);

        //Check Sandbox.
        List<BacklogItem> items = instance.getSandboxItems(project);
        assertTrue(items.contains(sandbox1));
        assertTrue(items.contains(sandbox2));
        assertFalse(items.contains(icebox1));
        assertFalse(items.contains(icebox2));
        assertFalse(items.contains(culturebox1));
        assertFalse(items.contains(culturebox2));
        assertFalse(items.contains(startbox1));
        assertFalse(items.contains(startbox2));
        assertFalse(items.contains(sprintbox1));
        assertFalse(items.contains(sprintbox2));
        assertFalse(items.contains(harvestbox1));
        assertFalse(items.contains(harvestbox2));

        //Check Icebox.
        items = instance.getIceboxItems(project);
        assertFalse(items.contains(sandbox1));
        assertFalse(items.contains(sandbox2));
        assertTrue(items.contains(icebox1));
        assertTrue(items.contains(icebox2));
        assertFalse(items.contains(culturebox1));
        assertFalse(items.contains(culturebox2));
        assertFalse(items.contains(startbox1));
        assertFalse(items.contains(startbox2));
        assertFalse(items.contains(sprintbox1));
        assertFalse(items.contains(sprintbox2));
        assertFalse(items.contains(harvestbox1));
        assertFalse(items.contains(harvestbox2));

        //Check Culturebox.
        items = instance.getCultureboxItems(project);
        assertFalse(items.contains(sandbox1));
        assertFalse(items.contains(sandbox2));
        assertFalse(items.contains(icebox1));
        assertFalse(items.contains(icebox2));
        assertTrue(items.contains(culturebox1));
        assertTrue(items.contains(culturebox2));
        assertFalse(items.contains(startbox1));
        assertFalse(items.contains(startbox2));
        assertFalse(items.contains(sprintbox1));
        assertFalse(items.contains(sprintbox2));
        assertFalse(items.contains(harvestbox1));
        assertFalse(items.contains(harvestbox2));

        //Check Startbox.
        items = instance.getStartboxItems(project);
        assertFalse(items.contains(sandbox1));
        assertFalse(items.contains(sandbox2));
        assertFalse(items.contains(icebox1));
        assertFalse(items.contains(icebox2));
        assertFalse(items.contains(culturebox1));
        assertFalse(items.contains(culturebox2));
        assertTrue(items.contains(startbox1));
        assertTrue(items.contains(startbox2));
        assertFalse(items.contains(sprintbox1));
        assertFalse(items.contains(sprintbox2));
        assertFalse(items.contains(harvestbox1));
        assertFalse(items.contains(harvestbox2));

        //Check Sprintbox.
        items = instance.getSprintboxItems(project);
        assertFalse(items.contains(sandbox1));
        assertFalse(items.contains(sandbox2));
        assertFalse(items.contains(icebox1));
        assertFalse(items.contains(icebox2));
        assertFalse(items.contains(culturebox1));
        assertFalse(items.contains(culturebox2));
        assertFalse(items.contains(startbox1));
        assertFalse(items.contains(startbox2));
        assertTrue(items.contains(sprintbox1));
        assertTrue(items.contains(sprintbox2));
        assertFalse(items.contains(harvestbox1));
        assertFalse(items.contains(harvestbox2));

        //Check Harvestbox.
        items = instance.getHarvestboxItems(project);
        assertFalse(items.contains(sandbox1));
        assertFalse(items.contains(sandbox2));
        assertFalse(items.contains(icebox1));
        assertFalse(items.contains(icebox2));
        assertFalse(items.contains(culturebox1));
        assertFalse(items.contains(culturebox2));
        assertFalse(items.contains(startbox1));
        assertFalse(items.contains(startbox2));
        assertFalse(items.contains(sprintbox1));
        assertFalse(items.contains(sprintbox2));
        assertTrue(items.contains(harvestbox1));
        assertTrue(items.contains(harvestbox2));

        backlogItemController.destroy(sandbox1);
        backlogItemController.destroy(sandbox2);
        backlogItemController.destroy(icebox1);
        backlogItemController.destroy(icebox2);
        backlogItemController.destroy(culturebox1);
        backlogItemController.destroy(culturebox2);
        backlogItemController.destroy(startbox1);
        backlogItemController.destroy(startbox2);
        backlogItemController.destroy(sprintbox1);
        backlogItemController.destroy(sprintbox2);
        backlogItemController.destroy(harvestbox1);
        backlogItemController.destroy(harvestbox2);
        storyBusinessValueController.destroy(businessValue);
        storyEstimationController.destroy(estimation);
        userController.destroy(creator);
        instance.destroy(project);
    }

}
