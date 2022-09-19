<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="cache-control" content="max-age=0, must-revalidate, no-cache, no-store, private">
<meta http-equiv="expires" content="-1">
<meta http-equiv="pragma" content="no-cache">

<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/COMMON/images/icons/favicon.ico" />
<script>var appContextPath = "${pageContext.request.contextPath}"</script>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/static/images/fci.jpg">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/COMMON/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/COMMON/CSS/regmain.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/epfo-style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap4_5_2.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/common.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery3_5_1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/popper.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery.form-validator.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/bootstrap/js/bootstrap.min.js"></script> --%>

<style type="text/css">

.labelwidth {
    display: block;
    text-align: left;
    width: 180px;
}

</style>

</head>
<body>
	
	<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
		<div class="wrapper wrapper--w680">
			<div class="card card-4">
				<div class="card-body">
					<h2 class="title">Forgot Password</h2>
					<sf:form id="forgotPasswordForm" action='${pageContext.request.contextPath}/resetPassword' method="POST" modelAttribute="forgotPassword">
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i class="fa fa-phone"></i>
								</span>
							</div>
							 <label class="labelwidth">Employee number</label>
							<sf:input  path="empNum" id="empNum" cssclass="form-control" onChange="activateSendOTP();"/><br/>
								<a style="background-color:#007bff;color: #fff;padding-top: 6px;padding-left: 25px;padding-right: 25px;
								"href="javascript:void(0)" class="btn btn-default" id="forgotPassSendOtp">Send Otp</a> 
						</div>
						
						
						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i class="fa fa-list-ol"></i>
								</span>
							</div>
							<label class="labelwidth">New Password</label>
							<sf:input path="newPassword" id="newPass" cssclass="form-control" />
						</div>

						<div class="form-group input-group">
							<div class="input-group-prepend">
								<span class="input-group-text"> <i class="fa fa-user"></i>
								</span>
							</div>
							<label class="labelwidth">Confirm New Password</label>
							<input type="password" id="confNewPass" class="form-control"  >
							<h5><b><span id='message'></span></h5>
						</div>
						<!-- form-group// -->

						<!-- form-group// -->
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">Update</button>
						</div>

					</sf:form>
				</div>
				<!-- card.// -->

			</div>
		</div>
	</div>
	
<div id="overlay">
  <div class="cv-spinner">
    <span class="spinner"></span>
  </div>
</div>
	
<%@include file="./shared/footer.jsp"%>

<script type="text/javascript">

$(document).ready(function() {
	$('#forgotPassSendOtp').addClass('disabled');
	$("#newPass").prop('readonly', true);
	$("#confNewPass").prop('readonly', true);
	
	var otpVal = '${otpValidate}';
	if(otpVal==='FAIL'){
		var empNum = '<%=session.getAttribute("empNum")%>';
		$("#empNum").val(empNum);
	
		$("#newPass").prop('readonly', true);
		$("#confNewPass").prop('readonly', true);
		$('#forgotPassSendOtp').removeClass('disabled');
	}
	if(otpVal==='SUCCESS'){
		var empNum = '<%=session.getAttribute("empNum")%>';
		$("#empNum").val(empNum);
		
		$("#empNum").prop('readonly', true);
		$("#newPass").prop('readonly', false);
		$("#confNewPass").prop('readonly', false);
		$('#forgotPassSendOtp').addClass('disabled');
	}
	
let newPassError=true;
let confNewPassError=true;
$.validate({
	validateOnBlur : true,
	form : '#forgotPasswordForm',
	onSuccess : function(event, form) {
		finalSubmit();
		if((newPassError==true)&&($('#newPass').val() == $('#confNewPass').val())){
			return true;
		}else{
			return false;
		}
	}
});

function finalSubmit(){
	if(!$("#newPass").val()){
		alert("Please provide new password !!!");
		newPassError=false;	
		return false;
	}else{
		newPassError=true;
	}
	/* if(!$("#confNewPass").val()){
		alert("Please provide confirm new password !!!");
		empNameError=false;	
		return false;
	}else{
		empNameError=true;
	} */
}
	
});


function activateSendOTP(){
	$('#forgotPassSendOtp').removeClass('disabled');
	$("#newPass").val('');
	$("#confNewPass").val('');
	$("#newPass").prop('readonly', true);
	$("#confNewPass").prop('readonly', true);
}

$('#newPass, #confNewPass').on('keyup', function () {
  if ($('#newPass').val() == $('#confNewPass').val()) {
    $('#message').html('').css('color', 'green');
  } else 
    $('#message').html('Not Matching').css('color', 'red');
});

$(document).ajaxSend(function() {
    $("#overlay").fadeIn(500);
  });

$("#forgotPassSendOtp").on('click', function(event){
		
		var empNum = $("#empNum").val().trim();
		if(empNum!=""){
		var urlVar = '${pageContext.request.contextPath}/forgotSendOtp?empNum='+empNum;
		$.ajax({
			type : 'GET',
			url : urlVar,
			//data : input,
			async : false,
			dataType : "JSON",
			success : function(data, textStatus, xhr) {
				if (xhr.status=='200') {
					//$('#msg').html("Successfully send OTP on your registered mobile no  "+maskedMobileNo).fadeIn('slow');
					//$('#msg').delay(10000).fadeOut('slow');
					var mobileNo = data;
					var maskedMobileNo = mobileNo.toString().replace(/\d(?=\d{4})/g, "X");
					window.location.href='${pageContext.request.contextPath}/sendOTP?mobileNo='+maskedMobileNo+'&empNum='+empNum;		
				}
			},
			error: function(jqXHR, textStatus, errorThrown){
				if(jqXHR.status=='404'){
					alert("Employee ID not found !!!")
				}
			}
		}).done(function() {
      		setTimeout(function(){
        	$("#overlay").fadeOut(500);
      	},600);
    	});
	}else{
		alert("Employee number required...!!!");
		return false;	
	}
});


</script>


</body>
</html>