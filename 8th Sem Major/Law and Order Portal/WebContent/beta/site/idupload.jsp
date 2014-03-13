<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<link rel="stylesheet" href="./includes/css/general.css" type="text/css"
	media="screen" />

<script type="text/javascript" src="./../includes/js/jquery.js"></script>
<script type="text/javascript" src="./../includes/js/jquery.form.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#photoimg').bind('change', function() {
			$("#preview").html('');
			$("#preview").html('<p><img src="./../includes/image/loader.gif" alt="Uploading...."/></p>');
			$("#imageform").ajaxForm({
				target : '#preview',
				success : function(data)
				{
					alert(data);
					var filename= data.split("=");
					filename = "./../upload"+filename[1];
					alert(filename);
					
					
					$.ajax({
						url : "./submit/submit_idupload.jsp" ,
					 type : 'GET',
					 data : "url="+filename,
					 success : function(data)
					 {   alert(data);
					 
					      window.location="./../";
					 },
					 error : function()
					 {alert("err");}
					});
					
					
					
				}
			}).submit();
		});
	});
</script>

</head>
<body>
	<h1>Upload ID Copy</h1>

	<form action="./../../v1/upload/file" method="post"
		enctype="multipart/form-data" id="imageform">

		<p>
			Select a file : <input type="file" name="uploadedFile" size="50"
				id="photoimg" />
		</p>
		<!--  <p>
       The Next file : <input type="file" name="uploadedFile2" size="50" />
       </p> -->

		<!-- <input type="submit" value="Upload It" /> -->
	</form>
	<div id='preview'>Here</div>

</body>
</html>