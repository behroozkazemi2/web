/**
 * Created by thunderbolt on 10/31/19.
 */

let map;

let vectorSource = new ol.source.Vector({});

let vectorLayer = new ol.layer.Vector({
    source: vectorSource
});

let initMap = function (target) {
    map = new ol.Map({
        target: target,
        key: 'web.YeZhWrrTtiGr4V7b1dhlvMHNyXx8VWXTaXb6oCZS',
        maptype: 'neshan',
        view: new ol.View({
            center: ol.proj.fromLonLat([59.449611, 36.356213]),
            zoom: 14
        }),
        layers: [
            vectorLayer
        ]
    });
    // selectOnMap();
};