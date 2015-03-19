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

import com.whisperio.data.entity.StoryEstimation;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test class for StoryEstimationController.
 *
 * @author Maxime ESCOURBIAC
 */
public class StoryEstimationControllerTest {

    /**
     * Default constructor.
     */
    public StoryEstimationControllerTest() {
    }

    /**
     * Test of create method, of class StoryEstimationController.
     */
    @Test
    public void testCreate() {
        System.out.println("StoryEstimationController:Create");
        StoryEstimationController storyEstimationController = new StoryEstimationController();
        String name = "name";
        BigDecimal value = BigDecimal.TEN;
        StoryEstimation estimation = new StoryEstimation(name, value);
        StoryEstimation estimationResult = storyEstimationController.create(estimation);

        //Check StoryEstimation properties.
        assertNotNull(estimationResult.getId());
        assertEquals(name, estimationResult.getName());
        assertEquals(value, estimationResult.getValue());

        storyEstimationController.destroy(estimationResult);
    }

    /**
     * Test of destroy method, of class StoryEstimationController.
     */
    @Test
    public void testDestroy() {
        System.out.println("StoryEstimationController:Destroy");
        StoryEstimationController storyEstimationController = new StoryEstimationController();
        String name = "name";
        BigDecimal value = BigDecimal.TEN;
        StoryEstimation estimation = new StoryEstimation(name, value);
        estimation = storyEstimationController.create(estimation);
        assertTrue(storyEstimationController.destroy(estimation));
    }

    /**
     * Test of getStoryEstimations method, of class StoryEstimationController.
     */
    @Test
    public void testGetStoryEstimations() {
        System.out.println("ProjectController:GetStoryEstimations");
        StoryEstimationController storyEstimationController = new StoryEstimationController();
        StoryEstimation estimation1 = storyEstimationController.create(new StoryEstimation("name", BigDecimal.TEN));
        StoryEstimation estimation2 = storyEstimationController.create(new StoryEstimation("name", BigDecimal.TEN));
        StoryEstimation estimation3 = storyEstimationController.create(new StoryEstimation("name", BigDecimal.TEN));

        List result = storyEstimationController.getStoryEstimations();
        assertTrue(result.contains(estimation1));
        assertTrue(result.contains(estimation2));
        assertTrue(result.contains(estimation3));
        storyEstimationController.destroy(estimation1);
        storyEstimationController.destroy(estimation2);
        storyEstimationController.destroy(estimation3);
    }
}
