document.addEventListener('DOMContentLoaded', () => {
    // DOM Elements
    const cityInput = document.getElementById('cityInput');
    const daysInput = document.getElementById('daysInput');
    const searchBtn = document.getElementById('searchBtn');
    const locationEl = document.getElementById('location');
    const temperatureEl = document.getElementById('temperature');
    const conditionEl = document.getElementById('condition');
    const weatherIcon = document.getElementById('weatherIcon');
    const forecastDaysEl = document.getElementById('forecastDays');
    const forecastContainer = document.getElementById('forecast');

    // Weather Icons Mapping
    const weatherIcons = {
        'clear': 'wi-day-sunny',
        'clouds': 'wi-cloudy',
        'rain': 'wi-rain',
        'snow': 'wi-snow',
        'thunderstorm': 'wi-thunderstorm',
        'drizzle': 'wi-sprinkle',
        'mist': 'wi-fog',
        'smoke': 'wi-smoke',
        'haze': 'wi-day-haze',
        'dust': 'wi-dust',
        'fog': 'wi-fog',
        'sand': 'wi-sandstorm',
        'ash': 'wi-volcano',
        'squall': 'wi-strong-wind',
        'tornado': 'wi-tornado'
    };

    // Initialize with default city
    fetchWeatherData('Guna', 5);

    // Event Listeners
    searchBtn.addEventListener('click', handleSearch);
    cityInput.addEventListener('keypress', (e) => e.key === 'Enter' && handleSearch());
    daysInput.addEventListener('change', () => {
        const city = cityInput.value.trim();
        if (city) handleSearch();
    });

    function handleSearch() {
        const city = cityInput.value.trim();
        const days = parseInt(daysInput.value) || 5;
        if (city) {
            fetchWeatherData(city, days);
        }
    }

    async function fetchWeatherData(city, days = 5) {
        try {
            // Validate days
            days = Math.min(Math.max(1, days), 14);
            
            // Show loading state
            forecastDaysEl.innerHTML = '<div class="loading">Loading forecast...</div>';
            forecastContainer.querySelector('h3').textContent = `${days}-Day Forecast`;
            
            // Fetch data from API
            const response = await fetch(`http://localhost:8080/weather/forecast?city=${encodeURIComponent(city)}&days=${days}`);
            
            if (!response.ok) {
                throw new Error('City not found or server error');
            }
            
            const data = await response.json();
            
            // Update UI
            updateCurrentWeather(data.weather);
            updateForecast(data.dayTemps);
            
        } catch (error) {
            console.error('Error:', error);
            forecastDaysEl.innerHTML = `
                <div class="error">
                    Failed to fetch weather data. Please check:<br>
                    1. The backend server is running on http://localhost:8080<br>
                    2. The city name is spelled correctly<br>
                    3. You have an internet connection<br><br>
                    Error: ${error.message}
                </div>`;
        }
    }

    function updateCurrentWeather(weather) {
        locationEl.textContent = `${weather.city}, ${weather.region}, ${weather.country}`;
        temperatureEl.textContent = `${weather.temp}°C`;
        conditionEl.textContent = weather.condition;
        
        // Update weather icon based on condition
        const condition = weather.condition.toLowerCase();
        let iconClass = 'wi-day-sunny'; // Default icon
        
        // Find matching icon
        for (const [key, value] of Object.entries(weatherIcons)) {
            if (condition.includes(key)) {
                iconClass = value;
                break;
            }
        }
        
        weatherIcon.className = `wi ${iconClass}`;
    }

    function updateForecast(forecastDays) {
        forecastDaysEl.innerHTML = '';
        
        forecastDays.forEach(day => {
            const date = new Date(day.date);
            const dayName = date.toLocaleDateString('en-US', { weekday: 'short' });
            const formattedDate = date.toLocaleDateString('en-US', { month: 'short', day: 'numeric' });
            
            // Get appropriate icon for forecast
            const condition = day.condition ? day.condition.toLowerCase() : 'clear';
            let iconClass = 'wi-day-sunny';
            for (const [key, value] of Object.entries(weatherIcons)) {
                if (condition.includes(key)) {
                    iconClass = value;
                    break;
                }
            }
            
            const forecastDayEl = document.createElement('div');
            forecastDayEl.className = 'forecast-day';
            forecastDayEl.innerHTML = `
                <div class="date">${dayName}<br>${formattedDate}</div>
                <div class="weather-icon">
                    <i class="wi ${iconClass}"></i>
                </div>
                <div class="temps">
                    <span class="max-temp">H: ${day.max_temp}°C</span>
                    <span class="min-temp">L: ${day.min_temp}°C</span>
                </div>
            `;
            
            forecastDaysEl.appendChild(forecastDayEl);
        });
    }
});