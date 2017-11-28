<div class="modalSix">
	<div class="tab">
		<label class="head"> UPDATE LATE FEE </label>
		<form class="form" id="admin" action="LateFeeUpdate" method='post' name="admin">
			
			<p class="float-label">
				<input type="text" id="latefeeUpdate" name="latefeeUpdate" placeholder="NEW LATE FEE"/>
			</p>		
			
			<p>
				<input class = "type" type="submit" onclick="return latefeeUpdateNullCheck()" value="UPDATE LATE FEE" />
			</p>
			  				
			<#if statusUpdateLatfeeG??>
				
				<p class="good">
					<label> ${statusUpdateLatfeeG} </label>
				</p>
					
			</#if>
			  				
			<#if statusUpdateLatfeeB??>
			
				<p class="error">
					 <label> ${statusUpdateLatfeeB} </label>
				</p>
				
			</#if>
		</form>
	</div>
</div>