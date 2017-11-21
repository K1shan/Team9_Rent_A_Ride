<div class="modalTwo">
	<div class="tab">
		<label class="head">UPDATE PROFILE </label>
		<form class="form" id="admin" action="CustomerUpdate" method='post' name="admin">

	  			<!-- User input for First name -->
				<p class="float-label">
	    			 	<input id="fName" name="first-name" placeholder="First Name"/>
				</p>
				
				<!-- User input for Last name -->
	  			<p class="float-label">
	    				<input id="lName" name="last-name" placeholder="Last Name"/>
	  			</p>
	  			
	  			<!-- User input for Email -->
	 	 		<p class="float-label">
	    				<input type="email" id="email" name="email" placeholder="Email"/>
	 			</p>
	 			
	 			<!-- User input for Address -->
	  			<p class="float-label">
	    				<input type="text" id="address" name="address" placeholder="Address"/>
	  			</p>
	  			
	  			<!-- User input for password -->
      			<p class="float-label">
        				<input type="password" id="password" name="password" placeholder="New Password"/>
      			</p>
      			
      			<!-- User input for confirm password -->
      			<p class="float-label">
        				<input type="password" id="confirm-password" name="confirm" placeholder="Confirm password"/>
      			</p>
	  			
	  			<p>
		    			<input class = "type" type="submit" onclick="return customerUpdateNullCheck()" value="UPDATE CUSTOMER" />
		  		</p>

	  		<#if statusUpdateCustomerG??>			
				<p class="good">
   					<label> ${statusUpdateCustomerG} </label>
  				</p>
			</#if>
			<#if statusUpdateCustomerB??>
				<p class="error">
					 <label> ${statusUpdateCustomerB} </label>
				</p>
			</#if>
		</form>
	</div>
</div>
