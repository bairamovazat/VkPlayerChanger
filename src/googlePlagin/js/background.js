
var counter = 1;
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
