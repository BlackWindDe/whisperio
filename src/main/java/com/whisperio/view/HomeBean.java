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
import com.whisperio.view.item.ProjectListItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Bean used by the home page.
 *
 * @author Maxime ESCOURBIAC
 */
@ManagedBean(name = "homeBean")
@ViewScoped
public class HomeBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private final ProjectController projectController;

    //Attrributes used for the creation of a project.
    String name;
    String description;

    /**
     * Creates a new instance of projectsBean
     */
    public HomeBean() {
        projectController = new ProjectController();
        name = "";
        description = "";
    }

    /**
     * Construct the project list used for the selection.
     *
     * @return Project list used for the selection.
     */
    public List<ProjectListItem> getProjectsList() {
        List<ProjectListItem> list = new ArrayList<>();
        List<Project> projects = projectController.getAllProjects();
        int line = projects.size() / 3; //3 projects per line.
        int left = projects.size() - (3 * line);

        //Construct the entire line.
        for (int i = 0; i < (line * 3); ++i) {
            list.add(new ProjectListItem(33, projects.get(i)));
        }

        //Construct the last line.
        if (left == 1) {
            list.add(new ProjectListItem(100, projects.get(projects.size() - 1)));
        } else if (left == 2) {
            list.add(new ProjectListItem(50, projects.get(projects.size() - 1)));
            list.add(new ProjectListItem(50, projects.get(projects.size() - 2)));
        }

        return list;
    }

    /**
     * Go to project page.
     *
     * @param keyName Project Key Name.
     */
    public void goToProject(String keyName) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        try {
//            Project project = projectController.getProjectByKeyName(keyName);
//            if (project != null) {
//                sessionBean.setSelectedProject(project);
//                FacesContext.getCurrentInstance().getExternalContext().redirect("/Whisperio/obeya.xhtml");
//            } else {
//                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The project " + keyName + " is not existing."));
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(HomeBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exception", ex.getMessage()));
//        }
    }

    /**
     * Create a new project.
     */
    public void createProject() {
//        FacesContext context = FacesContext.getCurrentInstance();
//
//        if (validateNewProject()) {
//            Project newProject = new Project(name, keyName, description, new Date());
//            newProject = projectController.create(newProject);
//            if (newProject == null) {
//                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "The project has not created."));
//            } else {
//                goToProject(keyName);
//            }
//        }
    }

    /**
     * Validating method of a new project.
     *
     * @return True if the new project is valid.
     */
//    private boolean validateNewProject() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        boolean valid = true;
//
//        if (name.compareTo("") == 0) {
//            valid = false;
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Name cannot be empty"));
//        }
//
//        if (name.length() > 50) {
//            valid = false;
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Name cannot exceed 50"));
//        }
//
//        if (keyName.compareTo("") == 0) {
//            valid = false;
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Key Name cannot be empty"));
//        }
//
//        if (keyName.length() > 3) {
//            valid = false;
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Key Name cannot exceed 3"));
//        }
//
//        if (projectController.getProjectByKeyName(keyName) != null) {
//            valid = false;
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Key Name is already taken."));
//        }
//
//        if (description.compareTo("") == 0) {
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Description cannot be empty"));
//        }
//        return valid;
//    }
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
