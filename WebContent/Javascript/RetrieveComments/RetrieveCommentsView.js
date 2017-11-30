$(document).ready(function() {
	
	$("#RetrieveCommentsView").hide();
	$('#viewComments').on('click',function(e){
		
		e.preventDefault();
		$.ajax({
			
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveComments",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {
					 
					
					 
					 var id = element.id;
					 var comment = element.text;					 
					 var date = element.date;				 
					 var make = element.rental.vehicle.make;
					 var model = element.rental.vehicle.model;
					 var tag = element.rental.vehicle.registrationTag;
					 var first = element.rental.reservation.customer.firstName;
					 var last = element.rental.reservation.customer.lastName;

					 
					 
					 html += '<div class="ui">';
					 	html += '<div class="statusbar">';
					 		html += '<span id="hour">Product ID ' + id + '</span>';
					 	html += '</div>';
					 	html += '<div class="screen">';
				 			html += '<div class="product-des">';
				 				html += '<form id = "formOne" action="" method="post">';
				 					html += '<div class="product-name">'+  first + ' ' + last + '</div>';
				 					html += '<div class="product-name">'+  make + ' ' + model + '</div>';
				 					html += '<div class="product-description">' + date + '</div>';
				 					html += '<div class="product-description">' + comment + '</div>';
				 					html += '<div class="product-description"> Status: ' + tag + '</div>';
		 						html += '</form>';
	 						html += '</div>';
 						html += '</div>';
					html += '</div>';
					html += '</div>';

				 });
				 
				 $('#RetrieveCommentsView').html(html);
			},
			error: function(exception){
				
				alert("exception: " + exception);
			}
		});
		
		$("#RetrieveCommentsView").show();
	});
});
