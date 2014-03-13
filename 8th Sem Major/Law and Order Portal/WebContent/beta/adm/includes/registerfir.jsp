<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.controller.CitizenController"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	if (session.getAttribute("pid") != null
			&& !session.getAttribute("pid").toString().isEmpty()) {
%>
<%
	int complain_id = 0, citizen_id = 0, sub_dept_id = 0;

		String complain_content = null, complain_subject = null, citizen_name = null, gender = null, identification_copy = null, pincode = null, address = null;

		complain_id = Integer.valueOf(request
				.getParameter("complaintId"));
		citizen_id = Integer.valueOf(request.getParameter("citizenId"));
%>
<%
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

				out.println(sub_dept_id);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
%>




<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" href="./includes/css/form.css" type="text/css"
	media="screen" />
<script type="text/javascript"
	src="./includes/js/complaint_validation.js"></script>



<script type="text/javascript">
	$(document).ready(function() {

		$('#registerfirForm #cancel').click(function(e) {

			alert("A");

			e.preventDefault(); // prevent the browser from following the link
			e.stopPropagation(); // prevent the browser from following the link
			var data_content = $("form#registerfirForm").serialize();
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
			//alert(data_content);

		});

	});
</script>

<script type="text/javascript">
	var aid = 0;
	//FUNCTION TO ADD TEXT BOX ELEMENT
	function addAElement() {
		aid = aid + 1;
		var contentID = document.getElementById('add-accused');
		var newTBDiv = document.createElement('div');
		newTBDiv.setAttribute('id', 'aid' + aid);
		newTBDiv.innerHTML = "<hr/><div><label>accused ".concat(aid)
				+ "</label></div>"
				+ "<div><label>accused Full Name * :</label> <input id='accusedname"
						.concat(aid)
				+ "' name='accusedname".concat(aid)
				+ "' type='text'  /> <span	id='accusednameInfo".concat(aid)
				+ "'></span></div>"
				+ "<div><label>Gender * :</label> <select name='accusedgender"
						.concat(aid)
				+ "'	id='accusedgender".concat(aid)
				+ "'>	<option value='1'>Gender *</option>	<option value='2'>Male</option>	<option value='3'>Female</option></select> <span id='accusedgenderInfo"
						.concat(aid)
				+ "'></span></div>"
				+ "<div><label>Pincode * :</label> <input id='accusedpincode"
						.concat(aid)
				+ "'	name='accusedpincode".concat(aid)
				+ "' type='text' /> <span	id='accusedpincodeInfo".concat(aid)
				+ "'></span></div>"
				+ "<div><label>Mobile * :</label> <input id='accusedmobile"
						.concat(aid)
				+ "'	name='accusedmobile".concat(aid)
				+ "' type='text' /> <span	id='accusedmobileInfo".concat(aid)
				+ "'></span></div>"
				+ "<div><label>Address * :</label> <input id='accusedaddress_block"
						.concat(aid)
				+ "'	name='accusedaddress_block".concat(aid)
				+ "' type='text'	 /> <span	id='accusedaddress_blockInfo"
						.concat(aid) + "'></span></div>";
		contentID.appendChild(newTBDiv);
	}
	//FUNCTION TO REMOVE TEXT BOX ELEMENT
	function removeAElement() {
		if (aid != 0) {
			var contentID = document.getElementById('add-accused');
			contentID.removeChild(document.getElementById('aid' + aid));
			aid = aid - 1;
		} else {

			alert("No Accused Added !!!");
		}
	}
</script>
<script type="text/javascript">
	var wid = 0;
	//FUNCTION TO ADD TEXT BOX ELEMENT
	function addWElement() {
		wid = wid + 1;
		var contentID = document.getElementById('add-witness');
		var newTBDiv = document.createElement('div');
		newTBDiv.setAttribute('id', 'wid' + wid);
		newTBDiv.innerHTML = "<hr/><div><label>witness ".concat(wid)
				+ "</label></div>"
				+ "<div><label>witness Full Name * :</label> <input id='witnessname"
						.concat(wid)
				+ "' name='witnessname".concat(wid)
				+ "' type='text'  /> <span	id='witnessnameInfo".concat(wid)
				+ "'></span></div>"
				+ "<div><label>Gender * :</label> <select name='witnessgender"
						.concat(wid)
				+ "'	id='witnessgender".concat(wid)
				+ "'>	<option value='1'>Gender *</option>	<option value='2'>Male</option>	<option value='3'>Female</option></select> <span id='witnessgenderInfo"
						.concat(wid)
				+ "'></span></div>"
				+ "<div><label>Pincode * :</label> <input id='witnesspincode"
						.concat(wid)
				+ "'	name='witnesspincode".concat(wid)
				+ "' type='text' /> <span	id='witnesspincodeInfo".concat(wid)
				+ "'></span></div>"
				+ "<div><label>Mobile * :</label> <input id='witnessmobile"
						.concat(wid)
				+ "'	name='witnessmobile".concat(wid)
				+ "' type='text' /> <span	id='witnessmobileInfo".concat(wid)
				+ "'></span></div>"
				+ "<div><label>Address * :</label> <input id='witnessaddress_block"
						.concat(wid)
				+ "'	name='witnessaddress_block".concat(wid)
				+ "' type='text'	 /> <span	id='witnessaddress_blockInfo"
						.concat(wid) + "'></span></div>";
		contentID.appendChild(newTBDiv);
	}
	//FUNCTION TO REMOVE TEXT BOX ELEMENT
	function removeWElement() {
		if (wid != 0) {
			var contentID = document.getElementById('add-witness');
			contentID.removeChild(document.getElementById('wid' + wid));
			wid = wid - 1;
		} else {

			alert("No Witnesses Added !!!");
		}
	}
</script>
<script>
	function test() {
		alert(aid + "Hello   " + wid);
	}
</script>



<style>
#registerfirForm {
	
}

#add-accused hr {
	border-color: red;
}

#add-witness hr {
	border-color: green;
}

p a {
	color: blue;
}

p {
	text-align: center;
	font-size: 15px;
	padding: 2px;
	border: 1px solid gray;
}
</style>
<%
	out.println(request.getParameter("complaintId"));
		String id = request.getParameter("id");
%>

</head>

<body>

	<center>
		<h1>Register FIR</h1>
		<label>( * ) are mandatory</label>
	</center>

	<form method="post" class="form" id="registerfirForm" action="">

		<div>
			<label>Complainer</label>
		</div>

		<div>
			<input id="complainid" name="complainid" type="hidden"
				value='<%=complain_id%>' />
		</div>
		<div>
			<input id="sub_dept_id" name="sub_dept_id" type="hidden"
				value='<%=sub_dept_id%>' />
		</div>

		<div>
			<label>Full Name * :</label> <input id="name" name="name" type="text"
				value='<%=citizen_name%>' /> <span id="nameInfo"></span>
		</div>


		<div>
			<label>Gender * :</label> <select name="gender" id="gender">
				<option value="1">Gender *</option>
				<option value="2">Male</option>
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
			<textarea id="complainDesc" name="complainDesc"><%=complain_content%>
		</textarea>
			<span id="complainDescInfo"></span>
		</div>

		<div>
			<label>Pincode * :</label> <input id="pincode" name="pincode"
				type="text" value='<%=pincode%>' /> <span id="pincodeInfo"></span>
		</div>



		<div>
			<label>Address * :</label> <input id="address_block"
				name="address_block" type="text" value='<%=address%>' /> <span
				id="address_blockInfo"></span>
		</div>

		<div>
			<label>Identification Copy* :</label> <input id="file" name="file"
				type="text" value='<%=identification_copy%>' /> <span id="fileInfo"></span>
		</div>

		<div id="add-accused">
			<%-- <div>
				<label>Accused 1</label>
			</div>



			<div>
				<label>Accused Full Name * :</label> <input id="accusedname"
					name="accusedname" type="text" value='' /> <span
					id="accusednameInfo"></span>
			</div>


			<div>
				<label>Gender * :</label> <select name="accusedgender"
					id="accusedgender">
					<option value="1">Gender *</option>
					<option value="2">Male</option>
					<option value="3">Female</option>
				</select> <span id="accusedgenderInfo"></span>
			</div>



			<div>
				<label>Pincode * :</label> <input id="accusedpincode"
					name="accusedpincode" type="text" value='<%=accusedpincode%>' /> <span
					id="accusedpincodeInfo"></span>
			</div>



			<div>
				<label>Address * :</label> <input id="accusedaddress_block"
					name="accusedaddress_block" type="text"
					value='<%=accusedaddress_block%>' /> <span
					id="accusedaddress_blockInfo"></span>
			</div> --%>


		</div>

		<p>
			<a href="javascript:addAElement();">Add Accused </a> | <a
				href="javascript:removeAElement();"> Remove Accused</a>
		</p>




		<div>
			<label>Crime Location</label>
		</div>


		<div>
			<label>Crime Address * :</label> <input id="crimeaddress"
				name="crimeaddress" type="text" value='' /> <span
				id="crimeaddressInfo"></span>
		</div>




		<div>
			<label>Pincode * :</label> <input id="crimepincode"
				name="crimepincode" type="text" value='' /> <span
				id="crimepincodeInfo"></span>
		</div>



		<div>
			<label>State * :</label> <input id="crimestate" name="crimestate"
				type="text" value='' /> <span id="crimestateInfo"></span>
		</div>

		<div>
			<label>Crime Time * :</label> <input id="crimetime" name="crimetime"
				type="time" value='' /> <span id="crimetimeInfo"></span>
		</div>


		<div id="add-witness">
			<%-- <div>
				<label>Witness Info</label>
			</div>


			<div>
				<label>Witness Name * :</label> <input id="witnessname"
					name="witnessname" type="text" value='<%=witnessname%>' /> <span
					id="witnessnameInfo"></span>
			</div>



			<div>
				<label>Witness Address * :</label> <input id="witnessaddress"
					name="witnessaddress" type="text" value='<%=witnessaddress%>' /> <span
					id="witnessaddressInfo"></span>
			</div>




			<div>
				<label>Witness Pincode * :</label> <input id="witnesspincode"
					name="witnesspincode" type="text" value='<%=witnesspincode%>' /> <span
					id="witnessInfo"></span>
			</div>



			<div>
				<label>Witness State * :</label> <input id="witnessstate"
					name="witnessstate" type="text" value='<%=witnessstate%>' /> <span
					id="witnessstateInfo"></span>
			</div>
 --%>

		</div>
		<p>
			<a href="javascript:addWElement();">Add Witness </a> | <a
				href="javascript:removeWElement();"> Remove Witness</a>
		</p>



		<br />


		<div>
			<input id="send" name="send" type="submit" value="Create FIR"
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
