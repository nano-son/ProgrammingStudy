var http = require('http');

// 호출 페이지 정보 설정
var option = {               
        host : "127.0.0.1",
        port : "9090",
        path : "/"
};

//페이지 호출
var req = http.request(option, (res) => {
    var data = "";
    
    res.on('data', (chunk) => { // 서버가 보내는 데이터 수신
        data += chunk;
    });
    
    res.on('end', () => {       // 수신 완료하면 화면에 출력
        console.log(data);
    });
});

//명시적 완료
req.end();