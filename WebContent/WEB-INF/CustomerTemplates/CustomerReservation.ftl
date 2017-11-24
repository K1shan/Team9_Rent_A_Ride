CUSTOMER VIEW
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride | Customer</title>

<link href='cssfiles/admin.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="Javascript/Navbar/navbar.js"></script>

<script type = "text/javascript" src="Javascript/Null/CustomerView/updateProfile.js"></script>


</head>

<#include "CustomerNavbar.ftl">

<body>
    
    <h1 id="product"> WELCOME ${user} </h1>
   	<section id="section1">
   		<div class="product-des">
   		<#if reservations??>
   			<#list reservations as reservation>
   				<p>Vehicle Type: ${reservation.vehicleType.name}</p>
   				<p>Pickup time: ${reservation.pickupTime}</p>
   				<p>Length: ${reservation.length}</p>
   				<p>Cancel</p>
   				<p>Pickup</p>
   			</#list>
   		</#if>
   		</div>
    </section>
	
</body>
</html>
