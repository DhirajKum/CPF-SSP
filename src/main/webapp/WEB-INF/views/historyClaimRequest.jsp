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
			<security:authorize access="hasAnyRole('USER','ADMIN','CPF_ADMIN')">
				<i class="fa fa-bars" aria-hidden="true" data-target=".ocs-track-status .panel-body, .ocs-track-status .toggle-icon" data-toggle="collapse"></i> &nbsp;&nbsp;&nbsp;Claim History Trail
			</security:authorize>
		</security:authorize>	
		</h5>
	</div>
<div class="panel-body in" style="height: auto;">
<table id="claimhistorytraildatatable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
  <thead>
    <tr>
      <th class="th-sm">S.No.</th>
      <th class="th-sm">Request ID</th>
      <th class="th-sm">Action Date</th>
      <th class="th-sm">Action Taken By</th>
      <th class="th-sm">Action</th>
      <th class="th-sm">Remarks</th>
      <th class="th-sm">Status</th>
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
	var i=1;
	var urlVar = '${pageContext.request.contextPath}/claim/completeHistoryTrail?reqId='+${reqId};
	var $table = $('#claimhistorytraildatatable');
	if($table.length){
			$table.dataTable({
				lengthMenu:[[5,10,15,-1],['5','10','15','ALL']],
				searching:false,
				lengthChange:false,
				ordering:false,
				info:false,
				paging:false,
				ajax:{
					url:urlVar,
					dataSrc:''
				},
				columns:[
					{
						mRender: function(data, type, full, meta) {
        				return i++;
      					}
      				},
					{
						data:'requestId'
					},
					{
						data:'actionDate'
					},
					{
						data:'actionTakenBy'
					},
					{
						data:'action'
					},
					{
						data:'remarks'
					},
					{
						data:'status'
					}
				]
			});
	}
	});

	/* $(document).ready(function() {
		$('#approvedclaimdatatable').dataTable();
	}); */
</script>