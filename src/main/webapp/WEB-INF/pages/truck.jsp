<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>



        <div class="clearfix"></div>
             <ol class="breadcrumb">
              <li><a href="index.html">Home</a></li>
               <li>Truck</li>
            </ol>
            <div class="clearfix"></div>
        <div class="container">
            
                    
            <div class="row">
            <div class="col-md-10 col-md-offset-1 col-sm-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Add Truck</h4>
                        <div class="options">
                        </div>
                    </div>
                    <div class="panel-body">
                     <form:form modelAttribute="truckForm" class="form-horizontal" action="addTruck">
                     <c:if test="${not empty msg}">
									<div class="alert alert-success fadeIn animated">${msg}</div>
								</c:if>
                     <form:hidden path="id"/>
						  <div class="form-group">
						    <label for="focusedinput" class="col-sm-2 control-label">Truck Number<span class="impColor">*</span></label>
						    <div class="col-sm-3">
						      <form:input path="trucknumber" type="text" class="form-control validate"  placeholder="Ttruck Number"/>
						    </div>
						    <div>
						    	 <label for="focusedinput" class="col-sm-2 control-label">Registration Expiry<span class="impColor">*</span></label>
						    </div>
                             <div class="col-sm-3">
						      <form:input path="registrationexpirydate1" type="text" readonly="true" class="form-control"  placeholder="Registration Expiry"/>
						    </div>
						  </div>
                          
                          <div class="form-group">
						    <label for="focusedinput" class="col-sm-2 control-label">Civil defense card expiry<span class="impColor">*</span></label>
						    <div class="col-sm-3">
						       <form:input path="civildefensecardexpirydate1" type="text" readonly="true" class="form-control"  placeholder="Civil defense card expiry"/>
						    </div>
						    <div>
						    	 <label for="focusedinput" class="col-sm-2 control-label">Service Due<span class="impColor">*</span></label>
						    </div>
                             <div class="col-sm-3">
                             <form:input path="servicedue1" type="text" class="form-control" readonly="true"  placeholder="Servicedue"/>
						    <%-- <form:select path="servicedue"  class="form-control">
						  		<form:option value="Maintenance">Maintenance</form:option>
						  		<form:option value="Agency Repairs">Agency Repairs</form:option>
						  		<form:option value="Agency Service">Agency Service</form:option>
						  	</form:select> --%>
						    </div>
						  </div>
                          
                          <div class="form-group">
						    <label for="focusedinput" class="col-sm-2 control-label">Make<span class="impColor">*</span></label>
						    <div class="col-sm-3">
						       <form:input path="make" type="text" class="form-control validate"  placeholder="Make"/>
						    </div>
						    <div>
						    	 <label for="focusedinput" class="col-sm-2 control-label">Description<span class="impColor">*</span></label>
						    </div>
                             <div class="col-sm-3">
                              <form:input path="description" type="text" class="form-control validate"  placeholder="Description"/>
						    
						    </div>
						  </div>
                          
                          <div class="form-group">
						    <label for="focusedinput" class="col-sm-2 control-label">Capacity of truck<span class="impColor">*</span></label>
						    <div class="col-sm-3">
						       <form:input path="capacityoftruck" type="text" class="form-control validate"  placeholder="Capacity of truck"/>
						    </div>
						    <div>
						    	 <label for="focusedinput" class="col-sm-2 control-label">LPO number <span class="impColor">*</span> </label>
						    </div>
                             <div class="col-sm-3">
						    <form:input path="lponumber" type="text" class="form-control validate"  placeholder="LPO number "/>
						    </div>
						  </div>
                          
                           <div class="form-group">
						    <label for="focusedinput" class="col-sm-2 control-label ">Type Of Service<span class="impColor">*</span></label>
						    <div class="col-sm-3">
						      <form:select path="typeOfService"  class="form-control">
						  		<form:option value="Maintenance">Maintenance</form:option>
						  		<form:option value="Agency Repairs">Agency Repairs</form:option>
						  		<form:option value="Agency Service">Agency Service</form:option>
						  	</form:select>
						    </div>
						   
						  </div>
						
                    </div>
                    
                    <div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar  pull-right">
				      			<input class="btn-primary btn" type="submit" value="Sumbit" id="submit1">
				      			<input class="btn-default btn" type="reset" value="Cancel">
					      			</form:form>
				      			</div>
				      		</div>
				      	</div>
				      </div>
                </div>
            </div>
            
            
            
        </div>
        
        <div class="row">
              <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h4>Truck List</h4>
                            <div class="options">   
                                <a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
                            </div>
                        </div>
                        <div class="panel-body collapse in">
                        <div class="table-responsive" id="tableId">
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
                                <thead>
                                    <tr>
                                        <th>Truck Number</th> <th>Registration Expiry</th> <th>Civil defense card expiry</th><th>Service due</th> <th>Make</th><th>Description</th><th>Capacity of truck</th><th>LPO number</th><th>Truck Status</th><th>Action</th>
                                    </tr>
                                </thead>
                                <tbody> </tbody>
                            </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div> <!-- container -->
    </div> <!-- #wrap -->
</div> <!-- page-content -->
<script type='text/javascript' src='${baseurl }/js/jquery-ui.min.js'></script> 
<!--  <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script> -->
<script type='text/javascript' src='${baseurl }/js/custemValidation.js'></script> 
<script type="text/javascript">
$(function () {
$("#registrationexpirydate1").datepicker({
 dateFormat: "dd-MM-yy",
 changeDate : true,
	changeMonth : true,
	changeYear : true,
});

$("#civildefensecardexpirydate1").datepicker({
	 dateFormat: "dd-MM-yy",
	 changeDate : true,
		changeMonth : true,
		changeYear : true,
	});
	
$("#servicedue1").datepicker({
	 dateFormat: "dd-MM-yy",
	 changeDate : true,
		changeMonth : true,
		changeYear : true,
	});
	

});


var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr> <th>Truck Number</th> <th>Registration Expiry</th> <th>Civil defense card expiry</th><th>Service due</th> <th>Make</th><th>Description</th><th>Capacity of truck</th><th>LPO number</th><th>Truck Status</th><th>Action</th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
					var edit = "<a  onclick='editTruckMaster("+ orderObj.id+ ")'><i class='fa fa-pencil green'></i></a>"
					var deleterow = "<a  onclick='deletetruckMaster("+ orderObj.id+ ")'><i class='fa fa-trash-o red'></i></a>"
					serviceUnitArray[orderObj.id] = orderObj;
					var tblRow = "<tr >"
							+ "<td title='"+orderObj.trucknumber+"'>"+ orderObj.trucknumber + "</td>"
							+ "<td title='"+orderObj.registrationexpirydate1+"'>"+ orderObj.registrationexpirydate1 + "</td>"
							+ "<td title='"+orderObj.civildefensecardexpirydate1+"'>"+ orderObj.civildefensecardexpirydate1 + "</td>"
							+ "<td title='"+orderObj.servicedue1+"'>"+ orderObj.servicedue1 + "</td>"
							+ "<td title='"+orderObj.make+"'>"+ orderObj.make + "</td>"
							+ "<td title='"+orderObj.description+"'>"+ orderObj.description+ "</td>"
							+ "<td title='"+orderObj.capacityoftruck+"'>"+ orderObj.capacityoftruck + "</td>"
							+ "<td title='"+orderObj.lponumber+"'>"+ orderObj.lponumber + "</td>"
							+ "<td title='"+orderObj.truckStatus+"'>"+ orderObj.truckStatus + "</td>"
							
							+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
							+ "</tr >";
					$(tblRow).appendTo("#tableId table tbody");
				
	});
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
	$(window).scrollTop($('#page-heading').offset().top);
	}
function deletetruckMaster(id){
	var checkstr =  confirm('Are you sure you want to delete this?');
	if(checkstr == true){
	var formData = new FormData();
     formData.append('id', id);
	$.fn.makeMultipartRequest('POST', 'deletetruckMaster', false,
			formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		alert(jsonobj.message);
		var alldata = jsonobj.allOrders1;
		console.log(jsonobj.allOrders1);
		displayTable(alldata);
	});
	}
	
}
$("#pageName").text("Truck Master");
$(".truck").addClass("active"); 
</script>
