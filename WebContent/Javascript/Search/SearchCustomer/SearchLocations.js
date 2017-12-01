$(document).ready(function() {
	
	$("#searchError").hide();
	
	$('#Location-bar').keyup(function(){

		 var searchField = $('#email-bar').val();
		 var optionn = $('#searchOption').val();
		 
		 var regex = new RegExp(searchField, "i");

		 if(searchField === ""){

			 $('#search').html("");
			 return;
		 }

	});
});