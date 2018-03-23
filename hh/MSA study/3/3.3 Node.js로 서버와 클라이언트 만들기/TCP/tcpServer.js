//net 모듈 로드
var net = require('net');              

//tcp 서버 만듬
var server = net.createServer((socket) => {
    socket.end("hello world"); // 접속하면 hello world 뿌리기
});

server.on('error', (err) => {
    console.log(err);
});


// 9090 포트로 리슨, 리슨이 가능해지면 화면에 출력
server.listen(9090, () => {
    console.log('listen', server.address());
})