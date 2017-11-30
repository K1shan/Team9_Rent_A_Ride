<div class="modalFour">
	<div class="tab">
		<label class="head">ADD VEHICLE </label>
		<form class="form" id="admin" action="VehicleCreate" method='post' name="admin">
			
			<select id="selectVehicleVehicleTypeAdd" name="selectVehicleVehicleTypeAdd" class="minimal" required >
					<option value="">Select Vehicle Type</option>
			</select>
								
			<p class="float-label">
				<input type="text" id="makeAdd" name="makeAdd" placeholder="Make" required />
			</p>
			  		
			<p class="float-label">
				<input type="text" id="modelAdd" name="modelAdd" placeholder="Model" required />
			</p>	
				
			<p class="float-label">
				<input type="text" id="yearAdd" name="yearAdd" placeholder="Year" required />
			</p>	
			
			<p class="float-label">
				<input type="text" id="mileageAdd" name="mileageAdd" placeholder="Mileage" required />
			</p>
			
			<select id="selectVehicleLocationAdd" name="selectVehicleLocationAdd" class="minimal" required >
				<option value="">Select Vehicle Location</option>
			</select>
			
			<p class="float-label">
				<input type="text" id="tagAdd" name="tagAdd" placeholder="Tag" required />
			</p>
		
		
			<p>
				<input class = "type" type="submit" onclick="return vehicleCreateNullCheck()" value="ADD VEHICLE" />
			</p>
			  				
			<#if statusAddVehicleG??>
				
				<p class="good">
					<label> ${statusAddVehicleG} </label>
				</p>
					
			</#if>
			  				
			<#if statusAddVehicleB??>
			
				<p class="error">
					 <label> ${statusAddVehicleB} </label>
				</p>
				
			</#if>
		</form>
	</div>
</div>