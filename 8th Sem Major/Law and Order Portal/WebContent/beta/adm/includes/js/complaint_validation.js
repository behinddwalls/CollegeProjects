/*validation.js*/

$(document)
		.ready(
				function() {
					// global vars
					var form = $("#registerfirForm");
					var chargesheetForm = $("#chargesheetForm");
					var name = $("#name");
					var nameInfo = $("#nameInfo");
					var accusedname = $("#accusedname");
					var accusednameInfo = $("#accusednameInfo");
					var dob = $("#dob");
					var dobInfo = $("#dobInfo");
					var gender = $("#gender");
					var genderInfo = $("#genderInfo");
					var accusedgender = $("#accusedgender");
					var accusedgenderInfo = $("#accusedgenderInfo");
					var address_block = $("#address_block");
					var address_blockInfo = $("#address_blockInfo");

					var complainSub = $("#complainSub");
					var complainSubInfo = $("#complainSubInfo");
					var complainDesc = $("#complainDesc");
					var complainDescInfo = $("#complainDescInfo");

					var accusedaddress_block = $("#accusedaddress_block");
					var accusedaddress_blockInfo = $("#accusedaddress_blockInfo");
					var pincode = $("#pincode");
					var pincodeInfo = $("#pincodeInfo");
					var accusedpincode = $("#accusedpincode");
					var accusedpincodeInfo = $("#accusedpincodeInfo");
					var file = $("#file");
					var fileInfo = $("#fileInfo");
					var crimeaddress = $("#crimeaddress");
					var crimeaddressInfo = $("#crimeaddressInfo");
					var crimepincode = $("#crimepincode");
					var crimepincodeInfo = $("#crimepincodeInfo");
					var crimestate = $("#crimestate");
					var crimestateInfo = $("#crimestateInfo");

					var witnessname = $("#witnessname");
					var witnessnameInfo = $("#witnessnameInfo");
					var witnessaddress = $("#witnessaddress");
					var witnessaddressInfo = $("#witnessaddressInfo");
					var witnesspincode = $("#witnesspincode");
					var witnesspincodeInfo = $("#witnesspincodeInfo");
					var witnessstate = $("#witnessstate");
					var witnessstateInfo = $("#witnessstateInfo");
					var crimetime = $("#crimetime");

					// On blur
					name.blur(validateFName);
					accusedname.blur(validateAccusedFName);
					dob.blur(validateDOB);
					gender.blur(validateGender);
					accusedgender.blur(validateAccusedGender);
					address_block.blur(validateAddress_block);
					complainSub.blur(validatecomplainSub);
					complainDesc.blur(validatecomplainDesc);
					pincode.blur(validatepincode);
					accusedaddress_block.blur(validateAccusedAddress_block);
					accusedpincode.blur(validateAccusedpincode);
					file.blur(validateFile);
					crimeaddress.blur(validateCrimeAddress);
					crimepincode.blur(validateCrimePincode);
					crimestate.blur(validateCrimeState);
					witnessname.blur(validateWitnessName);
					witnessaddress.blur(validateWitnessAddress);
					witnessstate.blur(validateWitnessState);
					witnesspincode.blur(validateWitnessPincode);
					crimetime.blur(validateCrimeTime);

					// On key press
					name.keyup(validateFName);
					accusedname.keyup(validateAccusedFName);
					dob.keyup(validateDOB);
					gender.keyup(validateGender);
					accusedgender.keyup(validateAccusedGender);
					address_block.keyup(validateAddress_block);
					complainSub.keyup(validatecomplainSub);
					complainDesc.keyup(validatecomplainDesc);
					pincode.keyup(validatepincode);
					accusedaddress_block.keyup(validateAccusedAddress_block);
					accusedpincode.keyup(validateAccusedpincode);
					file.keyup(validateFile);
					crimeaddress.keyup(validateCrimeAddress);
					crimepincode.keyup(validateCrimePincode);
					crimestate.keyup(validateCrimeState);
					witnessname.keyup(validateWitnessName);
					witnessaddress.keyup(validateWitnessAddress);
					witnessstate.keyup(validateWitnessState);
					witnesspincode.keyup(validateWitnessPincode);
					crimetime.keyup(validateCrimeTime);

					//

					// On Submitting
					form
							.submit(function(e) {
								if (validateFName() & validateGender()
										& validatepincode()
										& validateAddress_block()
										& validateFile()/*
														 * &
														 * validateAccusedFName() &
														 * validateAccusedGender() &
														 * validateAccusedpincode() &
														 * validateAccusedAddress_block()
														 */
										& validatecomplainSub()
										& validateCrimeAddress()
										& validateCrimePincode()
										& validateCrimeState()
										/*
										 * & validateWitnessName() &
										 * validateWitnessAddress() &
										 * validateWitnessPincode() &
										 * validateWitnessState()
										 */& validatecomplainDesc()) {
									alert("kjkjk");
									send(
											e,
											"./includes/submit/submit_register_fir.jsp",
											"form#registerfirForm", aid, wid);
									e.preventDefault(); // prevent the browser
									// from following the
									// link
									e.stopPropagation();
									return true;
								} else {
									alert("fff");
									return false;
								}

							});

					function send(e, url, form_data) {

						alert("A");

						e.preventDefault(); // prevent the browser from
						// following the link
						e.stopPropagation(); // prevent the browser from
						// following the link
						var data_content = $(form_data).serialize();
						data_content = data_content + "&aid=" + aid + "&wid="
								+ wid;
						var id = $("#send").attr("data-content");
						alert(aid + "  " + wid);
						alert(id);
						alert(data_content);
						$.ajax({
							url : url,
							cache : false,
							data : data_content,
							success : function(data) {
								// $("div#customPopup").html(data);
								alert("success" + data);
								$("#" + id).html("DONE !!!").css("color",
										"rgb(51, 255, 0)");
								$("#customPopup").hide();
								$("#backShadow").hide();

							},
							error : function() {

								alert("errr");
							}
						});

					}

					chargesheetForm.submit(function(e) {
						alert("kjkjk");
						/*send(e, "./includes/submit/submit_filechargesheet.jsp",
								"form#chargesheetForm");*/
						e.preventDefault(); // prevent the browser
						// from following the
						// link
						e.stopPropagation();
						var id = $("#send").attr("data-content");
						var data_content = $("form#chargesheetForm").serialize();
						$.ajax({
							url : "./includes/submit/submit_filechargesheet.jsp",
							cache : false,
							data : data_content,
							success : function(data) {
								// $("div#customPopup").html(data);
								alert("success" + data);
								$("#" + id).html("DONE !!!").css("color",
										"rgb(51, 255, 0)");
								$("#customPopup").hide();
								$("#backShadow").hide();

							},
							error : function() {

								alert("errr");
							}
						});
						return true;

					});

					/*
					 * $('#registerfirForm #cancel').click( function(e) {
					 * 
					 * alert("A");
					 * 
					 * e.preventDefault(); // prevent the browser from following
					 * the link e.stopPropagation(); // prevent the browser from
					 * following the link var data_content =
					 * $("form#registerfirForm") .serialize(); var id =
					 * $("#cancel").attr("data-content");
					 * 
					 * alert(id); alert(data_content); $.ajax({ url :
					 * $(this).attr('href'), cache : false, data : data_content,
					 * success : function(data) {
					 * //$("div#customPopup").html(data); alert("success");
					 * $("#"+id).html("REJECTD ").css("color","RED");
					 * $("#customPopup").hide(); $("#backShadow").hide(); } });
					 * //alert(data_content);
					 * 
					 * 
					 * });
					 */

					// validation functions
					function validateAccusedFName() {
						// testing regular expression
						if (accusedname.val().length < 4) {
							accusedname.addClass("error");
							accusednameInfo
									.text("We want names with more than 3 letters!");
							accusednameInfo.addClass("error");
							return false;
						}
						// if it's valid
						else {
							accusedname.removeClass("error");
							accusednameInfo.text("");
							accusednameInfo.removeClass("error");
							return true;
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

					function validateAccusedGender() {
						// not selected
						if (accusedgender.val() == 1) {
							accusedgender.addClass("error");
							accusedgenderInfo
									.text("You Missed ur Gener  ! Select it");
							accusedgenderInfo.addClass("error");
							return false;
						}
						// selected
						else {
							accusedgender.removeClass("error");
							accusedgenderInfo.text("");
							accusedgenderInfo.removeClass("error");
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

					function validateAccusedAddress_block() {
						// it's NOT valid
						if (accusedaddress_block.val().length < 1) {
							accusedaddress_block.addClass("error");
							accusedaddress_blockInfo
									.text("Can not be Empty !!!");
							accusedaddress_blockInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							accusedaddress_block.removeClass("error");
							accusedaddress_blockInfo.text("");
							accusedaddress_blockInfo.removeClass("error");
							return true;
						}
					}

					function validateAccusedpincode() {
						// it's NOT valid
						if (accusedpincode.val().length < 5) {
							accusedpincode.addClass("error");
							accusedpincodeInfo.text("Enter Digits Only");
							accusedpincodeInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							accusedpincode.removeClass("error");
							accusedpincodeInfo.text("");
							accusedpincodeInfo.removeClass("error");
							return true;
						}
					}

					function validateFile() {
						// it's NOT valid
						if (file.val() == '') {
							file.addClass("error");
							fileInfo.text("Supply Path");
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
					function validatecomplainSub() {
						// it's NOT valid
						if (complainSub.val().length < 1) {
							complainSub.addClass("error");
							complainSubInfo.text("Can not be Empty !!!");
							complainSubInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							complainSub.removeClass("error");
							complainSubInfo.text("");
							complainSubInfo.removeClass("error");
							return true;
						}
					}

					function validatecomplainDesc() {
						// it's NOT valid
						if (complainDesc.val().length < 1) {
							complainDesc.addClass("error");
							complainDescInfo.text("Can not be Empty !!!");
							complainDescInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							complainDesc.removeClass("error");
							complainDescInfo.text("");
							complainDescInfo.removeClass("error");
							return true;
						}
					}

					function validateCrimeAddress() {
						// it's NOT valid
						if (crimeaddress.val().length < 1) {
							crimeaddress.addClass("error");
							crimeaddressInfo.text("Can not be Empty !!!");
							crimeaddressInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							crimeaddress.removeClass("error");
							crimeaddressInfo.text("");
							crimeaddressInfo.removeClass("error");
							return true;
						}
					}
					function validateCrimePincode() {
						// it's NOT valid
						if (crimepincode.val().length < 1) {
							crimepincode.addClass("error");
							crimepincodeInfo.text("Can not be Empty !!!");
							crimepincodeInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							crimepincode.removeClass("error");
							crimepincodeInfo.text("");
							crimepincodeInfo.removeClass("error");
							return true;
						}
					}

					function validateCrimeState() {
						// it's NOT valid
						if (crimestate.val().length < 1) {
							crimestate.addClass("error");
							crimestateInfo.text("Can not be Empty !!!");
							crimestateInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							crimestate.removeClass("error");
							crimestateInfo.text("");
							crimestateInfo.removeClass("error");
							return true;
						}
					}

					function validateWitnessName() {
						// it's NOT valid
						if (witnessname.val().length < 1) {
							witnessname.addClass("error");
							witnessnameInfo.text("Can not be Empty !!!");
							witnessnameInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							witnessname.removeClass("error");
							witnessnameInfo.text("");
							witnessnameInfo.removeClass("error");
							return true;
						}
					}
					function validateWitnessAddress() {
						// it's NOT valid
						if (witnessaddress.val().length < 1) {
							witnessaddress.addClass("error");
							witnessaddressInfo.text("Can not be Empty !!!");
							witnessaddressInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							witnessaddress.removeClass("error");
							witnessaddressInfo.text("");
							witnessaddressInfo.removeClass("error");
							return true;
						}
					}

					function validateWitnessPincode() {
						// it's NOT valid
						if (witnesspincode.val().length < 1) {
							witnesspincode.addClass("error");
							witnesspincodeInfo.text("Can not be Empty !!!");
							witnesspincodeInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							witnesspincode.removeClass("error");
							witnesspincodeInfo.text("");
							witnesspincodeInfo.removeClass("error");
							return true;
						}
					}
					function validateWitnessState() {
						// it's NOT valid
						if (witnessstate.val().length < 1) {
							witnessstate.addClass("error");
							witnessstateInfo.text("Can not be Empty !!!");
							witnessstateInfo.addClass("error");
							return false;
						}
						// it's valid
						else {
							witnessstate.removeClass("error");
							witnessstateInfo.text("");
							witnessstateInfo.removeClass("error");
							return true;
						}
					}

					function validateCrimeTime() {
						// it's NOT valid
						/*
						 * if (crimetime.val()== "--:--") {
						 * crimetime.addClass("error"); crimetimeInfo.text("Can
						 * not be Empty !!!"); crimetimeInfo.addClass("error");
						 * return false; } // it's valid else {
						 * crimetime.removeClass("error");
						 * crimetimeInfo.text("");
						 * crimetimeInfo.removeClass("error"); return true; }
						 */return false;
					}
				});