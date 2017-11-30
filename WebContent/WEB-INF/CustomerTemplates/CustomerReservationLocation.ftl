<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride | Admin View</title>
<link href='cssfiles/admin.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/location.css' rel='stylesheet' type='text/css'>

<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="Javascript/Navbar/navbar.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
<script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>

<script type = "text/javascript" src="Javascript/AdminReservation/createReservations.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminReservationNull/addReservationAdmin.js"></script>


</head>

<#include "CustomerNavbar.ftl">

<body>	
	
	<h1 id = "product"> RESERVATION </h1>

	<div class="modalReservation">
	<div class="tab">
	<label class="head">CREATE RESERVATION</label>	
	<form class="form" id="admin" action="ReservationCustomerCreate" method='post' name="admin">
	
  			<#if locationId??>
  				<input type="hidden" name="locationId" value='${locationId}' />
			</#if>
				
  			 <select id="selectReservationVehicleTypeAdmin" name="selectReservationVehicleTypeAdd" class="minimal">
				<option value="">Select Vehicle Type</option>
  				<#if vehicleTypesAvail??>
  					<#list vehicleTypesAvail as type>
						<option value="${type.id}">${type.name}</option>
					</#list>
  				</#if>
			</select>
			
			<p class="float-label">
				<input type="text" id="dateAdmin" name="date" placeholder="PICK DATE">
			</p>
			
			<p class="float-label">
				<input type="text" id="timeAdmin" name="time" placeholder="PICK TIME">
			</p>
			
			<select id="selectReservationLengthAdmin" name="selectReservationLengthAdd" class="minimal">
					<option value="">Select Hours</option>
					<option value="24">Twinty-Four (24)</option>
					<option value="48">Fourty-Eight (48)</option>
					<option value="72">Seventy-Two (72)</option>
			</select>
			
			<p>
				<input class = "type" type="submit" onclick="return reservationAdminCreateNullCheck()" value="CREATE RESERVATION" />
			</p>
		
			<#if statusCustomersCreateReservationG??>
				
				<p class="good">
   					<label> ${statusCustomersCreateReservationG} </label>
  				</p>
					
			</#if>
			
			<#if statusCustomersCreateReservationB??>
			
				<p class="error">
					 <label> ${statusCustomersCreateReservation} </label>
				</p>
				
			</#if>
		</form>
		</div>
	</div>

</body>
</html>
