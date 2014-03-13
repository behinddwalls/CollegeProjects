<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.regex.*,java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!String emailid = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	boolean validateName(String name) {
		
		if (name.length() < 6)
			return false;
		return true;
	}

	boolean validateEmail(String email_id) {
		Pattern p = Pattern.compile(emailid);
		Matcher m = p.matcher(email_id);
		return m.matches();
	}

	boolean validatePassword(String pass1, String pass2) {
		if (pass1.length() > 5 && pass1.equals(pass2))
			return true;
		
		return false;
	}

	boolean validateDOB(String dob) {
		if (dob.length() < 10)
			return false;
		return true;
	}

	boolean validateGender(String gender) {
		if (gender.equals("male") || gender.equals("Male")
				|| gender.equals("female") || gender.equals("Female"))
			return true;
		return false;
	}

	boolean validatePincode(String pincode) {

		try {
			if (pincode.length() < 6)
				return false;
			double d = Double.parseDouble(pincode);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;

	}

	boolean validateAddress(String address) {
		if (address.length() < 10)
			return false;

		return true;
	}%>
