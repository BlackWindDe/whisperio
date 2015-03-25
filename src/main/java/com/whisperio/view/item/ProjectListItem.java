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
package com.whisperio.view.item;

import com.whisperio.data.entity.Project;

/**
 * Class used for displaying project list in home page.
 *
 * @author Maxime ESCOURBIAC
 */
public class ProjectListItem {

    private final int width;
    private final Project project;

    /**
     * ProjectListItem constructor.
     *
     * @param width Responsive width (33,50 or 100%).
     * @param project Project to display.
     */
    public ProjectListItem(int width, Project project) {
        this.width = width;
        this.project = project;
    }

    /**
     * Responsive width (33,50 or 100%).
     *
     * @return Responsive width (33,50 or 100%).
     */
    public int getWidth() {
        return width;
    }

    /**
     * Project to display.
     *
     * @return Project to display.
     */
    public Project getProject() {
        return project;
    }
}
