<%@page import="com.law.order.controller.PoliceController"%>
<%@page import="com.law.order.model.ComplaintStatus"%>
<%@page import="com.law.order.model.Complain"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.controller.CitizenController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	int complain_id = Integer.valueOf(request
			.getParameter("complainid"));
	String complaintSubject = request.getParameter("complaintSub");
	String complaintDesc = request.getParameter("complaintDesc");

	//int vps_id=Integer.valueOf(request.getParameter("vps_id"));

	if (request.getParameter("ref").equals("new")) {
		int department = Integer.valueOf(request.getParameter("dept"));
		int subDepartment = Integer.valueOf(request
				.getParameter("subDept"));
		int assignedPolice = Integer.valueOf(request
				.getParameter("assignedPolice"));

		PoliceController policeController = new PoliceController();
		Response res = policeController.updateComplain(complain_id,
				complaintSubject, complaintDesc, department,
				subDepartment, assignedPolice, 0);
		String resp = res.getEntity().toString();
		if (resp.equals("Success"))
			out.println("SUCCESS");
		else
			out.println("FAILED");

	} else {
		out.println("pending");
		//changing status
		ComplaintStatus comp = new ComplaintStatus();
		comp.setComplaint_id(complain_id);
		comp.setComplaint_status_name("pending FIR");
		comp.update();
	}
%>