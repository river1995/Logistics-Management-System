$(document).ready(function(){
	var prov = document.getElementById('gateway-city');
	
	showUser();
	showProvince();
	
	function showUser(){		
		var userInfo = JSON.parse(document.cookie.split(";")[0].split("=")[1]);
		console.log("userInfo:"+JSON.stringify(userInfo));
		var name = userInfo.username;
		document.getElementById('user-name-left').innerHTML = name;
		document.getElementById('user-name-top').innerHTML = name;
	}
	
	function showProvince(){
		var len = provice.length;
		
	    for (var i = 0; i < len; i++) {
	        var provOpt = document.createElement('option');
	        provOpt.innerText = provice[i]['name'];
	        provOpt.value = i;
	        //alert(provOpt);
	        prov.appendChild(provOpt);
	    }
	}
	
	function showCity(obj) {
	    var val = obj.options[obj.selectedIndex].value;
	    if (val != current.prov) {
	        current.prov = val;
	        addrShow.value = '';
	        btn.disabled = true;
	    }
	    //console.log(val);
	    if (val != null) {
	        city.length = 1;
	        var cityLen = provice[val]["city"].length;
	        for (var j = 0; j < cityLen; j++) {
	            var cityOpt = document.createElement('option');
	            cityOpt.innerText = provice[val]["city"][j].name;
	            cityOpt.value = j;
	            city.appendChild(cityOpt);
	        }
	    }
	}
	
	
})