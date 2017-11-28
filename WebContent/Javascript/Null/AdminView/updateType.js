// This will check if the user and password field are null in sign in form
function vehicleUpdateTypeNullCheck(){
	
	var username = document.getElementById("type").value;
	var select1 = document.getElementById("select1").value;
	if(select1 === ""){
		alert("Invalid Select Type");
		return false;
	}else if(username === ""){
		alert("Invalid Type");
		return false;
	}else{
		return true;
	}
}