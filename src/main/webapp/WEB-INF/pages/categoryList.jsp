<%-- 
    Document   : categoriList
    Created on : 12/03/2018, 08:11:48 PM
    Author     : josera
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Categorias</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css"> 

</head>
<body class="bg-grey" >
   <jsp:include page="_header.jsp" />
  
   <fmt:setLocale value="en_US" scope="session"/>
   <div class="container">
        <h1>Categorias</h1>
  
        <div class="row">
         <c:forEach items="${paginationCategories.list}" var="cateInfo">
             <div class="col-sm-4">
                 <div class="card" style="width: 18rem;">
                     <img class="card-img-top" style="height: 12em;" 
                            src="${pageContext.request.contextPath}/categoryImage?code=${cateInfo.code}" 
                            alt="Card image cap">
                       <div class="card-body">
                           <h5 class="card-title">${cateInfo.name}</h5>
                           <p class="card-text">
                               Codigo: ${cateInfo.code} <br>
                           </p>
                           <a href="${pageContext.request.contextPath}/productListCategory?code=${cateInfo.code}" 
                                class="btn btn-success">
                                Ver productos
                           </a>
                       </div>
                 </div>
             </div>
         </c:forEach>
        </div>
   
   <br/>
   </div>
   <jsp:include page="_footer.jsp" />
 
</body>
</html>
