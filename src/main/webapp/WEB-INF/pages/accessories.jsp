<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<div id="page-content">
    <div id="wrap">
        <div id="page-heading" class="row">
        <div class="col-md-6">
              <h1>Accessories</h1>
              </div>
              <div class="col-md-6">
              <div class="options">
                <div class="btn-toolbar">
                    <a href="#" class="btn btn-danger "><span>123456</span><br />Cylinders</a>
                    <a href="#" class="btn btn-warning"><span>223456</span><br />Customers</a>
                    <a href="#" class="btn btn-info"><span>123456</span><br />Gas in Kgs</a>
                </div>
            </div>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="clearfix"></div>
             <ol class="breadcrumb">
              <li><a href="index.html">Home</a></li>
               <li>Accessories</li>
            </ol>
            <div class="clearfix"></div>
        <div class="container">
            
                    
            <div class="row">
            <div class="col-md-10 col-md-offset-1 col-sm-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Add Accessories</h4>
                        <div class="options">
                        </div>
                    </div>
	                <form:form modelAttribute="accessorForm" action="accessoriesSave" class="form-horizontal" method="Post" >
	                <c:if test="${not empty msg}">
									<div class="alert alert-success fadeIn animated">${msg}</div>
								</c:if>
                    <div class="panel-body">
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Type of Accessory</label>
                    				<div class="col-md-6">
		                            	<form:hidden path="id"/>
								      	<form:select path="typeofaccessory" class="form-control">
									  		<form:option value="Industrial">Industrial</form:option>
									  		<form:option value="Commercial">Commercial</form:option>
									  		<form:option value="Domestic">Domestic</form:option>
								  		</form:select>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Supplier Name</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="suppliername" class="form-control" placeholder="Supplier name"/>
								  	</div>
                    			</div>
                    		</div>
                    	</div>
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Made In</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="madein" class="form-control" placeholder="Made in"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">LPO No</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="lponumber" class="form-control" placeholder="LPO No"/>
								  	</div>
                    			</div>
                    		</div>
                    	</div>
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Remarks</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="remarks" id="remarks" class="form-control" placeholder="Remarks"/>
								  	</div>
                    			</div>
                    		</div>
                    		<!-- <div class="col-md-6">
                    			<div class="form-group">
                    				<div class="col-md-offset-4 col-md-6">
		                            	<input class="btn-primary btn" type="submit" value="Submit" />
					      				<input class="btn-danger btn" type="reset" value="Reset" />
								  	</div>
                    			</div>
                    		</div> -->
                    	</div>
                    </div>
                    
                    <div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar pull-right">
					      			<input class="btn-primary btn" type="submit" value="Submit" />
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
                            <h4>Accessories List</h4>
                            <div class="options">   
                                <a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
                            </div>
                        </div>
                        <div class="panel-body collapse in">
                        <div class="table-responsive" id="tableId" >
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
                                <thead>
                                	<tr><th>Type of Accessory</th><th>Supplier name</th><th>Made in</th><th>LPO No</th><th>Remarks</th><th></th></tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                         </div>
                        </div>
                    </div>
                </div>
            </div>

        </div> <!-- container -->
    </div> <!-- #wrap -->
</div> <!-- page-content -->

<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
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
// 	$("#userData table ").remove();
	//$("#userData table tr").remove();
	$('#tableId').html('');
	serviceUnitArray = {};
	if(response != undefined && response.length >0){
	var protectType = null;
	var tableHead = '<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">'+
    	'<thead><tr><th>Type of Accessory</th><th>Supplier name</th><th>Made in</th><th>LPO No</th><th>Remarks</th><th></th></tr>'+
    	"</thead><tbody></tbody></table>";
	$("#tableId").html(tableHead);
	$.each(response,function(i, orderObj) {
		var edit = "<a class='edit' onclick='editAccessory("+ orderObj.id+ ")'><i class='fa fa-pencil green'></i></a>"
		var deleterow = "<a class='delete' onclick='deleteAccessory("+ orderObj.id+ ")'><i class='fa fa-trash-o red'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
			
		var tblRow ="<tr>"
						+ "<td title='"+orderObj.typeofaccessory+"'>" + orderObj.typeofaccessory + "</td>"
						+ "<td title='"+orderObj.suppliername+"'>" + orderObj.suppliername + "</td>"
						+ "<td title='"+orderObj.madein+"'>" + orderObj.madein + "</td>"
						+ "<td title='"+orderObj.lponumber+"'>" + orderObj.lponumber + "</td>"
						+ "<td title='"+orderObj.remarks+"'>" + orderObj.remarks + "</td>"
						+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;|&nbsp;" + deleterow + "</td>"
						
						/* + "<td><a href='javascript:void(0)' id='"
						+ orderObj.suplierId
						+"'onclick='editProduct(this.id)'  class='delRec' href='#'>Edit</a>"
						+ '</td>'
						
						+ "<td ><a href='javascript:void(0)' id='"
						+ orderObj.suplierId
						+"'onclick='deleteProduct(this.id)'  class='delRec' href='#'>Delete</a>"
						+ "</td>" */
						+"</tr>";
				$(tblRow).appendTo("#tableId table tbody");
			});
	}
}
function editAccessory(id) {
	$("#id").val(id);
	$("#typeofaccessory").val(serviceUnitArray[id].typeofaccessory);
	$("#suppliername").val(serviceUnitArray[id].suppliername);
	$("#madein").val(serviceUnitArray[id].madein);
	$("#lponumber").val(serviceUnitArray[id].lponumber);
	$("#remarks").val(serviceUnitArray[id].remarks);
	$(window).scrollTop($('body').offset().top);
}

function deleteAccessory(id) {
	var checkstr =  confirm('Are you sure you want to delete this?');
	if(checkstr == true){
		$.ajax({
					type : "POST",
					url : "deleteDamage.htm",
					data :"id="+id,
					success: function (response) {
		                 if(response != null ){
		                	var resJson=JSON.parse(response);
		                	showTableData(resJson);
		                	alert("Delete Sucessfully");
		                	}
		                 },
		             error: function (e) { 
							console.log(e);
		             }
				});
	}
}

function damageDataClear(){
 $('#productId').val("");
 $('#quantity').val("");
 $('#description').val("");
}


</script>