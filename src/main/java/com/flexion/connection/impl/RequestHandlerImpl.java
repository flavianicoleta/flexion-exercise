package com.flexion.connection.impl;

import com.flexion.connection.RequestHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by flavia.gheorghe on 22/10/15.
 */
public class RequestHandlerImpl implements RequestHandler {

    public Map<String, String> createRequest(String connectURL, String method) {
        InputStream response = null;

        try {
            URL url = new URL(connectURL);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);

            urlConnection.setRequestMethod(method);

            urlConnection.setRequestProperty("Content-Type","application/json");
            urlConnection.connect();

            response = urlConnection.getInputStream();
            StringBuilder line = new StringBuilder();

            int character;
            while ((character = response.read())!= -1) {
                char c = (char) character;
                line.append(c);
            }

            Map<String, String> result = new HashMap<String, String>();
            result.put("code", String.valueOf(urlConnection.getResponseCode()));
            if(!line.toString().equals("")) {
                result.put("response", line.toString());
            }
            return result;
        } catch (Exception e) {
            return null;
        } finally {
            if(response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
