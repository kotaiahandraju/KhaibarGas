<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>


<div id="page-content">
	<div id="wrap">
		<div id="page-heading" class="row">
			<div class="col-md-6">
				<h1>Tariff Master</h1>
			</div>
			<div class="col-md-6">
				<div class="options">
					<div class="btn-toolbar">
						<a href="#" class="btn btn-danger "><span>123456</span><br />Cylinders</a>
						<a href="#" class="btn btn-warning"><span>223456</span><br />Customers</a>
						<a href="#" class="btn btn-info"><span>123456</span><br />Gas
							in Kgs</a>
					</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="clearfix"></div>
		<ol class="breadcrumb">
			<li><a href="">Home</a></li>
			<li>Tariff Master</li>
		</ol>
		<div class="clearfix"></div>
		<div class="container">

			<div class="container">


				<div class="row">
					<div class="col-md-10 col-md-offset-1 col-sm-12">
						<div class="panel panel-primary">
							<div class="panel-heading">
								<h4>Tariff Master Details</h4>
								<div class="options"></div>
							</div>
							<form:form modelAttribute="tariffMaster" class="form-horizontal"
								role="form" id="education-form" action="saveTariffDetails"
								method="post">
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
												<form:hidden path="id" />
												<label class="col-sm-4 control-label required">Asset
													Code (Either Cylinder Code or Accessory Code)</label>
												<div class="col-sm-6">
													<form:input path="assetcode"
														class="form-control onlyCharacters validate"
														autocomplete="off" placeholder="Asset Code"
														required="required" />
													<span class="hasError" id="assetcodeError"></span>
													<div>
														<form:errors path="assetcode" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Asset
													Description <span class="impColor">*</span>
												</label>
												<div class="col-sm-6">
													<form:input path="assetdescription"
														class="form-control onlyCharacters validate"
														autocomplete="off" placeholder="" required="required" />
													<span class="hasError" id="assetdescriptionError"></span>
													<div>
														<form:errors path="assetdescription" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Rate</label>
												<div class="col-sm-6">
													<form:input path="rate"
														class="form-control onlyCharacters validate"
														autocomplete="off" placeholder="" required="required" />
													<span class="hasError" id="rateError"></span>
													<div>
														<form:errors path="Rate" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Allowed Discount
													<span class="impColor">*</span>
												</label>
												<div class="col-sm-4">
													<div class="input-group">
												      <form:input path="alloweddiscount"
															class="form-control onlyCharacters validate"
															autocomplete="off" placeholder="" required="required" />
												      <span class="input-group-addon"><i class="fa fa-percent"></i>
</span>   
												    </div>
													<span class="hasError" id="alloweddiscountError"></span>
													<div>
														<form:errors path="alloweddiscount" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Remarks</label>
												<div class="col-sm-6">
													<form:input path="remarks"
														class="form-control onlyCharacters validate"
														autocomplete="off" placeholder="" required="required" />
													<span class="hasError" id="remarksError"></span>
													<div>
														<form:errors path="remarks" cssClass="error" />
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												<label class="col-sm-4 control-label required">Status
													<span class="impColor">*</span>
												</label>
												<div class="col-sm-6">
													<form:input path="status"
														class="form-control onlyCharacters validate"
														autocomplete="off" placeholder="" required="required" />
													<span class="hasError" id="statusError"></span>
													<div>
														<form:errors path="status" cssClass="error" />
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
												<input type="reset" value="Reset" class="btn-danger btn" />
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
										<table cellpadding="0" cellspacing="0" border="0"
											class="table table-striped table-bordered datatables"
											id="example">
											<thead>
												<tr>
													<th>Asset Code</th>
													<th>Asset Description</th>
													<th>Rate</th>
													<th>Allowed Discount</th>
													<th>Remarks</th>
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

				</div>
			</div>
		</div>
	</div>
</div>


<script>
			var listOrders1 = ${allOrders1};
			if (listOrders1 != "") {
				displayTable(listOrders1);
			}
			function displayTable(listOrders) {
				$('#tableId').html('');
				var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
					+ '<thead><tr><th>Asset Code</th><th>Asset Description</th><th>Rate</th><th>Allowed Discount</th><th>Remarks</th><th>Status</th><th style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
			$('#tableId').html(tableHead);
				serviceUnitArray = {};
				$
						.each(
								listOrders,
								function(i, orderObj) {
									var edit = "<a class='edit' onclick='editTariffMasterDetails(" + orderObj.id + ")'><i class='fa fa-pencil green'></i></a>"
									var deleterow = "<a class='delete' onclick='deleteTariffMasterDetails(" + orderObj.id + ")'><i class='fa fa-trash-o red'></i></a>"
									serviceUnitArray[orderObj.id] = orderObj;
									var tblRow = "<tr >"
											+ "<td title='"+orderObj.id+"'>" + orderObj.assetcode + "</td>"
											+ "<td title='"+orderObj.id+"'>" + orderObj.assetdescription + "</td>"
											+ "<td title='"+orderObj.id+"'>" + orderObj.rate + "</td>"
											+ "<td title='"+orderObj.id+"'>" + orderObj.alloweddiscount + "</td>"
											+ "<td title='"+orderObj.id+"'>" + orderObj.remarks + "</td>"
											+ "<td title='"+orderObj.id+"'>" + orderObj.status + "</td>"
											+ "<td style='text-align: center;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
											+ "</tr >";
									$(tblRow).appendTo("#tableId table tbody");
								});

			}
			
			function editTariffMasterDetails(id) {
				$("#id").val(serviceUnitArray[id].id);
				$("#assetcode").val(serviceUnitArray[id].assetcode);
				$("#assetdescription").val(serviceUnitArray[id].assetdescription);
				$("#rate").val(serviceUnitArray[id].rate);
				$("#alloweddiscount").val(serviceUnitArray[id].alloweddiscount);
				$("#remarks").val(serviceUnitArray[id].remarks);
				$("#status").val(serviceUnitArray[id].status);
				//$("#customerid").val(serviceUnitArray[id].customerid);
				$("#submit1").val("Update");
				$(window).scrollTop($('body').offset().top);
			}
			function deleteTariffMasterDetails(id) {
				var checkstr = confirm('Are you sure you want to delete this?');
				if (checkstr == true) {
					var formData = new FormData();
					formData.append('id', id);
					$.fn.makeMultipartRequest('POST', 'deleteTariffMasterDetails',
							false, formData, false, 'text', function(data) {
								var jsonobj = $.parseJSON(data);
								alert(jsonobj.message);
								var alldata = jsonobj.allOrders1;
								console.log(jsonobj.allOrders1);
								displayTable(alldata);
							});
				}

			}
		</script>
<%-- <tr>
<td>  Religion System</td>
 <td> <form:select path ="religionSystem" items="${religionsSystemList}"/> </td>
</tr>
 --%>

</html>