$(document).ready(function(){
	var userInfo = JSON.parse(document.cookie.split(";")[0].split("=")[1]);
	console.log("userInfo:"+JSON.stringify(userInfo));
	var name = userInfo.username;
	document.getElementById('user-name-left').innerHTML = name;
	document.getElementById('user-name-top').innerHTML = name;
	
})