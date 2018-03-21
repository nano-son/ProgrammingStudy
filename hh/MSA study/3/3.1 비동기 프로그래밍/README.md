# 화살표 함수

>중괄호 갯수를 줄일 수 있는 코딩 스타일이라고 하는데 아직 잘 모르겠다. 아래 두 코드는 같은 코드이다. dynamic this 와 lexical this 이 부분을 공부해야된다.

<pre>
<code>

friends.forEach(function (friend) {
	console.log(that.name + " says hello to " + friend)
});

</code>
</pre>

<pre>
<code>

friends.forEach(friend => {
	console.log(this.name + " says hello to " + friend)
});

</code>
</pre>


# nextTick을 사용한 비동기 프로그래밍

<pre>
<code>
function func(callback) {
	// nextTick을 사용해 인자 값으로 전달된  callback. 함수 호출
	process.nextTick(callback, "callback!!");
}

try {						//예외처리를 위한 try~catch문
	func( (param) => {		
		a.a = 0;			//예외발생
	});
} catch (e) {
	console.log ("exception!!");		//같은 스레드일 경우 호출
}
</code></pre>

> 의도적으로 예외를 던졌음에도 불구하고 잡지 못한다. 이는 process.nextTick 함수 때문이다. 이 함수는 node.js 내부의 스레드 풀로 다른 스레드 위에서 콜백 함수를 동작합니다. try catch 문은 같은 스레드 내에서만 동작하기 때문에 예외를 못잡았다. 


