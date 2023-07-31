<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="epfo-container displayDiv">
<div class="row displayDiv">
		
<div class="col-md-12 displayDiv">
	<noscript>
		<div class="alert alert-danger"	style="padding-left: 20px; padding-right: 0px; margin-left: 0px;">Please enable JavaScript in your browser</div>
	</noscript>

<div class="panel panel-info ocs-track-status" style="margin-left:5px">
	<div class="panel-heading p-3" style="background: #E6E6E6;">
		<h5 class="panel-title">
		<security:authorize access="isAuthenticated()">
			<security:authorize access="hasRole('CPF_ADMIN')">
				<i class="fa fa-bars" aria-hidden="true" data-target=".ocs-track-status .panel-body, .ocs-track-status .toggle-icon" data-toggle="collapse"></i> &nbsp;&nbsp;&nbsp;My Pending Request Status
			</security:authorize>
		</security:authorize>	
		</h5>
	
	</div>
<div class="panel-body in" style="height: auto;">
		
<table id="pendingclaimdatatable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
  <thead>
    <tr>
      <th class="th-sm">Request Id</th>
      <th class="th-sm">Date of Submission (yyyy/mm/dd)</th>
      <th class="th-sm">Submitted By (Name(CPF Code))</th>
      <th class="th-sm">Claim Received (yyyy/mm/dd)</th>
      <th class="th-sm">Action Taken By (Name - CPF Code)</th>
      <th class="th-sm">Remarks</th>
      <th class="th-sm">Claim Type</th>
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
	
	var urlVar = '${pageContext.request.contextPath}/claim/myAllPendingClaim';
	var $table = $('#pendingclaimdatatable');
	if($table.length){
			$table.dataTable({
				lengthMenu:[[5,10,15,-1],['5','10','15','ALL']],
				pageLength:5,
				order: [[3, 'desc']],
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
						data:'adminActionDate'
					},
					{
						data:'adminActionTakenBy'
					},
					{
						data:'remarks'
					},
					{
						data:'claimType'
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
							str += '<a href="${pageContext.request.contextPath}/claim/viewClaimReq?reqId='+data+'" target="_blank">View</a>';
							if('${userModel.roleName}'=='ADMIN' || '${userModel.roleName}'=='CPF_ADMIN'){
							str += '&nbsp;|&nbsp;';
							str += '<a href="${pageContext.request.contextPath}/claim/actClaimReq?reqType=otherReq&reqId='+data+'">Take Action</a>';
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