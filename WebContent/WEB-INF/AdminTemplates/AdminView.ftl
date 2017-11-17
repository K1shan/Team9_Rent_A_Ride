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
<script type = "text/javascript" src="Javascript/RetrieveType/RetrieveType.js"></script>
<script type = "text/javascript" src="Javascript/tab.js"></script>
</head>

<#include "AdminNavbar.ftl">

<body>	


  <div class="tabbs">
    <ul>
      <li><a href="#section1">Section 1</a></li>
      <li><a href="#section2">Section 2</a></li>
      <li><a href="#section3">Section 3</a></li>
      <li><a href="#section4">Section 4</a></li>
      <li><a href="#section5">Section 5</a></li>
    </ul>
    <section id="section1">
      Hello
    </section>
    <section id="section2">
      HI
    </section>
    <section id="section3">

    </section>
    <section id="section5">
  

    </section>
  </div>

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
  				
  				<#if statusAddAdminG??>
					
					<p class="good">
	   					<label> ${statusAddAdminG} </label>
	  				</p>
						
				</#if>
  				
				<#if statusAddAdminB??>
				
					<p class="error">
   						 <label> ${statusAddAdminB} </label>
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
  				
  				<#if statusAddLocationG??>
					
					<p class="good">
	   					<label> ${statusAddLocationG} </label>
	  				</p>
						
				</#if>
  				
				<#if statusAddLocationB??>
				
					<p class="error">
   						 <label> ${statusAddLocationB} </label>
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
    					<input type="text" id="avaUpdate" name="avaUpdate" placeholder="AVAILABILITY"/>
  				</p>
  				
  				<p>
    					<input type="file" id="picUpdate" name="picUpdate"/>
  				</p>			
  				<p>
    					<input class = "location" type="submit" onclick="return locationUpdateNullCheck()" value="UPDATE LOCATION" />
  				</p>
  				
  					<#if statusUpdateLocationG??>
					
						<p class="good">
	   						 <label> ${statusUpdateLocationG} </label>
	  					</p>
						
					</#if>
  				
  					<#if statusUpdateLocationB??>
					
						<p class="error">
	   						 <label> ${statusUpdateLocationB} </label>
	  					</p>
						
					</#if>

			</form>
		</div>
	</div>


		<div class="modalFour">
			<div class="tab">
					<label class="head">DELETE LOCATION </label>
					<form class="form" id="admin" action="LocationDelete" method='post' name="admin">
					
		  				<p class="float-label">
		    					<input type="text" id="id" name="id" placeholder="LOCATION ID"/>
		  				</p>
		  				<p>
		    				<input class = "type" type="submit" onclick="return locationDeleteNullCheck()" value="DELETE LOCATION" />
		  				</p>
		  				
		  				<#if statusDeleteLocationG??>
							
							<p class="good">
			   					<label> ${statusDeleteLocationG} </label>
			  				</p>
								
						</#if>
		  				
  						<#if statusDeleteLocationB??>
					
						<p class="error">
	   						 <label> ${statusDeleteLocationB} </label>
	  					</p>
						
						</#if>
					</form>
		  		</div>
			</div>
		</div>



		<div class="modalFive">
			<div class="tab">
					<label class="head">ADD VEHICLE TYPE </label>
					<form class="form" id="admin" action="VehicleTypeCreate" method='post' name="admin">
					
		  				<p class="float-label">
		    					<input type="text" id="type" name="type" placeholder="Vehicle Type Name"/>
		  				</p>
		  				<p>
		    				<input class = "type" type="submit" onclick="return typeCreateNullCheck()" value="ADD VEHICLE TYPE" />
		  				</p>
		  				
		  				<#if statusAddTypeG??>
							
							<p class="good">
			   					<label> ${statusAddTypeG} </label>
			  				</p>
								
						</#if>
		  				
						<#if statusAddTypeB??>
						
							<p class="error">
		   						 <label> ${statusAddTypeB} </label>
		  					</p>
							
						</#if>
					</form>
		  		</div>
			</div>
		</div>
		
		<div class="modalSix">
			<div class="tab">
					<label class="head">UPDATE VEHICLE TYPE </label>
					<form class="form" id="admin" action="VehicleTypeUpdate" method='post' name="admin">
					
					
						<select id="select1" name="select1" class="minimal">
								<option value="">Select</option>
						</select>
					
		  				<p class="float-label">
		    					<input type="text" id="type" name="type" placeholder="Vehicle Type ID"/>
		  				</p>
		  				<p>
		    				<input class = "type" type="submit" onclick="return typeUpdateNullCheck()" value="UPDATE VEHICLE TYPE" />
		  				</p>
		  				
		  				<#if statusUpdateTypeG??>
							
							<p class="good">
			   					<label> ${statusUpdateTypeG} </label>
			  				</p>
								
						</#if>
		  				
						<#if statusUpdateTypeB??>
						
							<p class="error">
		   						 <label> ${statusUpdateTypeB} </label>
		  					</p>
							
						</#if>
					</form>
		  		</div>
			</div>
		</div>
		
		<div class="modalSeven">
			<div class="tab">
					<label class="head">DELETE VEHICLE TYPE </label>
					<form class="form" id="admin" action="VehicleTypeDelete" method='post' name="admin">
					
					
						<select id="select2" name="select2" class="minimal">
								<option value="">Select</option>
						</select>
					
		  				<p class="float-label">
		    					<input type="text" id="type" name="type" placeholder="Vehicle Type ID"/>
		  				</p>
		  				<p>
		    				<input class = "type" type="submit" onclick="return typeDeleteNullCheck()" value="DELETE VEHICLE TYPE" />
		  				</p>
		  				
		  				<#if statusDeleteTypeB??>
							
							<p class="good">
			   					<label> ${statusDeleteTypeB} </label>
			  				</p>
								
						</#if>
		  				
						<#if statusDeleteTypeB??>
						
							<p class="error">
		   						 <label> ${statusDeleteTypeB} </label>
		  					</p>
							
						</#if>
					</form>
		  		</div>
			</div>
		</div>

</body>
</html>
