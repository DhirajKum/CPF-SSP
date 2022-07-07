<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/static/images/fci.jpg">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/COMMON/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/COMMON/CSS/regmain.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/epfo-style.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/bootstrap4_5_2.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/jquery-ui.min.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery3_5_1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/popper.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery.form-validator.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/bootstrap/js/bootstrap.min.js"></script> --%>


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
  <c:if test="${not empty msg}">
  		<div class="col-md-3"></div>
		<div class="col-md-6">
			<div class="alert alert-info alert-dismissible" style="text-align: center;">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4><b>${msg}</b></h4>
			</div>
		</div>
		<div class="col-md-3"></div>
</c:if>

  <div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
	<div class="wrapper wrapper--w680">
<%-- <c:if test="${not empty otpFail}">
		<div class="col-md-12">
			<div class="alert alert-danger alert-dismissible" style="text-align: center;">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<h4><b>${otpFail}</b></h4>
			</div>
		</div>
</c:if> --%>

		<div class="card card-4">
			<div class="card-body">
				<h2 class="title">Verification OTP</h2>
				<form method="GET">

					<div class="form-group input-group">
						<div class="input-group-prepend">
							<span class="input-group-text"> <i class="fa fa-list-ol"></i>
							</span>
						</div>
						<input type="text" id="otp" name=""  maxLength="6" size="6" class="form-control" placeholder="Enter Otp">
					</div>

					<div class="form-group">
						<button type="button" id="verifyOtp" class="btn btn-primary btn-block">Verify</button>
					</div>
					
				</form>
			</div>

		</div>
	</div>
</div>
<%@include file="./shared/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/cpf/app.js"></script>
<script type="text/javascript">



</script>


</body>
</html>