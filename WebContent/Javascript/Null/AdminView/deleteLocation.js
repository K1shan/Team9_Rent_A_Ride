// This will check if the user and password field are null in sign in form
function locationDeleteNullCheck(){
	
	var username = document.getElementById("id").value;
	if(username === ""){
		alert("ID field is blank");
		return false;
	}else{
		return true;
	}
}