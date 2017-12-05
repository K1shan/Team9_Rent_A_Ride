function customerUpdateNullCheck(){
	
	var fname = document.getElementById("fName").value;
	var lname = document.getElementById("lName").value;
	var address = document.getElementById("address").value;
	var password = document.getElementById("password").value;
	var cPassword = document.getElementById("confirm-password").value;
	
	if(fname === "" && lname === "" && password === "" && cPassword === ""){
		alert("You must input something");
		return false;
	}else if(password === "" && cPassword !== "" ){
		alert("You must input password and confirm password");
		return false;
	}else if(password !== "" && cPassword === "" ){
		alert("You must input password and confirm password");
		return false;
	}else if(cPassword != password){
		alert("Invalid Confirm Password");
		return false;
	}else{
		return true;
	}
}

function customerUpdate2NullCheck(){
	
	var licenseState = document.getElementById("licenseState").value;
	var licenseNumber = document.getElementById("licenseNumber").value;
	var ccNum = document.getElementById("ccNum").value;
	var ccExp = document.getElementById("ccExp").value;
	
	if(licenseState === "" && licenseNumber === "" && ccNum === "" && ccExp === ""){
		alert("You must input something");
		return false;
	}else{
		return true;
	}
}