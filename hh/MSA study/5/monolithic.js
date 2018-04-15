const http = require('http'); 
const url = require('url'); //url 모듈 
const querystring = require('querystring'); // querystring 모듈

const members = require('./monolithic_members.js');
const goods = require('./monolithic_goods.js');
const purchases = require('./monolithic_purchases.js');


var server = http.createServer((req, res) =>{
    var method = req.method;                //메서드를 얻어옴
    var uri = url.parse(req.url, true);    
    var pathname = uri.pathname;            //uri 얻어옴
    
    
    if (method === "POST" || method === "PUT") {
        var body = "";
        
        req.on('data', function(data) {
            body += data;
        });
        req.on('end', function() {
            var params;
            //헤더 정보가 json이면 처리
            if (req.headers['content-type'] == "application/json") {
                params =JOSN.parse(body);
                
            } else {
                params = querystring.parse(body);
            }
            
        console.log("if");
        onRequest(res, method, pathname, params);
        });
    } else {
        // GET or DELETE
        console.log("else");
        onRequest(res, method, pathname, uri.query);
    }
    
    
    
}).listen(9090);

function onRequest(res, method, pathname, params) {
    
    
    switch (pathname){
    case "/members":
        members.onRequest(res, method, pathname, params, response);
        break;
    case "/goods":
        goods.onRequest(res, method, pathname, params, response);
        break;
    case "/purchases":
        purchases.onRequest(res, method, pathname, params, response);
        break;
    default:
        res.writeHead(404);
        return res.end();
    }
            
    //res.end("response"); // 모든 요청에 response
}

//JSON 형태로 응답
function response(res, packet) {
    res.writeHead(200, { 'Context-Type' : 'application/json' });
    res.end(JSON.stringifiy(packet));
}