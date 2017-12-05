<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride | Admin View</title>
<link href='cssfiles/admin.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/locationView.css' rel='stylesheet' type='text/css'>

<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="Javascript/Navbar/navbar.js"></script>

<script type = "text/javascript" src="Javascript/Null/AdminView/addAdmin.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/addLocation.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/addType.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/addVehicle.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/addHourlyPrice.js"></script>

<script type = "text/javascript" src="Javascript/Null/AdminView/updateProfile.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/updateInfo.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/updateLocation.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/updateType.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/updateVehicle.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/updateCustomer.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/updateHourlyPrice.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/updateMembership.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/updateLatefee.js"></script>

<script type = "text/javascript" src="Javascript/Null/AdminView/deleteLocation.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/deleteVehicleType.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/deleteVehicle.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/deleteHourlyPrice.js"></script>

<script type = "text/javascript" src="Javascript/RetrieveType/RetrieveType.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveLocation/RetrieveLocationAdmin.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveHourlyPrice/RetrieveHourlyPrice.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveCustomer/RetrieveCustomer.js"></script>

<script type = "text/javascript" src="Javascript/RetrieveLocation/RetrieveLocationView.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveCustomer/RetrieveCustomerView.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveReservation/RetrieveReservationView.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveReservation/RetrieveReservationNoShow.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveVehicle/RetrieveVehicleView.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveVehicle/RetrieveVehicleAdmin.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveComments/RetrieveCommentsView.js"></script>

<script type = "text/javascript" src="Javascript/Search/Search.js"></script>

<script type = "text/javascript" src="Javascript/Tab/tab.js"></script>
</head>

<#include "AdminNavbar.ftl">

<body>	


  <div class="tabbs">
    <ul>
      <li><a href="#section1">Personal</a></li>
      <li><a href="#section2">Create</a></li>
      <li><a href="#section3">Update</a></li>
      <li><a href="#section4">Delete</a></li>
      <li><a href="#section5">View</a></li>
    </ul>
    <section id="section1">
   	  <#include "Profile/ViewProfile.ftl">
      <#include "Profile/EditProfile.ftl">
      <#include "Profile/EditInfo.ftl">
    </section>
    
    <section id="section2">
      <#include "Create/AddAdmin.ftl">
      <#include "Create/AddLocation.ftl">
      <#include "Create/AddVehicleType.ftl">
      <#include "Create/AddVehicle.ftl">
      <#include "Create/AddHourlyPrice.ftl">
    </section>
    
    <section id="section3">
      <#include "Update/UpdateLocation.ftl">
      <#include "Update/UpdateVehicleType.ftl">
      <#include "Update/UpdateVehicle.ftl">
      <#include "Update/UpdateCustomer.ftl">
      <#include "Update/UpdateMembership.ftl">
      <#include "Update/UpdateLatefee.ftl">
      <#include "Update/UpdateHourlyPrice.ftl">
      <#include "Update/UpdateReservationNoShow.ftl">
    </section>
    
    <section id="section4">
		<#include "Delete/DeleteLocation.ftl">
		<#include "Delete/DeleteVehicleType.ftl">
		<#include "Delete/DeleteVehicle.ftl">
		<#include "Delete/DeleteHourlyPrice.ftl">
    </section>
    
    <section id="section5">
	
		<#include "View/ViewSearch.ftl">
    		<#include "View/ViewCar.ftl">
    			<div id='RetrieveVehicleView'></div>
      	<#include "View/ViewLocation.ftl">
       		<div id='RetrieveLocationView'></div>	
      	<#include "View/ViewReservations.ftl">
      		<div id='RetrieveReservationView'></div>
      	<#include "View/ViewCustomers.ftl">
      		<div id='RetrieveCustomerView'></div>
      	<#include "View/ViewComments.ftl">
      		<div id='RetrieveCommentsView'></div>

    </section>
    
  </div>

</body>
</html>
