


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

(function(){
    var myApp2=angular.module('myApp2',[])
    
    myApp2.controller('SecondCtrl',['$scope', function($scope){
        $scope.first= {
            "key":"value",
            "key2":"value2",
            "key3":"value3"
        };
        $scope.first.detail={
            "key":"Detailvalue",
            "key2":"Detailvalue2",
            "key3":"Detailvalue3"
        };
    }]);
})();