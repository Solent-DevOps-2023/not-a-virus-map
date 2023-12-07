// Map initialize
var map = L.map('map', {
    minZoom: 2,
    zoom: 6
});

// Get user's current location using HTML5 Geolocation API
if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function (position) {
        var userLatitude = position.coords.latitude;
        var userLongitude = position.coords.longitude;

        // Set map center to user's location
        map.setView([userLatitude, userLongitude], 6);

        // Add a marker at the user's location
        L.marker([userLatitude, userLongitude]).addTo(map)
            .bindPopup('Your Location\nLat: ' + userLatitude + '\nLong: ' + userLongitude).openPopup();
    }, function (error) {
        console.error('Error getting user location:', error.message);
    });
} else {
    console.error('Geolocation is not supported by this browser.');
}

// Use HTTPS for tile layer to avoid browser warnings
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; <a href="https://www.github.com/imaginalis">@imaginalis</a>',
    subdomains: ['a', 'b', 'c']
}).addTo(map);

var controlLoader = L.control.loader().addTo(map);
