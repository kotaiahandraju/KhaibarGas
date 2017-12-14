<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

	<div class="clearfix"></div>
	<ol class="breadcrumb">
    	<li><a href="#">Home</a></li>
		<li>Items</li>
	</ol>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Items List</h4>
						<div class="options">   
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
                        <div class="table-responsive" id="tableId" >
							<table class="table table-striped table-bordered datatables" id="example">
								<thead>
									<tr><th>Item Name</th><th>Item Type</th><th>Description</th><th>Price</th><th>Status</th><th></th></tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
                    
		<div class="row" id="moveTo">
			<div class="col-md-10 col-md-offset-1 col-sm-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Add Item</h4>
					</div>
					<form:form modelAttribute="itemsForm" action="itemsSave" class="form-horizontal" method="Post" >
					<div class="panel-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<form:hidden path="id"/>
									<label for="focusedinput" class="col-md-4 control-label">Item Name <span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="name" class="form-control validate" placeholder="Item Name"/>
									</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Item Type <span class="impColor">*</span></label>
                    				<div class="col-md-6">
	                    				<form:select path="itemType" class="form-control validate" onfocus="removeBorder(this.id)">
		                    				<form:option value="">-- Select Item Type --</form:option>
		                    				<form:option value="Cylinder">Cylinder</form:option>
		                    				<form:option value="Truck">Truck</form:option>
		                    				<form:option value="Accessories">Accessories</form:option>
		                    				<form:option value="GAS">GAS</form:option>
		                    				<form:option value="Equipment">Equipment</form:option>
	                    				</form:select>
                    				</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Price <span class="impColor">*</span></label>
                    				<div class="col-md-6">
		                            	<form:input  path="price" class="form-control numericOnly validate" placeholder="Price"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Description</label>
                    				<div class="col-md-6">
		                            	<form:textarea path="description" class="form-control" placeholder="Description"></form:textarea>
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
					      			<input class="btn-danger btn cancel" type="reset" value="Reset" />
				      			</div>
				      		</div>
				    	</div>
					</div>
         			</form:form>				    
                </div>
            </div>
        </div>
	</div> <!-- container -->

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
    	'<thead><tr><th>Item Name</th><th>Item Type</th><th>Description</th><th>Price</th><th>Status</th><th></th></tr>'+
    	"</thead><tbody></tbody></table>";
	$("#tableId").html(tableHead);
	$.each(response,function(i, orderObj) {
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactivate' onclick='deleteItem("+ orderObj.id+ ",0)'><i class='fa fa-bell green'></i></a>"
		}else{  
			var deleterow = "<a class='activate' onclick='deleteItem("+ orderObj.id+ ",1)'><i class='fa fa-bell-o red'></i></a>"
		}
		var edit = "<a class='edit' onclick='editItem("+ orderObj.id+ ")'><i class='fa fa-pencil green'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
		var tblRow ="<tr>"
			+ "<td title='"+orderObj.name+"'>" + orderObj.name + "</td>"
			+ "<td title='"+orderObj.itemType+"'>" + orderObj.itemType + "</td>"
			+ "<td title='"+orderObj.description+"'>" + orderObj.description + "</td>"
			+ "<td title='"+orderObj.price+"'>" + orderObj.price + "</td>"
			+ "<td title='"+orderObj.Status+"'>" + orderObj.itemstatus + "</td>"
			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>"
			+"</tr>";
		$(tblRow).appendTo("#tableId table tbody");
// 		$('.datatables').dataTable({});
	});
}

function editItem(id){
	$("#id").val(id);
	$("#name").val(serviceUnitArray[id].name);
	$("#description").val(serviceUnitArray[id].description);
	$("#itemType").val(serviceUnitArray[id].itemType);
	$("#price").val(serviceUnitArray[id].price);
	$("#status").val(serviceUnitArray[id].status);
	$("#submit1").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
}

function deleteItem(id,status){
	var checkstr=null;
	if(status == 0){
		 checkstr = confirm('Are you sure you want to Deactivate?');
	}else{
		 checkstr = confirm('Are you sure you want to Activate?');
	}
	if(checkstr == true){
		$.ajax({
			type : "POST",
			url : "itemDelete.htm",
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
	$("#Name").val("");
	$("#s").val("");
	$("#location").val("");
}
$("#pageName").text("Item Master");
$(".items").addClass("active"); 
</script>