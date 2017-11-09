$(document).ready(function() {
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RegularLocation",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {

					 var image = element.photo;
					 var ava = element.availability;
					 var city_state = element.city_state;
					 var address = element.address;
					 var city_state_zip = element.city_state_zip;
					 
					 html += '<div class="ui">';
					 	html += '<div class="screen">';
					 		html += '<div class="shop">';
					 			html +='<div class="product-img"><img id = "image" src="' + image + '"></div>';
				 			html += '</div>';
				 			html += '<div class="product-des">';
				 				html += '<form id = "formOne" action="customerCar" method="post">';
				 					html += '<div class="product-price" id="price">' +  ava + '<div class="sub">AVAIILABILITY</div></div>';
				 					html += '<div class="product-name">' + city_state + '</div>';
				 					html += '<div class="product-description">' + address + '</div>';
				 					html += '<div class="product-description">' + city_state_zip + '</div>';
				 					html += '<div class="product-cta">';
				 						html += '<input type="submit" class = "cta" value="RENT" />';
			 						html += '</div>';
		 						html += '</form>';
	 						html += '</div>';
 						html += '</div>';
					html += '</div>';
					html += '</div>';
						 
				 });
				
				 
				 $('#main').html(html);
			},
			error: function(exception){
				alert("exception: " + exception);
			}
		});
});

