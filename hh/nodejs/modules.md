# Nodejs  HTTP Module

``` javascript
var http = require('http');
```

### 1. createServer()

#### Usage
``` javascript
http.createServer(requestlistener);
```

#### Example
``` javascript
var http = require('http');
http.createServer((req, res) => {
    res.writeHead(200, {'Content-Type': 'type/plain'});
    res.write('hello');
    res.end();
}).listen(8080);

```


# Nodejs URL Module

``` javascript
var url = require('url');
```

#### Example
``` javascript

var url = require('url');
var adr = 'http://localhost:8080/default.html?year=2017&month=february';

var q = url.parse(adr, true);

console.log(q.host); // localhost:8080
console.log(q.pathname); // /default
console.log(q.search); // ?year=2017&month=february

q.query // year:2017, month:february
console.log(q.query.month); // february

```

# Nodejs QueryString Module

``` javascript

var querystring = require('querystring');


```

#### Example

``` javascript
var params = querystring.parse(body);
console.log(params);
/*
{
    key1: 'value_example1',
    key2: 'value_example2'
}
*/
```

# Nodejs net Module

``` javascript
var net = require('net');
```

### 기본적인 TCP 서버 구현

#### Example

``` javascript
// tcp_server.js
var net = require('net');
var port = 5000;
var server = net.createServer();

//TCP서버 접속 대기
server.on('listening', function() {
    console.log('listening...');
});

//TCP 접속 완료
server.on('connection', function(socket) {
    console.log('connection...');

    socket.on('end', function() {
        console.log('Client socket close...');
        setTimeout(function() {
            server.close();
        }, 5000);
    });

    socket.on('data', function(data) {
        console.log('data:'+data);
        socket.write('echo:' + data);
    });
})

server.on('close', function() {
    console.log('server closed...');
})

server.on('error', function(err) {
    console.log('error'+ err.message);
})

server.listen(port);
```

###  기본적인 TCP 클라 구현

#### Example

``` javascript
// tcp_client.js
var net = require('net');

var client = net.connect({port:5000}, function() {
    console.log('connected');

    client.write('heelo', function() {
        //client.end();
    });
});

client.on('data', function(data) {
    console.log('from server:', data);
    //client.end();
});

client.on('end',m function(){
    console.log('disconnected...');
});

```