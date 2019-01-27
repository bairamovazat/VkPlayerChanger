var counter = 1;

function Background() {

    let that = {};

    that.constructor = function () {
        that.authUrl = "/login";
        that.serverUrl = localStorage["server"] || "http://127.0.0.1:8000";
        that.token = localStorage["token"] || null;
        that.popupConnection = PopupConnection();
        that.popupConnection.addHandler(that.popupHandler);
        that.popupConnection.addHandler((data) => {
            console.log(data);
        });
    };

    that.popupHandler = function (message) {
        if (message.command === "auth") {
            that.auth(message.data.login, message.data.password);
        }
    };

    that.auth = function (login, password) {
        axios.post(that.serverUrl + that.authUrl, {"login": login, "password": password})
            .then(function (response) {
                let token = response.data.value;
                that.token = token;
                localStorage["token"] = token;
            })
            .catch(function (error) {
                console.log(error);
            });
    };

    that.constructor();
    return that;
}

function PopupConnection() {
    let that = {};

    that.constructor = function () {
        that.handlers = [];
        that.port = {};
        chrome.extension.onConnect.addListener(function (port) {
            that.port = port;
            that.port.onMessage.addListener(that.accept);
        });
    };

    that.addHandler = function (handler) {
        that.handlers.push(handler);
    };

    that.send = function (message) {
        that.port.postMessage(message);
    };

    that.accept = function (message) {
        that.handlers.forEach(h => {
            h(message);
        })
    };

    that.constructor();
    return that;
}

var background = {};

function main() {
    background = Background();
}

main();
