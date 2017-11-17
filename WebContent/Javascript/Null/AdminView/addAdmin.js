// This will check if the user and password field are null in sign in form
function adminCreateNullCheck(){
	
	var username = document.getElementById("email").value;
	if(username === ""){
		alert("Email field is blank");
		return false;
	}else{
		return true;
	}
}