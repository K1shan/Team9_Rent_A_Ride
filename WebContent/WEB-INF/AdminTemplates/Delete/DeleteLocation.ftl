<div class="modal">
	<div class="tab">
		<label class="head">DELETE LOCATION </label>
		<form class="form" id="admin" action="LocationDelete" method='post' name="admin">
		
			<select id="selectLocationDelete" name="selectLocationDelete" class="minimal" required >
					<option value="">Select Location</option>
			</select>
	  		<p>
	    			<input class = "type" type="submit" onclick="return locationDeleteNullCheck()" value="DELETE LOCATION" required />
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

