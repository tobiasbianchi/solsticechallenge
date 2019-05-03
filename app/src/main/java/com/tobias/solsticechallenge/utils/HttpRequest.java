package com.tobias.solsticechallenge.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class HttpRequest {
    public interface ConnectionPreparer {
        void prepareForRequest(HttpURLConnection connection) throws ProtocolException;
    }

    public static class Response {
        private int code;
        private String responseText;

        Response(int code, String responseText) {
            this.code = code;
            this.responseText = responseText;
        }

        public boolean requestWasResolved() {
            return responseText != null;
        }

        public String getResponseText() {
            return responseText;
        }

        public int getCode() {
            return code;
        }
    }

    public HttpRequest(String uri){
        this.uri = uri;
    }
    private String uri;

    public Response doRequest(ConnectionPreparer preparer) {
        HttpURLConnection client = null;
        int statusCode = -1;
        String data = null;
        try {
            URL url = new URL(uri);
            client = (HttpURLConnection) url.openConnection();
            preparer.prepareForRequest(client);
            client.connect();

            BufferedReader br;
            statusCode = client.getResponseCode();

            if( HttpRequest.successResponse(statusCode) ) {
                br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            } else {
                br = new BufferedReader(new InputStreamReader(client.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                sb.append(output);
            }
            data = sb.toString();
        } catch ( IOException e) {
            e.printStackTrace();
        } finally {
            if(client != null) {
                client.disconnect();
            }
        }
        return new Response(statusCode, data);
    }

    public static boolean successResponse(int statusCode){
        return 200 <= statusCode && statusCode <= 299;
    }
}
