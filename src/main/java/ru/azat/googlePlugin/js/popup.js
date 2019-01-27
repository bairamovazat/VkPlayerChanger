function Popup() {
    let that = {};

    that.constructor = function () {
        that.backgroundConnection = BackgroundConnection();
    };

    that.auth = function (login, password) {
        that.backgroundConnection.send({
            command: "auth",
            data: {
                login: login,
                password: password
            }
        });
    };

    that.constructor();
    return that;
};

function BackgroundConnection() {
    let that = {};

    that.constructor = function () {
        that.handlers = [];
        that.port = {};

        that.port = chrome.extension.connect({
            name: "Sample Communication"
        });

        that.port.onMessage.addListener(that.accept);
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

function formSubmit(event) {
    popup.auth(document.forms["loginForm"].login.value, document.forms["loginForm"].password.value);
    return false;
}

var popup = {};

function main() {
    document.getElementById("loginButton").onclick = formSubmit;
    popup = Popup();
}

document.addEventListener("DOMContentLoaded", main);