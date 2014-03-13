<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.controller.VpsController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin</title>
<link rel="stylesheet" type="text/css" href="./includes/css/main.css" />
<script type="text/javascript" src="./includes/js/jquery.js"></script>
<script type="text/javascript" src="./includes/js/dynamicload.js"></script>
<script type="text/javascript" src="./includes/js/main.js"></script>
</head>
<%
	if (session.getAttribute("pid") != null
			&& !session.getAttribute("pid").toString().isEmpty()) {

		VpsController vpsController = new VpsController();
		Response resp = vpsController
				.getVPSNAMEBYVPSID((Integer) session
						.getAttribute("vps_id"));
%>
<body>
	<div id="container">
		<div id="header">
			<!-- <div id="search">
				<form id="searchForm" action="get" method="post">
					<input type="text" name="searchField" id="searchField" placeholder="Search..."/> <br />
					<input type="submit" name="search" id="searchSend" value="Search" />
				</form>
			</div> -->

			<div id="logo">
				<img src="./media/logo.gif" width="100px" height="70px" title="logo"
					alt="logo" />
			</div>
			<div id="department">
				<h5>
					Virtual Police Station ,
					<%=resp.getEntity().toString()%></h5>
				<h2>
					Department of
					<%
					out.println(session.getAttribute("dept"));
				%>
				</h2>

			</div>

			<!-- div id="top-nav">
				<ul>
					<li><img src="./media/logo.gif" width="15px" height="15px" />
					</li>
					<li>Home</li>

					<li>Profile</li>
					<li>Logout</li>


				</ul>

			</div-->


		</div>
		<div id="top_nav">
			<table id="nav_tab">
				<tr>
					<th><a href="">Home</a></th>
					<th><a href="./../?pgid=missing">Missing Person</a></th>
					<th><a href="./../?pgid=wantedlist">Wanted List</a></th>

				</tr>
			</table>
		</div>
		<%
			} else
				response.sendRedirect("./../");
		%>