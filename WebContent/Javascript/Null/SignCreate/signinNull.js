// This will check if the user and password field are null in sign in form
function loginNullCheck(){
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	if(username === ""){
		alert("Username field is blank");
		return false;
	}else if(password === ""){
		alert("Password field is blank");
		return false;
	}else{
		return true;
	}
}