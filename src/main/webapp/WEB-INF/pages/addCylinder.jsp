<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<c:set var = "activePage" scope = "session" value = ""/>
     
     <%
     
     
String baseurl =  request.getScheme() + "://" + request.getServerName() +      ":" +   request.getServerPort() +  request.getContextPath();
session.setAttribute("baseurl", baseurl);
%>
<head>
	<!-- jQuery -->
	<script src="${baseurl }/js/jquery.min.js"></script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%-- <div class="container">
  
  <div class="panel panel-default">
 
 <div class="panel-heading"> Add New Religion
             
    </div>
  <div class="panel-body">

<form:form action="/addcylinder" method="post" modelAttribute="cylinderform">
<table class="table table-bordered table-hover specialCollapse">



<tr>
<td>Cylinder Id </td>
 <td> <form:input path ="cylinderid"/> </td>
</tr>
<tr>
<td>Size </td>
 <td> <form:input path ="size"/> </td>
</tr>

<tr>
<td>Capacity </td>
 <td> <form:input path ="capacity"/> </td>
</tr>

<tr>
<td>Status </td>
 <td> <form:input path ="status"/> </td>
</tr>

<tr>
<td>Customer Id</td>
 <td> <form:input path ="customerid"/> </td>
</tr>

<tr>
<td>Location</td>
 <td> <form:input path ="location"/> </td>
</tr>

<tr>
<td>Remarks</td>
 <td> <form:input path ="remarks"/> </td>
</tr>

<tr>
<td>Cylinder Colour </td>
 <td> <form:input path ="color"/> </td>
</tr>

<tr>
<td>Made In </td>
 <td> <form:input path ="madein"/> </td>
</tr>

<tr>
<td>Expiry Date </td>
 <td> <form:input path ="expirydate"/> </td>
</tr>

<tr>
<td>Cylinder Owner </td>
 <td> <form:input path ="ownercompany"/> </td>
</tr>


<tr>
<td></td>
<td><input type="submit" value="Submit"/> <input type="reset" value="Reset" /></td>
 </tr>

</table> --%>


<form:form modelAttribute="cylinderForm" class="form-horizontal" role="form" id="education-form" action="addCylinder" method="post">								
						<div class="form-group">
							<div class="col-sm-12">
								<div class="errorMessage" id="Education_invalid_em_">
									<c:if test="${not empty msg}">
										<div class="alert alert-success fadeIn animated">${msg}</div>
									</c:if>
								</div>
								<form:hidden path="id"/>
							</div>
					  	</div>
						<div class="form-group">
							<label class="col-sm-4 control-label required">Cylinder id</label>
							<div class="col-sm-8">
						  		<form:input path="cylinderid" type="text" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Enter Education"  maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="cylinderid" cssClass="error" /></div>										
							</div>
					  	</div>
					  	
					  	<div class="form-group">
							<label class="col-sm-4 control-label required">Size</label>
							<div class="col-sm-8">
						  		<form:input path="size" type="text" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Enter Education"  maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="size" cssClass="error" /></div>										
							</div>
					  	</div>
					  	
					  	<div class="form-group">
							<label class="col-sm-4 control-label required">Capacity</label>
							<div class="col-sm-8">
						  		<form:input path="capacity" type="text" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Enter Education"  maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="capacity" cssClass="error" /></div>										
							</div>
					  	</div>
					  	
					  	<div class="form-group">
							<label class="col-sm-4 control-label required">Cylinder Status</label>
							<div class="col-sm-8">
						  		<form:input path="cylinderstatus" type="text" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Enter Education"  maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="cylinderstatus" cssClass="error" /></div>										
							</div>
					  	</div>
					  	
					  	<div class="form-group">
							<label class="col-sm-4 control-label required">Customer id</label>
							<div class="col-sm-8">
						  		<form:input path="customerid" type="text" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Enter Education"  maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="customerid" cssClass="error" /></div>										
							</div>
					  	</div>
					  	
					  	<div class="form-group">
							<label class="col-sm-4 control-label required">Location</label>
							<div class="col-sm-8">
						  		<form:input path="location" type="text" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Enter Education"  maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="location" cssClass="error" /></div>										
							</div>
					  	</div>
					  	
					  	<div class="form-group">
							<label class="col-sm-4 control-label required">LPO Number</label>
							<div class="col-sm-8">
						  		<form:input path="lponumber" type="text" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Enter Education"  maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="lponumber" cssClass="error" /></div>										
							</div>
					  	</div>
					  	
					  	<div class="form-group">
							<label class="col-sm-4 control-label required">Colour</label>
							<div class="col-sm-8">
						  		<form:input path="color" type="text" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Enter Education"  maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="color" cssClass="error" /></div>										
							</div>
					  	</div>
					  	
					  	<div class="form-group">
							<label class="col-sm-4 control-label required">Made in</label>
							<div class="col-sm-8">
						  		<form:input path="madein" type="text" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Enter Education"  maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="madein" cssClass="error" /></div>										
							</div>
					  	</div>
					  	
					  	<div class="form-group">
							<label class="col-sm-4 control-label required">Expiry Date</label>
							<div class="col-sm-8">
						  		<form:input path="expirydate" type="text" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Enter Education"  maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="expirydate" cssClass="error" /></div>										
							</div>
					  	</div>
					  	<div class="form-group">
							<label class="col-sm-4 control-label required">Status</label>
							<div class="col-sm-8">
						  		<form:input path="status" type="text" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Enter Education"  maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="status" cssClass="error" /></div>										
							</div>
					  	</div>
					  	<div class="form-group">
							<label class="col-sm-4 control-label required">Owner Company</label>
							<div class="col-sm-8">
						  		<form:input path="ownercompany" type="text" class="form-control onlyCharacters validate" autocomplete="off" placeholder="Enter Education"  maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="ownercompany" cssClass="error" /></div>										
							</div>
					  	</div>
					  	
					  	
				  		<div class="form-group">
							<div class="col-sm-8 col-sm-offset-4"><input class="btn btn btn-primary" type="submit" id="submit1"  name="yt0" value="Add"></div>
					  	</div>
					</form:form>
					
	
<div class="row">
<div class="col-sm-12">
	<div class="box">
		<div class="box-title">
			<h3>
				<i class="fa fa-table"></i>
				Education's List
			</h3>
		</div>
		<div class="box-content nopadding w3-animate-zoom" id="tableId">
			<table class="table table-hover table-nomargin table-bordered dataTable dataTable-column_filter" data-column_filter_types="text,null">
				<thead>
				<tr>
					<th>Education</th>
					<th></th>
				</tr>
				</thead>
				<tbody></tbody>
			</table>
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
		var tableHead = '<table  class="table table-hover table-nomargin table-bordered dataTable dataTable-column_filter" data-column_filter_types="text,null">'
				+ '<thead><tr><th>Education</th><th style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
		$('#tableId').html(tableHead);
		serviceUnitArray = {};
		$.each(listOrders,function(i, orderObj) {
							var edit = "<a onclick='editEducation("	+ orderObj.id+ ")'><i style='color: green;' class='fa fa-edit'></i></a>"
							var deleterow = "<a onclick='deleteEducation("+ orderObj.id+ ")'><i style='color: red;' class='fa fa-trash'></i></a>"
							serviceUnitArray[orderObj.id] = orderObj;
							var tblRow = "<tr >"
									+ "<td title='"+orderObj.cylinderid+"'>" + orderObj.cylinderid + "</td>"
									+ "<td title='"+orderObj.cylinderid+"'>" + orderObj.size + "</td>"
									+ "<td title='"+orderObj.cylinderid+"'>" + orderObj.capacity + "</td>"
									+ "<td title='"+orderObj.cylinderid+"'>" + orderObj.cylinderstatus + "</td>"
									+ "<td title='"+orderObj.cylinderid+"'>" + orderObj.customerid + "</td>"
									+ "<td title='"+orderObj.cylinderid+"'>" + orderObj.location + "</td>"
									+ "<td title='"+orderObj.cylinderid+"'>" + orderObj.lponumber+ "</td>"
									+ "<td title='"+orderObj.cylinderid+"'>" + orderObj.color + "</td>"
									+ "<td title='"+orderObj.cylinderid+"'>" + orderObj.madein + "</td>"
									+ "<td title='"+orderObj.cylinderid+"'>" + orderObj.expirydate + "</td>"
									+ "<td title='"+orderObj.cylinderid+"'>" + orderObj.ownercompany+ "</td>"
									+ "<td title='"+orderObj.cylinderid+"'>" + orderObj.status + "</td>"
									+ "<td style='text-align: center;'>" + edit + "&nbsp;|&nbsp;" + deleterow + "</td>" 
									+ "</tr >";
							$(tblRow).appendTo("#tableId table tbody");
						});
					
 }

</script>
<%-- <tr>
<td>  Religion System</td>
 <td> <form:select path ="religionSystem" items="${religionsSystemList}"/> </td>
</tr>
 --%>

</html>