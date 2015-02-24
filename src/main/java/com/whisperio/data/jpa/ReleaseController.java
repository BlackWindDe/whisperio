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
package com.whisperio.data.jpa;

import com.whisperio.data.entity.BacklogItem;
import com.whisperio.data.entity.Project;
import com.whisperio.data.entity.Release;
import com.whisperio.data.entity.Sprint;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 * Controller class from Releases table.
 *
 * @author Maxime ESCOURBIAC
 */
public class ReleaseController {

    private final EntityManagerFactory emf;

    /**
     * Default constructor.
     */
    public ReleaseController() {
        this.emf = Persistence.createEntityManagerFactory("com.whisperio_db");
    }

    /**
     * Entity Manager. Manage the object-relational-mapping persistance.
     *
     * @return Entity Manager.
     */
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    /**
     * Create the release in database.
     *
     * @param release Release to create.
     * @return Release created with id value returned.
     *
     * Sprints and Backlog items are not taken in account because the release
     * creation process these two lists are empty.
     */
    public Release create(Release release) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            //Insert release
            em.persist(release);
            em.flush();

            //Update Project.
            Project project = release.getProject();
            project.addRelease(release);
            em.merge(project);

            //Commit.
            em.getTransaction().commit();
        } catch (Exception ex) {
            release = null;
            Logger.getLogger(ReleaseController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return release;
    }

    /**
     * Destroy the release.
     *
     * @param release Release to destroy.
     * @return True if the deletion was sucessfuly executed.
     *
     * ToDo: Delete Sprints on cascade.
     */
    public boolean destroy(Release release) {
        EntityManager em = null;
        boolean success = true;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            release = em.getReference(Release.class, release.getId());

            //Updating project.
            Project project = release.getProject();
            if (project != null) {
                project.getReleases().remove(release);
                em.merge(project);
            }

            //Updating Backlog items
            List<BacklogItem> backlogItems = release.getBacklogItems();
            for (BacklogItem backlogItem : backlogItems) {
                backlogItem.setRelease(null);
                em.merge(backlogItem);
            }

            //Deleting release.
            em.remove(em.merge(release));
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(ReleaseController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            success = false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return success;
    }

    /**
     * Edit release attributes of the release.
     *
     * @param release Release to update.
     * @return Release updated.
     *
     * Only attribute editable in the project will be taken in account in this
     * method.
     */
    public Release edit(Release release) {
        EntityManager em = null;
        Release persistantRelease;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            persistantRelease = em.find(Release.class, release.getId());

            //Editing release values.
            persistantRelease.setName(release.getName());
            persistantRelease.setEndDate(release.getEndDate());
            persistantRelease.setIsActive(release.isActive());
            persistantRelease.setNumberOfSprint(release.getNumberOfSprint());

            //Merge & Commit.
            em.merge(persistantRelease);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(ReleaseController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            persistantRelease = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return persistantRelease;
    }

    /**
     * Get closed sprints of the release.
     *
     * @param release Release to get.
     * @return The active result;
     */
    public List<Sprint> getReleaseClosedSprints(Release release) {
        EntityManager em = null;
        List<Sprint> closedSprints = null;
        try {
            em = getEntityManager();
            closedSprints = (List<Sprint>) em.createNamedQuery(("Sprints.getReleaseClosedSprint"))
                    .setParameter("releaseID", release.getId()).getResultList();
        } catch (NoResultException ex) {
            Logger.getLogger(ReleaseController.class.getName()).
                    log(Level.WARNING, "No closed sprints for release: {0}", release.getId());
            closedSprints = null;
        } catch (Exception ex) {
            Logger.getLogger(ReleaseController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            closedSprints = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return closedSprints;
    }

    /**
     * Compute the average velocity of a release.
     *
     * @param release Release velocity to compute
     * @return The average velocity of the release.
     */
    public BigDecimal getReleaseAverageVelocity(Release release) {
        List<Sprint> closedSprints = getReleaseClosedSprints(release);
        BigDecimal averageVelocity = BigDecimal.ZERO;
        if (closedSprints != null) {
            for (Sprint closedSprint : closedSprints) {
                averageVelocity = averageVelocity.add(closedSprint.getVelocity());
            }
            averageVelocity = averageVelocity.divide(new BigDecimal(closedSprints.size()), MathContext.DECIMAL128);
        }
        return averageVelocity;
    }

    /**
     * Refresh a release.
     *
     * @param release Release to refresh.
     * @return The release refreshed.
     */
    public Release refresh(Release release) {
        return getEntityManager().getReference(Release.class, release.getId());
    }
}
