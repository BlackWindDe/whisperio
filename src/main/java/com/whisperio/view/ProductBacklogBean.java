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
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Bean used by the product backlog page.
 *
 * @author Maxime ESCOURBIAC
 */
@ManagedBean(name = "productBacklogBean")
@SessionScoped
public class ProductBacklogBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    private ProductBacklogBox selectedBox;
    private List<BacklogItem> selectedBoxBacklogItems;

    /**
     * Creates a new instance of projectsBean
     */
    public ProductBacklogBean() {
    }

    /**
     * Post Contruct method.
     */
    @PostConstruct
    public void init() {
        selectedBox = ProductBacklogBox.CULTUREBOX;
    }

    /**
     * Move Product Backlog Item to sandbox.
     *
     * @param item Product backlog item to move to sandbox.
     */
    public void moveToSandbox(BacklogItem item) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Call", "Move to sandbox : #" + item.getId() + " " + item.getTitle()));
    }

    /**
     * Move Product Backlog Item to icebox.
     *
     * @param item Product backlog item to move to icebox.
     */
    public void moveToIcebox(BacklogItem item) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Call", "Move to icebox : #" + item.getId() + " " + item.getTitle()));
    }

    /**
     * Move Product Backlog Item to an culturebox.
     *
     * @param item Product backlog item to move to culturebox.
     */
    public void moveToCulturebox(BacklogItem item) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Call", "Move to culture box : #" + item.getId() + " " + item.getTitle()));
    }

    /**
     * Move Product Backlog Item to an startbox.
     *
     * @param item Product backlog item to move to startbox.
     */
    public void moveToStartbox(BacklogItem item) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Call", "Move to startbox : #" + item.getId() + " " + item.getTitle()));
    }

    /**
     * Move Product Backlog Item to an sprintbox.
     *
     * @param item Product backlog item to move to sprintbox.
     */
    public void moveToSprintbox(BacklogItem item) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Call", "Move to sprint : #" + item.getId() + " " + item.getTitle()));
    }

    /**
     * Move Product Backlog Item to an harvestbox.
     *
     * @param item Product backlog item to move to harvestbox.
     */
    public void moveToHarvestbox(BacklogItem item) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Call", "Move to harvestbox : #" + item.getId() + " " + item.getTitle()));
    }

    /**
     * Change the order of Product Backlog Item inside a box.
     */
    public void sortProductBacklogItem() {
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String productBacklogItemsOrder = params.get("productBacklogItemsOrder");
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Call", "Sort Items method called, Order : " + productBacklogItemsOrder));
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
     * Get product backlog item logo.
     *
     * @param item Item to display
     * @return Logo of the product item depending on it type.
     */
    public String getProductBacklogItemTypeLogo(BacklogItem item) {
        String logoIcon = "";
        switch (item.getBacklogItemType()) {
            case IDEA:
                logoIcon = "icon-lightbulb";
                break;
            case EPIC:
                logoIcon = "icon-flash";
                break;
            case USER_STORY:
                logoIcon = "icon-user";
                break;
            case TECHNICAL_STORY:
                logoIcon = "icon-terminal";
                break;
            case BUG_RESOLUTION:
                logoIcon = "icon-bug";
                break;
            case TECHNICAL_DEBT:
                logoIcon = "icon-money";
                break;
            case SPIKE:
                logoIcon = "icon-book";
                break;
        }
        return logoIcon;
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
