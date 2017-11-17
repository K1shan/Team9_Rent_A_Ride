<div class="modalThree">
	<div class="tab">
		<label class="head">UPDATE VEHICLE </label>
		<form class="form" id="admin" action="VehicleCreate" method='post' name="admin">
			
			<select id="selectVehicleTypeUpdate" name="selectVehicleTypeUpdate" class="minimal">
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
			
			<select id="selectLocationUpdate" name="selectLocationUpdate" class="minimal">
				<option value="">Select</option>
			</select>
			
			<p class="float-label">
				<input type="text" id="tag" name="tag" placeholder="Tag"/>
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