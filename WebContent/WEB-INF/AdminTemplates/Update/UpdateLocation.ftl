<div class="modal">
		 <div class="tab">
			<label class="head">UPDATE LOCATION</label>
			
			<form enctype="multipart/form-data" class="form" id="location" action="LocationUpdate" method='post' name="location">
			
  				<select id="selectLocationUpdate" name="selectLocationUpdate" class="minimal">
					<option value="">Select Location</option>
				</select>
				
  				<p class="float-label">
    					<input type="text" id="nameUpdate" name="nameUpdate" placeholder="NAME"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="addressUpdate" name="addressUpdate" placeholder="ADDRESS"/>
  				</p>
  				
 				<p class="float-label">
    					<input type="text" id="cityUpdate" name="cityUpdate" placeholder="CITY"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="stateUpdate" name="stateUpdate" placeholder="STATE"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="zipUpdate" name="zipUpdate" placeholder="ZIP CODE"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="avaUpdate" name="avaUpdate" placeholder="AVAILABILITY"/>
  				</p>
  				
  				<p>
    					<input type="file" id="picUpdate" name="picUpdate"/>
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