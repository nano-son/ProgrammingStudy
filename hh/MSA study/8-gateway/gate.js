/*
    1. httpServer를 만든다. API 요청이 올테니 callback함수를 만들어 놓는다.
    2. listen을 하면서 동시에 distributor에 현재 접속해있는 서비스들의 목록을 받아온다(이때 gate는 클라이언트입장)
    3. 클라이언트가 만들어 질 때 gate의 정보를 distributor에 뿌리고 distributor에 sendInfo함수로 접속해있는 서비스 정보를 받는다.

*/
const http = require('http');
const url = require('url');
const querystring = require('querystring');

const tcpClient = require('../6-distributor/client.js'); // HTTP 게이트웨이가 마이크로 서비스들과 통신하려고 Client 클래스 참조

var mapClient = {}; // Client(호스트, 포트) 로 접속해있는 client 저장해둔 map -> 요청후 삭제
var mapUrls = {}; // method+pathname / 처리 가능한 API URL 을 key값으로 가지고 그걸 담당하는 서비스를 반환
var mapResponse = {}; // res 객체를 저장하는 배열
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
    console.log('start listening', server.address());

    // Distributor에 전달할 패킷
    // Distributor와 HTTP 게이트웨이는 물리적으로 다른 장비에서 실행하는 것이 좋지만
    // 개발 편의상 로컬 ㅎ
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

    //distributor에 접속, 클라리언트 인스턴스 생성
    this.clientDistributor = new tcpClient(
            "127.0.0.1"
            , 9000
            , (options) => {                                        // 접속 완료 이벤트
                isConnectedDistributor = true;
                this.clientDistributor.write(packet);
            }
            , (options, data) => {  onDistribute(data); }           // 데이터 수신 이벤트=> 접속 가능한 마이크로 서비스 목록이 전달된다.
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

    var key = method + pathname;
    var client = mapUrls[key];

    if (client == null) {
        res.writeHead(404);
        res.end();
        return;
    } else {
        params.key = index;

        var packet = {
            uri: pathname,
            method: method,
            params: params
        };

        mapResponse[index] = res;
        index++;

        if (mapRR[key] == null) {
            mapRR[key] = 0;
        }

        mapRR[key]++;
        client[mapRR[key] % client.length].write(packet);
    }
}

//data 접속 가능한 마이크로서비스 목록
function onDistribute(data) {

    for (var n in data.params) {

        //port, urls, 노드네임이 들어있음
        var node = data.params[n];
        var key = node.host + ":" + node.port;

        //게이트웨이에서 접속 하고 있지 않은 마이크로서비스의 Client클래스 인스턴스 생성
        if (mapClient[key] == null && node.name != "gate") {
            var client = new tcpClient(node.host, node.port, onCreateClient, onReadClient, onEndClient, onErrorClient);

            mapClient[key] = {
                client: client,
                info: node
            };
            // 각 마이크로 서비스마다 처리 가능한 URL들 저장
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
    console.log("onReadClient", packet);
    mapResponse[packet.key].writeHead(200, {'"Content-Type': 'application/json'});
    mapResponse[packet.key].end(JSON.stringify(packet));
    delete mapResponse[packet.key];
}

// 접속을 종료하면 그 마이크로서비스가 처리가능했던 url을 삭제하고
// Client객체도 지워준다.
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