// This will check if the user and password field are null in sign in form
function hourlypriceUpdateNullCheck(){

	var id = document.getElementById("selectHourlyPriceUpdate").value;
	var type = document.getElementById("selectHourlyPriceVehicleTypeUpdate").value;
	var maxHrs = document.getElementById("selectHourlyPriceHourUpdate").value;
	var price = document.getElementById("hourlyPricePriceUpdate").value;
	
	if(id === ""){
		
		alert("Invalid Select Hourly Price");
		return false;
	}else if(type === ""){
		
		alert("Invalid Type");
		return false;
	}else if(price === "" || price < 0 || isNaN(price)){
		
		alert("Invalid Price");
		return false;
	}else if(maxHrs === ""){
		
		alert("Invalid Hours");
		return false;
	}else {
		return true;
	}
}
