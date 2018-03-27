<%-- 
    Document   : shoppingCartFinalize
    Created on : 12/03/2018, 09:43:03 AM
    Author     : josera
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Compra</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css"> 

</head>
<body>
   <jsp:include page="_header.jsp" />
   <div class="container">
     <div class="col-md-6 offset-3">
       <h1 class="text-success">Gracias por comprar!</h1>
        <p class="h3">
          Tu numero de orden es: <span class="text-primary">${lastOrderedCart.orderNum}</span>
        </p>
     </div>
   </div>
   
   <jsp:include page="_footer.jsp" />
 
</body>
</html>
