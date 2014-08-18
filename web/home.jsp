<%-- 
    Document   : home.jsp
    Created on : Aug 14, 2014, 5:54:02 PM
    Author     : paulozeferino
--%>

<%@page import="model.Local"%>
<%@page import="dao.LocalDao"%>
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
                var nome_locais = [];
            <%for (CoordenadaLocal obj_coordenda : coordenadas) {%>
                // var myLatlng = new google.maps.LatLng('','');
                //coordenadas.push(myLatlng);
                latitudes.push('<%=obj_coordenda.getLatitude()%>');
                longitudes.push('<%=obj_coordenda.getLongitude()%>');
                <%LocalDao objeto_local_dao = new LocalDao();%>
                <%Local obj_local = objeto_local_dao.getLocal(obj_coordenda.getIdLocal()); %>
                nome_locais.push('<%=obj_local.getDescricao()%>');
               
            <%}%>



   var mapOptions = {
    center: new google.maps.LatLng(latitudes[0], longitudes[0]),
    zoom: 8
  };
 
  var map = new google.maps.Map(document.getElementById('map-canvas'),
    mapOptions);
    
    for (i = 0; i < latitudes.length; i++) {
                    var position = new google.maps.LatLng(latitudes[i], longitudes[i]);
                    var nome_local = nome_locais[i];
                    marker = new google.maps.Marker({
                        position: position,
                        map: map,
                        title: nome_local
                    });
                
    
     google.maps.event.addListener(marker, 'click', function () {
         
                infowindow.setContent(this.title);
                infowindow.open(map, this);
            });
    
    }

  
}

google.maps.event.addDomListener(window, 'load', initialize);

    </script>
        
        <div id="map-canvas" class="container">
        </div>
        

    </body>
</html>
