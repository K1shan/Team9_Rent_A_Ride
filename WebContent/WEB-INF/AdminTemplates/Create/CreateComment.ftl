<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rent-A-Ride</title>
<link href='index.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/admin.css' rel='stylesheet' type='text/css'>
<script type = "text/javascript" src="//code.jquery.com/jquery-1.10.2.js"></script>
<script type = "text/javascript" src="Javascript/Navbar/navbar.js"></script>
<link href='cssfiles/location.css' rel='stylesheet' type='text/css'>
<link href='cssfiles/additionalCustomer.css' rel='stylesheet' type='text/css'>


</head>

<body>

	<div id="header"></div>
	
	<h1 id = "product">Comment </h1>
	
	<div class="modalTwo">
	 <div class="tab">
		<label class="head">CREATE COMMENT</label>	
		
		<form class="form" id="admin" action="CommentCreate" method='post' name="admin">
  			
  			 <p class="float-label">
				<input type="text" id="comment" name="comment" placeholder="COMMENT" />
			</p>
			
			<p>
				<input type="hidden" name="rentalId" value='${rentalId}' required />
				<input class = "type" type="submit" value="LEAVE COMMENT" required />
			</p>
		
			<#if statusCreateAdminCommentG??>
				<p class="good">
					${statusCreateAdminCommentG}
				</p>
			</#if>
			<#if statusCreateAdminCommentB??>
				<p class="bad">
					${statusCreateAdminCommentB}
				</p>
			</#if>
		</form>
	</div>
</div>
</body>
</html>