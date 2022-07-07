$(function(){
	
	switch(menu){
		
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
	
});