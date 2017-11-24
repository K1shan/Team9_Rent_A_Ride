$(document).ready(function() {
		
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveLocationVehicleType",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {
					 var name = element.name;
					 var id = element.id;

					 $('#selectReservationVehicleTypeAdd').append($('<option>').text(name).attr('value', id));	 
				 });

			},
			error: function(exception){
				alert("exception: " + exception);
			}
		});
});




