<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride | Customer</title>

<link href='cssfiles/admin.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>


</head>

<#include "CustomerNavbar.ftl">

<body>
    
    <h1 id="product"> WELCOME ${user} </h1>
   	<section id="section1">
   		<div class="modalTwo">
   		<div class="tab">
   		<#if reservations??>
   			<#list reservations as reservation>
   				<p class="float-label">Rental Location: ${reservation.rentalLocation.name}</p>
   				<p class="float-label">Vehicle Type: ${reservation.vehicleType.name}</p>
   				<p class="float-label">Pickup time: ${reservation.pickupTime}</p>
   				<p class="float-label">Length: ${reservation.length}</p>
   				<#if reservation.cancelled == true>
				<p class="float-label">
					Cancelled
				</p> 
   				</#if>
   				
   				<form class="form" action="CustomerPickup" method='post'>
   					<p class="float-label">
						<input class="type" type="submit" value="PICKUP RENTAL" />
					</p>
		  			<input type="hidden" name="reservationId" value='${reservation.id}' />
		  			<input type="hidden" name="reservationVehicleTypeId" value='${reservation.vehicleType.id}' />
		  			<input type="hidden" name="pickupTime" value='${reservation.pickupTime}' />
		  			<input type="hidden" name="pickupTime" value='${reservation.length}' />
				</form>
				<p></p>
				
				<!--
   				<form class="form" action="CustomerCancelReservation" method='post'>
   					<p>
						<input class="type" type="submit" value="CANCEL" />
					</p>
					<input type="hidden" name="reservationId" value='${reservation.id}' />
		  			<input type="hidden" name="reservationVehicleTypeId" value='${reservation.vehicleType.id}' />
		  			<input type="hidden" name="pickupTime" value='${reservation.pickupTime}' />
		  			<input type="hidden" name="pickupTime" value='${reservation.length}' />
				</form>-->
				
				<#if statusUpdateCustomerReservationG??>
					<p class="good">
						${statusUpdateCustomerReservationG}
					</p>
				</#if>
				<#if statusUpdateCustomerReservationB??>
					<p class="bad">
						${statusUpdateCustomerReservationB}
					</p>
				</#if>
   			</#list>
   		</#if>
   		</div>
   		</div>
    </section>
	
</body>
</html>
