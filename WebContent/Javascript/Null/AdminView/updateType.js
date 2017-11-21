// This will check if the user and password field are null in sign in form
function vehicleUpdateTypeNullCheck(){

	var selectVehicleTypeUpdate = document.getElementById("selectVehicleTypeUpdate").value;
	var typeName = document.getElementById("vehicleTypeUpdate").value;
	
	if(select1 === ""){
		alert("Invalid Select Type");
		return false;
	}else if(typeName === ""){
		alert("Invalid Type Name");
		return false;
	}else{
		return true;
	}
}