<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.model.CriminalRecord"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" type="text/css"
	href="./includes/css/showdata.css" />
<%
	if (session.getAttribute("pid") != null
			&& !session.getAttribute("pid").toString().isEmpty()) {
%>

<%
	//out.println("Criminal data" + request.getParameter("criminalid"));
		int criminalid = Integer.parseInt(request
				.getParameter("criminalid"));

		CriminalRecord criminalRecord = new CriminalRecord();
		criminalRecord.setCriminal_id(criminalid);
		JSONObject resp = criminalRecord.retrive();
		//out.println(resp.toString());
%>

<h1>Criminal Data</h1>
<div class="table data">
	<table>
		<tr>
			<th>Name</th>
			<td><%=resp.getString("criminal_name")%></td>
		</tr>
		<tr>
			<th>Criminal Image</th>
			<td><img src='<%=resp.getString("image_url")%>' height="200px"
				width="150px" />
			</td>

		</tr>
		<tr>
			<th>Height</th>
			<td><%=resp.getString("height")%></td>
		</tr>
		<tr>
			<th>Weight</th>
			<td><%=resp.getString("weight")%></td>
		</tr>
		<tr>
			<th>Complexion</th>
			<td><%=resp.getString("complexion")%></td>
		</tr>
		<tr>
			<th>Identification Marks</th>
			<td><%=resp.getString("identification_marks")%></td>
		</tr>
		<tr>
			<th>Date of Birth</th>
			<td><%=resp.getString("date_of_birth")%></td>
		</tr>






	</table>
</div>
<%
	} else
		response.sendRedirect("./../");
%>