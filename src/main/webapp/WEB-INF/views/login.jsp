<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" type="image/png" href="${pageContext.request.contextPath}/resources/COMMON/images/icons/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/COMMON/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/COMMON/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/COMMON/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/COMMON/vendor/css-hamburgers/hamburgers.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/COMMON/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/COMMON/CSS/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/COMMON/CSS/util.css">

<style>

@font-face {
    font-family: hindi;
    src: url(${pageContext.request.contextPath}/resources/COMMON/fonts/Kruti_Dev_010.ttf);
}
.head2{
	font-family: hindi;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>

<div class="limiter">
	<c:if test="${not empty message}">
		<div class="alert alert-danger" style="text-align: center;">
			${message}</div>
	</c:if>
	<div class="container-login100">
		<div class="wrap-login100" style="padding-top: 50px;">
			<div class="login100-pic js-tilt" data-tilt>
				<img
					src="${pageContext.request.contextPath}/resources/COMMON/images/fci.jpg"
					alt="IMG"> <a class="navbar-brand navbar-right" href="#"
					style="margin-top: -10px; margin-left: 58px; text-align: center;">
					<span class="header-text">CPF Self Service Portal </span>
				</a>
			</div>

			<form class="login100-form validate-form"
				action="<c:url value='/j_spring_security_check' />" method='POST'
				name="loginForm" autocomplete="off" id="loginform">
				<span class="login100-form-title"> Member Login </span>

				<div class="wrap-input100 validate-input"
					data-validate="Employee number is required">
					<input class="input100" type="text" name="username" id="username"
						placeholder="Employee Number"> <span
						class="symbol-input100"> <i class="fa fa-pencil"
						aria-hidden="true"></i>
					</span>
				</div>

				<div class="wrap-input100 validate-input"
					data-validate="Password is required">
					<input class="input100" type="password" name="password"
						id="password" placeholder="Password"> <span
						class="symbol-input100"> <i class="fa fa-lock"
						aria-hidden="true"></i>
					</span>
				</div>

				<div class="container-login100-form-btn">
					<button class="login100-form-btn" type="submit">Login</button>
				</div>

				<div class="text-center p-t-12">
					<span class="txt1"> </span> <a class="txt2"
						href="${pageContext.request.contextPath}/forgotPassword">
						Forgot Password? </a>
				</div>

				<div class="text-center">
					<a class="txt2"
						href="${pageContext.request.contextPath}/userRegistration">
						Create your Account <i class="fa fa-long-arrow-right m-l-5"
						aria-hidden="true"></i>
					</a>
				</div>
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
			<div class="col-sm-12"
				style="text-align: center; margin-top: 30px; margin-bottom: -30px;">
				<small class="form-text text-muted smallText">For any
					discrepancy email to fci.cssp@gmail.com or contact to help desk
					no. 011-43527367</small>
			</div>
			
			<div class="col-sm-12" style="text-align: center; margin-top: 30px; margin-bottom: -30px;">
				<small class="form-text text-muted smallText">Language <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
				<a class="txt2"	href="http://fcilekha.in:7778/CpfSelfService">English <i aria-hidden="true"></i></a>
				<a class="txt2 head2" href="http://hi.fcilekha.in:7778/CpfSelfService/login"><b> fgUnh</b><i aria-hidden="true"></i></a>
				</small>
			</div>
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath}/resources/COMMON/vendor/jquery/jquery-3.2.1.min.js"></script>
<script	src="${pageContext.request.contextPath}/resources/COMMON/vendor/bootstrap/js/popper.js"></script>
<script	src="${pageContext.request.contextPath}/resources/COMMON/vendor/bootstrap/js/bootstrap.min.js"></script>
<script	src="${pageContext.request.contextPath}/resources/COMMON/vendor/select2/select2.min.js"></script>
<script	src="${pageContext.request.contextPath}/resources/COMMON/vendor/tilt/tilt.jquery.min.js"></script>
<script>
	$('.js-tilt').tilt({
		scale : 1.1
	})
	
	/* var $loginform = $('#loginform');
	if($loginform.length){
		$loginform.validate({
			rules:{
				username:{
					required : true
					
				},
				password:{
					required : true
					
				}
			},
			
			message :{
				username:{
					required : 'Please enter the user name'
				},
				password:{
					required : 'Please enter the password'
				}
			},
			errorElement :'em',
			errorPlacement: function(error, element){
				error.addClass('help-block');
				error.insertAfter(element);
			}
			
		}); 
	
	}*/
	
	$(document).ready(function() {
	 $("#message").hide();
	});
	
</script>
<script	src="${pageContext.request.contextPath}/resources/COMMON/JS/main.js"></script>
<script	src="${pageContext.request.contextPath}/resources/COMMON/vendor/jquery/jquery-3.2.1.min.js"></script>
<script	src="${pageContext.request.contextPath}/resources/COMMON/JS/bootstrap.min.js"></script>

</body>
</html>