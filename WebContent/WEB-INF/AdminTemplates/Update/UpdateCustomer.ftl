<div class="modalFour">
	<div class="tab">
		<label class="head">UPDATE CUSTOMER </label>
		<form class="form" id="admin" action="CustomerStatusUpdate" method='post' name="admin">
			
			<p class="float-label">
				<select id="selectCustomerUpdate" name="selectCustomerUpdate" class="minimal">
						<option value="">Select Customer</option>
				</select>
			</p>
			
			<p class="float-label">
				<select id="selectCustomerStatusUpdate" name="selectCustomerStatusUpdate" class="minimal">
					<option value="">Customer Status</option>
					<option value="ACTIVE">ACTIVE</option>
					<option value="TERMINATED">TERMINATED</option>
				</select>
			</p>
			
			<p>
				<input class = "type" type="submit" onclick="return custoemerUpdateNullCheck()" value="UPDATE CUSTOMER" />
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