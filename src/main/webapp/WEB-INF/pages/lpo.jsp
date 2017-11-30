<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
 
 
        <div class="clearfix"></div>
             <ol class="breadcrumb">
              <li><a href="#">Home</a></li>
               <li>LPO </li>
            </ol>
            <div class="clearfix"></div>
        <div class="container">
                    
            <div class="row">
            <div class="col-md-10 col-md-offset-1 col-sm-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Add LPOs</h4>
                        <div class="options"></div>
                    </div>
	                <form:form modelAttribute="lpoForm" id="formId" action="lpoSave" class="form-horizontal" method="post" >
                    <div class="panel-body">
	                	<c:if test="${not empty msg}">
	                    	<div class="row">
	                    		<div class="col-sm-4 col-sm-offset-4">
	                    			<div class="form-group">
	                    				<div class="msgcss alert alert-${cssMsg} fadeIn animated" style="text-align: center;">${msg}</div>
	                    			</div>
	                    		</div>
	                    	</div>
                    	</c:if>
                    	<div class="row">
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">LPO Number</label>
                    				<div class="col-md-6">
		                            	<form:hidden path="id"/>
		                            	<form:hidden path="status"/>
								      	<form:input type="text" path="lponumber" class="form-control validate" placeholder="LPO Number"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Item</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="item" class="form-control validate" placeholder="Item"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Remarks</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="remarks" class="form-control validate" placeholder="Remarks"/>
								  	</div>
                    			</div>
                    		</div>
                    	</div>
                    	<div class="row">
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Supplier Name</label>
                    				<div class="col-md-6">
								      	<form:input type="text" path="suppliername" class="form-control validate onlyCharacters" placeholder="Supplier name"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Supplier Address</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="supplieraddress" class="form-control validate" placeholder="Supplier address"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Supplier Contact no</label>
                    				<div class="col-md-6">
		                            	<form:input  path="suppliercontactno" class="form-control validate numericOnly" placeholder="Supplier contact no"/>
								  	</div>
                    			</div>
                    		</div>
                    	</div>
                    	<div class="row">
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Supplier Email</label>
                    				<div class="col-md-6">
								      	<form:input type="email" path="supplieremail" class="form-control validate" placeholder="Supplier email"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Amount</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="amount" class="form-control validate numericOnly" placeholder="amount"/>
								  	</div>
                    			</div>
                    		</div>
                    		
                    		<%-- <div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Made In</label>
                    				<div class="col-md-6">
		                            	<form:input  path="madein" class="form-control validate onlyCharacters" placeholder="Made in"/>
								  	</div>
                    			</div>
                    		</div> --%>
                    	</div>
                    	</div>
                    </div>
                    
                    <div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar pull-right">
					      			<input class="btn-primary btn" type="submit" id="submit1" value="Submit" />
					      			<input class="btn-danger btn cancel" type="reset" id="clearData" onclick="dataClear();" value="Reset" />
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
                            <h4>LPO List</h4>
                            <div class="options">   
                                <a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
                            </div>
                        </div>
                        <div class="panel-body collapse in">
                        <div class="table-responsive" id="tableId" >
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
                                <thead>
                                	<tr><th>LPO Number</th><th>Item</th><th>Supplier name</th><th>Remarks</th><th>Supplier Address</th><th>Supplier Contact no</th><th>Supplier Email</th><th>Amount</th><th>Status</th><th></th></tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                         </div>
                        </div>
                    </div>
                </div>
            </div>

        </div> <!-- container -->

<!-- <script type="text/javascript" src="js/jquery-2.1.3.min.js"></script> -->
<script type="text/javascript">
// var lstOrders =${allObjects};
// console.log(lstOrders);

var listOrders1 = ${allObjects};
if (listOrders1 != "") {
	displayTable(listOrders1);
}

/* $(function() {
// 	var listOrders=JSON.parse(lstOrders);
	showTableData(lstOrders);
	
}); */


// var damageId = 0;
// var data = {};


function displayTable(listOrders) {

	$('#tableId').html('');
	var tableHead = '<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">'
	    	+'<thead><tr><th>LPO Number</th><th>Item</th><th>Supplier name</th><th>Remarks</th><th>Supplier Address</th><th>Supplier Contact no</th><th>Supplier Email</th><th>Amount</th><th>Status</th><th></th></tr>'
	    	+"</thead><tbody></tbody></table>";
	$("#tableId").html(tableHead);
	
	serviceUnitArray ={};
	$.each(listOrders,function(i, orderObj) {
		var edit = "<a class='edit' id='editId' onclick='editLpo("+ orderObj.id+ ")'><i class='fa fa-pencil green'></i></a>"
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactive' onclick='lpoDelete("+ orderObj.id+ ",0)'><i class='fa fa-bell green'></i></a>"
		}else{  
			var deleterow = "<a class='active' onclick='lpoDelete("+ orderObj.id+ ",1)'><i class='fa fa-bell-o red'></i></a>"
		}
			
		serviceUnitArray[orderObj.id] = orderObj;
				
		var tblRow ="<tr>"
			+ "<td title='"+orderObj.lponumber+"'>" + orderObj.lponumber + "</td>"
			+ "<td title='"+orderObj.item+"'>" + orderObj.item + "</td>"
			+ "<td title='"+orderObj.suppliername+"'>" + orderObj.suppliername + "</td>"
			+ "<td title='"+orderObj.remarks+"'>" + orderObj.remarks + "</td>"
			+ "<td title='"+orderObj.suppliercontactno+"'>" + orderObj.suppliercontactno + "</td>"
			+ "<td title='"+orderObj.supplieremail+"'>" + orderObj.supplieremail+ "</td>"
			+ "<td title='"+orderObj.supplieraddress+"'>" + orderObj.supplieraddress + "</td>"
			+ "<td title='"+orderObj.amount+"'>" + orderObj.amount + "</td>"
			+ "<td title='"+orderObj.lpoStatus+"'>" + orderObj.lpoStatus + "</td>"
			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>"
			+"</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
}

function editLpo(id)
{	
	var inputs = $('input[type="text"]');
    inputs.removeAttr('placeholder');
    inputs.css('border','');
    inputs.addClass('default-class');
    inputs.css('color','black ');
	
	$("#id").val(id);
	$("#lponumber").val(serviceUnitArray[id].lponumber);
	$("#supplieraddress").val(serviceUnitArray[id].supplieraddress);
	$("#suppliercontactno").val(serviceUnitArray[id].suppliercontactno);
	$("#item").val(serviceUnitArray[id].item);
	$("#remarks").val(serviceUnitArray[id].remarks);
	$("#amount").val(serviceUnitArray[id].amount);
	$("#suppliername").val(serviceUnitArray[id].suppliername);
	$("#amount").val(serviceUnitArray[id].amount);
	$("#supplieremail").val(serviceUnitArray[id].supplieremail);
	$("#status").val(serviceUnitArray[id].status);
	$("#submit1").val("Update");
	$(window).scrollTop($('body').offset().top);
}

function lpoDelete(id,status)
{
	var checkstr=null;
	if(status == 0){
		 checkstr =  confirm('Are you sure you want to Deactivate this?');
	}else{
		 checkstr =  confirm('Are you sure you want to Activate this?');
	}
	if(checkstr == true)
	{
		$.ajax({
			type : "POST",
			url : "lpoDelete.htm",
			data :"id="+id+"&status="+status,
			success: function (response) {
				if(response != null ){
// 					var resJson=JSON.parse(response);
// 					displayTable(resJson);
// 					alert("Delete Sucessfully");
// 					window.location.reload();
				}
				window.location.reload();
			},
			error: function (e) { 
				console.log(e);
			}
		});
	}
}

function dataClear()
{
	$("#id").val('');
	$("#lponumber").val('');
	$("#item").val('');
	$("#remarks").val('');
	$("#amount").val('');
	$("#suppliername").val("");
	$("#suplieraddress").val("");
	$("#supliercontactno").val("");
	$("#amount").val("");
}

$("#pageName").text("LPO Master");
$(".lpo").addClass("active");
</script>