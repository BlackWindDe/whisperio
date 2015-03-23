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

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import com.whisperio.data.entity.User;

/**
 * Test class for UserController.
 *
 * @author Maxime ESCOURBIAC
 */
public class UserControllerTest {

    /**
     * Default constructor.
     */
    public UserControllerTest() {
    }

    /**
     * Test of create method, of class UserController.
     */
    @Test
    public void testCreate() {
        System.out.println("UserController:Create");
        String mail = "testCreate@whisper.io";
        String username = "Username";
        String forename = "Forename";
        String lastName = "Last Name";
        User user = new User(mail, username, forename, lastName);
        UserController instance = new UserController();
        User userResult = instance.create(user);
        assertNotNull(userResult.getId());
        assertEquals(mail, userResult.getMail());
        assertEquals(username, userResult.getUsername());
        assertEquals(forename, userResult.getForename());
        assertEquals(lastName, userResult.getLastName());
        instance.destroy(user);
    }

    /**
     * Test of edit method, of class UserController.
     */
    @Test
    public void testEdit() {
        System.out.println("UserController:Edit");
        UserController instance = new UserController();
        User oldUser = new User("testEditOld@whisper.io", "Username", "Forename", "Last Name");
        oldUser = instance.create(oldUser);
        User newUser = new User("testEditNew@whisper.io", "New Username", "New Forename", "New Last Name");
        newUser.setId(oldUser.getId());
        User editResult = instance.edit(newUser);
        assertEquals(newUser.getMail(), editResult.getMail());
        assertEquals(newUser.getUsername(), editResult.getUsername());
        assertEquals(newUser.getForename(), editResult.getForename());
        assertEquals(newUser.getLastName(), editResult.getLastName());
        instance.destroy(editResult);
    }

    /**
     * Test of destroy method, of class UserController.
     */
    @Test
    public void testDestroy() {
        System.out.println("UserController:Destroy");
        User newUser = new User("testDestroy@whisper.io", "Username", "Forename", "Last Name");
        UserController instance = new UserController();
        newUser = instance.create(newUser);
        assertTrue(instance.destroy(newUser));
        assertNull(instance.getUserByMail("testDestroy@whisper.io"));
    }

    /**
     * Test of getUsers method, of class UserController.
     */
    @Test
    public void testGetUsers() {
        System.out.println("UserController:GetUsers");
        UserController instance = new UserController();
        User user1 = new User("testGetUsers1@whisper.io", "Username1", "Forename", "Last Name");
        User user2 = new User("testGetUsers2@whisper.io", "Username2", "Forename", "Last Name");
        User user3 = new User("testGetUsers3@whisper.io", "Username3", "Forename", "Last Name");
        user1 = instance.create(user1);
        user2 = instance.create(user2);
        user3 = instance.create(user3);
        List result = instance.getUsers();
        assertTrue(result.contains(user1));
        assertTrue(result.contains(user2));
        assertTrue(result.contains(user3));
        instance.destroy(user1);
        instance.destroy(user2);
        instance.destroy(user3);
    }

    /**
     * Test of getUserByMail method, of class UserController.
     */
    @Test
    public void testGetUserByMail() {
        System.out.println("UserController:GetUserByMail");
        User user = new User("testGetUserByMail@whisper.io", "Username", "Forename", "Last Name");
        UserController instance = new UserController();
        instance.create(user);

        //Check true case.
        User userResult = instance.getUserByMail(user.getMail());
        assertEquals(user.getMail(), userResult.getMail());
        assertEquals(user.getForename(), userResult.getForename());
        assertEquals(user.getLastName(), userResult.getLastName());
        instance.destroy(userResult);

        //Check wrong case.
        userResult = instance.getUserByMail("FalseCase@whisper.io");
        assertNull(userResult);
    }

    /**
     * Test of getUserByUsername method, of class UserController.
     */
    @Test
    public void testGetUserByUsername() {
        System.out.println("UserController:GetUserByUsername");
        User user = new User("testGetUserByUsername@whisper.io", "Username", "Forename", "Last Name");
        UserController instance = new UserController();
        instance.create(user);

        //Check true case.
        User userResult = instance.getUserByUsername(user.getUsername());
        assertEquals(user.getMail(), userResult.getMail());
        assertEquals(user.getForename(), userResult.getForename());
        assertEquals(user.getLastName(), userResult.getLastName());
        instance.destroy(userResult);

        //Check wrong case.
        userResult = instance.getUserByUsername("FalseCase@whisper.io");
        assertNull(userResult);
    }
}
