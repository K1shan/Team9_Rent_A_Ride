$(document).ready(function() {
	$("#RetrieveCustomerView").hide();
	$('#viewCustomers').on('click',function(e){
		alert("he");
		e.preventDefault();
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveCustomers",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {
					 
					 var id = element.id;
					 var image = 'images/user-256.png';
					 var name = element.firstName;
					 var last = element.lastName;
					 var email = element.userName;
					 var address = element.address;
					 var member = element.memberUntil;
					 
					 html += '<div class="ui">';
					 	html += '<div class="statusbar">';
					 		html += '<span id="hour">Product ID ' + id + '</span>';
					 	html += '</div>';
					 	html += '<div class="screen">';
					 		html += '<div class="shop">';
					 			html +='<div class="product-img"><img id = "image" src="' + image + '"></div>';
				 			html += '</div>';
				 			html += '<div class="product-des">';
				 				html += '<form id = "formOne" action="CustomerReservation" method="post">';
				 					html += '<div class="product-price" id="price">' + email +'</div>';
				 					html += '<div class="product-name">'+  name + ' ' + last + '</div>';
				 					html += '<div class="product-description">' + address + '</div>';
				 					html += '<div class="product-description">' + member + '</div>';
				 					html += '<div class="product-cta">';
				 					html += '<input type="hidden" name="locationId" value=' +id+ '	>';
			 						html += '</div>';
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


