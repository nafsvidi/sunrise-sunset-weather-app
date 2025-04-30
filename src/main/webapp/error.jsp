<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error - Sunrise/Sunset App</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Oops! Something went wrong</h1>
        </header>
        
        <main>
            <section class="error-section">
                <div class="notification error">
                    <p>${errorMessage != null ? errorMessage : "An unknown error occurred. Please try again later."}</p>
                </div>
                
                <div class="back-section">
                    <a href="index.jsp" class="btn">Back to Home</a>
                </div>
            </section>
        </main>
        
        <footer>
            <p>&copy; 2025 Sunrise/Sunset Weather App</p>
        </footer>
    </div>
</body>
</html>