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
package com.whisperio.data.entity;

/**
 * Story Types (User, Technical, Bug Resolution etc...).
 *
 * @author Maxime ESCOURBIAC
 */
public enum BacklogItemType {

    /**
     * Items used in the sandbox or the icebox.
     */
    IDEA,
    
    /**
     * The highest level of Backlog Item.
     */
    FEATURE,

    /**
     * Big Story.
     */
    EPIC,
    
    /**
     * Functional Story. (Visibility + added value)
     */
    USER_STORY,

    /**
     * Technical Story (Added Value)
     */
    TECHNICAL_STORY,

    /**
     * Story promoted from the issue tracker.
     */
    BUG_RESOLUTION,

    /**
     * Technical Debt (Test, Documentation missing etc...)
     */
    TECHNICAL_DEBT,

    /**
     * Backlog Items used for studying some subjects.
     */
    SPIKE
}
