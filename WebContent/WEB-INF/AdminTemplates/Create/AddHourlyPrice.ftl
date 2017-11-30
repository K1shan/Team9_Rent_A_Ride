<div class="modalFive">
	<div class="tab">
		<label class="head"> ADD HOURLY PRICE </label>
		<form class="form" id="admin" action="HourlyPriceCreate" method='post' name="admin">

			<p class="float-label">
				<select id="selectHourlyPriceVehicleTypeAdd" name="selectHourlyPriceVehicleTypeAdd" class="minimal" required >
						<option value="">Select Vehicle Type</option>
				</select>
			</p>
			
			<p class="float-label">
				<select id="selectHourlyPriceHourAdd" name="selectHourlyPriceHourAdd" class="minimal" required >
						<option value="">Select Hours</option>
						<option value="24">Twinty-Four (24)</option>
						<option value="48">Fourty-Eight (48)</option>
						<option value="72">Sevehnty-Two (72)</option>
				</select>
			</p>
			<p class="float-label">
				<input type="text" id="hourlyPricePriceAdd" name="hourlyPricePriceAdd" placeholder="PRICE" required />
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