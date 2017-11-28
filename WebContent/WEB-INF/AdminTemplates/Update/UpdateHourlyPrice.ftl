<div class="modalFive">
	<div class="tab">
		<label class="head"> UPDATE HOURLY PRICE </label>
		<form class="form" id="admin" action="HourlyPriceCreate" method='post' name="admin">
			
			<p class="float-label">
			
				<select id="hourlypriceUpdate" name="hourlypriceUpdate" class="minimal">
						<option value="">Select</option>
						<option value="temp">TEMP</option>
				</select>
			</p>
			
			<p class="float-label">
				<select id="hourlyPriceUpdate" name="hourlyPriceUpdate" class="minimal">
						<option value="">Select</option>
						<option value="24">Twinty-Four (24)</option>
						<option value="42">Fourty-Two (42)</option>
						<option value="78">Seventy-Eight (78)</option>
				</select>
			</p>
			
			<p class="float-label">
				<input type="text" id="vehiclePriceUpdate" name="vehiclePriceUpdate" placeholder="PRICE"/>
			</p>
			
			<p class="float-label">
				<select id="selectHourlyPriceUpdate" name="selectHourlyPriceUpdate" class="minimal">
						<option value="">Select</option>
				</select>
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