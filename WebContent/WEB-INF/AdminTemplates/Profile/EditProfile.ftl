<div class="modal">
	<div class="tab">
		<label class="head">EDIT PROFILE </label>
		<form class="form" id="admin" action="AdminUpdate" method='post' name="admin">
			
	  		<#if userSession?has_content>
	  			<!-- User input for First name -->
				<p class="float-label">
	    			 First Name: ${userSession.firstName} <input id="fName" name="first-name" placeholder="First Name"/>
				</p>
				
				<!-- User input for Last name -->
	  			<p class="float-label">
	    			Last Name: ${userSession.lastName} <input id="lName" name="last-name" placeholder="Last Name"/>
	  			</p>
	  			
	  			<!-- User input for Email -->
	 	 		<p class="float-label">
	    			Email: ${userSession.userName} <input type="email" id="email" name="email" placeholder="Email"/>
	 			</p>
	 			
	 			<!-- User input for Address -->
	  			<p class="float-label">
	    			Address: ${userSession.address} <input type="text" id="address" name="address" placeholder="Address"/>
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
		    		<input class = "type" type="submit" value="UPDATE ADMIN" />
		  		</p>
	  		</#if>
	  		
	  		
	  		
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
