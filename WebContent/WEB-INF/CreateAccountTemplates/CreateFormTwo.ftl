<!-- This is the part two of the create account -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- This is the tite of the page -->
<title>Rent-A-Ride | Part 2</title>

<!-- This is the css for the create account page part two -->
<link href='cssfiles/signIn.css' rel='stylesheet' type='text/css'>

<!-- This will import the lib of the jquery -->
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>

<!-- The navbar.js will help import navbar.html -->
<script type = "text/javascript" src="Javascript/Navbar/navbar.js"></script>

<!-- The will check the user input and empty fields -->
<script type = "text/javascript" src="Javascript/Null/SignCreate/createNull.js"></script>
</head>

<body>

	<!-- The will import the navbar -->
	<div id="header"></div>
	
		<!-- This is where we start part two of the create account -->
  		<div class="modal">
  		
			<input type="radio" id="sign-in" name="options" class="option" checked/>
			<label for="sign-in"> MORE INFO </label>
   				
   			<!-- Part two of the create account form will start from here and will go to the -->
   			<!-- servlet signinOne in presentation.regular package -->
			<form class="form" action="CreateAccount" method='get'>
			
				<!-- User input for Driver License No -->
  				<p class="float-label">
    				<input type="text" id="drive" name="drive" placeholder="DRIVER LICENSE NO"/>
  				</p>
  				
  				<!-- User input for Card No -->
  				<p class="float-label">
    				<input type="text" id="card" name="card" placeholder="CARD NO"/>
    				
  				</p>
				
  				<!-- User input for Expriation Date -->
  				<p class="float-label">
  					<p>EXPIRATION DATE</p>
  					<select id="exp-month-register" name="exp-month-register" class="minimal">
						<option value=""></option>
					    <option value="01">January</option>
					    <option value="02">February</option>
					    <option value="03">March</option>
					    <option value="04">April</option>
					    <option value="05">May</option>
					    <option value="06">June</option>
					    <option value="07">July</option>
					    <option value="08">August</option>
					    <option value="09">September</option>
					    <option value="10">October</option>
					    <option value="11">November</option>
					    <option value="12">December</option>
					</select>
					
					<select id="exp-year-register" name="exp-year-register" class="minimal">
						<option value=""></option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
						<option value="2019">2019</option>
						<option value="2020">2020</option>
						<option value="2021">2021</option>
						<option value="2022">2022</option>
						<option value="2023">2023</option>
						<option value="2024">2024</option>
						<option value="2025">2025</option>
						<option value="2026">2026</option>
						<option value="2027">2027</option>
						<option value="2028">2028</option>
    				</select>
  				</p>
  				
  				<!-- User input for Address -->
  				<p class="float-label">
    				<input type="text" id="add" name="add" placeholder="ADDRESS"/>
  				</p>
  				
  				<p class="float-label">
    				<input type="text" id="city" name="city" placeholder="CITY"/>
  				</p>
  				
  				<!-- User input for State -->
  				<p class="float-label">
    				<input type="text" id="state" name="state" placeholder="STATE"/>
  				</p>
  				
  				<!-- User input for Zip -->
  				<p class="float-label">
    				<input type="text" id="zip" name="zip" placeholder="ZIP"/>
  				</p>
  	
  				<!-- This is a button and will submit the form -->
  				<p>
    				<input type="submit" name="register" onclick="return registertwoNullCheck()" value="SIGN IN" />
  				</p>
			</form>
  		</div>
	</div>
</body>
</html>