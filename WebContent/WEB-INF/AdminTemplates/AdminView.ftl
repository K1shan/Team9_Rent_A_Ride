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
  				
  				<#if status??>
					
					<p class="error">
   						 <label> ${status} </label>
  					</p>
						
				</#if>
  				
			</form>
  		</div>
	</div>
	
	
	<div class="modalTwo">
		 <div class="tab">
			<label class="head">ADD LOCATION</label>
			
			<form enctype="multipart/form-data" class="form" id="location" action="LocationCreate" method='post' name="location">
  				
  				<p class="float-label">
    					<input type="text" id="nameAdd" name="nameAdd" placeholder="NAME"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="addressAdd" name="addressAdd" placeholder="ADDRESS"/>
  				</p>
  				
 				<p class="float-label">
    					<input type="text" id="cityAdd" name="cityAdd" placeholder="CITY"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="stateAdd" name="stateAdd" placeholder="STATE"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="zipAdd" name="zipAdd" placeholder="ZIP CODE"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="avaAdd" name="avaAdd" placeholder="AVAIILABILITY"/>
  				</p>
  				
  				<p>
    					<input type="file" id="picAdd" name="picAdd"/>
  				</p>			
  				<p>
    					<input class = "location" type="submit" onclick="return locationAddNullCheck()" value="ADD LOCATION" />
  				</p>
  				
  				<#if status??>
					
					<p class="error">
   						 <label> IT&#8217S NOT NICE TO FOOL OUR SYSTEM </label>
  					</p>
						
				</#if>
  				
  				
			</form>
		</div>
	</div>

	<div class="modalThree">
		 <div class="tab">
			<label class="head">UPDATE LOCATION</label>
			
			<form enctype="multipart/form-data" class="form" id="location" action="LocationUpdate" method='post' name="location">
  				
  				<p class="float-label">
    					<input type="text" id="nameUpdate" name="nameUpdate" placeholder="NAME"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="addressUpdate" name="addressUpdate" placeholder="ADDRESS"/>
  				</p>
  				
 				<p class="float-label">
    					<input type="text" id="cityUpdate" name="cityUpdate" placeholder="CITY"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="stateUpdate" name="stateUpdate" placeholder="STATE"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="zipUpdate" name="zipUpdate" placeholder="ZIP CODE"/>
  				</p>
  				
  				<p class="float-label">
    					<input type="text" id="avaUpdate" name="avaUpdate" placeholder="AVAIILABILITY"/>
  				</p>
  				
  				<p>
    					<input type="file" id="picUpdate" name="picUpdate"/>
  				</p>			
  				<p>
    					<input class = "location" type="submit" onclick="return locationUpdateNullCheck()" value="UPDATE LOCATION" />
  				</p>
  				
  				<#if status??>
					
					<p class="error">
   						 <label> IT&#8217S NOT NICE TO FOOL OUR SYSTEM </label>
  					</p>
						
				</#if>

			</form>
		</div>
	</div>

	
</body>
</html>
