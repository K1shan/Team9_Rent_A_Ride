// This will check if the user and password field are null in sign in form
function hourlypriceCreateNullCheck(){

	var hourlyPrice = document.getElementById("hourlyPrice").value;
	var priceOne = document.getElementById("vehiclePrice").value;
	var type = document.getElementById("selectHourlyPriceAdd").value;
	
	if(hourlyPrice === ""){
		
		alert("Invalid Hour");
		return false;
	}else if(priceOne === "" || priceOne < 0 || isNaN(priceOne)){
		
		alert("Invalid Price");
		return false;
	}else if(type === ""){
		
		alert("Invalid Vehicle Type ");
		return false;
	}else {
		return true;
	}
}