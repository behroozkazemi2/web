var pointList = [];
var markers = new ol.layer.Vector({
    source: new ol.source.Vector(),
    style: new ol.style.Style({
        image: new ol.style.Icon({
            anchorXUnits: 'fraction',
            anchorYUnits: 'fraction',
            scale: 0.1,
            src: '/assets/images/data-marker.png'
        })
    })
});
var map;
var coordsMultiPolygon = [];
var coordsSinglePolygon = [];
var coordsCircle = [];
var coordsPoint = [];
var coordsLineString = [];
var circlePoint = [];
var polygonPoints = [];
var lineStringPoint = [];
var vectorSource = new ol.source.Vector({});
var vectorLayer = new ol.layer.Vector({
    source: vectorSource
});
var view = new ol.View({
    center: ol.proj.fromLonLat([59.5974796, 36.2999664]),
    zoom: 11,
    minZoom: autocad ? 11 : 0,
    maxZoom: autocad ? 11 : 21,
});
var raster = new ol.layer.Tile({
    source: new ol.source.OSM()
});
var vector = new ol.layer.Vector({
    source: vectorSource,
    style: new ol.style.Style({
        fill: new ol.style.Fill({
            color: 'rgba(255,255,255,0.7)',
        }),
        stroke: new ol.style.Stroke({
            color: '#3399CC',
            width: 3,
        }),
        image: new ol.style.Circle({
            radius: 7,
            fill: new ol.style.Fill({
                color: '#3399CC'
            })
        })
    })
});

var highlightStyle = new ol.style.Style({
    fill: new ol.style.Fill({
        color: 'rgba(255,255,255,0.7)',
    }),
    stroke: new ol.style.Stroke({
        color: '#3399CC',
        width: 3,
    }),
});
var autocad;
var selectedProviderId = -1;
var oldSelectedArea;

let vecs = [];

var addressMarkerLat;
var addressMarkerLon;


let mapSearchedLocationAddressFlag = false
var initAddressMap = function (target, lat, lon) {
    map = new ol.Map({
        target: target,
        key: 'web.2354814b91684ec8974cab1d1c48ff78',
        maptype: 'neshan',
        projection: 'EPSG:4326',
        view: view,
        layers: [
            raster, vector, vectorLayer
        ],
    });

    var modify = new ol.interaction.Modify({source: vectorSource});
    map.addInteraction(modify);
    var markers = new ol.layer.Vector({
        source: new ol.source.Vector(),
        style: new ol.style.Style({
            image: new ol.style.Icon({
                anchor: [0.5, 1],
                zIndex: 1000,
                // the scale factor
                scale: 1,
                src: '/assets/images/icons/9.svg',
            })
        })
    });

    map.addLayer(markers);
    markers.setZIndex(1001);
    if (lat != '' && lon != '') {
        var marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat([lon, lat])));
        markers.getSource().addFeature(marker);

    }
    map.on('click', function (evt) {

        var lonlat = ol.proj.transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326');

        var lon = lonlat[0];
        var lat = lonlat[1];
        addressMarkerLat = lonlat[1];
        addressMarkerLon = lonlat[0];
        var features = markers.getSource().getFeatures();
        features.forEach((feature) => {
            markers.getSource().removeFeature(feature);
        });


        var marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat([lon, lat])));

        /*  checck marker is inside poligun
        let flagOutSideThePol = true

        vecs.forEach(item => {
            if (item.getSource().getFeaturesAtCoordinate(evt.coordinate).length == 1 ){
               flagOutSideThePol = false;
            }
        });
        if (flagOutSideThePol){
            oldSelectedArea.setStyle(undefined);
            selectedProviderId = null;
        }
        */

        markers.getSource().addFeature(marker);

    });

    // map.on('singleclick', function (e) {
    //     map.forEachFeatureAtPixel(e.pixel, function (f) {
    //
    //         if (selectedProviderId != f.values_.geometry.values_.id && selectedProviderId != -1) {
    //             oldSelectedArea.setStyle(undefined);
    //         }
    //         f.setStyle(highlightStyle);
    //
    //         //  selIndex = selectedProviderId;
    //         // console.log(selectedProviderId != f.values_.geometry.values_.id);
    //         // if (selIndex < 0) {
    //         //     console.log("Where")
    //         //     selectedProviderId.push(f.values_.geometry.values_.id);
    //         //     f.setStyle(highlightStyle);
    //         // } else {
    //         //     selectedProviderId = 0;
    //         //     f.setStyle(undefined);
    //         // }
    //         selectedProviderId = f.values_.geometry.values_;
    //         oldSelectedArea = f;
    //
    //         $('#providerId').val(f.values_.geometry.values_);
    //         // console.log(f.values_.geometry.values_ , selectedProviderId);
    //     });
    // })

    //
    setTimeout(() => {
        mapMoveEvents();
        // initAddressMap('kt_location_mobile')
    }, 10);

};

let mapMoveEvents = function () {

    // $('#searchLocation').on('select2:select', function (selection) {
    //     mapSearchedLocationAddressFlag = true;
    //     map.getView().setCenter(ol.proj.transform([selection.params.data.lon, selection.params.data.lat], 'EPSG:4326', 'EPSG:3857'));
    //     map.getView().setZoom(16);
    // });

    map.on('moveend', function () {
        // $("#searchLocation").prop("disabled", false);
        // $('#searchLocation-loading-icon').removeClass('kt-spinner kt-spinner--right kt-spinner--md kt-spinner--danger');
        // $('.location-select2-div').find('.select2-selection__arrow').removeClass('hidden-arrow')
    });

    map.on('movestart', function () {
        // $("#searchLocation").prop("disabled", true);
        // $('#searchLocation-loading-icon').addClass('kt-spinner kt-spinner--right kt-spinner--md kt-spinner--danger');
        // $('.location-select2-div').find('.select2-selection__arrow').addClass('d-none')
    })


    map.on('click', function (evt){
        var coords = ol.proj.toLonLat(evt.coordinate);
        // let mapCenter = {
        //     coordinate: map.getView().getCenter()
        // };
        // let coord = ol.proj.transform(mapCenter.coordinate, 'EPSG:3857', 'EPSG:4326');
        getLocationAddressAndSetInSelect2(coords);
    })
}



var drawPoint = function (points) {
    pointList = points;
    points.forEach(p => {
        console.log("add p ", p);
        var coordinates = [p.lng, p.lat];
        var marker = new ol.Feature(new ol.geom.Point(ol.proj.fromLonLat(coordinates)));
        markers.getSource().addFeature(marker);

    });

    map.addLayer(markers);

};

var drawPolygon = function (items) {

    items.forEach(points => {
        var polygonPoints = [];
        points.locations.forEach(p => {
            var coordinates = [p.lng, p.lat];
            polygonPoints.push(coordinates)
        });

        var geometry = new ol.geom.Polygon([polygonPoints]);

        geometry.set('id', points.id)

        geometry.transform('EPSG:4326', 'EPSG:3857');

        var vectorLayer = new ol.layer.Vector({
            source: new ol.source.Vector({
                features: [new ol.Feature({
                    geometry: geometry
                })]
            })
        });
        vecs.push(vectorLayer)
        map.addLayer(vectorLayer);
        markers.setZIndex(900);
    });
};
let getLocationAddressAndSetInSelect2 = function (coord) {
    $.ajax({
        delay: 300,
        url: 'https://api.neshan.org/v2/reverse?lat=' + coord[1] + '&lng=' + coord[0],
        headers: {
            'Api-Key': 'service.21ae843b25c0416db9c3eaf70c394ac5',
        },
        beforeSend: function () {
            $('#addMarkerToCenter').attr('disabled', true);
            $("#searchLocation").prop("disabled", true);
            $('#searchLocation-loading-icon').addClass('kt-spinner kt-spinner--right kt-spinner--md kt-spinner--danger');
            $('.location-select2-div').find('.select2-selection__arrow').addClass('d-none')

        },
        success: function (res) {
            $($(document).find('#address-field')).empty();
            if (res.status == 'OK'){
                $($(document).find('#address-field')).text(res.formatted_address)
                $($(document).find('#address-field')).val(res.formatted_address)
                $($(document).find('#address-field')).attr('disabled',false)
            }

        },
        error: function () {
            swal.fire({
                "title": "خطا",
                "text": "خطا در پیدا کردن موقعیت",
                "type": "error",
                "confirmButtonClass": "btn btn-warning",
                "confirmButtonText": "تایید"
            });
        },
        complete: function (res) {
            $('.location-select2-div').find('.select2-selection__arrow').removeClass('d-none')
            $('#searchLocation-loading-icon').removeClass('kt-spinner kt-spinner--right kt-spinner--md kt-spinner--danger');
            $('#addMarkerToCenter').attr('disabled', false);
            $("#searchLocation").prop("disabled", false);
        },
    });

}


var zoomMap = function (points, z) {

    var p = [points[0].lng, points[0].lat];
    var parts = 2;
    var called = false;

    var london = ol.proj.fromLonLat(p);

    function callback(compvare) {
        --parts;
        if (called) {
            return;
        }
        if (parts === 0 || !compvare) {
            called = true;
            done(compvare);
        }
    }

    view.animate(
        {
            zoom: 11,
            duration: 1,
        },
        {
            zoom: z,
            duration: 1,
        },
        callback
    );

    view.animate({
        center: london,
        duration: 1,
    });


};
