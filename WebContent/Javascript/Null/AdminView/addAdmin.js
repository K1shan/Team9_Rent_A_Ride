// This will check if the user and password field are null in sign in form
function adminCreateNullCheck(){
	
	var admin_email = document.getElementById("admin_email").value;
	if(admin_email === ""){
		
		alert("Invalid Email");
		return false;
	}else{
		return true;
	}
}