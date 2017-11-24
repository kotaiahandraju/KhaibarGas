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
              <h1>Store</h1>
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
	                <c:if test="${not empty msg}">
									<div class="alert alert-success fadeIn animated">${msg}</div>
								</c:if>
	                <!-- <div id="errorMsg" class="alert alert-success fadeIn animated" style="display: none"></div> -->
                    <div class="panel-body">
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Store Name</label>
                    				<div class="col-md-6">
                    				<form:input type="text" path="storename" class="form-control" placeholder="Supplier name"/>
                    				<form:hidden path="id"/>
                    				</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Store Location</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="location" class="form-control" placeholder="Supplier name"/>
								  	</div>
                    			</div>
                    		</div>
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
                            <h4>Store List</h4>
                            <div class="options">   
                                <a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
                            </div>
                        </div>
                        <div class="panel-body collapse in">
                        <div class="table-responsive" id="tableId" >
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
                                <thead>
                                	<tr><th>Store ID</th><th>Store Name</th><th>Location</th><th>Color</th></tr>
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
<script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>

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
    	'<thead><tr><th>Store ID</th><th>Store Name</th><th>Location</th><th>Color</th></tr>'+
    	"</thead><tbody></tbody></table>";
	$("#tableId").html(tableHead);
	$.each(response,function(i, orderObj) {
		var edit = "<a class='edit' onclick='editStore("+ orderObj.id+ ")'><i class='fa fa-pencil green'></i></a>"
		var deleterow = "<a class='delete' onclick='deleteStore("+ orderObj.id+ ")'><i class='fa fa-trash-o red'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
			
		var tblRow ="<tr>"
			+ "<td title='"+orderObj.id+"'>" + orderObj.storeid + "</td>"
						+ "<td title='"+orderObj.storename+"'>" + orderObj.storename + "</td>"
						+ "<td title='"+orderObj.location+"'>" + orderObj.location + "</td>"
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
				//$('.datatables').dataTable({});
			});
	}
}
function editStore(id) {
	$("#id").val(id);
	$("#storename").val(serviceUnitArray[id].storename);
	$("#storeid").val(serviceUnitArray[id].storeid);
	$("#location").val(serviceUnitArray[id].location);
	$(window).scrollTop($('body').offset().top);
}

function deleteStore(id) {
	var checkstr =  confirm('Are you sure you want to delete this?');
	if(checkstr == true){
		$.ajax({
					type : "POST",
					url : "storeDelete.htm",
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
	$("#id").val("");
	$("#storeName").val("");
	$("#storeid").val("");
	$("#location").val("");
}


</script>