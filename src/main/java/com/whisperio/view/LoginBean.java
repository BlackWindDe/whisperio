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
package com.whisperio.view;

import com.whisperio.data.entity.User;
import com.whisperio.data.jpa.UserController;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Bean used by the index page.
 *
 * @author Maxime ESCOURBIAC
 */
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    /**
     * Creates a new instance of indexBean
     */
    public LoginBean() {
    }

    /**
     * Connect Method.
     */
    public void connect() {
        FacesContext context = FacesContext.getCurrentInstance();
        UserController userController = new UserController();
        try {
            User userChecked = userController.getUserByUsername(username);
            if (userChecked != null) {
                sessionBean.setConnectedUser(userChecked);
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Whisperio/dashboard.xhtml");
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Connection failed", "The user " + username + " is not existing."));
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    /**
     * Auto-loging Method. If the user is already connected, he will be
     * redirected to projects pages.
     */
    public void autologin() {
        if (sessionBean.getConnectedUser() != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Whisperio/dashboard.xhtml");

            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class
                        .getName()).log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }

    /**
     * Username from the sign in form.
     *
     * @return Username from the sign in form.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Username from the sign in form.
     *
     * @param username Username from the sign in form.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Password from the sign in form.
     *
     * @return Password from the sign in form.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Password from the sign in form.
     *
     * @param password Password from the sign in form.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Session Bean.
     *
     * @return Session Bean.
     */
    public SessionBean getSessionBean() {
        return sessionBean;
    }

    /**
     * Session Bean.
     *
     * @param sessionBean Session Bean.
     */
    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }
}
