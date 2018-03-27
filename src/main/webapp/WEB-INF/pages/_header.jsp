<%-- 
    Document   : _header
    Created on : 12/03/2018, 09:34:20 AM
    Author     : josera
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>   
 
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal" id="page-title">Food shop</h5>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-primary" href="${pageContext.request.contextPath}/">Home</a>
        <a class="p-2 text-primary" href="${pageContext.request.contextPath}/categoryList">
            Categorias
        </a>
        <a class="p-2 text-primary" href="${pageContext.request.contextPath}/productList">
            Lista de productos
        </a>
        <a class="p-2 text-primary" href="${pageContext.request.contextPath}/shoppingCart">
            Mi carro
        </a>
        <security:authorize  access="hasAnyRole('ROLE_EMPLEADO','ROLE_MANAGER')">
        <a class="p-2 text-primary" href="${pageContext.request.contextPath}/orderList">
            Ordenes
        </a>
        |
        </security:authorize>
   
        <security:authorize  access="hasRole('ROLE_MANAGER')">
        <a class="p-2 text-primary" href="${pageContext.request.contextPath}/product">
            Crear Producto
        </a>
        |
        </security:authorize>
    </nav>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
    <a class="p-2 text-success" href="${pageContext.request.contextPath}/accountInfo">
            Hola ${pageContext.request.userPrincipal.name} </a>
     &nbsp;|&nbsp;
       <a class="p-2 text-danger" href="${pageContext.request.contextPath}/logout">Logout</a>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name == null}">
        <a class="btn btn-outline-success" href="${pageContext.request.contextPath}/login">Login</a>
    </c:if>
</div>


