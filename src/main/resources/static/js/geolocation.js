// geolocations.js

function updateUserLocation(map) {
    // Check if geolocation is supported
    if ('geolocation' in navigator) {
        navigator.geolocation.getCurrentPosition(
            function (position) {
                // On success, update the map with the user's location
                var userLatitude = position.coords.latitude;
                var userLongitude = position.coords.longitude;

                // Remove existing markers (if any)
                map.eachLayer(function (layer) {
                    if (layer instanceof L.Marker) {
                        map.removeLayer(layer);
                    }
                });

                // Add a new marker at the user's location
                var userMarker = L.marker([userLatitude, userLongitude]).addTo(map);
                userMarker.bindPopup('Your Location').openPopup();

                // Optionally, you can also center the map on the user's location
                map.setView([userLatitude, userLongitude], 13);
            },
            function (error) {
                console.error('Error getting user location:', error.message);
            }
        );
    } else {
        console.error('Geolocation is not supported.');
    }
}
