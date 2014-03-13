<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>


<%@page import="com.law.order.controller.CitizenController"%>

<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="java.io.PrintWriter,java.util.regex.*,java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<%
	int uid = (Integer) session.getAttribute("uid");// (String) session.getAttribute("citizenid");
	String pincode = null, citizen_name = null, email_id = null, date_of_birth = null, address_block = null, mobile_number = null, identification_copy = null, profilepic = null, password = null, gender = null;
	String mobile = null;
	int address_id = 0;
	if (uid != 0) {
		CitizenController citizen = new CitizenController();
		Response resp = citizen.retrieveCitizen(uid);
		//out.println(resp.getEntity().toString());
		try {
			JSONObject json = new JSONObject(resp.getEntity()
					.toString());

			citizen_name = (String) json.getString("citizen_name");
			email_id = (String) json.getString("email_id");
			date_of_birth = json.getString("date_of_birth");
			address_id = Integer.parseInt(json.getString("addr_id"));
			pincode = new JSONArray(json.getString("address_id"))
					.getJSONObject(0).getString("pincode");
			address_block = new JSONArray(json.getString("address_id"))
					.getJSONObject(0).getString("address_block");
			mobile_number = json.getString("mobile_no");
			identification_copy = json.getString("identification_copy");

			profilepic = json.getString("profilepic");
			gender = json.getString("gender");
			mobile = json.getString("mobile_no");

			password = json.getString("password");
			
			session.setAttribute("pincode" , pincode);
			
			
			//out.println(profilepic);
		} catch (Exception e) {
			e.printStackTrace();
		}
%>


<head>
<link rel="stylesheet" href="./includes/css/general.css" type="text/css"
	media="screen" />
<link rel="stylesheet" href="./includes/css/citizen.css" type="text/css"
	media="screen" />
<script type="text/javascript" src="./includes/js/jquery.js"></script>
<script type="text/javascript" src="./includes/js/jquery.form.js"></script>
<script type="text/javascript" src="./includes/js/validation.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#customForm #cancel").click(function() {
			window.location = "./?pgid=user";
			/* $("#customForm input").attr('disabled', 'disabled');
			$("#picForm input").attr('disabled', 'disabled');
			$("#edit").slideDown(); */

		});
		$("div#edit").click(function() {

			$("#customForm input").removeAttr("disabled");
			$("#customForm select").removeAttr("disabled");
			$("#picForm input").removeAttr("disabled");
			$("#edit").slideUp();
			return true;
		});

	});
</script>

</head>





<body>

	<h1>Profile</h1>
	<div class="profile-pic">
		<div id="edit">Edit Profile</div>
		<br /><div id="img"> <img src="<%=profilepic%>" id="picImg" /></div>
		<form method="post" action="./../v1/upload/file"
			id="picForm" enctype="multipart/form-data">
			<label>Profile Pic* :</label><br /> <input id="pic" name="pic"
				type="file" /><br /> <input id="send" name="send" type="submit"
				value="Upload Image" />
		</form>
		<div id="preview"></div>

	</div>

	<div>
		<form method="post" id="customForm" action="">
			<label>( * ) are mandatory</label> <input id="citizenId"
				name="citizenId" type="hidden" value='<%=uid%>' /> <input
				id="addrId" name="addrId" type="hidden" value='<%=address_id%>' />

			<div>
				<label>Full Name * :</label> <input id="name" name="name"
					type="text" value='<%=citizen_name%>' disabled="disabled" /><span
					id="nameInfo"></span>
			</div>

			<div>
				<label>Email Id * :</label> <input id="email_id" name="email_id"
					type="text" value='<%=email_id%>' disabled="disabled" /><span
					id="email_idInfo"></span>
			</div>

			<div>
				<label>Password * :</label> <input id="pass1" name="pass1"
					type="password" value='<%=""%>' disabled="disabled" /><span
					id="pass1Info"></span>
			</div>


			<div>
				<label>Date of Birth(DD-MM-YYYY) * :</label> <input id="dob"
					name="dob" type="date" value='<%=date_of_birth%>'
					disabled="disabled" /> <span id="dobInfo"></span>
			</div>


			<div>
				<label>Gender * :</label> <select name="gender" id="gender"
					disabled="disabled">
					<option value="1">Gender *</option>
					<option value="2"
						<%if (gender.equals("2"))
					out.println("selected");%>>Male</option>
					<option value="3"
						<%if (gender.equals("3"))
					out.println("selected");%>>Female</option>
				</select> <span id="genderInfo"></span>
			</div>



			<div>
				<label>Pincode * :</label> <input id="pincode" name="pincode"
					type="text" value='<%=pincode%>' disabled="disabled" /> <span
					id="pincodeInfo"></span>
			</div>

			<div>
				<label>Mobile * :</label> <input id="mobile" name="mobile"
					type="text" value='<%=mobile%>' disabled="disabled" /> <span
					id="mobileInfo"></span>
			</div>



			<div>
				<label>Address * :</label> <input id="address_block"
					name="address_block" type="text" value='<%=address_block%>'
					disabled="disabled" /> <span id="address_blockInfo"></span>
			</div>

			<div>
				<input id="send" name="send" type="submit" value="Update"
					disabled="disabled" /> <input type="button" id="cancel"
					name="cancel" value="Cancel" disabled="disabled" />
			</div>

		</form>
	</div>


</body>
</html>
<%
	} else {
		response.sendRedirect("http://localhost:8080/TGMC/beta");
	}
%>
