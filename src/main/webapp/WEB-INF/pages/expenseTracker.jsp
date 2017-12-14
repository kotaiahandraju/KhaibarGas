<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

	<div class="clearfix"></div>
	<ol class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li>Expense Tracker</li>
	</ol>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Expense Tracker Details</h4>
						<div class="options">
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
						<div class="table-responsive" id="tableId">
							<table class="table table-striped table-bordered datatables" id="example">
								<thead>
									<tr>
										<th>Account Head</th><th>Date of Expense</th><th>Item description</th>
										<th>Payment Type</th><th>Remarks</th><th></th>
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
			<div class="col-md-10 col-md-offset-1 col-sm-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Expense Tracker </h4>
					</div>
					<form:form class="form-horizontal" modelAttribute="expensiveTrackerForm" role="form" id="expensiveTrackerForm-form" action="expenseTrackerSave" method="post">
					<div class="panel-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<form:hidden path="id" />
									<label class="col-sm-4 control-label required">Account Head <span class="impColor">*</span></label>
									<div class="col-sm-6">
										<form:input path="accountHead" class="form-control validate" autocomplete="off" placeholder="Account Head" required="required" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Date of Expense <span class="impColor">*</span></label>
									<div class="col-sm-6">
										<form:input path="dateOfExpense" class="form-control validate" autocomplete="off" placeholder="Date of Expense" required="required" readonly="true"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Item Description <span class="impColor">*</span></label>
									<div class="col-sm-6">
										<form:input path="itemDescription" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Item Description" required="required" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Payment Type <span class="impColor">*</span></label>
									<div class="col-sm-6">
										<form:input path="paymentType" class="form-control validate" autocomplete="off" placeholder="Payment Type" required="required" />
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Remarks</label>
									<div class="col-sm-6">
										<form:textarea path="paymentRemarks" class="form-control onlyCharacters" placeholder="Remarks"></form:textarea>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="panel-footer">
						<div class="row">
							<div class="col-sm-12">
								<div class="btn-toolbar  pull-right">
									<input type="submit" value="Submit" id="submit1" class="btn-primary btn" />
									<input type="reset" value="Reset" class="btn-danger btn cancel"  />
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
$(function() {
	$("#dateOfExpense").datepicker({
		dateFormat : "dd-MM-yy",
		changeDate : true,
		changeMonth : true,
		changeYear : true,
	});
});

var listOrders1 = ${allObjects};
if (listOrders1 != "") {
	displayTable(listOrders1);
}

function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Account Head</th><th>Date of Expense</th><th>Item description</th><th>Payment Type</th><th>Remarks</th><th>Status</th><th></th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders, function(i, orderObj){
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactivate' onclick='trckerDelete("+ orderObj.id+ ",0)'><i class='fa fa-bell green'></i></a>"
		}else{  
			var deleterow = "<a class='activate' onclick='trckerDelete("+ orderObj.id+ ",1)'><i class='fa fa-bell-o red'></i></a>"
		}
		var edit = "<a class='edit' onclick='editExpensiveTracker("+ orderObj.id + ")'><i class='fa fa-pencil green'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr >"
			+ "<td title='"+orderObj.accountHead+"'>" + orderObj.accountHead + "</td>"
			+ "<td title='"+orderObj.dateOfExpense+"'>" + orderObj.dateOfExpense + "</td>"
			+ "<td title='"+orderObj.itemDescription+"'>" + orderObj.itemDescription + "</td>"
			+ "<td title='"+orderObj.paymentType+"'>" + orderObj.paymentType + "</td>"
			+ "<td title='"+orderObj.paymentRemarks+"'>" + orderObj.paymentRemarks + "</td>"
			+ "<td title='"+orderObj.trackrstatus+"'>" + orderObj.trackrstatus + "</td>"
			+ "<td style='text-align: center;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>"
			+ "</tr >";
		$(tblRow).appendTo("#tableId table tbody");
	});
}

function editExpensiveTracker(id) {
	$("#id").val(serviceUnitArray[id].id);
	$("#accountHead").val(serviceUnitArray[id].accountHead);
	$("#dateOfExpense").val(serviceUnitArray[id].dateOfExpense);
	$("#dateOfExpense").val(serviceUnitArray[id].dateOfExpense);
	$("#itemDescription").val(serviceUnitArray[id].itemDescription);
	$("#paymentType").val(serviceUnitArray[id].paymentType);
	$("#paymentRemarks").val(serviceUnitArray[id].paymentRemarks);
	$("#submit1").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
}

function trckerDelete(id,status) {
	var checkstr=null;
	if(status == 0){
		checkstr = confirm('Are you sure you want to Deactivate?');
	}else{
		checkstr = confirm('Are you sure you want to Activate?');
	}
	if (checkstr == true) {
		var formData = new FormData();
		formData.append('id', id);
		formData.append('status', status);
		$.fn.makeMultipartRequest('POST', 'trckerDelete', false, formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
// 			var alldata = jsonobj.allOrders1;
// 			console.log(jsonobj.allOrders1);
// 			displayTable(alldata);
			window.location.reload();
		});
	}

}
$("#pageName").text("Expense Tracker");
$(".expenseTracker").addClass("active"); 
</script>