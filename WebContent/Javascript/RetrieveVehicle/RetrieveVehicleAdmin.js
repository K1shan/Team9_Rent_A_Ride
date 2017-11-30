$(document).ready(function() {
	var html = ''
		
	$.ajax({
		url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveVehicle",
		type: "GET",
		dataType: "JSON",
		success: function(data) {
			html = '';
			var counter = 1;
			 $.each(data, function(index, element) {
				 var id = element.id;
				 var make = element.make;
				 var model = element.model;
				 var tag = element.registrationTag;
				 var year = element.year;
				 var address = element.rentalLocation.address;
				 var city = element.rentalLocation.city;
				 var state = element.rentalLocation.state;
				 var zip = element.rentalLocation.zip;
				 var path = element.vehicleType.path;
				 
				 html += '<div class="ui">';
				 	html += '<div class="screen">';
				 		html += '<div class="shop">';
				 		html +='<div class="product-img"><img id = "image" src='+path+'></div>';
			 			html += '</div>';
			 			html += '<div class="product-des">';
			 				html += '<form id = "formOne" action="CustomerReservation" method="post">';
			 					html += '<div class="product-price" id="price">' +  year + '</div>';
			 					html += '<div class="product-name">'+  make + ' ' + model + '</div>';
			 					html += '<div class="product-description">' + address + '</div>';
			 					html += '<div class="product-description">' +  city + ', ' + state + ', ' + zip + '</div>';
			 					html += '<div class="product-cta">';
			 					html += '<input type="hidden" name="locationId" value=' +id+ '	>';
		 						html += '<input type="submit" class = "cta" value="MAKE RESERVATION" />';
		 						html += '</div>';
	 						html += '</form>';
	 						html += '<form id = "formOne" action="ViewVehicle" method="post">';
		 						html += '<div class="product-cta">';
		 							html += '<input type="hidden" name="locationId" value=' +id+ '	>';
		 						html += '<input type="submit" class = "cta" value="VIEW VEHICLE" />';
	 						html += '</div>';
 						html += '</form>';
 						html += '</div>';
					html += '</div>';
				html += '</div>';
				html += '</div>';
				 
				 $('#select1').append($('<option>').text(id + ' - ' +  name).attr('value', id));
				 $('#select2').append($('<option>').text(id + ' - ' +  name).attr('value', id));
				 $('#select3').append($('<option>').text(id + ' - ' +  name).attr('value', id));
				 $('#selectVehicle').append($('<option>').text(tag).attr('value', id));
				 $('#selectVehicleUpdate').append($('<option>').text(id + ' - ' + make + ' - ' + model + ' - ' + tag).attr('value', id));
				 $('#selectVehicleDelete').append($('<option>').text(id + ' - ' + make + ' - ' + model + ' - ' + tag).attr('value', id));
				 counter++;
			 });

			 $('#main').html(html);
		},
		error: function(exception){
			alert("exception: " + exception);
		}
	});
});




