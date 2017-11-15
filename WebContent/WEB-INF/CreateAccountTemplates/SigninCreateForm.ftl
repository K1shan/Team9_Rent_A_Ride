<!-- This is the part one of the create account and sign in -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- This is the tite of the page -->
<title>Rent-A-Ride | Sign In</title>

<!-- This is the css for the create account page part one and sign in -->
<link href='cssfiles/signIn.css' rel='stylesheet' type='text/css'>

<!-- This will import the lib of the jquery -->
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>

<!-- The navbar.js will help import navbar.html -->
<script type = "text/javascript" src="Javascript/Navbar/navbar.js"></script>

<!-- The will check the user input and empty fields -->
<script type = "text/javascript" src="Javascript/Null/SignCreate/signinNull.js"></script>

<!-- The will check the user input and empty fields -->
<script type = "text/javascript" src="Javascript/Null/SignCreate/createNull.js"></script>
</head>

<body>

	<!-- The will import the navbar -->
	<div id="header"></div>
	
		<!-- This is where we start part one of the create account and sign in -->
  		<div class="modal">
  		
  				
		<#if status??>
			<p>${status}</p>
		</#if>
	
  		
  			<!-- This is the tab for sign in -->
 			 <div class="tab">
    			<input type="radio" id="sign-in" name="options" class="option" checked/>
    			
    			<!-- This is where we start the sign in tab -->
    			<label for="sign-in">SIGN IN</label>
   				
   				<!-- Part two of the sign in form will start from here and will go to the -->
   				<!-- servlet customerSignin in presentation.customer package -->
   				<form class="form" action="SigninMenu" method='get'>
   				
   					<!-- User input for email -->
      				<p class="float-label">
        				<input type="text" id="username" name="email" placeholder="Email"/>
      				</p>
      				
      				<!-- User input for password -->
     				<p class="float-label">
        				<input type="password" id="password" name="password" placeholder="Password"/>
      				</p>
      				
      				<!-- User input for checkbox remember -->
      				<p class="toggle">
       					 <label for="remember"><input type="checkbox" id="remember" name="remember"/> Remember me</label>
      				</p>
      				
      				<!-- Link to forgot password -->
      				<p class="forgot">
       					 <a href="ForgotPassword"/> FORGOT PASSWORD </a>
      				</p>
      				
      				<!-- This is a button and will submit the form -->
      				<p>
        				<input type="submit" name="signin" onclick="return loginNullCheck()" value="Sign In" />
      				</p>
      				
    			</form>
    			
  			</div>
  
		  		<!-- This is the tab for create account -->
		  		<div class="tab">
		    		<input type="radio" id="sign-up" name="options" class="option"/>
		    		
		    		<!-- This is where we start the create account tab -->
		    		<label for="sign-up">CREATE ACCOUNT</label>
		    
		    		<!-- Part one of the create account form will start from here and will go to the -->
		   		<!-- servlet signinTwo in presentation.regular package -->
		   		
		    		<form name="sign" class="form" action="CreateAccount" method="get">
    		
    				<!-- User input for First name -->
				<p class="float-label">
        				<input id="fName" name="first-name" placeholder="First Name"/>
				</p>
				
				<!-- User input for Last name -->
      			<p class="float-label">
        			<input id="lName" name="last-name" placeholder="Last Name"/>
      			</p>
      			
      			<!-- User input for email -->
     	 		<p class="float-label">
        			<input type="email" id="email" name="email" placeholder="Email"/>
     			</p>
     			
     			<!-- User input for password -->
      			<p class="float-label">
        			<input type="password" id="password" name="password" placeholder="Password"/>
      			</p>
      			
      			<!-- User input for confirm password -->
      			<p class="float-label">
        			<input type="password" id="confirm-password" name="confirm" placeholder="Confirm password"/>
      			</p>
      			
      			<!-- User input for terms -->
      			<p class="toggle">
       	 			<label for="accept"><input type="checkbox" id="accept" name="accept"/> I accept the <a href="#">Terms &amp; Services</a></label>
      			</p>
      			
      			<!-- This is a button and will submit the form -->
      			<p>
        			<input id="view" name="next" type="submit" onclick="return registerNullCheck()" value="NEXT" />
     			</p>
    		</form>
  		</div>
	</div>
</body>
</html>