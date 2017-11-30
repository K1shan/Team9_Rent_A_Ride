<div class="modalEight">
	<div class="tab">
		<label class="head">UPDATE RESERVATION NO SHOW</label>
		<form class="form" id="admin" action="ReservationUpdateNoShow" method='post' name="admin">
						
			<select id="selectReservationNoShowUpdate" name="selectReservationNoShowUpdate" class="minimal">
				<option value="">Select Reservation No Show</option>
			</select>
						
			<p>
				<input class = "type" type="submit" onclick="return reservationNoShowUpdateNullCheck()" value="UPDATE RESERVATION NO SHOW" />
			</p>
			  				
			<#if statusUpdateReservationNoShowG??>
			<p class="good">
				<label> ${statusUpdateReservationNoShowG} </label>
			</p>
			</#if>
			  				
			<#if statusUpdateReservationNoShowB??>
			<p class="error">
				<label> ${statusUpdateReservationNoShowB} </label>
			</p>
			</#if>
		</form>
	</div>
</div>