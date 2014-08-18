<%-- 
    Document   : home.jsp
    Created on : Aug 14, 2014, 5:54:02 PM
    Author     : paulozeferino
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.CoordenadaLocal"%>
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
         <% ArrayList<CoordenadaLocal> coordenadas = (ArrayList<CoordenadaLocal>) request.getAttribute("coordenadas");%>
function initialize() {
  
     var latitudes = [];
                var longitudes = [];
            <%for (CoordenadaLocal obj_coordenda : coordenadas) {%>
                // var myLatlng = new google.maps.LatLng('','');
                //coordenadas.push(myLatlng);
                latitudes.push('<%=obj_coordenda.getLatitude()%>');
                longitudes.push('<%=obj_coordenda.getLongitude()%>');
            <%}%>



   var mapOptions = {
    center: new google.maps.LatLng(latitudes[0], longitudes[0]),
    zoom: 8
  };
 
  var map = new google.maps.Map(document.getElementById('map-canvas'),
    mapOptions);
    
    for (i = 0; i < latitudes.length; i++) {
                    var position = new google.maps.LatLng(latitudes[i], longitudes[i]);
                    
                    marker = new google.maps.Marker({
                        position: position,
                        map: map,
                        title: 'Local'
                    });
                }

  
}

google.maps.event.addDomListener(window, 'load', initialize);

    </script>
        
        <div id="map-canvas" class="container">
        </div>
        

    </body>
</html>
