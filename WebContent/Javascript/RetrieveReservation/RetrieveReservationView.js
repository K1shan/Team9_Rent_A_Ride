$(document).ready(function() {
	$("#RetrieveReservationView").hide();
	$('#viewReservations').on('click',function(e){
		e.preventDefault();
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveReservation",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {

					 var id = element.id;
					 var pickupTime = element.pickupTime;
					 var rentalLength = element.rentalLength;
					 var cancelled = element.cancelled;
					 var vehicleName = element.vehicleType.name;
					 var location = element.rentalLocation.name;
					 var customerName = element.customer.firstName;
					 var customerLast = element.customer.lastName;
					 var customerEmail = element.customer.userName;
					 
					 html += '<div class="ui">';
					 	html += '<div class="statusbar">';
					 		html += '<span id="hour">Product ID ' + id + '</span>';
					 	html += '</div>';
					 	html += '<div class="screen">';
				 			html += '<div class="product-des">';
				 				html += '<form id = "formOne" action="CustomerReservation" method="post">';
				 					html += '<div class="product-name">'+  customerName + ' ' + customerLast + '</div>';
				 					html += '<div class="product-description">' + customerEmail + '</div>';
				 					html += '<div class="product-description"> Pick Up Date: ' + pickupTime + '</div>';
				 					html += '<div class="product-description"> Length: '+ rentalLength + ' Hrs ' +'</div>';
				 					html += '<div class="product-description"> Status: ' + vehicleName + '</div>';
				 					html += '<div class="product-description"> Location: ' + location + '</div>';
		 						html += '</form>';
	 						html += '</div>';
 						html += '</div>';
					html += '</div>';
					html += '</div>';

				 });
				 
				 $('#RetrieveReservationView').html(html);
			},
			error: function(exception){
				alert("exception: " + exception);
			}
		});
		
		$("#RetrieveReservationView").show();
	});
});


