<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="java.io.PrintWriter,java.util.regex.*,java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<!-- <link rel="stylesheet" href="./includes/css/general.css" type="text/css" -->
<!-- 	media="screen" /> -->
<link rel="stylesheet" href="./includes/css/form1.css" type="text/css"
	media="screen" />
<script type="text/javascript" src="./includes/js/validation.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#registerForm").submit(function(e) {
			e.preventDefault(); // prevent the browser from following the link
			e.stopPropagation(); // prevent the browser from following the link
			var data_content = $("form#registerForm").serialize();
			alert(data_content);
			$.ajax({
				type : "POST",
				url : "./site/submit/reg_val.jsp",
				enctype : 'multipart/form-data',
				cache : false,
				data : data_content,
				success : function(data) {

					alert(data);
					if ($.trim(data) === "SUCCESS") {
						
						window.location="./site/idupload.jsp";
					} else {
						alert("FAILED" + dat);
					}
				}
			});
		});
	});
</script>
</script>





</head>

<body>

	<h1>Registration Form</h1>

	<form method="post" id="registerForm" class="form"
		action="http://localhost:8080/TGMC/beta/?pgid=reg"
		enctype="multipart/form-data">
		<label>( * ) are mandatory</label>
		<div id="demo"></div>
		<div>
			<label>Full Name * :</label> <input id="name" name="name" type="text"
				value='' /><span id="nameInfo"></span>
		</div>

		<div>
			<label>Email Id * :</label> <input id="email_id" name="email_id"
				type="text" value='' /><span id="email_idInfo"></span>
		</div>

		<div>
			<label>Password* :</label> <input id="pass1" name="pass1"
				type="password" value='' /><span id="pass1Info"></span>
		</div>

		<div>
			<label>Confirm Password * :</label> <input id="pass2" name="pass2"
				type="password" value='' /> <span id="pass2Info"></span>
		</div>


		<div>
			<label>Date of Birth(DD-MM-YYYY) * :</label> <input id="dob"
				name="dob" type="date" value='<%--dob--%>' /> <span id="dobInfo"></span>
		</div>


		<div>
			<label>Gender * :</label> <select name="gender" id="gender">
				<option value="1">Gender *</option>
				<option value="2"<%--out.println(gender.equals("2") ? "selected=\'selected\'" : "");--%>>Male</option>
				<option value="3"<%--out.println(gender.equals("3") ? "selected=\'selected\'" : "");--%>>Female</option>
			</select> <span id="genderInfo"></span>
		</div>

		<div>
			<label>Mobile Number * :</label> <input id="mobile" name="mobile"
				type="text" value='' /> <span id="mobileInfo"></span>
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
		<!-- 
		<div>
			<label>Attach Identification Copy* :</label> <input id="file"
				name="file" type="file" /> <span id="fileInfo"></span>
		</div> -->
		<div>
			<input id="send" name="send" type="submit" value="Register" />
		</div>

	</form>


</body>
</html>