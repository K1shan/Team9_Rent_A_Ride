<div class="modalThree">
	<div class="tab">
		<label class="head">UPDATE VEHICLE </label>
		<form class="form" id="admin" action="VehicleUpdate" method='post' name="admin">
			
			
			<select id="selectVehicleUpdate" name="selectVehicleUpdate" class="minimal">
					<option value="">Select Vehicle</option>
			</select>
				
			<p class="float-label">
				<input type="text" id="makeUpdate" name="makeUpdate" placeholder="Make"/>
			</p>
			  		
			<p class="float-label">
				<input type="text" id="modelUpdate" name="modelUpdate" placeholder="Model"/>
			</p>	
				
			 <select id="selectVehicleTypeUpdate" name="selectVehicleTypeUpdate" class="minimal">
				<option value="">Select Vehicle Type</option>
			</select>
				
			<p class="float-label">
				<input type="text" id="yearUpdate" name="yearUpdate" placeholder="Year"/>
			</p>	

			<p class="float-label">
				<input type="text" id="mileageUpdate" name="mileageUpdate" placeholder="Mileage"/>
			</p>
			
			<select id="selectLocationUpdate" name="selectLocationUpdate" class="minimal">
				<option value="">Select Location</option>
			</select>
						
			<p class="float-label">
				<input type="text" id="tagUpdate" name="tagUpdate" placeholder="Tag"/>
			</p>
		
			<p>
				<input class = "type" type="submit" onclick="return vehicleUpdateNullCheck()" value="UPDATE VEHICLE" />
			</p>
			  				
			<#if statusUpdateVehicleG??>
				
				<p class="good">
					<label> ${statusUpdateVehicleG} </label>
				</p>
					
			</#if>
			  				
			<#if statusUpdateVehicleB??>
			
				<p class="error">
					 <label> ${statusUpdateVehicleB} </label>
				</p>
				
			</#if>
		</form>
	</div>
</div>