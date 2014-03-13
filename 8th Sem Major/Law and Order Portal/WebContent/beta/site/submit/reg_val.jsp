
<%@page import="javax.jms.Session"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.controller.CitizenController"%>
<%@page import="com.law.order.model.Citizen"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.regex.Matcher"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>

<%
	boolean nameVal = false, emailVal = false, passwordVal = false, dobVal = false, mobileVal = false, genderVal = false, pincodeVal = false, address_blockVal = false;
	String name = request.getParameter("name");
	String email_id = request.getParameter("email_id");
	String password = request.getParameter("pass1");
	String pass2 = request.getParameter("pass2");
	String dob = request.getParameter("dob");
	String mobile = request.getParameter("mobile");
	String gender = request.getParameter("gender");
	String pincode = request.getParameter("pincode");
	String address_block = request.getParameter("address_block");
	String profilepic = "";

	String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	Pattern pattern = Pattern.compile(EMAIL_PATTERN);
	Matcher matcher = pattern.matcher(email_id);

	if (name.length() >= 4) {
		nameVal = true;

	}
	if (matcher.matches()) {
		emailVal = true;

	}

	if (password.equals(pass2)) {
		passwordVal = true;
	}
	if (dob.length() == 10) {
		dobVal = true;
	}
	if (mobile.length() == 10) {
		mobileVal = true;
	}
	if (!gender.equalsIgnoreCase("1")) {
		genderVal = true;
	}
	if (pincode.length() == 6) {

		pincodeVal = true;
	}
	if (address_block.length() > 1) {
		address_blockVal = true;
	}

	if (nameVal && emailVal && passwordVal && dobVal && mobileVal
			&& genderVal && pincodeVal && address_blockVal) {

		//out.println(name+","+email_id+","+dob+","+gender+","+pincode+","+address_block+","+mobile+","+password+","+profilepic);
		CitizenController citizenController = new CitizenController();
		Response resp = citizenController.createCitizen(name, email_id,
				dob, gender, pincode, address_block, mobile, password,
				profilepic);
		//out.println(resp.getEntity().toString());
		JSONObject jsonObject = new JSONObject(resp.getEntity()
				.toString());
		if (jsonObject.getString("status").equalsIgnoreCase("SUCCESS")) {
			out.println("SUCCESS");

			session.setAttribute("mobile", mobile);
			session.setAttribute("citizen_id", jsonObject.getInt("uid"));
			/* 
			 response.addHeader("mobile", mobile);
			 response.addHeader("status", "SUCCESS");
			 response.sendRedirect("../idupload.jsp"); */

		} else {
			out.println("FAILURE");
		}
	} else
		out.println("FAILURE-2");
%>



























