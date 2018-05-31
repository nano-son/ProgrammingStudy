const http = require('http');
const url = require('url');
const querystring = require('querystring');

const tcpClient = require('../6-distributor/client.js'); // HTTP 게이트웨이가 마이크로 서비스들과 통신하려고 Client 클래스 참조

var mapClient = {};
var mapUrls = {};
var mapResponse = {};
var mapRR = {};
var index = 0;

// HTTP 서버 생성
var server = http.createServer( (req, res) => {

    var method = req.method;
    var uri = url.parse(req.url, true);
    var pathname = uri.pathname;

    //POST, PUT 메소드 처리
    if (method == "POST" || method == "PUT") {
        var body = "";

        req.on('data', function(data) {
            body += data;
        });

        req.on('end', function() {
            var params;
            // 헤더가 application/json 일 때는 JSON으로 파싱
            if (req.headers['content-type'] == "application/json") {
                params = JSON.parse(body);
            } else {
                // JSON이 아니면 querystring으로 파싱
                params = querystring.parse(body);
            }

            onRequest(res, method, pathname, params);
        });
    } else {
        onRequest(res, method, pathname, uri.query);
    }

}).listen(8000, ()=> {
    console.log('listen', sever.address());

    //Distributor에 전달할 패킷
    var packet = {
        uri: "/distributes",
        method: "POST",
        key: 0,
        params: {
            port: 8000,
            name: "gate",
            urls: []
        }
    };

    var isConnectedDistributor = false;

    //distributor에 접속
    this.clientDistributor = new tcpClient(
            "127.0.0.1"
            , 9000
            , (options) => {                                        // 접속 완료 이벤트
                isConnectedDistributor = true;
                this.clientDistributor.write(packet);
            }
            , (options, data) => {  onDistribute(data); }           // 데이터 수신 이벤트
            , (options) => {    isConnectedDistributor = false; }   // 접속 종료 이벤트
            , (options) => {    isConnectedDistributor = false; }   // 에러 이벤트
    );

    // 재접속
    setInterval(() => {
        if (isConnectedDistributor != true) {
            this.clientDistributor.connect();
        }
    }, 3000);
});

function onRequest(res, method, pathname, params) {

}

function onDistribute(data) {

    for (var n in data.params) {

        var node = data.params[n];
        var key = node.host + ":" + node.port;

        if (mapClient[key] == null && node.name != "gate") {
            var client = new tcpClient(node.host, node.port, onCreateClient, onReadClient, onEndClient, onErrorClient);

            mapClient[key] = {
                client: client,
                info: node
            };

            for (var m in node.urls) {
                
                var key = node.urls[m];
                if (mapUrls[key] == null) {
                    mapUrls[key] = [];
                }
                mapUrls[key].push(client);
            }
            client.connect();
        }
    }
}

function onCreateClient(options) {
    console.log("onCreateClient");
}

function onReadClient(options, packet) {

}

function onEndClient(options) {
    var key = options.host + ":" + options.port;
    console.log("onEndClient", mapClients[key]);
    for (var n in mapClients[key].info.urls) {

        var node = mapClients[key].info.urls[n];
        delete mapUrls[node];
    }
    delete mapClients[key];
}

function onErrorClient(options) {
    console.log("onErrorClient");
}