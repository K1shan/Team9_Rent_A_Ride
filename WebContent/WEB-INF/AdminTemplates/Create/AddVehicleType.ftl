<div class="modalThree">
	<div class="tab">
		<label class="head">ADD VEHICLE TYPE </label>
		<form class="form" id="admin" action="VehicleTypeCreate" method='post' name="admin">
		
			<select id="selectVehicleTypeAdd" name="selectVehicleTypeAdd" class="minimal">
					<option value="">View Only</option>
			</select>
								
			<p class="float-label">
					<input type="text" id="type" name="type" placeholder="Vehicle Type Name"/>
			</p>
			  				
			<p>
				<input class = "type" type="submit" onclick="return typeCreateNullCheck()" value="ADD VEHICLE TYPE" />
			</p>
			  				
			<#if statusAddTypeG??>
				
				<p class="good">
					<label> ${statusAddTypeG} </label>
				</p>
					
			</#if>
			  				
			<#if statusAddTypeB??>
			
				<p class="error">
					 <label> ${statusAddTypeB} </label>
				</p>
				
			</#if>
		</form>
	</div>
</div>