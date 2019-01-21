

(function(){
    var myApp=angular.module('myApp',[]);

    myApp.controller('FirstCtrl',['$scope', function($scope){
        $scope.first="hello";
        $scope.two="my";
        $scope.three="friend";
    }]);
    
    //키 + 값으로 다음과 같은 형태로 만들 수도 있다.
    myApp.controller('SecondCtrl',['$scope', function($scope){
        $scope.first= {};
        $scope.first.detail={
            "key":"Detailvalue",
            "key2":"Detailvalue2",
            "key3":"Detailvalue3"
        };
    }]);
})();

