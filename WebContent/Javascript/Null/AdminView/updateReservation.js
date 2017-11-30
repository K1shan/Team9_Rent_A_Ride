function reservationNoShowUpdateNullCheck(){
	
	var reservation = document.getElementById("selectReservationNoShowUpdate").value;
	
	if(reservation === ""){
		alert("You must select a reservation");
		return false;
	}else{
		return true;
	}
}