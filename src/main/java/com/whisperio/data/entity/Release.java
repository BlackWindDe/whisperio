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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity class from Releases table.
 *
 * @author Maxime ESCOURBIAC
 */
@Entity
@Table(name = "Releases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sprints.getReleaseClosedSprint", query = "SELECT s FROM Sprint s Where s.release.id=:releaseID and s.isClosed=true")})
public class Release implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "ReleaseNumber")
    private int releaseNumber;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Name")
    private String name;

    @Basic(optional = false)
    @NotNull
    @Column(name = "StartDate")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "EndDate")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "IsActive")
    private boolean isActive;

    @Basic(optional = false)
    @Column(name = "NumberOfSprint")
    private int numberOfSprint;

    @JoinColumn(name = "ProjectID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Project project;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "release")
    @OrderBy("sprintNumber asc")
    private List<Sprint> sprints;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "release")
    private List<BacklogItem> backlogItems;

    /**
     * Default constructor.
     */
    public Release() {
    }

    /**
     * Release constructor.
     *
     * @param name Release name.
     * @param releaseNumber Release number.
     * @param startDate Release start date.
     * @param endDate Release end date.
     * @param numberOfSprint Number of sprints of the Release.
     * @param isActive Boolean for determine if the release is active.
     * @param project Release project.
     */
    public Release(String name, int releaseNumber, Date startDate, Date endDate, int numberOfSprint, boolean isActive, Project project) {
        this.name = name;
        this.releaseNumber = releaseNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.numberOfSprint = numberOfSprint;
        this.project = project;
        this.sprints = new ArrayList<>();
        this.backlogItems = new ArrayList<>();
    }

    /**
     * Release ID.
     *
     * @return Release ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Release ID.
     *
     * @param id Release ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Release number.
     *
     * @return Release number.
     */
    public int getReleaseNumber() {
        return releaseNumber;
    }

    /**
     * Release number.
     *
     * @param releaseNumber Release number.
     */
    public void setReleaseNumber(int releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    /**
     * Release name.
     *
     * @return Release name.
     */
    public String getName() {
        return name;
    }

    /**
     * Release name.
     *
     * @param name Release name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Release start date.
     *
     * @return Release start date.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Release start date.
     *
     * @param startDate Release start date.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * End date.
     *
     * @return End date.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * End date.
     *
     * @param endDate End date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Boolean for determine if the release is active.
     *
     * @return True if the release is active.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Boolean for determine if the release is active.
     *
     * @param isActive True if the release is active.
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Number of sprints of the Release.
     *
     * @return Number of sprints of the Release.
     */
    public int getNumberOfSprint() {
        return numberOfSprint;
    }

    /**
     * Number of sprints of the Release.
     *
     * @param numberOfSprint Number of sprints of the Release.
     */
    public void setNumberOfSprint(int numberOfSprint) {
        this.numberOfSprint = numberOfSprint;
    }

    /**
     * Release project.
     *
     * @return Release project.
     */
    public Project getProject() {
        return project;
    }

    /**
     * Release project.
     *
     * @param project Release project.
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * Release sprints.
     *
     * @return Release sprints.
     */
    @XmlTransient
    public List<Sprint> getSprints() {
        return sprints;
    }

    /**
     * Add a sprint to the release.
     *
     * @param sprint Sprint to add.
     */
    public void addSprint(Sprint sprint) {
        this.sprints.add(sprint);
    }

    /**
     * Release sprints.
     *
     * @param sprints Release sprints.
     */
    public void setSprints(List<Sprint> sprints) {
        this.sprints = sprints;
    }

    /**
     * Release backlog items.
     *
     * @return Release backlog items.
     */
    @XmlTransient
    public List<BacklogItem> getBacklogItems() {
        return backlogItems;
    }

    /**
     * Add a backlog item to the release.
     *
     * @param backlogItem Backlog item to add.
     */
    public void addBacklogItem(BacklogItem backlogItem) {
        this.backlogItems.add(backlogItem);
    }

    /**
     * Release backlog items.
     *
     * @param backlogItems Release backlog items.
     */
    public void setBacklogItems(List<BacklogItem> backlogItems) {
        this.backlogItems = backlogItems;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Release)) {
            return false;
        }
        Release other = (Release) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.whisperio.data.entity.Release[ id=" + id + " ]";
    }
}
