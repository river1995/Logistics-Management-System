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
	
	showLogisticData();
	//showUser();
	showProvince();
	
	$('.add-info-btn').on('click',function(){
		$('.confirm-tbody').empty();
		$('#from-city').empty();
		var resetBtn = document.getElementById('reset');
		resetBtn.click();
		
		
	});
	
	
	
	
	$('.add-submit').on('click',function(){
		
		$('.confirm-tbody').empty();
		var contact = $('#contact').val();
		var phone = $('#phone').val();
		var sender = $('#sender').val();
		var senderAddress = $('#sender-address').children('option:selected').html();
		var contactAddress = $('#contact-address').val();
		var fromCountry = $('#from-country').children('option:selected').val();
		var fromCity = $('#from-city').children('option:selected').html();
		var senderPhone = $('#sender-phone').val();
		//var gatewayProvince = $('#gateway-province').children('option:selected').html();
		var gatewayCity = $('#gateway-city').children('option:selected').html();
		//var expireTime = $('#expire-time').val();
		//alert(senderPhone);
		$.ajax({
			type : 'POST',
			url : '../api/v1.0/generate_logistic_info',
			data  : {'from_country' : fromCountry ,'from_city' : fromCity  ,'gateway_city' : gatewayCity },
			dataType : 'JSON',
			success : function(res){
				console.log(res);
				if(res.code === 0){
					$('.add-info').modal('hide');
					var logList = res.data;
					for(var i = 0;i<logList.length;i++){
						$('.confirm-tbody').append('<tr><th scope="row">'+(i+1)+'</th><td><input type="text" class="add-time" style="width:100%;height:100%;border:0px;" value="'+logList[i].time+'"></td><td><input type="text" value="'+logList[i].address+'" class="add-address" style="width:100%;height:100%;border:0px;"></td></tr>');
					}
					$('.confirm').modal('show');
					
					$('.confirm-submit').on('click' ,function(){
						$('.confirm-submit').attr('disabled');
						var logisticList = new Array();
						var count = $('.add-time').length;
						var gatewayTime = $('.add-time').eq(count-1).val();
						for(var i=0;i<count;i++){
							var time = $('.add-time').eq(i).val();
							var address = $('.add-address').eq(i).val();
							var logisticNew = new Logistic(time,address);
							logisticList[i] = logisticNew;
						}
						$.ajax({
							type : 'POST',
							url : '../api/v1.0/add_logstic',
							data  : {'phone':phone,'contact_address':contactAddress,'sender-phone':senderPhone,'gateway_time':gatewayTime,'contact_address':contactAddress,'contact':contact,'phone':phone,'sender':sender,'sender_address':senderAddress,'from_country' : fromCountry ,'from_city' : fromCity  ,'gateway_city' : gatewayCity ,'logistic_json' : JSON.stringify(logisticList)},
							dataType : 'JSON',
							success : function(data){
								console.log("add_logistic:"+JSON.stringify(data));
								if(data.code === 0){
									layer.msg('添加物流信息成功', {
										  icon: 1,
										  time: 1500 //2秒关闭（如果不配置，默认是3秒）
									  }, function(){
										  location.reload();
									  }); 
								}else{
									$('.confirm-submit').removeAttr('disabled');
								}
							}
						});
						
					});
				}else if(res.code === 40007){
					layer.msg('您的库存不足，无法添加', {
						  icon: 2,
						  time: 1500 //2秒关闭（如果不配置，默认是3秒）
					  }, function(){
						 
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
	$('#from-city').change(function(){
		//alert($(this).children('option:selected').val());
		if($(this).children('option:selected').val() === 'seattle'){
			console.log('执行选择西雅图地址');
			$('#sender-address').val('seattle');
		}else if($(this).children('option:selected').val() === 'newyork'){
			$('#sender-address').val('newyork');
		}else if($(this).children('option:selected').val() === 'milano'){
			$('#sender-address').val('milano');
		}
	});
	$('#from-country').change(function(){
		$('#from-city').empty();
		var countryVal = $(this).children('option:selected').val()
		console.log(countryVal);
		if(countryVal === 'us'){
			$('#from-city').append("<option>请选择发货城市</option><option value='seattle' class='us-city'>西雅图</option><option value='newyork' class='us-city'>纽约</option>");
		}else if(countryVal === 'ita'){
			$('#from-city').append("<option>请选择发货城市</option><option value='milano' class='ita-city'>米兰</option>");
		}
	});
	
	
	
	function showLogisticData(){
		window.operateEvents = {
			    'click .view': function (e, value, row, index) {
			    	$('.view-tbody').empty();
			    	console.log($('.view-tbody').val());
			    	NProgress.start();
			    	$.ajax({
						type : 'POST',
						url : '../api/v1.0/logistic_status_list',
						data  : {"order_id":value},
						cache : 'false',
						dataType : 'JSON',
						success : function(res){
							console.log("status_list:"+JSON.stringify(res))
							var logList = res.data;
							for(var i = 0;i<logList.length;i++){
								$('.view-tbody').append('<tr><th scope="row">'+(i+1)+'</th><td><input type="text" class="time" style="width:100%;height:100%;border:0px;" value="'+logList[i].time+'"></td><td><input type="text" value="'+logList[i].address+'" class="address" style="width:100%;height:100%;border:0px;"></td></tr>');
							}
							$('.view-info').modal('show');
							NProgress.done();
							
						}
					});
			    },
			    'click .print': function (e, value, row, index) {
			    	window.location.href = "./print.html?orderId="+value;
			    },
			    'click .edit': function (e, value, row, index) {
			    	$('.edit-info').modal('show');
			    	$('.edit-submit').on('click' ,function(){
			    		//alert('run test');
			    		
			    		var logisticCompany = $('#company-select').children('option:selected').html();
			    		var logisticNo = $('#logistic-no').val();
			    		$.ajax({
			    			type : 'POST',
			    			url : '../api/v1.0/add_logistic_no',
			    			data  : {"order_id":value ,'logistic_no':logisticNo ,"logistic_company":logisticCompany},
			    			dataType : 'JSON',
			    			success : function(res){
			    				console.log("添加单号:"+JSON.stringify(res));
			    				if(res.code === 0){
			    					
			    					layer.msg('添加物流信息成功', {
			    						icon: 1,
			    						time: 1500 //2秒关闭（如果不配置，默认是3秒）
			    					}, function(){
			    						location.reload();
			    					}); 
			    				}
			    			}
			    		});
			    		console.log(logisticCompany+"/"+logisticNo);
			    		
			    	});
			    }
			};
		
		$('#datatable').bootstrapTable({
			pagination: true,
			pageSize : 10,
			url: '../api/v1.0/logistic_list',
			contentType:"application/x-www-form-urlencoded",
			queryParamsType: "limit",
			sidePagination: "server",
			//smartDisplay:false,
			method: 'POST',
			responseHandler: function(res) {
            	console.log(res);
            	if(res.code == 0){
            		return {
            			"rows": res.data.rows,
            			"total": res.data.total
            		};
            	}else{
            		return {total:0,rows:[]};
            	}
            },
			columns: [
			
			{
				field:'order_seq'
			},{
				field:'contact'
			},{
				field:'sender'
			},{
				field:'from_city'
			},{
				field:'gateway_city'
			},{
				field:'finish_time'
			},{
				field:'logistic_company'
			},{
				field:'id',
				formatter:function(value,row,index){
					return "<a href='#' class='btn btn-primary btn-xs view clear-view' data-toggle='modal' ><i class='fa fa-folder'></i> 查看 </a>"+
					"<a href='#' class='btn btn-primary btn-xs print' style='color: #fff;background-color: #5bc0de;border-color: #46b8da;'><i class='fa fa-print' ></i> 打印 </a>"+
					"<a href='#' class='btn btn-primary btn-xs edit'  style='color: #fff;background-color: #26B99A;border-color: #46b8da;'><i class='fa fa-plus'></i> 绑定 </a>";
				},
				events : operateEvents
				
				
			}]
		});
		
		$('.search-btn').on('click',function(){
			
			var seq = $('.search-text').val();
			if(seq == ''){
				
			}else{
				
				$.ajax({
	    			type : 'POST',
	    			url : '../api/v1.0/logistic_info_orderseq',
	    			data  : {"order_seq":seq},
	    			dataType : 'JSON',
	    			success : function(res1){
	    				console.log("根据单号查询:"+JSON.stringify(res1));
	    				if(res1.code === 0){
	    					
	    					$('#datatable').bootstrapTable('removeAll');
	    					$('#datatable').bootstrapTable('insertRow',{index:0,row:res1.data});
	    				}
	    			}
	    		});
			}
			
		})
	}
	
})