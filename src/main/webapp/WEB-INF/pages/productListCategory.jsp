<%-- 
    Document   : productListCategory
    Created on : 15/03/2018, 07:47:03 PM
    Author     : josera
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de productos</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css">
</head>
<body class="bg-grey">
   <jsp:include page="_header.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="container">
   <h1>Productos disponibles</h1>
   
   <c:if test="${empty paginationProductsByCate.list}">
       <div class="col-md-6 offset-3 top-space" >
           <h2 class="text-warning">No hay productos en esta categoria</h2>
       </div>
        <div class="col-md-6 offset-5 top-space" >
            <a class="btn btn-primary" style="align-content: center;" href="${pageContext.request.contextPath}/categoryList">
             Ver categorias
            </a>
        </div>
   </c:if>
   
   <div class="card-group">
    <c:forEach items="${paginationProductsByCate.list}" var="prodInfo">
        <div class="col-sm-4 top-space">
             <div class="card" style="width: 18rem;">
                  <img class="card-img-top" style="height: 12em;"
                       src="${pageContext.request.contextPath}/productImage?code=${prodInfo.code}" 
                       alt="Card image cap">
                  <div class="card-body">
                      <h5 class="card-title">${prodInfo.name}</h5>
                      <p class="card-text">
                          Codigo: ${prodInfo.code} <br>
                          Categoria: ${prodInfo.categoryCode} <br>
                          Precio: <fmt:formatNumber value="${prodInfo.price}" type="currency"/> <br>
                          Cantidad: 
                          <input class="form-control col-sm-3" type="number" id="${prodInfo.code}" value="1" >
                      </p>
                      <a href="" onclick="this.href='${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}&quantity='+document.getElementById('${prodInfo.code}').value"
                         class="btn btn-primary">
                          Comprar
                      </a>
                         <!-- For Manager edit Product -->
                      <security:authorize access="hasRole('ROLE_MANAGER')">
                       <a class="text-center" style="color:red; margin-left: 2px;"
                           href="${pageContext.request.contextPath}/product?code=${prodInfo.code}">
                            Editar Producto
                       </a>
                      </security:authorize>
                  </div>
             </div>
        </div>
    </c:forEach>
   </div>
   
   <br/>
   
   <c:if test="${paginationProducts.totalPages > 1}">
       <nav aria-label="Page navigation">
           <ul class="pagination">
                <c:forEach items="${paginationProducts.navigationPages}" var = "page">
                    <li class="page-item">
                     <c:if test="${page != -1 }">
                       <a href="productList?page=${page}" class="page-link">${page}</a>
                     </c:if>
                     <c:if test="${page == -1 }">
                       <span class="page-link"> ... </span>
                     </c:if>
                    </li>
                 </c:forEach>
           </ul>
       </nav>
   </c:if>
 
   </div>
   <jsp:include page="_footer.jsp" />
 
</body>
</html>