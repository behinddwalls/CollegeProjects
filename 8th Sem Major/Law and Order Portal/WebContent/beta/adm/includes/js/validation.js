/*validation.js*/

$(document)
		.ready(
				function() {
					// global vars
					var form = $("#profileForm");
					var passForm = $("#passForm");
					var picForm = $("#picForm");
					var pic = $("#pic");
					var mobile = $("#mobile");
					var mobileInfo = $("#mobileInfo");

					var name = $("#name");
					var nameInfo = $("#nameInfo");
					var email_id = $("#email_id");
					var email_idInfo = $("#email_idInfo");
					var dob = $("#dob");
					var dobInfo = $("#dobInfo");
					var pass1 = $("#pass1");
					var pass1Info = $("#pass1Info");
					var pass2 = $("#pass2");
					var pass2Info = $("#pass2Info");
					var gender = $("#gender");
					var genderInfo = $("#genderInfo");
					var address_block = $("#address_block");
					var address_blockInfo = $("#address_blockInfo");
					var pincode = $("#pincode");
					var pincodeInfo = $("#pincodeInfo");
					var file = $("#file");
					var fileInfo = $("#fileInfo");

					// On blur
					name.blur(validateFName);
					email_id.blur(validateEmail);
					dob.blur(validateDOB);
					gender.blur(validateGender);
					address_block.blur(validateAddress_block);
					pincode.blur(validatepincode);
					file.blur(validateFile);
					pass1.blur(validatePass1);
					pass2.blur(validatePass2);
					mobile.blur(validateMobile);
					pic.blur(validatePic);

					// On key press
					name.keyup(validateFName);
					email_id.keyup(validateEmail);
					dob.keyup(validateDOB);
					gender.keyup(validateGender);
					address_block.keyup(validateAddress_block);
					pincode.keyup(validatepincode);
					file.keyup(validateFile());
					pass1.keyup(validatePass1);
					pass2.keyup(validatePass2);
					pic.keyup(validatePic);
					mobile.keyup(validateMobile);

					//
					file.change(validateFile);
					pic.change(validatePic);

					// On Submitting
					form.submit(function(e) {

						if (validateFName() & validateEmail()
								& validateGender() & validateAddress_block()
								& validatepincode() & validateMobile()) {
							alert("A");

							e.preventDefault(); // prevent the browser from
							// following the link
							e.stopPropagation(); // prevent the browser from
							// following the link
							var data_content = $("form#profileForm")
									.serialize();
							alert(data_content);

							$.ajax({
								url : "./includes/submit/submit_update_police.jsp",
								cache : false,
								data : data_content,
								success : function(data) { //
									$("div#customPopup").html(data);
									alert("success"+data);
									$("div#load")
											.load("./includes/profile.jsp");
								},
								error : function(data) {
									alert("error");
								}
							});

							return true;
						} else
							return false;
					});

					$("#picForm #send").click(function(e) {
						e.preventDefault(); // prevent the browser from
						// following the link
						e.stopPropagation();
						if (validatePic()) {
							alert("hello");
						
							$("#preview").html('');
							$("#preview").html('<p><img src="./media/loader.gif" alt="Uploading...." width="50px" height="20px"/></p>');
							$("#picForm").ajaxForm({
								success : function(data){
									alert(data);
									var filename= data.split("=");
									filename = "./../../upload"+filename[1];
									alert(filename);
									$("#img").html('<img src="'+filename +'" id="picImg" />');
									$.ajax({
										url:"./includes/submit/submit_profile_pic.jsp",
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
							return true;
						} else
							return false;

					});
					passForm.submit(function(e) {
						if (validatePass1() & validatePass2()) {
							alert("Pas");

							e.preventDefault(); // prevent the browser from
							// following the link
							e.stopPropagation(); // prevent the browser from
							// following the link
							var data_content = $("form#passForm").serialize();
							alert(data_content);
							$.ajax({
								url :"./includes/submit/submit_change_password.jsp",
								cache : false,
								data : data_content,
								success : function(data) {
									// $("div#customPopup").html(data);
									alert("Password Changed - " +data);
									$("div#load")
											.load("./includes/profile.jsp");

								},
								error : function(data) {
									alert("error");
								}
							});
							return true;
						} else
							return false;
					});

					// validation functions
					function validateEmail() {
						// testing regular expression
						var a = $("#email_id").val();
						var filter = /^[a-zA-Z0-9]+[a-zA-Z0-9_.-]+[a-zA-Z0-9_-]+@[a-zA-Z0-9]+[a-zA-Z0-9.-]+[a-zA-Z0-9]+.[a-z]{2,4}$/;
						// if it's valid email
						if (filter.test(a)) {
							email_id.removeClass("error");
							email_idInfo.text("");
							email_idInfo.removeClass("error");
							return true;
						}
						// if it's NOT valid
						else {
							email_id.addClass("error");
							email_idInfo
									.text("This email_id is invalid , Plz type a valid Email Id");
							email_idInfo.addClass("error");
							return false;
						}
					}
					function validateFName() {
						// if it's NOT valid
						if (name.val().length < 4) {
							name.addClass("error");
							nameInfo
									.text("We want names with more than 3 letters!");
							nameInfo.addClass("error");
							return false;
						}
						// if it's valid
						else {
							name.removeClass("error");
							nameInfo.text("");
							nameInfo.removeClass("error");
							return true;
						}
					}

					function validateDOB() {
						if (dob.val().length < 10) {
							dob.addClass("error");
							dobInfo
									.text("Enter Date of Birth in proper format (dd/mm/yyyy)");
							dobInfo.addClass("error");
							return false;
						} else {
							dob.removeClass("error");
							dobInfo.text("");
							dobInfo.removeClass("error");
							return true;
						}

					}

					function validateGender() {
						// not selected
						if (gender.val() == 1) {
							gender.addClass("error");
							genderInfo.text("You Missed ur Gener  ! Select it");
							genderInfo.addClass("error");
							return false;
						}
						// selected
						else {
							gender.removeClass("error");
							genderInfo.text("");
							genderInfo.removeClass("error");
							return true;
						}
					}
					function validateAddress_block() {
						// it's NOT valid
						if (address_block.val().length < 1) {
							address_block.addClass("error");
							address_blockInfo.text("Can not be Empty !!!");
							address_blockInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							address_block.removeClass("error");
							address_blockInfo.text("");
							address_blockInfo.removeClass("error");
							return true;
						}
					}

					function validatepincode() {
						// it's NOT valid
						if (pincode.val().length < 5) {
							pincode.addClass("error");
							pincodeInfo.text("Enter Digits Only");
							pincodeInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							pincode.removeClass("error");
							pincodeInfo.text("");
							pincodeInfo.removeClass("error");
							return true;
						}
					}

					function validateFile() {
						// it's NOT valid
						if (file.val() == '') {
							file.addClass("error");
							fileInfo.text("Attach Id Copy");
							fileInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							file.removeClass("error");
							fileInfo.text("");
							fileInfo.removeClass("error");
							return true;
						}
					}

					function validatePass1() {

						// it's NOT valid
						if (pass1.val().length < 5) {
							pass1.addClass("error");
							pass1Info
									.text("For length 5 ,use characters, letters, numbers and '_'");
							pass1Info.addClass("error");
							return false;
						}
						// it's valid
						else {
							pass1.removeClass("error");
							pass1Info
									.text("For length 5 ,use characters, letters, numbers and '_'");
							pass1Info.removeClass("error");
							validatePass2();
							return true;
						}
					}
					function validatePass2() {

						// are NOT valid
						if (pass1.val() != pass2.val()) {
							pass2.addClass("error");
							pass2Info.text("Passwords doesn't match!");
							pass2Info.addClass("error");
							return false;
						}
						// are valid
						else {
							pass2.removeClass("error");
							pass2Info.text("");
							pass2Info.removeClass("error");
							return true;
						}
					}
					function validatePic() {
						// it's NOT valid
						if (pic.val() == '') {
							pic.addClass("error");

							return false;
						}
						// it's valid
						else {
							pic.removeClass("error");

							return true;
						}
					}

					function validateMobile() {
						// it's NOT valid
						if (mobile.val().length < 10) {
							mobile.addClass("error");
							mobileInfo.text("Length 10 only");
							mobileInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							mobile.removeClass("error");
							mobileInfo.text("");
							mobileInfo.removeClass("error");

							return true;
						}
					}
				});