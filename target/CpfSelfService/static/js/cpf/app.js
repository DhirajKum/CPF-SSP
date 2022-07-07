
$(function(){

	/*switch(menu){
		
	case 'Pending Request':
		$('#claimServ').addClass('active');
		break;
	default:
		$('home').addClass('active');
		break;
	}
	
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');
	if(token.length >0 && header.length >0){
		$(document).ajaxSend(function(e, xhr, options){
			xhr.setRequestHeader(header,token);
		});
	}
*/
	
});	
	
$("#verifyOtp").on('click', function(event){
		
		//$("#phone").prop('readonly', true);
		//var mobile = $("#phone").val().trim();
		var otp = $("#otp").val().trim();
		
		if(otp!=""){		
		var urlVar = appContextPath +'/verify-otp?otp='+otp;
		$.ajax({
			type : 'GET',
			url : urlVar,
			//data : input,
			async : false,
			contentType : "application/json",
			success : function(res) {
				if (res) {
					/* var cAdding = "Approval Process In Progress";
					var cAdded = " Wait for a moment..."
					var dialog = bootbox.dialog({
								title : cAdding,
								message : '<p><i class="fa fa-spin fa-spinner"></i> Loading...</p>'
								});
					dialog.init(function() {
						setTimeout(function() {
							dialog.find('.bootbox-body').html(cAdded);
						}, 3000);
					});
					setTimeout(function() {
						dialog.modal('hide');
					}, 4000); */
					//$("#otpModal").modal('hide');
					$("#pass").prop('readonly', false);
					$("#repass").prop('readonly', false);
					window.history.back();
				}else{
					alert("OTP not valid or Session has been expired !!!");
					window.history.back();
				}
			}
		});
	}else{
		if(mobile==="")
		alert("Phone number required...");
		if(otp==="")
		alert("OTP number required...");
	}
});
	
	