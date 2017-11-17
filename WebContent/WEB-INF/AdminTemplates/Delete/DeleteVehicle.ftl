<div class="modalThree">
	<div class="tab">
		<label class="head">DELETE VEHICLE </label>
		<form class="form" id="admin" action="LocationDelete" method='post' name="admin">
			<p class="float-label">
	    			<input type="text" id="id" name="id" placeholder="VEHICLE ID"/>
	  		</p>
	  		<p>
	    			<input class = "type" type="submit" onclick="return vehicleDeleteNullCheck()" value="DELETE VEHICLE" />
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

