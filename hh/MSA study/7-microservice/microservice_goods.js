'use strict';

const business = require('../5-monolithic/monolithic_goods.js');
const cluster = require('cluster');

class goods extends require('../6-distributor/server.js') {

    constructor() {
        super("goods"
            , process.argv[2] ? Number(process.argv[2]) : 9010
            , ["POST/goods", "GET/goods", "DELETE/goods"]
        );

        this.connectToDistributor("127.0.0.1", 9000, (data) => {
            console.log("Distributor Notification", data);
        });
    }

    onRead(socket, data) {

        console.log("onRead", socket.remoteAddress, socket.remotPort, data);

        business.onRequest(socket, data.method, data.uri, data.params
                        , (s, packet) => {
                            socket.write(JSON.stringify(packet) + '¶');
                        });
    }

}

//cluster 모듈 적용
if (cluster.isMaster) {
    cluster.fork();

    // exit 이벤트가 발생하면 새로운 자식 프로세스 실행
    cluster.on('exit', (worker, code, signal) => {
        console.log('worker ${worker.process.pid} died');
        cluster.fork();
    });
} else {
    new goods();
}