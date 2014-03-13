<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="java.io.PrintWriter,java.util.regex.*,java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	if (session.getAttribute("pid") != null
			&& !session.getAttribute("pid").toString().isEmpty()) {
%>

<html>
<head>
<link rel="stylesheet" href="./includes/css/form.css" type="text/css"
	media="screen" />
<link rel="stylesheet" href="./includes/css/profile.css" type="text/css"
	media="screen" />

<script type="text/javascript" src="./includes/js/jquery.form.js"></script>

<script type="text/javascript" src="./includes/js/createpolice.js"></script>


<style>
#createpoliceForm {
	margin-left: 200px;
}
</style>
</head>





<body>

	<h1>Create Police Officer</h1>
	<div class="profile-pic">
		<form method="post" action="./../../v1/upload/file" id="picForm">
			<label>Upload ID Copy :</label><br /> <input id="file" name="file"
				type="file" /><span id="fileInfo"></span><br /> <br /> <input
				id="send" name="send" type="submit" value="Upload Image" />
		</form>

		<div id="preview">Here</div>
	</div>
	<div>
		<form method="post" id="createpoliceForm" class="form" action="">
			<label>( * ) are mandatory</label>
			<div>
				<label>Full Name * :</label> <input id="name" name="name"
					type="text" value='' /><span id="nameInfo"></span>
			</div>

			<div>
				<label>Email Id * :</label> <input id="email_id" name="email_id"
					type="text" value='' /><span id="email_idInfo"></span>
			</div>


			<div>
				<label>Password * :</label> <input id="password" name="password"
					type="password" value='' /><span id="passwordInfo"></span>
			</div>


			<div>
				<label>Date of Birth(DD-MM-YYYY) * :</label> <input id="dob"
					name="dob" type="date" value='' /> <span id="dobInfo"></span>
			</div>


			<div>
				<label>Gender * :</label> <select name="gender" id="gender">
					<option value="1">Gender *</option>
					<option value="2">Male</option>
					<option value="3">Female</option>
				</select> <span id="genderInfo"></span>
			</div>



			<div>
				<label>Pincode * :</label> <input id="pincode" name="pincode"
					type="text" value='' /> <span id="pincodeInfo"></span>
			</div>



			<div>
				<label>Address * :</label> <input id="address_block"
					name="address_block" type="text" value='' /> <span
					id="address_blockInfo"></span>
			</div>

			<div>
				<label>Mobile Number * :</label> <input id="mobile" name="mobile"
					type="text" value='' /> <span id="mobileInfo"></span>
			</div>

			<div>
				<label>Role * :</label> <select name="role" id="role">
					<option value="1">Roles*</option>
					<option value="Admin">Admin</option>
					<option value="police">Police</option>
						<option value="traffic">Traffic Police</option>
							<option value="cbi">Cbi</option>
								<option value="detective">Detective</option>
									<option value="Sub-Admin">Sub-Admin</option>
				</select> <span id="roleInfo"></span>
			</div>



			<div>
				<label>ID PATH * :</label> <input id="pid" name="pid" type="text"
					readonly /> <span id="pidInfo"></span>
			</div>



			<div>
				<input id="send" name="send" type="submit" value="Create Police" />

			</div>

		</form>
	</div>


</body>
</html>
<%
	} else
		response.sendRedirect("./../");
%>