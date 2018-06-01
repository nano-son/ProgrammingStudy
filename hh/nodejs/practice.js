const http = require('http');

console.log('creating server');
var server = http.createServer( (req, res) => {

    console.log('calling createServer callback function');
    res.writeHead(200, {'Content-Type': 'type/plain'});
    res.write('hello');
    res.end();
   
}).listen(8080, () => {
    console.log('calling listener callback function');
});

console.log('app.listen() executed');
