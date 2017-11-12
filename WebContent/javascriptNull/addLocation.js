// This will check if the user and password field are null in sign in form
function locationNullCheck(){
	
	var add = document.getElementById("address").value;
	var city = document.getElementById("city").value;
	var state = document.getElementById("state").value;
	var zip = document.getElementById("zip").value;
	var ava = document.getElementById("ava").value;
	
	if(add === ""){
		alert("Address field is blank");
		return false;
	}else if(city === ""){
		alert("City field is blank");
		return false;
	}else if(state === ""){
		alert("State field is blank");
		return false;
	}else if(zip === ""){
		alert("Zip field is blank");
		return false;
	}else if(ava === ""){
		alert("Avalibility field is blank");
		return false;
	}else if( document.getElementById("pic").files.length == 0 ){
		alert("File field is blank");	
		return false;
	}else{
		return true;
	}
}