<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	if (session.getAttribute("pid") != null
			&& !session.getAttribute("pid").toString().isEmpty()) {
%>
<head>
<link rel="stylesheet" href="./includes/css/form.css" type="text/css"
	media="screen" />

<script type="text/javascript">
	$(document).ready(function() {

		$('a.custompopup').click(function(e) {
			e.preventDefault(); // prevent the browser from following the link
			e.stopPropagation(); // prevent the browser from following the link
			// $( '#load' ).load( $( this ).attr( 'href' ));

			//var data-content = $(this).attr('data-content');
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

		$("#searchForm #send").click(function(e) {

			alert("A");
			e.preventDefault(); // prevent the browser from following the link
			e.stopPropagation(); // prevent the browser from following the link
			var data_content = $("form#searchForm").serialize();

			alert(data_content);
			$.ajax({
				url : "./includes/searchresult.jsp",
				cache : false,
				data : data_content,
				success : function(data) {
					alert("jkjk");
					//$("#serachResult").show();
					$("div#serachResult").html(data);
					$("#notify").hide();

				}
			});

		});

	});
</script>


<style>
#searchForm label {
	display: inline;
}
</style>
</head>
<body>
	<h1>Search</h1>
	<form method="post" id="searchForm" class="form" action="">

		<div>
			<label>Search Element * :</label> <input id="searchKey"
				name="searchKey" type="text" value='' /><span id="searchKeyInfo"></span>

			<label>Filter * :</label> <select name="filter" id="filter">
				<option value="height">HEIGHT</option>
				<option value="weight">WEIGHT</option>
				<option value="age">AGE</option>
				<option value="complexion">COMPLEXION</option>
				<option value="identification_marks">IDENTIFICATION MARKS</option>
			</select> <span id="filterInfo"></span> <input id="send" name="send"
				type="submit" value="Search" style="margin-left: 10px" />


		</div>



	</form>

	<%-- <jsp:include page="./searchresult.jsp" /> --%>
	<hr />


	<div id="serachResult" class="table">
		<center>
			<h2 id="notify">Enter the Search Element To Search</h2>
		</center>
	</div>
<body></body>
<%
	} else
		response.sendRedirect("./../");
%>