<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

        <div class="clearfix"></div>
             <ol class="breadcrumb">
              <li><a href="index.html">Home</a></li>
               <li>CUSTOMER </li>
            </ol>
            <div class="clearfix"></div>
        <div class="container">
            
                    
            <div class="row">
            <div class="col-md-10 col-md-offset-1 col-sm-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Add Customers</h4>
                        <div class="options">
                        </div>
                    </div>
	                <form:form modelAttribute="customerForm" action="customerSave" class="form-horizontal" method="Post" >
	                <c:if test="${not empty msg}">
									<div class="alert alert-success fadeIn animated">${msg}</div>
								</c:if>
                    <div class="panel-body">
                    	<div class="row">
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Customer ID</label>
                    				<div class="col-md-6">
		                            	<form:hidden path="id"/>
								      	<form:input type="text" path="customerid" class="form-control validate" placeholder="Supplier name"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Customer Name</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="customername" class="form-control validate" placeholder="customer name"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Customer Address</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="customeraddress" class="form-control validate" placeholder="Supplier name"/>
								  	</div>
                    			</div>
                    		</div>
                    	</div>
                    	<div class="row">
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">MobileNo</label>
                    				<div class="col-md-6">
		                            	<form:hidden path="id"/>
								      	<form:input type="text" path="mobile" class="form-control validate" placeholder="Supplier name"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Land Line</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="landline" class="form-control " placeholder="Supplier name"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Authorized person</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="authorizedperson" class="form-control validate" placeholder="Supplier name"/>
								  	</div>
                    			</div>
                    		</div>
                    	</div>
                    	<div class="row">
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label">Contact person</label>
                    				<div class="col-md-6">
		                            	<form:hidden path="id"/>
								      	<form:input type="text" path="contactperson" class="form-control validate" placeholder="Supplier name"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-4">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-6 control-label validate">customertype</label>
                    				<div class="col-md-6">
		                            	<form:select path="customertype" class="form-control">
									  		<form:option value="Commercial">COMMERCIAL</form:option>
									  		<form:option value="Domestic">Domestic</form:option>
									  		<form:option value="industrial">industrial</form:option>
								  		</form:select>
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
					      			<input class="btn-danger btn" type="reset" onclick="dataClear();" value="Reset" />
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
                            <h4>Customers List</h4>
                            <div class="options">   
                                <a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
                            </div>
                        </div>
                        <div class="panel-body collapse in">
                        <div class="table-responsive" id="tableId" >
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
                                <thead>
                                	<tr><th>Customer ID</th><th>Customer Name</th><th>Supplier name</th><th>Mobile</th><th>Land Line</th><th>Authorized person</th><th>Contact person</th><th>Customer Type</th><th>Color</th><th></th></tr>
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
<script type='text/javascript' src='${baseurl}/js/custemValidation.js'></script> 
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
	table .clear().draw();
	
	
	serviceUnitArray = {};
	if(response != undefined && response.length >0){
	var protectType = null;
	var tableHead = '<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">'+
    	'<thead><tr><th>Customer ID</th><th>Customer Name</th><th>Supplier name</th><th>Mobile</th><th>Land Line</th><th>Authorized person</th><th>Contact person</th><th>Customer Type</th><th></th></tr>'+
    	"</thead><tbody></tbody></table>";
	$("#tableId").html(tableHead);
	$.each(response,function(i, orderObj) {
		var edit = "<a class='edit' onclick='editCustomer("+ orderObj.id+ ")'><i class='fa fa-pencil green'></i></a>"
		var deleterow = "<a class='delete' onclick='customerDelete("+ orderObj.id+ ")'><i class='fa fa-trash-o red'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
			
		var tblRow ="<tr>"
			+ "<td title='"+orderObj.id+"'>" + orderObj.customerid + "</td>"
						+ "<td title='"+orderObj.customername+"'>" + orderObj.customername + "</td>"
						+ "<td title='"+orderObj.customeraddress+"'>" + orderObj.customeraddress + "</td>"
						+ "<td title='"+orderObj.mobile+"'>" + orderObj.mobile + "</td>"
						+ "<td title='"+orderObj.landline+"'>" + orderObj.landline + "</td>"
						+ "<td title='"+orderObj.authorizedperson+"'>" + orderObj.authorizedperson + "</td>"
						+ "<td title='"+orderObj.contactperson+"'>" + orderObj.contactperson + "</td>"
						+ "<td title='"+orderObj.customertype+"'>" + orderObj.customertype + "</td>"
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
function editCustomer(id) {
	$("#id").val(id);
	$("#customerid").val(serviceUnitArray[id].customerid);
	$("#customername").val(serviceUnitArray[id].customername);
	$("#customeraddress").val(serviceUnitArray[id].customeraddress);
	$("#mobile").val(serviceUnitArray[id].mobile);
	$("#landline").val(serviceUnitArray[id].landline);
	$("#authorizedperson").val(serviceUnitArray[id].authorizedperson);
	$("#contactperson").val(serviceUnitArray[id].contactperson);
	$("#customertype").val(serviceUnitArray[id].customertype);
	
	
	$(window).scrollTop($('body').offset().top);
}

function customerDelete(id) {
	var checkstr =  confirm('Are you sure you want to delete this?');
	if(checkstr == true){
		$.ajax({
					type : "POST",
					url : "customerDelete.htm",
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

function dataClear(){
	$("#id").val("");
	$("#customerid").val("");
	$("#customername").val();
	$("#customeraddress").val("");
	$("#mobile").val("");
	$("#landline").val("");
	$("#authorizedperson").val("");
	$("#contactperson").val("");
	$("#customertype").val("");
}
$("#pageName").text("Customer");
$(".customer").addClass("active"); 

</script>