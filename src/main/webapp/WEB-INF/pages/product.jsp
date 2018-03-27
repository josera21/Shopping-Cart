<%-- 
    Document   : product
    Created on : 12/03/2018, 09:41:22 AM
    Author     : josera
--%>
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product</title>
 
<link rel="stylesheet" type="text/css" href="resources/static/css/style.css">
<link rel="stylesheet" type="text/css" href="resources/static/css/bootstrap.css"> 
</head>
<body>
   <jsp:include page="_header.jsp" />
 
   <div class="text-center">
       <h2>Producto</h2>
   </div>
  
   <c:if test="${not empty errorMessage }">
     <div class="error-message">
         ${errorMessage}
     </div>
   </c:if>
   
   <div class="container">
   <form:form modelAttribute="productForm" method="POST" enctype="multipart/form-data">
       <table class="table" style="text-align:left;">
           <tr>
               <td>Codigo</td>
               <td style="color:red;">
                  <c:if test="${not empty productForm.code}">
                       <input type="hidden" name="code" value="${productForm.code}"/>
                       ${productForm.code}
                  </c:if>
                  <c:if test="${empty productForm.code}">
                       <input name="code" type="text" />
                       <input type="hidden" name="newProduct" path="newProduct" />
                  </c:if>
               </td>
               <td><form:errors path="code" class="error-message" /></td>
           </tr>

           <tr>
               <td>Nombre</td>
               <td><input path="name" value="${productForm.name}" /></td>
               <td><form:errors path="name" class="error-message" /></td>
           </tr>
 
           <tr>
               <td>Precio</td>
               <td><input type="text" name="price" value="${productForm.price}" /></td>
               <td><form:errors path="price" class="error-message" /></td>
           </tr>
           <tr>
               <td>Imagen</td>
               <td>
               <img src="${pageContext.request.contextPath}/productImage?code=${productForm.code}" width="100"/></td>
               <td> </td>
           </tr>
           <tr>
               <td>Subir Imagen</td>
               <td><input type="file" name="fileData" path="fileData"/></td>
               <td> </td>
           </tr>
 
 
           <tr>
               <td>&nbsp;</td>
               <td><input type="submit" class="btn btn-outline-primary" value="Enviar" /> <input type="reset"
                    value="Reset" class="btn btn-outline-dark" /></td>
           </tr>
       </table>
   </form:form>
   </div>
 
 
 
   <jsp:include page="_footer.jsp" />
 
</body>
</html>
