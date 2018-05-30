'use strict';

const business = require('../5-monolithic/monolithic_members.js');

class members extends require('../6-distributor/server.js') {
    constructor() {
        super("members"
            , process.argv[2] ? Number(process,argv[2]) : 9020
            , ["POST/members", "GET/members", "DELETE/members"]
        );

        this.connectToDistributor("127.0.0.1", 9000
                                , (data) => {
                                    console.log("Distributor Notification", data);
                                });
    }

    onRead(socket, data) {

        console.log("onRead", socket, socket.remoteAddress, socket.remotePort, data);
        business.onRequest(socket, data.method, data.uri, data.params
                        , (s, packet) => {
                            socket.write(JSON.stringify(packet) + '¶');
                        });
    }
}

new members();