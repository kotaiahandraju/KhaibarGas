<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

        <div class="clearfix"></div>
             <ol class="breadcrumb">
              <li><a href="index.html">Home</a></li>
               <li>Store</li>
            </ol>
            <div class="clearfix"></div>
        <div class="container">
            
                    
            <div class="row">
            <div class="col-md-10 col-md-offset-1 col-sm-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Add Store</h4>
                        <div class="options">
                        </div>
                    </div>
	                <form:form modelAttribute="storeForm" action="storeSave" class="form-horizontal" method="Post" >
	                <div class="panel-body">
	                <c:if test="${not empty msg}">
	                    	<div class="row">
	                    		<div class="col-sm-4 col-sm-offset-4">
	                    			<div class="form-group">
	                    				<div class="msgcss alert alert-${cssMsg} fadeIn animated" style="text-align: center;">${msg} <span style="color: red;">${msgId}</span></div>
	                    			</div>
	                    		</div>
	                    	</div>
                    	</c:if>
                    	<div class="row">
                    	   
                    			<div class="form-group">
                    			<form:hidden path="id"/>
                   				<%-- <form:hidden  path="storeid" length="3" class="form-control validate" placeholder="Store id"/> --%>
                    			
                    		 <div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Store Name<span class="impColor">*</span></label>
                    				<div class="col-md-6">
                    				<form:input  path="storename" class="form-control validate" placeholder="Store Name"/>
                    				<form:hidden path="id"/>
                    				<form:hidden path="status"/>
                    				</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label ">Store Location<span class="impColor">*</span></label>
                    				<div class="col-md-6">
		                            	<form:input  path="location" class="form-control validate" placeholder="Store Location"/>
								  	</div>
                    			</div>
                    		</div>
                    	</div>
                    </div>
                    </div>
                    
                    <div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar pull-right">
					      			<input class="btn-primary btn" type="submit" value="Submit" id="submit1"/>
					      			<input class="btn-danger btn" type="reset" value="Reset" />
				      			</div>
				      		</div>
				      	</div>
				      </div>
         			</form:form>				    
                </div>
            </div>
        </div>
        <div class="row">
              <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4>Store List</h4>
                            <div class="options">   
                                <a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
                            </div>
                        </div>
                        <div class="panel-body collapse in">
                        <div class="table-responsive" id="tableId" >
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
                                <thead>
                                	<tr><th>Store ID</th><th>Store Name</th><th>Location</th><th>Status</th><th>Action</th></tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                         </div>
                        </div>
                    </div>
                </div>
            </div>

        </div> <!-- container -->




<script type="text/javascript">
var lstOrders =${allObjects};

console.log(lstOrders);
$(function() {
// 	var listOrders=JSON.parse(lstOrders);
	showTableData(lstOrders);
	
});


</script>


<script>

var damageId = 0;
var serviceUnitArray ={};
var data = {};


function showTableData(response){
	
	var table=$('#tableId').html('');
	
	serviceUnitArray = {};
	if(response != undefined && response.length >0){
	var protectType = null;
	var tableHead = '<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">'+
    	'<thead><tr><th>Store ID</th><th>Store Name</th><th>Store Location</th><th>Status</th><th>Action</th></tr>'+
    	"</thead><tbody></tbody></table>";
	$("#tableId").html(tableHead);
	$.each(response,function(i, orderObj) {
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactive' onclick='deleteStore("+ orderObj.id+ ",0)'><i class='fa fa-bell green'></i></a>"
		}else{  
			var deleterow = "<a class='active' onclick='deleteStore("+ orderObj.id+ ",1)'><i class='fa fa-bell-o red'></i></a>"
		}
		
		var edit = "<a class='edit' onclick='editStore("+ orderObj.id+ ")'><i class='fa fa-pencil green'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
			
		var tblRow ="<tr>"
			+ "<td title='"+orderObj.id+"'>" + orderObj.storeid + "</td>"
						+ "<td title='"+orderObj.storename+"'>" + orderObj.storename + "</td>"
						+ "<td title='"+orderObj.location+"'>" + orderObj.location + "</td>"
						+ "<td title='"+orderObj.storeStatus+"'>" + orderObj.storeStatus + "</td>"
						+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;|&nbsp;" + deleterow + "</td>"
						+"</tr>";
				$(tblRow).appendTo("#tableId table tbody");
				//$('.datatables').dataTable({});
			});
	}
}
function editStore(id) {
	$("#id").val(id);
	$("#storename").val(serviceUnitArray[id].storename);
	$("#storeid").val(serviceUnitArray[id].storeid);
	$("#location").val(serviceUnitArray[id].location);
	$("#status").val(serviceUnitArray[id].status);
	$("#submit1").val("Update");
	$(window).scrollTop($('body').offset().top);
}

function deleteStore(id,status) {
	var checkstr=null;
	if(status == 0){
		 checkstr =  confirm('Are you sure you want to Deactivate this?');
	}else{
		 checkstr =  confirm('Are you sure you want to Activate this?');
	}
	if(checkstr == true){
		$.ajax({
					type : "POST",
					url : "storeDelete.htm",
					data :"id="+id+"&status="+status,
					beforeSend : function() {
			             $.blockUI({ message: 'Please wait' });
			          },
					success: function (response) {
		                 if(response != null ){
		                	 $.unblockUI();
		                	//var resJson=JSON.parse(response);
		                	//showTableData(resJson);
		                	//alert("Delete Sucessfully");
		                	//window.location.reload();
		                	}
		                 window.location.reload();
		                 },
		             error: function (e) { 
		            	 $.unblockUI();
							console.log(e);
		             }
				});
	}
}

function dataClear(){
	$("#id").val("");
	$("#storeName").val("");
	$("#storeid").val("");
	$("#location").val("");
}
$("#pageName").text("Stores Master");
$(".stores").addClass("active"); 

</script>