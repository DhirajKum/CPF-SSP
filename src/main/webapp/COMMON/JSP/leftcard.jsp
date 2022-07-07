<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"> -->

</head>
<body>

	<div class="col-md-12" style="padding-left: 5px">

		<c:if test="${not empty message}">
			<div class="col-md-12">
				<div class="alert alert-success alert-dismissible" style="text-align: center;">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					${message}
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
							<td style="width: 30%;"><b>UAN</b></td>
							<td colspan="2">${homePageData.empUan}</td>
						</tr>
						<tr>
							<td style="width: 30%;"><b>Name</b></td>
							<td style="width: 80%;">${homePageData.empName}</td>
							<!-- <td rowspan="3" style="vertical-align: middle;"><span><a
									href="/memberinterface/modifyBasicDetails/loadMemberDetails?_HDIV_STATE_=2-32-A97142B0DEDADEE96F8A3BA1657D8E5C"
									title="Edit"><i class="glyphicon glyphicon-edit"></i></a></span></td> -->
						</tr>
						<tr>
							<td style="width: 30%;"><b>Birth Date</b></td>
							<td style="width: 80%;">${homePageData.empDob}</td>
						</tr>
						<tr>
							<td style="width: 30%;"><b>Gender</b></td>
							<td style="width: 80%;">${homePageData.empGender}</td>
						</tr>
						<tr>
							<td style="width: 30%;"><b>AADHAAR</b></td>
							<td style="width: 80%;">${homePageData.empAadhar} 
							<!-- | <span style="color: green;">Verified ( DEMOGRAPHIC )</span> -->
							</td>
							<!-- <td rowspan="3" style="vertical-align: middle;"><span><a
									href="/memberinterface/kyc/viewKYCRegistrationForm?_HDIV_STATE_=2-34-A97142B0DEDADEE96F8A3BA1657D8E5C"
									title="Edit"><i class="glyphicon glyphicon-edit"></i></a></span></td> -->
						</tr>
						<tr>
							<td style="width: 30%;"><b>PAN</b></td>
							<td style="width: 80%;">${homePageData.empPan}</td>
						</tr>
						<tr>
							<td style="width: 30%;"><b>Bank Account No.</b></td>
							<td style="width: 80%;">${homePageData.empBankAccNo}</td>
						</tr>
						<tr>
							<td><b>Mobile No.</b></td>
							<td>${homePageData.empMobile}</td>
							<!--<td rowspan="2" style="vertical-align: middle;"><span><a
									href="/memberinterface/member/profile/changeContactDetails?_HDIV_STATE_=2-33-A97142B0DEDADEE96F8A3BA1657D8E5C"
									title="Edit"><i class="glyphicon glyphicon-edit"></i></a></span></td> -->
						</tr>
						<tr>
							<td><b>E-mail</b></td>
							<td>${homePageData.empEmail}</td>
						</tr>
						<!-- <tr>
							<th colspan="3" onclick="toggleFaIcon(this);"><a
								class="faq-links" data-toggle="collapse" href=".colrw"
								style="color: #A68347; text-decoration: none;"><i
									class="fa fa-plus-square"></i>&emsp;More information</a></th>
						</tr>
						<tr class="colrw collapse">
							<td><b>Last Updated</b></td>
							<td colspan="2">20-SEP-2016</td>
						</tr>
						<tr class="colrw collapse">
							<td><b>Password Change Date</b></td>
							<td colspan="2">07-NOV-2019</td>
						</tr> -->
					</tbody>
				</table>
			</div>
		</div>

	</div>

</body>
</html>