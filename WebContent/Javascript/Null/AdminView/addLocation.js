// This will check if the user and password field are null in sign in form
function locationCreateNullCheck(){
	
	var name = document.getElementById("nameAdd").value;
	var add = document.getElementById("addressAdd").value;
	var city = document.getElementById("cityAdd").value;
	var state = document.getElementById("stateAdd").value;
	var zip = document.getElementById("zipAdd").value;
	var zip_l = zip.length;
	var ava = document.getElementById("avaAdd").value;
	
	if(name === ""){
		
		alert("Invalid Name");
		return false;
	}else if(add === ""){
		
		alert("Invalid Address");
		return false;
	}else if(city === ""){
		
		alert("Invalid City");
		return false;
	}else if(state === ""){
		
		alert("Invalid State");
		return false;
	}else if(zip === "" || zip < 0 || isNaN(zip) || zip_l != 5){
		
		alert("Invalid Zip");
		return false;
	}else if(ava === "" || ava < 0 || isNaN(ava)){
		
		alert("Invalid Avalibility");
		return false;
	}else if( document.getElementById("pic").files.length == 0 ){
		
		alert("Invalid File");	
		return false;
	}else{
		return true;
	}
}