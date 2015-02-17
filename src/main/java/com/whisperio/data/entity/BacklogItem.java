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
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class from BacklogItems table.
 *
 * @author Maxime ESCOURBIAC
 */
@Entity
@Table(name = "BacklogItems")
@XmlRootElement
public class BacklogItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Title")
    private String title;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Description")
    private String description;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CreationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Basic(optional = false)
    @NotNull
    @Column(name = "LastUpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    @NotNull
    @JoinColumn(name = "ProjectID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private Project project;

    @JoinColumn(name = "ReleaseID", referencedColumnName = "ID")
    @ManyToOne
    private Release release;

    @JoinColumn(name = "SprintID", referencedColumnName = "ID")
    @ManyToOne
    private Sprint sprint;

    @NotNull
    @JoinColumn(name = "CreatorID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private User creator;

    /**
     * Default constructor.
     */
    public BacklogItem() {
    }

    /**
     * BacklogItem constructor.
     *
     * @param title BacklogItem title.
     * @param description BacklogItem description.
     * @param creationDate BacklogItem creation date.
     * @param lastUpdateDate BacklogItem last update date.
     * @param project BacklogItem project.
     * @param release BacklogItem release.
     * @param sprint BacklogItem sprint.
     * @param creator BacklogItem creator.
     */
    public BacklogItem(String title, String description, Date creationDate, Date lastUpdateDate, Project project, Release release, Sprint sprint, User creator) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.lastUpdateDate = lastUpdateDate;
        this.project = project;
        this.release = release;
        this.sprint = sprint;
        this.creator = creator;
    }

    /**
     * BacklogItem ID.
     *
     * @return BacklogItem ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * BacklogItem ID.
     *
     * @param id BacklogItem ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * BacklogItem title.
     *
     * @return BacklogItem title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * BacklogItem title.
     *
     * @param title BacklogItem title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * BacklogItem description.
     *
     * @return BacklogItem description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * BacklogItem description.
     *
     * @param description BacklogItem description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * BacklogItem creation date.
     *
     * @return BacklogItem creation date.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * BacklogItem creation date.
     *
     * @param creationDate BacklogItem creation date.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * BacklogItem last update date.
     *
     * @return BacklogItem last update date.
     */
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    /**
     * BacklogItem last update date.
     *
     * @param lastUpdateDate BacklogItem last update date.
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    /**
     * BacklogItem creator.
     *
     * @return BacklogItem creator.
     */
    public User getCreator() {
        return creator;
    }

    /**
     * BacklogItem creator.
     *
     * @param creator BacklogItem creator.
     */
    public void setCreator(User creator) {
        this.creator = creator;
    }

    /**
     * BacklogItem project.
     *
     * @return BacklogItem project.
     */
    public Project getProject() {
        return project;
    }

    /**
     * BacklogItem project.
     *
     * @param project BacklogItem project.
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * BacklogItem release.
     *
     * @return BacklogItem release.
     */
    public Release getRelease() {
        return release;
    }

    /**
     * BacklogItem release.
     *
     * @param release BacklogItem release.
     */
    public void setRelease(Release release) {
        this.release = release;
    }

    /**
     * BacklogItem sprint.
     *
     * @return BacklogItem sprint.
     */
    public Sprint getSprint() {
        return sprint;
    }

    /**
     * BacklogItem sprint.
     *
     * @param sprint BacklogItem sprint.
     */
    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BacklogItem)) {
            return false;
        }
        BacklogItem other = (BacklogItem) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.whisperio.data.entity.BacklogItem[ id=" + id + " ]";
    }
}
