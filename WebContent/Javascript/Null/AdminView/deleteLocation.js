// This will check if the user and password field are null in sign in form
function locationDeleteNullCheck(){
	
	var id = document.getElementById("id").value;
	if(id === "" && id > 0 && isNaN(id)){
		alert("ID field is blank");
		return false;
	}else{
		return true;
	}
}