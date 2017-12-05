<div class="modalThree">
	<div class="tab">
		<label class="head">UPDATE PROFILE </label>
		<form class="form" id="admin" action="CustomerUpdate2" method='post' name="admin">

				<p class="float-label">
    			 	<input type="text" id="licenseState" name="licenseState" placeholder="License State"/>
				</p>
				
	  			<p class="float-label">
    				<input type="text" id="licenseNumber" name="licenseNumber" placeholder="License Number"/>
	  			</p>
	  			
	 	 		<p class="float-label">
    				<input type="text" id="ccNum" name="ccNum" placeholder="Credit Card Number"/>
	 			</p>
	 			
	  			<p class="float-label">
    				<input type="text" id="ccExp" name="ccExp" placeholder="Credit Card Expiration"/>
	  			</p>
	  			
	  			<p>
	    			<input class = "type" type="submit" onclick="return customerUpdate2NullCheck()" value="UPDATE CUSTOMER" />
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
