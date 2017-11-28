<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

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
                    				<label for="focusedinput" class="col-md-4 control-label">Type of Accessory<span class="impColor">*</span></label>
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
                    				<label for="focusedinput" class="col-md-4 control-label">Supplier Name<span class="impColor">*</span></label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="suppliername" class="form-control validate" placeholder="Supplier name"/>
								  	</div>
                    			</div>
                    		</div>
                    	</div>
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Made In<span class="impColor">*</span></label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="madein" class="form-control validate" placeholder="Made in"/>
								  	</div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">LPO No<span class="impColor">*</span></label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="lponumber" class="form-control validate" placeholder="LPO No"/>
								  	</div>
                    			</div>
                    		</div>
                    	</div>
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Remarks</label>
                    				<div class="col-md-6">
		                            	<form:input type="text" path="remarks" id="remarks" class="form-control validate " placeholder="Remarks"/>
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
					      			<input class="btn-primary btn" type="submit" value="Submit" id="submit1" />
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
                                	<tr><th>Type of Accessory</th><th>Supplier name</th><th>Made in</th><th>LPO No</th><th>Remarks</th><th>Action</th></tr>
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
    	'<thead><tr><th>Type of Accessory</th><th>Supplier name</th><th>Made in</th><th>LPO No</th><th>Remarks</th><th>Action</th></tr>'+
    	"</thead><tbody></tbody></table>";
	$("#tableId").html(tableHead);
	$.each(response,function(i, orderObj) {
		
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactive' onclick='deletetruckMaster("+ orderObj.id+ ",0)'><i class='fa fa-bell green'></i></a>"
		}else{  
			var deleterow = "<a class='active' onclick='deletetruckMaster("+ orderObj.id+ ",1)'><i class='fa fa-bell-o red'></i></a>"
		}
		
		var edit = "<a class='edit' onclick='editAccessory("+ orderObj.id+ ")'><i class='fa fa-pencil green'></i></a>"
		var deleterow = "<a class='delete' onclick='deleteAccessory("+ orderObj.id+ ")'><i class='fa fa-trash-o red'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
			
		var tblRow ="<tr>"
						+ "<td title='"+orderObj.typeofaccessory+"'>" + orderObj.typeofaccessory + "</td>"
						+ "<td title='"+orderObj.suppliername+"'>" + orderObj.suppliername + "</td>"
						+ "<td title='"+orderObj.madein+"'>" + orderObj.madein + "</td>"
						+ "<td title='"+orderObj.lponumber+"'>" + orderObj.lponumber + "</td>"
						+ "<td title='"+orderObj.remarks+"'>" + orderObj.remarks + "</td>"
						+ "<td title='"+orderObj.accessoriesStatus+"'>" + orderObj.accessoriesStatus + "</td>"
						+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;|&nbsp;" + deleterow + "</td>"
						
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
		                	window.location.reload();
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

$("#pageName").text("Accessories Master");
$(".accessories").addClass("active"); 

</script>