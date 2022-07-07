<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
	<nav class="navbar navbar-expand-lg navbar-light bg-light" style="background-color: #048282!important;">
	<button class="navbar-toggler" type="button" data-toggle="collapse"	data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
			<a class="nav-link text-white" href="${pageContext.request.contextPath}/home">Home <span class="sr-only">(current)</span></a>
			</li>
			
			<!-- <li class="nav-item dropdown">
			<a class="nav-link dropdown-toggle text-white" href="#"	id="navbarDropdown" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> View </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown" style="font-size: 12px">
					<a class="dropdown-item" href="#">Profile</a> 
					
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Something else here</a>
				</div>
			</li> -->
			
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle text-white" href="#"
				id="navbarDropdown" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Manage </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown"style="font-size: 12px">
					<!-- <a class="dropdown-item" href="#">Basic Details</a>
					<a class="dropdown-item" href="#">Contact Details</a> --> 
					<a class="dropdown-item" href="${pageContext.request.contextPath}/changePassword">Change Password</a>
					<!-- <div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Something else here</a> -->
				</div></li>
			
			<security:authorize access="isAuthenticated()">
			<li class="nav-item dropdown" id="claimServ">
				<security:authorize access="hasAnyRole('USER','ADMIN','CPF_ADMIN')">
				<a class="nav-link dropdown-toggle text-white" href="#"	id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Claim Services </a>
				</security:authorize>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown"style="font-size: 12px">
					
						<a class="dropdown-item" href="${pageContext.request.contextPath}/claim/raiseClaimReq">Raise Claim Request</a>
						<security:authorize access="hasRole('USER')">
							<a class="dropdown-item" href="${pageContext.request.contextPath}/claim/pendingReq?reqType=myReq">Request Status</a>
						</security:authorize>
						<security:authorize access="hasRole('ADMIN')">
							<a class="dropdown-item" href="${pageContext.request.contextPath}/claim/pendingReq?reqType=myReq">My Request Status</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/claim/pendingReq?reqType=otherReq">Pending Request</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/claim/approvedReq">Approved Request</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/claim/completedReq">Completed Request</a>
						</security:authorize>
						<security:authorize access="hasRole('CPF_ADMIN')">
							<a class="dropdown-item" href="${pageContext.request.contextPath}/claim/pendingReq?reqType=myReq">My Request Status</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/claim/myPendingReq">My Pending Request</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/claim/pendingReq?reqType=otherReq">Pending Request</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/claim/approvedReq">Approved Request</a>
							<a class="dropdown-item" href="${pageContext.request.contextPath}/claim/completedReq">Completed Request</a>
						</security:authorize>
					<%-- </security:authorize> --%>
				</div>
			</li>
			</security:authorize>
			<security:authorize access="hasAnyRole('SUPER_USER')">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle text-white" href="#"
				id="navbarDropdown" role="button" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Role </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown"style="font-size: 12px">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/role/roleMapping">Manage Role</a> 
					<a class="dropdown-item" href="${pageContext.request.contextPath}/role/viewRevokeRole">View And Revoke Role</a>
				</div>
			</li>
			</security:authorize>
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle text-white" href="#"	id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> View </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown"style="font-size: 12px">
					<a class="dropdown-item" href="${pageContext.request.contextPath}/report/cpfSlip">My CPF Slip</a>
					<security:authorize access="hasAnyRole('ADMIN','CPF_ADMIN','SUPER_USER')">
						<a class="dropdown-item" href="${pageContext.request.contextPath}/report/claimReport">Claim Request Report</a>
					</security:authorize>
				</div>
			</li>
		</ul>
		<!-- 
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
    -->
	</div>
</nav>