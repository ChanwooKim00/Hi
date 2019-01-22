#AngulJS 사용법.

## Module , Controller
### 1. ng-app 은 사용할 모듈을 설정.

### 2. ng-controller 는 모듈안의 특정 컨트롤러를 설정.

#### Example
###### script 부분

```javascript


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


```

###### HTML 부분
```html
 <!-- div 태그에 모듈 적용 -->
 <div ng-app="myApp" ng-init="hi=0">
 <!-- 해당 모듈 안에 있는 컨트롤러 사용-->
      <div ng-controller="FirstCtrl"> 
	      <input type="text" ng-model="first">
	      <input type="text" ng-model="two">
	      <input type="text" ng-model="three">
	      <input type="text" ng-model="hi">
	        <p>{{first}}</p>
	        <p>{{two}}</p>
	        <p>{{three}}</p>   
	        <!-- ng-init 에 들어간 키값 -->
	        <p>{{hi}}</p>  
      </div>
      
      <div ng-controller="SecondCtrl">
       <!-- 키+값으로 구성된 배열 전체 불러오기 -->
      	<p>{{first}}</p>
      	<!-- first 에 키 가 key2인 값 가져오기 -->
      	<p>{{first.detail.key2}}</p>
      </div>
    </div>
```
### 3. 하나의 페이지에서는 하나의 Module 밖에 갖지 못한다.
### 4. 하나의 Module 에서 여러개의 Controller 를 사용해서 여러가지 구현 
### 5. 위의   SecondCtrl Controller 부분처럼 여러가지 데이터를  [ 키:값 ] 형태(Json)로 사용