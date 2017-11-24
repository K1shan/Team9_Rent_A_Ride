CUSTOMER VIEW
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride | Customer</title>

<link href='cssfiles/admin.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="Javascript/Navbar/navbar.js"></script>
<link href='cssfiles/location.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="Javascript/Null/CustomerView/updateProfile.js"></script>


</head>

<#include "CustomerNavbar.ftl">

<body>
    
    <h1 id="product"> WELCOME ${user} </h1>
   	<section id="section1">
   	  <#include "Update/ViewProfile.ftl">
      <#include "Update/EditProfile.ftl">
      <#include "Update/RenewMembership.ftl">
      <#include "Update/ViewProfile2.ftl">
      <#include "Update/EditProfile2.ftl">
      <#include "Update/CancelMembership.ftl">
    </section>
	
</body>
</html>
