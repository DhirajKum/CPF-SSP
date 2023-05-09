<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<h3>
	<b><div id='msg' style="text-align: center; font-weight: bold; color: green"></div>
</h3>
<!-- <hr class="solid"> -->

<div class="container-fluid" style="margin-bottom: 18%;">
	<div class="row profile profile-card">
		<div class="col-md-12">
			<h5 class="main-heading" style="margin-bottom: 2%;">
				<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
				<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
			</svg>
				Change Password
			</h5>
			<div class="row marginT20">
				<div class="col-md-12">
					<sf:form id="changePasswordForm" action='${pageContext.request.contextPath}/changePasswordSubmit' method="POST" modelAttribute="changePassword">
						<div class="row" style="margin-left: 0px; margin-bottom: 5%;">
							
							<div class="col-md-6">
								<div class="form-group row">
									<label for="newpassword" cssclass="col-sm-5 col-form-label"	class="labelwidth"><b>New Password <span class="red">*</span></b></label>
									<div class="col-sm-7">
										<sf:input path="newPassword" id="newPassword" cssclass="form-control" maxlength="20"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group row">
									<label for="confirmnewpassword"	cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Confirm New Password <span class="red">*</span>
									</b></label>
									<div class="col-sm-7">
										<sf:input type="password" path="confNewPass" cssclass="form-control  form-control-sm" id="confNewPassword" maxlength="20" />
									</div>
									<h6><b><span id='message'></span></b></h6>
								</div>
							</div>
						</div>

						<div style="text-align: center; padding-top: 10px; padding-bottom: 10px;">
							<button type="submit" id="update" class="btn btn-primary btn-sm" onclick="return finalSubmit()">Update</button>
							&nbsp;<button type="button" id="cancel" class="btn btn-primary btn-sm reset-form">Cancel</button>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		let newPassError = true;
		let confNewPassError = true;
 		$(":submit").attr("disabled", true);
		$.validate({
			validateOnBlur : true,
			form : '#changePasswordForm',
			onSuccess : function(event, form) {
				finalSubmit();
				if ((newPassError == true) && (confNewPassError == true)) {
					return true;
				} else {
					return false;
				}
			}
		});
	});

	function finalSubmit() {
		if (!$("#newPassword").val()) {
			alert("Please provide new password !!!");
			newPassError = false;
			return false;
		} else {
			newPassError = true;
		}
		if (!$("#confNewPassword").val()) {
			alert("Please provide confirm password !!!");
			confNewPassError = false;
			return false;
		} else {
			confNewPassError = true;
		}
	}

	$('#newPassword, #confNewPassword').on('keyup', function() {
		if ($('#newPassword').val() == $('#confNewPassword').val()) {
			$('#message').html('').css('color', 'green');
			$(":submit").attr("disabled", false);
		} else{
			$('#message').html('Not Matching').css('color', 'red');
			$(":submit").attr("disabled", true);
		}
	});

	$("#cancel").on('click', function(event) {
		$('#newPassword').val('');
		$('#confNewPassword').val('');
		$(":submit").attr("disabled", true);
	});
	
	/* $(document).ready(function() {
	 $(".change-contact-field").hide(); 
	 $(document).delegate('.change-contact-no','click',function(){ if($(this).is(":checked")){ $(".change-contact-field").show(); }else{ $(".change-contact-field").hide(); } });      

	 $(".reset-form").click(function (e) {
	 e.preventDefault();
	 $('form[name="change-password"]').find('input:text').val('');  
	 });
	 $('form[name="change-password"]').validate({
	 rules: {
	 old_password: {required: true, minlength: 8, maxlength: 20},
	 new_password: {required: true, minlength: 8, maxlength: 20},
	 confirm_new_password: {required: true, minlength: 8, maxlength: 20, equalTo: "#newpassword"}
	 },
	 messages: {
	 old_password: {
	 required: "Enter Old Password."
	 },
	 new_password: {
	 required: "Enter New Password."
	 },
	 confirm_new_password: {
	 required: "Enter Confirm Password.",
	 equalTo: "Please enter the same password as above"
	 }
	 },
	 submitHandler: function (form) {
	 form.submit();
	 }
	 });	
	 });	 */
</script>
