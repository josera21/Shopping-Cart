<%-- 
    Document   : order
    Created on : 12/03/2018, 09:36:15 AM
    Author     : josera
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle Orden</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css"> 

</head>
<body>
 
   <jsp:include page="_header.jsp" />
    
   <fmt:setLocale value="en_US" scope="session"/>
   <div class="container">
 
     <div class="customer-info-container">
         <h3>Informacion del Cliente</h3>
         <ul>
             <li>Nombre: ${orderInfo.clienteName}</li>
             <li>Email: ${orderInfo.clienteEmail}</li>
             <li>Telefono: ${orderInfo.clientePhone}</li>
             <li>Direccion: ${orderInfo.clienteAddress}</li>
         </ul>
         <h3>Resumen de orden</h3>
         <ul>
             <li>Total:
             <span class="total">
             <fmt:formatNumber value="${orderInfo.amount}" type="currency"/>
             </span></li>
         </ul>
     </div>
      
     <br/>
      
     <table class="table table-striped">
         <thead>
            <tr>
              <th scope="col">Codigo producto</th>
              <th scope="col">Producto</th>
              <th scope="col">Cantidad</th>
              <th scope="col">Precio</th>
              <th scope="col">Total</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach items="${orderInfo.details}" var="orderDetailInfo">
               <tr>
                   <td>${orderDetailInfo.productCode}</td>
                   <td>${orderDetailInfo.productName}</td>
                   <td>${orderDetailInfo.quanity}</td>
                   <td>
                    <fmt:formatNumber value="${orderDetailInfo.price}" type="currency"/>
                   </td>
                   <td>
                    <fmt:formatNumber value="${orderDetailInfo.amount}" type="currency"/>
                   </td>  
               </tr>
            </c:forEach>
          </tbody>
     </table>
     <c:if test="${paginationResult.totalPages > 1}">
         <div class="page-navigator">
            <c:forEach items="${paginationResult.navigationPages}" var = "page">
                <c:if test="${page != -1 }">
                  <a href="orderList?page=${page}" class="nav-item">${page}</a>
                </c:if>
                <c:if test="${page == -1 }">
                  <span class="nav-item"> ... </span>
                </c:if>
            </c:forEach>
              
         </div>
     </c:if>
   </div>
   
 
 
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>
