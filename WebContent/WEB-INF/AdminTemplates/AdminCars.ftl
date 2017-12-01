<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride</title>
<link href='index.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/locationAdmin.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/additionalCustomer.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="javascript/navbar.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveVehicle/RetrieveVehicleAdmin.js"></script>
<link href='cssfiles/carsSearch.css' rel='stylesheet' type='text/css'>
</head>

<#include "AdminNavbar.ftl">

<body>
	
		<h1 id = "product"> Cars </h1>
		
		<form class="search-cars">
    			<input type="text" id="cars-bar" placeholder="What can I help you with ${user}?">
  		</form>

		<div id='searchCars'></div>

		<div id='main'></div>

</body>

</html>

