<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride</title>

<link href='cssfiles/admin.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/location.css' rel='stylesheet' type='text/css'>

<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="Javascript/Navbar/navbar.js"></script>

</head>

<#include "CustomerNavbar.ftl">

<body>
	
	<h1 id = "product">Comment </h1>
	
	<div class="modalTwo">
	 <div class="tab">
		<label class="head">CREATE COMMENT</label>	
		
		<form class="form" id="admin" action="CommentCustomerCreate" method='post' name="admin">
  			
  			<#if charges??>
	  			<#if hours??>
		  			<p class="float-label">
		  				You returned your rental after ${hours} hours.<br>
		  				You have been charged $${charges} for your rental.<br>
		  				Thank you!
		  			</p>
	  			</#if>
  			</#if>
  			
  			 <p class="float-label">
				<input type="text" id="comment" name="comment" placeholder="COMMENT" />
			</p>
			
			<p>
				<input type="hidden" name="reservationId" value='${reservationId}' required />
				<input type="hidden" name="rentalId" value='${rentalId}' required />
				<input class = "type" type="submit" value="LEAVE COMMENT" required />
			</p>
		
			<#if statusCreateCustomerCommentG??>
				<p class="good">
					${statusCreateCustomerCommentG}
				</p>
			</#if>
			<#if statusCreateCustomerCommentB??>
				<p class="bad">
					${statusCreateCustomerCommentB}
				</p>
			</#if>
		</form>
	</div>
</div>
</body>

</html>