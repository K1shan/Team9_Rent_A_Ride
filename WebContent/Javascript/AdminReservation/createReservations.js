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
			    interval: 60,
			    minTime: '10',
			    maxTime: '11:00pm',
			    startTime: '4',
			    dynamic: true,
			    dropdown: true,
			    scrollbar: true,

			});
	 });
	 
});