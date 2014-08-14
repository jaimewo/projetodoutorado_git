<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <button data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar" type="button">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="#" class="brand">JCarbon</a>
                    <div class="nav-collapse collapse">
                        <ul class="nav">
                            <li class="active"><a href="#">Home</a></li>
                            <li class="dropdown">
                                <a data-toggle="dropdown" class="dropdown-toggle" href="#">Locais <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="novoLocal">Incluir</a></li>                                    
                                    <li><a href="listarLocais">Manter</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a data-toggle="dropdown" class="dropdown-toggle" href="#">Trabalhos Científicos <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="novoTrabalhoCientifico">Incluir</a></li>                                    
                                    <li><a href="listarTrabalhosCientificos">Manter</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a data-toggle="dropdown" class="dropdown-toggle" href="#">Cadastros <b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li class="divider"></li>
                                    <li class="nav-header">Cadastros para os Locais</li>
                                    <li><a href="listarAutores">Autores</a></li>
                                    <li><a href="listarBiomas">Biomas</a></li>
                                    <li><a href="listarFormacoes">Formações</a></li>
                                    <li><a href="listarEspacamentos">Espaçamentos</a></li>
                                    <li class="divider"></li>
                                    <li class="nav-header">Cadastros para os Trabalhos Científicos</li>
                                    <li><a href="listarTiposDisponibilidade">Tipos de Disponibilidade do Trabalho Científico</a></li>
                                    <li><a href="listarMetodosQuantificacaoBiomassa">Metodos de Quantificação de Biomassa</a></li>
                                    <li><a href="listarMetodosQuantificacaoCarbono">Metodos de Quantificação de Carbono</a></li>
                                    <li><a href="listarVariaveis">Variáveis</a></li>
                                    <li><a href="listarAutoresModelo">Autores de Modelos</a></li>
                                    <li><a href="listarModelos">Modelos</a></li>
                                    <li><a href="listarEquacao">Equações</a></li>
                                </ul>
                            </li>
                            <li class="dropdown">
                                <a data-toggle="dropdown" class="dropdown-toggle" href="#">Menu para Testes<b class="caret"></b></a>
                                <ul class="dropdown-menu">
                                    <li><a href="listarDetalhesCalculoParcelas">Listar Detalhes do Cálculo (Parcelas)</a></li>                                    
                                    <li><a href="listarDetalhesCalculoArvores">Listar Detalhes do Cálculo (Árvores)</a></li>                                                                        
                                </ul>
                            </li>                       
                        </ul>
                        <form class="navbar-form pull-right">
                            <input type="text" placeholder="Email" class="span2">
                            <input type="password" placeholder="Password" class="span2">
                            <button class="btn" type="submit">Sign in</button>
                        </form>
                    </div><!--/.nav-collapse -->
                </div>
            </div>
        </div>
        <script src="js/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <br />
        <br />
        
        
        <style>
      html, body, #map-canvas {
        height: 100%;
        width:100%;
        margin: 0px;
        padding: 0px
      }
    </style>
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
        
