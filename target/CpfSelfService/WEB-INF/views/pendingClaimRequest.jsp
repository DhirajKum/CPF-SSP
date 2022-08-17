<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<security:authorize access="isAuthenticated()">
			<security:authorize access="hasRole('USER')">
				<i class="fa fa-bars" aria-hidden="true" data-target=".ocs-track-status .panel-body, .ocs-track-status .toggle-icon" data-toggle="collapse"></i> &nbsp;&nbsp;&nbsp;Request Status
			</security:authorize>
			<security:authorize access="hasRole('ADMIN')">
				<c:if test="${reqType == 'myReq'}">
					<i class="fa fa-bars" aria-hidden="true" data-target=".ocs-track-status .panel-body, .ocs-track-status .toggle-icon" data-toggle="collapse"></i> &nbsp;&nbsp;&nbsp;My Request Status
				</c:if>
				<c:if test="${reqType == 'otherReq'}">
					<i class="fa fa-bars" aria-hidden="true" data-target=".ocs-track-status .panel-body, .ocs-track-status .toggle-icon" data-toggle="collapse"></i> &nbsp;&nbsp;&nbsp;Pending Request Status
				</c:if>
			</security:authorize>
			<security:authorize access="hasRole('CPF_ADMIN')">
				<c:if test="${reqType == 'myReq'}">
					<i class="fa fa-bars" aria-hidden="true" data-target=".ocs-track-status .panel-body, .ocs-track-status .toggle-icon" data-toggle="collapse"></i> &nbsp;&nbsp;&nbsp;My Request Status
				</c:if>
				<c:if test="${reqType == ''}">
					<i class="fa fa-bars" aria-hidden="true" data-target=".ocs-track-status .panel-body, .ocs-track-status .toggle-icon" data-toggle="collapse"></i> &nbsp;&nbsp;&nbsp;My Pending Request Status
				</c:if>
				<c:if test="${reqType == 'otherReq'}">
					<i class="fa fa-bars" aria-hidden="true" data-target=".ocs-track-status .panel-body, .ocs-track-status .toggle-icon" data-toggle="collapse"></i> &nbsp;&nbsp;&nbsp;Pending Request Status
				</c:if>
			</security:authorize>
		</security:authorize>	
		</h5>
	
	</div>
<div class="panel-body in" style="height: auto;">
		<!-- <table class="table table-bordered table-responsive">
			<tr>
				<td><b style="color: red">Claim Record Not Found</b></td>
			</tr>
		</table>-->
<table id="pendingclaimdatatable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
  <thead>
    <tr>
      <th class="th-sm">Request Id</th>
      <th class="th-sm">Date of Submission</th>
      <th class="th-sm">Submitted By</th>
      <th class="th-sm">Designation</th>
      <th class="th-sm">Claim Arrived</th>
      <th class="th-sm">Action Taken By</th>
      <th class="th-sm">Remarks</th>
      <th class="th-sm">Invoice No</th>
      <th class="th-sm">Sanction Amount</th>
      <th class="th-sm">Status</th>
      <th class="th-sm">Action</th>
    </tr>
  </thead>
</table>							
</div>
</div>
</div>
</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
	
	var urlVar = '${pageContext.request.contextPath}/claim/allPendingClaim?reqType=${reqType}';
	var $table = $('#pendingclaimdatatable');
	if($table.length){
			$table.dataTable({
				lengthMenu:[[5,10,15,-1],['5','10','15','ALL']],
				pageLength:5,
				ajax:{
					url:urlVar,
					dataSrc:''
				},
				columns:[
					{
						data:'requestId'
					},
					{
						data:'claimSubmittedDate'
					},
					{
						data:'claimSubmittedBy'
					},
					{
						data:'designation'
					},
					{
						data:'adminActionDate'
					},
					{
						data:'adminActionTakenBy'
					},
					{
						data:'remarks'
					},
					{
						data:'invoiceNo'
					},
					{
						data:'sancAmount'
					},
					{
						data:'status'
					},
					{
						data:'requestId',
						mRender: function(data, type, row){
							var str ='';
							str += '<a href="${pageContext.request.contextPath}/claim/claimHistoryTrail?reqId='+data+'" target="_blank">History</a>';							
							str += '&nbsp;|&nbsp;';
							if(row.statusCode=='0'){
								str += '<a href="${pageContext.request.contextPath}/claim/raiseClaimReq?reqId='+data+'">Re-Apply</a>';
							}else{
								str += '<a href="${pageContext.request.contextPath}/claim/viewClaimReq?reqId='+data+'" target="_blank">View</a>';
							}
							if('${userModel.roleName}'=='ADMIN' || '${userModel.roleName}'=='CPF_ADMIN'){
								if('${reqType}'!='myReq'){
								str += '&nbsp;|&nbsp;';
								str += '<a href="${pageContext.request.contextPath}/claim/actClaimReq?reqType=${reqType}&reqId='+data+'">Take Action</a>';
								}
							}
							return str;
						}
					}
				]
			});
	}
	});

	/* $(document).ready(function() {
		$('#approvedclaimdatatable').dataTable();
	}); */
</script>