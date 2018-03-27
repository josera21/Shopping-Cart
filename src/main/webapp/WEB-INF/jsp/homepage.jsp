<%-- 
    Document   : homepage
    Created on : 11/03/2018, 05:25:31 PM
    Author     : josera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gestion de Compra</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../resources/static/css/app.css">
    </head>
    <body>
        <h2>Spring and Angularjs Tutorial</h2>
        <div class="home-section">
            <ul class="menu-list">
                <li><a href="#/gallery">Photo Gallery</a></li>
                <li><a href="#/contactus">Contact</a></li>
            </ul>
        </div>
        <div ng-view></div>
        <script src="./webjars/angularjs/1.4.8/angular.js"></script>
        <script src="./webjars/angularjs/1.4.8/angular-resource.js"></script>
        <script src="./webjars/angularjs/1.4.8/angular-route.js"></script>
        <script src="../resources/static/js/app.js"></script>
        <script src="../resources/static/js/controller.js"></script>
        <link rel="stylesheet" href="./webjars/bootstrap/3.3.6/css/bootstrap.css">
    </body>
</html>
