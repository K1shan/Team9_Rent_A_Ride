<div class="modalTwo">
	<div class="tab">
		<label class="head">UPDATE PROFILE </label>
		<form class="form" id="admin" action="AdminUpdate" method='post' name="admin">

	  			<!-- User input for First name -->
				<p class="float-label">
	    			 	<input id="fName" name="first-name" placeholder="First Name" required />
				</p>
				
				<!-- User input for Last name -->
	  			<p class="float-label">
	    				<input id="lName" name="last-name" placeholder="Last Name" required />
	  			</p>
	  			
	 			
	 			<!-- User input for Address -->
	  			<p class="float-label">
	    				<input type="text" id="address" name="address" placeholder="Address" required />
	  			</p>
	  			
	  			<!-- User input for password -->
      			<p class="float-label">
        				<input type="password" id="password" name="password" placeholder="New Password" required />
      			</p>
      			
      			<!-- User input for confirm password -->
      			<p class="float-label">
        				<input type="password" id="confirm-password" name="confirm" placeholder="Confirm password" required />
      			</p>
	  			
	  			<p>
		    			<input class = "type" type="submit" onclick="return adminUpdateNullCheck()" value="UPDATE ADMIN" />
		  		</p>

	  		<#if statusUpdateAdminG??>			
				<p class="good">
   					<label> ${statusUpdateAdminG} </label>
  				</p>
			</#if>
			<#if statusUpdateAdminB??>
				<p class="error">
					 <label> ${statusUpdateAdminB} </label>
				</p>
			</#if>
		</form>
	</div>
</div>
