<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="cache-control" content="max-age=0, must-revalidate, no-cache, no-store, private">
<meta http-equiv="expires" content="-1">
<meta http-equiv="pragma" content="no-cache">
<title>CPF Self Service</title>
<link href="${pageContext.request.contextPath}/resources/static/images/fci.jpg"	rel="shortcut icon">
<link href="${pageContext.request.contextPath}/resources/static/css/bootstrap4_5_4.min.css"	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/static/css/jquery-ui.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/resources/static/css/epfo-style.css"	rel="stylesheet" />
<!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"> -->

<style>
@font-face {
    font-family: hindi;
    src: url(${pageContext.request.contextPath}/resources/COMMON/fonts/Kruti_Dev_010.ttf);
}
.head2{
	font-family: hindi;
}
.header-sub-text {
	font-size: 12px;
	font-weight: normal;
}

.header-text {
	font-size: 14px;
}
</style>
</head>

<body class="epfo-body session-disp">

	<div class="container" style="max-width: 100%; padding-top: 10px; padding-bottom: 20px;">
		
		<div class="row row-fluid">
		<div class="col-md-12">
			<div class="col-md-4" style="text-align: left;">
				<img style="padding-top: 5px !important" class="header-logo" id="header-icon-img" src="${pageContext.request.contextPath}/resources/static/images/fci.jpg">
			</div>
			<div class="col-md-4" style="text-align: center; padding-top: 5px !important">
				<span class="header-text">CPF Self Service Portal </span><br> 
				<span class="hidden-xs header-sub-text" style=""> Food Corporation Of India</span>
			</div>
			<div class="col col-md-4" style="padding-top: 5px !important; text-align: right; font-size: 11px;">
				<span class="btn btn-success"> Emp No. : <span class="uan-face">${userModel.empNum}</span> /${userModel.empName} (Role-${userModel.roleName})</span><br> 
				<span style="color: #FFF; padding: 3px 3px; margin-top: 5px;"><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out fa-2"></i>Logout</a></span><br>
				<!-- <small class="form-text text-muted smallText">Language <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
				<a class="txt2"	href="http://fcilekha.in:7778/CpfSelfService"><h7>English </h7><i aria-hidden="true"></i></a>
				<a class="txt2 head2" href="http://hi.fcilekha.in:7778/CpfSelfService/login"><h7><b> fgUnh</b></h7><i aria-hidden="true"></i></a>
				</small> -->
			</div>
		</div>
		</div>
	</div>