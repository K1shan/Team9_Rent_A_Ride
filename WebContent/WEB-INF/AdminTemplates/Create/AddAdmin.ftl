<div class="modal">
	<div class="tab">
		<label class="head">ADD ADMIN</label>
		<form class="form" id="admin" action="AdminCreate" method='post' name="admin">
			
			<p class="float-label">
				<input type="text" id="email" name="email" placeholder="Email"/>
			</p>
			<p>
				<input class = "admin" type="submit" onclick="return adminCreateNullCheck()" value="ADD ADMIN" />
			</p>
  				
			<#if statusAddAdminG??>
				
				<p class="good">
					<label> ${statusAddAdminG} </label>
				</p>
					
			</#if>
  				
			<#if statusAddAdminB??>
			
				<p class="error">
			 		<label> ${statusAddAdminB} </label>
				</p>
				
			</#if>	
		</form>
	</div>
 </div>