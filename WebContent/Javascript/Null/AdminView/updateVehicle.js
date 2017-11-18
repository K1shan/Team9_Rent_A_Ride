// This will check if the user and password field are null in sign in form
function vehicleUpdateNullCheck(){
	
	var vehicleType = document.getElementById("selectVehicleTypeUpdate").value;
	var make = document.getElementById("makeUpdate").value;
	var model = document.getElementById("modelUpdate").value;
	var year = document.getElementById("yearUpdate").value;
	var mileage = document.getElementById("mileageUpdate").value;
	var selectLocation = document.getElementById("selectLocationUpdate").value;
	var tag = document.getElementById("tagUpdate").value;
	
	if(vehicleType === ""){
		alert("Type field is blank");
		return false;
	}if(make === ""){
		alert("Make field is blank");
		return false;
	}if(model === ""){
		alert("Model field is blank");
		return false;
	}if(year === "" && year > 0 && isNaN(year)){
		alert("Year field is blank");
		return false;
	}else if(mileage === "" && mileage > 0 && isNaN(mileage)){
		alert("Milage field is blank");
		return false;
	}if(selectLocation === ""){
		alert("Location field is blank");
		return false;
	}if(tag === ""){
		alert("Tag field is blank");
		return false;
	}else{
		return true;
	}
}