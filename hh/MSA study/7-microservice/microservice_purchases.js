'use strict'

const business = require('../5-monolithic/monolithic_purchases.js')

class purchases extends require('../6-distributor/server.js') {
    
    constructor() {
        
        super("purchases"
            , process.argv[2] ? Number(process.argv[2]) : 9030
            , ["POST/purchases", "GET/purchases"]);
        
            this.connectToDistributor("127.0.0.1", 9000, (data) => {
                console.log("Distributor Notification", data);
            });
    }

    onRead(socket, data) {
        console.log("onRead", socket.remoteAddress, socket.romotePort, data);
        
        business.onRequest(socket, data.method, data.uri, data.params
                        , (s, packet) => {
                            socket.write(JOSN.stringify(packet) + '¶');
                        })
    }
}

new purchases();