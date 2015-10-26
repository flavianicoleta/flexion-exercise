package com.flexion.integration.impl;

import com.flexion.connection.RequestHandler;
import com.flexion.connection.impl.RequestHandlerImpl;
import com.flexion.model.PurchaseImpl;
import com.flexion.model.WrappperPurchaseList;
import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.Purchase;
import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by flavia.gheorghe on 22/10/15.
 */
public class BillingIntegration implements Integration {

    private static final String DEVELOPER_ID = "flavia";
    private static final String ENDPOINT = "http://dev2.flexionmobile.com/javachallenge/rest/developer/" + DEVELOPER_ID;
    private RequestHandler requestHandler;

    public BillingIntegration() {
        requestHandler = new RequestHandlerImpl();
    }


    public Purchase buy(String s) {
        String connectURL = String.format(ENDPOINT + "/buy/%s", s);
        Map<String, String> result = requestHandler.createRequest(connectURL, "POST");

        Purchase purchase = null;

        if(result != null && result.get("code").equals("200")) {
            try {
                purchase = new ObjectMapper().readValue(result.get("response"), PurchaseImpl.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return purchase;
    }

    public List<Purchase> getPurchases() {
        String connectURL = ENDPOINT + "/all";
        Map<String, String> result = requestHandler.createRequest(connectURL, "GET");

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule m = new SimpleModule("SimpleModule", Version.unknownVersion());
        m.addAbstractTypeMapping(Purchase.class, PurchaseImpl.class);
        mapper.registerModule(m);

        List<Purchase> purchaseList = new ArrayList<Purchase>();
        if(result != null && result.get("code").equals("200")) {
            try {
                WrappperPurchaseList wrappperPurchaseList = mapper.readValue(result.get("response"), WrappperPurchaseList.class);
                purchaseList = wrappperPurchaseList.getPurchases();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return purchaseList;
    }

    public void consume(Purchase purchase) {
        String connectURL = String.format(ENDPOINT + "/consume/%s", purchase.getId());
        requestHandler.createRequest(connectURL, "POST");
    }
}
