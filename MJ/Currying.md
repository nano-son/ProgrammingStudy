Currying
=======
여러 개의 인자를 가진 함수를 호출할 경우, 파라미터의 수보다 적은 수의 파라미터를 인자로 받으면 누락된 파라미터를 인자로 받는 함수를 리턴하는 기법.

함수의 팩토리같은 느낌이다. 함수 자체를 재활용할 수 있도록 한 것.

```
//10을 더하는 함수
const add10 = (a) => a + 10;

//20을 더하는 함수
const add20 = (a) => a + 20;

//30을 더하는 함수
const add30 = (a) => a + 30;
```

이럻게 다 따로 만드는 것이 아니라 주요 기능을 하나의 함수로 만들어 놓고, 그것을 재활용

```
const currying = func => arg1 => arg2 => func(arg1, arg2);
const adder = currying((a, b) => a + b);
const add10 = adder(10);
const add20 = adder(20);

add10(1); //11
```

- 커링 : 다인수 함수를 일인수 함수들의 체인으로 바꿔주는 방법.
- 부분적용 : 다인수 함수를 생략될 인수의 값을 미리 정해서 더 적은수의 인수를 받는 하나의 함수로 변형하는 방법.

https://github.com/seongminwoo/study/wiki/%5BFunctional-Programming%5D-%EC%BB%A4%EB%A7%81(currying)%EA%B3%BC-%EB%B6%80%EB%B6%84%EC%A0%81%EC%9A%A9(partial-application)



http://blog.jeonghwan.net/js/2017/04/17/curry.html

https://rhostem.github.io/posts/2017-04-20-curry-and-partial-application/

