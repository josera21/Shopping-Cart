<%-- 
    Document   : orderList
    Created on : 12/03/2018, 09:36:39 AM
    Author     : josera
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista de ordenes</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css"> 
</head>
<body>
 
   <jsp:include page="_header.jsp" />
 
   <fmt:setLocale value="en_US" scope="session"/>
  
   <div class='container' >
        <h2>Lista de ordenes</h2>
   
        <div>Total de ordenes: ${paginationResult.totalRecords}</div>

        <table class='table top-space' border="1" style="width:100%">
            <thead class='thead-dark' >
                <tr>
                     <th scope='col'>Numero orden</th>
                     <th scope='col'>Fecha orden</th>
                     <th scope='col'>Nombre cliente</th>
                     <th scope='col'>Direccion cliente</th>
                     <th scope='col'>Email cliente</th>
                     <th scope='col'>Monto</th>
                     <th scope='col'>Link</th>
                 </tr>
            </thead>
            <tbody>
                <c:forEach items="${paginationResult.list}" var="orderInfo">
                    <tr>
                        <th scope='row' >${orderInfo.orderNum}</th>
                        <td>
                           <fmt:formatDate value="${orderInfo.orderDate}" pattern="dd-MM-yyyy HH:mm"/>
                        </td>
                        <td>${orderInfo.clienteName}</td>
                        <td>${orderInfo.clienteAddress}</td>
                        <td>${orderInfo.clienteEmail}</td>
                        <td style="color:red;">
                           <fmt:formatNumber value="${orderInfo.amount}" type="currency"/>
                        </td>
                        <td><a href="${pageContext.request.contextPath}/order?orderId=${orderInfo.id}">
                           Ver</a></td>
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
