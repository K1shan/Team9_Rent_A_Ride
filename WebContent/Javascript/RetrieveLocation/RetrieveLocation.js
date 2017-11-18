$(document).ready(function() {
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveLocation",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {
					 
					 var id = element.id;
					 var image = element.path;
					 var ava = element.capacity;
					 var name = element.name;
					 var address = element.address+", "+element.city+", "+element.state;
					 var zip = element.zip;
					 
					 html += '<div class="ui">';
					 	html += '<div class="screen">';
					 		html += '<div class="shop">';
					 			html +='<div class="product-img"><img id = "image" src="' + image + '"></div>';
				 			html += '</div>';
				 			html += '<div class="product-des">';
				 				html += '<form id = "formOne" action="customerCar" method="post">';
				 					html += '<div class="product-price" id="price">' +  ava + '<div class="sub"> AVAIILABILITY</div></div>';
				 					html += '<div class="product-name">' + name + '</div>';
				 					html += '<div class="product-description">' + address + '</div>';
				 					html += '<div class="product-description">' + zip + '</div>';
				 					html += '<div class="product-cta">';
				 						html += '<input type="submit" class = "cta" value="RENT" />';
			 						html += '</div>';
		 						html += '</form>';
	 						html += '</div>';
 						html += '</div>';
					html += '</div>';
					html += '</div>';
					
					$('#selectLocation').append($('<option>').text(name).attr('value', id));
					$('#selectLocationAdd').append($('<option>').text(name).attr('value', id)); 
					$('#selectLocationUpdate').append($('<option>').text(name).attr('value', id));
					$('#selectLocationDelete').append($('<option>').text(name).attr('value', id)); 

				 });
				 
				 $('#main').html(html);
			},
			error: function(exception){
				alert("exception: " + exception);
			}
		});
});




