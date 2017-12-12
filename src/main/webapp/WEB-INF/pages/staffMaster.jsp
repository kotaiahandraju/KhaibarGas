<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>



		<div class="clearfix"></div>
		<ol class="breadcrumb">
			<li><a href="">Home</a></li>
			<li>Staff Master</li>
		</ol>
		<div class="clearfix"></div>
		<div class="container">

			<div class="container">


				<div class="row">
					<div class="col-md-10 col-md-offset-1 col-sm-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>Staff Master Details</h4>
								<div class="options"></div>
							</div>
							<form:form modelAttribute="staffMaster" class="form-horizontal"
								role="form" id="education-form" action="saveStaffDetails"
								method="post"  enctype="multipart/form-data">
								<div class="panel-body">
									<c:if test="${not empty msg}">
										<div class="form-group">
											<div class="col-sm-4 col-sm-offset-4">
												<div class="msgcss alert alert-${cssMsg} fadeIn animated"
													style="text-align: center;">${msg}</div>
											</div>
										</div>
									</c:if>
									
								<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Staff Code
												<span class="impColor">*</span>
												
												</label>
												<div class="col-sm-6">
												<form:hidden path="id"/>
													<form:input path="staffcode"
														class="form-control  validate"
														autocomplete="off" placeholder="Staff Code"
														required="required" />
													<span class="hasError" id="staffcodeError"></span>
													<div>
														<form:errors path="staffcode" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Staff Number
												<span class="impColor">*</span>
												
												</label>
												<div class="col-sm-6">
													<form:input path="staffno"
														class="form-control  validate"
														autocomplete="off" placeholder="3 digit number" required="required" maxlength="3" />
													<span class="hasError" id="staffnoError"></span>
													<div>
														<form:errors path="staffno" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Designation</label>
												<div class="col-sm-6">
													<form:input path="designation"
														class="form-control onlyCharacters validate"
														autocomplete="off" placeholder="Designation" required="required" />
													<span class="hasError" id="designationError"></span>
													<div>
														<form:errors path="designation" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Nationality
												</label>
												<div class="col-sm-6">
												      <form:input path="nationality"
															class="form-control onlyCharacters validate"
															autocomplete="off" placeholder="Nationality" required="required" />
													<span class="hasError" id="nationalityError"></span>
													<div>
														<form:errors path="nationality" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">First Name
												<span class="impColor">*</span>
												</label>
												<div class="col-sm-6">
													<form:input path="firstname"
														class="form-control onlyCharacters validate"
														autocomplete="off" placeholder="First Name" required="required" />
													<span class="hasError" id="firstnameError"></span>
													<div>
														<form:errors path="firstname" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Last Name
													<span class="impColor">*</span>
												</label>
												<div class="col-sm-6">
													<form:input path="lastname"	class="form-control onlyCharacters validate" autocomplete="off" placeholder="Last Name" required="required" />
													<span class="hasError" id="lastnameError"></span>
													<div>
														<form:errors path="lastname" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Mobile Number</label>
												<div class="col-sm-6">
													<form:input path="mobile"
														class="form-control numericOnly validate"
														autocomplete="off" placeholder="Mobile Number" required="required" maxlength="13" />
													<span class="hasError" id="mobileError"></span>
													<div>
														<form:errors path="mobile" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Documents
													<!-- <span class="impColor">*</span> -->
												</label>
												<div class="col-sm-6">
													<input type="file" name="file" id="documents" />
<%-- 													<form:input path="documents"	class="form-control validate" autocomplete="off" placeholder="" required="required" /> --%>
													<span class="hasError" id="documentsError"></span>
													<div>
														<form:errors path="documents" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
									</div>
									
									<%-- <div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Status</label>
												<div class="col-sm-6">
													<form:input path="status"
														class="form-control  validate"
														autocomplete="off" placeholder=""  />
													<span class="hasError" id="statusError"></span>
													<div>
														<form:errors path="status" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
									
									</div> --%>
								</div>
								<div class="panel-footer">
									<div class="row">
										<div class="col-sm-12">
											<div class="btn-toolbar  pull-right">
												<input type="submit" value="Submit" id="submit1" class="btn-primary btn" />
												<input type="reset" value="Reset" class="btn-danger btn cancel" />
											</div>
										</div>
									</div>
								</div>
							</form:form>
						</div>
					</div>

					<div class="row">
						<div class="col-md-12">
							<div class="panel panel-primary">
								<div class="panel-heading">
									<h4>Tariff Master Details</h4>
									<div class="options">
										<a href="javascript:;" class="panel-collapse"><i
											class="fa fa-chevron-down"></i></a>
									</div>
								</div>
								<div class="panel-body collapse in">
									<div class="table-responsive" id="tableId">
										<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
											<thead>
												<tr>
													<th>First Name</th>
													<th>Last Name</th>
													<th>Staff Code</th>
													<th>Staff Number</th>
													<th>Designation</th>
													<th>Nationality</th>
													<th>Mobile</th>
													<th>Documents</th>
													<th>Status</th>
													<th>Action</th>
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
		</div>
	


<script type="text/javascript">
var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}

function displayTable(listOrders)
{
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
		+ '<thead><tr><th>First Name</th><th>Last Name</th><th>Staff Code</th><th>Staff Number</th><th>Designation</th><th>Nationality</th><th>Mobile</th><th>Documents</th><th>Status</th><th style="text-align: center;">Action</th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders, function(i, orderObj){
		if(orderObj.status == "1"){
			var deleterow = "<a class='deactive' onclick='deleteStaffMasterDetails("+ orderObj.id+ ",0)'><i class='fa fa-bell green'></i></a>"
		}else{  
			var deleterow = "<a class='active' onclick='deleteStaffMasterDetails("+ orderObj.id+ ",1)'><i class='fa fa-bell-o red'></i></a>"
		}
		
		var edit = "<a class='edit' onclick='editStaffMasterDetails(" + orderObj.id + ")'><i class='fa fa-pencil green'></i></a>"
		serviceUnitArray[orderObj.id] = orderObj;
		var tblRow = "<tr >"
			+ "<td title='"+orderObj.firstname+"'>" + orderObj.firstname + "</td>"
			+ "<td title='"+orderObj.lastname+"'>" + orderObj.lastname + "</td>"
			+ "<td title='"+orderObj.staffcode+"'>" + orderObj.staffcode + "</td>"
			+ "<td title='"+orderObj.staffcode+"'>" + orderObj.staffno + "</td>"
			+ "<td title='"+orderObj.designation+"'>" + orderObj.designation + "</td>"
			+ "<td title='"+orderObj.nationality+"'>" + orderObj.nationality + "</td>"
			+ "<td title='"+orderObj.mobile+"'>" + orderObj.mobile + "</td>"
			+ "<td title='"+orderObj.documents+"'>" + orderObj.documents + "</td>"
			+ "<td title='"+orderObj.staffStatus+"'>" + orderObj.staffStatus + "</td>"
			+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
			+ "</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
}
			
function editStaffMasterDetails(id)
{
	$("#id").val(serviceUnitArray[id].id);
	$("#firstname").val(serviceUnitArray[id].firstname);
	$("#lastname").val(serviceUnitArray[id].lastname);
	$("#staffcode").val(serviceUnitArray[id].staffcode);
	$("#staffno").val(serviceUnitArray[id].staffno);
	$("#designation").val(serviceUnitArray[id].designation);
	$("#nationality").val(serviceUnitArray[id].nationality);
	$("#mobile").val(serviceUnitArray[id].mobile);
// 	$("#documents").val(serviceUnitArray[id].documents);
	$("#submit1").val("Update");
	$(window).scrollTop($('body').offset().top);
}

function deleteStaffMasterDetails(id,status)
{
	var checkstr=null;
	if(status == 0){
		 checkstr =  confirm('Are you sure you want to Deactivate this?');
	}else{
		 checkstr =  confirm('Are you sure you want to Activate this?');
		 
	}
	console.log(status);
	if (checkstr == true)
	{
		var formData = new FormData();
		formData.append('id', id);
		formData.append('status', status);
		$.fn.makeMultipartRequest('POST', 'deleteStaffMasterDetails',
			false, formData, false, 'text', function(data) {
				var jsonobj = $.parseJSON(data);
				window.location.reload();
// 				var alldata = jsonobj.allOrders1;
// 				console.log(jsonobj.allOrders1);
// 				displayTable(alldata);
		});
	}
}
$("#pageName").text("Staff Master");
$(".staff").addClass("active");
		</script>
<%-- <tr>
<td>  Religion System</td>
 <td> <form:select path ="religionSystem" items="${religionsSystemList}"/> </td>
</tr>
 --%>

</html>