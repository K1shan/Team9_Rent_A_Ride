<div class="modalTwo">
			<div class="tab">
					<label class="head">DELETE VEHICLE TYPE </label>
					<form class="form" id="admin" action="VehicleTypeDelete" method='post' name="admin">
					
					
						<select id="select2" name="select2" class="minimal">
								<option value="">View Only</option>
						</select>
					
		  				<p class="float-label">
		    					<input type="text" id="type" name="type" placeholder="Vehicle Type ID"/>
		  				</p>
		  				<p>
		    				<input class = "type" type="submit" onclick="return typeDeleteNullCheck()" value="DELETE VEHICLE TYPE" />
		  				</p>
		  				
		  				<#if statusDeleteTypeB??>
							
							<p class="good">
			   					<label> ${statusDeleteTypeB} </label>
			  				</p>
								
						</#if>
		  				
						<#if statusDeleteTypeB??>
						
							<p class="error">
		   						 <label> ${statusDeleteTypeB} </label>
		  					</p>
							
						</#if>
					</form>
		  		</div>
			</div>
