<div class="modalTwo">
	 <div class="tab">
		<label class="head">ADD LOCATION</label>	
		<form enctype="multipart/form-data" class="form" id="location" action="LocationCreate" method='post' name="location">
  				
			<p class="float-label">
				<input type="text" id="nameAdd" name="nameAdd" placeholder="NAME" required />
			</p>
			
			<p class="float-label">
				<input type="text" id="addressAdd" name="addressAdd" placeholder="ADDRESS" required />
			</p>
			
			<p class="float-label">
				<input type="text" id="cityAdd" name="cityAdd" placeholder="CITY" required />
			</p>
			
			<p class="float-label">
				<input type="text" id="stateAdd" name="stateAdd" placeholder="STATE" required />
			</p>
			
			<p class="float-label">
				<input type="text" id="zipAdd" name="zipAdd" placeholder="ZIP CODE" required />
			</p>
			
			<p class="float-label">
				<input type="text" id="avaAdd" name="avaAdd" placeholder="AVAIILABILITY" required />
			</p>
			
			<p>
				<input type="file" id="picAdd" name="picAdd" required />
			</p>	
				
			<p>
				<input class = "location" type="submit" onclick="return locationCreateNullCheck()" value="ADD LOCATION" />
			</p>
		
			<#if statusAddLocationG??>
				
				<p class="good">
   					<label> ${statusAddLocationG} </label>
  				</p>
					
			</#if>
			
			<#if statusAddLocationB??>
			
				<p class="error">
					 <label> ${statusAddLocationB} </label>
				</p>
				
			</#if>
		</form>
	</div>
</div>