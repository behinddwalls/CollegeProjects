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
<script type="text/javascript" src="./includes/js/validation.js"></script>

<style type="text/css">
#passForm {
	margin-left: 200px;
}
</style>
</head>


<%
	int pid = (Integer) session.getAttribute("pid");
%>


<body>

	<h1>Change Password</h1>

	<div>
		<form method="post" id="passForm" class="form" action="">
			<label>( * ) are mandatory</label> <input type="hidden"
				value="<%=pid%>" name="pid" />
			<div>
				<label>New Password* :</label> <input id="pass1" name="pass1"
					type="password" value='' /><span id="pass1Info"></span>
			</div>

			<div>
				<label>Confirm New Password * :</label> <input id="pass2"
					name="pass2" type="password" value='' /> <span id="pass2Info"></span>
			</div>
			<div>
				<input id="send" name="send" type="submit" value="Update" />
			</div>

		</form>
	</div>


</body>
</html>
<%
	} else {
		response.sendRedirect("./../");
	}
%>