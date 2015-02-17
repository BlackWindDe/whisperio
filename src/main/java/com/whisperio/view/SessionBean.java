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
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

    /**
     * Connect Method.
     *
     * @param mail User mail.
     * @return True, if the connection is successful.
     */
    public boolean connect(String mail) {
        boolean connectionSuccessful = false;
        try {
            User userChecked = userController.getUserByMail(mail);
            if (userChecked != null) {
                connectedUser = userChecked;
                connectionSuccessful = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connectionSuccessful;
    }

    /**
     * Logout the user.
     */
    public void logout() {
        connectedUser = null;
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Whisperio/Index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test if the user is connected.
     */
    public void checkSession() {
        if (connectedUser == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Whisperio/Index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Get projects accessible by the connected user.
     *
     * @return Projects accessible by the connected user.
     */
    public List<Project> getAccessibleProjectByConnectedUser() {
        return projectController.getAllProjects();
    }

    //Getter & Setter for Bean properties.
    public User getConnectedUser() {
        return connectedUser;
    }

    public void setConnectedUser(User user) {
        this.connectedUser = user;
    }

    public Project getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(Project selectedProject) {
        this.selectedProject = selectedProject;
    }

}
