<%@page import="com.law.order.model.utility.DBUtility"%>
<%@page import="com.law.order.controller.DepartmentController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="com.law.order.controller.PoliceController"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="java.io.PrintWriter,java.util.regex.*,java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	if (session.getAttribute("pid") != null
		&& !session.getAttribute("pid").toString().isEmpty()) {
%>


<html>
<head>
<link rel="stylesheet" href="./includes/css/form.css" type="text/css"
	media="screen" />
<script type="text/javascript">
	$(document).ready(function() {

		$("#send").click(function(e) {
			alert("A");
			e.preventDefault(); // prevent the browser from following the link
			e.stopPropagation(); // prevent the browser from following the link
			var data_content = $("form#createvpsForm").serialize();

			alert(data_content);
			$.ajax({
				url : "./includes/submit/submit_vps.jsp",
				cache : false,
				data : data_content,
				success : function(data) {
					//$("div#customPopup").html(data);
					alert("success" + data);

				}
			});
			//alert(data_content);

		});

	});
</script>
<style>
#createvpsForm {
	margin-left: 200px;
}

#createvpsForm select {
	height: 300px;
	width: 500px;
}

#createvpsForm input {
	width: 300px;
}

#createvpsForm label {
	display: block;
}
</style>
</head>
<%
	ArrayList<String> policeList = new ArrayList<String>();
		ArrayList<String> policeId = new ArrayList<String>();
		ArrayList<String> deptId = new ArrayList<String>();
		ArrayList<String> deptName = new ArrayList<String>();
		//-----------------------------------------------------------
		PoliceController police = new PoliceController();
		Response resp = police.policeofficeretrieve(0, "", "createvps");
		JSONObject json = new JSONObject(resp.getEntity().toString());
		//out.println(json.toString());
		JSONArray jArray = json.getJSONArray("content");

		for (int i = 0; i < jArray.length(); i++) {
			policeList.add(
					i,
					new JSONObject(jArray.get(i).toString())
							.getString("officer_id")
							+ "-"
							+ new JSONObject(jArray.get(i).toString())
									.getString("officer_name")
							+ "-"
							+ new JSONObject(jArray.get(i).toString())
									.getString("email_id"));

			policeId.add(i, new JSONObject(jArray.get(i).toString())
					.getString("officer_id"));
		}
		//out.println(policeList.get(0));
		//out.println(policeId.get(0));
		//--------------------------------------------------------------------------

		DepartmentController dept = new DepartmentController();
		Response respDept = dept.retrieve("all");
		JSONObject jsonDept = new JSONObject(respDept.getEntity()
				.toString());
		//out.println(jsonDept);
		JSONArray jArrayDept = jsonDept.getJSONArray("content");

		for (int i = 0; i < jArrayDept.length(); i++) {
			deptName.add(i,
					new JSONObject(jArrayDept.get(i).toString())
							.getString("dept_name"));

			deptId.add(i, new JSONObject(jArrayDept.get(i).toString())
					.getString("dept_type_id"));
		}

		//--------------------------------------------------------------------------------
		ArrayList<String> pincode = new DBUtility().getPincodeTable();
		//out.println(pincode.toString());
%>




<body>

	<h1>Create VPS</h1>

	<div>
		<form method="post" id="createvpsForm" class="form" action="">
			<label>( * ) are mandatory</label>
			<div>
				<label>VPS Name * :</label> <input id="vpsname" name="vpsname"
					type="text" value='' /><span id="vpsnameInfo"></span>
			</div>




			<div>
				<label>Sub-Admin Id * :</label> <select name="subadmin"
					id="subadmin" style="height: 30px;">

					<%
						for (int i = 0; i < policeId.size(); i++) {
					%>
					<option value="<%=policeId.get(i)%>"><%=policeList.get(i)%></option>


					<%
						}
					%>

				</select><span id="subadminInfo"></span>
			</div>




			<div>
				<label>Pincode List * :</label> <select name="pincodelist"
					id="pincodelist" multiple="multiple">
					<%
						for (int i = 0; i < pincode.size(); i++) {
					%>
					<option value="<%=pincode.get(i)%>"><%=pincode.get(i)%></option>


					<%
						}
					%>
				</select> <span id="pincodelistInfo"></span>
			</div>


			<div>
				<label>Departments * :</label> <select name="departments"
					id="departments" multiple="multiple">
					<%
						for (int i = 0; i < deptName.size(); i++) {
					%>
					<option value="<%=deptId.get(i)%>"><%=deptName.get(i)%></option>


					<%
						}
					%>
				</select> <span id="departmentsInfo"></span>
			</div>



			<div>
				<label>VPS Officer Id * :</label> <select name="officerid"
					id="officerid" multiple="multiple">
					<%
						for (int i = 0; i < policeId.size(); i++) {
					%>
					<option value="<%=policeId.get(i)%>"><%=policeList.get(i)%></option>


					<%
						}
					%>
				</select> <span id="officeridInfo"></span>
			</div>



			<div>
				<input id="send" name="send" type="submit"
					value="Create NEW Virtual Police Station" />

			</div>

		</form>


	</div>


</body>
</html>
<%
	} else
		response.sendRedirect("./../");
%>