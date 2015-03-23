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

import com.whisperio.data.entity.User;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 * Controller class from Users table.
 *
 * @author Maxime ESCOURBIAC
 */
public class UserController {

    private final EntityManagerFactory emf;

    /**
     * Default constructor.
     */
    public UserController() {
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
     * Create the user in database.
     *
     * @param user User to create.
     * @return User created with id value returned.
     *
     * The user creation process has been simplified. The only case where this
     * method is called, the future user cannot have any interactions with
     * existants projects.
     */
    public User create(User user) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(user);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            user = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return user;
    }

    /**
     * Edit the user in database.
     *
     * @param user User to edit.
     * @return User edited.
     *
     * The user edition process has been simplified. The only case where this
     * method is called, is during update user profile. For assignment stuff, it
     * will used the edit method of the concerned element.
     */
    public User edit(User user) {
        EntityManager em = null;
        User persistantUser;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            persistantUser = em.find(User.class, user.getId());
            persistantUser.setLastName(user.getLastName());
            persistantUser.setForename(user.getForename());
            persistantUser.setUsername(user.getUsername());
            persistantUser.setMail(user.getMail());
            persistantUser = em.merge(persistantUser);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            persistantUser = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return persistantUser;
    }

    /**
     * Destroy the user.
     *
     * @param user User to destroy.
     * @return True if the deletion was sucessfuly executed.
     *
     * User having interactions with projects, backlog items etc... cannot be
     * deleted for tracability reasons. It should be disabled.
     */
    public boolean destroy(User user) {
        EntityManager em = null;
        boolean success = true;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(user));
            em.getTransaction().commit();
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            success = false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return success;
    }

    /**
     * List of all users
     *
     * @return List of all users
     */
    public List<User> getUsers() {
        EntityManager em = null;
        List<User> users = null;
        try {
            em = getEntityManager();
            users = em.createNamedQuery(("Users.findAll")).getResultList();
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            users = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return users;
    }

    /**
     * Look for user by mail
     *
     * @param mail User's mail
     * @return User corresponding to the mail.
     */
    public User getUserByMail(String mail) {
        EntityManager em = null;
        User user = null;
        try {
            em = getEntityManager();
            user = (User) em.createNamedQuery(("Users.findByMail")).setParameter("mail", mail).getSingleResult();
        } catch (NoResultException ex) {
            user = null;
        } catch (Exception ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            user = null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return user;
    }

    /**
     * Refresh an user.
     *
     * @param user User to refresh.
     * @return The user refreshed.
     */
    public User refresh(User user) {
        return getEntityManager().getReference(User.class, user.getId());
    }
}
