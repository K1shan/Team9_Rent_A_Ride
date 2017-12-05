<div class="modalThree">
	<div class="tab">
		<label class="head">RENEW MEMBERSHIP</label>
		<form class="form" id="admin" action="MembershipRenew" method='post' name="admin">
			
			<#if memberFee??>
			<p class="float-label">
				You will be charged $${memberFee} upon renewel.
			</p>
			</#if>
		
  			<p>
    			<input class = "type" type="submit" value="RENEW MEMBERSHIP" />
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