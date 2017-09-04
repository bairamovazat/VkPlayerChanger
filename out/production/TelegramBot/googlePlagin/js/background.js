function ready(){
	var newClass = document.getElementsByClassName("top_audio_player_btn_icon");
	console.log("getElementsByClassName вызвался")
	console.log(newClass);
}
document.addEventListener("DOMContentLoaded", ready);
ready();