$(document).ready(function(){
	var prov = document.getElementById('gateway-province');
	var city = document.getElementById('gateway-city');
	
	var current = {
		    prov: '',
		    city: '',
		    country: ''
		};
	
	showUser();
	showProvince();
	
	$('.add-info').on('click',function(){
		$('.buttonPrevious').click();
		
	});
	
	$('.buttonNext').on('click',function(){
		//alert('run test');
		SmartWizard.prototype.goForward;
	})
	
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
	
	$('#gateway-province').change(function(){
		showCity(this);
	});
	$('#from-country').change(function(){
		$('#from-city').empty();
		var countryVal = $(this).children('option:selected').val()
		console.log(countryVal);
		if(countryVal === '0'){
			$('#from-city').append("<option value='0' class='us-city'>西雅图</option>");
		}else if(countryVal === '1'){
			$('#from-city').append("<option value='0' class='ita-city'>米兰</option>");
		}
	});
	
	
})