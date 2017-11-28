// This will check if the first, last name, email, password null in signinOne.ftl
function registerNullCheck(){

	var fname = document.getElementById("fName").value;
	var lname = document.getElementById("lName").value;
	var email = document.getElementById("email").value;
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

//This will check if the drive, card, edp, add, state, zip is null in signinTwo.ftl
function registertwoNullCheck(){
	var drive = document.getElementById("drive").value;
	var card = document.getElementById("card").value;
	var exp = document.getElementById("exp").value;
	var add = document.getElementById("add").value;
	var city = document.getElementById("city").value;
	var state = document.getElementById("state").value;
	var zip = document.getElementById("zip").value;
	
	if(drive == ""){
		
		alert("Invalid Drive Licence No");
		return false;
	} else if(card == ""){
		
		alert("Invalid Card No");s
		return false;
	} else if(exp == "") {
		
		alert("Invalid Expiration");
		return false;
	} else if(add == "") {
		
		alert("Invalid Address");
		return false;
	}else if(city == "") {
		
		alert("Invalid City");
		return false;
	}else if(state == "") {
		
		alert("Invalid State");
		return false;
	} else if(zip == "") {
		
		alert("Invalid Zipcode");
		return false;
	} else {
		return true;
	}

}