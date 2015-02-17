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
import com.whisperio.data.entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Controller class from BacklogItems table.
 *
 * @author Maxime ESCOURBIAC
 */
public class BacklogItemController {

    private final EntityManagerFactory emf;

    /**
     * Default constructor.
     */
    public BacklogItemController() {
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
     * Create the backlog item in database.
     *
     * @param backlogItem Backlog Item to create.
     * @return Backlog Item created with id value returned.
     *
     */
    public BacklogItem create(BacklogItem backlogItem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            //Insert backlog item
            em.persist(backlogItem);
            em.flush();

            //Update Project
            Project project = backlogItem.getProject();
            project.addBacklogItem(backlogItem);
            em.merge(project);

            //Update Release.
            Release release = backlogItem.getRelease();
            if (release != null) {
                release.addBacklogItem(backlogItem);
                em.merge(release);
            }

            //Update Sprint.
            Sprint sprint = backlogItem.getSprint();
            if (sprint != null) {
                sprint.addBacklogItem(backlogItem);
                em.merge(sprint);
            }

            //Update Creator.
            User creator = backlogItem.getCreator();
            creator.addBacklogItemCreated(backlogItem);
            em.merge(creator);

            //Commit.
            em.getTransaction().commit();
        } catch (Exception ex) {
            backlogItem = null;
            Logger.getLogger(BacklogItemController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return backlogItem;
    }

    /**
     * Destroy the backlog item.
     *
     * @param backlogItem Backlog Item to destroy.
     * @return True if the deletion was sucessfuly executed.
     *
     */
    public boolean destroy(BacklogItem backlogItem) {
        EntityManager em = null;
        boolean success = true;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            backlogItem = em.getReference(BacklogItem.class, backlogItem.getId());

            //Update Project
            Project project = backlogItem.getProject();
            project.getBacklogItems().remove(backlogItem);
            em.merge(project);

            //Update Release.
            Release release = backlogItem.getRelease();
            if (release != null) {
                release.getBacklogItems().remove(backlogItem);
                em.merge(release);
            }

            //Update Sprint.
            Sprint sprint = backlogItem.getSprint();
            if (sprint != null) {
                sprint.getBacklogItems().remove(backlogItem);
                em.merge(sprint);
            }

            //Update Creator.
            User creator = backlogItem.getCreator();
            creator.getBacklogItemsCreated().remove(backlogItem);
            em.merge(creator);

            //Deleting Backlog Item.
            em.remove(em.merge(backlogItem));
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(BacklogItemController.class.getName()).log(Level.SEVERE, null, ex);
            success = false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return success;
    }

    /**
     * Refresh a backlog item.
     *
     * @param backlogItem Backlog item to refresh.
     * @return The Backlog item refreshed.
     */
    public BacklogItem refresh(Sprint backlogItem) {
        return getEntityManager().getReference(BacklogItem.class, backlogItem.getId());
    }
}
