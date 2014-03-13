<%@page import="com.law.order.controller.VpsController"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.law.order.model.ComplaintStatus"%>
<%@page import="com.law.order.model.Complain"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	String complaintSub = request.getParameter("complainSubject");
	String complainDesc = request.getParameter("complainDesc");
	int citizenId = Integer.parseInt(request.getParameter("citizenId"));

	String status = request.getParameter("status");
	if (request.getParameter("complainid") != null
			&& !request.getParameter("complainid").toString().isEmpty()) {
		int complainId = Integer.parseInt(request
				.getParameter("complainid"));

		Complain complain = new Complain();
		complain.setComplain_subject(complaintSub);
		complain.setComplaint_content(complainDesc);
		complain.setComplaint_id(complainId);
		complain.updateComplainByCitizen();

		out.println("complain");
	} else {

		int vps_id = Integer.parseInt(new VpsController()
				.getVpsIdByPincode(
						(String) session.getAttribute("pincode"))
				.getEntity().toString());
		out.println(citizenId);
		String date = new SimpleDateFormat("dd-MM-yyyy")
				.format(new Date());
		Complain complain = new Complain(0, citizenId, complainDesc,
				date, 0, 0, "", 0, complaintSub, vps_id);
		complain.create();
		int complaintid = complain.getComplaint_id();
		out.println(complaintid + "Hello");

		ComplaintStatus comp = new ComplaintStatus();
		comp.setComplaint_id(complaintid);
		comp.setComplaint_status_name("pending");
		comp.create();

	}
%>
