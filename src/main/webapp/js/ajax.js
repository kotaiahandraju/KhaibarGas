$.fn.makeMultipartRequest = function(method, url, processData, formData,
		contentType, dataType, successFunction) {
	$("#loadAjax").show();
	$.ajax({
		type : method,
		url : url,
		processData : processData,
		data : formData,
		contentType : contentType,
		dataType : dataType,
		
		success : successFunction,
		error :  function(e){console.log(e);}
	});
	$("#loadAjax").hide();
};

$.fn.makeRequest = function (method,url,data,successFunction)
{
	$.ajax
	({
		type		: method,	  			// Ex: 'POST' or 'GET'
		url			: url,       			// Ex:  'home/filelist.html'
		data		: data,     
		success		: successFunction,		// The call back function to be called on success.   
		error 		: function(jqXHR, textStatus){
			if(textStatus === 'timeout'){
				try{
				 $(".loader").hide();
				 $(".loader_storage").parent().html("0 Bytes");
				 $(".loader_storage").hide();
				 displayErrorBanner(ERR_COMMON);
				}catch(e){
					
				}
			}
		}, 
		xhrFields: {withCredentials: true},
		timeout: 30000
	});
};