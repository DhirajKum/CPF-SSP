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
			<div class="alert alert-danger alert-dismissible" style="text-align: center;">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				${message}
			</div>
		</div>
	</c:if>
	<div class="row profile profile-card">
		<div class="col-md-12">
		<h5 class="main-heading" style="margin-left:-5px">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="https://www.w3.org/TR/SVG/">
			<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
			</svg> 
			Claim Request
		</h5>
		
		<div class="row marginT20" style="margin-left:-5px">
			<div class="col-md-12">
			 <sf:form action='${pageContext.request.contextPath}/claim/saveClaimRequest' method='POST' modelAttribute="claimData" id="claimForm" onsubmit="this.js_enabled.value=1;return true;">
			 	<noscript>
					<div id="noJS" class="alert alert-danger" style="padding-left: 20px; padding-right: 0px; margin-left: 0px;">Please enable JavaScript in your browser</div>
				</noscript>
			 	<div class="form-group row" style="margin-left:-30px">
					<label class="col-sm-2 col-form-label" style="max-width: 20%;"><b>Claim applied for  <span class="red">*</span></b></label>
					<div class="col-sm-10" id="claimAppliedFor">
						<div class="form-check-inline">
						  <label cssclass="form-check-label">
							<sf:radiobutton cssclass="form-check-input" path="CLAIM_APPLIED_FOR" value="CpfFinalSettlement" id="rd1" onclick="installEnbDsb();" /> CPF Final Settlement
						  </label>
						</div>
						<div class="form-check-inline">
						  <label cssclass="form-check-label">
							<sf:radiobutton cssclass="form-check-input" path="CLAIM_APPLIED_FOR" value="CpfPartFinalWithdrawal"  id="rd2" onclick="installEnbDsb();"/>CPF Part final Withdrawal 
						  </label>
						</div>
						<div class="form-check-inline">
						  <label cssclass="form-check-label">
							<sf:radiobutton cssclass="form-check-input" path="CLAIM_APPLIED_FOR"  value="90%Withdrawal"  id="rd3"  onclick="installEnbDsb();"/> 90% Withdrawal
						  </label>
						</div>
						<div class="form-check-inline">
						  <label cssclass="form-check-label">
							<sf:radiobutton cssclass="form-check-input" path="CLAIM_APPLIED_FOR"  value="TempAdv"  id="rd4"  onclick="installEnbDsb();"/> Temp. Adv.
						  </label>
						</div>	
						<div class="row col-sm-12">
						<small class="form-text text-muted paddingL40">
							Tick the applicable
						</small>
						</div>	
					</div>	
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="empName" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Name of the member <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="EMP_NAME" cssclass="form-control" id="empName" maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="designation" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Designation <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="DESIGNATION" cssclass="form-control  form-control-sm" id="designation"  maxlength="20"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="fatherName" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Father's Name/Husband's Name <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="FATHER_HUSBAND_NAME" cssclass="form-control" id="fatherName"  maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="dob" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Date of Birth  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<input type="text" name="DATE_OF_BIRTH" cssclass="form-control" id="dob" value="${dob}" maxlength="30" style="font-size:small; "  />
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="basic" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Basic pay <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="BASIC" cssclass="form-control" id="basic" maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="cpfAccountNumber" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>C.P.F Account No.  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="CPF_ACCOUNT_NUMBER" cssclass="form-control  form-control-sm" id="cpfAccountNumber"  maxlength="20"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="uan" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Universal Account Number(UAN)</b></label>
							<div class="col-sm-7">
								<sf:input path="UAN" cssclass="form-control" id="uan"  maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="staffCode" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Staff Code  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="STAFF_CODE" cssclass="form-control  form-control-sm" id="staffCode"  maxlength="20"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="dojFci" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Date of joining the FCI <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<input type="text" name="DOJ_FCI" cssclass="form-control" id="dojFci" placeholder="" value="${dojFci}" maxlength="30"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="retirementDate" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Retirement/Date of leaving service<span class="red">*</span></b></label>
							<div class="col-sm-7">
								<input type="text" name="RETIREMENT_DATE" cssclass="form-control  form-control-sm" id="retirementDate" placeholder="" value="${retirementDate}" maxlength="30"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="pan" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Permanent Account NO.(PAN)</b><small class="form-text text-muted paddingL40">(Only in case of service less than 5 years)</small></label>
							<div class="col-sm-7">
								<sf:input  path="PAN" cssclass="form-control" id="pan"  maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="placepost" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Place of posting  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input  path="PRESENT_LOCATION" cssclass="form-control  form-control-sm" id="placepost" maxlength="20"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="purpose" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Purpose of CPF Part withdrawal/Temporary Advance <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:select cssclass="form-control" path="PURPOSE" id="purpose">
									<option value="" label="---Select---"/>
								</sf:select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="amount" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Amount(in RS.) <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="AMOUNT" cssclass="form-control" id="amount" maxlength="10" onkeypress="return isNumber(event)"/>
								<label><sf:checkbox path="PERMISSIBLE_AMOUNT" id="perAmount"/>&nbsp; Max Permissible Amount(in RS.)</label>
							</div>
						</div>
					</div>
				</div>
				<div class="row marginT10">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="installmentNo" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>No. of installments in which advance is to be recovered <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="INSTALLMENT_NUMBER" cssclass="form-control  form-control-sm" id="installmentNo"  maxlength="3" onkeypress="return isNumber(event)"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="mobileNo" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Mobile Number  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="MOBILE_NUMBER" cssclass="form-control  form-control-sm" id="mobileNo"  maxlength="20"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row marginT10">	
					<div class="col-md-6">
						<div class="form-group row">
							<label cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Uploaded UAN Document with KYC</b></label>
							<a href="${pageContext.request.contextPath}/claim/downloadCpfDoc?pathId=${claimData.kycFilePath}&fileType=1" class="col-sm-7" target="_blank">${claimData.kycFileName}</a>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label cssclass="col-sm-5 col-form-label" class="labelwidth"><b>User Uploaded Other Documents(Salary/CPF slips etc.)</b></label>
							<div class="col-sm-7">
							<c:forEach var="userOtherFile" items="${claimData.userOtherFiles}">
								<a href="${pageContext.request.contextPath}/claim/downloadCpfDoc?pathId=${userOtherFile.value}&fileType=3" target="_blank">${userOtherFile.key}</a></br>
							</c:forEach>
							</div>
						</div>
					</div>
				</div>
				
				<div class="row" id="docUpload">
				<div class="col-md-6">
					<div class="form-group row">
						<label cssClass="col-sm-5 col-form-label" class="labelwidth"><b>User Upload Other Documents(Salary/CPF slips etc.)</b></label>
						<input type="file" class="col-sm-7" id="files" name="files" multiple data-validation="ckeckFileFormat ckeckFileSize required"/>
					</div>
					<div class="form-group row">
					    <label cssClass="col-sm-5 col-form-label" class="labelwidth"></label>
						<small cssClass="col-sm-7 col-form-label" id="docUploadHelp" class="form-text text-muted" style="text-align: left;"><b>Note</b>: You can upload multiple file with +Ctrl key</br>File should be in pdf, png, jpg and jpeg format only.</br>File maximum size should be 5 Mb.</small>
					</div>
				</div>
				<div class="col-md-6">
					<div class="form-group row">
						<input type="button" class="col-sm-5" id="uploadOtherDoc" value="Upload File"/>
						<div class="col-sm-7"></div>
					</div>
				</div>
				</div>
				
				<hr class="solid">
				<div class="row">
					<div class="col-md-6">
						<div class="checkbox">
							<label><sf:checkbox path="LAST_DRAWN_ADVANCE" id="lastDrawnAdvance"/><b>Particular of Temporary advance last drawn, if  any</b></label>
						</div>
					</div>
					</div>
					<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="advanceAmount" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Amount of advance</b></label>
							<div class="col-sm-7">
								<sf:input path="ADVANCE_AMOUNT" cssclass="form-control  form-control-sm" id="advanceAmount"  maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="outstandingBal" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Balance outstanding</b></label>
							<div class="col-sm-7">
								<sf:input path="OUTSTANDING_BAL" cssclass="form-control  form-control-sm" id="outstandingBal"  maxlength="20"/>
							</div>
						</div>
					</div>
				</div>				
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="amountRepaid" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Amount Re-paid</b></label>
							<div class="col-sm-7">
								<sf:input path="AMOUNT_REPAID" cssclass="form-control" id="amountRepaid"  maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="amount90Partfinal" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Amount of 90% Part Final drawn previously before retirement, if  any</b></label>
							<div class="col-sm-7">
								<sf:input path="AMOUNT_90PARTFINAL_BEF_RETR" cssclass="form-control" id="amount90Partfinal"  maxlength="20"/>
							</div>
						</div>
					</div>
				</div>				
				
				<div class="form-group row">
					<div class="col-sm-10">
						<div class="checkbox">
							<label><sf:checkbox path="DEC_NOT_EMP_TWOMONTH" id="decNotEmpTwoMonth"/> The member here by declares that he has not been employed for two months <b>(In Case of Final Payment only).</b></label>
						</div>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-sm-12">
						<div class="checkbox">
							<label><sf:checkbox path="EMP_DECLARATION" id="empDec"/> Certified that the particulars are true to the best of my knowledge and as per the provision of the Employees Provident Fund Scheme,1952.</label>
						</div>
					</div>
				</div>						
				<div class="form-group row">
					<div class="col-sm-12">
						<div class="checkbox">
							<label><sf:checkbox path="EMP_ACCEPTANCE" checked="checked" id="empAccept"/> In case the amount is used for any purpose other than stated above, I am liable to return the entire amount with penal interest.</label>
						</div>
					</div>
				</div>
				
				<div style="text-align: center;  padding-top: 10px; padding-bottom: 10px;">
				<button type="submit" id="saveClaim" class="btn btn-primary btn-sm" onclick="return finalSubmit()">Submit</button>&nbsp;<a href="" class="btn btn-primary btn-sm reset-form">Cancel</a>
				</div>
				<input type="hidden" name="js_enabled" value="0">
				</sf:form>
			</div>
		</div>
</div>
</div>
</div>

<script>		
$(document).ready(function() {
		
	var radioValue=$("#claimAppliedFor input:radio:checked").val();
	if(radioValue != undefined && radioValue != null){
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
	}
	
 	$("#noJS").hide();
	$("#empName").prop("readonly", true);   
	$("#designation").prop("readonly", true); 
	$("#fatherName").prop("readonly", true); 
	$("#dob").prop("readonly", true); 
	$("#basic").prop("readonly", true); 
	$("#cpfAccountNumber").prop("readonly", true); 
	$("#staffCode").prop("readonly", true); 
	$("#uan").prop("readonly", true); 
	$("#mobileNo").prop("readonly", true); 
	$("#dojFci").prop("readonly", true); 
	$("#retirementDate").prop("readonly", true); 
	$("#pan").prop("readonly", true); 
	$("#placepost").prop("readonly", true);
	//document.getElementById("rd1").checked = true;
	$('#advanceAmount').prop("readonly", true);
	//$("#advanceAmount").val('');
	$('#outstandingBal').prop("readonly", true);
	//$("#outstandingBal").val('');
	$('#amountRepaid').prop("readonly", true);
	//$("#amountRepaid").val('');
	$('#amount90Partfinal').prop("readonly", true);
	//$("#amount90Partfinal").val('');
	if(document.getElementById("rd1").checked || document.getElementById("rd2").checked){	
		$("#installmentNo").prop("disabled", true);
		$("#installmentNo").val('');
		if('${kycUpdate}' === '0'){
			$("#saveClaim").prop("disabled",true);
		}
	jQuery('#empAccept').prop("disabled", true);
	}
	$("#perAmount").prop("disabled", true);
});

document.oncontextmenu = rightClick;
function rightClick(clickEvent) {
    clickEvent.preventDefault();
    return false;
}

document.onkeydown = function(e) {
	if(event.keyCode == 123) {
		return false;
	}
	if(e.ctrlKey && e.shiftKey && e.keyCode == 'I'.charCodeAt(0)){
		return false;
	}
	if(e.ctrlKey && e.shiftKey && e.keyCode == 'J'.charCodeAt(0)){
		return false;
	}
	if(e.ctrlKey && e.keyCode == 'U'.charCodeAt(0)){
		return false;
	}
}

$('#claimForm').submit(function (){
	var radioValue=$("#claimAppliedFor input:radio:checked").val();
	if(radioValue != undefined && radioValue != null){
	if(radioValue==='CpfFinalSettlement'){
	if('${empStatus}'==='RESG'){
		if($('#decNotEmpTwoMonth').is(":checked")){
			if($('#empDec').is(":checked")){
				$("#saveClaim").prop("disabled",true);
				$("#saveClaim").html('<i class="fa fa-spinner fa-spin"></i>Claim Submission in progress ...');
				return true;
			}else{
				alert("Kindly tick, employee declaration checkbox ...!");
				return false;
			}
		}else{
			alert("Kindly tick, Member here by declares that he has not been employed for two month ...!");
			return false;
		}
	}else{
		if($('#empDec').is(":checked")){
			$("#saveClaim").prop("disabled",true);
			$("#saveClaim").html('<i class="fa fa-spinner fa-spin"></i>Claim Submission in progress ...');
			return true;
		}else{
			alert("Kindly tick, employee declaration checkbox ...!");
			return false;
		}
	}
	}else{
		if($('#empDec').is(":checked")){
			if((radioValue==='CpfPartFinalWithdrawal') || (radioValue==='TempAdv')){
				if($("#purpose").val()==''){
					alert("Kindly select 'Purpose of CPF' ...!");
					return false;
				}else{
					$("#saveClaim").prop("disabled",true);
					$("#saveClaim").html('<i class="fa fa-spinner fa-spin"></i>Claim Submission in progress ...');
					return true;	
				}
			}
		}else{
			alert("Kindly tick, employee declaration checkbox ...!");
			return false;
		}
	}
	}else{
		alert("Kindly check, 'Claim Applied For' option radio button ...!");
		return false;
	}

	/* if(!($('#perAmount').is(":checked"))){
		alert("Kindly Enter Amount ...");
		return false;
	} */
});


$("#perAmount").click(function (){
	if($(this).is(":checked")){
		$("#amount").prop("disabled", true);
		$("#amount").val('');
		/*var sancType = $("#purpose").val();
		if(sancType!=''){
		var urlVar = '${pageContext.request.contextPath}/claim/getMaxPermAmount?empId=${userModel.empNum}&sancType='+sancType;
		$.ajax({
			type : 'GET',
			url : urlVar,
			//data : input,
			async : false,
			contentType : "application/json",
			success : function(res) {
				if (res) {
										
				}
			}
		}); 
		}else{
			alert("To know Maximum Permissible Amount, kindly select 'Purpose of CPF' first !!!");
		}*/
	}else{
		$('#amount').prop("disabled", false);
	}
});

$("#lastDrawnAdvance").click(function (){
	if($(this).is(":checked")){
		$("#advanceAmount").prop("readonly", false);
		$('#outstandingBal').prop("readonly", false);
		$('#amountRepaid').prop("readonly", false);
		$('#amount90Partfinal').prop("readonly", false);
	}else{
		$('#advanceAmount').prop("readonly", true);
		//$("#advanceAmount").val('');
		$('#outstandingBal').prop("readonly", true);
		//$("#outstandingBal").val('');
		$('#amountRepaid').prop("readonly", true);
		//$("#amountRepaid").val('');
		$('#amount90Partfinal').prop("readonly", true);
		//$("#amount90Partfinal").val('');
	}
});

$("#claimAppliedFor input:radio").change(function (){
	
	var radioValue=$("#claimAppliedFor input:radio:checked").val();
	
	var urlVar = '${pageContext.request.contextPath}/claim/getPurposeOfCPF?radioValue='+radioValue.trim();
	$.ajax({
		type : 'GET',
		url : urlVar,
		//data : input,
		async : false,
		contentType : "application/json",
		success : function(res) {
			if (res) {
				$("#purpose").find('option').remove();
				var purpose=$('#purpose'), option="";
				//locId.empty();
				option = option + "<option value=''>---Select---</option>";
	            for(var i=0; i<res.length; i++){
	                option = option + "<option value='"+res[i].cpfPurposeKey+"'>"+res[i].cpfPurposeValue +"</option>";
	            }
	            purpose.append(option);					
			}
		}
	});
	
});

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && ((charCode < 48) || (charCode > 57))) {
        return false;
    }
    return true;
}


function finalSubmit(){
if(document.getElementById("rd1").checked || document.getElementById("rd2").checked ||document.getElementById("rd3").checked ){ 
}else{
var num =parseInt($("#installmentNo").val().trim());
	if(num<1){
	    alert("Installment No Should be Between 1 to 36");
	    $("#installmentNo").val("");
	    $("#installmentNo").focus();
	    return false;
	   }
	   if(num>36)
	   {
	    alert("Installment No Should be Between 1 to 36");
	    $("#installmentNo").val("");
	    $("#installmentNo").focus();
	    return false;
	   }
	   if(!$("#installmentNo").val()){
		alert("Please provide No OF installment !!!");
		return false;
	}
}
if(!$('#perAmount').is(":checked"))
{
	if($("#amount").val()==""){
		alert("Please provide amount.....");
		$("#amount").focus();
		return false;
	}else if(parseInt($("#amount").val().trim())==0){
		alert("amount Should be Greater then Zero .....");
		$("#amount").focus();
		return false;
	}else{
		return true;
	}
}
return true;
}

function installEnbDsb(){
	if(document.getElementById("rd1").checked || document.getElementById("rd2").checked ||document.getElementById("rd3").checked ){	
		$("#installmentNo").prop("disabled", true);
		$("#installmentNo").val('');
	}else if(document.getElementById("rd4").checked){	
		$("#installmentNo").prop("disabled", false);
		$("#installmentNo").val('');
	}
}

function getFileExtension(name) {
	var splitData = name.split(".");
	var returnFlag = false;
	var index = splitData.length;
	var extension = splitData[index - 1];
	var exteArr = [ "pdf","png","jpg","jpeg" ];
	$.each(exteArr, function(i, j) {
		if (j == extension) {
			returnFlag = true;
		}
	})
	return returnFlag;
}

function getFileSize(file){
	//var file = $el[0].files[0];
	if (file.size > 5242880) {
		return false;
	}else{
		return true;
	}
}

$("#uploadOtherDoc").on('click', function(event){

 var filetype=true;
 var fd = new FormData();
 var totalfiles = document.getElementById('files').files.length;
 var radioValue=$("#claimAppliedFor input:radio:checked").val();
 var claimPurpose=$("#purpose").val();
 var claimAmount=$("#amount").val();
 var claimInstallmentNo=$("#installmentNo").val();
 
   if(totalfiles>0){
   for (var index = 0; index < totalfiles; index++) {
      fd.append("files", document.getElementById('files').files[index]);
      if(getFileExtension(document.getElementById('files').files[index].name) && getFileSize(document.getElementById('files').files[index])){
      	filetype=true;
      	}
      	else
      	{
      	filetype=false;
      	break;
      }
	}
	}/*else{
		alert("Kindly upload at list one document.");
	} */

if(filetype){
  $.ajax({
            type: 'POST',
            url: 'multiUplodCpfDoc?${_csrf.parameterName}=${_csrf.token}&reqId=${reqId}&claimSubmittedBy=${actClaimDto.CLAIM_SUBMITTED_BY}&claimAppliedFor='+radioValue,
            enctype: 'multipart/form-data',
            data: fd,
            processData: false,
            contentType: false,
            success: function (data, textStatus, xhr) {
            	if (xhr.status=='200') {
	                console.log('Upload Completed ...');
	                window.location.href='${pageContext.request.contextPath}/claim/raiseClaimReq?claimAppliedFor='+radioValue+'&claimPurpose='+claimPurpose+'&claimAmount='+claimAmount+'&claimInstallmentNo='+claimInstallmentNo+'&reqId=${reqId}&uploadfiles='+data+' Files uploaded successfully !!!';
                }
            }
        });
}else{
		$('#msg').html("Kindly upload your file/s according to the given instructions !!!").fadeIn('slow');
		$('#msg').delay(10000).fadeOut('slow');
}

});

</script>