call, apply, bind
====================
함수의 this가 지칭하는 object를 바꾼다.
------------
- call, apply
call과 apply는 비슷하다. 


파라미터로 여러 개의 인자를 넘겨줄 때 call은 ,로 구분하고 apply는 배열로 넘겨준다.

```
var wizard = {
  hp: 50,
  heal: function() {
    this.hp++
  }
};

var warrior = {
  hp: 100
}

wizard.heal.call(warrior);
```
call과 apply에 첫 번째 인자로 넘겨주는 객체가 this로 할당된다.

```
const 큰돌 = {
    "이름": "큰돌"
}

function editmyresume(능력, 힘) {
    this.능력 = 능력
    this.힘 = 힘
} 
editmyresume.call(큰돌, "Vue.js", "팔굽혀펴기") 
console.log(큰돌)
```

- bind
call, apply가 자신이 붙어있는 함수를 바로 실행시키는 반면, bind는 그렇지 않다.
bind는 대상 함수의 사본을 만든다.

```
var log = function() {
  colsole.log('Logged: ' + this.greet());
}

log(); //Error

var person = {
  name: 'John',
  greet: function() {
    return 'Hello ' + this.name;
  }
}
var logBind = log.bind(person);

logBind(); //Hello John
```
