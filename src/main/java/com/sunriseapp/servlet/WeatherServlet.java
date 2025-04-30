package com.sunriseapp.servlet;

import com.sunriseapp.model.WeatherData;
import com.sunriseapp.service.WeatherService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet to handle weather data requests and forward to JSP
 */
@WebServlet("/weather")
public class WeatherServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private WeatherService weatherService;
    
    /**
     * Initializes the servlet and creates a WeatherService instance
     */
    @Override
    public void init() {
        weatherService = new WeatherService();
    }
    
    /**
     * Handles GET requests - retrieves city from parameters, gets weather data, 
     * and forwards to the result JSP
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            // Get city parameter from the request (or use blank for default)
            String city = request.getParameter("city");
            
            // Get weather data from the service
            WeatherData weatherData = weatherService.getWeatherData(city);
            
            // Store the weather data in the request attribute
            request.setAttribute("weatherData", weatherData);
            
            // Forward to the result JSP
            request.getRequestDispatcher("/WEB-INF/views/result.jsp").forward(request, response);
            
        } catch (Exception e) {
            // Log the error
            getServletContext().log("Error processing weather data", e);
            
            // Send error message to the client
            request.setAttribute("errorMessage", "Error retrieving weather data: " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
    
    /**
     * Handles POST requests - same behavior as GET for this application
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Just call doGet for this application since behavior is the same
        doGet(request, response);
    }
}