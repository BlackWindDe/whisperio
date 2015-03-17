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

import com.whisperio.data.entity.StoryEstimation;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Controller class from StoryEstimation table.
 *
 * @author Maxime ESCOURBIAC
 */
public class StoryEstimationController {

    private final EntityManagerFactory emf;

    /**
     * Default constructor.
     */
    public StoryEstimationController() {
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
     * Create an StoryEstimation tuple.
     *
     * @param estimation Tuple to create.
     * @return Tuple created.
     *
     */
    public StoryEstimation create(StoryEstimation estimation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estimation);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(StoryEstimationController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            estimation = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return estimation;
    }

    /**
     * Destroy the StoryEstimation tuple.
     *
     * @param estimation StoryEstimation tuple to destroy.
     * @return True if the deletion was sucessfuly executed.
     *
     */
    public boolean destroy(StoryEstimation estimation) {
        EntityManager em = null;
        boolean success = true;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(estimation));
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(StoryEstimationController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            success = false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return success;
    }

    /**
     * List of all StoryEstimations.
     *
     * @return List of all StoryEstimations.
     */
    public List<StoryEstimation> getStoryEstimations() {
        EntityManager em = null;
        List<StoryEstimation> estimations = null;
        try {
            em = getEntityManager();
            estimations = em.createNamedQuery(("StoryEstimation.findAll")).getResultList();
        } catch (Exception ex) {
            Logger.getLogger(StoryEstimationController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            estimations = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return estimations;
    }
}
