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

import com.whisperio.data.entity.StoryBusinessValue;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Test class for StoryBusinessValueController.
 *
 * @author Maxime ESCOURBIAC
 */
public class StoryBusinessValueControllerTest {

    /**
     * Default constructor.
     */
    public StoryBusinessValueControllerTest() {
    }

    /**
     * Test of create method, of class StoryBusinessValueController.
     */
    @Test
    public void testCreate() {
        System.out.println("StoryBusinessValueController:Create");
        StoryBusinessValueController storyBusinessValueController = new StoryBusinessValueController();
        String name = "name";
        BigDecimal value = BigDecimal.TEN;
        StoryBusinessValue businessValue = new StoryBusinessValue(name, value);
        StoryBusinessValue businessValueResult = storyBusinessValueController.create(businessValue);

        //Check StoryBusinessValue properties.
        assertNotNull(businessValueResult.getId());
        assertEquals(name, businessValueResult.getName());
        assertEquals(value, businessValueResult.getValue());

        storyBusinessValueController.destroy(businessValueResult);
    }

    /**
     * Test of destroy method, of class StoryBusinessValueController.
     */
    @Test
    public void testDestroy() {
        System.out.println("StoryBusinessValueController:Destroy");
        StoryBusinessValueController storyBusinessValueController = new StoryBusinessValueController();
        String name = "name";
        BigDecimal value = BigDecimal.TEN;
        StoryBusinessValue businessValue = new StoryBusinessValue(name, value);
        businessValue = storyBusinessValueController.create(businessValue);
        assertTrue(storyBusinessValueController.destroy(businessValue));
    }

    /**
     * Test of getStoryBusinessValues method, of class StoryBusinessValueController.
     */
    @Test
    public void testGetStoryBusinessValues() {
        System.out.println("ProjectController:GetStoryBusinessValues");
        StoryBusinessValueController storyBusinessValueController = new StoryBusinessValueController();
        StoryBusinessValue businessValue1 = storyBusinessValueController.create(new StoryBusinessValue("name", BigDecimal.TEN));
        StoryBusinessValue businessValue2 = storyBusinessValueController.create(new StoryBusinessValue("name", BigDecimal.TEN));
        StoryBusinessValue businessValue3 = storyBusinessValueController.create(new StoryBusinessValue("name", BigDecimal.TEN));

        List result = storyBusinessValueController.getStoryBusinessValues();
        assertTrue(result.contains(businessValue1));
        assertTrue(result.contains(businessValue2));
        assertTrue(result.contains(businessValue3));
        storyBusinessValueController.destroy(businessValue1);
        storyBusinessValueController.destroy(businessValue2);
        storyBusinessValueController.destroy(businessValue3);
    }
}
