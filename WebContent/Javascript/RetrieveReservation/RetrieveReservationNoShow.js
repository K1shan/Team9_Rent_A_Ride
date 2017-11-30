$(document).ready(function() {
		
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveReservationNoShow",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				
				 $.each(data, function(index, element) {
					 
					 var id = element.id;
					 var name = element.customer.firstName+" "+element.customer.lastName;

					 $('#selectReservationNoShowUpdate').append($('<option>').text(id + ' - ' + name).attr('value', id));	 
				 });
			},
			error: function(exception){
				alert("exception: " + exception);
			}
		});
});