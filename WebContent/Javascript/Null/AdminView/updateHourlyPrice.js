// This will check if the user and password field are null in sign in form
function hourlypriceUpdateNullCheck(){

	var hourlypriceUpdate = document.getElementById("hourlypriceUpdate").value;
	var hourlyPrice = document.getElementById("hourlyPriceUpdate").value;
	var priceOne = document.getElementById("vehiclePriceUpdate").value;
	var type = document.getElementById("selectHourlyPriceUpdate").value;
	
	if(hourlypriceUpdate === ""){
		
		alert("Invalid Update Price");
		return false;
	}else if(hourlyPrice === ""){
		
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
