'use strict';

const net = require('net');
const tcpClient = require('./client.js'); //Client 클래스 참조

class tcpServer {

    constructor(name, port, urls) {
        this.context = {
            port: port,
            name: name,
            urls: urls
        }
        this.merge = {};

        //서버 생성
        this.server = net.createServer((socket) => {
            // 클라이언트 접속 이벤트 처리
            this.onCreate(socket);

            //에러이벤트 처리
            socket.on('error', (exception) => {
                this.onClose(socket);
            });

            socket.on('close', () => {
                this.onClose(socket);
            });

            socket.on('data', (data) => {

                var key = socket.remoteAddress + ":" + socket.remotePort;
                var sz = this.merge[key] ? this.merge[key] +data.toString() : data.toString();
                var arr = sz.split('¶');

                for (var n in arr) {

                    if (sz.charAt(sz.length - 1) != '¶' && n == arr.length - 1) {

                        this.merge[key] = arr[n];
                        break;

                    } else if (arr[n] == "") {
                        break;
                    } else {
                        this.onRead(socket, JSON.parse(arr[n]));
                    }

                }

            });

        });

        this.server.on('error', (err) => {
            console.log(err);
        });

        this.server.listen(port, () => {
            console.log('listen', this.server.address());
        });
    }

    onCreate(socket) {
        console.log("onCreate", socket.remoteAddress, socket.remotePort);
    }
    
    onClose(socket) {
        console.log("onClose", socket.remoteAddress, socket.remotePort);
    }

    /*
        distributor 접속 기능
    */

    //Distributor 접속함수
    connectToDistributor(host, port, onNotice) {

        //Distributor에 전달할 패킷
        var packet = {
            uri: "/distributes",
            method: "POST",
            key: 0,
            params: this.context
        };

        // Distributor에 접속해있는지 저장할 변수
        var isConnectedDistributor = false;

        // 클라이언트 instance
        /*
            tcpClient(host, port, onCreate, onRead, onEnd, onError)
        */
        this.clientDistributor = new tcpClient(
            host
            , port
            , (options) => { // 접속 event
                isConnectedDistributor = true;
                this.clientDistributor.write(packet);
            }
            , (options, data) => { onNotice(data); }  // 데이터 수신 이벤트
            , (options) => { isConnectedDistributor = false; } //접속 종료 이벤트
            , (options) => { isConnectedDistributor = false; } //에러 이벤트
        );

        // Distributor에 3초 간격으로 계속 접속 시도
        setInterval( () => {
            if (isConnectedDistributor != true) {
                this.clientDistributor.connect();
            }
            
        }, 3000);
    }
}

module.exports = tcpServer; // exports 선언