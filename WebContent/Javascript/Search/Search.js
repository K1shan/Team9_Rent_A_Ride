$(document).ready(function() {
	
	$("#searchError").hide();
	
		$('#email-bar').keyup(function(){
			
			$("#searchError").hide();
			
			 var searchField = $('#email-bar').val();
			 var optionn = $('#searchOption').val();
			 
			 var regex = new RegExp(searchField, "i");

			 if(searchField === ""){

				 $('#search').html("");
				 return;
			 }
			
			 if(optionn === 'locations'){
				 	var html = '';
				 
					$.ajax({
						
						url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveLocation",
						type: "GET",
						dataType: "JSON",
						success: function(data) {

							 $.each(data, function(index, element) {
								
								 if ((element.name.search(regex) != -1) || (element.address.search(regex) != -1)) {
									 
									 $("#searchError").hide();
									 
									 var id = element.id;
									 var image = element.path;
									 var ava = element.capacity;
									 var name = element.name;
									 var address = element.address+", "+element.city+", "+element.state;
									 var zip = element.zip;
									 
									 html += '<div class="ui">';
									 html += '<div class="statusbar">';
									 html += '<span id="hour">Product ID ' + id + '</span>';
									 html += '</div>';
									 	html += '<div class="screen">';
								 			html += '<div class="product-des">';
								 				html += '<form id = "formOne" action="CustomerReservation" method="post">';
								 					html += '<div class="product-name">'+name+'</div>';
								 					html += '<div class="product-description">' + address + '</div>';
								 					html += '<div class="product-description">' + zip + '</div>';
								 					html += '<div class="product-description"> CAPACITY: ' + ava + '</div>';
								 					html += '<div class="product-description"> IMAGE PATH: ' + image + '</div>';
						 						html += '</form>';
					 						html += '</div>';
										html += '</div>';
									html += '</div>';
									html += '</div>';
									
								 }else{
									 
									 $("#searchError").show();
								 }
							 });
							 
							 $('#search').html(html);
						},
						error: function(exception){
							alert("exception: " + exception);
						}
					});	
					
					$.ajax({
						
						url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveReservation",
						type: "GET",
						dataType: "JSON",
						success: function(data) {

							 $.each(data, function(index, element) {

								 if ((element.rentalLocation.name.search(regex) != -1) || (element.rentalLocation.address.search(regex) != -1)) {

									 $("#searchError").hide();
									 
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
									
								 }
			
							 });
							 
							 $('#search').html(html);
						},
						error: function(exception){
							alert("exception: " + exception);
						}
					});
					
					$.ajax({
						url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveVehicle",
						type: "GET",
						dataType: "JSON",
						success: function(data) {

							 $.each(data, function(index, element) {
								 
								 if ((element.rentalLocation.name.search(regex) != -1) || (element.rentalLocation.address.search(regex) != -1)) {
									 
									 $("#searchError").hide();
									 
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
								 }
							 });

							 $('#search').html(html);
						},
						error: function(exception){
							alert("exception: " + exception);
						}
					});
					
					
			 } else if(optionn === 'customers'){

				 var html = '';
					
					$.ajax({
						url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveCustomers",
						type: "GET",
						dataType: "JSON",
						success: function(data) {
							
							 $.each(data, function(index, element) {
								 
								 if ((element.firstName.search(regex) != -1) || (element.lastName.search(regex) != -1)) {
									 
									 $("#searchError").hide();
									 
									 var id = element.id;
									 var name = element.firstName;
									 var last = element.lastName;
									 var email = element.userName;
									 var address = element.address;
									 var from = element.createDate
									 var till = element.memberUntil;
									 var status = element.userStatus;
									 
									 html += '<div class="ui">';
									 	html += '<div class="statusbar">';
									 		html += '<span id="hour">Product ID ' + id + '</span>';
									 	html += '</div>';
									 	html += '<div class="screen">';
								 			html += '<div class="product-des">';
								 				html += '<form id = "formOne" action="CustomerReservation" method="post">';
								 					html += '<div class="product-name">'+  name + ' ' + last + '</div>';
								 					html += '<div class="product-description">' + email + '</div>';
								 					html += '<div class="product-description">' + address + '</div>';
								 					html += '<div class="product-description">'+ till + ' - '  + from + '</div>';
								 					html += '<div class="product-description"> Status: ' + status + '</div>';
						 						html += '</form>';
					 						html += '</div>';
				 						html += '</div>';
									html += '</div>';
									html += '</div>';
									
								 }
								 
							 });
								 
							 $('#search').html(html);
						},
						error: function(exception){
							alert("exception: " + exception);
						}
					});
					
					$.ajax({
						
						url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveReservation",
						type: "GET",
						dataType: "JSON",
						success: function(data) {

							 $.each(data, function(index, element) {

								 if ((element.customer.firstName.search(regex) != -1) || (element.customer.lastName.search(regex) != -1)) {

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
								 }
							 });
					
							 $('#search').html(html);
						},
						error: function(exception){
							alert("exception: " + exception);
						}
					});
					
			 } else if (optionn === '') {

				 $("#searchError").show();
			 }
	
		});
});

 
 