//// This will check if the user and password field are null in sign in form
//function locationUpdateNullCheck(){
//	
//	var selectLocationUpdate = document.getElementById("selectLocationUpdate").value;
//	alert(selectLocationUpdate);
//	var name = document.getElementById("nameUpdateLocation").value;
//	alert(name);
//	var add = document.getElementById("addressUpdate").value;
//	var city = document.getElementById("cityUpdate").value;
//	var state = document.getElementById("stateUpdate").value;
//	var zip = document.getElementById("zipUpdate").value;
//	var zip_l = zip.length;
//	var ava = document.getElementById("avaUpdate").value;
//	
//	
//	if(selectLocationUpdate === "")
//		
//		alert("You must select a location")
//		return false;
//	if(name === ""){
//		
//		alert("Invalid Name");
//		return false;
//	}else if(add === ""){
//		
//		alert("Invalid Address");
//		return false;
//	}else if(city === ""){
//		
//		alert("Invalid City");
//		return false;
//	}else if(state === ""){
//		
//		alert("Invalid State");
//		return false;
//	}else if(zip === "" || zip < 0 || isNaN(zip) || zip_l != 5){
//		
//		alert("Invalid Zip");
//		return false;
//	}else if(ava === "" || ava < 0 || isNaN(ava)){
//		
//		alert("Invalid Avalibility");
//		return false;
//	}else if( document.getElementById("pic").files.length == 0 ){
//		
//		alert("Invalid File");	
//		return false;
//	}else{
//		return true;
//	}
//}