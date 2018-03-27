<%-- 
    Document   : shoppingCart
    Created on : 12/03/2018, 09:42:03 AM
    Author     : josera
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Carro de compras</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css">
<script src="resources/static/js/angular.min.js" ></script>

</head>
<body class="bg-grey">
   <jsp:include page="_header.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
   <div class="container">
       
   
   <h1>Mi carro de compras</h1>
 
   <c:if test="${empty cartForm or empty cartForm.carLines}">
       <div class="col-md-6 offset-3 top-space" >
           <h2 class="text-warning" >Debes agregar productos al carrito</h2>
       </div>
        <div class="col-md-6 offset-5 top-space" >
            <a class="btn btn-primary" style="align-content: center;" href="${pageContext.request.contextPath}/categoryList">
             Ir de compras!
            </a>
        </div>
   </c:if>
 
   <c:if test="${not empty cartForm and not empty cartForm.carLines   }">
       <form:form method="POST" modelAttribute="cartForm"
           action="${pageContext.request.contextPath}/shoppingCart">
           
           <div class="card-group">    
           <c:forEach items="${cartForm.carLines}" var="cartLineInfo"
               varStatus="varStatus">
               <div class="col-sm-4">
                <div class="card" style="width: 16rem;">
                    <img class="card-img-top" style="height: 12em;"
                         src="${pageContext.request.contextPath}/productImage?code=${cartLineInfo.productoInfo.code}" 
                         alt="Card image cap">
                    <div class="card-body">
                        <h5 class="card-title">${cartLineInfo.productoInfo.name} 
                            <input type="hidden" name="code" value="${cartLineInfo.productoInfo.code}" />
                        </h5>
                        <p class="card-text">
                            Codigo: ${cartLineInfo.productoInfo.code} <br>
                            Precio: <span class="price">
                                <fmt:formatNumber value="${cartLineInfo.productoInfo.price}" type="currency"/>
                            </span> <br>
                            Cantidad: ${cartLineInfo.quantity} <br>
                            Subtotal: <span class="subtotal">
                               <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/>
                            </span>
                        </p>
                        <a href="${pageContext.request.contextPath}/shoppingCartRemoveProduct?code=${cartLineInfo.productoInfo.code}" 
                           class="btn btn-danger">Retirar</a>
                    </div>
                </div>
               </div> 
           </c:forEach>
           </div>
           
           <div class="top-space col-md-6 offset-4" style="clear: both">
            <p class="h5" style="display: inline;">
                 <a class="navi-item btn btn-primary"
                     href="${pageContext.request.contextPath}/shoppingCartCustomer">
                     Hacer pedido
                 </a>    
            </p>
            <p class="h5" style="display: inline;" >
                <a class="navi-item" href="${pageContext.request.contextPath}/productList">
                    Seguir comprando
                </a>
            </p>
           </div>
       </form:form>
 
 
   </c:if>
   </div>
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>
