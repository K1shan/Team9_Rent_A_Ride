// This will check if the user and password field are null in sign in form
function reservationAdminCreateNullCheck(){

	var reservationType = document.getElementById("selectReservationVehicleTypeAdmin").value;
	var dateAdmin = document.getElementById("dateAdmin").value;
	var timeAdmin = document.getElementById("timeAdmin").value;
	var reservationLength = document.getElementById("selectReservationLengthAdmin").value;
	
	if(reservationType === ""){
		
		alert("Invalid Vehicle Type");
		return false;
	}else if(dateAdmin === ""){
		
		alert("Invalid Date");
		return false;
	}else if(timeAdmin === ""){
		
		alert("Invalid Time");
		return false;
	}else if(reservationLength === ""){
		
		alert("Invalid Length");
		return false;
	}{
		return true;
	}
}