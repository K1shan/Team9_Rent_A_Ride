// This will check if the user and password field are null in sign in form
function locationUpdateNullCheck(){
	
	var name = document.getElementById("nameUpdate").value;
	var add = document.getElementById("addressUpdate").value;
	var city = document.getElementById("cityUpdate").value;
	var state = document.getElementById("stateUpdate").value;
	var zip = document.getElementById("zipUpdate").value;
	var ava = document.getElementById("avaUpdate").value;
	
	if(name === ""){
		alert("Name field is blank");
		return false;
	}else if(add === ""){
		alert("Address field is blank");
		return false;
	}else if(city === ""){
		alert("City field is blank");
		return false;
	}else if(state === ""){
		alert("State field is blank");
		return false;
	}else if(zip === "" && zip > 0 && isNaN(zip)){
		alert("Zip field is blank");
		return false;
	}else if(ava === "" && ava > 0 && isNaN(ava)){
		alert("Avalibility field is blank");
		return false;
	}else if( document.getElementById("pic").files.length == 0 ){
		alert("File field is blank");	
		return false;
	}else{
		return true;
	}
}