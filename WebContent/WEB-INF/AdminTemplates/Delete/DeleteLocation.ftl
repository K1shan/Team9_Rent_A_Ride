<div class="modal">
	<div class="tab">
		<label class="head">DELETE LOCATION </label>
		<form class="form" id="admin" action="LocationDelete" method='post' name="admin">
			<p class="float-label">
	    			<input type="text" id="id" name="id" placeholder="LOCATION ID"/>
	  		</p>
	  		<p>
	    			<input class = "type" type="submit" onclick="return locationDeleteNullCheck()" value="DELETE LOCATION" />
	  		</p>
	  		<#if statusDeleteLocationG??>
						
				<p class="good">
   					<label> ${statusDeleteLocationG} </label>
  				</p>
							
			</#if>
	  				
			<#if statusDeleteLocationB??>
				
				<p class="error">
					 <label> ${statusDeleteLocationB} </label>
				</p>
					
			</#if>
		</form>
	</div>
</div>

