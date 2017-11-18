// This will check if the user and password field are null in sign in form
function vehicleUpdateNullCheck(){
	
	var VehicleUpdate = document.getElementById("selectVehicleUpdate").value;
	var vehicleType = document.getElementById("selectVehicleTypeUpdate").value;
	var make = document.getElementById("makeUpdate").value;
	var model = document.getElementById("modelUpdate").value;
	var year = document.getElementById("yearUpdate").value;
	var year_l = year.length;
	var mileage = document.getElementById("mileageUpdate").value;
	var selectLocation = document.getElementById("selectLocationUpdate").value;
	var tag = document.getElementById("tagUpdate").value;
	
	if(VehicleUpdate === ""){
		alert("Invalid Vehicle");
		return false;
	}else if(make === ""){
		alert("Invalid Make");
		return false;
	}else if(model === ""){
		alert("Invalid Model");
		return false;
	}if(vehicleType === ""){
		alert("Invalid Type");
		return false;
	}else if(year === "" || year < 0 || isNaN(year) || year_l != 4){
		alert("Invalid Year");
		return false;
	}else if(mileage === "" || mileage < 0 || isNaN(mileage)){
		alert("Invalid Milage");
		return false;
	}else if(selectLocation === ""){
		alert("Invalid Location");
		return false;
	}else if(tag === ""){
		alert("Invalid Tag");
		return false;
	}else{
		return true;
	}
}