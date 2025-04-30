package com.sunriseapp.service;

import com.sunriseapp.model.WeatherData;
import com.sunriseapp.util.ApiUtil;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Service class to interact with the weather API and process data
 */
public class WeatherService {
    
    // Replace with your actual API key from OpenWeatherMap
    private static final String API_KEY = "459e0b5ccfdb6fb3330abe4678a934f2";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";
    
    // The ApiUtil instance to use for making API requests
    private ApiUtil apiUtil;
    
    /**
     * Default constructor that creates a new ApiUtil instance
     */
    public WeatherService() {
        this.apiUtil = new ApiUtil();
    }
    
    /**
     * Constructor that accepts an ApiUtil instance (useful for testing)
     * 
     * @param apiUtil The ApiUtil instance to use
     */
    public WeatherService(ApiUtil apiUtil) {
        this.apiUtil = apiUtil;
    }
    
    /**
     * Gets weather data for a specified city
     * 
     * @param city The city name to get weather data for
     * @return WeatherData object containing the processed weather information
     * @throws IOException If an I/O error occurs during the API request
     * @throws ParseException If an error occurs while parsing the JSON response
     */
    public WeatherData getWeatherData(String city) throws IOException, ParseException {
        if (city == null || city.trim().isEmpty()) {
            city = "London"; // Default city if none provided
        }
        
        // Construct the API URL with query parameters
        String apiUrlWithParams = API_URL + "?q=" + city + "&appid=" + API_KEY;
        
        // Send the GET request to the API
        String jsonResponse = apiUtil.sendGetRequest(apiUrlWithParams);
        
        // Parse and process the JSON response
        return processJsonResponse(jsonResponse);
    }
    
    /**
     * Processes the JSON response from the weather API
     * 
     * @param jsonResponse The JSON response from the API
     * @return WeatherData object with processed information
     * @throws ParseException If an error occurs while parsing the JSON
     */
    private WeatherData processJsonResponse(String jsonResponse) throws ParseException {
        WeatherData weatherData = new WeatherData();
        
        try {
            // Parse the JSON string
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
            
            // Extract basic information
            JSONObject main = (JSONObject) jsonObject.get("main");
            JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
            JSONObject weather = (JSONObject) weatherArray.get(0);
            JSONObject clouds = (JSONObject) jsonObject.get("clouds");
            JSONObject sys = (JSONObject) jsonObject.get("sys");
            
            // Set weather conditions
            weatherData.setWeatherCondition((String) weather.get("main"));
            weatherData.setWeatherDescription((String) weather.get("description"));
            
            // Set cloud cover (if available)
            if (clouds != null && clouds.get("all") != null) {
                weatherData.setCloudCover(((Number) clouds.get("all")).doubleValue());
            }
            
            // Set location info
            weatherData.setCityName((String) jsonObject.get("name"));
            weatherData.setCountryCode((String) sys.get("country"));
            
            // Set sunrise and sunset times
            weatherData.setSunriseTime(((Number) sys.get("sunrise")).longValue() * 1000);
            weatherData.setSunsetTime(((Number) sys.get("sunset")).longValue() * 1000);
            weatherData.setCurrentTime(System.currentTimeMillis());
            
            // Determine if sunrise/sunset is viewable based on weather conditions
            boolean isViewable = determineViewability(weatherData);
            weatherData.setViewable(isViewable);
            
            // Set appropriate message
            weatherData.setMessage(createMessage(weatherData));
            
        } catch (Exception e) {
            // If any unexpected error occurs, wrap it in a ParseException
            if (e instanceof ParseException) {
                throw (ParseException) e;
            } else {
                throw new ParseException(ParseException.ERROR_UNEXPECTED_EXCEPTION,
                        "Error processing weather data: " + e.getMessage());
            }
        }
        
        return weatherData;
    }
    
    /**
     * Determines if the sunrise/sunset will be viewable based on weather conditions
     * 
     * @param weatherData The weather data to evaluate
     * @return true if conditions are favorable, false otherwise
     */
    private boolean determineViewability(WeatherData weatherData) {
        String condition = weatherData.getWeatherCondition().toLowerCase();
        String description = weatherData.getWeatherDescription().toLowerCase();
        
        // Consider favorable if condition or description contains "clear"
        boolean isClear = condition.contains("clear") || description.contains("clear");
        
        // Consider unfavorable if high cloud cover
        boolean lowCloudCover = weatherData.getCloudCover() < 50.0;
        
        // Also consider favorable if condition or description mentions "sunny"
        boolean isSunny = description.contains("sunny") || condition.contains("sunny");
        
        return (isClear || isSunny) && lowCloudCover;
    }
    
    /**
     * Creates an appropriate message based on weather conditions and time
     * 
     * @param weatherData The weather data to use for the message
     * @return A message indicating if sunrise/sunset is viewable
     */
    private String createMessage(WeatherData weatherData) {
        // Determine if it's close to sunrise or sunset
        long currentTime = weatherData.getCurrentTime();
        long sunriseTime = weatherData.getSunriseTime();
        long sunsetTime = weatherData.getSunsetTime();
        
        // Format times for display
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        sdf.setTimeZone(TimeZone.getDefault());
        String sunriseFormatted = sdf.format(new Date(sunriseTime));
        String sunsetFormatted = sdf.format(new Date(sunsetTime));
        
        // Determine if we're closer to sunrise or sunset
        boolean closerToSunrise = Math.abs(currentTime - sunriseTime) < Math.abs(currentTime - sunsetTime);
        String timeEvent = closerToSunrise ? "sunrise" : "sunset";
        String timeFormatted = closerToSunrise ? sunriseFormatted : sunsetFormatted;
        
        if (weatherData.isViewable()) {
            return "The " + timeEvent + " at " + timeFormatted + " should be viewable with current weather conditions in " 
                   + weatherData.getCityName() + "! Weather is " + weatherData.getWeatherDescription() + ".";
        } else {
            return "Conditions are not favorable for viewing the " + timeEvent + " at " + timeFormatted + " in "
                   + weatherData.getCityName() + " due to " + weatherData.getWeatherDescription() + ".";
        }
    }
}