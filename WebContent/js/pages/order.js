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
	
	$('.add-submit').on('click',function(){
		var fromCountry = $('#from-country').children('option:selected').val();
		var fromCity = $('#from-city').children('option:selected').html();
		//var gatewayProvince = $('#gateway-province').children('option:selected').html();
		var gatewayCity = $('#gateway-city').children('option:selected').html();
		var expireTime = $('#expire-time').val();
		var logisticCompany = $('#logistic-type').val();
		$.ajax({
			type : 'POST',
			url : '../api/v1.0/generate_logistic_info',
			data  : {'from_country' : fromCountry ,'from_city' : fromCity  ,'gateway_city' : gatewayCity ,'expire_time' : expireTime,'logistic_company' : logisticCompany},
			dataType : 'JSON',
			success : function(res){
				console.log(res);
				if(res.code === 0){
					
				}else{
					layer.msg('deleted success', {
						  icon: 1,
						  time: 1500 //2秒关闭（如果不配置，默认是3秒）
					  }, function(){
						  $('#datatable').bootstrapTable('refresh');
					  }); 
				}
			}
		});
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
		if(countryVal === 'us'){
			$('#from-city').append("<option value='0' class='us-city'>西雅图</option>");
		}else if(countryVal === 'ita'){
			$('#from-city').append("<option value='0' class='ita-city'>米兰</option>");
		}
	});
	
	
})