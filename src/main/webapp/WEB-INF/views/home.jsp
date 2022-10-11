<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/COMMON/images/fci.jpg" rel="shortcut icon">
<link href="${pageContext.request.contextPath}/resources/COMMON/JS/jquery-3.5.1.min.js" rel="STYLESHEET" type="text/css" >
<link href="${pageContext.request.contextPath}/resources/COMMON/JS/bootstrap.min.js" rel="STYLESHEET" type="text/css">

</head>
<body>
 <td class="w-25"><b>Employer Contribution</b></td><div class="col-md-12" style="padding-left: 5px">

		<c:if test="${not empty message}">
			<div class="col-md-12" id="message">
				<div class="alert alert-success alert-dismissible" style="text-align: center;">
					${message}
				</div>
			</div>
		</c:if>
		
		<c:if test="${not empty errorMessage}">
			<div class="col-md-12" id="errormessage">
				<div class="alert alert-danger alert-dismissible" style="text-align: center;">
					${errorMessage}
				</div>
			</div>
		</c:if>

		<div class="col-md-12 data-box">
			<div class="col-md-12 content-pad-0 data-box-header"
				style="font-size: 15px;">
				<div class="col-md-1 content-pad-0 box-icon">
					<span><i class="fa fa-user-plus"></i></span>
				</div>
				<div class="col-md-11 content-pad-0 box-icon-title">Member Profile</div>
			</div>
			<div class="col-md-12 content-pad-0 box-icon-body">

				<table class="table table-striped table-bordered form-group"
					style="margin-left: 0px; margin-right: 20px; border: none;">
					<tbody>
						<tr>
							<td class="w-25"><b>UAN</b></td>
							<td class="w-25">${homePageData.empUan}</td>
							<td class="w-25"><b>CPF Freeze as on</b></td>
							<td class="w-25">${homePageData.cpfFreezeYear}</td>
						</tr>
						<tr>
							<td class="w-25"><b>Name</b></td>
							<td class="w-25">${homePageData.empName}</td>
							<td class="w-25"><b>Employee Contribution</b></td>
							<td class="w-25">${homePageData.employeeContribute}</td>
						</tr>
						<tr>
							<td class="w-25"><b>Birth Date</b></td>
							<td class="w-25">${homePageData.empDob}</td>
							<td class="w-25"><b>Employer Contribution</b></td>
							<td class="w-25">${homePageData.employerContribute}</td>
						</tr>
						<tr>
							<td class="w-25"><b>Gender</b></td>
							<td class="w-25">${homePageData.empGender}</td>
							<td class="w-25"><b>VPF</b></td>
							<td class="w-25">${homePageData.vpfContribute}</td>
						</tr>
						<tr>    
							<td class="w-25"><b>AADHAAR</b></td>
							<td class="w-25">${homePageData.empAadhar}</td>
							<td class="w-25"><b>Uploaded KYC Document</b></td>
							<td class="w-25"><a href="${pageContext.request.contextPath}/claim/downloadCpfDoc?pathId=${homePageData.filePath}&fileType=1" target="_blank">${homePageData.fileName}</a></td>
						</tr>
						<tr>
							<td class="w-25"><b>PAN</b></td>
							<td class="w-25">${homePageData.empPan}</td>
							<td class="w-25"><b></b></td>
							<td class="w-25"></td>
						</tr>
						<tr>
							<td class="w-25"><b>Bank Account No.</b></td>
							<td class="w-25">${homePageData.empBankAccNo}</td>
							<td class="w-25"><b></b></td>
							<td class="w-25"></td>
						</tr>
						<tr>
							<td class="w-25"><b>Mobile No.</b></td>
							<td class="w-25">${homePageData.empMobile}</td>
							<td class="w-25"><b></b></td>
							<td class="w-25"></td>
						</tr>
						<tr>
							<td class="w-25"><b>E-mail</b></td>
							<td class="w-25">${homePageData.empEmail}</td>
							<td class="w-25"><b></b></td>
							<td class="w-25"></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

	<div class="col-md-12 content-pad-0 box-icon-body">
		<sf:form id="docUploadForm" action="claim/uplodCpfDoc?${_csrf.parameterName}=${_csrf.token}" method="POST" enctype="multipart/form-data">  
			<div class="row col-md-12" style="text-align: center;">
			<!-- <div class="col-md-2">
			</div> -->
			<!-- <div class="col-md-6"> -->
				<!-- <div class="form-group row"> -->
				<label class="col-sm-4 col-form-label" style="text-align: center;"><b>Upload KYC Document <span class="red">*</span></b><small class="form-text text-muted" style="text-align: center;">Upload "UAN" document</small></label>
				
				<div class="col-sm-4">
					<span id="uploadSupptDoc"></span>
					<input type="file" name="file" id="kycDoc" data-validation="ckeckFileFormat ckeckFileSize required"/>
					<small id="docUploadHelp" class="form-text text-muted" style="text-align: left;">File should be in pdf, jpg and jpeg format only.</br>File maximum size should be 5 Mb.</small>
				</div>
				<!-- </div> -->
			<!-- </div> -->
			<div class="col-md-4" style="text-align: left;">
				<input type="submit" value="Upload File"/>
			</div>
			</div> 
		<!-- 	<div class="col-md-12" style="text-align: center;">
			<label class="col-md-4 col-form-label"></label>
			<div class="col-md-4">
				<small id="docUploadHelp" class="form-text text-muted" style="text-align: left;">File should be in pdf, jpg and jpeg format only.</br>File maximum size should be 5 Mb.</small>
			</div>
			<div class="col-md-4"></div>			
			</div> -->
			
			 
		</sf:form>
		</div>

	</div>

<script type="text/javascript">
$(document).ready(function(){
	//$("#message").hide();
	//$("#errormessage").hide();
});
$.validate({
	validateOnBlur : true,
	form : '#docUploadForm',
	onSuccess : function(event, form) {
		return true;
	}
});

$.formUtils.addValidator({
	name : 'ckeckFileFormat',
	validatorFunction : function(value, $el, config, language, $form) {
		if (value == null || "" == value) {
			return false;
		}
		return getFileExtension(value);
	},
	//errorMessage : 'The File format should be doc, docx, pdf , xls , xlsx',
	errorMessage : 'The File format should be only pdf, jpg and jpeg',
	errorMessageKey : 'uploadSupptDoc'
});
function getFileExtension(name) {
	var splitData = name.split(".");
	var returnFlag = false;
	var index = splitData.length;
	var extension = splitData[index - 1];
	//var exteArr = [ "pdf", "doc", "docx", "xls", "xlsx" ];
	var exteArr = [ "pdf","jpg","jpeg" ];
	$.each(exteArr, function(i, j) {
		if (j == extension) {
			returnFlag = true;
		}
	})
	return returnFlag;
}

$.formUtils.addValidator({
	name : 'ckeckFileSize',
	validatorFunction : function(value, $el, config, language, $form) {
		var file = $el[0].files[0];
	    if (file.size > 5242880) {
			return false;
	    }
	},
	errorMessage : 'The File Size should be less than 5 MB',
	errorMessageKey : 'uploadSupptDocSize'
});

</script>

</body>
</html>
