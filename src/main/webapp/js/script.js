// Wait for DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    
    // Get the form element
    const weatherForm = document.querySelector('form');
    
    // Add form validation
    if (weatherForm) {
        weatherForm.addEventListener('submit', function(event) {
            const cityInput = document.getElementById('city');
            
            // Simple validation: check if city name is not empty
            if (!cityInput.value.trim()) {
                event.preventDefault();
                alert('Please enter a city name');
                cityInput.focus();
            }
        });
    }
    
    // Add auto-refresh functionality (optional)
    // This will refresh the weather data every 30 minutes if the user stays on the result page
    if (document.querySelector('.result-section')) {
        setTimeout(function() {
            location.reload();
        }, 30 * 60 * 1000); // 30 minutes
    }
    
    // Add animation to notifications
    const notifications = document.querySelectorAll('.notification');
    notifications.forEach(function(notification) {
        notification.style.opacity = '0';
        setTimeout(function() {
            notification.style.transition = 'opacity 0.5s ease-in-out';
            notification.style.opacity = '1';
        }, 100);
    });
});