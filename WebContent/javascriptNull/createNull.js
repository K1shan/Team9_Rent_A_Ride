// This will check if the first, last name, email, password null in signinOne.ftl
function registerNullCheck(){
	var fname = document.getElementById("fName").value;
	var lname = document.getElementById("lName").value;
	var email = document.getElementById("email").value;
	var password = document.sign.password.value;
	var conPass = document.sign.confirm.value;

	if(fname == ""){
		
		alert("First name field is blank");
		return false;
	} else if(lname == ""){
		
		alert("Last name field is blank");s
		return false;
	} else if(email == "") {
		
		alert("Email field is blank");
		return false;
	} else if(password.length == 0) {
		
		alert("Password field is blank");
		return false;
	}
	if(conPass.length == 0 ) {
		
		alert("Please confirm password");
		return false;
	} else {
		return true;
	}

}

//This will check if the drive, card, edp, add, state, zip is null in signinTwo.ftl
function registertwoNullCheck(){
	var drive = document.getElementById("drive").value;
	var card = document.getElementById("card").value;
	var exp = document.getElementById("exp").value;
	var add = document.getElementById("add").value;
	var state = document.getElementById("state").value;
	var zip = document.getElementById("zip").value;
	
	if(drive == ""){
		
		alert("Drive Licence No field is blank");
		return false;
	} else if(card == ""){
		
		alert("Card No field is blank");s
		return false;
	} else if(exp == "") {
		
		alert("Expiration field is blank");
		return false;
	} else if(add == "") {
		
		alert("Address field is blank");
		return false;
	}else if(state == "") {
		
		alert("State field is blank");
		return false;
	} else if(zip == "") {
		
		alert("Zipcode field is blank");
		return false;
	} else {
		return true;
	}

}