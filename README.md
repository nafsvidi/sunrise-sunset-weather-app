# Sunrise/Sunset Weather Notification Web Application

A Java web application that informs users whether the sunrise or sunset will be viewable based on current weather conditions.

## Features
- Check if weather conditions are favorable for viewing sunrise/sunset
- Real-time weather data from OpenWeatherMap API
- Responsive design for mobile and desktop

## Technologies Used
- Java Servlets and JSP
- HTML5, CSS3, and JavaScript
- Maven for dependency management
- Apache Tomcat as the web server
- OpenWeatherMap API for weather data

## Installation Instructions
1. Prerequisites:
   - Java JDK 8 or higher
   - Apache Maven
   - Apache Tomcat 9
   - OpenWeatherMap API key

2. Setup:
   - Clone this repository
   - Open the project in your IDE
   - Update the API key in WeatherService.java
   - Build with Maven: `mvn clean package`
   - Deploy the WAR file to Tomcat

3. Running the Application:
   - Start Tomcat
   - Access the application at: http://localhost:8080/sunrise-sunset-weather-app/

## Usage
1. Enter a city name in the search form
2. Click "Check Weather"
3. View the result showing whether sunrise/sunset will be viewable

## Project Structure
- `src/main/java/com/sunriseapp/model/` - Data models
- `src/main/java/com/sunriseapp/service/` - Business logic
- `src/main/java/com/sunriseapp/servlet/` - Controllers
- `src/main/java/com/sunriseapp/util/` - Utility classes
- `src/main/webapp/` - JSP views and web resources