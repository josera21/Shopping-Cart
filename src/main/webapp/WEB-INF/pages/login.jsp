<%-- 
    Document   : login
    Created on : 12/03/2018, 09:36:06 AM
    Author     : josera
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Login</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css"> 
<script src="resources/static/js/angular.min.js" ></script>
<script src="resources/static/js/app.js" ></script>
</head>
<body data-ng-app="formApp" class="bg-grey">
   <jsp:include page="_header.jsp" />
    <br>
    <!-- /login?error=true -->
    <c:if test="${param.error == 'true'}">
        <div style="color: red; margin: 10px 0px;">

            Login Failed!!!<br /> Reason :
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

        </div>
    </c:if>
    
    <div data-ng-controller="MainCtrl">
        <form name="loginForm" class="form-signin text-center" method="POST"
            action="${pageContext.request.contextPath}/j_spring_security_check">
            <img class="mb-4" src="resources/static/images/perfil-icon.png" alt="" width="72" height="72">
            <h1 class="h3 mb-3 font-weight-normal">Por favor inicia sesion</h1>

            <label for="userName" class="sr-only">Nombre de usuario</label>
            <input type="text" name="userName" class="form-control" 
                   placeholder="ingrese el usuario" data-ng-model='formData.userName' required autofocus
                   data-ng-class='{ error: loginForm.userName.$invalid && !loginForm.$pristine }'>

            <label for="password" class="sr-only">Password</label>
            <input type="password" name="password" class="form-control"
                   placeholder="Password" ng-model="formData.password" required
                   data-ng-class='{ error: loginForm.password.$invalid && !loginForm.$pristine }'>

            <input class="btn btn-lg btn-primary btn-block" data-ng-disabled='!loginForm.$valid' 
                   type="submit" value="Login" /> 
         </form>
    </div>
    <span class="error-message">${error }</span>
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>
