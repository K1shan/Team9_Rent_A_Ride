<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride | Admin View</title>
<link href='cssfiles/admin.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="javascriptNavbar/navbar.js"></script>
<script type = "text/javascript" src="javascriptNull/addAdmin.js"></script>
<script type = "text/javascript" src="javascriptNull/addLocation.js"></script>
</head>

<#include "AdminNavbar.ftl">

<body>
	
	<div class="modal">
 		<div class="tab">
    			<label class="head">ADD ADMIN</label>
   				<form class="form" id="admin" action="AdminCreate" method='post' name="admin">
   				
      				<p class="float-label">
        					<input type="text" id="email" name="email" placeholder="Email"/>
      				</p>
      				<p>
        				<input class = "admin" type="submit" onclick="return adminNullCheck()" value="ADD ADMIN" />
      				</p>
    				</form>
  			</div>
  		</div>
	</div>
	
	<div class="modalTwo">
 			 <div class="tab">
    			<label class="head">ADD LOCATION</label>
   				
   				<form enctype="multipart/form-data" class="form" id="location" action="LocationCreate" method='post' name="location">
      				
      				<p class="float-label">
        					<input type="text" id="address" name="address" placeholder="ADDRESS"/>
      				</p>
      				
     				<p class="float-label">
        					<input type="text" id="city" name="city" placeholder="CITY"/>
      				</p>
      				
      				<p class="float-label">
        					<input type="text" id="state" name="state" placeholder="STATE"/>
      				</p>
      				
      				<p class="float-label">
        					<input type="text" id="zip" name="zip" placeholder="ZIP CODE"/>
      				</p>
      				
      				<p class="float-label">
        					<input type="text" id="ava" name="ava" placeholder="AVAIILABILITY"/>
      				</p>
      				
      				<p>
        					<input type="file" id="pic" name="pic"/>
      				</p>			
      				<p>
        					<input class = "location" type="submit" onclick="return locationNullCheck()" value="ADD LOCATION" />
      				</p>
    			</form>
  			</div>
  		</div>
	</div>

</body>
</html>
