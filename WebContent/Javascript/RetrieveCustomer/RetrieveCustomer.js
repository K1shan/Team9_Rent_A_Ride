$(document).ready(function() {
		
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveCustomers",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {
					 
					 var id = element.id;
					 var fname = element.firstName;
					 var lname = element.lastName;
					 var email = element.email
					 var status = element.userStatus;

					 html += '<div class="ui">';
					 	html += '<div class="screen">';
					 		html += '<div class="shop">';
					 			html +='<div class="product-img"><img id = "image" src="/city/Atlanta.png"></div>';
				 			html += '</div>';
				 			html += '<div class="product-des">';
				 				html += '<form id = "formOne" action="CustomerReservation" method="post">';
				 					html += '<div class="product-price" id="price">' +  ava + '<div class="sub"> AVAILABILITY</div></div>';
				 					html += '<div class="product-name">'+name+'</div>';
				 					html += '<div class="product-description">' + address + '</div>';
				 					html += '<div class="product-description">' + zip + '</div>';
				 					html += '<div class="product-cta">';
				 					html += '<input type="hidden" name="locationId" value=' +id+ '	>';
			 						html += '<input type="submit" class = "cta" value="MAKE RESERVATION" />';
			 						html += '</div>';
		 						html += '</form>';
	 						html += '</div>';
						html += '</div>';
					html += '</div>';
					html += '</div>';
					 
					 
					 $('#select1').append($('<option>').text(id + ' - ' +  name).attr('value', id));
					 $('#select2').append($('<option>').text(id + ' - ' +  name).attr('value', id));
					 $('#select3').append($('<option>').text(id + ' - ' +  name).attr('value', id));
					 $('#selectCustomerUpdate').append($('<option>').text(id + ' - ' + email + ' - ' + status).attr('value', id));
				 });

			},
			error: function(exception){
				alert("exception: " + exception);
			}
		});
});