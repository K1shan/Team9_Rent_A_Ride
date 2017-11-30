<div class="modalFour">
	<div class="tab">
		<label class="head">UPDATE CUSTOMER </label>
		<form class="form" id="admin" action="CustomerStatusUpdate" method='post' name="admin">
			
			<p class="float-label">
				<select id="selectCustomerUpdateAdmin" name="selectCustomerUpdateAdmin" class="minimal" required >
						<option value="">Select Customer</option>
				</select>
			</p>
			
			<p class="float-label">
				<select id="selectCustomerStatusUpdate" name="selectCustomerStatusUpdate" class="minimal" required >
					<option value="">Customer Status</option>
					<option value="ACTIVE">ACTIVE</option>
					<option value="TERMINATED">TERMINATED</option>
				</select>
			</p>
			
			<p>
				<input class = "type" type="submit" onclick="return customerUpdateNullCheck()" value="UPDATE CUSTOMER" />
			</p>
			  				
			<#if statusUpdateCustomerStatusG??>
				<p class="good">
					<label> ${statusUpdateCustomerStatusG} </label>
				</p>
			</#if>
			<#if statusUpdateCustomerStatusB??>
				<p class="error">
					 <label> ${statusUpdateCustomerStatusB} </label>
				</p>
				
			</#if>
		</form>
	</div>
</div>

