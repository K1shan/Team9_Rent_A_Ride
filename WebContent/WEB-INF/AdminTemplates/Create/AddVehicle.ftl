<div class="modalFour">
	<div class="tab">
		<label class="head">ADD VEHICLE </label>
		<form class="form" id="admin" action="VehicleCreate" method='post' name="admin">
			
			<select id="selectVehicleTypeAdd" name="selectVehicleTypeAdd" class="minimal">
					<option value="">Select</option>
			</select>
								
			<p class="float-label">
				<input type="text" id="makeAdd" name="makeAdd" placeholder="Make"/>
			</p>
			  		
			<p class="float-label">
				<input type="text" id="modelAdd" name="modelAdd" placeholder="Model"/>
			</p>	
				
			<p class="float-label">
				<input type="text" id="yearAdd" name="yearAdd" placeholder="Year"/>
			</p>	
			
			<p class="float-label">
				<input type="text" id="mileageAdd" name="mileageAdd" placeholder="Mileage"/>
			</p>
			
			<select id="selectLocationAdd" name="selectLocationAdd" class="minimal">
				<option value="">Select</option>
			</select>
			
			<p class="float-label">
				<input type="text" id="tagAdd" name="tagAdd" placeholder="Tag"/>
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