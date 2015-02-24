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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Bean used by the project selection page.
 *
 * @author Maxime ESCOURBIAC
 */
@ManagedBean(name = "projectsBean")
@RequestScoped
public class ProjectsBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private final ProjectController projectController;

    //Attrributes used for the creation of a project.
    String name;
    String keyName;
    String description;

    /**
     * Creates a new instance of projectsBean
     */
    public ProjectsBean() {
        projectController = new ProjectController();
        name = "";
        keyName = "";
        description = "";
    }

    /**
     * Go to project page.
     *
     * @param keyName Project Key Name.
     */
    public void goToProject(String keyName) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Project project = projectController.getProjectByKeyName(keyName);
            if (project != null) {
                sessionBean.setSelectedProject(project);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Whisperio/Project.xhtml");
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The project " + keyName + " is not existing."));
            }
        } catch (Exception ex) {
            Logger.getLogger(ProjectsBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exception", ex.getMessage()));
        }
    }

    /**
     * Create a new project.
     */
    public void createProject() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (validateNewProject()) {
            Project newProject = new Project(name, keyName, description, new Date());
            newProject = projectController.create(newProject);
            if (newProject == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The project has not created."));
            } else {
                goToProject(keyName);
            }
        }
    }

    /**
     * Validating method of a new project.
     *
     * @return True if the new project is valid.
     */
    private boolean validateNewProject() {
        FacesContext context = FacesContext.getCurrentInstance();
        boolean valid = true;

        if (name.compareTo("") == 0) {
            valid = false;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Name cannot be empty"));
        }

        if (name.length() > 50) {
            valid = false;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Name cannot exceed 50"));
        }

        if (keyName.compareTo("") == 0) {
            valid = false;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Key Name cannot be empty"));
        }

        if (keyName.length() > 3) {
            valid = false;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Key Name cannot exceed 3"));
        }

        if (projectController.getProjectByKeyName(keyName) != null) {
            valid = false;
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Key Name is already taken."));
        }

        if (description.compareTo("") == 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Description cannot be empty"));
        }
        return valid;
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

    /**
     * New project name.
     *
     * @return New project name.
     */
    public String getName() {
        return name;
    }

    /**
     * New project name.
     *
     * @param name New project name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * New project key name.
     *
     * @return New project key name.
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * New project key name.
     *
     * @param keyName New project key name.
     */
    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    /**
     * New project description.
     *
     * @return New project description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * New project description.
     *
     * @param description New project description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
