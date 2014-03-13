<%@page import="com.law.order.model.ComplaintStatus"%>
<%@page import="com.law.order.controller.PoliceController"%>
<%@page import="com.law.order.model.Accused"%>
<%@page import="com.law.order.model.utility.DBUtility"%>
<%@page import="com.law.order.model.utility.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	int complain_id = Integer.valueOf(request
			.getParameter("complainid"));
	int sub_dept_id = Integer.valueOf(request
			.getParameter("sub_dept_id"));
	String crimeaddress = request.getParameter("crimeaddress");
	String crimepincode = request.getParameter("crimepincode");
	String crimestate = request.getParameter("crimestate");
	String crimetime = request.getParameter("crimetime");
	int aid = Integer.valueOf(request.getParameter("aid"));
	int wid = Integer.valueOf(request.getParameter("wid"));

	String respCrime = new DBUtility().insertCrimeLT(complain_id,
			sub_dept_id, crimetime, crimepincode, crimeaddress);

	for (int i = 1; i <= aid; i++) {
		String aName = request.getParameter("accusedname"
				.concat(Integer.toString(i)));
		String aGender = request.getParameter("accusedgender"
				.concat(Integer.toString(i)));
		String aPincode = request.getParameter("accusedpincode"
				.concat(Integer.toString(i)));
		String aMobile = request.getParameter("accusedmobile"
				.concat(Integer.toString(i)));
		String aAddress = request.getParameter("accusedaddress_block"
				.concat(Integer.toString(i)));

		out.println(aName + "aname");
		PoliceController policeController = new PoliceController();
		policeController.createAccused(aName, aGender, aPincode,
				aAddress, aMobile, complain_id);

	}

	for (int j = 1; j <= wid; j++) {
		String wName = request.getParameter("witnessname"
				.concat(Integer.toString(j)));
		String wGender = request.getParameter("witnessgender"
				.concat(Integer.toString(j)));
		String wPincode = request.getParameter("witnesspincode"
				.concat(Integer.toString(j)));
		String wMobile = request.getParameter("witnessmobile"
				.concat(Integer.toString(j)));
		String wAddress = request.getParameter("witnessaddress_block"
				.concat(Integer.toString(j)));

		out.println(wName + "wname");
		PoliceController policeController = new PoliceController();
		policeController.createWitness(wName, wGender, wPincode,
				wAddress, wMobile, complain_id);
	}

	ComplaintStatus comp = new ComplaintStatus();
	comp.setComplaint_id(complain_id);
	comp.setComplaint_status_name("pending Chargesheet");
	comp.update();
%>