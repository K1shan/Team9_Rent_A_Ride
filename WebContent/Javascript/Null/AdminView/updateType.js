// This will check if the user and password field are null in sign in form
function vehicleUpdateTypeNullCheck(){

	var id = document.getElementById("selectVehicleTypeUpdate").value;
	var typeName = document.getElementById("vehicleTypeUpdate").value;
	
	if(id === ""){
		alert("Invalid Select Type");
		return false;
	}else if(typeName === ""){
		alert("Invalid Type Name");
		return false;
	}else{
		return true;
	}
}