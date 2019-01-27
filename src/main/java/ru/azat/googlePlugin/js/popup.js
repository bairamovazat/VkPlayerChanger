
function ready(){
  console.log("running ready");
  document.getElementById("submit").onclick = function(){formSubmit()};
}

function formSubmit(){

  window.postMessage({ type: "FROM_PAGE", text: "Hello from the webpage!" }, "*");
  console.log("running formSubmit");
  return false;
}

document.addEventListener("DOMContentLoaded", ready);

