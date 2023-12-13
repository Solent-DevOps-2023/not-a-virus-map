// Rendering markers

loader = L.DomUtil.get('loader');
layerGroup = L.layerGroup();
layerGroup.addTo(map);
L.layerJSON({
    //caching: true,				//enable requests caching
    minShift: 300,				//min shift for update data(in meters)
    updateOutBounds: false,		//request new data only if current bounds higher than last bounds
    layerTarget:layerGroup,
    url: '/get',
    propertyItems: '',
    propertyTitle: 'name',
    propertyLoc: ['lat','lng'],
    buildIcon: function(data, title) {
        return new L.Icon({
            iconUrl:'images/marker24.png',
            iconSize: new L.Point(29, 41),
            iconAnchor: new L.Point(18, 41),
            popupAnchor: new L.Point(0, -41)
        });
    },
    buildPopup: function(data, marker) {
        var popupContent = '<h4>' + data.name + '</h4><br/>Category:<b>' + data.category + '</b><hr>Description:<br/>' + data.description;

    // Check if the image data exists
    if (data.image) {
        // Add an image tag with the dynamically generated URL
        popupContent += '<br/><img src="/image/' + data.id + '" alt="Map Point Image" style="max-width: 100px; max-height: 100px;"/>';
    }

    return popupContent;
}
})
    .on('dataloading',function(e) {
        loader.style.display = 'block';
    })
    .on('dataloaded',function(e) {
        loader.style.display = 'none';
    })
    .addTo(map);
