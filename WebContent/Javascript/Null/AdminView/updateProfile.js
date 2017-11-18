function adminUpdateNullCheck(){
	
	var fname = document.getElementById("fName").value;
	var lname = document.getElementById("lName").value;
	var email = document.getElementById("email").value;
	var address = document.getElementById("address").value;
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
	}else if(address === ""){
		alert("Invalid Address");
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