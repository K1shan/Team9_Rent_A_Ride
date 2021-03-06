<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride</title>
<link href='index.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/locationView.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/additionalCustomer.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="javascript/navbar.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveLocation/RetrieveLocationCustomer.js"></script>
</head>

<#include "CustomerNavbar.ftl">

<body>
	
	<h1 id = "product"> Reservations </h1>

	<#if statusRetrieveCustomerReservationG??>
		<p class="good">
			<label>${statusRetrieveCustomerReservationG}</label>
		</p>
	</#if>
	<#if statusRetrieveCustomerReservationB??>
		<p class="error">
			<label>${statusRetrieveCustomerReservationB}</label>
		</p>
	</#if>
	
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
	 					<#if reservation.rental??>
	 						<div class="product-description">Charges: $${reservation.rental.charges}</div>
	 						<#if reservation.rental.charges < 1>
			 					<div class="product-description">Vehicle Pickedup</div>
			 				<#else>
			 					<div class="product-description">Vehicle Returned</div>
			 				</#if>
	 					</#if>
	 					<#if reservation.cancelled!false>
	 						<div class="product-description">Cancelled</div>
			 			</#if>
			 			<#if reservation.length == 1>
	 						<div class="product-description">Charges: $${latefee}</div>
			 			</#if>
			 			
			 			
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
				</div>
			</div>
		</div>
		</div>
		</#list>
	</#if>
	
</body>

</html>