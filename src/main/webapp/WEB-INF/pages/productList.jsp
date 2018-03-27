<%-- 
    Document   : productList
    Created on : 12/03/2018, 09:41:41 AM
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
<title>Product List</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css">

</head>
<body class="bg-grey">
   <jsp:include page="_header.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
 
   <div class="container">
   <h1>Lista de Productos</h1>
   
   <div class="card-group">
    <c:forEach items="${paginationProducts.list}" var="prodInfo">
        <div class="col-sm-4 top-space">
             <div class="card" style="width: 18rem;">
                  <img class="card-img-top" style="height: 12em;" 
                       src="${pageContext.request.contextPath}/productImage?code=${prodInfo.code}" 
                       alt="Card image cap">
                  <div class="card-body">
                      <h5 class="card-title">${prodInfo.name}</h5>
                      <p class="card-text">
                          Codigo: ${prodInfo.code} <br>
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
                       <a style="color:red;"
                           href="${pageContext.request.contextPath}/product?code=${prodInfo.code}">
                             Edit Product
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
