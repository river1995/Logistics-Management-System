$(document).ready(function(){
	if (window.saveAs) {
	    window.saveAs(blob, name);
	}else{
	    console.log("saveAs not supported")
	}
	
	getData();
	
	
	$('.print-btn').on('click',function(){
		//var node = document.getElementById('#print-area');
		//var node = document.getElementById('print-area');
		domtoimage.toJpeg(document.getElementById('print-area'), { quality: 0.95 })
	    .then(function (dataUrl) {
	    	
	        var link = document.createElement('a');
	        link.download = 'my-image-name.jpeg';
	        link.href = dataUrl;
	        link.click();
	    });
//		var obj = $('#print-area');
//		var des = "";  
//	    for(var name in obj){  
//	    	des += name + ":" + obj[name] + ";";  
//	     }  
//	    window.dump(des); 
	})
	function getData(){
		var orderId = window.location.href.split('orderId=')[1];
		$.ajax({
			type : 'POST',
			url : '../api/v1.0/logistic_info',
			data  : {"order_id" : orderId},
			dataType : 'JSON',
			success : function(res){
				console.log("print_logistic:"+JSON.stringify(res));
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
					$('.sender-phone').html(data.sender_phone);
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