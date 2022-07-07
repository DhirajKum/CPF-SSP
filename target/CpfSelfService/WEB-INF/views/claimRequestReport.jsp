<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/COMMON/images/fci.jpg" rel="shortcut icon">
<link href="${pageContext.request.contextPath}/resources/COMMON/JS/jquery-3.5.1.min.js" rel="STYLESHEET" type="text/css" >
<link href="${pageContext.request.contextPath}/resources/COMMON/JS/bootstrap.min.js" rel="STYLESHEET" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/jquery-ui.min.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/jquery-ui.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/static/js/jquery-ui.min.js"></script>

</head>
<body>
<c:if test="${not empty message}">
	<div class="col-md-12">
		<div class="alert alert-danger alert-dismissible" style="text-align: center;">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			${message}
		</div>
	</div>
</c:if>
<div class="col-md-12" style="padding-left: 10px;">

<sf:form action='${pageContext.request.contextPath}/report/generateClaimRequestReport' method='POST' modelAttribute="claimRequestReportDto">
<div class="row" style="text-align: center;">	
	<div class="col-md-5">
		<div class="form-group row">
			<label class="col-sm-4 col-form-label"><b>From Date</b></label>
			<div class="col-sm-8">
				<sf:input path="fromDate" id="datepicker1" cssClass="form-control" />
				
			</div>
		</div>
	</div>
	<div class="col-md-5">
		<div class="form-group row">
			<label class="col-sm-4 col-form-label"><b>To Date</b></label>
			<div class="col-sm-8">
			<sf:input path="toDate" id="datepicker2" cssClass="form-control" />
			
			</div>
		</div>
	</div>
	 
</div>

<div class="row" style="text-align: center;">	
	<div class="col-md-5">
		<div class="form-group row">
			<label class="col-sm-4 col-form-label"><b>Emp Num </b></label>
			<div class="col-sm-8">
						 <sf:input path="empNum" id="empNum" cssClass="form-control numbers" />
			</div>
		</div>
	</div>
	
	</div>
	
	<div class="row" style="text-align: center;">	
	<div class="col-md-5">
		<div class="form-group row">
			<label class="col-sm-4 col-form-label"><b>Claim Type </b></label>
			<div class="col-sm-8">
				<sf:select cssclass="form-control" id="claimType" path="claimType">
					<option value="" label="---Select---"/>
					<option value="CpfFinalSettlement"> CPF Final Settlement </option>
									<option value="CpfPartFinalWithdrawal"> CPF Part final Withdrawal </option>
									<option value="90%Withdrawal"> 90% Withdrawal </option>
									<option value="TempAdv"> Temp. Adv. </option>
				</sf:select>
			</div>
		</div>
	</div>
	
</div>
	 

<div class="row" style="text-align: center;">	
		<div class="col-md-5">
		<div class="form-group row">
			<label class="col-sm-4 col-form-label"><b></b></label>
			<div class="col-sm-8">
				
			</div>
		</div>
	</div>
	<div class="col-md-5">
		<div class="form-group row">
			<button type="submit" class="btn btn-primary btn-sm">Generate Report</button>&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-primary btn-sm" onclick="clearData()">Clear</button>
		
			<!-- <a href="#" id="generateReport" class="btn btn-primary btn-sm reset-form">Generate Report</a> -->
		</div>
	</div>
	 
</div>



</sf:form>

<div class="epfo-container displayDiv">
<div class="row displayDiv">
		
<div class="col-md-12 displayDiv">
	<noscript>
		<div class="alert alert-danger"	style="padding-left: 20px; padding-right: 0px; margin-left: 0px;">Please enable JavaScript in your browser</div>
	</noscript>

	<!-- <div class="bs-example" style="padding-top: 1%;">
		<ul class="breadcrumb">
			<li>Online Services</li>
			<li class="active">Track Claim Status</li>
		</ul>
	</div> -->


<div class="panel panel-info ocs-track-status" style="margin-left:5px">
	<div class="panel-heading p-3" style="background: #E6E6E6;">
		<h5 class="panel-title">
			<i class="fa fa-bars" aria-hidden="true" data-target=".ocs-track-status .panel-body, .ocs-track-status .toggle-icon" data-toggle="collapse"></i> &nbsp;&nbsp;&nbsp;Claim Request List 
		</h5>
	
	</div>
<div class="panel-body in" style="height: auto;">
		<!-- <table class="table table-bordered table-responsive">
			<tr>
				<td><b style="color: red">No User Found...</b></td>
			</tr>
		</table>-->
<table id="claimReqReportdatatable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
  <thead>
    <tr>
      <th class="th-sm">Employee Number</th>
      <th class="th-sm">Employee Name</th>
      <th class="th-sm">Claim Id</th>
      <th class="th-sm">Claim Type</th>
      <th class="th-sm">Claim Date</th>
      <th class="th-sm">Claim Status</th>
      <th class="th-sm">Present Place Of Posting</th>
      <th class="th-sm">Parent Zone</th>
    
    </tr>
  </thead>
  <tbody>
  <c:forEach var="ClaimRequestReportDto" items="${claimReportList}">
			<tr>
				<td>${ClaimRequestReportDto.empNum}</td>
				<td>${ClaimRequestReportDto.empName}</td>
				<td>${ClaimRequestReportDto.claimId}</td>
				<td>${ClaimRequestReportDto.claimType}</td>
				<td>${ClaimRequestReportDto.claimDate}</td>
				<td>${ClaimRequestReportDto.claimStatus}</td>
				<td>${ClaimRequestReportDto.presLocation}</td>
				<td>${ClaimRequestReportDto.parentZone}</td>
				
			</tr>
			</c:forEach>
			
    </tbody>
</table>							
</div>
</div>
</div>
</div>
</div>

</body>
</html>

<script>

$(document).ready(function() {
    $("#datepicker1").datepicker({dateFormat:"dd-mm-yy"});
    $("#datepicker2").datepicker({dateFormat:"dd-mm-yy"});
    $("#datepicker1").prop('readonly', true);
    $("#datepicker2").prop('readonly', true);
    
  $('.numbers').keyup(function () { 
    this.value = this.value.replace(/[^0-9\.]/g,'');
});
    });

function clearData(){

$("#datepicker1").val('');
$("#datepicker2").val('');
$("#empNum").val('');
}

</script>