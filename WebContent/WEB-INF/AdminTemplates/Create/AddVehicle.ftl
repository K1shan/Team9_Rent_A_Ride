<div class="modalFour">
	<div class="tab">
		<label class="head">ADD VEHICLE </label>
		<form class="form" id="admin" action="VehicleCreate" method='post' name="admin">
			
			<select id="selectVehicleType" name="selectVehicleType" class="minimal">
					<option value="">Select</option>
			</select>
								
			<p class="float-label">
				<input type="text" id="make" name="make" placeholder="Make"/>
			</p>
			  		
			<p class="float-label">
				<input type="text" id="model" name="model" placeholder="Model"/>
			</p>	
				
			<p class="float-label">
				<input type="text" id="year" name="year" placeholder="Year"/>
			</p>	
			
			<p class="float-label">
				<input type="text" id="mileage" name="mileage" placeholder="Mileage"/>
			</p>		
			
			<select id="selectLocation" name="selectLocation" class="minimal">
				<option value="">Select</option>
			</select>
			
			<p class="float-label">
				<input type="text" id="tag" name="tag" placeholder="Tag"/>
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