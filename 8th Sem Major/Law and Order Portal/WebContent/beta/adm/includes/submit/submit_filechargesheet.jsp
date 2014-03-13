<%@page import="com.law.order.model.CriminalStatus"%>
<%@page import="com.law.order.model.ComplaintStatus"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	int complain_id = Integer.parseInt(request
			.getParameter("complain_id"));
	String arrestWarrant = request.getParameter("arrestwarrant");
	String property = request.getParameter("property");
	String disposal = request.getParameter("disposal");
	String arrestStatus = "Arrest NO";
	String propertyStatus = "Seize No";
	String disposalStatus = "Disposal No";

	int aid = Integer.parseInt(request.getParameter("aid"));
	out.println(request.getParameter("aid"));
	out.println(request.getParameter("wid"));
	out.println(request.getParameter("property"));
	out.println(request.getParameter("disposal"));
	if (arrestWarrant.equals("Yes")) {
		arrestStatus = "Arrest Yes";
	}
	if (property.equals("Yes")) {
		propertyStatus = "Seize Yes";
	}
	if (disposal.equals("Yes")) {
		disposalStatus = "Disposal Yes";
	}

	for (int i = 0; i < aid; i++) {
		new CriminalStatus(Integer.parseInt(request
				.getParameter("accusedid" + (i + 1))), arrestStatus)
				.create();
		new CriminalStatus(Integer.parseInt(request
				.getParameter("accusedid" + (i + 1))), propertyStatus)
				.create();
		new CriminalStatus(Integer.parseInt(request
				.getParameter("accusedid" + (i + 1))), disposalStatus)
				.create();

	}

	ComplaintStatus comp = new ComplaintStatus();
	comp.setComplaint_id(complain_id);
	comp.setComplaint_status_name("Complete");
	comp.update();
%>