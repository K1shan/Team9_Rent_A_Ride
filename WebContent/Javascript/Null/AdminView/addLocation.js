// This will check if the user and password field are null in sign in form
function locationCreateAddNullCheck(){
	
	var name = document.getElementById("nameAdd").value;
	var add = document.getElementById("addressAdd").value;
	var city = document.getElementById("cityAdd").value;
	var state = document.getElementById("stateAdd").value;
	var zip = document.getElementById("zipAdd").value;
	var ava = document.getElementById("avaAdd").value;
	
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