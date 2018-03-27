/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var app = angular.module('formApp', []);

// For login
app.controller('MainCtrl', function ($scope) {
  $scope.formData = {};
});

app.controller('customerCtrl', function ($scope) {
   $scope.formData = {};
   
   $scope.submitForm = function() {
     alert('Compra procesada!');  
   };
});


