// This will check if the user and password field are null in sign in form
function hourlypriceCreateNullCheck(){

	var typeId = document.getElementById("selectHourlyPriceVehicleTypeAdd").value;
	var hours = document.getElementById("selectHourlyPriceHourAdd").value;
	var price = document.getElementById("hourlyPricePriceAdd").value;
	
	if(typeId === ""){
		
		alert("Invalid Vehicle Type");
		return false;
	}else if(price === "" || price < 0 || isNaN(price)){
		
		alert("Invalid Price");
		return false;
	}else if(hours === ""){
		
		alert("Invalid Hours");
		return false;
	}else {
		return true;
	}
}