# 3.2 싱글 스레드 프로그래밍

> node.js 는 싱글스레드로 멀티스레드 성능을 구현하도록 프레임워크가 구성되어 있다.

<pre>
<code>

function func(callback) {
	process.nextTick(callback, "callback!!");
}

try {
	func((param) => {
		a.a = 0;
	});
} catch (e) {
	console.log("exception!!");
}

process.on("uncaughtException", (error) => {	// 모든 스레드에서 발생하는 예외
	console.log("uncaughtException")
});

</code></pre>


> 여기서 주의해야할 점은 싱글 스레드라고 다 같은 스레드 위에서 동작하지 않는다는 점이다. 3.1에서 try ~ catch 문에서 못 잡은 예외가 있는 것이 그 경우이다. node.js는 모든 스레드에서 예외 처리를 할 수 있도록 uncaughtException 이벤트를 제공한다.