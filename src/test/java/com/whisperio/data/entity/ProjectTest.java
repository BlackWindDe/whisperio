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
package com.whisperio.data.entity;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test class for Project.
 *
 * @author Maxime ESCOURBIAC
 */
public class ProjectTest {

    /**
     * Default constructor.
     */
    public ProjectTest() {
    }

    /**
     * Test of getActiveRelease method, of class Project.
     */
    @Test
    public void testGetActiveRelease() {
        System.out.println("Project:GetActiveRelease");

        //Configure Release.
        Release release1 = new Release();
        release1.setId(1);
        release1.setIsActive(true);
        Release release2 = new Release();
        release2.setId(2);
        release2.setIsActive(true);
        Release release3 = new Release();
        release3.setId(3);
        release3.setIsActive(false);
        Release release4 = new Release();
        release4.setId(4);
        release4.setIsActive(false);

        //Project with 0 release.
        Project project = new Project();
        Release releaseResult = project.getActiveRelease();
        assertNull(releaseResult);

        //Project with 0 active release.
        project = new Project();
        project.addRelease(release3);
        project.addRelease(release4);
        releaseResult = project.getActiveRelease();
        assertNull(releaseResult);

        //Project with more than 1 active release.
        project = new Project();
        project.addRelease(release1);
        project.addRelease(release2);
        project.addRelease(release3);
        project.addRelease(release4);
        releaseResult = project.getActiveRelease();
        assertNull(releaseResult);

        //Good project.
        project = new Project();
        project.addRelease(release2);
        project.addRelease(release3);
        project.addRelease(release4);
        releaseResult = project.getActiveRelease();
        assertEquals(release2.getId(), releaseResult.getId());
    }

}
