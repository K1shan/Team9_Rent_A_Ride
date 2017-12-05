<div class="modalThree">
	<div class="tab">
		<label class="head">UPDATE INFORMATION </label>
		<form class="form" id="admin" action="AdminUpdate2" method='post' name="admin">
	 			

				<p class="float-label">
	    			 	<input type="text" id="stateInfo" name="state" placeholder="State" required />
				</p>
				

	  			<p class="float-label">
	    				<input type="text" id="licNumInfo" name="licenseNumber" placeholder="License Number" required />
	  			</p>
	  			

	 	 		<p class="float-label">
	    				<input type="text" id="ccNumInfo" name="ccNumber" placeholder="Credit Card Number" required />
	 			</p>
	 			

	  			<p class="float-label">
	    				<input type="text" id="ccExpInfo" name="expDate" placeholder="Credit Card Exp Date" required />
	  			</p>
	  			
	  			<p>
		    			<input class = "type" type="submit" onclick="return adminUpdateInfoNullCheck()" value="UPDATE INFO" required />
		  		</p>

	  		<#if statusUpdateInfoG??>			
				<p class="good">
   					<label> ${statusUpdateInfoG} </label>
  				</p>
			</#if>
			<#if statusUpdateInfoB??>
				<p class="error">
					 <label> ${statusUpdateInfoB} </label>
				</p>
			</#if>
		</form>
	</div>
</div>
