<div class="modal">
		 <div class="tab">
			<label class="head">UPDATE LOCATION</label>
			
			<form enctype="multipart/form-data" class="form" id="location" action="LocationUpdate" method='post' name="location">
			
  				<select id="selectLocationUpdate" name="selectLocationUpdate" class="minimal" required>
					<option value="">Select Location</option>
				</select>
				
  				<p class="float-label">
    					<input type="text" id="nameUpdateLocation" name="nameUpdate" placeholder="NAME" required />
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="addressUpdate" name="addressUpdate" placeholder="ADDRESS" required />
  				</p>
  				
 				<p class="float-label">
    					<input type="text" id="cityUpdate" name="cityUpdate" placeholder="CITY"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="stateUpdate" name="stateUpdate" placeholder="STATE" required />
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="zipUpdate" name="zipUpdate" placeholder="ZIP CODE" required />
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="avaUpdate" name="avaUpdate" placeholder="AVAILABILITY" required />
  				</p>
  				
  				<p>
    					<input type="file" id="picUpdate" name="picUpdate" required/>
  				</p>			
  				<p>
    					<input class = "location" type="submit" onclick="return locationUpdateNullCheck()" value="UPDATE LOCATION" />
  				</p>
  				
  					<#if statusUpdateLocationG??>
					
						<p class="good">
	   						 <label> ${statusUpdateLocationG} </label>
	  					</p>
						
					</#if>
  				
  					<#if statusUpdateLocationB??>
					
						<p class="error">
	   						 <label> ${statusUpdateLocationB} </label>
	  					</p>
						
					</#if>

			</form>
		</div>
	</div>