<div class="modalFour">
	<div class="tab">
		<label class="head">VIEW PROFILE</label>
		<form class="form" id="admin" action="AdminUpdate2" method='post' name="admin">
			
	  		<#if userSession?has_content>
	  		
	  			<!-- User input for License State -->
				<p class="float-label">
					<p class ="in">
    				 	<b>LICENSE STATE:</b><br><br> ${userSession.licenseState}
    				</p> 	    				
				</p>
				
				<!-- User input for License Num -->
	  			<p class="float-label">
	  				<p class ="in">
    					<b>LICENSE NUMBER:</b><br><br> ${userSession.licenseNum} 
    				</p>
	  			</p>
	  			
	  			<!-- User input for CC Num -->
	 	 		<p class="float-label">
	 	 			<p class ="in">
    					<b>Credit Card Number:</b> <br><br> ${userSession.ccNum} 
    				</p>
	 			</p>
	 			
	 			<!-- User input for CC Exp -->
  				<p class="float-label">
  					<p class ="in">
    					<b>Credit Card Expiration:</b> <br><br>${userSession.ccExp} 
	    			</p>	
	  			</p>
	  			
	  		</#if>
		</form>
	</div>
</div>
