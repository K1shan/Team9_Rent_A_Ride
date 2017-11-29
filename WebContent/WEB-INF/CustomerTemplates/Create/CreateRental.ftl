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
	
	<h1 id = "product">Rental </h1>
	
	<div class="modalTwo">
	 <div class="tab">
		<label class="head">CREATE RENTAL</label>	
		
		<form class="form" id="admin" action="RentalCustomerCreate" method='post' name="admin">
  			
  			 <select id="selectRentalVehicleAdd" name="selectRentalVehicleAdd" class="minimal">
				<option value="">Select Vehicle</option>
				<#if vehicles??>
   				<#list vehicles as vehicle>
   				<option value="${vehicle.id}">${vehicle.make} - ${vehicle.model} - ${vehicle.year}</select>
   				</#list>
   				</#if>
			</select>
			
			<p>
				<input type="hidden" name="reservationId" value='${reservationId}' />
				<input class = "type" type="submit" onclick="return rentalCreateNullCheck()" value="PICKUP RENTAL" />
			</p>
		
			<#if statusCreateCustomerRentalG??>
				<p class="good">
					${statusCreateCustomerRentalG}
				</p>
			</#if>
			<#if statusCreateCustomerRentalB??>
				<p class="bad">
					${statusCreateCustomerRentalB}
				</p>
			</#if>
		</form>
	</div>
</div>
</body>
</html>