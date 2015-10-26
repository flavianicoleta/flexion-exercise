package com.flexion.test;

import com.flexion.integration.impl.BillingIntegration;
import com.flexionmobile.codingchallenge.integration.Integration;
import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;

/**
 * Created by flavia.gheorghe on 22/10/15.
 */
public class Application {

    public static void main(String[] args) {
        Integration integration = new BillingIntegration();

        IntegrationTestRunner integrationTestRunner = new IntegrationTestRunner();

        integrationTestRunner.runTests(integration);
    }
}
