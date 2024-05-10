<div class="epfo-container displayDiv">
<div class="row displayDiv">
	<c:if test="${not empty message}">
		<div class="col-md-12" style= "float:none">
			<div class="alert alert-success alert-dismissible" style="text-align: center;">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				${message}
			</div>
		</div>
	</c:if>
		
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
			<i class="fa fa-bars" aria-hidden="true" data-target=".ocs-track-status .panel-body, .ocs-track-status .toggle-icon" data-toggle="collapse"></i> &nbsp;&nbsp;&nbsp;Assigend Role User 
		</h5>
	
	</div>
<div class="panel-body in" style="height: auto;">
		<!-- <table class="table table-bordered table-responsive">
			<tr>
				<td><b style="color: red">No User Found...</b></td>
			</tr>
		</table>-->
<table id="roleassigneduserdatatable" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
  <thead>
    <tr>
      <th class="th-sm">Employee Number</th>
      <th class="th-sm">Employee Name</th>
      <th class="th-sm">Designation</th>
      <th class="th-sm">Present Place Of Posting</th>
      <th class="th-sm">Role Assigned Place</th>
      <th class="th-sm">Role Assigned</th>
      <th class="th-sm">Role Assigned Date (dd/mm/yyyy)</th>
      <th class="th-sm">Role Revoked</th>
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
	
	var urlVar = '${pageContext.request.contextPath}/role/getAsignedRoleUser';
	var $table = $('#roleassigneduserdatatable');
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
						data:'empId'
					},
					{
						data:'empName'
					},
					{
						data:'designation'
					},
					{
						data:'presLocation'
					},
					{
						data:'roleAssignedLocation'
					},
					{
						data:'assignedRole'
					},
					{
						data:'assignedDate'
					},
					{
						data:'regId',
						mRender: function(data, type, row){
							var str ='';
							//str += '<a href="javascript:confirmRemove(${pageContext.request.contextPath}/role/revokeRole?reqId='+data+');" target="_blank" >Revoke Role</a>';
							str += '<a href="${pageContext.request.contextPath}/role/revokeRole?reqId='+data+'">Revoke Role</a>';
							
							return str;
						}
					}
				]
			});
	}
	});


function confirmRemove(delUrl) 
    { alert(delUrl);
        if (confirm("Do you want to continue....")) 
        {
            document.location = delUrl;
        }
    }
</script>