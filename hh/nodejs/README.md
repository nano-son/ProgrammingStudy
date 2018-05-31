## Nodejs  HTTP Module

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


## Nodejs URL Module

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

## Nodejs QueryString Module

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
