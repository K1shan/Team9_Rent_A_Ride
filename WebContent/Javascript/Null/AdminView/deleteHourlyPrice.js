// This will check if the user and password field are null in sign in form
function hourlypriceDeleteNullCheck(){
	
	var id = document.getElementById("selectHourlyPriceDelete").value;
	
	if(id === ""){
		
		alert("Invalid Hourly Price");
		return false;
	}else{
		return true;
	}
}