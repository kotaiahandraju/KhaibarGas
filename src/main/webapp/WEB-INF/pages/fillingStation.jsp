<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

			<div class="clearfix"></div>
			<ol class="breadcrumb">
				<li><a href="index.html">Home</a></li>
				<li>Filling station</li>
			</ol>
			<div class="clearfix"></div>
			<div class="container">
				<div class="row">
					<div class="col-md-10 col-md-offset-1 col-sm-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>Add Filling Station</h4>
								<div class="options"></div>
							</div>
						<form:form class="form-horizontal" 	modelAttribute="fillingStationForm" role="form" id="fillingstation-form" action="addfillingstation" method="post">
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
	                    		<div class="col-md-6">
	                    			<div class="form-group">
	                    				<form:hidden path="id"/>
										<label for="focusedinput" class="col-md-4 control-label">Station Number<span class="impColor">*</span></label>
										<div class="col-md-6">
											<form:input path="unitpoint" class="form-control validate" placeholder="station number" />	
											<span class="hasError" id="unitpointError"></span>
									    </div>
	                    			</div>
	                    		</div>
	                    		<div class="col-md-6">
	                    			<div class="form-group">
										<label for="focusedinput" class="col-md-4 control-label">Station Name<span class="impColor">*</span></label>
										<div class="col-md-6">
											<form:input path="stationname" class="form-control validate" placeholder="station Name" />	
											<span class="hasError" id="stationnameError"></span>
									    </div>
	                    			</div>
	                    		</div>
                    		</div>
                    		<div class="row">
	                    		<div class="col-md-6">
	                    			<div class="form-group">
										<label for="focusedinput" class="col-md-4 control-label">Opening Balance<span class="impColor">*</span></label>
										<div class="col-md-6">
											<form:input path="gasavailability" class="form-control validate numericOnly" placeholder="opening Balance" />	
											<span class="hasError" id="gasavailabilityError"></span>
									    </div>
	                    			</div>
	                    		</div>
	                    		<div class="col-md-6">
	                    			<div class="form-group">
										<label for="focusedinput" class="col-md-4 control-label ">Quantity<span class="impColor">*</span></label>
										<div class="col-md-6">
											<form:input path="quantity" type="text" class="form-control validate numericOnly" placeholder="quantity" />	
											<span class="hasError" id="quantityError"></span>
									    </div>
	                    			</div>
	                    		</div>
                    		</div>
                    		<div class="row">
	                    		<div class="col-md-6">
	                    			<div class="form-group">
										<label for="focusedinput" class="col-md-4 control-label ">Capacity<span class="impColor">*</span></label>
										<div class="col-md-6">
											<form:input path="gascapacity" class="form-control  validate numericOnly"	placeholder="capacity" />	
											<span class="hasError" id="gascapacityError"></span>
									    </div>
	                    			</div>
	                    		</div>
	                    		<div class="col-md-6">
	                    			<div class="form-group">
										<label for="focusedinput" class="col-md-4 control-label ">Filling 	Machines<span class="impColor">*</span> </label>
										<div class="col-md-6">
											<form:input path="numberoffillingmachines"	class="form-control  validate  numericOnly " placeholder="filling machines" />	
											<span class="hasError" id="numberoffillingmachinesError"></span>
									    </div>
	                    			</div>
	                    		</div>
                    		</div>
                    		<div class="row">
	                    		<div class="col-md-6">
	                    			<div class="form-group">
										<label for="focusedinput" class="col-md-4 control-label ">Closing Balance<span class="impColor">*</span> </label>
										<div class="col-md-6">
											<form:input path="availablegas" class="form-control  validate numericOnly" 	placeholder="closing Balance in gasTank" />	
											<span class="hasError" id="availablegasError"></span>
									    </div>
	                    			</div>
	                    		</div>
                    		</div>
                    	</div>

						<div class="panel-footer">
					      	<div class="row">
					      		<div class="col-sm-12">
					      			<div class="btn-toolbar  pull-right">
						      			<input type="submit" id="submit1" value="Submit" class="btn-primary btn"/>
						      			<input type="reset" value="Reset" class="btn-danger btn"/>
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
							<h4>Filling Stations List</h4>
							<div class="options">
								<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
							</div>
						</div>
						<div class="panel-body collapse in">
							<div class="table-responsive" id="tableId">
								<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
									<thead>
										<tr>
											<th>Opening Balance</th><th>Filling Machines</th><th>Quantity</th><th>Capacity</th>
											<th>Closing Balance</th><th>Station Name</th><th>Unit Point</th><th>status</th><th></th>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
			<!-- container -->
</div>
		<!-- #wrap -->
	
	<!-- page-content -->

</body>
<%-- <script type='text/javascript' src='${baseurl }/js/custemValidation.js'></script>  --%>
<script type="text/javascript">


var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Opening Balance</th><th>Filling Machines</th><th>Quantity</th><th>Capacity</th><th>Closing Balance</th><th>Station Name</th><th>Station Number</th><th>Status</th><th style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
		
					if(orderObj.status == "1"){
						var deleterow = "<a class='deactive' onclick='deleteCylinder("+ orderObj.id+ ",0)'><i class='fa fa-bell green'></i></a>"
					}else{  
						var deleterow = "<a class='active' onclick='deleteCylinder("+ orderObj.id+ ",1)'><i class='fa fa-bell-o red'></i></a>"
					}
					var edit = "<a class='edit' onclick='editCylinder("	+ orderObj.id+ ")'><i class='fa fa-pencil green'></i></a>"
					serviceUnitArray[orderObj.id] = orderObj;
					var tblRow = "<tr >"
							+ "<td title='"+orderObj.gasavailability+"'>"+ orderObj.gasavailability + "</td>"
							+ "<td title='"+orderObj.numberoffillingmachines+"'>"+ orderObj.numberoffillingmachines + "</td>"
							+ "<td title='"+orderObj.quantity+"'>"+ orderObj.quantity + "</td>"
							+ "<td title='"+orderObj.gascapacity+"'>"+ orderObj.gascapacity + "</td>"
							+ "<td title='"+orderObj.availablegas+"'>"+ orderObj.availablegas+ "</td>"
							+ "<td title='"+orderObj.stationname+"'>"+ orderObj.stationname + "</td>"
							+ "<td title='"+orderObj.unitpoint+"'>"+ orderObj.unitpoint+ "</td>"
							+ "<td title='"+orderObj.fillingStatus+"'>"+ orderObj.fillingStatus + "</td>"
							+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
							+ "</tr >";
					$(tblRow).appendTo("#tableId table tbody");
					});
	
}


function editCylinder(id) {
	$("#id").val(serviceUnitArray[id].id);
	$("#gasavailability").val(serviceUnitArray[id].gasavailability);
	$("#numberoffillingmachines").val(serviceUnitArray[id].numberoffillingmachines);
	$("#quantity").val(serviceUnitArray[id].quantity);
	$("#gascapacity").val(serviceUnitArray[id].gascapacity);
	$("#availablegas").val(serviceUnitArray[id].availablegas);
	$("#stationname").val(serviceUnitArray[id].stationname);
	$("#unitpoint").val(serviceUnitArray[id].unitpoint);
	$("#submit1").val("Update");
	$(window).scrollTop($('body').offset().top);
	
	
	
	
	
//	$(window).scrollTop($('#addForm').offset().top);
	}
function deleteCylinder(id,status){
	var checkstr=null;
	if(status == 0){
		 checkstr =  confirm('Are you sure you want to Deactivate this?');
	}else{
		 checkstr =  confirm('Are you sure you want to Activate this?');
	}
	if(checkstr == true){
	var formData = new FormData();
     formData.append('id', id);
     formData.append('status', status);
	$.fn.makeMultipartRequest('POST', 'deletefillingstation', false,
			formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		alert(jsonobj.message);
		var alldata = jsonobj.allOrders1;
		console.log(jsonobj.allOrders1);
		displayTable(alldata);
	});
	}
	
}
$("#pageName").text("Filling Station Master");
$(".fillingStation").addClass("active"); 
</script>


</html>