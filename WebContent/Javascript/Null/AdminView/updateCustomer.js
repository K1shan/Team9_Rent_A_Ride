// This will check if the user and password field are null in sign in form
function customerUpdateNullCheck(){

	var customerUpdate = document.getElementById("selectCustomerUpdateAdmin").value;
	var statusUpdate = document.getElementById("selectCustomerStatusUpdate").value;
	
	if(customerUpdate === ""){
		
		alert("Invalid Customer");
		return false;
	}else if(statusUpdate === ""){
		
		alert("Invalid Status");
		return false;
	}else{
		return true;
	}
}