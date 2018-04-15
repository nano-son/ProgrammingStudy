const mysql = require('mysql')  // mysql 모듈 참조
const conn = {
    host : 'localhost',
    user: 'micro',
    password: 'service',
    database: 'monolithic'
};


// 이제 monolithic.js 에서 이 함수의 onRequest를 호출 할 수 있게 된다.
exports.onRequest = function (res, method, pathname, params, cb) {
    
    swtich (method) {
        
        case "POST":
            return register(method, pathname, params, (response) => {
                process.nextTick(cb, res, response);
            });
        case "GET":
            return inquiry(method, pathname, params, (response) => {
                process.nextTick(cb, res, response);
            });
        case "DELETE":
            return unregister(method, pathname, params, (response) => {
                process.nextTick(cb, res, response);
            });
        default:
            return process.nextTick(cb, res, null);
    }
}

function register(method, pathname, params, cb) {
    var response = {
            errorcode: 0,
            eorrormessage: "success"
    };
    
    if (params.name == null || params.category == null || params.price == null ||
            params.description == null ){
        response.errorcode = 1;
        response.errormessage = "Invalid Parameters";
        cb(response);
    } else {
        var connection = mysql.createConnection(conn);
        connection.connect();
        connection.query("insert into goods(name, category, price, description) " +
        		"value(?,?,?,?)",
        		[params.name, params.category, params.price, params.description],
        		(error, results, fields) => {
        		    if (error) {
        		        response.errorcode = 1;
        		        response.errormessage = error;
        		    }
        		    cb(response);
        		});
        conncection.end();
    }
}