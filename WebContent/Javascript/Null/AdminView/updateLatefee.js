// This will check if the user and password field are null in sign in form
function latefeeUpdateNullCheck(){

	var latefee = document.getElementById("latefeeUpdate").value;

	
	if(latefee === "" || latefee < 0 || isNaN(latefee)){
		
		alert("Invalid Late Fee");
		return false;
	}else {
		return true;
	}
}