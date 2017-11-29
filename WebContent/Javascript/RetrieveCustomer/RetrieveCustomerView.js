$(document).ready(function() {
	$("#RetrieveCustomerView").hide();
	$('#viewCustomers').on('click',function(e){
		e.preventDefault();
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveCustomers",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {
					 
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

				 });
				 
				 $('#RetrieveCustomerView').html(html);
			},
			error: function(exception){
				
				alert("exception: " + exception);
			}
		});
		
		$("#RetrieveCustomerView").show();
	});
});


