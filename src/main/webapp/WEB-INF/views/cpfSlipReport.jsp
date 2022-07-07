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

<sf:form id="cpfSlipRepoForm" action='${pageContext.request.contextPath}/report/generateCpfSlip' method='POST' modelAttribute="cpfSlipReportDto">
<div class="row" style="text-align: center;">	
	<div class="col-md-5">
		<div class="form-group row">
			<label class="col-sm-4 col-form-label"><b>From Year<span class="red">*</span></b></label>
			<div class="col-sm-8">
				<sf:select cssclass="form-control" id="fromYear" path="fromYear">
					<option value="" label="---Select---"/>
				</sf:select>
			</div>
		</div>
	</div>
	<div class="col-md-5">
		<div class="form-group row">
			<label class="col-sm-4 col-form-label"><b>To Year<span class="red">*</span></b></label>
			<div class="col-sm-8">
				<sf:select cssclass="form-control" id="toYear" path="toYear">
					<option value="" label="---Select---"/>
				</sf:select>
			</div>
		</div>
	</div>
	
	<div class="col-md-2">
		<div class="form-group row">
			<button type="submit" id="generateRepo" class="btn btn-primary btn-sm">Generate Report</button>
			<!-- <a href="#" id="generateReport" class="btn btn-primary btn-sm reset-form">Generate Report</a> -->
		</div>
	</div>
	<sf:hidden path="empNum" /> <%-- value="${userModel.empNum}"/> --%> 
</div>

</sf:form>

<div class="col-md-12 data-box">
	<div class="col-md-12 content-pad-0 data-box-header" style="font-size: 15px;">
		<div class="col-md-11 content-pad-0 box-icon-title" style="text-align:center;">FOOD CORPORATION OF INDIA</div>
		<div class="col-md-11 content-pad-0 box-icon-title" style="text-align:center;">CPF TRUST</div>
		<div class="col-md-11 content-pad-0 box-icon-title"><h5>Statement of Member's accounts with contributory provident found for the year ${fromYear}-${toYear}</h5></div>
	</div>
	<div class="col-md-12 content-pad-0 box-icon-body table-responsive">

		<table class="table table-striped table-bordered form-group" style="margin-left: 0px; margin-right: 20px; border: none; width:100%">
			<tbody style="width:100%">
				<tr>
					<td class="w-15"><b>Name</b></td>
					<td class="w-15">${cpfSlipHeader.empName}</td>
					<td class="w-15"><b>Staff Code</b></td>
					<td class="w-15">${cpfSlipHeader.staffCode}</td>
					<td class="w-15"><b>Designation</b></td>
					<td class="w-15">${cpfSlipHeader.designation}</td>
				</tr>
				<tr>
					<td class="w-15"><b>Office</b></td>
					<td class="w-15">${cpfSlipHeader.office }</td>
					<td class="w-15"><b>CPF No</b></td>
					<td class="w-15">${cpfSlipHeader.cpfNo}</td>
					<td class="w-15"><b>FPS No</b></td>
					<td class="w-15">${cpfSlipHeader.fpsNo}</td>
				</tr>
				<tr>
					<td class="w-15"><b>DT Joining FCI</b></td>
					<td class="w-15">${fciJoiningDt}</td>
					<td class="w-15"><b>DT Joining CPF</b></td>
					<td class="w-15">${fciJoiningDt}</td>
					<td class="w-15"><b>DT Of Birth</b></td>
					<td class="w-15">${dob}</td>
				</tr>
				<tr>
					<td class="w-15"><b>UAN</b></td>
					<td class="w-15">${cpfSlipHeader.uan}</td>
					<td class="w-15"></td>
					<td class="w-15"></td>
					<td class="w-15"></td>
					<td class="w-15"></td>
				</tr>
			</tbody>
		</table>
		
		
	</div>
	
	<div class="col-md-12 content-pad-0 box-icon-body table-responsive" style="min-height:0px;">
	<table class="table table-striped table-bordered form-group" style="margin-left: 0px; margin-right: 20px; border: none;">
		<thead>
			<tr>
				<th style="width:7%">Month</th>
				<th colspan="3" style="text-align:center;width:21%">FCI Contribution</th>
				<th colspan="7" style="text-align:center;width:45%">Member's Contribution</th>
				<th style="width:0%">Office</th>
				<th colspan="3" style="text-align:center;width:0%">Prog monthly bal for int</th>
			</tr>
			<tr>
				<th></th>
				<th>Matching</th>
				<th>EPS</th>
				<th>AMT W/D</th>
				<th>Compl</th>
				<th>Adv Taken</th>
				<th>Adv Refund</th>
				<th>Amt W/D</th>
				<th>VPF</th>
				<th>Adv Taken</th>
				<th>AMT W/D VPF</th>
				<th></th>
				<th>Corp</th>
				<th>Member</th>
				<th>VPF</th>
			</tr>
			
		</thead>
		<tbody>
			<tr>
				<td>O/B</td>
				<td>${cpfSlipOBDataDto.obMatching}</td>
				<td></td>
				<td></td>
				<td>${cpfSlipOBDataDto.obComl}</td>
				<td></td>
				<td></td>
				<td></td>
				<td>${cpfSlipOBDataDto.obVPF}</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<c:forEach var="CpfSlipMonthWiseDto" items="${cpfSlipMonthWiseList}">
			<tr>
				<td>${CpfSlipMonthWiseDto.month}</td>
				<td>${CpfSlipMonthWiseDto.fciMatching}</td>
				<td>${CpfSlipMonthWiseDto.fciEPS}</td>
				<td>${CpfSlipMonthWiseDto.fciAmtWD}</td>
				<td>${CpfSlipMonthWiseDto.memCompl}</td>
				<td>${CpfSlipMonthWiseDto.memAdvTaken}</td>
				<td>${CpfSlipMonthWiseDto.memAdvRef}</td>
				<td>${CpfSlipMonthWiseDto.memWD}</td>
				<td>${CpfSlipMonthWiseDto.memVPF}</td>
				<td>${CpfSlipMonthWiseDto.memVPFAdvTaken}</td>
				<td>${CpfSlipMonthWiseDto.memVPFAmtWD}</td>
				<td>${CpfSlipMonthWiseDto.office}</td>
				<td>${CpfSlipMonthWiseDto.monthlyCorp}</td>
				<td>${CpfSlipMonthWiseDto.monthlyMem}</td>
				<td>${CpfSlipMonthWiseDto.monthlyVPF}</td>
			</tr>
			</c:forEach>
			
			<tr>
				<td style="width:7%">Total</td>
				<td>${fciMatchingTot}</td>
				<td>${fciEPSTot}</td>
				<td>${fciAmtWDTot}</td>
				<td>${memComplTot}</td>
				<td>${memAdvTakenTot}</td>
				<td>${memAdvRefTot}</td>
				<td>${memWDTot}</td>
				<td>${memVPFTot}</td>
				<td>${memVPFAdvTakenTot}</td>
				<td>${memVPFAmtWDTot}</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td style="width:7%">Int</td>
				<td>${cpfSlipIntDataDto.intMatching}</td>
				<td></td>
				<td></td>
				<td>${cpfSlipIntDataDto.intComl}</td>
				<td></td>
				<td></td>
				<td></td>
				<td>${cpfSlipIntDataDto.intVPF}</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>	
			<tr>
				<td>C/B at ${creditAsOn}</td>
				<td>${cbMatching}</td>
				<td></td>
				<td></td>
				<td>${cbComl}</td>
				<td></td>
				<td></td>
				<td></td>
				<td>${cbVPF}</td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</tbody>
	</table>
</div>
	
<div class="col-md-12 content-pad-0 box-icon-body table-responsive" style="min-height:0px;">
	<table class="table table-striped table-bordered form-group"style="width:100%">
		<tbody>
			<tr>
				<td style="width:30%">Total balance to credit as on</td>
				<td style="width:35%">${creditAsOn}</td>
				<td style="width:35%">Rs ${totalBal}</td>
			</tr>
	</tbody>
</table>
</div>
<h5><b>Adjustment Details</b></h5>
<div class="col-md-12 content-pad-0 box-icon-body table-responsive" style="min-height:0px;">
	<table class="table table-striped table-bordered form-group"style="width:100%">
		<tbody>
			<tr>
				<th style="width:25%">MONTH-YEAR</th>
				<th style="width:25%">ADJUSTMENT AMC</th>
				<th style="width:25%">ADJUSTMENT TYPE</th>
				<th style="width:25%">REMARKS</th>
			</tr>
			<c:forEach var="CpfSlipAdjustmentDataDto" items="${cpfSlipAdjustmentDataList}">
			<tr>
				<td style="width:25%">${CpfSlipAdjustmentDataDto.month}</td>
				<td style="width:25%">${CpfSlipAdjustmentDataDto.adjustAmt}</td>
				<td style="width:25%">${CpfSlipAdjustmentDataDto.adjustType}</td>
				<td style="width:25%">${CpfSlipAdjustmentDataDto.remarks}</td>
			</tr>
			</c:forEach>
			<!-- <tr>
				<td style="width:25%">DEC-2019</td>
				<td style="width:25%">12345</td>
				<td style="width:25%">Employee Subscription</td>
				<td style="width:25%">Bonus Interest on CPF</td>
			</tr> -->
	</tbody>
</table>
</div>		
	<div style="text-align:right;">Asst Manager(CPF)</div>
		<h5><b>Note:</b></h5> 
		<h5><b>1.Please report any discrepency on written.</b></h5>
		<h5><b>2.In case of incomplete/wrong bio-data viz. spelling in name designation, date of birth etc.<b></h5>
		<h5><b>Please furnish correct and complete details in writting to CPF Division.</b></h5>
	</div>
</div>
	
	
<script type="text/javascript">

$(document).ready(function() {

	var option="";
	var fYear = (new Date()).getFullYear();
	
	for(var i=2013; i<=fYear; i++){
	    option = option + "<option value='"+i+ "'>"+i+"</option>";
	}
	$("#fromYear").append(option);					
	$("#toYear").append(option);
	
	
	$("#generateRepo").click(function(){
	$(this).prop("disabled",true);
	$(this).html('<i class="fa fa-spinner fa-spin"></i> CPF Slip Generation In Progress ...');
	$("#cpfSlipRepoForm").submit();

});
});

</script>	
</body>
</html>