<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="STYLESHEET" type="text/css" href="/CpfSelfServiceFci/COMMON/CSS/custom.css">
<link rel="STYLESHEET" type="text/css" href="/CpfSelfServiceFci/COMMON/JS/jquery-3.5.1.min.js">
<link rel="STYLESHEET" type="text/css" href="/CpfSelfServiceFci/COMMON/JS/bootstrap.min.js">
</head>
<body>

<div class="col-md-4" style="padding-left:5px">
      
            
<div class="col-md-12 data-box">
            <div class="col-md-12 content-pad-0 data-box-header">
                <div class="col-md-1 content-pad-0 box-icon">
                    <span><i class="fa fa-user-plus"></i></span>
                </div>
                <div class="col-md-11 content-pad-0 box-icon-title">
                    Member Profile
                </div>
            </div>
            <div class="col-md-12 content-pad-0 box-icon-body">               
                  
                                
                       
                <script>
                   /* $(function () {
                        $.ajax({
                            //url: '/memberinterface/userController/userinfo?_HDIV_STATE_=2-25-A97142B0DEDADEE96F8A3BA1657D8E5C',
                            url:'',
                            type: "POST",
                            success: function (result) {

                                var table = '<tr>\n\
                                                <td style="width: 30%;"><b>UAN</b></td>\n\
                                                <td colspan="2">' + result.memberinfo.uan + '</td>\n\
                                            </tr>\n\
                                                    <tr>\n\
                                                        <td style="width: 30%;"><b>Name</b></td>\n\
                                                        <td style="width: 80%;">' + result.memberinfo.name + '</td>\n\\n\
                                                        <td rowspan="3" style="vertical-align:middle;"><span><a href="' + result.modifyDetailsUrl + '" title="Edit"><i class="glyphicon glyphicon-edit" ></i></a></span>\n\
                                                    </tr>\n\
                                                    <tr>\n\
                                                        <td style="width: 30%;"><b>Birth Date</b></td>\n\
                                                        <td style="width: 80%;">' + result.memberinfo.dob + '</td>\n\
                                                    </tr>\n\
                                                    <tr>\n\
                                                        <td style="width: 30%;"><b>Gender</b></td>\n\
                                                        <td style="width: 80%;">' + result.memberinfo.gender + '</td>\n\
                                                    </tr>\n\
                                            <tr>\n\
                                                <td style="width: 30%;"><b>AADHAAR</b></td>\n\
                                                <td style="width: 80%;">' + result.memberinfo.maskedAadhaar;
                                if (result.memberinfo.aadhaar !== "<span style='color: red;'>---</span>") {
//                                                    if(result.memberinfo.aadhaarStatus.includes('Unverified'))
                                    if (result.memberinfo.aadhaarStatus.indexOf('Unverified') !== -1)
                                        table = table + ' | <span style="color: red;">' + result.memberinfo.aadhaarStatus + '</span>&emsp;<a href="javascript:void(0)" title="Click here to verify AADHAAR." style="cursor:hand; color:white; background-color:#048282; border-color:#048282;" class="btn btn-success" onclick="verifyAadhaar(\'' + result.memberinfo.aadhaarVerifyUrl + '\',\'' + result.homeUrl + '\');return false;">Verify</a>\n';
//                                                    else if(result.memberinfo.aadhaarStatus.includes('Verified'))
                                    else if (result.memberinfo.aadhaarStatus.indexOf('Verified') !== -1)
                                        table = table + ' | <span style="color: green;">' + result.memberinfo.aadhaarStatus + '</span>\n';
                                }
                                table = table + '</td>\n\
                                                <td rowspan="3" style="vertical-align:middle;"><span><a href="' + result.kycDetailsChangeUrl + '" title="Edit"><i class="glyphicon glyphicon-edit" ></i></a></span>\n\
                                            </tr>\n\
                                            <tr>\n\
                                                <td style="width: 30%;"><b>PAN</b></td>\n\
                                                <td style="width: 80%;">' + result.memberinfo.maskedPan;
                    
                                table = table + '</tr>\n\
                                            <tr>\n\
                                                <td style="width: 30%;"><b>Bank Account No.</b></td>\n\
                                                <td style="width: 80%;">' + result.memberinfo.maskedBankAccNo + '</td>\n\
                                            </tr>\n\
                                            <tr>\n\
                                                <td><b>Mobile No.</b></td>\n\
                                                <td>' + result.memberinfo.maskedMobileNo + '</td>\n\
                                                <td rowspan="2" style="vertical-align:middle;"><span><a href="' + result.changeContactsUrl + '" title="Edit"><i class="glyphicon glyphicon-edit" ></i></a></span>\n\
                                            </tr>\n\
                                            <tr>\n\
                                                <td><b>E-mail</b></td>\n\
                                                <td>' + result.memberinfo.maskedEmailId + '</td>\n\
                                            </tr>\n\
                                            <tr>\n\
                                                <th colspan="3" onclick="toggleFaIcon(this);"><a class="faq-links" data-toggle="collapse" href=".colrw" style="color:#A68347; text-decoration:none;"><i class="fa fa-plus-square"></i>&emsp;More information</a></th>\n\
                                            </tr>\n\
                                            <tr class="colrw collapse">\n\
                                                <td><b>Last Updated</b></td>\n\
                                                <td colspan="2">' + result.memberinfo.lastUpdate + '</td>\n\
                                            </tr>\n\
                                            <tr class="colrw collapse">\n\
                                                <td><b>Password Change Date</b></td>\n\
                                                <td colspan="2">' + result.memberinfo.lastPasswordUpdate + '</td>\n\
                                            </tr>';

                                $('.table').html(table);

                            },
                            error: function (result, status, error) {
                                alert(result + "" + status + "" + error);
                            }
                        });
                    });*/

                    //TOGGLE FONT AWESOME ON CLICK
                    function toggleFaIcon(ele) {
                        var collapsed = $(ele).find('i').hasClass('fa-plus-square');
                        $('.faq-links').find('i').removeClass('fa-minus-square');
                        $('.faq-links').find('i').addClass('fa-plus-square');
                        if (collapsed)
                            $(ele).find('i').toggleClass('fa-plus-square fa-minus-square');
                    }

                    function verifyAadhaar(url, homeurl) {
//                            console.log("verifyAadhaar url : "+ url);
//                            blockUI("Verifying...");
                        $.blockUI({message: '<h4>Verifying...</h4>'});
                        $.ajax({
                            url: url,
                            type: "POST",
                            //async: false,
                            datatype: "json",
                            contentType: "text/html; charset=utf-8",
                            success: function (response)
                            {
                                if (response.error !== undefined) {
                                    alert(response.error);
                                } else {
                                    if (response.message !== undefined) {
                                        alert(response.message);
                                    }

                                }
                                window.location.href = homeurl;     //Redirect to home page after verification
//                                    window.location.reload(); 
//                                    unBlockUI();
                                $.unblockUI();
                            },
                            error: function (a, b, c) {
                                alert("Error while verifying AADHAAR. Please try after some time.");
                                console.log(a + ",   " + b + ",   " + c);
                            }
                        });
                    }


                    function verifyPan(url, homeurl) {
//                            console.log("verifyAadhaar url : "+ url);
//                            alert("In verifyPan url : "+ url);
//                            blockUI("Verifying...");
                        $.blockUI({message: '<h4>Verifying...</h4>'});
                        $.ajax({
                            url: url,
                            type: "POST",
                            //async: false,
                            datatype: "json",
                            contentType: "text/html; charset=utf-8",
                            success: function (response)
                            {
                                if (response.error !== undefined) {
                                    alert(response.error);
                                } else {
                                    if (response.message !== undefined) {
                                        alert(response.message);
                                    }

                                }
                                window.location.href = homeurl;     //Redirect to home page after verification
//                                    window.location.reload(); 
//                                    unBlockUI();
                                $.unblockUI();
                            },
                            error: function (a, b, c) {
                                alert("Error while verifying PAN. Please try after some time.");
                                console.log(a + ",   " + b + ",   " + c);
                            }
                        });
                    }

                    $(function () {
                        $.blockUI({message: '<h4>Verifying...</h4>'});
                        $.ajax({
                            //url: '/memberinterface/userController/checkeNominationStatus?_HDIV_STATE_=2-26-A97142B0DEDADEE96F8A3BA1657D8E5C',
                            url:'',
                            type: "POST",
                            //async: false,
                            datatype: "json",
                            contentType: "text/html; charset=utf-8",
                            success: function (response)
                            {
                                if (response.nominationStatus !== undefined) {


//                                    alert("nominationStatus: " + response.nominationStatus);
                                    if (response.nominationStatus === 1) {
                                        //CORRUPT PDF FOUND
                                        //DISPLAY MODAL WITHOUT CANCLE BUTTON
                                        $("#nominationPDFAlertModal").modal({
                                            backdrop: 'static',
                                            keyboard: false
                                        });
                                    } else {
                                        //CORRUPT PDF NOT FOUND
                                        $("#nominationAlertModal").modal();
                                    }

                                } else {

//                                    alert("nominationStatus else: " + response.nominationStatus);
                                    $("#nominationAlertModal").modal();

                                }

                                $.unblockUI();
                            },
                            error: function (a, b, c) {
                                alert("Error while fetching member information. Please try after some time.");
                                console.log(a + ",   " + b + ",   " + c);
                            }
                        });

//                        $("#nominationAlertModal").modal({
//                            backdrop: 'static',
//                            keyboard: false
//                        });
                    });
                </script>
                  
                <table class="table table-striped table-bordered form-group" style="margin-left: 0px; margin-right: 20px;border:none;"><tbody><tr>
                                                <td style="width: 30%;"><b>UAN</b></td>
                                                <td colspan="2">100733909666</td>
                                            </tr>
                                                    <tr>
                                                        <td style="width: 30%;"><b>Name</b></td>
                                                        <td style="width: 80%;">VIKASH SINGH</td><td rowspan="3" style="vertical-align:middle;"><span><a href="/memberinterface/modifyBasicDetails/loadMemberDetails?_HDIV_STATE_=2-32-A97142B0DEDADEE96F8A3BA1657D8E5C" title="Edit"><i class="glyphicon glyphicon-edit"></i></a></span>
                                                    </td></tr>
                                                    <tr>
                                                        <td style="width: 30%;"><b>Birth Date</b></td>
                                                        <td style="width: 80%;">15/05/1991</td>
                                                    </tr>
                                                    <tr>
                                                        <td style="width: 30%;"><b>Gender</b></td>
                                                        <td style="width: 80%;">MALE</td>
                                                    </tr>
                                            <tr>
                                                <td style="width: 30%;"><b>AADHAAR</b></td>
                                                <td style="width: 80%;">XXXX XXXX 1417 | <span style="color: green;">Verified ( DEMOGRAPHIC )</span>
</td>
                                                <td rowspan="3" style="vertical-align:middle;"><span><a href="/memberinterface/kyc/viewKYCRegistrationForm?_HDIV_STATE_=2-34-A97142B0DEDADEE96F8A3BA1657D8E5C" title="Edit"><i class="glyphicon glyphicon-edit"></i></a></span>
                                            </td></tr>
                                            <tr>
                                                <td style="width: 30%;"><b>PAN</b></td>
                                                <td style="width: 80%;">XXWPS191XX</td></tr>
                                            <tr>
                                                <td style="width: 30%;"><b>Bank Account No.</b></td>
                                                <td style="width: 80%;">91501006031XXXX</td>
                                            </tr>
                                            <tr>
                                                <td><b>Mobile No.</b></td>
                                                <td>886762XXXX</td>
                                                <td rowspan="2" style="vertical-align:middle;"><span><a href="/memberinterface/member/profile/changeContactDetails?_HDIV_STATE_=2-33-A97142B0DEDADEE96F8A3BA1657D8E5C" title="Edit"><i class="glyphicon glyphicon-edit"></i></a></span>
                                            </td></tr>
                                            <tr>
                                                <td><b>E-mail</b></td>
                                                <td>ESINGHVIXXXX@GMAIL.COM</td>
                                            </tr>
                                            <tr>
                                                <th colspan="3" onclick="toggleFaIcon(this);"><a class="faq-links" data-toggle="collapse" href=".colrw" style="color:#A68347; text-decoration:none;"><i class="fa fa-plus-square"></i>&emsp;More information</a></th>
                                            </tr>
                                            <tr class="colrw collapse">
                                                <td><b>Last Updated</b></td>
                                                <td colspan="2">20-SEP-2016</td>
                                            </tr>
                                            <tr class="colrw collapse">
                                                <td><b>Password Change Date</b></td>
                                                <td colspan="2">07-NOV-2019</td>
                                            </tr></tbody></table>
            </div>
        </div>        
                
            </div>
    
</body>
</html>