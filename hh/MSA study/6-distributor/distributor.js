var map = {};

class distributor extends require('./server.js') {
    constructor() {
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
        socket.wirte(JSON.stringify(packet) + '¶');
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