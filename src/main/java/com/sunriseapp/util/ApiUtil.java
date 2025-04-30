package com.sunriseapp.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utility class to handle API requests
 */
public class ApiUtil {

    /**
     * Sends a GET request to the specified URL
     * 
     * @param apiUrl The URL to send the request to
     * @return The response as a String
     * @throws IOException If an I/O error occurs during the request
     */
    public static String sendGetRequest(String apiUrl) throws IOException {
        // Create a URL object
        URL url = new URL(apiUrl);
        
        // Open a connection to the URL
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        // Set request method
        connection.setRequestMethod("GET");
        
        // Set timeouts
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        
        // Check for successful response (200)
        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("HTTP error code: " + responseCode);
        }
        
        // Read the response
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        
        // Close the connection
        connection.disconnect();
        
        return response.toString();
    }
}