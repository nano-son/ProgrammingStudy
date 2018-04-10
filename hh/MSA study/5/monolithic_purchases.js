exports.onRequest = function (res, method, pathname, params, cb) {
    
    switch (method) {
    case "POST":
        return register(res, method, pathname, params, (response) => {
            process.nextTick(cb, res, response);
        });
    case "GET":
        return inquiry(res, method, pathname, params, (response) => {
            process.nextTick(cb, res, response);
        });
        default:
            return process.nextTick(cb, res, null);
    }
}