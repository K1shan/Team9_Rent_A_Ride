<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride</title>
<link href='index.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="Javascript/Navbar/navbar.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveLocation/RetrieveLocationCustomer.js"></script>
<link href='cssfiles/location.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/additionalCustomer.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/locationCustomerSearch.css' rel='stylesheet' type='text/css'>
</head>

<#include "CustomerNavbar.ftl">

<body>
	
	<h1 id = "product"> Location </h1>
	
  	<form class="search-location">
    		<input type="text" id="locaiton-bar" placeholder="What can I help you with ${user}?">
  	</form>

	<div class="searchR"id="search"></div>	
	
	<div id='main'></div>

</body>

</html>

