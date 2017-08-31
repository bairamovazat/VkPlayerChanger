function ready(){
	console.log("vk.js ready")
	var script=document.createElement('script');
	script.type='text/javascript';
	script.src=chrome.extension.getURL("js/change_vk_js.js");
	document.body.appendChild(script)
	
}
ready();