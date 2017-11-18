// This will check if the user and password field are null in sign in form
function vehicleDeleteNullCheck(){
	
	var id = document.getElementById("selectVehicleDelete").value;
	
	if(id === ""){
		alert("Invalid Vehicle");
		return false;
	}else{
		return true;
	}
}