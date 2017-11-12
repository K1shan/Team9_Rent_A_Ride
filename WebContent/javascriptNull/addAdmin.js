// This will check if the user and password field are null in sign in form
function adminNullCheck(){
	
	var username = document.getElementById("address").value;
	if(username === ""){
		alert("Email field is blank");
		return false;
	}else{
		return true;
	}
}