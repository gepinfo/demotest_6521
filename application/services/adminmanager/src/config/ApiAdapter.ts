import * as fetch from 'node-fetch';

export class ApiAdapter {

    public post(url, data) {
        return new Promise((resolve, reject) => {
            fetch(url, { method: 'POST', body: JSON.stringify(data), headers: { 'Content-Type': 'application/json' } }).then((response) => {
                this.sendResponse(resolve, reject, response, null);
            }).catch(error => {
                this.sendResponse(resolve, reject, null, error);
            })
        });
    }

    public get(url) {
        return new Promise((resolve, reject) => {
            fetch(url).then((response) => {
                this.sendResponse(resolve, reject, response, null);
            }).catch(error => {
                this.sendResponse(resolve, reject, null, error);
            })
        });
    }

    public put(url, data) {
        return new Promise((resolve, reject) => {
            fetch(url, { method: 'PUT', body: JSON.stringify(data), headers: { 'Content-Type': 'application/json' } }).then((response) => {
                this.sendResponse(resolve, reject, response, null);
            }).catch(error => {
                this.sendResponse(resolve, reject, null, error);
            })
        });
    }

    public delete(url) {
        return new Promise((resolve, reject) => {
            fetch(url, {method: 'DELETE'}).then((response) => {
                this.sendResponse(resolve, reject, response, null);
            }).catch(error => {
                this.sendResponse(resolve, reject, null, error);
            })
        });
    }

    public sendResponse(resolve, reject, response, error) {
        if (response !== null) {
            switch(true) {
                case response.status === 200:
                    resolve({
                        response,
                        code: response.status,
                        message: " request has succeeded"
                    });
                    break;
                case response.status === 201 :
                    resolve({
                        response,
                        code: response.status,
                        message: "request has succeeded and a new resource has been created"
                    });
                    break;
                case response.status === 202:
                    resolve({
                        response,
                        code: response.status,
                        message: "request has been received but not yet acted upon"
                    });
                    break;
                case response.statusCode === 203:
                    resolve({
                        response,
                        code: response.status,
                        message: "non authoritative info"
                    });
                    break;
                case response.status === 204:
                    resolve({
                        response,
                        code: response.status,
                        message: "no conent"
                    });
                    break;
                case response.status === 205:
                    resolve({
                        response,
                        code: response.status,
                        message: "reset content"
                    });
                    break;
                case response.status === 206:
                    resolve({
                        response,
                        code: response.status,
                        message: "partial content"
                    });
                    break;
                case response.status === 400:
                    reject({
                        code: response.status,
                        message: "bad request"
                    });
                    break;
                case response.status === 401:
                    reject({
                        code: response.status,
                        message: "unauthorized"
                    });
                    break;
                case response.status === 402:
                    reject({
                        code: response.status,
                        message: "Payment Required"
                    });
                    break;
                case response.status === 403:
                    reject({
                        code: response.status,
                        message: "forbidden"
                    });
                    break;
                case response.status === 404:
                    reject({
                        code: response.status,
                        message: "not found"
                    });
                    break;
                case response.status === 405:
                    reject({
                        code: response.status,
                        message: "method not allowed"
                    });
                    break;
                case response.status === 406:
                    reject({
                        code: response.status,
                        message: "not acceptable"
                    });
                    break;
                case response.status === 407:
                    reject({
                        code: response.status,
                        message: "proxy authentication required"
                    });
                    break;
                case response.status === 408:
                    reject({
                        code: response.status,
                        message: "request timeout"
                    });
                    break;
                case response.status === 500:
                    reject({
                        code: response.status,
                        message: "internal server error"
                    });
                    break;
                case response.status === 501:
                    reject({
                        code: response.status,
                        message: "request method is not supported by the server and cannot be handled"
                    });
                    break;
                case response.status === 502:
                    reject({
                        code: response.status,
                        message: "bad gateway"
                    });
                    break;
                case response.status === 503:
                    reject({
                        code: response.status,
                        message: "service available"
                    });
                    break;
                case response.status === 504:
                    reject({
                        code: response.status,
                        message: "gateway timeout"
                    });
                    break;
                case response.status === 505:
                    reject({
                        code: response.status,
                        message: "HTTP version used in the request is not supported by the server"
                    });
                    break;
                default:
                    reject(error);
            }
        } else {
            if (error.port !== undefined && response.port !== null) {

                let errormsg = {
                    error: "Microservice Down",
                    service_port: error.port,
                };
                console.error(errormsg)
                reject(errormsg);
            }
        }
    }

}
