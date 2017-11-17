$(document).ready(function() {
		
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveVehicle",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {
					 
					 var id = element.id;
					 var make = element.make;
					 var model = element.model;
					 var tag = element.registrationTag;
					 
					 $('#select1').append($('<option>').text(id + ' - ' +  name).attr('value', id));
					 $('#select2').append($('<option>').text(id + ' - ' +  name).attr('value', id));
					 $('#select3').append($('<option>').text(id + ' - ' +  name).attr('value', id));
					 $('#selectVehicle').append($('<option>').text(tag).attr('value', id));
					 $('#selectVehicleUpdate').append($('<option>').text(id + ' - ' + make + ' - ' + model + ' - ' + tag).attr('value', id));
				 });

			},
			error: function(exception){
				alert("exception: " + exception);
			}
		});
});




