<%@page import="com.law.order.model.PoliceOfficer"%>
<%@page import="com.law.order.controller.PoliceController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String officer_name = request.getParameter("name");
	String email_id = request.getParameter("email_id");
	String gender = request.getParameter("gender");
	String pincode = request.getParameter("pincode");
	String mobile = request.getParameter("mobile");
	String address_block = request.getParameter("address_block");
	int pid = Integer.parseInt(request.getParameter("pid"));

	PoliceOfficer policeOfficer = new PoliceOfficer(pid, email_id, 0,
			0, 0, "", officer_name, mobile, "", gender);
	String resp = policeOfficer.update();
	out.println(resp);
%>