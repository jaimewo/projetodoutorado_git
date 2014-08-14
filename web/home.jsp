<%-- 
    Document   : home.jsp
    Created on : Aug 14, 2014, 5:54:02 PM
    Author     : paulozeferino
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JCarbon</title>
     <style>
      html, body, #map-canvas {
        height: 100%;
        width:100%;
        margin: 0px;
        padding: 0px
      }
    </style>
    </head>
    <body>
        <%@include  file="menu.jsp" %>
         <script src="https://maps.googleapis.com/maps/api/js?v=3.exp"></script>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&libraries=drawing"></script>
    <script>
function initialize() {
  var mapOptions = {
    center: new google.maps.LatLng(-34.397, 150.644),
    zoom: 8
  };

  var map = new google.maps.Map(document.getElementById('map-canvas'),
    mapOptions);

  var drawingManager = new google.maps.drawing.DrawingManager({
    drawingMode: google.maps.drawing.OverlayType.MARKER,
    drawingControl: true,
    drawingControlOptions: {
      position: google.maps.ControlPosition.RIGHT_CENTER,
      drawingModes: [
        google.maps.drawing.OverlayType.MARKER,
        google.maps.drawing.OverlayType.CIRCLE,
        google.maps.drawing.OverlayType.POLYGON,
        google.maps.drawing.OverlayType.POLYLINE,
        google.maps.drawing.OverlayType.RECTANGLE
      ]
    },
    markerOptions: {
      icon: 'images/beachflag.png'
    },
    circleOptions: {
      fillColor: '#ffff00',
      fillOpacity: 1,
      strokeWeight: 5,
      clickable: false,
      editable: true,
      zIndex: 1
    }
  });
  drawingManager.setMap(map);
}

google.maps.event.addDomListener(window, 'load', initialize);

    </script>
        
        <div id="map-canvas" class="container">
        </div>
        

    </body>
</html>
