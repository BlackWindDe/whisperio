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
 * Story Types. Be Careful, the order is very important!!!
 *
 * @author Maxime ESCOURBIAC
 */
public enum BacklogItemType {

    /**
     * Items used in the sandbox or the icebox.
     */
    IDEA, //0

    /**
     * Big Story.
     */
    EPIC, //1

    /**
     * Functional Story. (Visibility + added value)
     */
    USER_STORY, //2

    /**
     * Technical Story (Added Value)
     */
    TECHNICAL_STORY, //3

    /**
     * Story promoted from the issue tracker.
     */
    BUG_RESOLUTION, //4

    /**
     * Technical Debt (Test, Documentation missing etc...)
     */
    TECHNICAL_DEBT, //5

    /**
     * Backlog Items used for studying some subjects.
     */
    SPIKE //6
}
