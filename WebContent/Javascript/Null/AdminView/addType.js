// This will check if the user and password field are null in sign in form
function typeCreateNullCheck(){
	
	var username = document.getElementById("type").value;
	if(username === ""){
		alert("Type field is blank");
		return false;
	}else{
		return true;
	}
}