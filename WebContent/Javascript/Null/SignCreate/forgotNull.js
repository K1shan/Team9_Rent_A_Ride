// This will check if the user and password field are null in sign in form
function forgotNullCheck(){

	var fname = document.getElementById("fname").value;
	var lname = document.getElementById("lname").value;
	var email = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var cPassword = document.getElementById("confirm-password").value;
	
	if(fname === ""){
		alert("Invalid First Name");
		return false;
	}else if(lname === ""){
		alert("Invalid Last Name");
		return false;
	}else if(email === ""){
		alert("Invalid Email");
		return false;
	}else if(password === ""){
		alert("Invalid Password");
		return false;
	}else if(cPassword === "" || cPassword != password){
		alert("Invalid Confirm Password");
		return false;
	}else{
		return true;
	}
}
