<div class="modal">
	<div class="tab">
		<label class="head">VIEW PROFILE</label>
		<form class="form" id="admin" action="CustomerUpdate" method='post' name="admin">
			
	  		<#if userSession?has_content>

	  			<!-- User input for First name -->
				<p class="float-label">
					<p class ="in">
	    				 	<b>FIRST NAME:</b><br><br> ${userSession.firstName}
	    				</p> 	    				
				</p>
				
				<!-- User input for Last name -->
	  			<p class="float-label">
	  			
	  				<p class ="in">
	    					<b>LAST NAME:</b><br><br> ${userSession.lastName} 
	    				</p>
	  			</p>
	  			
	  			<!-- User input for Email -->
	 	 		<p class="float-label">
	 	 		
	 	 			<p class ="in">
	    					<b>EMAIL:</b> <br><br> ${userSession.userName} 
	    				</p>
	 			</p>
	 			
	 			<!-- User input for Address -->
  				<p class="float-label">
  				
  					<p class ="in">
	    					<b>ADDRESS:</b> <br><br>${userSession.address} 
	    				</p>
	    				
	  			</p>
	  			
	  		</#if>
	  		
	  		
	  		
	  		<#if statusUpdateCustomerG??>			
				<p class="good">
   					<label> ${statusUpdateCustomerG} </label>
  				</p>
			</#if>
			<#if statusUpdateAdminB??>
				<p class="error">
					 <label> ${statusUpdateCustomerB} </label>
				</p>
			</#if>
		</form>
	</div>
</div>
