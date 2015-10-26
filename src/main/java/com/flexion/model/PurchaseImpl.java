package com.flexion.model;

import com.flexionmobile.codingchallenge.integration.Purchase;

/**
 * Created by flavia.gheorghe on 22/10/15.
 */
public class PurchaseImpl implements Purchase {

    private String id;
    private boolean consumed;
    private String itemId;

    public String getId() {
        return id;
    }

    public boolean getConsumed() {
        return consumed;
    }

    public String getItemId() {
        return itemId;
    }
}
