<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.sunriseapp.model.WeatherData" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weather Results - Sunrise/Sunset App</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Sunrise/Sunset Weather Results</h1>
        </header>
        
        <main>
            <% 
            // Get the weather data from request attributes
            WeatherData weatherData = (WeatherData) request.getAttribute("weatherData");
            
            // Check if weather data exists
            if (weatherData != null) {
                String resultClass = weatherData.isViewable() ? "notification success" : "notification warning";
            %>
                <section class="result-section">
                    <div class="location-info">
                        <h2><%= weatherData.getCityName() %>, <%= weatherData.getCountryCode() %></h2>
                    </div>
                    
                    <div class="<%= resultClass %>">
                        <p><%= weatherData.getMessage() %></p>
                    </div>
                    
                    <div class="weather-details">
                        <p><strong>Weather Condition:</strong> <%= weatherData.getWeatherCondition() %></p>
                        <p><strong>Description:</strong> <%= weatherData.getWeatherDescription() %></p>
                        <p><strong>Cloud Cover:</strong> <%= weatherData.getCloudCover() %>%</p>
                    </div>
                </section>
            <% } else { %>
                <section class="error-section">
                    <div class="notification error">
                        <p>Sorry, no weather data is available. Please try again.</p>
                    </div>
                </section>
            <% } %>
            
            <section class="back-section">
                <a href="../index.jsp" class="btn">Check Another City</a>
            </section>
        </main>
        
        <footer>
            <p>&copy; 2025 Sunrise/Sunset Weather App</p>
        </footer>
    </div>
    
    <script src="../js/script.js"></script>
</body>
</html>