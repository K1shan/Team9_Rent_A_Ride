<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride | Admin View</title>
<link href='cssfiles/admin.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="Javascript/Navbar/navbar.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/addAdmin.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/addLocation.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/updateLocation.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/deleteLocation.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/updateLocation.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/addType.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/addVehicle.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/deleteVehicle.js"></script>
<script type = "text/javascript" src="Javascript/Null/AdminView/updateVehicle.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveType/RetrieveType.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveLocation/RetrievelocationVehicle.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveLocation/RetrieveLocation.js"></script>
<script type = "text/javascript" src="Javascript/RetrieveVehicle/RetrieveVehicle.js"></script>
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
      Hello Admin
    </section>
    
    <section id="section2">
      <#include "Create/AddAdmin.ftl">
      <#include "Create/AddLocation.ftl">
      <#include "Create/AddVehicleType.ftl">
      <#include "Create/AddVehicle.ftl">
    </section>
    
    <section id="section3">
      <#include "Update/UpdateLocation.ftl">
      <#include "Update/UpdateVehicleType.ftl">
      <#include "Update/UpdateVehicle.ftl">
    </section>
    
    <section id="section4">
		<#include "Delete/DeleteLocation.ftl">
		<#include "Delete/DeleteVehicleType.ftl">
		<#include "Delete/DeleteVehicle.ftl">
    </section>
    
    <section id="section5">
      Coming Soon!
    </section>
    
  </div>





</body>
</html>
