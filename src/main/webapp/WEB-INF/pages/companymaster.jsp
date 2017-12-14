
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

		<div class="clearfix"></div>
		<ol class="breadcrumb">
			<li><a href="#">Home</a></li>
			<li>Company Master</li>
		</ol>
		<div class="clearfix"></div>
		<div class="container">
			<div class="row">
					<div class="col-md-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>Company Master Details</h4>
								<div class="options">
									<a href="javascript:;" class="panel-collapse"><i
										class="fa fa-chevron-down"></i></a>
								</div>
							</div>
							<div class="panel-body collapse in">
								<div class="table-responsive" id="tableId">
									<table cellpadding="0" cellspacing="0" border="0"
										class="table table-striped table-bordered datatables"
										id="example">
										<thead>
											<tr>
												<th>Company Code</th>
												<th>Company Name</th>
												<th>Contact Person Name</th>
												<th>Contact Person Mobile</th>
												<th>Email Id</th>
												<th>Remarks</th>
												<th>Type of Comapany</th>
												<th>Status</th>
												<th></th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>

			<div class="row">
				<div class="col-md-10 col-md-offset-1 col-sm-12">
					<div class="panel panel-primary">
						<div class="panel-heading">
							<h4>Company Master </h4>
							<div class="options"></div>
						</div>
						<form:form class="form-horizontal" modelAttribute="companymaster"
							role="form" id="cylider-form" action="saveCompanyMaster" method="post">
							<div class="panel-body">
								<%-- <div class="row">
								<div class="col-sm-4 col-sm-offset-4">
									<div class="msgcss alert alert-success fadeIn animated" style="text-align: center;">${msg} User Created Successfully</div>
								</div>
						  	</div>
							 --%>
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<form:hidden path="id" />
											<label class="col-sm-4 control-label required">Company
												Code <span class="impColor">*</span>
											</label>
											<div class="col-sm-6">
												<form:input path="companycode"
													class="form-control validate"
													autocomplete="off" placeholder="Company Code"
													required="required" />
												<span class="hasError" id="companycodeError"></span>
												<div>
													<form:errors path="companycode" cssClass="error" />
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label required">Company
												Name <span class="impColor">*</span>
											</label>
											<div class="col-sm-6">
												<form:input path="companyname"
													class="form-control onlyCharacters validate"
													autocomplete="off" placeholder="Company Name"
													required="required" />
												<span class="hasError" id="companynameError"></span>
												<div>
													<form:errors path="companyname" cssClass="error" />
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label required">Contact
												Person Name <span class="impColor">*</span>
											</label>
											<div class="col-sm-6">
												<form:input path="contactpersonname"
													class="form-control onlyCharacters validate"
													autocomplete="off" placeholder="Contact Person Name"
													required="required" />
												<span class="hasError" id="contactpersonnameError"></span>
												<div>
													<form:errors path="contactpersonname" cssClass="error" />
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label required">Contact
												Person Mobile <span class="impColor">*</span>
											</label>
											<div class="col-sm-6">
												<form:input path="contactpersonmobile"
													class="form-control numericOnly validate"
													autocomplete="off" placeholder="Contact Person Mobile"
													required="required" />
												<span class="hasError" id="contactpersonmobileError"></span>
												<div>
													<form:errors path="contactpersonmobile" cssClass="error" />
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label required">Email
												Id <span class="impColor">*</span>
											</label>
											<div class="col-sm-6">
												<form:input path="emailid" class="form-control validate"
													autocomplete="off" placeholder="Email Id"
													required="required" />
												<span class="hasError" id="emailidError"></span>
												<div>
													<form:errors path="emailid" cssClass="error" />
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label required">Remarks</label>
											<div class="col-sm-6">
												<form:input path="remarks"
													class="form-control onlyCharacters validate"
													autocomplete="off" placeholder="Remarks" />
												<span class="hasError" id="remarksError"></span>
												<div>
													<form:errors path="remarks" cssClass="error" />
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label required">Type
												of Company</label>
											<div class="col-sm-6">
												<form:input path="typeofcompany"
													class="form-control validate onlyCharacters" autocomplete="off"
													placeholder="Type of Company" />
												<span class="hasError" id="typeofcompanyError"></span>
												<div>
													<form:errors path="typeofcompany" cssClass="error" />
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<label class="col-sm-4 control-label required">Customer
												Type</label>
											<div class="col-sm-6">
												<form:select path="customertype"
													class="form-control validate" onfocus="removeBorder(this.id)">
													<form:option value="">-- Select Customer Type --</form:option>
													<form:option value="Owner">Owner</form:option>
													<form:option value="Competitor">Competitor</form:option>
													<form:option value="Distributor">Distributor</form:option>
												</form:select>
												<%-- <form:select path="customertype" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Customer Type" /> --%>
												<span class="hasError" id="customertypeError"></span>
												<div>
													<form:errors path="customertype" cssClass="error" />
												</div>
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
		var listOrders1 = ${allOrders1};
		if (listOrders1 != "") {
			displayTable(listOrders1);
		}
		function displayTable(listOrders) {
			$('#tableId').html('');
			var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
					+ '<thead><tr><th>Company Code</th><th>Company Name</th><th>Contact Person Name</th><th>Contact Person Mobile</th><th>Email Id</th><th>Remarks</th><th>Type of Comapany</th><th>Customer Type</th><th>Status</th><th style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
			$('#tableId').html(tableHead);
			serviceUnitArray = {};
			$
					.each(
							listOrders,
							function(i, orderObj) {
								
								if(orderObj.status == "1"){
									var deleterow = "<a class='deactive' onclick='deleteCompanyMasterDetails("+ orderObj.id+ ",0)'><i class='fa fa-bell green'></i></a>"
								}else{  
									var deleterow = "<a class='active' onclick='deleteCompanyMasterDetails("+ orderObj.id+ ",1)'><i class='fa fa-bell-o red'></i></a>"
								}
								var edit = "<a class='edit' onclick='editCompanyMasterDetails("
										+ orderObj.id
										+ ")'><i class='fa fa-pencil green'></i></a>"
								
								serviceUnitArray[orderObj.id] = orderObj;
								var tblRow = "<tr >"
										+ "<td title='"+orderObj.companycode+"'>"
										+ orderObj.companycode
										+ "</td>"
										+ "<td title='"+orderObj.companyname+"'>"
										+ orderObj.companyname
										+ "</td>"
										+ "<td title='"+orderObj.contactpersonname+"'>"
										+ orderObj.contactpersonname
										+ "</td>"
										+ "<td title='"+orderObj.contactpersonmobile+"'>"
										+ orderObj.contactpersonmobile
										+ "</td>"
										+ "<td title='"+orderObj.emailid+"'>"
										+ orderObj.emailid
										+ "</td>"
										+ "<td title='"+orderObj.remarks+"'>"
										+ orderObj.remarks
										+ "</td>"
										+ "<td title='"+orderObj.typeofcompany+"'>"
										+ orderObj.typeofcompany
										+ "</td>"
										+ "<td title='"+orderObj.customertype+"'>"
										+ orderObj.customertype
										+ "</td>"
										+ "<td title='"+orderObj.companyStatus+"'>"
										+ orderObj.companyStatus
										+ "</td>"
										+ "<td style='text-align: center;white-space: nowrap;'>"
										+ edit
										+ "&nbsp;&nbsp;"
										+ deleterow + "</td>"
										+ "</tr >";
								$(tblRow).appendTo("#tableId table tbody");
							});

			/* $('#DataTables_Table_0').DataTable({
				dom: 'Bfrtip',
				buttons: [{extend:"print",className:"btn default"},{extend:"pdf",className:"btn default"},{extend:"csv",className:"btn default"}]
			}); */

			/*$('#datatable-buttons').DataTable({
			       "dom": 'C<"clear">lfrtip',
			       "colVis": {
			           "buttonText": "Change columns",
			       "buttons": [{extend:"copy",className:"btn default"},{extend:"print",className:"btn default"},{extend:"pdf",className:"btn default"},{extend:"csv",className:"btn default"}]
			       }
			   });*/
		}

		function editCompanyMasterDetails(id) {
			$("#id").val(serviceUnitArray[id].id);
			$("#companycode").val(serviceUnitArray[id].companycode);
			$("#companyname").val(serviceUnitArray[id].companyname);
			$("#contactpersonname").val(serviceUnitArray[id].contactpersonname);
			$("#contactpersonmobile").val(
					serviceUnitArray[id].contactpersonmobile);
			$("#emailid").val(serviceUnitArray[id].emailid);
			$("#remarks").val(serviceUnitArray[id].remarks);
			$("#customertype").val(serviceUnitArray[id].customertype);
			$("#typeofcompany").val(serviceUnitArray[id].typeofcompany);
			$("#status").val(serviceUnitArray[id].status);
			//$("#customerid").val(serviceUnitArray[id].customerid);
			$("#submit1").val("Update");
			$(window).scrollTop($('body').offset().top);
		}
		function deleteCompanyMasterDetails(id,status) {
			var checkstr=null;
			if(status == 0){
				 checkstr =  confirm('Are you sure you want to Deactivate this?');
			}else{
				 checkstr =  confirm('Are you sure you want to Activate this?');
			}
			if (checkstr == true) {
				var formData = new FormData();
				formData.append('id', id);
				formData.append('status', status);
				$.fn.makeMultipartRequest('POST', 'deleteCompanyMasterDetails',
						false, formData, false, 'text', function(data) {
							var jsonobj = $.parseJSON(data);
// 							var alldata = jsonobj.allOrders1;
// 							console.log(jsonobj.allOrders1);
// 							displayTable(alldata);
							window.location.reload();
						});
			}

		}
		$("#pageName").text("Company Master");
		$(".company").addClass("active"); 
	</script>