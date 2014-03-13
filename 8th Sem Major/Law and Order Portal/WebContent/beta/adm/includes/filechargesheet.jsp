<%@page import="org.eclipse.jetty.util.ajax.JSON"%>
<%@page import="com.law.order.model.utility.DBUtility"%>
<%@page import="com.sun.codemodel.JArray"%>
<%@page import="com.law.order.controller.CriminalController"%>
<%@page import="com.law.order.model.Accused"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="com.law.order.controller.CitizenController"%>
<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.controller.PoliceController"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<%
	if (session.getAttribute("pid") != null
		&& !session.getAttribute("pid").toString().isEmpty()) {
%>
<head>
<link rel="stylesheet" href="./includes/css/form.css" type="text/css"
	media="screen" />
<script type="text/javascript"
	src="./includes/js/complaint_validation.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		
		
		var ref= '<%=request.getParameter("ref")%>';
		if (ref == "closed") {
			$("#chargesheetForm input").attr("disabled", "disabled");
			$("#chargesheetForm select").attr("disabled", "disabled");
			$("#chargesheetForm textarea").attr("disabled", "disabled");
			$("#chargesheetForm #send").remove();
			$("#chargesheetForm #cancel").remove();

		}

		$('#chargesheetForm #cancel').click(function(e) {
			alert("A");
			e.preventDefault(); // prevent the browser from following the link
			e.stopPropagation(); // prevent the browser from following the link
			var data_content = $("form#chargesheetForm").serialize();
			var id = $("#cancel").attr("data-content");

			alert(id);
			alert(data_content);
			$.ajax({
				url : $(this).attr('href'),
				cache : false,
				data : data_content,
				success : function(data) {
					//$("div#customPopup").html(data);
					alert("success");
					$("#" + id).html("REJECTD ").css("color", "RED");
					$("#customPopup").hide();
					$("#backShadow").hide();

				}
			});

		});

	});
</script>
<style>
#chargesheetForm {
	margin-left: 20%;
}
</style>
<%
	String complain_content = null, complain_subject = null, citizen_name = null, gender = null, identification_copy = null, pincode = null, address = null;
		int complain_id = 0, citizen_id = 0, sub_dept_id = 0;
		JSONArray jArray1 = null, jArray2 = null;
		complain_id = Integer.valueOf(request
				.getParameter("complaintId"));
		citizen_id = Integer.valueOf(request.getParameter("citizenId"));

		//out.println(request.getParameter("complaintId"));
		//out.println(request.getParameter("citizenId"));

		String id = request.getParameter("id");
		int uid = (Integer) session.getAttribute("pid");// (String) session.getAttribute("citizenid");
		ArrayList<Integer> witness_complaint_id = new ArrayList<Integer>();
		ArrayList<Integer> witness_id = new ArrayList<Integer>();
		ArrayList<String> witness_name = new ArrayList<String>();
		ArrayList<String> witness_address = new ArrayList<String>();
		ArrayList<String> witness_pincode = new ArrayList<String>();
		ArrayList<String> witness_mobile = new ArrayList<String>();
		ArrayList<Integer> accused_complaint_id = new ArrayList<Integer>();
		ArrayList<Integer> accused_id = new ArrayList<Integer>();
		ArrayList<String> accused_name = new ArrayList<String>();
		ArrayList<String> accused_address = new ArrayList<String>();
		ArrayList<String> accused_pincode = new ArrayList<String>();
		ArrayList<String> accused_mobile = new ArrayList<String>();
		int dept_id = (Integer) session.getAttribute("dept_id");
		if (complain_id != 0) {

			// out.println(resp.getEntity());
			try {
				CitizenController citizen = new CitizenController();
				Response resp = citizen.retrieveSpecificComplain(
						complain_id, citizen_id);

				complain_subject = new JSONObject(resp.getEntity()
						.toString()).getString("complain_subject");
				complain_content = new JSONObject(resp.getEntity()
						.toString()).getString("complaint_content");
				sub_dept_id = new JSONObject(resp.getEntity()
						.toString()).getInt("sub_dept_id");
				citizen_name = new JSONObject(resp.getEntity()
						.toString()).getString("citizen_name");
				address = new JSONArray(new JSONObject(resp.getEntity()
						.toString()).getString("address_id"))
						.getJSONObject(0).getString("address_block");
				pincode = new JSONArray(new JSONObject(resp.getEntity()
						.toString()).getString("address_id"))
						.getJSONObject(0).getString("pincode");
				identification_copy = new JSONObject(resp.getEntity()
						.toString()).getString("identification_copy");

				//out.println(sub_dept_id);

				CriminalController crime = new CriminalController();
				Response resp1 = crime.retrieveAccused(complain_id);
				jArray1 = new JSONArray(resp1.getEntity().toString());
				Response resp2 = crime.retrieveWitness(complain_id);
				jArray2 = new JSONArray(resp2.getEntity().toString());

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
%>




</head>




<body>

	<center>
		<%
			if (request.getParameter("ref").toString().equals("closed")){
		%>
		<h1>Closed Complain Details</h1>
		<%
			}else{
		%>
		<h1>File Chargesheet</h1>
		<%} %>

		<label>( * ) are mandatory</label>
	</center>

	<form method="post" class="form" id="chargesheetForm" action="">

		<div>
			<label>Complainer</label>
		</div>

		<div>
			<input id="complain_id" name="complain_id" type="hidden"
				value='<%=complain_id%>' />
		</div>
		<div>
			<label>Full Name * :</label> <input id="name" name="name" type="text"
				value='<%=citizen_name%>' readonly /> <span id="nameInfo"></span>
		</div>


		<div>
			<label>Gender * :</label> <select name="gender" id="gender">
				<option value="1">Gender *</option>
				<option value="2" selected>Male</option>
				<option value="3">Female</option>
			</select> <span id="genderInfo"></span>
		</div>

		<div>
			<label>Complaint Subject* :</label> <input id="complainSub"
				name="complainSub" type="text" value='<%=complain_subject%>' /> <span
				id="complainSubInfo"></span>
		</div>
		<div>
			<label>Complain Description * :</label>
			<textarea id="complainDesc" name="complainDesc"><%=complain_content%></textarea>
			<span id="complainDescInfo"></span>
		</div>

		<div>
			<label>Pincode * :</label> <input id="pincode" name="pincode"
				type="text" value='<%=pincode%>' readonly /> <span id="pincodeInfo"></span>
		</div>



		<div>
			<label>Address * :</label> <input id="address_block"
				name="address_block" type="text" value='<%=address%>' readonly /> <span
				id="address_blockInfo"></span>
		</div>

		<div>
			<label>Identification Copy* :</label> <input id="file" name="file"
				type="text" value="<%=identification_copy%>" readonly /> <span
				id="fileInfo"></span>
		</div>

		<div>
			<label>Accused</label>
		</div>

		<%
			JSONObject jObjectAddr = null;
				for (int i = 0; i < jArray1.length(); i++) {

					accused_id.add(jArray1.getJSONObject(i)
							.getInt("accused_id"));
					accused_complaint_id.add(jArray1.getJSONObject(i).getInt(
							"complaint_id"));
					accused_name.add(jArray1.getJSONObject(i).getString(
							"accused_name"));
					accused_mobile.add(jArray1.getJSONObject(i).getString(
							"accused_mobileno"));
					//out.println(jArray1.getJSONObject(i).get("address_id"));

					JSONArray jAddr = new JSONArray(jArray1.getJSONObject(i)
							.get("address_id").toString());
					jObjectAddr = new JSONObject(jAddr.get(0).toString());

				}
		%>
		<%
			for (int i = 0; i < jArray1.length(); i++) {
		%>
		<hr />
		<br />
		<div>
			<label>Accused<%=i + 1%></label> <input id="accusedid<%=i + 1%>"
				name="accusedid<%=i + 1%>" type="hidden"
				value="<%=accused_id.get(i)%>" /> <input type="hidden" name="aid"
				value="<%=jArray1.length()%>" />
		</div>

		<div>
			<label>Accused Full Name * :</label> <input
				id="accusedname<%=i + 1%>" name="accusedname<%=i + 1%>" type="text"
				value='<%=accused_name.get(i)%>' readonly /> <span
				id="accusednameInfo<%=i + 1%>"></span>
		</div>


		<!-- <div>
			<label>Gender * :</label> <select name="accusedgender"
				id="accusedgender">
				<option value="1">Gender *</option>
				<option value="2">Male</option>
				<option value="3">Female</option>
			</select> <span id="accusedgenderInfo"></span>
		</div> -->


		<div>
			<label>Mobile * :</label> <input id="accusedmobile<%=i + 1%>"
				name="accusedmobile<%=i + 1%>" type="text"
				value='<%=accused_mobile.get(i)%>' readonly /> <span
				id="accusedmobileInfo<%=i + 1%>"></span>
		</div>

		<div>
			<label>Pincode * :</label> <input id="accusedpincode<%=i + 1%>"
				name="accusedpincode<%=i + 1%>" type="text"
				value='<%=jObjectAddr.getString("pincode")%>' readonly /> <span
				id="accusedpincodeInfo<%=i + 1%>"></span>
		</div>



		<div>
			<label>Address * :</label> <input id="accusedaddress_block<%=i + 1%>"
				name="accusedaddress_block<%=i + 1%>" type="text"
				value='<%=jObjectAddr.getString("address_block")%>' readonly /> <span
				id="accusedaddress_blockInfo<%=i + 1%>"></span>
		</div>
		<hr />

		<%
			}

				JSONObject jCrime = new DBUtility()
						.retrieveCrimeLT(complain_id);
		%>



		<div>
			<label>Crime Location</label>
		</div>


		<div>
			<label>Crime Address * :</label> <input id="crimeaddress"
				name="crimeaddress" type="text"
				value='<%=jCrime.getString("address_block")%>' readonly /> <span
				id="crimeaddressInfo"></span>
		</div>




		<div>
			<label>Pincode * :</label> <input id="crimepincode"
				name="crimepincode" type="text"
				value='<%=jCrime.getString("pincode")%>' readonly /> <span
				id="crimepincodeInfo"></span>
		</div>


		<div>
			<label>Crime Time * :</label> <input id="crimetime" name="crimetime"
				type="time" value='<%=jCrime.getString("crime_time")%>' readonly />
			<span id="crimetimeInfo"></span>
		</div>



		<div>
			<label>Witness Info</label>
		</div>
		<%
			JSONObject jObjectAddr1 = null;
				for (int i = 0; i < jArray2.length(); i++) {

					witness_id.add(jArray2.getJSONObject(i)
							.getInt("witness_id"));
					witness_complaint_id.add(jArray2.getJSONObject(i).getInt(
							"complaint_id"));
					witness_name.add(jArray2.getJSONObject(i).getString(
							"witness_name"));
					witness_mobile.add(jArray2.getJSONObject(i).getString(
							"witness_mobileno"));
					JSONArray jAddr = new JSONArray(jArray1.getJSONObject(i)
							.get("address_id").toString());
					jObjectAddr1 = new JSONObject(jAddr.get(0).toString());

				}
		%>
		<%
			for (int i = 0; i < jArray2.length(); i++) {
		%>
		<hr />
		<br />
		<div>
			<label>Witness<%=i + 1%></label><input id="witnessid<%=i + 1%>"
				name="witnessid<%=i + 1%>" type="hidden" <%=witness_id.get(i)%>" />
			<input type="hidden" name="wid" value="<%=jArray2.length()%>" />
		</div>

		<div>
			<label>Witness Name * :</label> <input id="witnessname<%=i + 1%>"
				name="witnessname<%=i + 1%>" type="text"
				value='<%=witness_name.get(i)%>' readonly /> <span
				id="witnessnameInfo<%=i + 1%>"></span>
		</div>



		<div>
			<label>Witness Address * :</label> <input
				id="witnessaddress<%=i + 1%>" name="witnessaddress<%=i + 1%>"
				type="text" value='<%=jObjectAddr1.getString("address_block")%>'
				readonly /> <span id="witnessaddressInfo<%=i + 1%>"></span>
		</div>

		<div>
			<label>Witness Mobile * :</label> <input id="witnessmobile<%=i + 1%>"
				name="witnessmobile<%=i + 1%>" type="text"
				value='<%=witness_mobile.get(i)%>' readonly /> <span
				id="witnessmobileInfo<%=i + 1%>"></span>
		</div>



		<div>
			<label>Witness Pincode * :</label> <input
				id="witnesspincode<%=i + 1%>" name="witnesspincode<%=i + 1%>"
				type="text" value='<%=jObjectAddr1.getString("pincode")%>' readonly />
			<span id="witnessInfo<%=i + 1%>"></span>
		</div>



		<!-- <div>
			<label>Witness State * :</label> <input id="witnessstate"
				name="witnessstate" type="text" value='W_STATE' readonly /> <span
				id="witnessstateInfo"></span>
		</div> -->
		<%
			}
		%>

		<hr />
		<br />

		<div>
			<label>Issue Arrest Warrant :</label> <input class="arrestwarrant "
				name="arrestwarrant" type="radio" value="Yes" />Yes <input
				class="arrestwarrant " name="arrestwarrant" type="radio" value="No"
				checked />No <span id="arrestwarrantInfo"></span>
		</div>
		<div>
			<label>Seize Property :</label> <input class="property"
				name="property" type="radio" value="Yes" />Yes <input
				class="property" name="property" type="radio" value="No" checked />No
			<span id="propertyInfo"></span>
		</div>
		<div>
			<label>Court Disposal :</label> <input class="disposal"
				name="disposal" type="radio" value="Yes" />Yes <input
				class="disposal" name="disposal" type="radio" value="No" checked />No
			<span id="disposalInfo"></span>
		</div>


		<br />



		<div>
			<input id="send" name="send" type="submit" value="Create Chargesheet"
				data-content="<%=id%>" /> <input id="cancel" name="cancel"
				type="button" value="Reject" data-content="<%=id%>" />
		</div>



	</form>

</body>
</html>
<%
	} else
		response.sendRedirect("./../");
%>
