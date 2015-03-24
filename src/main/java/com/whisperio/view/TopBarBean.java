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
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * JSF Managed Bean for User Session
 *
 * @author Maxime ESCOURBIAC
 */
@ManagedBean(name = "topBarBean")
@ViewScoped
public class TopBarBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    /**
     * Creates a new instance of SessionBean
     */
    public TopBarBean() {
    }

    /**
     * Logout the user.
     */
    public void logout() {
        sessionBean.setConnectedUser(null);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/Whisperio/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Test if the user is connected.
     */
    public void checkSession() {
        if (sessionBean.getConnectedUser() == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Whisperio/login.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(SessionBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    /**
     * Get projects accessible by the connected user.
     *
     * @return Projects accessible by the connected user.
     */
    public List<Project> getAccessibleProjectByConnectedUser() {
        ProjectController projectController = new ProjectController();
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
