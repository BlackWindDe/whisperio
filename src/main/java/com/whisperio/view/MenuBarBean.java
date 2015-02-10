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
package com.whisperio.view;

import com.whisperio.data.entity.Project;
import com.whisperio.data.jpa.ProjectController;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 * Bean used by the menubar component.
 *
 * @author Maxime ESCOURBIAC
 */
@ManagedBean(name = "menuBarBean")
@RequestScoped
public class MenuBarBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private final ProjectController projectController;

    /**
     * Creates a new instance of MenuBar
     */
    public MenuBarBean() {
        projectController = new ProjectController();
    }

    /**
     * Logout the user.
     */
    public void logout() {
        sessionBean.logout();
    }

    /**
     * Return the list of all the projects.
     *
     * @return The list of all the projects.
     */
    public List<Project> getProjects() {
        return projectController.getAllProjects();
    }

    /**
     * Session Bean.
     *
     * @return Session Bean.
     */
    public SessionBean getSessionBean() {
        return sessionBean;
    }

    /**
     * Session Bean.
     *
     * @param sessionBean Session Bean.
     */
    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

}
