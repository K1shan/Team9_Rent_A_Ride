$(document).ready(function() {
	$("#RetrieveLocationView").hide();
	$('#viewLocation').on('click',function(e){
		e.preventDefault();
		$.ajax({
			url: "http://localhost:8080/Team9_Rent_A_Ride/RetrieveLocation",
			type: "GET",
			dataType: "JSON",
			success: function(data) {
				var html = '';
				
				 $.each(data, function(index, element) {
					 
					 var id = element.id;
					 var name = element.name;
					 var address = element.address+", "+element.city+", "+element.state;
					 var zip = element.zip;
					 var image = element.path;
					 var ava = element.capacity;

					 html += '<div class="ui">';
					 html += '<div class="statusbar">';
					 html += '<span id="hour">Product ID ' + id + '</span>';
					 html += '</div>';
					 	html += '<div class="screen">';
				 			html += '<div class="product-des">';
				 				html += '<form id = "formOne" action="CustomerReservation" method="post">';
				 					html += '<div class="product-name">'+name+'</div>';
				 					html += '<div class="product-description">' + address + '</div>';
				 					html += '<div class="product-description">' + zip + '</div>';
				 					html += '<div class="product-description"> CAPACITY: ' + ava + '</div>';
				 					html += '<div class="product-description"> IMAGE PATH: ' + image + '</div>';
		 						html += '</form>';
	 						html += '</div>';
						html += '</div>';
					html += '</div>';
					html += '</div>';
				 });
				 
				 $('#RetrieveLocationView').html(html);
			},
			error: function(exception){
				alert("exception: " + exception);
			}
		});
		
		$("#RetrieveLocationView").show();
	});
});


