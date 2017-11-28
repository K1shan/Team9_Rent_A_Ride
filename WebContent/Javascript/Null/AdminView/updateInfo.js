function adminUpdateInfoNullCheck(){
	
	var email = document.getElementById("emailInfo").value;
	var state = document.getElementById("stateInfo").value;
	var licNum = document.getElementById("licNumInfo").value;
	var ccNum = document.getElementById("ccNumInfo").value;
	var ccNum_l = ccNum.length;
	var ccExp = document.getElementById("ccExpInfo").value;
	
	if(email === ""){
		
		alert("Invalid Email");
		return false;
	}else if(state === ""){	
		alert("Invalid State");
		return false;
	}else if(licNum === "" || licNum < 0 || isNaN(licNum)){
		alert("Invalid License Number");
		return false;
	}else if(ccNum === "" || ccNum < 0 || isNaN(ccNum)){
		alert("Invalid Cradit Card Number");
		return false;
	}else if(ccExp === ""){
		alert("Invalid Cradit Card Exp Date");
		return false;
	}else{
		return true;
	}
}