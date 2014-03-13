$(document).ready(function() {
	$("#picForm #send").click(function(e) {
		alert("hello");
		e.preventDefault(); // prevent the browser from following the link
		e.stopPropagation(); // prevent the browser from following the link
		var data_content = $("form#picForm").serialize();
		var val = $("#picForm #file").val();
		if ($("#picForm #file").val().length == 0) {
			alert("Select File");
			return false;
		}
		$("#preview").html('');
		$("#preview").html('<p><img src="./media/loader.gif" alt="Uploading...." width="50px" height="20px"/></p>');
		$("#picForm").ajaxForm({
			success : function(data){
				alert(data);
				var filename= data.split("=");
				filename = "./../../upload"+filename[1];
				alert(filename);
				$("input#pid").val(filename);
				$.ajax({
					url:"./site/submit/submit_police_id.jsp",
					type:"GET",
					data:"url="+filename,
					success: function(data)
					{    
						$("#preview").html("Uploaded");
						
					},
					error : function()
					{alert(err);}
				});
			}
		}).submit();
		
		
		
		

	});

	$("#createpoliceForm #send").click(function(e) {
		alert("hello");
		e.preventDefault(); // prevent the browser from following the link
		e.stopPropagation(); // prevent the browser from following the link
		var data_content = $("form#createpoliceForm").serialize();
		if ($("input#name").val() == '') {
			alert("Enter the Name...first")
			return false;
		}
		if ($("input#email_id").val() == '') {
			alert("Enter the emailid first")
			return false;
		}
		if ($("input#dob").val() == '') {
			alert("Enter DOB first")
			return false;
		}
		if ($("#gender").val() == 1) {
			alert("Select Gender first ..")
			return false;
		}
		if ($("input#pincode").val() == '') {
			alert("Enter Pincode first")
			return false;
		}
		if ($("input#address_block").val() == '') {
			alert("Enter Address Block first")
			return false;
		}
		if ($("input#mobile").val() == '') {
			alert("Enter Mobile no. first")
			return false;
		}
		if ($("input#pid").val() == '') {
			alert("Upload Id first")

			return false;
		}
		alert(data_content);
		$.ajax({
			url : "./includes/submit/submit_police.jsp",
			cache : false,
			data : data_content,
			success : function(data) {
				// $("div#customPopup").html(data);
				alert("success" + data);

			},
			error : function() {

				alert("err");
			}

		});

	});

});
