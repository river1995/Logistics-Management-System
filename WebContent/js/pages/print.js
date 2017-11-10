$(document).ready(function(){
	
	getData();
	
	function getData(){
		var orderId = window.location.href.split('orderId=')[1];
		$.ajax({
			type : 'POST',
			url : '../api/v1.0/logistic_info',
			data  : {"order_id" : orderId},
			dataType : 'JSON',
			success : function(res){
				console.log("add_logistic:"+JSON.stringify(res));
				if(res.code === 0){
					var data = res.data;
					var fromCity = '';
					var fromCountry = '';
					if(data.from_city === '西雅图'){
						fromCity = 'Seattle';
						fromCountry = 'The United States';
					}else if(data.from_city === '米兰'){
						fromCity = 'Milano';
						fromCountry = 'Italy';
					}
					$('.order-seq').html(data.order_seq);
					$('.sender').html(data.sender);
					$('.sender-address').html(data.sender_address);
					$('.from-city').html(fromCity);
					$('.from-country').html(fromCountry);
					$('.contact').html(data.contact);
					$('.phone').html(data.phone);
					$('.contact-address').html(data.contact_address);
					$('.contact-country').html('People\'s Republic of China');
				}
			}
		});
	}
})