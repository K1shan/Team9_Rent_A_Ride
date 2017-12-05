$(document).ready(function() {
	
	 $( function() {
		    $( "#dateAdmin" ).datepicker({ 
		    	
		    		minDate: 0,
		    		dateFormat: 'yy-mm-dd'
		    	
		    });
	 } );
	
	 $( function() {
		 
		 $('#timeAdmin').timepicker({
			    timeFormat: 'h:mm p',
			    interval: 30,
			    minTime: '24',
			    maxTime: '11:00pm',
			    startTime: '4',
			    dynamic: true,
			    dropdown: true,
			    scrollbar: true,

			});
	 });
	 
});