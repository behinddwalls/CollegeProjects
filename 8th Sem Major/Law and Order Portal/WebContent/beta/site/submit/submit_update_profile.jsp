<%@page import="com.law.order.controller.CitizenController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String name = request.getParameter("name");
	String email_id = request.getParameter("email_id");
	String date_of_birth = request.getParameter("dob");
	String gender = request.getParameter("gender");
	String pincode = request.getParameter("pincode");
	String address_block = request.getParameter("address_block");
	String mobile = request.getParameter("mobile");
	int uid = Integer.parseInt(request.getParameter("citizenId"));
	String password = request.getParameter("pass1");
	int addressId = Integer.parseInt(request.getParameter("addrId"));

	out.println(name + " " + password);

	CitizenController citizenController = new CitizenController();
	citizenController.updateCitizen(uid, name, email_id, date_of_birth,
			gender, pincode, address_block, mobile, password, "",
			addressId);
%>