<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<div class="container-fluid">
	<div class="row profile profile-card">
		<div class="col-md-12">
			<h5 class="main-heading">
				<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor"	xmlns="https://www.w3.org/TR/SVG/">
					<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z" />
				</svg>
				Claim Request Assign To
			</h5>

			<div class="row marginT20">
				<div class="col-md-12">
					<sf:form action='${pageContext.request.contextPath}/claim/assignToClaimRequest?claimType=assignTo&reqId=${reqId}' method='POST' id="assignToForm" modelAttribute="assignToClaimDto">
						<security:authorize access="isAuthenticated()">
							<security:authorize access="hasRole('ADMIN')">
								<div class="row">
									<div class="col-md-4">
										<div class="form-group row">
											<label cssclass="col-sm-5 col-form-label" class="labelwidth" ><b>Unit Name <span class="red">*</span></b></label>
											<div class="col-sm-7">
												<sf:select cssclass="form-control" path="unitId" id="unitId">
													<option value="" label="---Select---" />
												</sf:select>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group row">
											<label cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Location Name <span class="red">*</span></b></label>
											<div class="col-sm-7">
												<sf:select path="locationId" cssclass="form-control" id="locationId">
													<option value="" label="---Select---" />
												</sf:select>
											</div>
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group row">
											<label cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Administrator Name <span class="red">*</span></b></label>
											<div class="col-sm-7">
												<sf:select cssclass="form-control" path="employeeId" id="employeeId">
													<option value="" label="---Select---" />
												</sf:select>
											</div>
										</div>
									</div>
									
								</div>
							</security:authorize>
						</security:authorize>

						<security:authorize access="isAuthenticated()">
							<security:authorize access="hasRole('CPF_ADMIN')">
								<div class="row">
									<div class="col-md-12">
										<div class="form-group row">
											<label cssclass="col-sm-5 col-form-label"><b>Administrator Name <span class="red">*</span></b></label>
											<div class="col-sm-6">
												<sf:select cssclass="form-control" path="cpfAdminId" id="cpfAdminId">
													<option value="" label="---Select---" />
												</sf:select>
											</div>
										</div>
									</div>
								</div>
							</security:authorize>
						</security:authorize>


						<div class="row">
							<div class="col-md-12">
								<div class="form-group row">
									<label for="remarks" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Remarks</b></label>
									<div class="col-sm-7">
										<sf:textarea path="remarks" cssclass="form-control"	id="remarks"></sf:textarea>
									</div>
								</div>
							</div>
						</div>

						<div style="text-align: center;">
							<button type="submit" class="btn btn-primary btn-sm" id="assignToButton">Assign To</button>
							&nbsp;
							<!-- <a href="" class="btn btn-primary btn-sm reset-form" disabled ="true">Back To User</a> -->
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {

		if ('${userRole}' === 'ADMIN') {
			var urlVar = '${pageContext.request.contextPath}/claim/getUnitCodeList';
			$.ajax({
					type : 'GET',
					url : urlVar,
					//data : input,
					async : false,
					contentType : "application/json",
					success : function(res) {
						if (res) {
							var unitId = $('#unitId'), option = "";
							$("#unitId").find('option').remove();

							for (var i = 0; i < res.length; i++) {
								option = option	+ "<option value='"+res[i].unitkey + "'>" + res[i].unitValue+ "</option>";
							}
							unitId.append(option);
						}
					}
				});
		} else if ('${userRole}' === 'CPF_ADMIN') {
			var urlVar = '${pageContext.request.contextPath}/claim/getCPFAdminByParentZone?parentZone='	+ ${parentZone}	+'&userRole=' + '${userRole}' + '&empNum=' + ${empNum};
			$.ajax({
					type : 'GET',
					url : urlVar,
					//data : input,
					async : false,
					contentType : "application/json",
					success : function(res) {
						if (res) {
							var cpfAdminId = $('#cpfAdminId'), option = "";
							$("#cpfAdminId").find('option').remove();
	
							for (var i = 0; i < res.length; i++) {
								option = option	+ "<option value='"+res[i].cpfAdminId + "'>"+ res[i].cpfAdminName + "</option>";
							}
							cpfAdminId.append(option);
						}
					}
				});

		}

	});

	$("#unitId").on("change", function() {
		var unitid = $("#unitId").val();
		var urlVar = '${pageContext.request.contextPath}/claim/getLocationByUnit?unitId='+ unitid.trim();
		$.ajax({
				type : 'GET',
				url : urlVar,
				//data : input,
				async : false,
				contentType : "application/json",
				success : function(res) {
					if (res) {
						$("#locationId").find('option').remove();
						var locId = $('#locationId'), option = "";
						//locId.empty();
						option = option	+ "<option value=''>---Select---</option>";
						for (var i = 0; i < res.length; i++) {
							option = option	+ "<option value='"+res[i].locationKey + "'>" + res[i].locationDesc	+ "</option>";
						}
						locId.append(option);
					}
				}
			});
	});

	$("#locationId").on("change",function() {
		var locId = $("#locationId").val();
		var urlVar = '${pageContext.request.contextPath}/claim/getAdminByLocation?locId='+ locId.trim() + '&empNum='+ ${empNum};
		$.ajax({
				type : 'GET',
				url : urlVar,
				//data : input,
				async : false,
				contentType : "application/json",
				success : function(res) {
					if (res) {
						$("#employeeId").find('option').remove();
						var empId = $('#employeeId'), option = "";
						option = option	+ "<option value=''>---Select---</option>";
						for (var i = 0; i < res.length; i++) {
							option = option	+ "<option value='"+res[i].adminId+ "'>" + res[i].adminId + " - " + res[i].adminName + "</option>";
						}
						empId.append(option);
					}
				}
			});
	});

	/* function fillRemarks(){
	 var val=$("#remarks").val();
	 var finalVal= $.trim(val);
	 if(finalVal.length<=0){
	 alert("Plaese Input Remarks!..... ");
	 $("#remarks").val("");
	 $("#remarks").focus();
	 return false
	 }
	 else{
	 return true;
	 }
	 } */

	$('#assignToForm').submit(function() {

		if ($("#remarks").val().trim().length > 0) {
			$("#assignToButton").prop("disabled", true);
			$("#assignToButton")
					.html(
							'<i class="fa fa-spinner fa-spin"></i>AssignTo in progress ...');
			return true;
		} else {
			alert("Kindly give your remarks ...!!!");
			$("#remarks").focus();
			return false;
		}
	});
</script>
