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
@ManagedBean(name = "indexBean")
@RequestScoped
public class IndexBean implements Serializable {

    private String mail;

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    /**
     * Creates a new instance of indexBean
     */
    public IndexBean() {
    }

    /**
     * Connect Method.
     */
    public void connect() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (sessionBean.connect(mail)) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Whisperio/Projects.xhtml");
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Connection failed", "The user " + mail + " is not existing."));
            }
        } catch (Exception ex) {
            Logger.getLogger(IndexBean.class.getName()).log(Level.SEVERE, null, ex);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Exception", ex.getMessage()));
        }
    }

    /**
     * Auto-loging Method. If the user is already connected, he will be
     * redirected to projects pages.
     */
    public void autologin() {
        if (sessionBean.getConnectedUser() != null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/whisperio/Projects.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(IndexBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Mail from the sign in form.
     *
     * @return Mail from the sign in form.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Mail from the sign in form.
     *
     * @param mail Mail from the sign in form.
     */
    public void setMail(String mail) {
        this.mail = mail;
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
