// This will check if the user and password field are null in sign in form
function typeDeleteNullCheck(){
	
	var id = document.getElementById("select2").value;
	
	if(id === ""){
		alert("Invalid Vehicle Type");
		return false;
	}else{
		return true;
	}
}