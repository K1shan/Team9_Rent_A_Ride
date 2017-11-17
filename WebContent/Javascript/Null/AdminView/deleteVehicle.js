// This will check if the user and password field are null in sign in form
function vehicleDeleteNullCheck(){
	
	var id = document.getElementById("id").value;
	
	if(id === "" && id > 0 && isNaN(id)){
		alert("Vehicle ID field is blank");
		return false;
	}else{
		return true;
	}
}