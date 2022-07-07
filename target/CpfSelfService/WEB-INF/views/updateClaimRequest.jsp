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
		<div class="col-md-12">
			<div class="alert alert-danger alert-dismissible" style="text-align: center;">
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
			Update Claim Request
		</h5>
		
		<div class="row marginT20">
			<div class="col-md-12">
			 <sf:form action='${pageContext.request.contextPath}/claim/updateClaimRequest?reqId=${reqId}&reqType=${reqType}&claimReq=resubmit' method='POST' modelAttribute="claimData" id="claimForm">
			 <div class="form-group row">
					<label for="oldpassword" class="col-sm-3 col-form-label"><b>Claim applied for  <span class="red">*</span></b></label>
					<div class="col-sm-9" id="claimAppliedFor">
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
							<label for="EMP_NAME" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Name of the member <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="EMP_NAME" cssclass="form-control" id="empName" maxlength="20"/>
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
							<label for="FATHER_HUSBAND_NAME" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Father's Name/Husband's Name <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="FATHER_HUSBAND_NAME" cssclass="form-control" id="fatherName"  maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="DATE_OF_BIRTH" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Date of Birth  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<input type="text" name="DATE_OF_BIRTH" cssclass="form-control" id="dob" value="${dob}" maxlength="30" style="font-size:small; "  />
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="BASIC" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Basic pay <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="BASIC" cssclass="form-control" id="basic" maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="CPF_ACCOUNT_NUMBER" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>C.P.F Account No.  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="CPF_ACCOUNT_NUMBER" cssclass="form-control  form-control-sm" id="cpfAccountNumber"  maxlength="20"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="UAN" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Universal Account Number(UAN)</b></label>
							<div class="col-sm-7">
								<sf:input path="UAN" cssclass="form-control" id="uan"  maxlength="20"/>
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
								<input type="text" name="DOJ_FCI" cssclass="form-control" id="dojFci" placeholder="" value="${dojFci}" maxlength="30"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="RETIREMENT_DATE" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Retirement/Date of leaving service<span class="red">*</span></b></label>
							<div class="col-sm-7">
								<input type="text" name="RETIREMENT_DATE" cssclass="form-control  form-control-sm" id="retirementDate" placeholder="" value="${retirementDate}" maxlength="30"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="PAN" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Permanent Account NO.(PAN)</b><small class="form-text text-muted paddingL40">(Only in case of service less than 5 years)</small></label>
							<div class="col-sm-7">
								<sf:input  path="PAN" cssclass="form-control" id="pan"  maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="PRESENT_LOCATION" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Place of posting  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input  path="PRESENT_LOCATION" cssclass="form-control  form-control-sm" id="placepost" maxlength="20"/>
							</div>
						</div>
					</div>
				</div>
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="oldpassword" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Purpose of CPF Part withdrawal/Temporary Advance <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:select cssclass="form-control" path="PURPOSE" id="purpose">
									<option value="" label="---Select---"/>
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
								<sf:input path="INSTALLMENT_NUMBER" cssclass="form-control  form-control-sm" id="installmentNo"  maxlength="20" onkeypress="return isNumber(event)" onblur="return range()"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="MOBILE_NUMBER" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Mobile Number  <span class="red">*</span></b></label>
							<div class="col-sm-7">
								<sf:input path="MOBILE_NUMBER" cssclass="form-control  form-control-sm" id="mobileNo"  maxlength="20"/>
							</div>
						</div>
					</div>
						
				</div>
				
				<hr class="solid">
				<div class="row">
					<div class="col-md-6">
						<div class="checkbox">
							<label><sf:checkbox path="LAST_DRAWN_ADVANCE" id="lastDrawnAdvance"/><b>Particular of Temporary advance last drawn, if  any</b></label>
						</div>
						
						<%-- <div class="form-group row">
							<label for="LAST_DRAWN_ADVANCE" class="col-sm-5 col-form-label"><b>Particular of Temporary advance last drawn, if  any</b></label>
							<div class="col-sm-7">
								<sf:input type="text" path="LAST_DRAWN_ADVANCE" class="form-control" id="lastDrawnAdvance" placeholder="" maxlength="20"/>
							</div>
						</div> --%>
					</div>
					</div>
					<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="ADVANCE_AMOUNT" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Amount of advance</b></label>
							<div class="col-sm-7">
								<sf:input path="ADVANCE_AMOUNT" cssclass="form-control  form-control-sm" id="advanceAmount"  maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="OUTSTANDING_BAL" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Balance outstanding</b></label>
							<div class="col-sm-7">
								<sf:input path="OUTSTANDING_BAL" cssclass="form-control  form-control-sm" id="outstandingBal"  maxlength="20"/>
							</div>
						</div>
					</div>
				</div>				
				<div class="row">	
					<div class="col-md-6">
						<div class="form-group row">
							<label for="AMOUNT_REPAID" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Amount Re-paid</b></label>
							<div class="col-sm-7">
								<sf:input path="AMOUNT_REPAID" cssclass="form-control" id="amountRepaid"  maxlength="20"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group row">
							<label for="AMOUNT_90PARTFINAL_BEF_RETR" cssclass="col-sm-5 col-form-label" class="labelwidth"><b>Amount of 90% Part Final drawn previously before retirement, if  any</b></label>
							<div class="col-sm-7">
								<sf:input path="AMOUNT_90PARTFINAL_BEF_RETR" cssclass="form-control" id="amount90Partfinal"  maxlength="20"/>
							</div>
						</div>
					</div>
				</div>				
				
				<div class="form-group row">
					<div class="col-sm-10">
						<div class="checkbox">
							<label><sf:checkbox path="DEC_NOT_EMP_TWOMONTH" id="decNotEmpTwoMonth"/> The member here by declares that he has not been employed for two month</label>
						</div>
					</div>
				</div>
				<!-- <div class="form-group row">
					<div class="col-sm-10">
						<div class="checkbox">
							<label><input type="checkbox"> In case the amount is used for any purpose other than stated in column(10) above, I am liable to return the entire amount with penal interest</label>
						</div>
					</div>
				</div> -->
				<div class="form-group row">
					<div class="col-sm-12">
						<div class="checkbox">
							<label><sf:checkbox path="EMP_DECLARATION" id="empDec"/> Certified that the particulars are true to the best of my knowledge</label>
						</div>
					</div>
				</div>						
				
				<div style="text-align: center;">
				<button type="submit" id="saveClaim" class="btn btn-primary btn-sm" onclick="return finalSubmit()">Submit</button>&nbsp;<a href="" class="btn btn-primary btn-sm reset-form">Cancel</a>
				</div>
				<sf:hidden path="locId"/>
				<sf:hidden path="parentZone"/>		
				<sf:hidden path="CLAIM_SUBMITTED_BY"/>
				<sf:hidden path="claimSubmittedDate"/>
				
				</sf:form>
			</div>
		</div>
</div>
</div>
</div>

<script>		
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
	                option = option + "<option value='"+res[i].cpfPurposeKey + "'" +(res[i].cpfPurposeKey === '${claimData.PURPOSE}' ? " selected='selected'" : "") + "'>"+res[i].cpfPurposeValue + "</option>";
	            }
	            purpose.append(option);					
			}
		}
	});

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
	if(document.getElementById("rd1").checked || document.getElementById("rd2").checked || document.getElementById("rd3").checked){	
		$("#installmentNo").prop("disabled", true);
		$("#installmentNo").val('');
		if(${kycUpdate}==0){
			$("#saveClaim").prop("disabled",true);
		}
	}
	$("#perAmount").prop("disabled", true);
	/* if($('#perAmount').is(":checked")){
		$('#amount').prop("readonly", true);
	} */

});

$('#claimForm').submit(function (){
	var radioValue=$("#claimAppliedFor input:radio:checked").val();
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

	/* if(!($('#perAmount').is(":checked"))){
		alert("Kindly Enter Amount ...");
		return false;
	} */
});

$("#perAmount").click(function (){
	if($(this).is(":checked")){
		$('#amount').prop("readonly", true);
		$("#amount").val('');
		/* var sancType = $("#purpose").val();
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
		$('#amount').prop("readonly", false);
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
	                option = option + "<option value='"+res[i].cpfPurposeKey + "'>"+res[i].cpfPurposeValue + "</option>";
	            }
	            purpose.append(option);					
			}
		}
	});
	
});

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
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
	}else if($("#amount").val()=="0"){
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
</script>