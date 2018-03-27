<%-- 
    Document   : index
    Created on : 12/03/2018, 09:35:52 AM
    Author     : josera
--%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Tienda Online</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css">
<!-- 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
-->
</head>
<body>
    <jsp:include page="_header.jsp" />

    <div class="jumbotron" style="margin-top: -15px;">
        <div class="container">
            <h1 class="display-3">Bienvenido</h1>
            <p>
                Queremos hacer que tu vida sea más cómoda, práctica y fácil, ahorra tiempo, dinero y ten 
                la mejor experiencia de compra con nuestro servicio de envío puerta a puerta. 
                Queremos que tengas más tiempo para ti y tu familia, una mejor calidad de vida, es por eso que 
                te ofrecemos la posibilidad de comprar de forma simple, rápida y amena
            </p>
            <p><a class="btn btn-primary btn-lg" href="#" role="button">Leer mas &raquo;</a></p>
        </div>
    </div>

    <div class="container"> 
        <div class="row">
          <div class="col-md-4">
            <h2>Entrega rapida</h2>
            <p>
                Olvídate de cargar con bolsas pesadas, las colas o aparcar. ¡Que lo hagan otros! 
                Entrega programada con AL MENOS 8 HRS.
            </p>
            <p><a class="btn btn-info" href="#" role="button">Ver detalles &raquo;</a></p>
          </div>
          <div class="col-md-4">
            <h2>Bueno para tu salud</h2>
            <p>
                Por que pensamos en su salud y la de su familia, llevamos los mejores alimentos a su mesa.
                Te valoramos y te protegemos.
            </p>
            <p><a class="btn btn-info" href="#" role="button">Ver detalles &raquo;</a></p>
          </div>
          <div class="col-md-4">
            <h2>Alimentos 100% frescos</h2>
            <p>
                Seleccionamos los mejores alimentos para usted y su familia, muchas veces del productor al consumidor.
            </p>
            <p><a class="btn btn-info" href="#" role="button">Ver detalles &raquo;</a></p>
          </div>
        </div>
        <hr>
    </div>

    <jsp:include page="_footer.jsp" />
    
</body>
</html>
