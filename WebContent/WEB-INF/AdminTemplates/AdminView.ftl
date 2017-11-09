<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride | Admin View</title>
<link href='cssfiles/admin.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="javascriptNavbar/navbar.js"></script>
</head>

<#include "AdminNavbar.ftl">

<body>
	
	<div class="modal">
 		<div class="tab">
    			<label class="head">MAKE ADMIN</label>
   				<form class="form" id="add" action="AdminCreate" method='post' name="add">
   				
      				<p class="float-label">
        					<input type="text" id="email" name="email" placeholder="Email"/>
      				</p>
      					
      				<p>
        				<input class = "add" type="submit" onclick="return productNullCheck()" value="ADD ADMIN" />
      				</p>
    				</form>
  			</div>
  		</div>
	</div>

</body>
</html>
