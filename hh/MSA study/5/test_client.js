const http = require('http');

var option = {
        host : "127.0.0.1",
        port: 9090,
        headers : {
            'Content-Type' : 'application/json'
        }
};

function request(cb, params) {
    
    var req = http.request(options, (res) => {
        var data = "";
        res.on('data', (chunk) => {
            data += chunk;
        });
        
        res.on('end', () => {
            console.log(options, data);
            cb();
        });
    });
    
    if (params) {
        req.write(JSON.stringify(params)); // POST, PUT 이면 스트링으로 전송
    }
    
    req.end();
}

/**
 * 상품 관리 API 테스트
 */

function goods(callback) {
    
    goods_post(() => {
        goods_get(() => {
            goods_delete(callback);
        });
    });
    
    function goods_post(cb) {
        options.method = "POST";
        options.path = "/goods";
        request(cb, {
            name: "test Goods",
            category: "tests",
            price: 1000,
            description: "test"
        });
    }
    
    function goods_get(cb) {
        options.method = "GET";
        options.path = "/goods";
        request(cb);
    }
    
    function goods_delete(cb) {
        options.method = "DELETE";
        options.path = "/goods?id=1";
        request(cb);
    }
}