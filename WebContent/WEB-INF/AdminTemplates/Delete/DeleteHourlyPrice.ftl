<div class="modalFour">
	<div class="tab">
		<label class="head">DELETE HOURLY PRICE </label>
		<form class="form" id="admin" action="HourlyPriceDelete" method='post' name="admin">
		
			<select id="selectHourlyPriceDelete" name="selectHourlyPriceDelete" class="minimal">
					<option value="">Select Hourly Price</option>
			</select>
			
	  		<p>
	    			<input class = "type" type="submit" onclick="return hourlypriceDeleteNullCheck()" value="DELETE HOURLY PRICE" />
	  		</p>
	  		
	  		<#if statusDeleteHourlyPriceG??>
						
				<p class="good">
   					<label> ${statusDeleteHourlyPriceG} </label>
  				</p>
							
			</#if>
	  				
			<#if statusDeleteHourlyPriceB??>
				
				<p class="error">
					 <label> ${statusDeleteHourlyPriceB} </label>
				</p>
					
			</#if>
		</form>
	</div>
</div>

