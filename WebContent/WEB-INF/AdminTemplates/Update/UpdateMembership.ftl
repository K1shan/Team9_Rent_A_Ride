<div class="modalFive">
	<div class="tab">
		<label class="head"> UPDATE MEMEMBERSHIP </label>
		<form class="form" id="admin" action="MembershipFeeUpdate" method='post' name="admin">
			
			<p class="float-label">
				<input type="text" id="membershipUpdate" name="membershipUpdate" placeholder="New PRICE"/>
			</p>		
			
			<p>
				<input class = "type" type="submit" onclick="return membershipCreateNullCheck()" value="UPDATE MEMBERSHIP PRICE" />
			</p>
			  				
			<#if statusUpdateMembershipG??>
				
				<p class="good">
					<label> ${statusUpdateMembershipG} </label>
				</p>
					
			</#if>
			  				
			<#if statusUpdateMembershipB??>
			
				<p class="error">
					 <label> ${statusUpdateMembershipB} </label>
				</p>
				
			</#if>
		</form>
	</div>
</div>