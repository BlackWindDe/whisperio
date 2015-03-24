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
import com.whisperio.data.entity.User;
import com.whisperio.data.jpa.ProjectController;
import com.whisperio.data.jpa.UserController;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * JSF Managed Bean for User Session
 *
 * @author Maxime ESCOURBIAC
 */
@ManagedBean(name = "sessionBean")
@SessionScoped
public class SessionBean implements Serializable {

    private final UserController userController;
    private final ProjectController projectController;
    private User connectedUser;
    private Project selectedProject;

    /**
     * Creates a new instance of SessionBean
     */
    public SessionBean() {
        userController = new UserController();
        projectController = new ProjectController();
    }

    //Getter & Setter for Bean properties.
    public User getConnectedUser() {
        return connectedUser == null ? null : userController.refresh(connectedUser);
    }

    public void setConnectedUser(User user) {
        this.connectedUser = user;
    }

    public Project getSelectedProject() {
        return selectedProject == null ? null : projectController.refresh(selectedProject);
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

}
