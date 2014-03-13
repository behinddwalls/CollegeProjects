<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.law.order.controller.CitizenController"%>
<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link rel="stylesheet" href="./includes/css/citizen.css" type="text/css"
	media="screen" />
<script type="text/javascript">
	$(document).ready(function() {

		if (window.location.href.match("complainId").length > 0) {
			$('div.htmltabs div.tabsContent').hide();//tabsContent class is used to hide all the tabs content in the start
			$('div.tab2').show(); // It will show the first tab content when page load, you can set any tab content you want - just put the tab content class e.g. tab4
			$('div.htmltabs ul.tabs li.tab2 a').addClass('tab-current');// We will add the class to the current open tab to style the active state
			$('div.htmltabs ul.tabs li.tab1 a').removeClass('tab-current');
		}
		//It will add the click event on all the a

	});
</script>

</head>
<%
	int uid = (Integer) session.getAttribute("uid");
	ArrayList<Integer> complaint_id = new ArrayList<Integer>();
	ArrayList<String> complaint_content = new ArrayList<String>();
	ArrayList<String> complaint_time = new ArrayList<String>();
	ArrayList<String> complaint_status = new ArrayList<String>();
	ArrayList<String> complaint_subject = new ArrayList<String>();
	if (uid != 0) {
		CitizenController citizen = new CitizenController();
		Response resp = citizen.retrieveComplain(uid);
		// out.println(resp.getEntity());
		JSONArray jArray = null;
		try {
			 jArray = new JSONArray(resp.getEntity()
					.toString());

			for (int i = 0; i < jArray.length(); i++) {

				complaint_time.add(jArray.getJSONObject(i).getString(
						"complaint_time"));
				complaint_subject.add(jArray.getJSONObject(i)
						.getString("complain_subject"));

				complaint_id.add(jArray.getJSONObject(i).getInt(
						"complain_id"));

				complaint_status.add(jArray.getJSONObject(i).getString(
						"complaint_status"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
%>


<div id="complainStatus">
	<table>
		<tr>
			<th>S.No.</th>
			<th>Complain Subject</th>
			<th>Status</th>
			<th>Time</th>
			<th>Update Complain</th>
		</tr>
		<%
			for (int i = 0; i < jArray.length(); i++) {
		%>
		<tr>
			<td><%=i + 1%></td>
			<td><%=complaint_subject.get(i)%></td>
			<td><%=complaint_status.get(i)%></td>
			<td><%=complaint_time.get(i)%></td>

			<%
				if (complaint_status.get(i).equals("pending")) {
			%>
			<td><div class="updateComplain">
					<a href=".?pgid=user&complainId=<%=complaint_id.get(i)%>">Update
						Complain</a>
				</div> <%
 	} else {
 %>
			
			<td><div class="updateComplain">DONE</div> <%
 	}
 %>
			</td>
		</tr>
		<%
			}
		%>



	</table>




</div>
<%
	} else {
		response.sendRedirect("http://localhost:8080/TGMC/beta");
	}
%>