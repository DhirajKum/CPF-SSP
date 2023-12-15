<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<spring:url var="css" value="/resources/css" />
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="cache-control" content="max-age=0, must-revalidate, no-cache, no-store, private">
<meta http-equiv="expires" content="-1">
<meta http-equiv="pragma" content="no-cache">
<meta name="_csrf" content="${_csrf.token}">
<meta name="_csrf_header" content="${_csrf.headerName}">

<title>CPF Self Service - ${title}</title>

<link href="${pageContext.request.contextPath}/resources/static/images/fci.jpg"	rel="shortcut icon">
<link href="${pageContext.request.contextPath}/resources/static/css/epfo-style.css"	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/static/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/static/css/datepicker.min.css"	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/static/css/font-awesome.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/static/css/jquery.dataTables.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/static/css/buttons.dataTables.min.css" rel="stylesheet" />

<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery-3.5.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/popper.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery.dataTables.js"></script> --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery.dataTables.min-1.12.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jszip.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/pdfmake.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/vfs_fonts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/buttons.html5.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/buttons.print.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery.form-validator.min.js"></script>

<script type="text/javascript">
	window.menu = '${title}';
</script>
</head>
<style>
.red{
	color: #e32;
	position: absolute; 
	margin: 0px 0px 0px 0px; 
	font-size: small; 
	padding: 0 0px 0 0; 
	}
</style>

<body>
	<!-- Header -->
	<%@include file="./shared/appheader.jsp"%>

	<!-- Navigation -->
	<c:if test="${showNavBar == true}">
		<%@include file="./shared/navbar.jsp"%>
	</c:if>

	<c:if test="${clickHome == true}">
		<%@include file="./home.jsp"%>
	</c:if>

	<c:if test="${clickChangePass == true}">
		<%@include file="./changePassword.jsp"%>
	</c:if>

	<c:if test="${clickClaimReq == true}">
		<%@include file="./claimRequest.jsp"%>
	</c:if>

	<c:if test="${clickUpdateClaimReq == true}">
		<%@include file="./updateClaimRequest.jsp"%>
	</c:if>

	<c:if test="${clickMyPendingReq == true}">
		<%@include file="./myPendingClaimRequest.jsp"%>
	</c:if>

	<c:if test="${clickPendingReq == true}">
		<%@include file="./pendingClaimRequest.jsp"%>
	</c:if>

	<c:if test="${clickHistoryClaimReq == true}">
		<%@include file="./historyClaimRequest.jsp"%>
	</c:if>

	<c:if test="${clickViewClaimReq == true}">
		<%@include file="./viewClaimRequest.jsp"%>
	</c:if>

	<c:if test="${clickActClaimReq == true}">
		<%@include file="./actClaimRequest.jsp"%>
	</c:if>

	<c:if test="${clickApprovedReq == true}">
		<%@include file="./approvedClaimRequest.jsp"%>
	</c:if>

	<c:if test="${clickCompletedReq == true}">
		<%@include file="./completedClaimRequest.jsp"%>
	</c:if>

	<c:if test="${clickAssignClaimReq == true}">
		<%@include file="./assignClaimRequest.jsp"%>
	</c:if>

	<c:if test="${clickCpfSlip == true}">
		<%@include file="./cpfSlipReport.jsp"%>
	</c:if>
	
	<c:if test="${clickClaimReqReport == true}">
		<%@include file="./claimRequestReport.jsp"%>
	</c:if>
	<c:if test="${clickRoleMapping == true}">
		<%@include file="./roleMapping.jsp"%>
	</c:if>
	
	<c:if test="${clickRevokeRole == true}">
		<%@include file="./revokeRole.jsp"%>
	</c:if>

	<%@include file="./shared/footer.jsp"%>

	<script	href="https://cdn.datatables.net/1.10.22/js/jquery.dataTables.min.js" rel="stylesheet"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/cpf/app.js"></script>

</body>
</html>