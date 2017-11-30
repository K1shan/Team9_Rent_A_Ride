<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride | Customer</title>
<link href='cssfiles/locationView.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/additionalCustomer.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
</head>

<#include "CustomerNavbar.ftl">

<body>
	<h1 id = "product"> VIEW Reservations </h1>
	
	<#if reservations??>
	   			<#list reservations as reservation>

		 <div class="ui">
		 	<div class="screen">
	 			<div class="product-des">
	 				<form id = "formOne" action="" method="post">
	 				
	 					<div class="product-name" name="reservationId" value='${reservation.id}'>${reservation.rentalLocation.name}</div>
	 					<div class="product-description">${reservation.vehicleType.name}</div>
	 					<div class="product-description">Pickup Time: ${reservation.pickupTime}</div>
	 					<div class="product-description">Length: ${reservation.length}</div>
					</form>
					
					<form id = "formOne" action="CustomerPickup" method="post">
	 				
		  				<input type="hidden" name="reservationId" value='${reservation.id}' />
		  				<input type="hidden" name="reservationVehicleTypeId" value='${reservation.vehicleType.id}' />
		  				<input type="hidden" name="pickupTime" value='${reservation.pickupTime}' />
		  				<input type="hidden" name="pickupTime" value='${reservation.length}' />
	 					<div class="product-cta">
	 						<input type="submit" class = "cta" value="PICKUP RENTAL" />
	 					</div>
					</form>
					
					<form id = "formOne" action="CustomerReturn" method="post">
	 				
						<input type="hidden" name="reservationId" value='${reservation.id}' />
		  				<input type="hidden" name="reservationVehicleTypeId" value='${reservation.vehicleType.id}' />
		  				<input type="hidden" name="pickupTime" value='${reservation.pickupTime}' />
		  				<div class="product-cta">
	 						<input type="submit" class = "cta" value="RETURN RENTAL" />
	 					</div>
					</form>
					
					<form id = "formOne" action="CustomerCancelReservation" method="post">
	 				
						<input type="hidden" name="reservationId" value='${reservation.id}' />
		  				<input type="hidden" name="reservationVehicleTypeId" value='${reservation.vehicleType.id}' />
		  				<input type="hidden" name="pickupTime" value='${reservation.pickupTime}' />
		  				<input type="hidden" name="pickupTime" value='${reservation.length}' />
		  				<div class="product-cta">
	 						<input type="submit" class = "cta" value="CANCEL RENTAL" />
	 					</div>
					</form>
					
				<#if statusRetrieveAdminReservationG??>
					<p class="good">
						${statusRetrieveAdminReservationG}
					</p>
				</#if>
				<#if statusRetrieveAdminReservationB??>
					<p class="bad">
						${statusRetrieveAdminReservationG}
					</p>
				</#if>
				</div>
			</div>
		</div>
		</div>
		</#list>
	</#if>
</body>
</html>
