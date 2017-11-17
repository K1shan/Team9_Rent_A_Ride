<div class="modalTwo">
	<div class="tab">
		<label class="head">UPDATE VEHICLE TYPE </label>
		<form class="form" id="admin" action="VehicleTypeUpdate" method='post' name="admin">
						
			<select id="select1" name="select1" class="minimal">
					<option value="">Select</option>
			</select>
						
			<p class="float-label">
					<input type="text" id="type" name="type" placeholder="Vehicle Type"/>
			</p>
			<p>
				<input class = "type" type="submit" onclick="return typeUpdateNullCheck()" value="UPDATE VEHICLE TYPE" />
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