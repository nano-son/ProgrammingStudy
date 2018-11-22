call, apply, bind
====================
함수의 this가 지칭하는 object를 바꾼다.
------------
- call, apply
call과 apply는 비슷하다. 파라미터로 여러 개의 인자를 넘겨줄 때 call은 ,로 구분하고 apply는 배열로 넘겨준다.

```
var wizard = {
  hp: 50,
  heal: function() {
    this.hp++
  }
  add: function(a, b, c, d) {
    return a+b+c+d
  }
};

var warrior = {
  hp: 100
}

wizard.heal.call(warrior);
```
