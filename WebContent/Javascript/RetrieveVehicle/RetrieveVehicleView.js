$(document).ready(function() {
	$("#RetrieveVehicleView").hide();
	$('#viewVehicle').on('click',function(e){
		e.preventDefault();
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveVehicle",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {
					 
					 var id = element.id;
					 var make = element.make;
					 var model = element.model;
					 var year = element.year;
					 var tag = element.registrationTag;
					 var mile = element.mileage;
					 var condition = element.condition;
					 var status = element.status;
					 var lastServiced = element.lastServiced;
					 var vehicleType = element.vehicleType.name;
					 var location = element.rentalLocation.name;
					 
					 html += '<div class="ui">';
					 	html += '<div class="statusbar">';
					 		html += '<span id="hour">Product ID ' + id + '</span>';
					 	html += '</div>';
					 	html += '<div class="screen">';
				 			html += '<div class="product-des">';
				 				html += '<form id = "formOne" action="CustomerReservation" method="post">';
				 					html += '<div class="product-name">'+ make +'</div>';
				 					html += '<div class="product-description">' + model + ' ' + year +'</div>';
				 					html += '<div class="product-description"> Type: ' + vehicleType + '</div>';
				 					html += '<div class="product-description"> Tag: ' + tag + '</div>';
				 					html += '<div class="product-description"> Mileage: '+ mile + '</div>';
				 					html += '<div class="product-description"> Last Serviced: ' + lastServiced + '</div>';
				 					html += '<div class="product-description"> Condition: ' + condition + '</div>';
				 					html += '<div class="product-description"> Status: ' + status + '</div>';
				 					html += '<div class="product-description"> Location: ' + location + '</div>';
		 						html += '</form>';
	 						html += '</div>';
						html += '</div>';
					html += '</div>';
					html += '</div>';
										
				 });

				 $('#RetrieveVehicleView').html(html);
			},
			error: function(exception){
				alert("exception: " + exception);
			}
		});
		
		$("#RetrieveVehicleView").show();
	});
});




