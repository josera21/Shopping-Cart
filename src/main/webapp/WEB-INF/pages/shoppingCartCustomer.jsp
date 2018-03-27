<%-- 
    Document   : shoppingCartCustomer
    Created on : 12/03/2018, 09:42:41 AM
    Author     : josera
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 
<title>Proceso de pedido</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css">
<script src="resources/static/js/angular.min.js" ></script>
<script src="resources/static/js/app.js" ></script>

</head>
<body ng-app='formApp' class="bg-grey">
<jsp:include page="_header.jsp" />
 
    <div class='container'>
        <div ng-controller='customerCtrl' class="col-md-6 offset-3">
        <h2>Ingrese la informacion del cliente</h2>
        
        <form name='customerForm' class='top-space' method="POST" modelAttribute="customerForm"
            action="${pageContext.request.contextPath}/shoppingCartCustomer">
            
            <div class='form-group'>
                <label for="name">Nombre</label>
                <input type='text' name='name' ng-model='formData.name' required autofocus
                       class='form-control' placeholder='Nombre' />
                <span style='color: red;' ng-show='customerForm.name.$dirty && customerForm.name.$invalid'>
                    <span ng-show='customerForm.name.$error.required' >Se requiere nombre</span>
                </span>
                
            </div>
            <div class='form-group'>
                <label for="email">Email</label>
                <input type='email' name='email' ng-model='formData.email' required
                       class='form-control' placeholder='Email' />
                <span style='color: red;' ng-show='customerForm.email.$dirty && customerForm.email.$invalid'>
                    <span ng-show='customerForm.email.$error.required' >Se requiere email</span>
                    <span ng-show='customerForm.email.$error.email' >Correo invalido</span>
                </span>
            </div>
            <div class='form-group'>
                <label for="phone">Telefono</label>
                <input type='text' name='phone' ng-model='formData.phone' required
                       class='form-control' placeholder='Telefono' />
                <span style='color: red;' ng-show='customerForm.phone.$dirty && customerForm.phone.$invalid'>
                    <span ng-show='customerForm.phone.$error.required' >Se requiere telefono</span>
                </span>
            </div>
            <div class='form-group'>
                <label for="address">Direccion</label>
                <input type='text' name='address' ng-model='formData.address' required
                       class='form-control' placeholder='Direccion' />
                <span style='color: red;' ng-show='customerForm.address.$dirty && customerForm.address.$invalid'>
                    <span ng-show='customerForm.address.$error.required' >Se requiere direccion</span>
                </span>
            </div>
            <br>

            <input type='submit' ng-disabled='!customerForm.$valid'
                   ng-click="submitForm()"
                    class='btn btn-block btn-success' value='Enviar' />
            <!--
            <table>
                <tr>
                    <td>Name *</td>
                    <td><form:input path="name" /></td>
                    <td><form:errors path="name" class="error-message" /></td>
                </tr>

                <tr>
                    <td>Email *</td>
                    <td><form:input path="email" /></td>
                    <td><form:errors path="email" class="error-message" /></td>
                </tr>

                <tr>
                    <td>Phone *</td>
                    <td><form:input path="phone" /></td>
                    <td><form:errors path="phone" class="error-message" /></td>
                </tr>

                <tr>
                    <td>Address *</td>
                    <td><form:input path="address" /></td>
                    <td><form:errors path="address" class="error-message" /></td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" value="Submit" /> <input type="reset"
                        value="Reset" /></td>
                </tr>
            </table>
            -->
        </form>
        </div>
   </div>
 
   <jsp:include page="_footer.jsp" />
 
 
</body>
</html>
