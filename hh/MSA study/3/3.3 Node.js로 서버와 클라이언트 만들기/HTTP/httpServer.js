const http = require('http'); 			// http 모듈 로드

var server = http.createServer((req, res) => {	
//createServer 함수로 서버 인스턴스 생성, 콜백으로 req,res 전달받게 된다.
	res.end("hello world");
});

server.listen(9090);