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
					 
					 $('#select1').append($('<option>').text(id + ' - ' +  name).attr('value', id));
					 $('#select2').append($('<option>').text(name).attr('value', id));
					 $('#select3').append($('<option>').text(id + ' - ' +  name).attr('value', id));
					 $('#selectVehicleType').append($('<option>').text(name).attr('value', id));
					 $('#selectVehicleTypeAdd').append($('<option>').text(name).attr('value', id));
					 $('#selectVehicleTypeUpdate').append($('<option>').text(name).attr('value', id));
					 	 
				 });

			},
			error: function(exception){
				alert("exception: " + exception);
			}
		});
});




