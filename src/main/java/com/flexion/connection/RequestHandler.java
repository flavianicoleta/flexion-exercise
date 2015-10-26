package com.flexion.connection;

import java.util.Map;

/**
 * Created by flavia.gheorghe on 22/10/15.
 */
public interface RequestHandler {
    Map<String, String> createRequest(String connectURL, String method);
}
