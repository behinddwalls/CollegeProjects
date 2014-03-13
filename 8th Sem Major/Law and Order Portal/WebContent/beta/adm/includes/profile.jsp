<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="com.law.order.model.Address"%>
<%@page import="com.law.order.controller.PoliceController"%>
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
<script type="text/javascript" src="./includes/js/validation.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$("#profileForm #cancel").click(function() {

			$("#profileForm input").attr('disabled', 'disabled');
			$("#picForm input").attr('disabled', 'disabled');
			$("#edit").slideDown();

		});
		$("div#edit").click(function() {

			$("#profileForm input").removeAttr("disabled");
			$("#picForm input").removeAttr("disabled");
			$("#profileForm select").removeAttr("disabled");
			$("#edit").slideUp();
			return true;
		});

	});
</script>

<style>
#profileForm {
	margin-left: 200px;
}
</style>
</head>

<%
	int pid = (Integer) session.getAttribute("pid");
		PoliceController policeController = new PoliceController();
		Response resp = policeController.policeofficeretrieve(pid, "",
				"showpolice");
		JSONObject jPolice = new JSONObject(resp.getEntity().toString());
		//out.println(jPolice);
		Address addr = new Address();
		addr.setAddress_id(jPolice.getInt("address_id"));
		JSONArray jArray = addr.select();
		JSONObject jAddr = new JSONObject(jArray.get(0).toString());
%>



<body>

	<h1>Profile</h1>
	<div class="profile-pic">
		<div id="edit">Edit Profile</div>
		<br /> <div id="img"><img src="<%=jPolice.getString("identification_copy")%>"
			id="picImg" /></div>
		<form method="post" action="./../../v1/upload/file" id="picForm"
			enctype="multipart/form-data">

			<label>Profile Pic* :</label><br /> <input id="pic" name="pic"
				type="file" disabled="disabled" /><span id="picInfo"></span><br />
			<input id="send" name="send" type="submit" value="Upload Image"
				disabled="disabled" />
		</form>

		<div id="preview"></div>
	</div>
	<div>
		<form method="post" id="profileForm" class="form" action="">
			<label>( * ) are mandatory</label> <input type="hidden"
				value="<%=pid%>" name="pid" />
			<div>
				<label>Full Name * :</label> <input id="name" name="name"
					type="text" value='<%=jPolice.getString("officer_name")%>'
					disabled="disabled" /><span id="nameInfo"></span>
			</div>

			<div>
				<label>Email Id * :</label> <input id="email_id" name="email_id"
					type="text" value='<%=jPolice.getString("email_id")%>'
					disabled="disabled" /><span id="email_idInfo"></span>
			</div>




			<%-- <div>
				<label>Date of Birth(DD-MM-YYYY) * :</label> <input id="dob"
					name="dob" type="date"
					value='<%=jPolice.getString("date_of_birth")%>' disabled="disabled" />
				<span id="dobInfo"></span>
			</div> --%>


			<div>
				<label>Gender * :</label> <select name="gender" id="gender"
					disabled="disabled">
					<option value="1">Gender *</option>
					<option value="male"
						<%if (jPolice.getString("gender").equals("male")) {
					out.println("selected");
				}%>>Male</option>
					<option value="female"
						<%if (jPolice.getString("gender").equals("female")) {
					out.println("selected");
				}%>>Female</option>
				</select> <span id="genderInfo"></span>
			</div>


			<div>
				<label>Mobile Number * :</label> <input id="mobile" name="mobile"
					type="text" value='<%=jPolice.getString("mobile_no")%>'
					disabled="disabled" /> <span id="mobileInfo"></span>
			</div>

			<div>
				<label>Pincode * :</label> <input id="pincode" name="pincode"
					type="text" value='<%=jAddr.getString("pincode")%>'
					disabled="disabled" /> <span id="pincodeInfo"></span>
			</div>



			<div>
				<label>Address * :</label> <input id="address_block"
					name="address_block" type="text"
					value='<%=jAddr.getString("address_block")%>' disabled="disabled" />
				<span id="address_blockInfo"></span>
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
	} else
		response.sendRedirect("./../");
%>