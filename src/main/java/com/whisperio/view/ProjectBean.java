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

import com.whisperio.data.entity.BacklogItem;
import com.whisperio.data.entity.ProductBacklogBox;
import com.whisperio.data.jpa.ProjectController;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 * Bean used by the product backlog page.
 *
 * @author Maxime ESCOURBIAC
 */
@ManagedBean(name = "projectBean")
@SessionScoped
public class ProjectBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private ProductBacklogBox selectedBox;
    private List<BacklogItem> selectedBoxBacklogItems;

    /**
     * Creates a new instance of projectsBean
     */
    public ProjectBean() {
    }

    /**
     * Post Contruct method.
     */
    @PostConstruct
    public void init() {
        selectedBox = ProductBacklogBox.CULTUREBOX;
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
     * Use Sandbox as selected product backlog box.
     */
    public void useSandbox() {
        this.selectedBox = ProductBacklogBox.SANDBOX;
    }

    /**
     * Use Icebox as selected product backlog box.
     */
    public void useIcebox() {
        this.selectedBox = ProductBacklogBox.ICEBOX;
    }

    /**
     * Use Culturebox as selected product backlog box.
     */
    public void useCulturebox() {
        this.selectedBox = ProductBacklogBox.CULTUREBOX;
    }

    /**
     * Use Startbox as selected product backlog box.
     */
    public void useStartbox() {
        this.selectedBox = ProductBacklogBox.STARTBOX;
    }

    /**
     * Use Sprintbox as selected product backlog box.
     */
    public void useSprintbox() {
        this.selectedBox = ProductBacklogBox.SPRINTBOX;
    }

    /**
     * Use Harvestbox as selected product backlog box.
     */
    public void useHarvestbox() {
        this.selectedBox = ProductBacklogBox.HARVESTBOX;
    }

    /**
     * Get Selected backlog items.
     *
     * @return Selected backlog items.
     */
    public List<BacklogItem> getSelectedBoxBacklogItems() {
        ProjectController projectController = new ProjectController();
        switch (selectedBox) {
            case SANDBOX:
                selectedBoxBacklogItems = projectController.getSandboxItems(sessionBean.getSelectedProject());
                break;
            case ICEBOX:
                selectedBoxBacklogItems = projectController.getIceboxItems(sessionBean.getSelectedProject());
                break;
            case CULTUREBOX:
                selectedBoxBacklogItems = projectController.getCultureboxItems(sessionBean.getSelectedProject());
                break;
            case STARTBOX:
                selectedBoxBacklogItems = projectController.getStartboxItems(sessionBean.getSelectedProject());
                break;
            case SPRINTBOX:
                selectedBoxBacklogItems = projectController.getSprintboxItems(sessionBean.getSelectedProject());
                break;
            case HARVESTBOX:
                selectedBoxBacklogItems = projectController.getHarvestboxItems(sessionBean.getSelectedProject());
                break;
        }
        return selectedBoxBacklogItems;
    }

    /**
     * Set Selected backlog items.
     *
     * @param selectedBoxBacklogItems Selected backlog items.
     */
    public void setSelectedBoxBacklogItems(List<BacklogItem> selectedBoxBacklogItems) {
        this.selectedBoxBacklogItems = selectedBoxBacklogItems;
    }
}
