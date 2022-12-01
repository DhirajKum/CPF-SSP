<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core"%>

<style type="text/css">
.labelwidth {
    display: block;
    text-align: left;
    width: 250px;
}
</style>

<div class="container-fluid">
	<div class="row profile profile-card">
<!-- <div class="row profile profile-card"> -->
	<c:if test="${not empty message}">
			<div class="col-md-12" style= "float:none" id="message">
				<div class="alert alert-success alert-dismissible" style="text-align: center;">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
				</div>
			</div>
		</c:if>
		<c:if test="${not empty validationMessage}">
			<div class="col-md-12" style= "float:none" id="validationMessage">
				<div class="alert alert-success alert-dismissible" style="text-align: center;">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${validationMessage}
				</div>
			</div>
		</c:if>
	
		<div class="col-md-12">
		<div class="row">
		<h5 class="main-heading">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="https://www.w3.org/TR/SVG/">
			<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
			</svg> 
			Claim Request
		</h5>
		<div class="row col-sm-12">
				<small class="form-text text-muted paddingL40 main-heading"><b>Claim Request Id : ${actClaimDto.REQUEST_ID}</b></small>
		</div>
		</div>
		<div class="row marginT20" style="margin-left:-5px">
			<div class="col-md-12">
			  <sf:form action='${pageContext.request.contextPath}/claim/updateClaimRequest?reqType=${reqType}&claimReq=approve&reqId=${reqId}' method='POST' modelAttribute="actClaimDto" id="actClaimForm" onsubmit="this.js_enabled.value=1;return true;">
				<noscript>
					<div id="noJS" class="alert alert-danger" style="padding-left: 20px; padding-right: 0px; margin-left: 0px;">Please enable JavaScript in your browser</div>
				</noscript>
				<div class="row">
				<div class="col-md-3">
				<div class="form-group row">
					<label for="oldpassword" class="col-form-label"><b>Claim applied for  <span class="red">*</span></b></label>
				</div>
				</div>
					
				<div class="col-sm-9" id="claimAppliedFor">
				<div class="form-check-inline">
				  <label class="form-check-label">
					<sf:radiobutton cssclass="form-check-input" path="CLAIM_APPLIED_FOR" value="CpfFinalSettlement"  id="cpffinal"/> CPF Final Settlement
				  </label>
				</div>
				<div class="form-check-inline">
				  <label cssclass="form-check-label">
					<sf:radiobutton cssclass="form-check-input" path="CLAIM_APPLIED_FOR" value="CpfPartFinalWithdrawal"  id="cpfpartfinal" />CPF Part final Withdrawal 
				  </label>
				</div>
				<div class="form-check-inline">
				  <label cssclass="form-check-label">
					<sf:radiobutton cssclass="form-check-input" path="CLAIM_APPLIED_FOR"  value="90%Withdrawal"  id="cpfwdrl" /> 90% Withdrawal
				  </label>
				</div>
				<div class="form-check-inline">
				  <label cssclass="form-check-label">
					<sf:radiobutton cssclass="form-check-input" path="CLAIM_APPLIED_FOR"  value="TempAdv" id="cpftmpadv" /> Temp. Adv.
				  </label>
				</div>	
				<div class="row col-sm-12">
				<small class="form-text text-muted paddingL40">
					Tick whichever is applicable
				</small>
				</div>	
				</div>	
				</div>
				</br>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="EMP_NAME" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Name of the member <span class="red">*</span></b></label>
							<div class="col-sm-7">	
								  <sf:input path="EMP_NAME" id="empName" cssClass="form-control" maxlength="20"  />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="DESIGNATION" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Designation <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="DESIGNATION" cssclass="form-control  form-control-sm" id="designation"  maxlength="20" />
				
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="FATHER_HUSBAND_NAME" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Father's Name/Husband's Name <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="FATHER_HUSBAND_NAME" cssclass="form-control" id="fatherName"  maxlength="20" />			
			
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="DATE_OF_BIRTH" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Date of Birth  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<input type="text" name="DATE_OF_BIRTH" class="form-control  form-control-sm" id="dob"  value="${dob}" maxlength="30" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="BASIC" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Basic pay <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="BASIC" cssclass="form-control" id="basic"  maxlength="20" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="CPF_ACCOUNT_NUMBER" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>C.P.F Account No.  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="CPF_ACCOUNT_NUMBER" cssclass="form-control  form-control-sm" id="cpfAccountNumber"  maxlength="20" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="UAN" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Universal Account Number(UAN)</b></label>
							<div class="col-sm-7">
								<sf:input path="UAN" cssclass="form-control" id="uan" maxlength="20" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="STAFF_CODE" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Staff Code  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="STAFF_CODE" cssclass="form-control  form-control-sm" id="staffCode"  maxlength="20"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="DOJ_FCI" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Date of joining the FCI <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<input type="text" name="DOJ_FCI" class="form-control" id="dojFci"  value="${dojFci}" maxlength="30" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="RETIREMENT_DATE" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Retirement/Date of leaving service<span class="red">*</span></b></label>
							<div class="col-sm-7">
								<input type="text" name="RETIREMENT_DATE" class="form-control  form-control-sm" id="retirementDate" placeholder="" value="${retirementDate}" maxlength="30" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="PAN" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Permanent Account NO.(PAN)</b></label>
							<div class="col-sm-7">
								<sf:input path="PAN" cssclass="form-control" id="pan"  maxlength="20" />
							</div>
							<div cssclass="col-sm-12">
							<small class="form-text text-muted paddingL40">
								(Only in case of service less than 5 years)
							</small>
							</div>							
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="PRESENT_LOCATION" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Place of posting  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="PRESENT_LOCATION" cssclass="form-control  form-control-sm" id="placepost" maxlength="20" />
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Purpose of CPF Part withdrawal/Temporary Advance <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:select cssclass="form-control" path="PURPOSE" id="purpose" >
									<sf:option value="">select</sf:option>
									<sf:option value="HOUSE">Housing Loan/Purchase of site/House/Flat or for Construction/Addition, alteration in existing house</sf:option>
									<sf:option value="ILL">Illness of member/family</sf:option>
									<sf:option value="MARRIAGE">Marriage of Self/Son/Daughter or Dependent brother/sister</sf:option>
									<sf:option value="HigherEd">For Higher Education of Children i.e. Post Matriculation/Diploma</sf:option>
									<sf:option value="1YBRETIREMENT">One year before retirement</sf:option>
									<sf:option value="OTHER">Others(Please specify)</sf:option>
								</sf:select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="AMOUNT" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Amount(in RS.) <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="AMOUNT" cssclass="form-control" id="amount"  maxlength="15"/>
								<label><sf:checkbox path="PERMISSIBLE_AMOUNT" id="perAmount"/>&nbsp; Max Permissible Amount(in RS.)</label>
							</div>
						</div>
					</div>
										
				</div>
				<div class="row marginT10">	

					<div class="col-md-6">
						<div class="form-group row">
							<label for="INSTALLMENT_NUMBER" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>No. of installments in which advance is to be recovered <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="INSTALLMENT_NUMBER" cssclass="form-control  form-control-sm" id="installmentNo" maxlength="20" />
							</div>
						</div>

					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="LAST_DRAWN_ADVANCE" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Particular of Temporary advance last drawn, if  any</b></label>
							<div class="col-sm-7">
								<sf:input path="LAST_DRAWN_ADVANCE" cssclass="form-control" id="lastDrawnAdvance"  maxlength="20" />
							</div>
						</div>
					</div>	
				</div>
				
				<div class="row marginT10">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="ADVANCE_AMOUNT" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Amount of advance</b></label>
							<div class="col-sm-7">
								<sf:input path="ADVANCE_AMOUNT" cssclass="form-control  form-control-sm" id="advanceAmount"  maxlength="20" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="OUTSTANDING_BAL" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Balance outstanding</b></label>
							<div class="col-sm-7">
								<sf:input path="OUTSTANDING_BAL" cssclass="form-control  form-control-sm" id="outstandingBal" maxlength="20" />
							</div>
						</div>
					</div>
				</div>				
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="AMOUNT_REPAID" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Amount Re-paid</b></label>
							<div class="col-sm-7">
								<sf:input path="AMOUNT_REPAID" cssclass="form-control" id="amountRepaid"  maxlength="20" />
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="AMOUNT_90PARTFINAL_BEF_RETR" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Amount of 90% Part Final drawn previously before retirement, if  any</b></label>
							<div class="col-sm-7">
								<sf:input path="AMOUNT_90PARTFINAL_BEF_RETR" cssclass="form-control" id="amount90Partfinal" maxlength="20" />
							</div>
						</div>
					</div>
				</div>				
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="MOBILE_NUMBER" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Mobile Number  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="MOBILE_NUMBER" cssclass="form-control  form-control-sm" id="mobileNo"  maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Caste Dispute <span class="red">*</span></b></label>
							<div class="col-sm-7">
									<sf:select cssclass="form-control" path="CASTE_DISPUTE_CERT" id="casteDispute" >
										<sf:option value="">SELECT</sf:option>
										<sf:option value="N">NO</sf:option>
										<sf:option value="Y">YES</sf:option>
									</sf:select>
							</div>
						</div>
					</div>
				</div>		
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="AMOUNT_SANCTION" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Amount Sanction <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="AMOUNT_SANCTION" cssclass="form-control  form-control-sm" id="amountSanc" maxlength="10" onkeypress="return isNumber(event)"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="" cssclass="col-sm-5 col-form-label" class="labelwidth"></label>
							<div class="col-sm-7">
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Uploaded KYC Document</b></label>
							<a href="${pageContext.request.contextPath}/claim/downloadCpfDoc?pathId=${actClaimDto.kycFilePath}&fileType=1" class="col-sm-7" target="_blank">${actClaimDto.kycFileName}</a>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>User Uploaded Other Documents</b></label>
							<div class="col-sm-7">
							<c:forEach var="userOtherFile" items="${actClaimDto.userOtherFiles}">
								<a href="${pageContext.request.contextPath}/claim/downloadCpfDoc?pathId=${userOtherFile.value}&fileType=3" target="_blank">${userOtherFile.key}</a></br>
							</c:forEach>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="col-md-6">
						<div class="form-group row">
							<label for="" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Uploaded Other Documents</b></label>
							<div class="col-sm-7">
							<c:forEach var="otherFile" items="${actClaimDto.otherFiles}">
								<a class="col-sm-12" href="${pageContext.request.contextPath}/claim/downloadCpfDoc?pathId=${otherFile.value}&fileType=2" target="_blank">${otherFile.key}</a></br>
							</c:forEach>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row">
				<div class="col-md-12">
					<h5><b><div id='msg' style="text-align: center; font-weight: bold; color:red"></div></h5>
				</div>
				</div>
				
				<div class="row" id="docUpload">
				<div class="col-md-6">
					<div class="form-group row">
						<label cssClass="col-sm-5 col-form-label" class="labelwidth"><b>Upload Other Documents</b></label>
						<input type="file" class="col-sm-7" id="files" name="files" multiple data-validation="ckeckFileFormat ckeckFileSize required"/>
					</div>
					<small id="docUploadHelp" class="form-text text-muted" style="text-align: center;">Note: You can upload multiple file with +Ctrl key</br>File should be in pdf, png, jpg and jpeg format only.</br>File maximum size should be 5 Mb.</small>
				</div>
				<div class="col-md-6">
					<div class="form-group row">
						<input type="button" class="col-sm-5" id="uploadOtherDoc" value="Upload File"/>
						<div class="col-sm-7"></div>
					</div>
				</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-10">
						<div class="checkbox">
							<label><sf:checkbox path="DEC_NOT_EMP_TWOMONTH" id="decNotEmpTwoMonth" /> The member here by declares that he has not been employed for two month</label>
						</div>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-12">
						<div class="checkbox">
							<label><sf:checkbox path="EMP_DECLARATION" id="empDec" /> Certified that the particulars are true to the best of my knowledge</label>
						</div>
					</div>
				</div>						
				<div class="form-group row">
					<div class="col-sm-12">
						<div class="checkbox">
							<label><sf:checkbox path="EMP_ACCEPTANCE" id="empAccept"/> In case the amount is used for any purpose other than stated above, I am liable to return the entire amount with penal interest.</label>
						</div>
					</div>
				</div>
				
				<div class="row">
				<div class="col-md-12">
						<div class="form-group row">
							<label for="remarks" cssclass="col-sm-2 col-form-label"><b>Remarks <span class="red">*</span></b></label>
							<div class="col-sm-10">
								<sf:textarea path="remarks" cssclass="form-control" id="remarks"></sf:textarea>
							</div>
						</div>
					</div>
				</div>
			    <div style="text-align: center;">
			    	
					<c:set var = "claimSubmittedBy" scope = "session" value = '${actClaimDto.CLAIM_SUBMITTED_BY}'/>
			    	<c:set var = "loginEmpId" scope = "session" value = '${userModel.empNum}'/>
			    	
					<c:if test="${claimSubmittedBy != userModel.empNum}">
						<button type="submit" id="claimApprove" class="btn btn-primary btn-sm">Approve</button>
					</c:if>
					
					&nbsp;<a href="javascript:rejectCall();" class="btn btn-primary btn-sm reset-form" onclick="return fillRemarks();">Reject</a>&nbsp;
					<security:authorize access="isAuthenticated()">
						<security:authorize access="hasAnyRole('ADMIN','CPF_ADMIN')">
							<a href="javascript:assignCall();" class="btn btn-primary btn-sm reset-form">Assign To</a>
						</security:authorize>
					</security:authorize>
				</div>
				<sf:hidden path="locId"/>
				<sf:hidden path="parentZone"/>
				<sf:hidden path="CLAIM_SUBMITTED_BY"/>
				<sf:hidden path="claimSubmittedDate"/>
				<input type="hidden" name="js_enabled" value="0">
				
			</sf:form>
		</div>
	</div>
</div>
</div>
</div>
<script type="text/javascript">

$(document).ready(function() {

	var radioValue=$("#claimAppliedFor input:radio:checked").val();
	var purpose = $
	var urlVar = '${pageContext.request.contextPath}/claim/getPurposeOfCPF?radioValue='+radioValue.trim();
	$.ajax({
		type : 'GET',
		url : urlVar,
		//data : input,
		async : false,
		contentType : "application/json",
		success : function(res) {
			if (res) {
				//$("#purpose").find('option').remove();
				var purpose=$('#purpose'), option="";
				option = option + "<option value=''>---Select---</option>";
	            for(var i=0; i<res.length; i++){
	                option = option + "<option value='"+res[i].cpfPurposeKey + "'" +(res[i].cpfPurposeKey === '${actClaimDto.PURPOSE}' ? " selected='selected'" : "") + "'>"+res[i].cpfPurposeValue + "</option>";
	            }
	            purpose.append(option);					
			}
		}
	});
	
  $("#noJS").hide();
  jQuery('#cpffinal').prop("disabled", true);
  jQuery('#cpfpartfinal').prop("disabled", true);
  jQuery('#cpfwdrl').prop("disabled", true);
  jQuery('#cpftmpadv').prop("disabled", true);
  $("#empName").prop("disabled", true);   
  $("#designation").prop("disabled", true); 
  $("#fatherName").prop("disabled", true); 
  $("#dob").prop("disabled", true); 
  $("#basic").prop("disabled", true); 
  $("#cpfAccountNumber").prop("disabled", true); 
  $("#staffCode").prop("disabled", true);
  $("#uan").prop("disabled", true); 
  $("#mobileNo").prop("disabled", true); 
  $("#dojFci").prop("disabled", true); 
  $("#retirementDate").prop("disabled", true); 
  $("#pan").prop("disabled", true); 
  $("#placepost").prop("disabled", true);  
  jQuery('#purpose').prop("disabled", true);
  $("#amount").prop("disabled", true); 
  $("#perAmount").prop("disabled", true); 
  $("#installmentNo").prop("disabled", true); 
  $("#lastDrawnAdvance").prop("disabled", true); 
  $("#advanceAmount").prop("disabled", true); 
  $("#outstandingBal").prop("disabled", true); 
  $("#amountRepaid").prop("disabled", true); 
  $("#amount90Partfinal").prop("disabled", true); 
  jQuery('#decNotEmpTwoMonth').prop("disabled", true);
  jQuery('#empDec').prop("disabled", true);
  jQuery('#empAccept').prop("disabled", true);
  
  if('${userModel.roleName}' === 'CPF_ADMIN'){
	jQuery('#casteDispute').prop("disabled", true);
  }
  if('${userModel.roleName}' === 'ADMIN'){
  	jQuery('#amountSanc').prop("disabled", true);
  }
	
});	

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && ((charCode < 48) || (charCode > 57))) {
        return false;
    }
    return true;
}

$('#actClaimForm').submit(function (){
	//if($('#casteDispute').is(":checked")){
	 //var fileuploaded = '${actClaimDto.otherFiles}';
		//if(fileuploaded.length>0){
		
		if($("#casteDispute").val()!=''){
			if('${userModel.roleName}' === 'CPF_ADMIN' && $("#amountSanc").val()!="" && parseInt($("#amountSanc").val().trim())!=0){
				if($("#remarks").val().trim().length>0){
					$("#claimApprove").prop("disabled",true);
					$("#claimApprove").html('<i class="fa fa-spinner fa-spin"></i>Approval in progress ...');
					return true;
				} else {
					alert("Kindly give your remarks ...!!!");
					$("#remarks").focus();
					return false;
				}
			} else if('${userModel.roleName}' === 'ADMIN'){
				if($("#remarks").val().trim().length>0){
					$("#claimApprove").prop("disabled",true);
					$("#claimApprove").html('<i class="fa fa-spinner fa-spin"></i>Approval in progress ...');
					return true;
				} else {
					alert("Kindly give your remarks ...!!!");
					$("#remarks").focus();
					return false;
				}
			} else {
				alert("Please provide Amount Sanction ...!!!");
				$("#amountSanc").focus();
				return false;
			}
		} else {
			alert("Kindly select caste dispute option ...!!!");
			return false;
		}
		
	/* }else{
			alert("Kindly tick caste dispute certificate and if required, upload it ...!");
			return false;
	} */
});

function rejectCall(){
	var remarks = $("#remarks").val();
	if($("#casteDispute").val()!=''){
	var casteDisp = $("#casteDispute").val().trim();
	//var result = confirm("Are you sure, you are going to reject the claim ?");
		if(confirm("Are you sure, you are going to reject the claim ?")){
			window.location = "${pageContext.request.contextPath}/claim/rejectClaimRequest?claimType=reject&reqId="+${reqId}+"&remark="+remarks+"&casteDisp="+casteDisp;
		}
	}else{
		alert("Kindly select caste dispute option ...!!!");
		return false;
	}
}

function assignCall(){
	var remarks = $("#remarks").val();
	window.location = "${pageContext.request.contextPath}/claim/assignClaimRequest?claimType=assign&reqId="+${reqId};
}

function fillRemarks() {
	var val = $("#remarks").val();
	var finalVal = $.trim(val);
	if (finalVal.length <= 0) {
		alert("Kindly give your remarks ...!!!");
		$("#remarks").val("");
		$("#remarks").focus();
		return false
	} else {
		return true;
	}
}

function getFileExtension(name) {
	var splitData = name.split(".");
	var returnFlag = false;
	var index = splitData.length;
	var extension = splitData[index - 1];
	var exteArr = [ "pdf", "png", "jpg", "jpeg" ];
	$.each(exteArr, function(i, j) {
		if (j == extension) {
			returnFlag = true;
		}
	})
	return returnFlag;
}

function getFileSize(file) {
	//var file = $el[0].files[0];
	if (file.size > 5242880) {
		return false;
	} else {
		return true;
	}
}

$("#uploadOtherDoc")
		.on(
				'click',
				function(event) {

					var filetype = true;
					var fd = new FormData();
					var totalfiles = document.getElementById('files').files.length;
					var radioValue = $(
							"#claimAppliedFor input:radio:checked").val();

					if (totalfiles > 0) {
						for (var index = 0; index < totalfiles; index++) {
							fd.append("files", document
									.getElementById('files').files[index]);
							if (getFileExtension(document
									.getElementById('files').files[index].name)
									&& getFileSize(document
											.getElementById('files').files[index])) {
								filetype = true;
							} else {
								filetype = false;
								break;
							}
						}
					}/*else{
							alert("Kindly upload at list one document.");
						} */

					if (filetype) {
						$
								.ajax({
									type : 'POST',
									url : 'multiUplodCpfDoc?${_csrf.parameterName}=${_csrf.token}&reqId=${reqId}&claimSubmittedBy=${actClaimDto.CLAIM_SUBMITTED_BY}&claimAppliedFor='
											+ radioValue,
									enctype : 'multipart/form-data',
									data : fd,
									processData : false,
									contentType : false,
									success : function(data, textStatus,
											xhr) {
										if (xhr.status == '200') {
											console
													.log('Upload Completed ...');
											window.location.href = '${pageContext.request.contextPath}/claim/actClaimReq?reqType=otherReq&reqId=${reqId}&uploadfiles='
													+ data
													+ ' Files uploaded successfully !!!';
										}
									}
								});
					} else {
						$('#msg')
								.html(
										"Kindly upload your file/s according to the given instructions !!!")
								.fadeIn('slow');
						$('#msg').delay(10000).fadeOut('slow');
					}

				});
</script>