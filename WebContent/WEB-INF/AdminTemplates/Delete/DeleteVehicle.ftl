<div class="modalThree">
	<div class="tab">
		<label class="head">DELETE VEHICLE </label>
		<form class="form" id="admin" action="VehicleDelete" method='post' name="admin">
		
			<select id="selectVehicleDelete" name="selectVehicleDelete" class="minimal" required >
					<option value="">Select Vehicle</option>
			</select>
	  		<p>
	    			<input class = "type" type="submit" onclick="return vehicleDeleteNullCheck()" value="DELETE VEHICLE" required />
	  		</p>
	  		<#if statusDeleteVehicleG??>
						
				<p class="good">
   					<label> ${statusDeleteVehicleG} </label>
  				</p>
							
			</#if>
	  				
			<#if statusDeleteVehicleB??>
				
				<p class="error">
					 <label> ${statusDeleteVehicleB} </label>
				</p>
					
			</#if>
		</form>
	</div>
</div>

