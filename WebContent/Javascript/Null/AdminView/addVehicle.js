// This will check if the user and password field are null in sign in form
function vehicleCreateNullCheck(){
	
	var vehicleType = document.getElementById("selectVehicleTypeAdd").value;
	var make = document.getElementById("makeAdd").value;
	var model = document.getElementById("modeAddl").value;
	var year = document.getElementById("yearAdd").value;
	var mileage = document.getElementById("mileageAdd").value;
	var selectLocation = document.getElementById("selectLocationAdd").value;
	var tag = document.getElementById("tagAdd").value;
	
	if(vehicleType === ""){
		alert("Type field is blank");
		return false;
	}else if(make === ""){
		alert("Make field is blank");
		return false;
	}else if(model === ""){
		alert("Model field is blank");
		return false;
	}else if(year === ""&& year > 0 && isNaN(year)){
		alert("Year field is blank");
		return false;
	}else if(mileage === "" && mileage > 0 && isNaN(mileage)){
		alert("Milage field is blank");
		return false;
	}else if(selectLocation === ""){
		alert("Location field is blank");
		return false;
	}else if(tag === ""){
		alert("Tag field is blank");
		return false;
	}else{
		return true;
	}
}