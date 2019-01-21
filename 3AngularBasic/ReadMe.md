#AngulJS 사용법.

## Module , Controller
### 1. ng-app 은 사용할 모듈을 설정.

### 2. ng-controller 는 모듈안의 특정 컨트롤러를 설정.

#### 3. Example
###### script 부분

```javascript
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
```

###### HTML 부분
```html
    <div ng-app="myApp"><!-- div 태그에 모듈 적용 -->
      <div ng-controller="FirstCtrl"><!-- 해당 모듈 안에 있는 컨트롤러 사용-->
        <!-- controller logic-->
        <p>{{first}}</p>
        <p>{{two}}</p>
        <p>{{three}}</p>
        
      </div>
    </div>
    
    <div ng-app="todo"> 
      <div ng-controller="TodoCtrl">
        <p>{{name}}</p>
      </div>
    </div>
```