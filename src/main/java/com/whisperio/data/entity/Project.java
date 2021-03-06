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
 * Entity class from Projects table.
 *
 * @author Maxime ESCOURBIAC
 */
@Entity
@Table(name = "Projects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Projects.findByID", query = "SELECT p FROM Project p WHERE p.id = :id"),
    @NamedQuery(name = "Releases.getProjectActiveRelease", query = "SELECT r FROM Release r Where r.project.id=:projectID and r.isActive=true"),
    @NamedQuery(name = "Sprints.getProjectClosedSprints", query = "SELECT s FROM Sprint s Where s.release.project.id =:projectID and s.isClosed=true"),
    @NamedQuery(name = "BacklogItems.getProductBacklogBoxItems", query = "SELECT b FROM BacklogItem b Where b.project.id =:projectID and b.productBacklogBox =:boxID")})
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Name")
    private String name;

    @Basic(optional = false)
    @NotNull
    @Column(name = "Description")
    private String description;

    @Basic(optional = false)
    @NotNull
    @Column(name = "CreationDate")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    @OrderBy("releaseNumber asc")
    private List<Release> releases;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private List<BacklogItem> backlogItems;

    /**
     * Default constructor.
     */
    public Project() {
        this.releases = new ArrayList<>();
        this.backlogItems = new ArrayList<>();
    }

    /**
     * Project constructor.
     *
     * @param name Project name.
     * @param description Project description.
     * @param creationDate Project creation date.
     */
    public Project(String name, String description, Date creationDate) {
        this.name = name;
        this.description = description;
        this.creationDate = creationDate;
        this.releases = new ArrayList<>();
        this.backlogItems = new ArrayList<>();
    }

    /**
     * Project ID.
     *
     * @return Project ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Project ID.
     *
     * @param id Project ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Project name.
     *
     * @return Project name.
     */
    public String getName() {
        return name;
    }

    /**
     * Project name.
     *
     * @param name Project name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Project description.
     *
     * @return Project description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Project description.
     *
     * @param description Project description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Project creation date.
     *
     * @return Project creation date.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Project creation date.
     *
     * @param creationDate Project creation date.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Project releases.
     *
     * @return Project releases.
     */
    @XmlTransient
    public List<Release> getReleases() {
        return releases;
    }

    /**
     * Add a release to the project.
     *
     * @param release Release to add.
     */
    public void addRelease(Release release) {
        this.releases.add(release);
    }

    /**
     * Project releases.
     *
     * @param releases Project releases.
     */
    public void setReleases(List<Release> releases) {
        this.releases = releases;
    }

    /**
     * Project backlog items.
     *
     * @return Project backlog items.
     */
    @XmlTransient
    public List<BacklogItem> getBacklogItems() {
        return backlogItems;
    }

    /**
     * Add a backlog item to the project.
     *
     * @param backlogItem Backlog item to add.
     */
    public void addBacklogItem(BacklogItem backlogItem) {
        this.backlogItems.add(backlogItem);
    }

    /**
     * Project backlog items.
     *
     * @param backlogItems Project backlog items.
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
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.whisperio.data.entity.Project[ id=" + id + " ]";
    }
}
