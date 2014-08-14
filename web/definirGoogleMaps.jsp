<%@page import="model.Local"%>
<!DOCTYPE html>
<html>
<head>
    <style type="text/css">
        #map_canvas {height:600px;width:800px}
    </style>
    <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<%@include  file="menu.jsp" %>
    <script type="text/javascript">
        var map;
        var markersArray = [];

        function initMap()
        {
            var latlng = new google.maps.LatLng(41, 29);
            var myOptions = {
                zoom: 10,
                center: latlng,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

            // add a click event handler to the map object
            google.maps.event.addListener(map, "click", function(event)
            {
                // place a marker
                placeMarker(event.latLng);

                // display the lat/lng in your form's lat/lng fields
                document.getElementById("latFld").value = event.latLng.lat();
                document.getElementById("lngFld").value = event.latLng.lng();
            });
        }
        function placeMarker(location) {
            // first remove all markers if there are any
            deleteOverlays();

            var marker = new google.maps.Marker({
                position: location, 
                map: map
            });

            // add marker in markers array
            markersArray.push(marker);

            //map.setCenter(location);
        }

        // Deletes all markers in the array by removing references to them
        function deleteOverlays() {
            if (markersArray) {
                for (i in markersArray) {
                    markersArray[i].setMap(null);
                }
            markersArray.length = 0;
            }
        }
    </script>
</head>

<body onload="initMap()">
    <% Local objeto_local = (Local) request.getAttribute("local");%>
    <div id="map_canvas" style="float: left"></div>
    <div style="float:left;padding:10px">
        <p><h3><b>Local:</b> <%=objeto_local.getDescricao()%></h3></p>
        <p>Insira aqui a mensagem para instruir o usuário</p>
        <form action="#" id="form_coord" method="POST">
            Latitude <br/>
            <input type="hidden" id="idLocal" value="<%=objeto_local.getIdString()%>" />
            <input type="text" id="latFld">
            <br />
            Longitude<br />
            <input type="text" id="lngFld">
            <br />
            <a href="#" class="btn btn-inverse" id="form_coord">Salvar</a>
        </form>
    </div>
    
        <script type="text/javascript">
               $("#form_coord").click(function(){
                          $.post('createCoordenadasGoogleMaps',{idLocal:$("#idLocal").val(), 
                                                latFld:$("#latFld").val(),
                                                lngFld: $("#lngFld").val()
                },function(responseText) {
                        eval(responseText);
                    });
                    
                });
            
        </script>
        
</body>
</html>