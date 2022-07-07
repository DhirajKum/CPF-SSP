<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE>
<%
  java.util.Date systemDate = new java.util.Date();
  java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("d-MMM-yyyy");
  String strSystemDate = dateFormat.format(systemDate);
  StringBuffer requestString = request.getRequestURL();
%>

<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" >
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<link rel="STYLESHEET" type="text/css" href="/CpfSelfServiceFci/COMMON/CSS/custom.css">
</head>  
<nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="z-index: 1;background-color: #D3D3D3;padding-top: 9px;">
        <div class="row row-fluid">
            <div class="col-lg-7 col-md-7 col-sm-7 col-xs-7">
            
    <img src="/CpfSelfServiceFci/COMMON/images/fci.jpg" style="width:200px; height:150px" class="d-inline-block align-top" alt="">
     <a class="navbar-brand navbar-left" href="#" style="font-size:17px;margin-top: 90px;margin-left: 13px;">
                               <span class="header-text" style=""> CPF Self Service Portal <br>
                                   Food Corporation Of India
                               </span>
		                    </a>
    
            </div>
            <div class="col-lg-5 col-md-5 col-sm-5 col-xs-5">
                <div class="row row-fluid" style="font-weight:600;">
                    <div class="col-md-12 epfo" style="text-align:right;font-size:11px;margin-left: 265px;">
                        <span class="btn btn-success" style="padding-bottom: 0px;
    padding-top: 0px;">
                            UAN : <span class="uan-face"> 1007 3390 9666</span>
/VIKASH SINGH
                        </span>
                       <br>  <span class="hidden-xs" style="color:#FFF;padding:5px 5px;border-right:1px solid #3654AE;line-height:24px;">
                            <a href="#" id="btnFontMinus" style="color:#FFF;font-weight:bold;background-color:#A68347;padding:0 2px;">-A</a>  
                            <a href="#" id="btnFontReset" style="color:#FFF;font-weight:bold;background-color:#A68347;padding:0 4px;" "="">A</a>
                            <a href="#" id="btnFontPlus" style="color:#FFF;font-weight:bold;background-color:#A68347;padding:0 1px;" "="">A+</a>
                        </span>
                        
                        
                             
                             <span style="color:#FFF;padding:3px 3px;margin-top:5px;">
                                <a href="#"><i class="fa fa-sign-out fa-2"></i> Logout</a>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="row row-fluid">
                    <div class="col-lg-11 col-md-11 col-sm-11 col-xs-11 content-pad-0" style="margin-top:9px;text-align:right;">
                        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 content-pad-0">
                                   
                        </div>
                     </div>
                </div>
            </div>
    </nav>
    </html>