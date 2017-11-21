<div class="modalTwo">
	<div class="tab">
		<label class="head">UPDATE VEHICLE TYPE </label>
		<form class="form" id="admin" action="VehicleTypeUpdate" method='post' name="admin">
						
			<select id="selectVehicleTypeUpdate" name="selectVehicleTypeUpdate" class="minimal">
					<option value="">Select Vehicle Type</option>
			</select>
						
			<p class="float-label">
					<input type="text" id="vehicleTypeUpdate" name="vehicleTypeUpdate" placeholder="New Vehicle Type Name"/>
			</p>
			<p>
				<input class = "type" type="submit" onclick="return vehicleUpdateTypeNullCheck()" value="UPDATE VEHICLE TYPE" />
			</p>
			  				
			<#if statusUpdateTypeG??>
				
				<p class="good">
					<label> ${statusUpdateTypeG} </label>
				</p>
					
			</#if>
			  				
			<#if statusUpdateTypeB??>
			
				<p class="error">
					 <label> ${statusUpdateTypeB} </label>
				</p>
				
			</#if>
		</form>
	</div>
</div>