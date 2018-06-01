# [nodejs] 스크립팅 

### 콜백 함수(callback)

파라미터로 전달되는 함수

``` javascript
function add(a, b, callback) {
    var result = a + b;
    callback(result);
}
```

#### Example

``` javascript

function add(a, b, callback) {

    var result = a + b;
    var cnt = 0;
    callback(result);

    var history = function() {
        cnt++;
        return a + '+' + b + '=' + result;
    }
    return history;
};

var add_history = add(10, 10, function(result) {
    console.log('파라미터로 전달된 콜백 함수 호출됨');
    console.log('더하기 (10, 10)의 결과: %d', result);
});

```

1. add(10, 10, function(result))로 add 함수를 호출한다. add함수에서는 result에 a+b를 대입한다.
2. callback(result)를 호출하여서 console.log를 찍는다.
3. add(10,10,function(result))를 호출한 쪽의 반환값으로 histroy의 함수 객체를 반환 시킨 값이 add_history 의 변수로 들어가게 된다.
