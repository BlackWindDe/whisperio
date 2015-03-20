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
import com.whisperio.data.entity.ProductBacklogBox;
import com.whisperio.data.entity.Project;
import com.whisperio.data.entity.Release;
import com.whisperio.data.entity.Sprint;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
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
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
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
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
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
            Logger.getLogger(ProjectController.class.getName()).log(Level.WARNING, "Project not found: {0}", keyName);
            project = null;
        } catch (Exception ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            project = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return project;
    }

    /**
     * Get active release of the project.
     *
     * @param project project to get.
     * @return The project active release.
     */
    public Release getProjectActiveRelease(Project project) {
        EntityManager em = null;
        Release release = null;
        try {
            em = getEntityManager();
            release = (Release) em.createNamedQuery(("Releases.getProjectActiveRelease"))
                    .setParameter("projectID", project.getId()).getSingleResult();
        } catch (NoResultException ex) {
            Logger.getLogger(ProjectController.class.getName()).
                    log(Level.WARNING, "No active release for project: {0}", project.getId());
            release = null;
        } catch (NonUniqueResultException ex) {
            Logger.getLogger(ProjectController.class.getName())
                    .log(Level.SEVERE, "Multiple active release for project: {0}", project.getId());
            release = null;
        } catch (Exception ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            release = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return release;
    }

    /**
     * Get closed sprints of the project.
     *
     * @param project project to get.
     * @return The closed sprints lists of the project.
     */
    public List<Sprint> getProjectClosedSprints(Project project) {
        EntityManager em = null;
        List<Sprint> sprints = null;
        try {
            em = getEntityManager();
            sprints = (List<Sprint>) em.createNamedQuery(("Sprints.getProjectClosedSprints"))
                    .setParameter("projectID", project.getId()).getResultList();
        } catch (Exception ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            sprints = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return sprints;
    }

    /**
     * Get all the items contained in the sandbox.
     *
     * @param project Project to get.
     * @return The items contained in the sandbox.
     */
    public List<BacklogItem> getSandboxItems(Project project) {
        return getProductBacklogBoxItems(project, ProductBacklogBox.SANDBOX);
    }

    /**
     * Get all the items contained in the icebox.
     *
     * @param project Project to get.
     * @return The items contained in the icebox.
     */
    public List<BacklogItem> getIceboxItems(Project project) {
        return getProductBacklogBoxItems(project, ProductBacklogBox.ICEBOX);
    }

    /**
     * Get all the items contained in the culturebox.
     *
     * @param project Project to get.
     * @return The items contained in the culturebox.
     */
    public List<BacklogItem> getCultureboxItems(Project project) {
        return getProductBacklogBoxItems(project, ProductBacklogBox.CULTUREBOX);
    }

    /**
     * Get all the items contained in the startbox.
     *
     * @param project Project to get.
     * @return The items contained in the startbox.
     */
    public List<BacklogItem> getStartboxItems(Project project) {
        return getProductBacklogBoxItems(project, ProductBacklogBox.STARTBOX);
    }

    /**
     * Get all the items contained in the sprintbox.
     *
     * @param project Project to get.
     * @return The items contained in the sprintbox.
     */
    public List<BacklogItem> getSprintboxItems(Project project) {
        return getProductBacklogBoxItems(project, ProductBacklogBox.SPRINTBOX);
    }

    /**
     * Get all the items contained in the harvestbox.
     *
     * @param project Project to get.
     * @return The items contained in the harvestbox.
     */
    public List<BacklogItem> getHarvestboxItems(Project project) {
        return getProductBacklogBoxItems(project, ProductBacklogBox.HARVESTBOX);
    }

    /**
     * Get all the items contained in a product backlog box.
     *
     * @param project Project to get.
     * @param box Product backlog box to get.
     * @return The items contained in the product backlog box.
     */
    private List<BacklogItem> getProductBacklogBoxItems(Project project, ProductBacklogBox box) {
        EntityManager em = null;
        List<BacklogItem> backlogItems = null;
        try {
            em = getEntityManager();
            backlogItems = (List<BacklogItem>) em.createNamedQuery(("BacklogItems.getProductBacklogBoxItems"))
                    .setParameter("projectID", project.getId()).setParameter("boxID", box).getResultList();
        } catch (Exception ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            backlogItems = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return backlogItems;
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
