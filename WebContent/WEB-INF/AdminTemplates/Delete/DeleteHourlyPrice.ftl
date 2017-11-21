<div class="modalFour">
	<div class="tab">
		<label class="head">DELETE HOURLY PRICE </label>
		<form class="form" id="admin" action="HourlyPriceDelete" method='post' name="admin">
		
			<select id="selectHourlyPriceDelete" name="selectHourlyPriceDelete" class="minimal">
					<option value="">Select Location</option>
			</select>
			
	  		<p>
	    			<input class = "type" type="submit" onclick="return hourlypriceDeleteNullCheck()" value="DELETE LOCATION" />
	  		</p>
	  		
	  		<#if statusDeleteLocationG??>
						
				<p class="good">
   					<label> ${statusDeleteLocationG} </label>
  				</p>
							
			</#if>
	  				
			<#if statusDeleteLocationB??>
				
				<p class="error">
					 <label> ${statusDeleteLocationB} </label>
				</p>
					
			</#if>
		</form>
	</div>
</div>

