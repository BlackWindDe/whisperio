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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Estimation Scale using Fibonnacci for estimating Story complexity.
 *
 * @author Maxime ESCOURBIAC
 */
@Entity
@Table(name = "StoryEstimation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StoryEstimation.findAll", query = "SELECT s FROM StoryEstimation s")
})
public class StoryEstimation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "Name")
    private String name;

    @Basic(optional = false)
    @Column(name = "Value")
    private BigDecimal value;

    /**
     * Default constructor.
     */
    public StoryEstimation() {
    }

    /**
     * StoryEstimation constructor
     *
     * @param name Visible name.
     * @param value Real value.
     */
    public StoryEstimation(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    /**
     * StoryEstimation ID.
     *
     * @return StoryEstimation ID.
     */
    public Integer getId() {
        return id;
    }

    /**
     * StoryEstimation ID.
     *
     * @param id StoryEstimation ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Visible name.
     *
     * @return Visible name.
     */
    public String getName() {
        return name;
    }

    /**
     * Visible name.
     *
     * @param name Visible name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Real value.
     *
     * @return Real value.
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Real value.
     *
     * @param value Real value.
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof StoryEstimation)) {
            return false;
        }
        StoryEstimation other = (StoryEstimation) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.whisperio.data.entity.StoryEstimation[ id=" + id + " ]";
    }

}
