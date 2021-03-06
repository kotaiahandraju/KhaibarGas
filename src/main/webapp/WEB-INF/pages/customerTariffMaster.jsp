<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<style>
.vatcheckbox {
width:25px;
height:25px;
padding-left:0px;

}
.vatcheckbox {
margin-top: 10px !important;
    margin-left: -1px !important;
    
}
</style>

<div class="clearfix"></div>
<ol class="breadcrumb">
	<li><a href="#">Home</a></li>
	<li>Tariff Master</li>
</ol>
<div class="clearfix"></div>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Tariff Master Details</h4>
					<div class="options">
						<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
					</div>
				</div>
				<div class="panel-body collapse in">
				<input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label>
					<div class="table-responsive" id="tableId">
						<table class="table table-striped table-bordered datatables"
							id="example">
							<thead>
								<tr>
									<th>Customer Name</th><th>Item</th> <th> Selling Price</th><th>Allowed Discount</th><th>Remarks</th><th>Status</th><th></th>
								</tr>
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
					<h4>Tariff Master Details</h4>
					<div class="options"></div>
				</div>
				<form:form modelAttribute="tariffMaster" class="form-horizontal"	role="form" id="education-form" action="saveCustomerTariffDetails"	method="post">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-6">
							<form:hidden path="id" />
								<%-- <div class="form-group">
									
									<label data-toggle="tooltip" title="Either Cylinder or Accessory Code" class="col-sm-4 control-label required">Asset Code <span class="impColor">*</span></label>
									<div class="col-sm-7">
										<form:input path="assetcode" class="form-control  validate" autocomplete="off" placeholder="Asset Code" required="required" />
										<span class="hasError" id="assetcodeError"></span>
									</div>
								</div> --%>
								<div class="form-group">
									<label class="col-sm-4 control-label required">Customer Name <span class="impColor">*</span></label>
									<div class="col-sm-7">
										<form:select path="customerId" class="form-control  validate" onfocus="removeBorder(this.id)">
											<form:option value="" selected="selected" disabled="disabled">-- Select Customer --</form:option>
											<form:options items="${customers}"></form:options>
										</form:select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label required">Item's <span class="impColor">*</span></label>
									<div class="col-sm-7">
										<form:select path="itemId" class="form-control  validate" onfocus="removeBorder(this.id)">
											<form:option value="" selected="selected" disabled="disabled">-- Select Item --</form:option>
											<form:options items="${items}"></form:options>
										</form:select>
									</div>
								</div>
								<%-- <div class="form-group">
									<label class="col-sm-4 control-label required">Asset Description <span class="impColor">*</span></label>
									<div class="col-sm-7">
										<form:input path="assetdescription" class="form-control  validate" autocomplete="off" placeholder="Asset Description" required="required" />
										<span class="hasError" id="assetdescriptionError"></span>
									</div>
								</div> --%>
								<div class="form-group">
									<label class="col-sm-4 control-label required"> Selling Price(AED) <span class="impColor">*</span></label>
									<div class="col-sm-7">
										<form:input path="rate" class="form-control numericOnly validate" autocomplete="off" placeholder="Selling Price(AED)" required="required" />
										<span class="hasError" id="rateError"></span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label required">Allowed Maximum Discount(%) <span class="impColor">*</span></label>
									<div class="col-sm-7">
										<form:input path="alloweddiscount" class="form-control numericOnly validate" autocomplete="off" placeholder="Allowed Maximum Discount" required="required" />
										<span class="hasError" id="alloweddiscountError"></span>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-2 control-label required">Remarks</label>
									<div class="col-sm-8">
										<form:textarea path="remarks" class="form-control" placeholder="Remarks" rows="6"></form:textarea>
									</div>
								</div>
								<div class="form-group">
								<label class="col-sm-2 control-label required">VAT Allow  </label>
									<label class=" control-label required">
									
									</label>
									 <div class="col-sm-2">
										<form:checkbox path="vatallow" class="vatcheckbox" value="1" placeholder="VAT Allow" ></form:checkbox>
									</div>  
								</div>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class="row">
							<div class="col-sm-12">
								<div class="btn-toolbar text-center">
									<input type="submit" value="Submit" id="submit1"
										class="btn-primary btn" /> <input type="reset" value="Reset"
										class="btn-danger btn cancel" />
								</div>
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">

/* $(document).ready(function() {
    $("body").tooltip({ selector: '[data-toggle=tooltip]' });
}); */

var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
		+ '<thead><tr><th>Customer Name</th><th>Item</th><th> Selling Price</th><th>Allowed Discount</th><th>Remarks</th><th>Vat Allow</th> <th>Status</th><th style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders, function(i, orderObj) {
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactivate' onclick='deleteTariffMasterDetails("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>"
		}else{  
			var deleterow = "<a class='activate' onclick='deleteTariffMasterDetails("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>"
		}
		var edit = "<a class='edit editIt' onclick='editTariffMasterDetails(" + orderObj.id + ")'><i class='fa fa-edit'></i></a>"
		var vatallow = "No";
		if(orderObj.vatallow=="0"){
			 vatallow = "No";
		}else{
			 vatallow = "Yes";
		}
		serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr>"
			+ "<td  title='"+orderObj.customername+"'>" + orderObj.customername + "</td>"
			
			+ "<td class='impFiled' title='"+orderObj.itemName+"'>" + orderObj.itemName + "</td>"
			+ "<td title='"+orderObj.rate+"'>" + orderObj.rate + "</td>"
			+ "<td title='"+orderObj.alloweddiscount+"'>" + orderObj.alloweddiscount + "</td>"
			+ "<td title='"+orderObj.remarks+"'>" + orderObj.remarks + "</td>"
			+ "<td title='"+vatallow+"'>" + vatallow + "</td>"
			+ "<td title='"+orderObj.tariffStatus+"'>" + orderObj.tariffStatus + "</td>"
			+ "<td style='text-align: center;white-space: nowrap;'>"
			+ '<c:if test="${(cacheUserBean.edit == 1)}">'
			 + edit +"&nbsp;&nbsp;"
			+ '</c:if>'
			+ '<c:if test="${(cacheUserBean.delete1 == 1)}">'+ deleterow + '</c:if>'
			+ "</td>"  
			+ "</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
	if(isClick=='Yes') $('.datatables').dataTable();
}

function editTariffMasterDetails(id) {
	$("#id").val(serviceUnitArray[id].id);
	$("#customerId").val(serviceUnitArray[id].customerId);
	$("#itemId").val(serviceUnitArray[id].itemId);
	$("#remarks").val(serviceUnitArray[id].remarks);
	$("#rate").val(serviceUnitArray[id].rate);
	$("#alloweddiscount").val(serviceUnitArray[id].alloweddiscount);
	if(serviceUnitArray[id].vatallow == "1"){
		$("#vatallow1").prop('checked', true);
	}
	$("#submit1").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
}

function deleteTariffMasterDetails(id,status) {
	var checkstr=null;
	if(status == 0){
		 checkstr =  confirm('Are you sure you want to Deactivate?');
	}else{
		 checkstr =  confirm('Are you sure you want to Activate?');
	}
	if (checkstr == true) {
		var formData = new FormData();
		formData.append('id', id);
		formData.append('status', status);
		$.fn.makeMultipartRequest('POST', 'deleteCustomerTariffMasterDetails', false, formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			console.log(jsonobj.allOrders1);
			displayTable(alldata);
			tooltip();
			window.location.reload();
		});
	}
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
		
		$.fn.makeMultipartRequest('POST', 'customerinActiveTariff', false,
				formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			console.log(jsonobj.allOrders1);
			displayTable(alldata);
			tooltip();
				});
		
}
$("#pageName").text("Customer Tariff Master");
$(".customerTariffMaster").addClass("active");
$(".master").addClass("active");
</script>