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
			<i class="fa fa-bars" aria-hidden="true" data-target=".ocs-track-status .panel-body, .ocs-track-status .toggle-icon" data-toggle="collapse"></i> &nbsp;&nbsp;&nbsp;Complete Request Status
		</h5>
	
	</div>
<div class="panel-body in" style="height: auto;">
		<!-- <table class="table table-bordered table-responsive">
			<tr>
				<td><b style="color: red">Claim Record Not Found</b></td>
			</tr>
		</table>-->
<table id="completedclaimdatatable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
  <thead>
    <tr>
      <th class="th-sm">Request Id</th>
      <th class="th-sm">Date of Submission (yyyy/mm/dd)</th>
      <th class="th-sm">Submitted By</th>
      <th class="th-sm">Date Of Action (yyyy/mm/dd)</th>
      <th class="th-sm">Action Taken By</th>
      <th class="th-sm">Remarks</th>
      <th class="th-sm">Invoice No</th>
      <th class="th-sm">Sanction Amount</th>
      <th class="th-sm">Status</th>
      <th class="th-sm">Action</th>
    </tr>
  </thead>
  <!-- <tbody>
    <tr>
      <td>00111</td>
      <td>01-10-2020</td>
      <td>Ram</td>
      <td>02-10-2020</td>
      <td>AdminUser</td>
      <td>Pending at admin</td>
      <td><a href="">Take Action</a></td>
    </tr>
    </tbody> -->
</table>							
</div>
</div>
</div>
</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
	
	var urlVar = '${pageContext.request.contextPath}/claim/allCompletedClaim';
	var $table = $('#completedclaimdatatable');
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
							str += '<a href="${pageContext.request.contextPath}/claim/viewClaimReq?reqId='+data+'" target="_blank">View</a>';
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