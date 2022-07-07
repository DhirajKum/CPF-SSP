<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<style type="text/css">
.labelwidth {
    display: block;
    text-align: left;
    width: 250px;    
}
</style>

<div class="container-fluid">
	
	<c:if test="${not empty message}">
			<div class="col-md-12" style= "float:none">
				<div class="alert alert-success alert-dismissible" style="text-align: center;">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>
	</c:if>
	<div class="row profile profile-card">
		<div class="col-md-12">
		<h5 class="main-heading">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="https://www.w3.org/TR/SVG/">
			<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
			</svg> 
			Role Mapping Form
		</h5>
		
		<div class="row marginT20">
			<div class="col-md-12">
		 	<sf:form action='${pageContext.request.contextPath}/role/saveRoleMappingRequest' method='POST' modelAttribute="roleMapping">
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="EMP_NAME" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Employee number <span class="red">*</span></b></label>
							<div class="col-sm-7">
							  	<sf:input path="EMPID" id="empNum"/>
								<a href="#" id="emproledetails" style="background-color:#007bff;color: #fff;padding-top: 4px;padding-left: 25px;padding-right: 25px;" class="btn btn-default">Get Details</a>
							</div>
						</div>
					</div>
				</div>
			
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="EMP_NAME" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Name of the member <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="EMPNAME" cssclass="form-control" id="empName" maxlength="20"/>
							<sf:hidden path="EMPNAME" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="DESIGNATION" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Designation <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="DESIGNATION" cssclass="form-control  form-control-sm" id="designation"  maxlength="20"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="Present_Location" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Employee PresentLocation <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="PRESLOCATION" id="presentLocation" cssClass="form-control" />
							</div>
						</div>
					</div>
					<%-- <div class="col-md-6">
						<div class="form-group row">
							<label for="Parent_Zone " cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Employee ParentZone  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="empParentZone" id="empParentZone" cssClass="form-control" />
							</div>
						</div>
					</div> --%>
				</div>
			
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="oldpassword" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Employee Role<span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:select cssclass="form-control" path="ROLEASSIGNED" id="empRole" onchange="changeval() ">
									<option value=""> ---Select---</option>
									 <option value="ADMIN">ADMIN</option>
									<option value="CPF_ADMIN">CPF ADMIN</option>
									<option value="SUPER_USER">SUPER USER</option>
								</sf:select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="AMOUNT" cssclass="col-sm-6 col-form-label" id="lblempUnit"><b>Employee Unit <span class="red">*</span></b></label>
							<label for="AMOUNT" cssclass="col-sm-6 col-form-label" id="lblparentZone"><b>Parent Zone <span class="red">*</span></b></label>
						
							<div class="col-sm-6">
								<sf:select cssclass="form-control" path="EMPUNIT" id="empUnit">
									<option value="" label="---Select---"/>
								</sf:select>
								<sf:select cssclass="form-control" path="PARENTZONE" id="parentZone">
									<option value="" label="---Select---"/>
								</sf:select>
							</div>
						</div>
					</div>			
				</div>
				<div style="text-align: center;">
					<button type="submit" class="btn btn-primary btn-sm" onclick="return Validate()">Submit</button>
					&nbsp;<button type="button" id="cancel" class="btn btn-primary btn-sm reset-form">Cancel</button>
				</div>
				</sf:form>
			</div>
		</div>
</div>
</div>
</div>

<script>
	$(document).ready(function() {
		
		$("#empName").prop("disabled", true);
		$("#designation").prop("disabled", true);
		$("#presentLocation").prop("disabled", true);
		$("#empParentZone").prop("disabled", true);
		$('#parentZone').hide();
		$('#empUnit').hide();
		$('#lblempUnit').hide();
		$('#lblparentZone').hide();
		//loadDropdown();
	});

	$("#cancel").on('click',function(event) {
		$('#empNum,#empName,#designation,#presentLocation,#empParentZone,#parentZone,#empUnit,#lblempUnit,#lblparentZone,#empRole').val('');
		$("#empNum").prop('readonly', false);
	});

	function changeval() {
		val = $('#empRole').val();

		switch (val) {
		case "ADMIN":
			$('#parentZone').hide();
			$('#empUnit').show();
			$('#lblparentZone').hide();
			$('#lblempUnit').show();
			loadDropdown();
			break;
		case "CPF_ADMIN":
			$('#empUnit').hide();
			$('#parentZone').show();
			$('#lblempUnit').hide();
			$('#lblparentZone').show();
			loadDropdown();
			break;
		case "SUPER_USER":
			$('#empUnit').hide();
			$('#parentZone').hide();
			$('#lblempUnit').hide();
			$('#lblparentZone').hide();
			break;
		}
	}

	function loadDropdown() {
		val = $('#empRole').val();

		if (val === 'ADMIN') {
			var urlVar = '${pageContext.request.contextPath}/role/getAllLocation';
			$.ajax({
				type : 'GET',
				url : urlVar,
				//data : input,
				async : false,
				contentType : "application/json",
				success : function(res) {
					if (res) {
						$("#empUnit").find('option').remove();
						var empUnit = $('#empUnit'), option = "";
						//locId.empty();
						option = option
								+ "<option value=''>---Select---</option>";
						for (var i = 0; i < res.length; i++) {
							option = option
									+ "<option value='"+res[i].allLocKey + "'>"
									+ res[i].allLocValue + "</option>";
						}
						empUnit.append(option);
					}
				}
			});
		} else if (val === 'CPF_ADMIN') {
			var urlVar1 = '${pageContext.request.contextPath}/role/getAllParentLocation';
			$
					.ajax({
						type : 'GET',
						url : urlVar1,
						//data : input,
						async : false,
						contentType : "application/json",
						success : function(res) {
							if (res) {
								$("#parentZone").find('option').remove();
								var parentZone = $('#parentZone'), option = "";
								//locId.empty();
								option = option
										+ "<option value=''>---Select---</option>";
								for (var i = 0; i < res.length; i++) {
									option = option
											+ "<option value='"+res[i].cpfParentLocKey + "'>"
											+ res[i].cpfParentLocValue
											+ "</option>";
								}
								parentZone.append(option);
							}
						}
					});
		}
	}

	$("#emproledetails").on('click',function(event) {

		$("#empNum").prop('readonly', true);
		var emp_num = $("#empNum").val().trim();
		/* var formData = {};
		formData['empNum'] = $("#empNum").val();
		var input = JSON.stringify(formData); */
		var urlVar = '${pageContext.request.contextPath}/role/getEmpRoleDetailsByEmpNum?empNum='+ $("#empNum").val().trim();
		var emp_num = $("#empNum").val().trim();
		$.ajax({
			type : 'GET',
			url : urlVar,
			//data : input,
			async : false,
			contentType : "application/json",
			success : function(res) {
				if (res) {
					setEmployeeData(res, emp_num);
				}
			}
		});
	});

	function setEmployeeData(res, emp_num) {
		var data = JSON.parse(JSON.stringify(res));
		if (data.empName == "noname") {
			alert("Employee no" + emp_num
					+ " Not Registered Kindly Register First");
		} else {
			$("#empNum").val(emp_num);
			//var middleName = (data.EMP_MIDDLE_NAME)!=null?data.EMP_MIDDLE_NAME:'';
			//var lastName = (data.EMP_LAST_NAME)!=null?data.EMP_LAST_NAME:'';

			$("#empName").val(data.empName);//+' '+middleName+' '+lastName);
			$("#designation").val(data.designation);
			$("#presentLocation").val(data.presentLocation);
			$("#empParentZone").val(data.empParentZone);
			//$("#phone").val(v[""]);
		}
	}

	function Validate() {
		var strRole = empRole.options[empRole.selectedIndex].value;
		var strUnit = empUnit.options[empUnit.selectedIndex].value;
		var strZone = parentZone.options[parentZone.selectedIndex].value;
		if (strRole == 0) {
			alert("Please Select Employee Role.....");
			return false;
		}

		else if (strRole == 'ADMIN') {
			if (strUnit == 0) {
				alert("Please Select Employee Unit.....");
				return false;
			}
		} else if (strRole == 'CPF_ADMIN') {
			if (strZone == 0) {
				alert("Please Select Employee Parent Zone.....");
				return false;
			}
		} else {
		}
	}
</script>