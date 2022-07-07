<!DOCTYPE>

<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

<link rel="STYLESHEET" type="text/css"
	href="${pageContext.request.contextPath}/resources/COMMON/CSS/custom.css">

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.header {
	overflow: hidden;
	background-color: #f1f1f1;
	padding: 20px 10px;
}

.header a {
	float: left;
	color: black;
	text-align: center;
	padding: 12px;
	text-decoration: none;
	font-size: 18px;
	line-height: 25px;
	border-radius: 4px;
}

.header a.logo {
	font-size: 25px;
	font-weight: bold;
}

.header a:hover {
	background-color: #ddd;
	color: black;
}

.header a.active {
	background-color: dodgerblue;
	color: white;
}

.header-right {
	float: right;
}

.header-left {
	float: left;
}

@media screen and (max-width: 500px) {
	.header a {
		float: none;
		display: block;
		text-align: left;
	}
	.header-right {
		float: none;
	}
}
</style>
</head>

<body>
	<div class="header">
		<div class="header-left">
			<img src="${pageContext.request.contextPath}/resources/COMMON/images/fci.jpg"
				style="width: 150px; height: 100px; margin-right: 10px;" class=""
				alt="">
		</div>

		<div class="">
			<a style="margin-top: 25px;"> <span class="header-text"
				style="font-weight: bold; font-size: 20px; color: #0000ff;">
					Central Provident Fund (CPF) Self Service Portal <br>
			</span> <span class="" style="text-align: left;"> Food Corporation Of
					India </span>

			</a>
		</div>

		<div class="header-right">
			<span class="btn btn-success" style="font-size: 14px;"> UAN : <span
				class="uan-face"> 1007 3390 9666</span> / VIKASH SINGH
			</span> <br> <span style="color: #FFF;"> <a
				style="font-size: 14px;" href="#"><i class="fa fa-sign-out fa-2"></i>
					Logout</a>
			</span>
		</div>

	</div>

</body>
</html>