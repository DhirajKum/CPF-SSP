<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="STYLESHEET" type="text/css" href="/EPFO/COMMON/CSS/custom.css">
<script src="/EPFO/COMMON/JS/jquery-3.5.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/EPFO/COMMON/JS/bootstrap.min.js"></script>
<script src="/EPFO/COMMON/JS/jquery.validate.min.js"></script>
</head>
<body>
<jsp:include page ="../COMMON/JSP/header.jsp"/> 
<nav class="navbar navbar-expand-md navbar-light bg-light" style="background-color: #048282!important;">
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link text-white" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      
       <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          View
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Manage
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
      
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Online Services
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="#">Action</a>
          <a class="dropdown-item" href="#">Another action</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
    </ul>
    
  </div>
</nav>



<div class="container-fluid">
    <div class="row profile profile-card">
		<div class="col-md-12">
		<h5 class="main-heading">
			<svg width="1em" height="1em" viewBox="0 0 16 16" class="bi bi-person-fill" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
			<path fill-rule="evenodd" d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
			</svg> 
			Manage Contact Details
		</h5>
		
        <div class="col-md-12 content-pad-0 data-box-header">
		<div class="row">
			<div class="col-md-4"><b>Registered Mobile No.</b></div>
			<div class="col-md-8">99XXXXXX63</div>
		</div>
		<div class="row marginT20">
			<div class="col-md-12">
			<input type="checkbox" name="change_contact_no" class="change-contact-no"> &nbsp;&nbsp; Change Mobile No.
			</div>
		</div>
		<div class="row marginT20 change-contact-field hide">
			<div class="col-md-12">
			  <form action="#" name="change-contact-no">
				<div class="form-group row">
					<label for="inputContect" class="col-sm-3 col-form-label"><b>New Mobile No. <span class="red">*</span></b></label>
					<div class="col-sm-4">
						<input type="text" name="new_mobile_no" class="form-control" id="inputContect" placeholder="Enter new mobile no." maxlength="10">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputreContect" class="col-sm-3 col-form-label"><b>Re-enter New Mobile No. <span class="red">*</span></b></label>
					<div class="col-sm-4">
						<input type="text" name="renew_mobile_no" class="form-control" id="inputreContect" placeholder="Re-enter new mobile No." maxlength="10">
					</div>
				</div>
				<button type="submit" class="btn btn-primary btn-sm">Get Authorization Pin</button> <a type="submit" class="btn btn-primary btn-sm reset-form">Reset</a>
			  </form>

			</div>
		</div>


		</div>

<script>		
$(document).ready(function() {
	$(".change-contact-field").hide(); 
	$(document).delegate('.change-contact-no','click',function(){ if($(this).is(":checked")){ $(".change-contact-field").show(); }else{ $(".change-contact-field").hide(); } });      

    $(".reset-form").click(function (e) {
		e.preventDefault();
         $('form[name="change-contact-no"]').find('input:text').val('');  
    });
    $('form[name="change-contact-no"]').validate({
        rules: {
            new_mobile_no: {required: true, number: true, minlength: 10, maxlength: 10},
			renew_mobile_no: {required: true, number: true, minlength: 10, maxlength: 10}
        },
        messages: {
            new_mobile_no: {
                required: 'Enter new mobile no.',
                minlength: 'Mobile no. cannot smaller than 10 digits ',
                number: "Mobile no. should contain digits only"
            },
            renew_mobile_no: {
                required: 'Please re-enter new mobile no.',
                minlength: 'Mobile no. cannot smaller than 10 digits ',
                number: "Mobile no. should contain digits only"
            }			
        },
        submitHandler: function (form) {
            form.submit();
        }
    });	
});	
	
</script>		
		

		</div>
	</div>
</div>
</body>
</html>