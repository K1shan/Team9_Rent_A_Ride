<div class="modalTwo">
	 <div class="tab">
		<label class="head">ADD LOCATION</label>	
		<form enctype="multipart/form-data" class="form" id="location" action="LocationCreate" method='post' name="location">
  				
			<p class="float-label">
				<input type="text" id="nameAdd" name="nameAdd" placeholder="NAME"/>
			</p>
			
			<p class="float-label">
				<input type="text" id="addressAdd" name="addressAdd" placeholder="ADDRESS"/>
			</p>
			
			<p class="float-label">
				<input type="text" id="cityAdd" name="cityAdd" placeholder="CITY"/>
			</p>
			
			<p class="float-label">
				<input type="text" id="stateAdd" name="stateAdd" placeholder="STATE"/>
			</p>
			
			<p class="float-label">
				<input type="text" id="zipAdd" name="zipAdd" placeholder="ZIP CODE"/>
			</p>
			
			<p class="float-label">
				<input type="text" id="avaAdd" name="avaAdd" placeholder="AVAIILABILITY"/>
			</p>
			
			<p>
				<input type="file" id="picAdd" name="picAdd"/>
			</p>			
			<p>
				<input class = "location" type="submit" onclick="return locationCreateAddNullCheck()" value="ADD LOCATION" />
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