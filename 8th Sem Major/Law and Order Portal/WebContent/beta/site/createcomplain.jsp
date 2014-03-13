<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.law.order.controller.CitizenController"%>
<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="javax.ws.rs.core.Response"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link rel="stylesheet" href="./includes/css/form.css" type="text/css"
	media="screen" />
<link rel="stylesheet" href="./includes/css/citizen.css" type="text/css"
	media="screen" />
<script type="text/javascript" src="./includes/js/form.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#complainForm #back").click(function() {

			window.location = "./?pgid=user";
			$('div.htmltabs div.tabsContent').hide();//tabsContent class is used to hide all the tabs content in the start
			$('div.tab1').show(); // It will show the first tab content when page load, you can set any tab content you want - just put the tab content class e.g. tab4
			$('div.htmltabs ul.tabs li.tab1 a').addClass('tab-current');// We will add the class to the current open tab to style the active state
			$('div.htmltabs ul.tabs li.tab2 a').removeClass('tab-current');
			//It will add the click event on all the a

		})

	});
</script>
</head>
<div id="createComplain">
	<%
		int complain_id = 0, citizen_id = (Integer) session
				.getAttribute("uid");
		;
		String complain_content = null, complain_subject = null;

		if (request.getParameter("complainId") != null) {
			complain_id = Integer.valueOf(request
					.getParameter("complainId"));
			//citizen_id = Integer.valueOf(request.getParameter("citizenId"));
		}
		//out.println(complain_id);
		if (complain_id != 0) {

			// out.println(resp.getEntity());
			try {
				CitizenController citizen = new CitizenController();
				Response resp = citizen.retrieveSpecificComplain(
						complain_id, citizen_id);

				complain_subject = new JSONObject(resp.getEntity()
						.toString()).getString("complain_subject");
				complain_content = new JSONObject(resp.getEntity()
						.toString()).getString("complaint_content");
			} catch (Exception e) {
				e.printStackTrace();
			}
	%>
	<h1>Update Complain</h1>
	<%
		} else {
			complain_subject = "";
			complain_content = "";
	%>

	<h1>Create Complain</h1>
	<%
		}
	%>



	<form action="" method="post" id="complainForm">



		<input id="citizenId" name="citizenId" type="hidden"
			value='<%=citizen_id%>' /> <input id="status" name="status"
			type="hidden" value='pending' />

		<div>
			<label>Complain Subject * :</label> <input id="complainSubject"
				name="complainSubject" type="text" value='<%=complain_subject%>' /><span
				id="complainSubjectInfo"></span>
		</div>
		<div>
			<label>Complain Description * :</label>
			<textarea id="complainDesc" name="complainDesc"><%=complain_content%>
		</textarea>
			<span id="complainDescInfo"></span>
		</div>
		<%
			if (request.getParameter("complainId") != null) {
		%>
		<input type="hidden" name="complainid" value="<%=complain_id%>" />
		<div>
			<input id="send" name="send" type="submit" value="Update Complain" />
			<input type="button" id="back" name="back" value="Back" />
		</div>
		<%
			} else {
		%>
		<div>
			<input id="send" name="send" type="submit" value="Create Complain" />
			<input type="button" id="back" name="back" value="Back" />
		</div>
		<%
			}
		%>
	</form>
</div>
