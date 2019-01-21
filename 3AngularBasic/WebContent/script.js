


(function(){
    var app= angular.module('todo', []);
    app.controller('TodoCtrl', ['$scope', function($scope){
        $scope.name='Chris';
    }]);
})();

(function(){
    var myApp=angular.module('myApp',[]);

    myApp.controller('FirstCtrl',['$scope', function($scope){
        $scope.first="hello";
        $scope.two="my";
        $scope.three="friend";
    }]);
})();