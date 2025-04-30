<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sunrise/Sunset Weather App</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Sunrise/Sunset Weather App</h1>
            <p>Find out if you can view the sunrise or sunset based on current weather conditions</p>
        </header>
        
        <main>
            <section class="search-section">
                <form action="weather" method="get">
                    <div class="form-group">
                        <label for="city">Enter City Name:</label>
                        <input type="text" id="city" name="city" placeholder="e.g., London" required>
                    </div>
                    <button type="submit" class="btn">Check Weather</button>
                </form>
            </section>
        </main>
        
        <footer>
            <p>&copy; 2025 Sunrise/Sunset Weather App</p>
        </footer>
    </div>
    
    <script src="js/script.js"></script>
</body>
</html>