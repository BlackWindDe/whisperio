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

import com.whisperio.data.entity.Project;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 * Controller class from Projects table.
 *
 * @author Maxime ESCOURBIAC
 */
public class ProjectController {

    private final EntityManagerFactory emf;

    /**
     * Default constructor.
     */
    public ProjectController() {
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
     * Create the project in database.
     *
     * @param project Project to create.
     * @return Project created with id value returned.
     */
    public Project create(Project project) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            //Insert project
            em.persist(project);
            em.flush();

            //Commit.
            em.getTransaction().commit();
        } catch (Exception ex) {
            project = null;
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return project;
    }

    /**
     * Destroy the project.
     *
     * @param project Project to destroy.
     * @return True if the deletion was successfuly executed.
     *
     * ToDo: Delete on cascate Release, Sprint, Backlog Items.
     */
    public boolean destroy(Project project) {
        EntityManager em = null;
        boolean success = true;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            project = em.getReference(Project.class, project.getId());

            //Deleting project.
            em.remove(em.merge(project));
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
     * List of all projects.
     *
     * @return List of all projects.
     */
    public List<Project> getAllProjects() {
        EntityManager em = null;
        List<Project> projects = null;
        try {
            em = getEntityManager();
            projects = em.createNamedQuery(("Projects.findAll")).getResultList();
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            projects = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return projects;
    }

    /**
     * Look for project by key name.
     *
     * @param keyName Project key name.
     * @return Project corresponding to the key name.
     */
    public Project getProjectByKeyName(String keyName) {
        EntityManager em = null;
        Project project = null;
        try {
            em = getEntityManager();
            project = (Project) em.createNamedQuery(("Projects.findByKeyName")).setParameter("keyName", keyName).getSingleResult();
        } catch (NoResultException ex) {
            project = null;
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
            project = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return project;
    }

    /**
     * Refresh a project.
     *
     * @param project Project to refresh.
     * @return The project refreshed.
     */
    public Project refresh(Project project) {
        return getEntityManager().getReference(Project.class, project.getId());
    }
}
