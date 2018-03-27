<%-- 
    Document   : accountInfo
    Created on : 12/03/2018, 09:35:37 AM
    Author     : josera
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Perfil</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css">
</head>
<body>
   <jsp:include page="_header.jsp" />
   
   <div class="container">
        <h1>Informacion de la cuenta</h1>

        <div class="container-fluid well span6">
            <div class='row-fluid'>
                <div class="span2" >
		    <img style='width: 15%; height: 15%;' src="resources/static/images/perfil-icon.png" class="img-circle">
                </div>
                <div class='span8'>
                    <h3>${pageContext.request.userPrincipal.name}</h3>
                    <h5>Permisos:
                        <c:forEach items="${userDetails.authorities}" var="auth">
                        <h6>${auth.authority }</h6>
                        </c:forEach>
                    </h5>
                </div>
            </div>
        </div>
   </div>
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>
