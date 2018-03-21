function func(callback) {		// fucn 함수 선언
	callback("callback!!");	// 인자 값으로 전달된 callback 함수 호출
}

func( (param) => {			// 익명 함수를 인자로 func 함수 호출
	console.log(param);
});