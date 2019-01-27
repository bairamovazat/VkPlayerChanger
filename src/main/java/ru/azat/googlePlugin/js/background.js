
var counter = 1;

class BgProcess {

    BgProcess(){
      var serverUrl = "http://127.0.0.1:8080"
    }

    function subscribe(url) {
      var xhr = new XMLHttpRequest();

      xhr.onreadystatechange = function() {
        if (xhr.readyState != 4) return;

        if (xhr.status == 200) {
          onMessage(xhr.responseText);
        } else {
          onError(xhr);
        }

        subscribe(url);
      }
      xhr.open("GET", url, true);
      xhr.send();
    }



}
function ready(){
    setListener();
}

function setListener(){
  chrome.runtime.onMessageExternal.addListener(
    function(request, sender, sendResponse) {
      console.log(sender);
      sendResponse(counter++);
    });
}

function sendMessageOnTab(message, tabId){
    chrome.tabs.sendRequest(tabId,message);
}

function onUpdateReceived(request, sender, sendResponse){
    var i = 0;
}


console.log("listener");
ready();
