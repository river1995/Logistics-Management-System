$(document).ready(function(){
	var prov = document.getElementById('gateway-province');
	var city = document.getElementById('gateway-city');
	
	function Logistic(time,address){
		this.time=time;
		this.address=address;
	}
	
	
	
	var current = {
		    prov: '',
		    city: '',
		    country: ''
		};
	
	showUser();
	showProvince();
	showLogisticData();
	
	$('.add-info-btn').on('click',function(){
		$('.confirm-tbody').empty();
		$('#from-city').empty();
		var resetBtn = document.getElementById('reset');
		resetBtn.click();
		
		
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
					$('.add-info').modal('hide');
					var logList = res.data;
					for(var i = 0;i<logList.length;i++){
						$('.confirm-tbody').append('<tr><th scope="row">'+(i+1)+'</th><td><input type="text" class="time" style="width:100%;height:100%;border:0px;" value="'+logList[i].time+'"></td><td><input type="text" value="'+logList[i].address+'" class="address" style="width:100%;height:100%;border:0px;"></td></tr>');
					}
					$('.confirm').modal('show');
					
					$('.confirm-submit').on('click' ,function(){
						var logisticList = new Array();
						var count = $('.time').length;
						for(var i=0;i<count;i++){
							var time = $('.time').eq(i).val();
							var address = $('.address').eq(i).val();
							var logisticNew = new Logistic(time,address);
							logisticList[i] = logisticNew;
						}
						$.ajax({
							type : 'POST',
							url : '../api/v1.0/add_logstic',
							data  : {'from_country' : fromCountry ,'from_city' : fromCity  ,'gateway_city' : gatewayCity ,'expire_time' : expireTime,'logistic_company' : logisticCompany ,'logistic_json' : JSON.stringify(logisticList)},
							dataType : 'JSON',
							success : function(res){
								console.log("add_logistic:"+JSON.stringify(res));
								if(res.code === 0){
									layer.msg('添加物流信息成功', {
										  icon: 1,
										  time: 1500 //2秒关闭（如果不配置，默认是3秒）
									  }, function(){
										  location = location;
									  }); 
								}
							}
						});
						
					});
				}else{
					
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
	
	
	function showLogisticData(){
		$('#datatable').bootstrapTable({
			pagination: true,
			pageSize : 10,
			url: '../api/v1.0/user_list',
			contentType:"application/x-www-form-urlencoded",
			queryParams : {"type" : type},
			method: 'POST',
			responseHandler: function(res) {
            	//console.log(res);
            	if(res.code == 0){
            		return res.data;
            	}else{
            		return {total:0,rows:[]};
            	}
            },
			columns: [
			{
				field:"profile_photo",
				formatter:function(value,row,index){
					return "<img src='http://192.168.2.175:8080/photo/img/upload/"+value+"' class='avatar' alt='Avatar'>";
				}
			},{
				field:'real_name'
			},{
				field:'phone'
			},{
				field:'email'
			},{
				field:'company'
			},{
				field:'id',
				formatter:function(value,row,index){
					return "<a href='#' class='btn btn-primary btn-xs view' data-toggle='modal' data-target='.view-info'><i class='fa fa-folder'></i> View </a>"
			        + "<a href='#' class='btn btn-info btn-xs edit' data-toggle='modal' data-target='.edit-info'><i class='fa fa-pencil'></i> Edit </a>"
			         +"<a href='#' class='btn btn-danger btn-xs delete'><i class='fa fa-trash-o'></i> Delete </a>";
				},
				events : operateEvents
				
				
			}]
		});
	}
	
})