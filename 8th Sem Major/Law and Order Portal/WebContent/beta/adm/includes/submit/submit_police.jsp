<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.controller.PoliceController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	String name = request.getParameter("name");
	String email_id = request.getParameter("email_id");
	String password = request.getParameter("password");
	String dob = request.getParameter("dob");
	String gender = request.getParameter("gender");
	String pincode = request.getParameter("pincode");
	String address = request.getParameter("address_block");
	String mobile = request.getParameter("mobile");
	String path = request.getParameter("pid");
	String role = request.getParameter("role");

	PoliceController police = new PoliceController();
	Response resp = police.policeofficercreate(email_id, password,
			name, pincode, address, mobile, path, gender, role);
	out.println(resp.getEntity());
%>


