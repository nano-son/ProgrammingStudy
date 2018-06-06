'use strict'

var map = {};

/*
    distributor는 모든 노드들이 접속해 있는 서버, 그 안에 각 노드들의 정보(포트, 노드이름, 처리가능한 urls)를 저장하고 있다.
*/

/*
    1. 노드가 접속하면 접속한 노드에 현재 접속 중인 다른 노드들의 정보를 제공,
    2. 노드가 종료되면 다른 접속된 노드에 전파
*/

class distributor extends require('./server.js') {
    constructor() {
        //노드이름, 포트, urls
        super("distributor", 9000, ["POST/distributors", "GET/distributors"]);
    }
    
    // 노드 접속 이벤트 처리
    onCreate(socket) {
        console.log("onCreate", socket.remoteAddress, socket.remotePort);
        this.sendInfo(socket);
    }

    // 접속 해제 이벤트 처리
    onClose(socket) {
        var key = socket.remoteAddress + ":" + socket.remotePort;
        console.log("onClose", socket.remoteAddress, socket.remotePort);
        delete map[key];
        this.sendInfo();
    }

    // 접속한 노드가 자신의 정보를 보낸 걸 받는 이벤트 처리
    onRead(socket, json) {
        //키 생성
        var key = socket.remoteAddress + ":" + socket.remotePort;
        console.log("onRead", socket.remoteAddress, socket.remotePort, json);
        
        //노드 정보 등록
        if (json.uri == "/distributes" && json.method == "POST") {
            map[key] = {
                socket: socket
            };
            map[key].info = json.params;
            map[key].info.host = socket.remoteAddress;
            this.sendInfo();
        }
    }
            

    write(socket, packet) {
        socket.write(JSON.stringify(packet) + '¶');
    }

    //노드 접속 정보를 전파
    sendInfo(socket) {
        var packet = {
            uri: "/distributes",
            method: "GET",
            key: 0,
            params: []
        };

        for (var n in map) {
            packet.params.push(map[n].info);
        }

        if (socket) {
            this.write(socket, packet);
        } else {
            for (var n in map) {
                this.write(map[n].socket, packet);
            }
        }
    }
}

new distributor();