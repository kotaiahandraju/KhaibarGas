<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  


<style>

.dataTables_filter {
display: none; 
}
</style>

	<div class="clearfix"></div>
	<div class="clearfix"></div>
	<ol class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li>Cylinder Report</li>
	</ol>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12">
			<form:form commandName="reportsForm">
				<div class="row">
				
				<div class="panel panel-primary">
				<div class="panel-body">			
				
				
				  	<div class="col-md-4">
						<div class="form-group">
							<label for="focusedinput" class="col-md-4 control-label">Company<span class="impColor">*</span></label>
							<div class="col-md-8">
				        		<form:select path="companyname" class="form-control " onchange="onChangeReports(id,name);" onfocus="removeBorder(this.id)">
				        			<form:option value="">-- Select Company --</form:option>
				        			<form:options items="${companys}"></form:options>
				        		</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label for="focusedinput" class="col-md-5 control-label">Store<span class="impColor">*</span></label>
							<div class="col-md-7">
				        		<form:select path="storename" class="form-control " onchange="onChangeReports(id,name);" onfocus="removeBorder(this.id)">
				        		<form:option value="">-- Store--</form:option>
				        			<form:options items="${stores}"></form:options>
				        		</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-3">
						<div class="form-group">
							<label for="focusedinput" class="col-md-5 control-label">Cylinder Status<span class="impColor">*</span></label>
							<div class="col-md-7">
				        		<form:select path="cylendersstatus" class="form-control " onchange="onChangeReports(id,name);" onfocus="removeBorder(this.id)">
				        		<form:option value="">-- Select --</form:option>
				        			<form:options items="${Cylinderstatus}"></form:options>
				        			</form:select>
							</div>
						</div>
					</div>
					
					</div>
					</div>
				</div>
				
				
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<div class="col-md-6">
						        		
									</div>
								</div>
							</div>
						</div>
						
						<div id="count"></div>
						<div class="panel panel-primary">
							<div class="panel-heading">
		                    	<h4>Cylinders List</h4>
								<div class="options">   
									<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
								</div>
							</div>
							<div class="panel-body collapse in">
		<p><input type="checkbox" id="parent" style="cursor: pointer;"/> <label for="parent" style="cursor: pointer;">Select All/UnSelect All</label></p>
								<div class="table-responsive" id="tableId">
									<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
										<thead>
											<tr><th>Cylinder ID</th>
											<th>LPO No</th>
											<th>Color</th></tr>
										</thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
							
							
							<div class="panel-footer" style="text-align:right;" >
							
							<input type="button" class="btn btn-primary" value="Cylinder Filled Status" onclick="QualityCheck()">
							
							</div>
							
							
							
						</div>
					</div>
					<div class="col-md-12">
					<br><br>
						<!-- <div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label for="focusedinput" class="col-md-3 control-label">Cylinders <span class="impColor">*</span></label>
									<div class="col-md-6">
						        		<div id="displayCylinders"></div>
									</div>
								</div>
							</div>
						</div> -->
						
						
						
						
						
						
						
						
						
					</div>
				</div>
				</form:form>
         	</div>
		</div>
	</div> <!-- container -->

<script type="text/javascript">
$(document).ready(function() {
	  $("#parent").click(function() {
	    $(".child").prop("checked", this.checked);
	    var cylenderId = [];
        $('#tableId :checkbox:checked').each(function(i){
       	 cylenderId[i] = $(this).val();
        }); 
        $("#displayCylinders").text(cylenderId);
//         alert(cylenderId);
	    $('.child').click(function() {
	    	 var cylenderId = [];
	         $('#tableId :checkbox:checked').each(function(i){
	        	 cylenderId[i] = $(this).val();
	         }); 
	         $("#displayCylinders").text(cylenderId);
// 	         alert(cylenderId);
	        if ($('.child:checked').length == $('.child').length) {
	          $('#parent').prop('checked', true);
	        } else {
	          $('#parent').prop('checked', false);
	        }
	      });
	  });
});
/* var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
} */
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Cylinder ID</th><th>LPO NO </th><th>Color</th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
					serviceUnitArray[orderObj.id] = orderObj;
					var tblRow = "<tr >"
							+ "<td title='"+orderObj.cylinderid+"'><input class='child' name='chkbox' type='checkbox' style='width: 21px;' value='"+orderObj.id+"' >"+ orderObj.cylinderid + "</td>"
							+ "<td title='"+orderObj.lponumber+"'>"+ orderObj.lponumber + "</td>"
							+ "<td title='"+orderObj.color+"'>"+ orderObj.color + "</td>"
							+ "</tr >";
					$(tblRow).appendTo("#tableId table tbody");
					
					 $('.child').click(function() {
				    	 var cylenderId = [];
				         $('#tableId :checkbox:checked').each(function(i){
				        	 cylenderId[i] = $(this).val();
				         }); 
				         $("#displayCylinders").text(cylenderId);
//			 	         alert(cylenderId);
				        if ($('.child:checked').length == $('.child').length) {
				          $('#parent').prop('checked', true);
				        } else {
				          $('#parent').prop('checked', false);
				        }
				      });
					});
	if(isClick=='Yes') $('.datatables').dataTable();
}

function QualityCheck(){
	var cylenderId = [];
		$('#tableId :checkbox:checked').each(function(i) {
			cylenderId[i] = $(this).val();
		});
		if (cylenderId.length == 0) {
			alert("Please Select Cylinder");
			return false;
		}
		var stationname = $("#stationname").val();
		var formData = new FormData();
		formData.append("fillingStation", stationname);
		formData.append("cylindetId", cylenderId);
		formData.append("cylinderStatus", 3);
		$.fn.makeMultipartRequest('POST', 'updateCylinderStatus2', false,
				formData, false, 'text', function(data) {
					var jsonobj = $.parseJSON(data);
					alert(jsonobj.msg);
					window.location.reload();
				});

	}

	function searchData() {
		var stationname = $("#stationname").val();
		var quantity = $("#quantity").val();
		var cylinderType = $("#cylinderType").val();
		if (stationname == null || stationname == "undefined" || stationname == "") {
			alert("Please Select Stationname");
			return false;
		}
		if (quantity == null || quantity == "undefined" || quantity == "") {
			alert("Please Enter Quantity");
			return false;
		}
		if (cylinderType == null || cylinderType == "undefined" || cylinderType == "") {
			alert("Please Select CylinderType");
			return false;
		}
		
		var formData = new FormData();
		formData.append('stationname', stationname);
		formData.append('quantity', quantity);
		formData.append('cylinderType', cylinderType);
		$.fn.makeMultipartRequest('POST', 'searchQualityCheck1', false,
				formData, false, 'text', function(data) {
					var jsonobj = $.parseJSON(data);
					var alldata = jsonobj.allOrders1;
					console.log(jsonobj.allOrders1);
					displayTable(alldata);
				});
	}
	function onChangeReports(id,name) {
		var companyname =null;
		var storename = null;
		var cylendersstatus=null;
		if(name== "cylendersstatus"){
			 $("#companyname").val("");
			 cylendersstatus = $("#cylendersstatus").val();
			 $("#storename").val("");
		}
		if(name== "storename"){
			 $("#companyname").val("");
			  $("#cylendersstatus").val("");
			 storename= $("#storename").val();
		}
		if(name== "companyname"){
			companyname= $("#companyname").val();
			 $("#cylendersstatus").val("");
			 $("#storename").val("");
		}
		
		 var companyname = $("#companyname").val();
		var storename = $("#storename").val();
		var cylendersstatus = $("#cylendersstatus").val(); 
		var formData = new FormData();
		formData.append('ownercompany', companyname);
		formData.append('storename', storename);
		formData.append('cylinderstatus', cylendersstatus);
		$.fn.makeMultipartRequest('POST', 'onChangeReports',
				false, formData, false, 'text', function(data) {
			console.log(data);
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.listJson;
			displayTable(alldata);
			var allCount=jsonobj.count
			$.each(allCount,function(i, orderObj) {
			$("#count").text("Cylinder Count: "+orderObj.count1);
			});
			
				});
	}

	$("#pageName").text("Cylinder Report");
	// $(".transactions").addClass("open");
	// $(".transactions").addClass("active");
	$(".CylinderReport").addClass("active");
</script>