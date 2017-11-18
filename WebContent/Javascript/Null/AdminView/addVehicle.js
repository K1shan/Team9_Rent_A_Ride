// This will check if the user and password field are null in sign in form
function vehicleCreateNullCheck(){
	
	var vehicleType = document.getElementById("selectVehicleTypeAdd").value;
	var make = document.getElementById("makeAdd").value;
	var model = document.getElementById("modelAdd").value;
	var year = document.getElementById("yearAdd").value;
	var year_l = year.length;
	var mileage = document.getElementById("mileageAdd").value;
	var selectLocation = document.getElementById("selectLocationAdd").value;
	var tag = document.getElementById("tagAdd").value;
	
	if(vehicleType === ""){
		alert("Invalid Type");
		return false;
	}else if(make === ""){
		alert("Invalid Make");
		return false;
	}else if(model === ""){
		alert("Invalid Model");
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