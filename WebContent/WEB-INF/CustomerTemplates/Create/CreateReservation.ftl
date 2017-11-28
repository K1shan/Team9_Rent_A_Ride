<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride</title>
<link href='index.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/admin.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="Javascript/Navbar/navbar.js"></script>
<link href='cssfiles/location.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/additionalCustomer.css' rel='stylesheet' type='text/css'>


</head>

<body>

	<div id="header"></div>
	
	<h1 id = "product">Reservation </h1>
	
	<div class="modalTwo">
	 <div class="tab">
		<label class="head">CREATE RESERVATION</label>	
		
		<form class="form" id="admin" action="ReservationCustomerCreate" method='post' name="admin">
  			
  			<#if locationId??>
  				<input type="hidden" name="locationId" value='${locationId}' />
			</#if>	
  			 <select id="selectReservationVehicleTypeAdd" name="selectReservationVehicleTypeAdd" class="minimal">
				<option value="">Select Vehicle Type</option>
  				<#if vehicleTypesAvail??>
  					<#list vehicleTypesAvail as type>
						<option value="${type.id}">${type.name}</option>
					</#list>
  				</#if>
			</select>
			
			<p class="float-label">
				<input type="text" id="pickup" name="pickup" placeholder="PICKUP TIME"/>
			</p>
			
			<select id="selectReservationLengthAdd" name="selectReservationLengthAdd" class="minimal">
					<option value="">Select Hours</option>
					<option value="24">Twinty-Four (24)</option>
					<option value="48">Fourty-Eight (48)</option>
					<option value="72">Seventy-Two (72)</option>
			</select>
			
			<p>
				<input class = "type" type="submit" onclick="return reservationCreateNullCheck()" value="CREATE RESERVATION" />
			</p>
		
			<#if statusCreateReservationG??>
				
				<p class="good">
   					<label> ${statusCreateReservationG} </label>
  				</p>
					
			</#if>
			
			<#if statusCreateReservationB??>
			
				<p class="error">
					 <label> ${statusCreateReservationB} </label>
				</p>
				
			</#if>
		</form>
	</div>
</div>
</body>
</html>