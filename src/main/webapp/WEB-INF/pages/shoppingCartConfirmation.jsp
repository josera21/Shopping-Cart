<%-- 
    Document   : shoppingCartInformation
    Created on : 12/03/2018, 09:42:23 AM
    Author     : josera
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Resumen</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css"> 
</head>
<body class="bg-grey" >
  <jsp:include page="_header.jsp" />
 
  <fmt:setLocale value="en_US" scope="session"/>
  <div class="container">
  
    <h1 class="page-title">Confirmacion</h1>
 
    <div class="card">
      <div class="card-header">
        Informacion del cliente
      </div>
      <div class="card-body">
        <h3 class="card-title">${myCart.clienteInfo.name}</h3>
        <p class="card-text">
          Correo: ${myCart.clienteInfo.email} <br>
          Telefono: ${myCart.clienteInfo.phone} <br>
          Direccion:  ${myCart.clienteInfo.address} <br>

          <h3>Resumen de compra</h3> <br>
          Cantidad: ${myCart.quantityTotal} <br>
          Total:
            <span class="total">
                <fmt:formatNumber value="${myCart.amountTotal}" type="currency"/>
            </span>
        </p>
        <form method="POST" 
        action="${pageContext.request.contextPath}/shoppingCartConfirmation">
          <!-- Editar Carrito -->
          <a class="btn btn-outline-warning"
              href="${pageContext.request.contextPath}/shoppingCart">Editar Carro
          </a>
          <!-- Edit Customer Info -->
          <a class="btn btn-outline-info"
              href="${pageContext.request.contextPath}/shoppingCartCustomer">Editar
              cliente
          </a>
          <!-- Guardar pedido -->
          <input type="submit" value="Finalizar" class="btn btn-outline-success">
        </form>
      </div>
    </div>
 
    <div class="container">
    <c:choose>
      <c:when test="${myCart.carLines.size() > 1}" > <!-- Si hay mas de un producto -->
        <div class="card-deck top-space">
            <c:forEach items="${myCart.carLines}" var="cartLineInfo">
              <div class="card" style="width: 18rem;">
                <img class="card-img-top" style="height: 12em;"
                     src="${pageContext.request.contextPath}/productImage?code=${cartLineInfo.productoInfo.code}" alt="Card image cap">
                <div class="card-body">
                  <h5 class="card-title">${cartLineInfo.productoInfo.name}</h5>
                  <p class="card-text">
                    Precio: 
                    <span class="price">
                      <fmt:formatNumber value="${cartLineInfo.productoInfo.price}" type="currency"/>
                    </span> <br>
                    Cantidad: ${cartLineInfo.quantity} <br>
                    Subtotal: 
                    <span class="subtotal">
                      <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/>
                    </span>
                  </p>
                </div>
              </div>
            </c:forEach>
          </div>
        </c:when>
        
        <c:otherwise >
          <c:forEach items="${myCart.carLines}" var="cartLineInfo">
            <div class="card" style="width: 18rem;">
              <img class="card-img-top" src="${pageContext.request.contextPath}/productImage?code=${cartLineInfo.productoInfo.code}" alt="Card image cap">
              <div class="card-body">
                <h5 class="card-title">${cartLineInfo.productoInfo.name}</h5>
                <p class="card-text">
                  Precio: 
                  <span class="price">
                    <fmt:formatNumber value="${cartLineInfo.productoInfo.price}" type="currency"/>
                  </span> <br>
                  Cantidad: ${cartLineInfo.quantity} <br>
                  Subtotal: 
                  <span class="subtotal">
                    <fmt:formatNumber value="${cartLineInfo.amount}" type="currency"/>
                  </span>
                </p>
              </div>
            </div>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </div>
  </div>
  <jsp:include page="_footer.jsp" />
 
</body>
</html>
