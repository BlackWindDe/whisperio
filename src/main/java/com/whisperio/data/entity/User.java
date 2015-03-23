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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Entity class from Users table.
 *
 * @author Maxime ESCOURBIAC
 */
@Entity
@Table(name = "Users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "Users.findByMail", query = "SELECT u FROM User u WHERE u.mail = :mail")
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Mail")
    private String mail;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Username")
    private String username;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Forename")
    private String forename;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LastName")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "creator")
    private List<BacklogItem> backlogItemsCreated;

    /**
     * Default constructor.
     */
    public User() {
    }

    /**
     * User constructor.
     *
     * @param mail User mail.
     * @param username User username.
     * @param forename User forename.
     * @param lastName User last name.
     */
    public User(String mail, String username, String forename, String lastName) {
        this.mail = mail;
        this.username = username;
        this.forename = forename;
        this.lastName = lastName;
        this.backlogItemsCreated = new ArrayList<>();
    }

    /**
     * User ID.
     *
     * @return User ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * User ID.
     *
     * @param id User ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * User mail.
     *
     * @return User mail.
     */
    public String getMail() {
        return mail;
    }

    /**
     * User mail.
     *
     * @param mail User mail.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * User username.
     *
     * @return User username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * User username.
     *
     * @param username User username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * User forename.
     *
     * @return User forename.
     */
    public String getForename() {
        return forename;
    }

    /**
     * User forename.
     *
     * @param forename User forename.
     */
    public void setForename(String forename) {
        this.forename = forename;
    }

    /**
     * User last name.
     *
     * @return User last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * User last name.
     *
     * @param lastName User last name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Backlog Items created by the user.
     *
     * @return Backlog Items created by the user.
     */
    @XmlTransient
    public List<BacklogItem> getBacklogItemsCreated() {
        return backlogItemsCreated;
    }

    /**
     * Add a backlog item created by the user.
     *
     * @param backlogItemCreated Backlog Item created by the user to add.
     */
    public void addBacklogItemCreated(BacklogItem backlogItemCreated) {
        this.backlogItemsCreated.add(backlogItemCreated);
    }

    /**
     * Backlog Items created by the user.
     *
     * @param backlogItemsCreated Backlog Items created by the user.
     */
    public void setBacklogItemsCreated(List<BacklogItem> backlogItemsCreated) {
        this.backlogItemsCreated = backlogItemsCreated;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.whisperio.data.entity.User[ id=" + id + " ]";
    }
}
