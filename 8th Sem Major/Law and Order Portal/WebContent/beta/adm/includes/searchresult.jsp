<%@page import="java.util.ArrayList"%>
<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page import="javax.ws.rs.core.Response"%>
<%@page import="com.law.order.controller.CriminalController"%>
<%
	if (session.getAttribute("pid") != null
		&& !session.getAttribute("pid").toString().isEmpty()) {
%>
<script>
	$(document).ready(function() {
		$('a.custompopup').click(function(e) {
			e.preventDefault(); // prevent the browser from following the link
			e.stopPropagation(); // prevent the browser from following the link
			// $( '#load' ).load( $( this ).attr( 'href' ));

			var data_content = $(this).attr('data-content');
			//alert(data);

			$("#customPopup").show();
			$("#backShadow").show();
			$.ajax({
				url : $(this).attr('href'),
				cache : false,
				data : $(this).attr('data-content'),
				success : function(data) {
					$("div#customPopup").html(data);
				}
			});

		});

		$("#backShadow img").click(function() {
			$("#customPopup").hide();
			$("#backShadow").hide();
		});

	});
</script>
<%
	String searchKey = request.getParameter("searchKey");
		String filter = request.getParameter("filter");
		out.println(request.getParameter("searchKey"));
		out.println(request.getParameter("filter"));

		ArrayList<String> criminal_name = new ArrayList<String>();
		ArrayList<String> height = new ArrayList<String>();
		ArrayList<String> weight = new ArrayList<String>();
		ArrayList<String> criminal_id = new ArrayList<String>();

		CriminalController crime = new CriminalController();
		Response resp = crime.retrieveCriminal(filter, searchKey);
		JSONArray jArray = null;
		jArray = new JSONArray(resp.getEntity().toString());

		for (int i = 0; i < jArray.length(); i++) {

			criminal_name.add(jArray.getJSONObject(i).getString(
					"criminal_name"));
			height.add(jArray.getJSONObject(i).getString("height"));

			weight.add(jArray.getJSONObject(i).getString("weight"));

			criminal_id.add(jArray.getJSONObject(i).getString(
					"criminal_id"));

		}
%>



<hr />
<h1>Search Result</h1>

<table>
	<tr>
		<th>S.No.</th>
		<th>Criminal Name</th>
		<th>Height</th>
		<th>Weight</th>
		<th>Check Data</th>
	</tr>
	<%
		for (int i = 0; i < criminal_id.size(); i++) {
	%>
	<tr>
		<td><%=i + 1%></td>
		<td><%=criminal_name.get(i)%></td>
		<td><%=height.get(i)%></td>
		<td><%=weight.get(i)%></td>
		<td><div class="buttonType ">
				<a href="./includes/showdata.jsp"
					data-content="criminalid=<%=criminal_id.get(i)%>&id=id<%=i%>&ref=search"
					id="id<%=i%>" class="custompopup">check Data</a>
			</div>
		</td>
	</tr>
	<%
		}
	%>



</table>
<%
	} else
		response.sendRedirect("./../");
%>
