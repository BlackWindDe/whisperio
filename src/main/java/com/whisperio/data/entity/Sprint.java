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
import java.math.BigDecimal;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity class from Sprints table.
 *
 * @author Maxime ESCOURBIAC
 */
@Entity
@Table(name = "Sprints")
@XmlRootElement
public class Sprint implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Column(name = "SprintNumber")
    private int sprintNumber;

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
    @NotNull
    @Column(name = "IsClosed")
    private boolean isClosed;

    @Basic(optional = false)
    @Column(name = "Velocity")
    private BigDecimal velocity;

    @Basic(optional = false)
    @Column(name = "ReleaseRemainingPointEndOfSprint")
    private BigDecimal releaseRemainingPointEndOfSprint;

    @JoinColumn(name = "ReleaseID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Release release;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sprint")
    private List<BacklogItem> backlogItems;

    /**
     * Default constructor.
     */
    public Sprint() {
    }

    /**
     * Sprint constructor.
     *
     * @param name Sprint name.
     * @param sprintNumber Sprint Number;
     * @param startDate Sprint start date.
     * @param endDate Sprint end date.
     * @param isActive Boolean for determine if the sprint is active.
     * @param isClosed Boolean for determine if the sprint is closed.
     * @param release Sprint release.
     */
    public Sprint(String name, int sprintNumber, Date startDate, Date endDate, boolean isActive, boolean isClosed, Release release) {
        this.name = name;
        this.sprintNumber = sprintNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.isClosed = isClosed;
        this.release = release;
        this.backlogItems = new ArrayList<>();
    }

    /**
     * Sprint ID.
     *
     * @return Sprint ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sprint ID.
     *
     * @param id Sprint ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Sprint Number.
     *
     * @return Sprint Number.
     */
    public int getSprintNumber() {
        return sprintNumber;
    }

    /**
     * Sprint Number.
     *
     * @param sprintNumber Sprint Number.
     */
    public void setSprintNumber(int sprintNumber) {
        this.sprintNumber = sprintNumber;
    }

    /**
     * Sprint name.
     *
     * @return Sprint name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sprint name.
     *
     * @param name Sprint name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sprint start date.
     *
     * @return Sprint start date.
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sprint start date.
     *
     * @param startDate Sprint start date.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Sprint end date.
     *
     * @return Sprint end date.
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sprint end date.
     *
     * @param endDate Sprint end date.
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Boolean for determine if the sprint is active.
     *
     * @return True if the sprint is active.
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Boolean for determine if the sprint is active.
     *
     * @param isActive True if the sprint is active.
     */
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    /**
     * Boolean for determine if the sprint is closed.
     *
     * @return Boolean for determine if the sprint is closed.
     */
    public boolean isClosed() {
        return isClosed;
    }

    /**
     * Boolean for determine if the sprint is closed.
     *
     * @param isClosed Boolean for determine if the sprint is closed.
     */
    public void setIsClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    /**
     * Sprint release.
     *
     * @return Sprint release.
     */
    public Release getRelease() {
        return release;
    }

    /**
     * Sprint release.
     *
     * @param release Sprint release.
     */
    public void setRelease(Release release) {
        this.release = release;
    }

    /**
     * Sprint backlog items.
     *
     * @return Sprint backlog items.
     */
    @XmlTransient
    public List<BacklogItem> getBacklogItems() {
        return backlogItems;
    }

    /**
     * Add a backlog item to the sprint.
     *
     * @param backlogItem Backlog item to add.
     */
    public void addBacklogItem(BacklogItem backlogItem) {
        this.backlogItems.add(backlogItem);
    }

    /**
     * Sprint backlog items.
     *
     * @param backlogItems Sprint backlog items.
     */
    public void setBacklogItems(List<BacklogItem> backlogItems) {
        this.backlogItems = backlogItems;
    }

    /**
     * Velocity of the Sprint.
     *
     * @return Velocity of the Sprint.
     */
    public BigDecimal getVelocity() {
        return velocity;
    }

    /**
     * Velocity of the Sprint.
     *
     * @param velocity Velocity of the Sprint.
     */
    public void setVelocity(BigDecimal velocity) {
        this.velocity = velocity;
    }

    /**
     * Release remaining point at the end of the sprint.
     *
     * @return Release remaining point at the end of the sprint.
     */
    public BigDecimal getReleaseRemainingPointEndOfSprint() {
        return releaseRemainingPointEndOfSprint;
    }

    /**
     * Release remaining point at the end of the sprint.
     *
     * @param releaseRemainingPointEndOfSprint Release remaining point at the
     * end of the sprint.
     */
    public void setReleaseRemainingPointEndOfSprint(BigDecimal releaseRemainingPointEndOfSprint) {
        this.releaseRemainingPointEndOfSprint = releaseRemainingPointEndOfSprint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Sprint)) {
            return false;
        }
        Sprint other = (Sprint) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.whisperio.data.entity.Sprint[ id=" + id + " ]";
    }
}
