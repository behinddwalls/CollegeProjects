<%@page import="com.law.order.model.PoliceOfficer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String pass1 = request.getParameter("pass1");
	String pass2 = request.getParameter("pass2");
	int pid = Integer.parseInt(request.getParameter("pid"));
	if (pass1.equals(pass2)) {
		PoliceOfficer policeOfficer = new PoliceOfficer();

		String resp = policeOfficer.updatePass(pid, pass1);
		out.println(resp);
	} else
		out.println("Password DIDNT MATCH");
%>