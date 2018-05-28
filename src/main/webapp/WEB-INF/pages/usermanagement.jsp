<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<style>
.editcheck {
width:20px;
height:25px;
}
</style>

        <div class="clearfix"></div>
             <ol class="breadcrumb">
              <li><a href="#">Home</a></li>
               <li>User Management</li>
            </ol>
            <div class="clearfix"></div>
        <div class="container">
            <div class="row">
              <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4>User Management List</h4>
                            <div class="options">   
                                <a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
                            </div>
                        </div>
                        <div class="panel-body collapse in">
                        <input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label>
                        <div class="table-responsive" id="tableId" >
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
                                <thead>
                                	<th>User Name</th><th>Password</th><th>Edit</th><th>Delete</th><th>Mobile app</th><th>Options</th>
                                </thead>
                                <tbody></tbody>
                            </table>
                         </div>
                        </div>
                    </div>
                </div>
            </div>
                    
            <div class="row" id="moveTo">
            <div class="col-md-12 col-sm-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Add Customers</h4>
                        <div class="options">
                        </div>
                    </div>
	                <form:form modelAttribute="usermanagementForm"  action="usermanagement" class="form-horizontal" method="Post" >
	                <div class="panel-body">
	                	<form:hidden path="id"/>
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">User Name<span class="impColor">*</span></label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="userName" class="form-control validate" placeholder="User Name"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Password<span class="impColor">*</span></label>
                    				<div class="col-md-6">
								      	<form:input type="text" path="password" class="form-control validate emailOnly" placeholder="Password"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Edit</label>
                    				<div class="col-md-6">
		                            	<input type="checkbox"  value="1" name="edit" id="edit" class="editcheck"  />
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    		
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Delete</label>
                    				<div class="col-md-6">
		                            	<input type="checkbox"  value="1"   name="delete1" id="delete1" class="editcheck" />
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    		
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Mobile App</label>
                    				<div class="col-md-6">
								      	<input type="checkbox"  value="1"  name="mobileapp" id="mobileapp" class="editcheck" placeholder="Mobile app"/>
								  	</div>
                    			</div>
                    		</div>
                    		
                    </div>
                    <div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar text-center">
					      			<input class="btn-primary btn" type="submit" value="Submit" onclick="submitForm()" id="submit123"/>
					      			<input class="btn-danger btn cancel" type="reset" value="Reset" />
				      			</div>
				      		</div>
				      	</div>
				      </div>
         			</form:form>				    
                </div>
            </div>
            
        </div>

        </div> <!-- container --></div>
        
        
        

<!--

//-->
<script type="text/javascript">
 var listOrders1 =${allObjects};

console.log(listOrders1);
if (listOrders1 != "") {
	showTableData(listOrders1);
}

var damageId = 0;
// var serviceUnitArray ={};
var data = {};


function showTableData(response){
	
	var table=$('#tableId').html('');
	serviceUnitArray = {};
	var protectType = null;
	var tableHead = '<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">'+
    	'<thead><tr><th>User Name</th><th>Password</th><th>Edit</th><th>Delete</th><th>MobileApp</th><th>Status</th><th>Options</th></tr>'+
    	"</thead><tbody></tbody></table>";
	$("#tableId").html(tableHead);
	$.each(response,function(i, orderObj) {
		var editstatus = "--";
		if(orderObj.edit=="1"){
			editstatus ="Enable";
		}else{
			editstatus ="Disable";
		}
		var deletestatus = "--";
		if(orderObj.delete1=="1"){
			deletestatus ="Enable";
		}else{
			deletestatus ="Disable";
		}
	      
		var appstatus = "--";
		if(orderObj.mobileapp=="1"){
			appstatus ="Enable";
		}else{
			appstatus ="Disable";
		}

		if(orderObj.status == "1"){
			var deleterow = "<a class='deactivate' onclick='userDelete("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>"
		}else{  
			var deleterow = "<a class='activate' onclick='userDelete("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>"
		}
		var edit = "<a class='edit editIt' onclick='editUser("+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>"
		
		serviceUnitArray[orderObj.id] = orderObj;
		var tblRow ="<tr>"
			
			+ "<td title='"+orderObj.userName+"'>" + orderObj.userName + "</td>"
			+ "<td title='"+orderObj.password+"'>" + orderObj.password + "</td>"
			+ "<td title='"+editstatus+"'>" + editstatus + "</td>"
			+ "<td title='"+deletestatus+"'>" + deletestatus+ "</td>"
			+ "<td title='"+appstatus+"'>" + appstatus + "</td>"
			+ "<td title='"+orderObj.userstatus+"'>" + orderObj.userstatus + "</td>"
			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>"
			+"</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
	
}  

$('#submit123').click(function(event) {
	validation = true;
	$.each(idArray, function(i, val) {
		var value = $("#" + idArray[i]).val();
		var placeholder = $("#" + idArray[i]).attr('placeholder');
		if (value == null || value == "" || value == "undefined") {
			$('style').append(styleBlock);
			$("#" + idArray[i] ).attr("placeholder", placeholder);
			$("#" + idArray[i] ).css('border-color','#e73d4a');
			$("#" + idArray[i] ).css('color','#e73d4a');
			$("#" + idArray[i] ).addClass('placeholder-style your-class');
			 var id11 = $("#" + idArray[i]+"_chosen").length;
			if ($("#" + idArray[i]+"_chosen").length)
			{
				$("#" + idArray[i]+"_chosen").children('a').css('border-color','#e73d4a');
			}
//			$("#" + idArray[i] + "Error").text("Please " + placeholder);
			validation = false;
		} 
	});
	if(validation) {
		$("#submit123").attr("disabled",true);
		$("#submit123").val("Please wait...");
		$("form").submit();											
		event.preventDefault();
	}else {
		return false;
		event.preventDefault();
	}
});

        
        
        
function editUser(id) {
	$("#id").val(id);
	$("#userName").val(serviceUnitArray[id].userName);
	$("#password").val(serviceUnitArray[id].password);
	$("#edit").val(serviceUnitArray[id].edit);
	$("#delete1").val(serviceUnitArray[id].delete1);
	if(serviceUnitArray[id].delete1 == "1"){
		$("#delete1").prop('checked',true);	
	}else{
		$("#delete1").prop('checked',false);
	}
	if(serviceUnitArray[id].edit == "1"){
		$("#edit").prop('checked',true);	
	}else{
		$("#edit").prop('checked',false);	
	}
	if(serviceUnitArray[id].mobileapp == "1"){
		$("#mobileapp").prop('checked',true);	
	}else{
		$("#mobileapp").prop('checked',false);
	}
	$("#moobileapp").val(serviceUnitArray[id].mobileapp);
	$("#status").val(serviceUnitArray[id].status);
	$("#submit123").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
}

function userDelete(id,status) {
	var checkstr=null;
	if(status == 0){
		 checkstr = confirm('Are you sure you want to Deactivate?');
	}else{
		 checkstr = confirm('Are you sure you want to Activate?');
	}
	
	if(checkstr == true){
		$.ajax({
					type : "POST",
					url : "userDelete.htm",
					data :"id="+id+"&status="+status,
					beforeSend : function() {
			             $.blockUI({ message: 'Please wait' });
			          }, 
					success: function (response) {
						 $.unblockUI();
		                	var resJson=JSON.parse(response);
		                	showTableData(resJson);
		                	tooltip();
		                	//alert("Delete Sucessfully");
		                	//window.location.reload();
		                 window.location.reload();
		                 },
		                 
		             error: function (e) { 
		            	 	$.unblockUI();
							console.log(e);
		             }
				});
		$("#loadAjax").hide();
	}
}

function dataClear(){
	$("#id").val("");
	$("#userName").val("");
	$("#password").val("");
	$("#edit").val("");
	$("#delete1").val("");
	$("#mobileapp").val("");
	
}
function inactiveData() {
	var status="0";
	if($('#inActive').is(":checked") == true){
		status="0";
	}else{
		status="1";
	}
		var formData = new FormData();
		formData.append('status', status);
		
		$.fn.makeMultipartRequest('POST', 'inActiveUser', false,
				formData, false, 'text', function(data) {
			var resJson=JSON.parse(data);
            showTableData(resJson);
            tooltip();
					console.log(resJson);
				});
		
}



$('#edit').click(function(){
	if ($('#edit').prop('checked')==true){ 
		$('#edit').val("1");
    }else{
    	$('#edit').val("0");
    }
	
});
$('#mobileapp').click(function(){
	if ($('#mobileapp').prop('checked')==true){ 
		$('#mobileapp').val("1");
    }else{
    	$('#mobileapp').val("0");
    }
	
});
$('#delete1').click(function(){
	if ($('#delete1').prop('checked')==true){ 
		$('#delete1').val("1");
    }else{
    	$('#delete1').val("0");
    }
	
});
	
	
   

$("#pageName").text("User Management");
$(".customer").addClass("active"); 

</script>