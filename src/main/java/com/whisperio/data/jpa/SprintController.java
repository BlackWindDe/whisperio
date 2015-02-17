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
import com.whisperio.data.entity.Release;
import com.whisperio.data.entity.Sprint;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Controller class from Sprints table.
 *
 * @author Maxime ESCOURBIAC
 */
public class SprintController {

    private final EntityManagerFactory emf;

    /**
     * Default constructor.
     */
    public SprintController() {
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
     * Create the sprint in database.
     *
     * @param sprint Sprint to create.
     * @return Sprint created with id value returned.
     *
     * Backlog items are not taken in account because the sprint creation
     * process this list is empty.
     */
    public Sprint create(Sprint sprint) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            //Insert sprint.
            em.persist(sprint);
            em.flush();

            //Update Release.
            Release release = sprint.getRelease();
            release.addSprint(sprint);
            em.merge(release);

            //Commit.
            em.getTransaction().commit();
        } catch (Exception ex) {
            sprint = null;
            Logger.getLogger(SprintController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return sprint;
    }

    /**
     * Edit sprint attributes of the Sprint.
     *
     * @param sprint Sprint to update.
     * @return Sprint updated.
     *
     * Only attribute editable in the project will be taken in account in this
     * method.
     */
    public Sprint edit(Sprint sprint) {
        EntityManager em = null;
        Sprint persistantSprint;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            persistantSprint = em.find(Sprint.class, sprint.getId());

            //Editing sprint values.
            persistantSprint.setName(sprint.getName());
            persistantSprint.setEndDate(sprint.getEndDate());
            persistantSprint.setIsActive(sprint.isActive());
            persistantSprint.setVelocity(sprint.getVelocity());
            persistantSprint.setReleaseRemainingPointEndOfSprint(sprint.getReleaseRemainingPointEndOfSprint());

            //Merge & Commit.
            em.merge(persistantSprint);
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(SprintController.class.getName()).log(Level.SEVERE, null, ex);
            persistantSprint = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return persistantSprint;
    }

    /**
     * Destroy the sprint.
     *
     * @param sprint Sprint to destroy.
     * @return True if the deletion was sucessfuly executed.
     *
     */
    public boolean destroy(Sprint sprint) {
        EntityManager em = null;
        boolean success = true;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sprint = em.getReference(Sprint.class, sprint.getId());

            //Updating release.
            Release release = sprint.getRelease();
            if (release != null) {
                release.getSprints().remove(sprint);
                em.merge(release);
            }

            //Updating Backlog items
            List<BacklogItem> backlogItems = sprint.getBacklogItems();
            for (BacklogItem backlogItem : backlogItems) {
                backlogItem.setSprint(null);
                em.merge(backlogItem);
            }

            //Deleting sprint.
            em.remove(em.merge(sprint));
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(Sprint.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return success;
    }

    /**
     * Refresh a sprint.
     *
     * @param sprint Sprint to refresh.
     * @return The sprint refreshed.
     */
    public Sprint refresh(Sprint sprint) {
        return getEntityManager().getReference(Sprint.class, sprint.getId());
    }
}
