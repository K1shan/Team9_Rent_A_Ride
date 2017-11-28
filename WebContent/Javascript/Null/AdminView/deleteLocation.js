// This will check if the user and password field are null in sign in form
function locationDeleteNullCheck(){
	
	var id = document.getElementById("selectLocationDelete").value;
	if(id === ""){
		alert("Invalid Location");
		return false;
	}else{
		return true;
	}
}