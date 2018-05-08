<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
<style>
.addItemButton {
	cursor: pointer;
	font-size: small;
	background: green;
	color: white;
	padding: 3px 10px 3px 10px;
}
table #dependent_table {
	/* 	width: 100%; */
	counter-reset: rowNumber;
}
table tbody tr.rowInc {
	counter-increment: rowNumber;
}
 table#dependent_table tbody tr td:first-child::before {
 content: counter(rowNumber);
/* 	min-width: 1em; */
/* 	margin-right: 0.5em; */
}
</style>
	<div class="clearfix"></div>
	<ol class="breadcrumb">
		<li><a href="#">Home</a></li>
	    <li>Security Deposit</li>
	</ol>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h4>Security Deposit List</h4>
						<div class="options">   
							<a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
						</div>
					</div>
					<div class="panel-body collapse in">
					 <input type="checkbox" class="form-check-input" onclick="inactiveData();" id="inActive"> <label class="form-check-label">Show Inactive List</label>
						<div class="table-responsive" id="tableId">
							<table class="table table-striped table-bordered datatables" id="example">
								<thead><tr><th>Customer Name</th><th>Security Deposit</th><th>Item Name</th><th>Quantity</th><th>Company Name</th><th>Amount</th></tr></thead>
								<tbody> </tbody>
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
						<h4>Add Security Deposit</h4>
                    </div>
					<form:form modelAttribute="securedepositForm" class="form-horizontal" action="saveSecurityDeposit">
					<table width="100%">
     			 <tr>
     			 <td>
					<div class="panel-body">
						<div class="row">
						<div class="col-md-6">
								<div class="form-group">
									<label for="customertype" class="col-md-4 control-label">Customer type <span class="impColor">*</span></label>
									<div class="col-md-6">
									<form:select path="customertype" class="form-control   validate"  onfocus="removeBorder(this.id);" onchange="getCustomerIds(this.value)">
		                            <form:option value="">-- Customer Type --</form:option>
		                            <form:option value="COMMERCIAL">COMMERCIAL</form:option>
		                            <form:option value="DOMESTIC">DOMESTIC</form:option>
		                            <form:option value="INDUSTIAL">INDUSTIAL</form:option>
		                          </form:select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-4 control-label">Customer Id <span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:hidden path="id" />
										  <form:select path="customerId" class="form-control  validate" onfocus="removeBorder(this.id);" onchange="getCustomerDetails(this.value)">
				                            <form:option value="">-- Select Customer Id --</form:option>
				                          </form:select>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-4 control-label">Security Deposit <span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:select path="securityDeposit" class="form-control validate" onfocus="removeBorder(this.id);" onchange="selectDeposit(this.value)">
				                            <form:option value="">-- Select Security Deposit --</form:option>
				                             <form:option value="Amount">Amount</form:option>
				                              <form:option value="Cylinder">Cylinder</form:option>
				                          </form:select>
									</div>
								</div>
							</div>
								
								
								
							
							<%-- <div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-4 control-label">Civil Defense Card Expiry <span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="itemId" type="text" readonly="true" class="form-control validate" placeholder="Civil Defense Card Expiry" onchange="removeBorder(this.id)"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-4 control-label">Service Due <span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="quantity" type="text" class="form-control validate" readonly="true" placeholder="Service Due" onchange="removeBorder(this.id)"/>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="focusedinput" class="col-md-4 control-label">Make <span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="companyId" type="text" class="form-control validate" placeholder="Make"/>
									</div>
								</div>
							</div> --%>
							
							
							 
							<div class="clearfix"></div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="focusedinput" class="col-md-2 control-label">Remarks</label>
									<div class="col-md-9">
										<form:textarea path="remarks" class="form-control" placeholder="Remarks" rows="5"></form:textarea>
									</div>
								</div>
							</div>
							<div class="clearfix"></div>
							<hr>
							<div class="col-md-6" style="display:none" id="securityAmountId">
								<div class="form-group">
									<label for="focusedinput" class="col-md-4 control-label">Security Amount (AED) <span class="impColor">*</span></label>
									<div class="col-md-6">
										<form:input path="amount" type="text"  class="form-control numericOnly" placeholder="Security Amount (AED)" onchange="removeBorder(this.id)"/>
									</div>
								</div>
							</div>
							
							
							
						</div>
<!-- 						cylinder grid -->
		<div class="panel-body" id="cylindergrid" style="display:none">         
         
            <form:select path="itemId" id="item1" style="display:none;font-size: small;">
              <form:option value="" selected="selected" disabled="disabled">-- Select Item --</form:option>
              <form:options items="${items}"></form:options>
            </form:select>
             <form:select path="companyId" id="company1" style="display:none;font-size: small;">
              <form:option value="" selected="selected" disabled="disabled">-- Select Company --</form:option>
              <form:options items="${companys}"></form:options>
            </form:select>
            
            <!-- </div>
		<div class="row"> -->
            <div >
              <div class="form-group">
              <span class="addItemButton btn-danger" onclick="addMoreRowsForDependent(this.form);">Add Item</span>
                <table class="table table-bordered" id="dependent_table">
                  <thead >
                    <tr class="default" style="background:#EBEBEB">
                      <th><span>Sno</span></th>
                      <th><span>Items</span></th>
                      <th><span>Quantity</span></th>
                      <th><span>Company</span></th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr id="1" class="rowInc">
                      <td></td>
                      <td><select name="item1" class="form-control " id="1item" style="width: 100%;font-size: small;" title="Select Product" onfocus="removeBorder(this.id)" >
                        <option value="" selected="selected" >-- Select Item --</option>
                        </select> </td>
                      <td><input name="unit" value="1" id="1unit" type="text" title="Unit" onkeydown="removeBorder(this.id);" class="form-control numericOnly" /></td>
                       <td><select name="company1" class="form-control " id="1company" style="width: 100%;font-size: small;" title="Select Product" onfocus="removeBorder(this.id)" >
                        <option value="" selected="selected" >-- Select company --</option>
                        </select> </td>
                      
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
<!--           cylinders grid end -->
                    </div>
                    <div class="panel-footer">
						<div class="row">
							<div class="col-sm-12">
				      			<div class="btn-toolbar text-center">
									<input class="btn-primary btn" type="submit" value="Submit" id="submit1">
					      			<input class="btn-danger btn cancel" type="reset" value="Reset">
				      			</div>
				      		</div>
						</div>
					</div>
					</td>
					</tr>
					</table>
					</form:form>
				</div>
			</div>
		</div>
	</div> <!-- container -->

<script type='text/javascript' src='${baseurl }/js/jquery-ui.min.js'></script> 
<script type="text/javascript">
var dependentRowCount = 1;
$(function() {
// 	var listOrders=JSON.parse(lstOrders);

	var dummyItems = $("#item1").html();
	$("#1item").empty();
	$(dummyItems).appendTo("#1item");
	
	var dummyCompanys = $("#company1").html();
	$("#1company").empty();
	$(dummyCompanys).appendTo("#1company");
	
	
});
var listOrders1 = ${allObjects};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Customer Name</th><th>Customer Id</th><th>Security Deposit</th><th>Item Name</th><th>Quantity</th><th>Company Name</th><th>Amount</th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactivate' onclick='deletetruckMaster("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>"
		}else{  
			var deleterow = "<a class='activate' onclick='deletetruckMaster("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>"
		}
		var edit = "<a class='edit editIt' onclick='editTruckMaster("+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr >"
			+ "<td class='impFiled' title='"+orderObj.customername+"'>"+ orderObj.customername + "</td>"
			+ "<td class='impFiled' title='"+orderObj.userId+"'>"+ orderObj.userId + "</td>"
			+ "<td title='"+orderObj.securityDeposit+"'>"+ orderObj.securityDeposit + "</td>"
			+ "<td title='"+orderObj.itemName+"'>"+ orderObj.itemName + "</td>"
			+ "<td title='"+orderObj.quantity+"'>"+ orderObj.quantity + "</td>"
			+ "<td title='"+orderObj.companyName+"'>"+ orderObj.companyName + "</td>"
			+ "<td title='"+orderObj.amount+"'>"+ orderObj.amount + "</td>"
// 			+ "<td title='"+orderObj.description+"'>"+ orderObj.description+ "</td>"
// 			+ "<td title='"+orderObj.capacityoftruck+"'>"+ orderObj.capacityoftruck + "</td>"
// 			+ "<td title='"+orderObj.lponumber+"'>"+ orderObj.lponumber + "</td>"
// 			+ "<td title='"+orderObj.truckStatus+"'>"+ orderObj.truckStatus + "</td>"
// 			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
			+ "</tr >";
		$(tblRow).appendTo("#tableId table tbody");
	});
		if(isClick=='Yes') $('.datatables').dataTable();
}

function editTruckMaster(id) {
	$("#id").val(serviceUnitArray[id].id);
	$("#trucknumber").val(serviceUnitArray[id].trucknumber);
	$("#registrationexpirydate1").val(serviceUnitArray[id].registrationexpirydate1);
	$("#civildefensecardexpirydate1").val(serviceUnitArray[id].civildefensecardexpirydate1);
	$("#servicedue1").val(serviceUnitArray[id].servicedue1);
	$("#make").val(serviceUnitArray[id].make);
	$("#description").val(serviceUnitArray[id].description);
	$("#capacityoftruck").val(serviceUnitArray[id].capacityoftruck);
	$("#lponumber").val(serviceUnitArray[id].lponumber);
	$("#typeOfService").val(serviceUnitArray[id].typeOfService).trigger("change");
// 	$("#typeOfService").trigger("chosen:updated")
	$("#submit1").val("Update");
	$(window).scrollTop($('#moveTo').offset().top);
}
function deletetruckMaster(id,status){
	var checkstr=null;
	if(status == 0){
		 checkstr = confirm('Are you sure you want to Deactivate?');
	}else{
		 checkstr = confirm('Are you sure you want to Activate?');
	}
	
	if(checkstr == true){
		var formData = new FormData();
	    formData.append('id', id);
	    formData.append('status', status);
		$.fn.makeMultipartRequest('POST', 'deletetruckMaster', false, formData, false, 'text', function(data){
			var jsonobj = $.parseJSON(data);
			window.location.reload();
	 		var alldata = jsonobj.allOrders1;
	 		console.log(jsonobj.allOrders1);
	 		displayTable(alldata);
	 		tooltip();
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
		
		$.fn.makeMultipartRequest('POST', 'inActiveTruckMaster', false,
				formData, false, 'text', function(data) {
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			console.log(jsonobj.allOrders1);
			displayTable(alldata);
			tooltip();
				});
}
function getCustomerIds(value){
	$("#customername").text(" ");
	$("#customeraddress").text(" ");
	$("#mobile").text(" ");
	$("#landline").text(" ");
	var formData = new FormData();
    formData.append('customertype', value);
	$.fn.makeMultipartRequest('POST', 'getCustomerIds', false,
			formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		var alldata = jsonobj.allOrders1;
		
		var html = "<option value=''>-- Select Customer Id --</option>";
		$.each(alldata,function(i, catObj) {
			
			 html = html + '<option value="'+ catObj.id + '">'	+ catObj.customerid + ' ( '+ catObj.customername+ ' )</option>';
		});
		$('#customerId').empty().append(html);
		$("#customerId").trigger("chosen:updated");
		
		
		
	});
}
function getCustomerDetails(value){
	var formData = new FormData();
    formData.append('customerid', value);
	$.fn.makeMultipartRequest('POST', 'getCustomerIds', false,
			formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		var alldata = jsonobj.allOrders1;
		$("#cylinders").text("");
		$.each(alldata,function(i, catObj) {
			$("#customername").text(catObj.customername);
			$("#mobile").text(catObj.mobile);
			$("#customeraddress").text(catObj.customeraddress);
			$("#landline").text(catObj.landline);
			$("#lastDueAmount").text(catObj.dueAmount);
			$("#previousDueAmount").val(catObj.dueAmount);
			
		
	});
	
});
}
function selectDeposit(value){
	if(value=="Amount"){
		$("#securityAmountId").show();
		$("#cylindergrid").hide();
	}
	if(value=="Cylinder"){
		$("#securityAmountId").hide();
		$("#cylindergrid").show();
	}
	if(value==""){
		$("#securityAmountId").hide();
		$("#cylindergrid").hide();
	}
}
function addMoreRowsForDependent() {
	rowvalidate =false;
	var rowid =$('#dependent_table tbody tr:last').attr('id');
	console.log(rowid);
	    var number = parseInt(rowid.match(/[0-9]+/)[0], 10);
	    var item = $('#' + number + 'item').val();
		var qty = $('#'+number+'unit').val();
	    
		 if(item == "" || item == null || item == "undefined" || qty ==  "" )
		{
			if(item == "" || item == null || item == "undefined")
			{
				$('#'+number+'item').css('color','red');
				$('#'+number+'item').css("border-color","#e73d4a");
				$('#'+number+'item').attr("placeholder","Enter Product Description");
				$('#'+number+'item').addClass('your-class');
// 				$('#'+number+'item').focus();
				return false;
			}
			if(qty == "" || qty == null || qty == "undefined")
			{
				$('#'+number+'unit').css('color','red');
				$('#'+number+'unit').css("border-color","#e73d4a");
				$('#'+number+'unit').attr("placeholder","Enter Quantity");
				$('#'+number+'unit').addClass('your-class');
// 				$('#'+number+'item').focus();
				return false;
			}
			
			
		} 
	dependentRowCount++;
	var dependentRow1 = '<tr class="rowInc" role="row" id="'+dependentRowCount+'">'
			+ '<td class="labelCss"></td>'
			+ '<td class="inputCss"><select title="Select Item" name="item1" style="width: 100%;font-size: small;" id="'
			+ dependentRowCount
			+ 'item" class="form-control validate" onchange="removeBorder(this.id)"><option>Select</option></select></td>'
			+ '<td class="inputCss"><input title="Unit" name="unit" id="'
			+ dependentRowCount
			+ 'unit" type="text" value="1" class="form-control numericOnly" /></td>'
			+ '<td class="inputCss"><select title="Select Company" name="company1" style="width: 100%;font-size: small;" id="'
			+ dependentRowCount
			+ 'company" class="form-control validate" onchange="removeBorder(this.id)"><option>Select</option></select></td>'
			+ "<th class='labelCss notPrintMe hideme' style='width: 10px;'><span><a href='javascript:void(0);' style='color: red;' onclick='removeDependentRow("
			+ dependentRowCount + ");'><i class='fa fa-trash' style='color: red;text-decoration: none;cursor: pointer;'></i></a></span></th>" +
			 + "</tr>";
	$(dependentRow1).appendTo("#dependent_table tbody");
	
	var dummyItem1 = $('#item1').html();
	$('#'+dependentRowCount+'item').empty();
	$(dummyItem1).appendTo('#'+dependentRowCount+'item');
	var dummycompany1 = $('#company1').html();
	$('#'+dependentRowCount+'company').empty();
	$(dummycompany1).appendTo('#'+dependentRowCount+'company');
	
}
function removeDependentRow(dependentRowCount) {
	jQuery('#' + dependentRowCount).remove();
	priceCalculator();
	
	
}
$("#pageName").text("Security Deposit");
$(".securedeposit").addClass("active");
</script>
<!-- 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <style type="text/css">
        body
        {
            font-family: Arial;
            font-size: 10pt;
        }
    </style>
</head>
<body>
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBof-nUbLfnD7fyKZ2DvfLXwWX-RPgdU8c&libraries=places&sensor=false"></script>    
    <script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBof-nUbLfnD7fyKZ2DvfLXwWX-RPgdU8c&callback=initMap"></script>
    <script type="text/javascript">
        google.maps.event.addDomListener(window, 'load', function () {
            var places = new google.maps.places.Autocomplete(document.getElementById('txtPlaces'));
            google.maps.event.addListener(places, 'place_changed', function () {
                var place = places.getPlace();
                var address = place.formatted_address;
                var latitude = place.geometry.location.lat();
                var longitude = place.geometry.location.lng();
                var mesg = "Address: " + address;
                mesg += "\nLatitude: " + latitude;
                mesg += "\nLongitude: " + longitude;
                alert(mesg);
            });
        });
    </script>
    <span>Location:</span>
    <input type="text" id="txtPlaces" style="width: 250px" placeholder="Enter a location" />
</body>
</html> -->