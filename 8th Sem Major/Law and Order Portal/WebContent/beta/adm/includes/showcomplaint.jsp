<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="com.law.order.model.PoliceOfficer"%>
<%@page import="com.law.order.model.ComplaintStatus"%>
<%@page import="com.law.order.model.Complain"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.controller.CitizenController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	if (session.getAttribute("pid") != null
			&& !session.getAttribute("pid").toString().isEmpty()) {
%>
<%
	int complain_id = 0, citizen_id = 0;

		String complain_content = null, complain_subject = null, citizen_name = null;
		int dept_id = 0, sub_dept_id = 0, police_id = 0;
		complain_id = Integer.valueOf(request
				.getParameter("complaintId"));
		citizen_id = Integer.valueOf(request.getParameter("citizenId"));
		String id = request.getParameter("id");
%>
<%
	JSONArray jArray = null;
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
				citizen_name = new JSONObject(resp.getEntity()
						.toString()).getString("citizen_name");
				dept_id = new JSONObject(resp.getEntity().toString())
						.getInt("dep_type_id");
				sub_dept_id = new JSONObject(resp.getEntity()
						.toString()).getInt("sub_dept_id");
				police_id = new JSONObject(resp.getEntity().toString())
						.getInt("assigned_police_id");

				//out.println(resp.getEntity().toString());

			} catch (Exception e) {
				e.printStackTrace();
			}

			PoliceOfficer policeOfficer = new PoliceOfficer();
			policeOfficer.setVps_id((Integer) session
					.getAttribute("vps_id"));
			jArray = policeOfficer.retriveByVPSId();
			//out.println(jArray.toString());

		}
		out.println(dept_id);
%>



<head>
<link rel="stylesheet" type="text/css" href="./includes/css/form.css" />
<script>
	$(document)
			.ready(
					function() {
						
						var ref = '<%=request.getParameter("ref")%>';
						if (ref == "new") {
							//alert(ref);
							$("#send").val("Assign Dept & Pfficer");
							$("#complainForm input").attr('readonly',
									'readonly');
							$("#complainForm textarea").attr('readonly',
									'readonly');

						} else {
							//alert(ref);
							$("#complainForm input").attr('readonly',
									'readonly');
							$("#complainForm select").attr('disabled',
									'disabled');
							$("#complainForm textarea").attr('readonly',
									'readonly');
							$("#complainForm #send").removeAttr('readonly',
									'readonly');
							$("#send").val("Create FIR");
							$("input #send").removeAttr('readonly',	'readonly');
							
							if(ref != "pending")
								{
								$("#send").remove();
								$("#cancel").remove();
								}

						}

						//alert(ref);

						$("#dept")
								.bind(
										'change',
										function() {

											//alert($(this).find('option:selected').val());
											var selected = $(this).find(
													'option:selected').val();
											var option = $('<option/>');
											var selector = $('#subDept');
											if (selected === "1") {
												//alert("Select 1");

												$(selector)
														.find('option')
														.remove()
														.end()
														.append(
																'<option value="1">Murder</option>')
														.append(
																'<option value="2" >Robbery</option>')
														.append(
																'<option value="3" >Kidnaping</option>')
														.append(
																'<option value="4" >Missing</option>')
														.append(
																'<option value="5" >Drug Trade</option>')
														.append(
																'<option value="6" >Corruption</option>')
														.append(
																'<option value="7" >Others</option>')

											} else if (selected === "2") {
												//alert("Select 2");

												$(selector)
														.find('option')
														.remove()
														.end()
														.append(
																'<option value="1">Rape</option>')
														.append(
																'<option value="2">Molestation</option>')
														.append(
																'<option value="3">Dowry</option>')
														.append(
																'<option value="4">Prodtitution</option>')
														.append(
																'<option value="5">Child Marriage</option>')
														.append(
																'<option value="6">Others</option>')

											} else if (selected === "3") {

												//alert("Select 3");

												$(selector)
														.find('option')
														.remove()
														.end()
														.append(
																'<option value="1">Hacking</option>')
														.append(
																'<option value="2">Spam</option>')
														.append(
																'<option value="2">Email Fraud</option>')
														.append(
																'<option value="2">SMS Forging</option>')
														.append(
																'<option value="2">Others</option>')

											} else if (selected === "4") {

												//alert("Select 4");

												$(selector)
														.find('option')
														.remove()
														.end()
														.append(
																'<option value="1">Hit Run</option>')
														.append(
																'<option value="2">Reckless Driving</option>')
														.append(
																'<option value="3">Speeding</option>')
														.append(
																'<option value="4">Driving Drunk</option>')
														.append(
																'<option value="5">Illegal License</option>')
														.append(
																'<option value="6">Others</option>')

											} else {

												//alert("CBI");
												$(selector).find('option')
														.remove().end()
											}

										});

						$('#send')
								.click(
										function(e) {

											//alert("A");
											//alert(ref);
											e.preventDefault(); // prevent the browser from following the link
											e.stopPropagation(); // prevent the browser from following the link
											var data_content = $(
													"form#complainForm")
													.serialize();
											//alert("data"+data_content);
											data_content += "&ref=" + ref;
											var id = $("#send").attr(
													"data-content");
											alert(id);
											alert(data_content);
											
											$.ajax({
														url : "./includes/submit/submit_complain_new_pending.jsp",
														cache : false,
														data : data_content,
														success : function(data) {
																alert("success"+ data);
															$("#" + id)
																	.html(
																			"DONE !!!")
																	.css(
																			"color",
																			"rgb(51, 255, 0)");
															$("#customPopup")
																	.hide();
															$("#backShadow")
																	.hide();

														},
														error: function(data){
															alert("err");
														}
													});
											//alert(data_content);

										});

						$('#cancel').click(
								function(e) {

									//alert("A");

									e.preventDefault(); // prevent the browser from following the link
									e.stopPropagation(); // prevent the browser from following the link
									var data_content = $("form#complainForm")
											.serialize();
									var id = $("#cancel").attr("data-content");

									//alert(id);
									//alert(data_content);
									$.ajax({
										url : $(this).attr('href'),
										cache : false,
										data : data_content,
										success : function(data) {
											//$("div#customPopup").html(data);
											alert("success");
											$("#" + id).html("REJECTD ").css(
													"color", "RED");
											$("#customPopup").hide();
											$("#backShadow").hide();

										}
									});
									//alert(data_content);

								});

					});
</script>
</head>




<div id="showComplain">
	<h1>Complain Details</h1>
	<form method="post" class="form" id="complainForm" action="">
		<label>( * ) are mandatory</label>


		<div>
			<input id="complainid" name="complainid" type="hidden"
				value='<%=complain_id%>' />
		</div>

		<div>
			<label>Citizen Name * :</label> <input id="citizenname"
				name="citizenname" type="text" value='<%=citizen_name%>' /><span
				id="citizennameInfo"></span>
		</div>
		<div>
			<label>Complaint Subject * :</label> <input id="complaintSub"
				name="complaintSub" type="text" value='<%=complain_subject%>' /><span
				id="complaintSubInfo"></span>
		</div>

		<div>
			<label>Complaint Description * :</label>
			<textarea id="complaintDesc" name="complaintDesc"><%=complain_content%>
		</textarea>
			<span id="complaintDescInfo"></span>
		</div>

		<div>
			<label>Department Type * :</label> <select name="dept" id="dept">
				<option value="0">Department *</option>
				<option value="1"
					<%if (dept_id == 1)
					out.println("selected");%>>Law
					and Order</option>
				<option value="2"
					<%if (dept_id == 2)
					out.println("selected");%>>Women
					Protection</option>
				<option value="3"
					<%if (dept_id == 3)
					out.println("selected");%>>Cybercrime</option>
				<option value="4"
					<%if (dept_id == 5)
					out.println("selected");%>>Traffic
					and control</option>
				<option value="5"
					<%if (dept_id == 6)
					out.println("selected");%>>CBI</option>
			</select><br /> <span id="deptInfo"></span>
		</div>

		<%
			if (request.getParameter("ref").toString().equals("new")) {
		%>
		<div>
			<label>Sub-Department Type * :</label> <select name="subDept"
				id="subDept">
				<option value="0">Sub-Department *</option>

			</select><br /> <span id="subDeptInfo"></span>
		</div>
		<%
			}
		%>


		<div>
			<label>Assigned Police Officer * :</label> <select
				name="assignedPolice" id="assignedPolice">
				<option value="0">Police List *</option>
				<%
					for (int i = 0; i < jArray.length(); i++) {
				%>
				<option
					value="<%=new JSONObject(jArray.get(i).toString())
							.getInt("officer_id")%>"
					<%if (police_id == new JSONObject(jArray.get(i).toString())
							.getInt("officer_id"))
						out.println("selected");%>><%=new JSONObject(jArray.get(i).toString())
							.getString("officer_name")%></option>
				<%
					}
				%>

			</select><br /> <span id="subDeptInfo"></span>
		</div>

		<!-- <div>
			<label>Virtual Police Station * :</label> <select name="vps_id"
				id="vps_id">
				<option value="0">VPS *</option>
				<option value="1">VPS list</option>

			</select><br /> <span id="vpsInfo"></span>
		</div> -->




		<div>
			<input id="send" name="send" type="submit" value="Update"
				data-content="<%=id%>" /> <input id="cancel" name="cancel"
				type="button" value="Reject" data-content="<%=id%>" />
		</div>

	</form>






</div>
<%
	} else
		response.sendRedirect("./../");
%>