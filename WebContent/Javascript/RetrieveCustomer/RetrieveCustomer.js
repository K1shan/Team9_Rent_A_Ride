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