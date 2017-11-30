<div class="modalTwo">
	<div class="tab">
		<label class="head">DELETE VEHICLE TYPE </label>
		<form class="form" id="admin" action="VehicleTypeDelete" method='post' name="admin">
		
		
			<select id="selectVehicleTypeDelete" name="selectVehicleTypeDelete" class="minimal" required >
					<option value="">Select Vehicle Type</option>
			</select>
			
			<p>
				<input class = "type" type="submit" onclick="return typeDeleteNullCheck()" value="DELETE VEHICLE TYPE" required />
			</p>
			
			<#if statusDeleteVehicleTypeG??>
				
				<p class="good">
   					<label> ${statusDeleteVehicleTypeG} </label>
  				</p>
					
			</#if>
			
			<#if statusDeleteVehicleTypeB??>
			
				<p class="error">
					 <label> ${statusDeleteVehicleTypeB} </label>
				</p>
				
			</#if>
		</form>
	</div>
</div>
