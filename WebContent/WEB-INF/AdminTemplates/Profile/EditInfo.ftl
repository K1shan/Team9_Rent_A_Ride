<div class="modalThree">
	<div class="tab">
		<label class="head">UPDATE INFORMATION </label>
		<form class="form" id="admin" action="AdminInfo" method='post' name="admin">

				<p class="float-label">
	    				<input type="email" id="emailInfo" name="email" placeholder="Email"/>
	 			</p>
	 			

				<p class="float-label">
	    			 	<input type="text" id="stateInfo" name="state" placeholder="State"/>
				</p>
				

	  			<p class="float-label">
	    				<input type="text" id="licNumInfo" name="licNum" placeholder="License Number"/>
	  			</p>
	  			

	 	 		<p class="float-label">
	    				<input type="text" id="ccNumInfo" name="ccNum" placeholder="Cradit Card Number"/>
	 			</p>
	 			

	  			<p class="float-label">
	    				<input type="text" id="ccExpInfo" name="ccExp" placeholder="Cradit Card Exp Date"/>
	  			</p>
	  			
	  			<p>
		    			<input class = "type" type="submit" onclick="return adminUpdateInfoNullCheck()" value="UPDATE INFO" />
		  		</p>

	  		<#if statusUpdateInfoG??>			
				<p class="good">
   					<label> ${statusUpdateInfoG} </label>
  				</p>
			</#if>
			<#if statusUpdateInfoB??>
				<p class="error">
					 <label> ${statusUpdateInfoB} </label>
				</p>
			</#if>
		</form>
	</div>
</div>
