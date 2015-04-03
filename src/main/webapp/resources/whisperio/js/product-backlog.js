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

/**
 * Constuct the list of the new order inside the product backlog box.
 * @returns Return the list of the new order inside the product backlog box.
 */
function constructProductBacklogItemsOrder() {
    var productBacklogItemsOrder = "";
    var prefix = "";
    var productBacklogItems = document.getElementById("productBacklogItemList");
    productBacklogItems = productBacklogItems.children;
    for (i = 0; i < productBacklogItems.length; i++) {
        productBacklogItemsOrder = productBacklogItemsOrder + prefix + productBacklogItems[i].id;
        prefix = ";";
    }
    return productBacklogItemsOrder;
}