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