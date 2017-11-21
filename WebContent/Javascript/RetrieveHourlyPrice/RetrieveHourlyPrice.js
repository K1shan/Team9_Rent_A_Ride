$(document).ready(function() {
		
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveHourlyPrice",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {
					 var typeName = element.vehicleType.name;
					 var maxHrs = element.maxHours;
					 var price = element.price;
					 var id = element.id;

					 $('#selectHourlyPriceUpdate').append($('<option>').text(id + ' - ' + typeName + ' - ' + maxHrs + ' - ' + price).attr('value', id));
					 $('#selectHourlyPriceDelete').append($('<option>').text(id + ' - ' + typeName + ' - ' + maxHrs + ' - ' + price).attr('value', id));
				 });
			},
			error: function(exception){
				alert("exception: " + exception);
			}
		});
});