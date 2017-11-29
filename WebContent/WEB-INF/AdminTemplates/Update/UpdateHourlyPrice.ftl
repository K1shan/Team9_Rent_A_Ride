<div class="modalSeven">
	<div class="tab">
		<label class="head"> UPDATE HOURLY PRICE </label>
		<form class="form" id="admin" action="HourlyPriceUpdate" method='post' name="admin">
			
			<p class="float-label">
			
				<select id="selectHourlyPriceUpdate" name="selectHourlyPriceUpdate" class="minimal">
						<option value="">Select Hourly Price</option>
				</select>
			</p>
			
			<p class="float-label">
				<select id="selectHourlyPriceVehicleTypeUpdate" name="selectHourlyPriceVehicleTypeUpdate" class="minimal">
						<option value="">Select New Vehicle Type</option>
				</select>
			</p>
			
			<p class="float-label">
				<select id="selectHourlyPriceHourUpdate" name="selectHourlyPriceHourUpdate" class="minimal">
						<option value="">Select New Hours</option>
						<option value="24">Twinty-Four (24)</option>
						<option value="48">Fourty-Eight (48)</option>
						<option value="72">Seventy-Two (72)</option>
				</select>
			</p>
			
			<p class="float-label">
				<input type="text" id="hourlyPricePriceUpdate" name="hourlyPricePriceUpdate" placeholder="New PRICE"/>
			</p>
			
			
			<p>
				<input class = "type" type="submit" onclick="return hourlypriceUpdateNullCheck()" value="UPDATE HOURLY PRICE" />
			</p>
			  				
			<#if statusUpdateHourlyPriceG??>
				
				<p class="good">
					<label> ${statusUpdateHourlyPriceG} </label>
				</p>
					
			</#if>
			  				
			<#if statusUpdateHourlyPriceB??>
			
				<p class="error">
					 <label> ${statusUpdateHourlyPriceB} </label>
				</p>
				
			</#if>
		</form>
	</div>
</div>