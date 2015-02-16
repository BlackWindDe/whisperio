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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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
            Logger.getLogger(ReleaseController.class.getName()).log(Level.SEVERE, null, ex);
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
     * ToDo: Delete Sprints.
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
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
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
            persistantRelease.setEstimatedNumberOfSprintToEmpty(release.getEstimatedNumberOfSprintToEmpty());
            persistantRelease.setEstimatedRemainingPointEndOfRelease(release.getEstimatedRemainingPointEndOfRelease());

            //Merge & Commit.
            em.merge(persistantRelease);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(ReleaseController.class.getName()).log(Level.SEVERE, null, ex);
            persistantRelease = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return persistantRelease;
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
