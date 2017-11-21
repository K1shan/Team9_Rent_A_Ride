<div class="modalFive">
	<div class="tab">
		<label class="head"> ADD HOURLY PRICE </label>
		<form class="form" id="admin" action="HourlyPriceCreate" method='post' name="admin">
			
			<select id="selectHourlyPriceVehicleTypeAdd" name="selectHourlyPriceVehicleTypeAdd" class="minimal">
					<option value="">Select</option>
			</select>
			
			<select id="hourlyPrice" name="hourlyPrice" class="minimal">
					<option value="">Select</option>
					<option value="24">Twinty-Four (24)</option>
					<option value="42">Fourty-Two (42)</option>
					<option value="78">Seventy-Eight (78)</option>
			</select>
			
			<p class="float-label">
				<input type="text" id="vehiclePrice" name="vehiclePrice" placeholder="PRICE"/>
			</p>
			
			<p>
				<input class = "type" type="submit" onclick="return hourlypriceCreateNullCheck()" value="ADD HOURLY PRICE" />
			</p>
			  				
			<#if statusAddHourlyPriceG??>
				
				<p class="good">
					<label> ${statusAddHourlyPriceG} </label>
				</p>
					
			</#if>
			  				
			<#if statusAddHourlyPriceB??>
			
				<p class="error">
					 <label> ${statusAddHourlyPriceB} </label>
				</p>
				
			</#if>
		</form>
	</div>
</div>