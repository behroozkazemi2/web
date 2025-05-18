let createMap = function () {
    myMap = new L.Map('mapCard', {
        key: 'web.YeZhWrrTtiGr4V7b1dhlvMHNyXx8VWXTaXb6oCZS',
        maptype: 'dreamy',
        poi: true,
        traffic: false,
        center: [36.2843298, 59.6086943],
        zoom: 12,
    });

}

// SET MARKER
let editSesetMarker = function (editLat, editLng) {

    // marker qabli pak she;

    myMap.off();
    myMap.remove();
    createMap();

    let marker;
    marker = L.marker([editLat, editLng]);
    marker.addTo(myMap);
    lat = editLat;
    lng = editLng;
    myMap.on('click', function (e) {
        if (marker) {
            myMap.removeLayer(marker);
        }
        lat = e.latlng.lat;
        lng = e.latlng.lng;
        marker = L.marker([lat, lng])
        marker.addTo(myMap)
    })


};

let setMarker = function () {


    // marker qabli pak she;
    myMap.off();
    myMap.remove();
    createMap();
    let marker;

    //center
    marker = L.marker([36.2843298, 59.6086943])
    marker.addTo(myMap);
    myMap.on('click', function (e) {
        if (marker) {
            myMap.removeLayer(marker);
        }
        lat = e.latlng.lat;
        lng = e.latlng.lng;
        marker = L.marker([lat, lng])
        marker.addTo(myMap)
    })
};