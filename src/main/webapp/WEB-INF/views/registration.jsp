<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="cache-control" content="max-age=0, must-revalidate, no-cache, no-store, private">
<meta http-equiv="expires" content="-1">
<meta http-equiv="pragma" content="no-cache">

<script>var appContextPath = "${pageContext.request.contextPath}"</script>
<link href="${pageContext.request.contextPath}/resources/COMMON/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery.mask.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery.mask.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery.passwordify.js"></script>

<style type="text/css">
label {
    display: block;
    text-align: left;
    width: 90px;
}

#overlay{	
  position: fixed;
  top: 0;
  z-index: 100;
  width: 100%;
  height:100%;
  display: none;
  background: rgba(0,0,0,0.6);
}
.cv-spinner {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;  
}
.spinner {
  width: 40px;
  height: 40px;
  border: 4px #ddd solid;
  border-top: 4px #2e93e6 solid;
  border-radius: 50%;
  animation: sp-anime 0.8s infinite linear;
}
@keyframes sp-anime {
  100% { 
    transform: rotate(360deg); 
  }
}
.is-hide{
  display:none;
}

</style>

</head>
<body>
<div class="container">
<div class="row row-fluid">
	<div class="col-md-5" style="">
	<img style="padding-top: 5px !important" class="header-logo" id="header-icon-img" src="${pageContext.request.contextPath}/resources/static/images/fci.jpg">
			<a class="navbar-brand navbar-right" href="#" style="margin-top: 5px;"> 
			<span class="header-text">CPF Self Service Portal </span><br> 
			<span class="hidden-xs header-sub-text" style=""> Food Corporation Of India</span>
			</a>
	</div>
</div>
</div>
	<c:if test="${not empty regInfo}">
		<div class="col-md-12" style= "float:none" id="regInfo">
			<div class="alert alert-danger alert-dismissible" style="text-align: center;">
				<!-- <button type="button" class="close" data-dismiss="alert">&times;</button> -->
				<h4><b>${regInfo}</b></h4>
			</div>
		</div>
	</c:if>
	<c:if test="${not empty message}">
		<div class="col-md-12" style= "float:none" id="message">
			<div class="alert alert-danger alert-dismissible" style="text-align: center;">
				<!-- <button type="button" class="close" data-dismiss="alert">&times;</button> -->
				<h4><b>${message}</b></h4>
			</div>
		</div>
	</c:if>
	
	<h4><b><div id='msg' style="text-align:center; font-weight:bold; color:green"></div></h4>
	<%-- <c:if test="${not empty empIdStatus}">
		<div class="col-md-12">
			<div class="alert alert-danger alert-dismissible" style="text-align: center;">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4><b>${empIdStatus}</b></h4>
			</div>
		</div>
	</c:if> --%>
<div class="page-wrapper p-t-30 p-b-100 font-poppins" style="background-color: silver;">
	<div class="wrapper wrapper--w680">
		<div class="card card-4">
			<div class="card-body">
				<h2 class="title">Registration Form</h2>
				<sf:form id="registrationForm" action='${pageContext.request.contextPath}/saveRegistrationData' method='POST' modelAttribute="registeredUser" onsubmit="this.js_enabled.value=1;return true;">
					<noscript>
						<div id="noJS" class="alert alert-danger" style="padding-left: 20px; padding-right: 0px; margin-left: 0px;">Please enable JavaScript in your browser</div>
					</noscript>

					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-user"></i>
							</span>
						</div>
						<label>Employee number</label>		
		    			<sf:input path="empNum" id="empNum" cssClass="form-control"/>
						<a href="javascript:void(0)" id="empdetails" style="background-color:#007bff;color: #fff;padding-top: 4px;padding-left: 25px;padding-right: 25px;" class="btn btn-default">Get Details</a>
						<div class="invalid-feedback">Please enter a valid employee number</div>
					</div>
	
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-users"></i>
							</span>
						</div>
						<label>Employee name</label>		
								  <sf:input path="empName" id="empName" cssClass="form-control" />
					</div>

					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-list-ol"></i>
							</span>
						</div>
						<label>UAN number</label>		
						    <sf:input path="uan" id="uan" cssClass="form-control" />
					</div>
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-users"></i>
							</span>
						</div>
						<label>Employee Email</label>		
						    <sf:input path="empEmail" id="empEmail" cssClass="form-control" />
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-phone"></i>	</span>
						</div>
						<label>Phone number</label>		
						    <sf:input path="empPhone" id="phone" maxLength="10" size="10" cssClass="form-control"  onChange="activeSendOTP();" /><br/>
							<a style="background-color:#007bff;color: #fff;padding-top: 3px;padding-left: 25px;padding-right: 25px;"
							href="javascript:void(0)" class="btn btn-default" id="sendOtp">Send Otp</a>
					</div>
                   <!-- data-toggle="modal"
                   	<a href="#" onClick="MyWindow=window.open('http://www.google.com',
                       'MyWindow','width=600,height=300'); 
                       return false;">verify</a> -->
			
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i></span>
						</div>
						<label>Create password</label>		
						    <sf:input path="password" id="pass" cssClass="form-control" />
					</div>
					<!-- form-group// -->
					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-lock"></i></span>
						</div>
						<label>Repeat password</label>	
							<input type="password" id="repass" class="form-control" >
							<h6><b><span id='passMessage'></span></b></h6>
					</div>
					<!-- form-group// -->
					<div class="form-group">
						<button type="submit" id="createAcc" class="btn btn-primary btn-block">Create Account</button>
					</div>
					<!-- form-group// -->
					<p class="text-center">
						Have an account? <a href="${pageContext.request.contextPath}/login">Log In</a>
					</p>
				
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<input type="hidden" name="js_enabled" value="0">
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
<!-- 	 <div class="modal" data-backdrop="static" id="otpModal">
	  <div class="modal-dialog modal-dialog-centered">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5><b>Please enter the 6-digit verification code we sent via SMS:</b></h5><br>
	      </div>
	      
	      <div class="modal-body">
	   	    <div id="form">
		      <input type="text" id="otp" class="font-weight-bold display-4" maxLength="6" size="6" min="0" max="9" pattern="[0-9]{1}" />
		    </div>
	      </div>
	
	      <div class="modal-footer">
	      	Didn't receive the code?<BR />
		      <a href="#">Resent OTP / </a><br />
		      <a href="#">Change phone number</a>
	        <a href="#" class="btn btn-primary btn-success" id="verifyOtp">Verify</a>
	        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
	      </div>
	
	    </div>
	  </div>
	</div> -->

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/cpf/app.js"></script>

<script type="text/javascript">

$(document).ready(function() {
    $("#noJS").hide();
    $("#regInfo").hide();
    $("#message").hide();
	$("#empName").prop('readonly', true);
	$("#uan").prop('readonly', true);
	$('#sendOtp').addClass('disabled');
	$("#pass").prop('readonly', true);
	$("#repass").prop('readonly', true);
	var otpVal = '${otpValidate}';
	if(otpVal==='FAIL'){
		var empNum = '<%=session.getAttribute("empNum")%>';
		var empName = '<%=session.getAttribute("empName")%>';
		var empEmail = '<%=session.getAttribute("empEmail")%>';
		var regMobileNo = '<%=session.getAttribute("regMobileNo")%>';
		var uan =  '<%=session.getAttribute("uan")%>';
		$("#empNum").val(empNum);
		$("#empName").val(empName);
		$("#empEmail").val(empEmail);
		$("#phone").val(regMobileNo);
		$("#uan").val(uan);
				
		$("#pass").prop('readonly', true);
		$("#repass").prop('readonly', true);
		$('#sendOtp').removeClass('disabled');
	}
	if(otpVal==='SUCCESS'){
		<%-- var obj= '<%=session.getAttribute("rUserData")%>';
		var data = JSON.parse(JSON.stringify(obj));
		$("#empNum").val(data.empNum); --%>
		var empNum = '<%=session.getAttribute("empNum")%>';
		var empName = '<%=session.getAttribute("empName")%>';
		var empEmail = '<%=session.getAttribute("empEmail")%>';
		var regMobileNo = '<%=session.getAttribute("regMobileNo")%>';
		var uan =  '<%=session.getAttribute("uan")%>';
		
		$("#empNum").val(empNum);
		$("#empName").val(empName);
		$("#empEmail").val(empEmail);
		$("#phone").val(regMobileNo);
		$("#uan").val(uan);
		
		$("#empNum").prop('readonly', true);
		$("#empName").prop('readonly', true);
		$("#uan").prop('readonly', true);
		//$("#empEmail").prop('readonly', true);
		$("#phone").prop('readonly', true);
		
		$("#pass").prop('readonly', false);
		$("#repass").prop('readonly', false);
		$('#sendOtp').addClass('disabled');
	}
	
	let empNumError=true;
	let empNameError=true;
	let empEmailError=true;
	let empPhoneError=true;
	let passError=true;
	let repassError=true;
$.validate({
	validateOnBlur : true,
	form : '#registrationForm',
	onSuccess : function(event, form) {
		finalSubmit();
		if((empNumError==true)&&(empNameError==true)&&(empEmailError==true)&&(empPhoneError==true)&&(passError==true)&&(repassError==true)&&($('#pass').val() == $('#repass').val())){
			$("#createAcc").prop("disabled",true);
			$("#createAcc").html('<i class="fa fa-spinner fa-spin"></i> Registration in progress ...');
			//$("#registrationForm").submit();
			return true;
		}else{
			return false;
		}
	}
});

function finalSubmit(){
	if(!$("#empNum").val()){
		alert("Please provide employee number !!!");
		empNumError=false;	
		return false;
	}else{
		empNumError=true;
	}
	if(!$("#empName").val()){
		alert("Please click on 'Get Details' to fetch employee name and other required details !!!");
		empNameError=false;	
		return false;
	}else{
		empNameError=true;
	}
	if(!validateEmail()){
		alert("Please provide valid email !!!");
		empEmailError=false;
		return false;
	}else{
		empEmailError=true;
	}
	
	if(!$("#phone").val()){
		alert("Please provide your mobile number for OTP validation !!!");
		empPhoneError=false;
		return false;
	}else{
		empPhoneError=true;
	}
	
	if(!$("#pass").val()){
		alert("Please provide password !!!");
		passError=false;
		return false;
	}else{
		passError=true;
	}
	if(!$("#repass").val()){
		alert("Please provide repeat password !!!");
		repassError=false;
		return false;
	}else{
		repassError=true;
	}
}
});


$('#pass, #repass').on('keyup', function () {
  if ($('#pass').val() == $('#repass').val()) {
    $('#passMessage').html('').css('color', 'green');
  } else 
    $('#passMessage').html('Not Matching').css('color', 'red');
});

function validateEmail(){ 
	const email = $("#empEmail").val().trim();
	var emailReg = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  return emailReg.test(email);
}


function activeSendOTP(){
	$('#sendOtp').removeClass('disabled');
	$("#pass").val('');
	$("#repass").val('');
	$("#pass").prop('readonly', true);
	$("#repass").prop('readonly', true);
}

$(document).ajaxSend(function() {
    $("#overlay").fadeIn(500);
});

/* function addRegistrationData() {
	var input = {};
	input["empNum"] = $('#registrationForm input[id=empNum]').val().trim();
	input["uan"] = $('#registrationForm input[id=uan]').val().trim();
	input["empName"] = $('#registrationForm input[id=empName]').val().trim();
	input["phone"] = $('#registrationForm input[id=phone]').val().trim();
	input["password"] = $('#registrationForm input[id=pass]').val().trim();

	var formData = JSON.stringify(input);
	$.ajax({
		type : 'POST',
		url : '${pageContext.request.contextPath}/saveRegistrationData',
		data : formData,
		contentType : "application/json",
		success : function(res) {
			if (res) {
				$('#login').show();
			}
		}
	}).done(function() {
     		setTimeout(function(){
       	$("#overlay").fadeOut(500);
     	},600);
   	});
} */


$("#empdetails").on('click', function(event){
	var empNum = $("#empNum").val().trim();

	if(empNum!=""){
	$("#empNum").prop('readonly', true);
	var urlVar = '${pageContext.request.contextPath}/getEmpDetailsByEmpNum?empNum='+empNum;
	$.ajax({
		type : 'GET',
		url : urlVar,
		//data : input,
		async : false,
		contentType : "application/json",
		headers: {"Authorization" : "8UmRUqrVzelvotk6jI2NpVke75B"},
		success : function(res) {
			if (res) {
					setEmployeeData(res);						
			}else{
				alert("Invalid employee id/number !!!")
				$("#empNum").prop('readonly', false);
			}
		}
	}).done(function() {
     		setTimeout(function(){
       	$("#overlay").fadeOut(500);
     	},600);
   	});
	}else{
		alert("Kindly provide the employee id/number first...")
	}
	
});

function setEmployeeData(res) {
	var data = JSON.parse(JSON.stringify(res));
	$("#empNum").val(data.EMP_NUM);
	var middleName = (data.EMP_MIDDLE_NAME)!=null?data.EMP_MIDDLE_NAME:'';
	var lastName = (data.EMP_LAST_NAME)!=null?data.EMP_LAST_NAME:'';
	$("#empName").val(data.EMP_FIRST_NAME+' '+middleName+' '+lastName);
	$("#uan").val(data.UAN);
	
	$('#empName').prop("readonly", true);
	$('#uan').prop("readonly", true);
	//$("#phone").val(v[""]);
}


document.oncontextmenu = rightClick;
function rightClick(clickEvent) {
    clickEvent.preventDefault();
    return false;
}
document.onkeydown = function(e) {
	if(event.keyCode == 123) {
	return false;
	}
	if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){
	return false;
	}
	if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){
	return false;
	}
	if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){
	return false;
	}
}

$("#sendOtp").on('click', function(event){
		
		$('#sendOtp').addClass('disabled');
		var mobile = $("#phone").val().trim();
		var empName = $("#empName").val().trim();
		var empNum = $("#empNum").val().trim();
		var empEmail = $("#empEmail").val().trim();
		var uan = $("#uan").val().trim();
				
		if(mobile!=""&&empNum!=""&&empName!=""){
		$("#phone").prop('readonly', true);
		var urlVar = '${pageContext.request.contextPath}/send-otp?mobile='+mobile+'&empNum='+empNum+'&empName='+empName;
		$.ajax({
			type : 'GET',
			url : urlVar,
			//data : input,
			async : false,
			dataType : "JSON",
			success : function(data, textStatus, xhr) {
				if (xhr.status=='200') {
			
					window.location.href='${pageContext.request.contextPath}/sendOTP?regMobileNo='+mobile+'&empNum='+empNum+'&empName='+empName+'&empEmail='+empEmail+'&uan='+uan;						
				}
				if(xhr.status=='201'){
					$('#sendOtp').removeClass('disabled');
					$("#phone").prop('readonly', false);
					$('#msg').html("Phone number already registered with other employee ...!!! </BR>Please use other phone number to complete your registration process.").fadeIn('slow');
					$('#msg').delay(10000).fadeOut('slow');
				}
				if(xhr.status=='202'){
					$('#sendOtp').removeClass('disabled');
					$('#msg').html("You have already registered !!! </BR> You can go for login...").fadeIn('slow');
					$('#msg').delay(5000).fadeOut('slow');
				}
			}
		}).done(function() {
      		setTimeout(function(){
      		$("#overlay").fadeOut(500);
      	},600);
    	});
	}else{
		alert("Phone Number, Employee Number and Employee Name required...");	
	}
});


/* $("#verifyOtp").on('click', function(event){
	
		$("#phone").prop('readonly', true);
		var mobile = $("#phone").val().trim();
		var otp = $("#otp").val().trim();
		
		if(mobile!="" & otp!=""){		
		var urlVar = '${pageContext.request.contextPath}/verify-otp?mobile='+mobile+'&otp='+otp;
		$.ajax({
			type : 'GET',
			url : urlVar,
			//data : input,
			async : false,
			contentType : "application/json",
			success : function(res) {
				if (res) {
					/* var cAdding = "Approval Process In Progress";
					var cAdded = " Wait for a moment..."
					var dialog = bootbox.dialog({
								title : cAdding,
								message : '<p><i class="fa fa-spin fa-spinner"></i> Loading...</p>'
								});
					dialog.init(function() {
						setTimeout(function() {
							dialog.find('.bootbox-body').html(cAdded);
						}, 3000);
					});
					setTimeout(function() {
						dialog.modal('hide');
					}, 4000); */
					//$("#otpModal").modal('hide');
					//$("#pass").prop('readonly', false);
					//$("#repass").prop('readonly', false);
					//setEmployeeData(res);						
				/* }else{
					alert("OTP Not valid !!!");
				}
			}
		});
	}else{
		if(mobile==="")
		alert("Phone number required...");
		if(otp==="")
		alert("OTP number required...");
	} */
//}); 

</script>
 	
</body>
</html>