<!-- This is the part one of the create account and sign in -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- This is the tite of the page -->
<title>Rent-A-Ride | Forgot Password</title>

<!-- This is the css for the create account page part one and sign in -->
<link href='cssfiles/signIn.css' rel='stylesheet' type='text/css'>

<!-- This will import the lib of the jquery -->
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>

<!-- The navbar.js will help import navbar.html -->
<script type = "text/javascript" src="Javascript/Navbar/navbar.js"></script>

<!-- The will check the user input and empty fields -->
<script type = "text/javascript" src="Javascript/Null/SignCreate/forgotNull.js"></script>

</head>

<body>

	<!-- The will import the navbar -->
	<div id="header"></div>
	
		<!-- This is where we start part one of the create account and sign in -->
  		<div class="modal">
  		
  			<!-- This is the tab for sign in -->
 			 <div class="tab">
    			<input type="radio" id="sign-in" name="options" class="option" checked/>
    			
    			<!-- This is where we start the sign in tab -->
    			<label for="sign-in">FORGOT PASSWORD</label>
   				
   				<!-- Part two of the sign in form will start from here and will go to the -->
   				<!-- servlet customerSignin in presentation.customer package -->

   				<form class="form" action="UpdatePassword" method='get'>

   					<!-- User input for email -->
      				<p class="float-label">
        				<input type="text" id="username" name="email" placeholder="Email"/>
      				</p>
      				
	     			<!-- User input for password -->
	      			<p class="float-label">
	        			<input type="password" id="password" name="password" placeholder="Password"/>
	      			</p>
      			
	      			<!-- User input for confirm password -->
	      			<p class="float-label">
	        			<input type="password" id="confirm-password" name="confirm" placeholder="Confirm password"/>
	      			</p>
      				
      				<!-- This is a button and will submit the form -->
      				<p>
        				<input type="submit" name="signin" onclick="return forgotNullCheck()" value="Sign In" />
      				</p>	
      				
		      			<#if statusUpdatePasswordG??>
							
							<p class="good">
			   					<label> ${statusUpdatePasswordG} </label>
			  				</p>
								
						</#if>
  				
						<#if statusUpdatePasswordB??>
						
							<p class="error">
		   						 <label> ${statusUpdatePasswordB} </label>
		  					</p>
							
						</#if>
      				
    				</form>
			</div>
		</div>
</body>
</html>