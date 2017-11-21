<div class="modalSix">
	<div class="tab">
		<label class="head">CANCEL MEMBERSHIP</label>
		<form class="form" id="admin" action="CancelMembership" method='post' name="admin">
  			<p>
    			<input class = "type" type="submit" value="CANCEL MEMBERSHIP" />
	  		</p>
	  		
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