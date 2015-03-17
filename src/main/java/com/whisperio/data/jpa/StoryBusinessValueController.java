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

import com.whisperio.data.entity.StoryBusinessValue;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Controller class from StoryBusinessValue table.
 *
 * @author Maxime ESCOURBIAC
 */
public class StoryBusinessValueController {

    private final EntityManagerFactory emf;

    /**
     * Default constructor.
     */
    public StoryBusinessValueController() {
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
     * Create an StoryBusinessValue tuple.
     *
     * @param estimation Tuple to create.
     * @return Tuple created.
     *
     */
    public StoryBusinessValue create(StoryBusinessValue estimation) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estimation);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(StoryBusinessValueController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            estimation = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return estimation;
    }

    /**
     * Destroy the StoryBusinessValue tuple.
     *
     * @param businessValue StoryBusinessValue tuple to destroy.
     * @return True if the deletion was sucessfuly executed.
     *
     */
    public boolean destroy(StoryBusinessValue businessValue) {
        EntityManager em = null;
        boolean success = true;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(businessValue));
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(StoryBusinessValueController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            success = false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return success;
    }

    /**
     * List of all StoryBusinessValues.
     *
     * @return List of all StoryBusinessValues.
     */
    public List<StoryBusinessValue> getStoryBusinessValues() {
        EntityManager em = null;
        List<StoryBusinessValue> businessValues = null;
        try {
            em = getEntityManager();
            businessValues = em.createNamedQuery(("StoryBusinessValue.findAll")).getResultList();
        } catch (Exception ex) {
            Logger.getLogger(StoryBusinessValueController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            businessValues = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return businessValues;
    }
}
