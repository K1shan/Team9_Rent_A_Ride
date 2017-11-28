$(document).ready(function() {
		
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveType",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {
					 var name = element.name;
					 var id = element.id;

					 $('#selectVehicleTypeAdd').append($('<option>').text(name).attr('value', id));
					 $('#selectVehiceTypeUpdate').append($('<option>').text(name).attr('value', id));
					 $('#selectVehicleTypeDelete').append($('<option>').text(name).attr('value', id));
					 
					 $('#selectVehicleType').append($('<option>').text(name).attr('value', id));
					 
					 $('#selectVehicleVehicleTypeAdd').append($('<option>').text(name).attr('value', id));
					 $('#selectVehicleVehicleTypeUpdate').append($('<option>').text(name).attr('value', id));
					 
					 $('#selectVehicleTypeUpdate').append($('<option>').text(name).attr('value', id));
					 
					 $('#selectHourlyPriceVehicleTypeAdd').append($('<option>').text(name).attr('value', id));
					 $('#selectHourlyPriceAdd').append($('<option>').text(name).attr('value', id));
					 $('#selectHourlyPriceVehicleTypeUpdate').append($('<option>').text(name).attr('value', id));	 
				 });

			},
			error: function(exception){
				alert("exception: " + exception);
			}
		});
});




