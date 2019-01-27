// console.log("change_vk_js.js ready");
// console.log(document.background);
// //getAudioPlayer().play();
// //cancelEvent(event);
// //var mp3 = document.getElementsByClassName("audio_row")[0]
//
// //getAudioPlayer().toggleAudio(mp3, event)
// class VkAudioChanger {
// 	isPlaying() {
// 		return(window.ap.isPlaying());
// 	}
//
// 	isRunning() {
// 		if(window.ap._currentAudio == false) {
// 			return false;
// 		}else{
// 			return true;
// 		}
// 	}
// 	play() {
// 		if(this.isRunning()) {
// 			this.getAudioPlayer().play();
// 			return true;
// 		}else{
// 			return false;
// 		}
//
// 	}
//
// 	stop() {
// 		if(this.isRunning()) {
// 			this.getAudioPlayer().stop();
// 			return true;
// 		}else{
// 			return false;
// 		}
// 	}
//
// 	next() {
// 		if(this.isRunning()) {
// 			this.getAudioPlayer().stop();
// 			return true;
// 		}else{
// 			return false;
// 		}
// 	}
//
// 	prev() {
// 		if(this.isRunning()) {
// 			this.getAudioPlayer().stop();
// 			return true;
// 		}else{
// 			return false;
// 		}
// 	}
//
// 	getAudioPlayer() {
// 		return window.ap;
// 	}
//
// 	getCurrentAudio() {
// 		if(this.isRunning){
// 			return this.getAudioPlayer()._currentAudio;
// 		}else{
// 			return null;
// 		}
// 	}
// }
//
// function setListener() {
//     chrome.extension.onRequest.addListener(function(req){ //обработчик запроса из background
//     	onRequest(req);
//     });
// }
//
// function onRequest(req) {
//     console.log(req)
// }
//
// function sendMessage(message) {
//     var editorExtensionId = "lgadnpdlbimkdkngmjglllclembkpoab";
//     var url =  "chrome-extension://lgadnpdlbimkdkngmjglllclembkpoab/js/change_vk_js.js";
//     chrome.runtime.sendMessage(editorExtensionId, {openUrlInEditor:url},
//     function(response) {
//       if (!response.success){}
//     });
// }
//
// function init() {
// 	var changer = new VkAudioChanger();
//     setListener();
// }
//
// init();