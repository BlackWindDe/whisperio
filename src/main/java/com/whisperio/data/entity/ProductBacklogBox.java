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
 * Product Backlog Boxes according to Claude Aubry theory.
 *
 * @author Maxime ESCOURBIAC
 */
public enum ProductBacklogBox {

    /**
     * The place where PO, Scrum Team & Stakeholders could propose ideas.
     * Then the PO decide what action to take :
     * - Send the idea to the refinement box.
     * - Freeze the idea for a next release.
     * - Delete it.
     */
    SANDBOX,

    /**
     * The place where Epic, Backlog items where frozen for a next release.
     * Then the PO decide for which release the element will be defrost.
     */
    ICEBOX,

    /**
     * The place where Epic, Backlog items will be detailled to be developed in 
     * the release. The refinement process will be explain later. When Backlog 
     * items will be ready. It will be moved to the ready box. By the way, a 
     * story could be frost if the product owner is not considering the story 
     * for this release.
     */
    CULTUREBOX,

    /**
     * The place where stories are ready to be included in a sprint.
     * This box will be used during sprint planification meeting.
     */
    STARTBOX,

    /**
     * The place where stories could be decomposed in tasks and developped/tested. 
     * This box corresponding to the sprint board.If a story is not done at 
     * the end of the sprint, the remaining work must be estimated and the story
     * must be put in the ready box. The stories points will not be taken in account 
     * for velocity computation.
     */
    SPRINTBOX,

    /**
     * The place where done stories are stored. A story is done when all the
     * criterias of the definition of done are met.
     */
    HARVESTBOX
}
