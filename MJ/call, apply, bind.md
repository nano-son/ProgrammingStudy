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
call에 첫 번째 인자로 넘겨주는 객체가 this로 할당된다.

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
