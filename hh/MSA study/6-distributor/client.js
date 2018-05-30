'use strict' // strict 모드 사용

const net = require('net');

//클래스 선언
class tcpClient {
    //생성자
    constructor(host, port, onCreate, onRead, onEnd, onError) {
        this.options = {
            host: host,
            port: port
        };
        this.onCreate = onCreate;
        this.onRead = onRead;
        this.onEnd = onEnd;
        this.onError = onError;
    }

    // 접속 처리 함수
    connect() {
        this.client = net.connect(this.options, () => {
            if(this.onCreate) {
                //접속 완료 이벤트 콜백
                this.onCreate(this.options);
            }
        });

        this.client.on('data', (data) => {
            var sz = this.merge ? this.merge + data.toString() : data.toString();
            var arr = sz.split('¶');
            for (var n in arr) {
                
                if (sz.charAt(sz.length - 1) != '¶' && n == arr.length - 1) {
                    this.merge = arr[n];
                    break;
                } else if (arr[n] == "") {
                    break;
                } else {
                    this.onRead(this.options, JSON.parse(arr[n]));
                }
                
            }
        });

        this.client.on('close', () => {
            if (this.onEnd) {
                this.onEnd(this.options);
            }
        });

        this.client.on('error', (err) => {
            if(this.onError) {
                this.onError(this.options, err);
            }
        });
    }

    write(packet) {
        this.client.write(JSON.stringify(packet) + '¶');
    }
}

module.exports = tcpClient; // exports 선언: 외부에서 참조할 수 있도록 exports를 한다.

