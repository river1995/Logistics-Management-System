$('docuemnt').ready(function(){
	var userInfo = JSON.parse(localStorage.getItem('user_info'));
	var type = userInfo.type;
	if(type === 3){
		//$('.proxy-tab').css('display','initial');
		$('.proxy').css('display','initial');
	}
	if(type === 2){
		//$('.proxy-tab').css('display','initial');
		$('.proxy-tab').hide();
	}
	render();
	
	
	$('.home-tab').on('click',function(){
		if($(this).data('type') == 1){
			$('.user-thread').show();
		}else if($(this).data('type') == 2){
			$('.user-thread').hide();
		}
		$('#datatable').bootstrapTable('refresh',{query: {'type':$(this).data('type')}});
		
	})
	
	$('#type').change(function(){
		var typeVal = $(this).children('option:selected').val()
		//console.log(countryVal);
		if(typeVal === '1'){
			$('.number').show();
		}else if(typeVal === '2'){
			$('.number').hide();
		}
	});
	
	$('.add-user-btn').on('click',function(){
		var password = $('#password').val(); 
		var confirmPassword = $('#confirm-password').val(); 
		if(password === confirmPassword){
			$.ajax({
				type : 'POST',
				url : '../api/v1.0/admin_add_user',
				data  : {'username':$('#username').val(),'password':password,'type':$('#type').children('option:selected').val(),'phone':$('#phone').val(),'remain_num':$('#remain_num').val()},
				dataType : 'JSON',
				success : function(data){
					console.log("add_logistic:"+JSON.stringify(data));
					if(data.code === 0){
						layer.msg('添加用户成功', {
							  icon: 1,
							  time: 1500 //2秒关闭（如果不配置，默认是3秒）
						  }, function(){
							  location.reload();
						  }); 
					}else if(data.code === 40007){
						layer.msg('用户名已经被使用', {
							  icon: 2,
							  time: 1500 //2秒关闭（如果不配置，默认是3秒）
						  }); 
					}else if(data.code === 40017){
						layer.msg('权限不足', {
							  icon: 2,
							  time: 1500 //2秒关闭（如果不配置，默认是3秒）
						  }); 
					}else{
						$('.confirm-submit').removeAttr('disabled');
					}
				}
			});
		}else{
			layer.msg('两次输入密码不一样',{
				icon:2,
				time:1500
			})
		}
		
	})
	function render(){
		
		
		
		window.operateEvents = {
			    'click .edit': function (e, value, row, index) {
			    	var type = $("#home-tab").data("type");
			    	if(type === 2){
			    		$('.form-group-number').css('display','none');
			    		$('.edit-user').modal('show');
			    	}else{
			    		$('.form-group-number').css('display','initial');
			    		$('.edit-user').modal('show');
			    		$('.edit-user-btn').on('click',function(){
				    		var num = $('#edit_remain_num').val();
				    		$.ajax({
								type : 'POST',
								url : '../api/v1.0/add_remain_no',
								data  : {'user_id' : value ,"remain_num":num},
								dataType : 'JSON',
								success : function(data){
									console.log("add_remain_no:"+JSON.stringify(data));
									if(data.code === 0){
										layer.msg('添加库存成功', {
											  icon: 1,
											  time: 1500 //2秒关闭（如果不配置，默认是3秒）
										  }, function(){
											  location.reload();
										  }); 
									}
								}
							});
				    	})
			    	}
			    	
			    },
			    'click .delete': function (e, value, row, index) {
			    	$.ajax({
						type : 'POST',
						url : '../api/v1.0/disable_user',
						data  : {'user_id' : value},
						dataType : 'JSON',
						success : function(data){
							console.log("disable_user:"+JSON.stringify(data));
							if(data.code === 0){
								layer.msg('删除用户成功', {
									  icon: 1,
									  time: 1500 //2秒关闭（如果不配置，默认是3秒）
								  }, function(){
									  location.reload();
								  }); 
							}
						}
					});
			    }
			};
		$('#datatable').bootstrapTable({
			pagination: true,
			pageSize : 10,
			url: '../api/v1.0/user_list',
			contentType:"application/x-www-form-urlencoded",
			queryParamsType: "limit",
			queryParams: function (params) {
			    return {
			        'offset' : params.offset,
			        'limit' : params.limit,
			        'type':  '1' 
			    }
			},

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
				field:'username'
			},{
				field:'phone'
			},{
				field:'type',
				formatter:function(value,row,index){
					if(value == 1){
						return '普通用户';
					}else if(value == 2){
						return "代理用户";
					}
				},
			},{
				field:'proxy'
			},{
				field:'total'
			},{
				field:'remain_num'
			},{
				field:'create_time'
			},{
				field:'id',
				formatter:function(value,row,index){
					return '<a href="#" class="btn btn-info btn-xs edit" ><i class="fa fa-pencil"></i> 编辑 </a>';
				},
				events : operateEvents
				
				
			}]
		});
	}
})