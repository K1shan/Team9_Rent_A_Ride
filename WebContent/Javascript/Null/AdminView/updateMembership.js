// This will check if the user and password field are null in sign in form
function membershipCreateNullCheck(){

	var membership = document.getElementById("membershipUpdate").value;
	
	if(membership === "" || membership < 0 || isNaN(membership)){
		
		alert("Invalid Membership Price");
		return false;
	} else {
		return true;
	}
}