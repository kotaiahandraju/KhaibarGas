<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  





<!-- Body starts heare -->
        <div class="clearfix"></div>
             <ol class="breadcrumb">
              <li><a href="#">Home</a></li>
               <li>Cylinder</li>
            </ol>
            <div class="clearfix"></div>
        <div class="container">
            
            <div class="row">
            <div class="col-md-10 col-md-offset-1 col-sm-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Add Cylinder</h4>
                        <div class="options"></div>
                    </div>
                    <form:form class="form-horizontal" modelAttribute="cylinderForm"  role="form" id="cylider-form" action="addcylinder" method="post">
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
                    				<label for="focusedinput" class="col-md-4 control-label">Cylinder ID<span class="impColor">*</span></label>
								    <div class="col-md-6">
								      <form:input path="cylinderid" class="form-control validate" placeholder="Cylinder ID"/>
								      <span class="hasError" id="cylinderidError"></span>
								    </div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Size<span class="impColor">*</span></label>
								    <div class="col-md-6">
								    	<form:select path="size" items="${cylinderTypes}" class="form-control cylinderSize"/>
								      <span class="hasError" id="sizeError"></span>
								    </div>
                    			</div>
                    		</div>
                    	</div>
                    	
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label ">Capacity</label>
								    <div class="col-md-6">
								    	<form:input path="capacity" value="11" class="form-control" readonly="true" placeholder="Capacity"/>
								      	<span class="hasError" id="capacityError"></span>
								    </div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Status<span class="impColor">*</span></label>
								    <div class="col-md-6">
								    	<form:select path="cylinderstatus" class="form-control">
									  		<form:option value="idle">Idle</form:option>
									  		<form:option value="filled">Filled</form:option>
									  		<form:option value="Delivered">Delivered</form:option>
			                                <form:option value="empty">Empty</form:option>
			                                <form:option value="missing">Missing</form:option>
									  	</form:select>
								      <span class="hasError" id="cylinderstatusError"></span>
								    </div>
                    			</div>
                    		</div>
                    	</div>
                     
                     <div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Location<span class="impColor">*</span></label>
								    <div class="col-md-6">
								    	<form:input path="location" value="" class="form-control validate onlyCharacters" placeholder="Location" />
								      	<span class="hasError" id="locationError"></span>
								    </div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">LPO Number<span class="impColor">*</span></label>
								    <div class="col-md-6">
								    	<form:select path="lponumber" value="" class="form-control  chzn-select validate"  onchange="removeBorder(this.id)" >
								    	<form:option value="">-- Select LPO Number --</form:option>
								    	<form:options items="${LPONumbers }"></form:options>
								    	</form:select>
								      	<span class="hasError" id="lponumberError"></span>
								    </div>
                    			</div>
                    		</div>
                    	</div>
                    	
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Owner Company<span class="impColor">*</span></label>
								    <div class="col-md-6">
								    	<form:input path="ownercompany" value="" class="form-control validate onlyCharacters" placeholder="Owner Company" />
								      	<span class="hasError" id="ownercompanyError"></span>
								    </div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Color Of Cylinder<span class="impColor">*</span></label>
								    <div class="col-md-6">
								    	<form:select path="color" class="form-control" value="Red">
									  		<form:option value="red">Red</form:option>
									  		<form:option value="green">Green</form:option>
									  		<form:option value="yellow">Yellow</form:option>
			                                <form:option value="blue">Blue</form:option>
			                                <form:option value="pink">Pink</form:option>
			                                <form:option value="indigo">Indigo</form:option>
			                                <form:option value="violet">Violet</form:option>
			                                <form:option value="orange">Orange</form:option>
			                               </form:select>
			                                
								      	<span class="hasError" id="colorError"></span>
								    </div>
                    			</div>
                    		</div>
                    	</div>
                    	
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Made In<span class="impColor">*</span></label>
								    <div class="col-md-6">
								    	<form:input path="madein" value="" class="form-control validate onlyCharacters" placeholder="Made In" readonly="true" />
								      	<span class="hasError" id="madeinError"></span>
								    </div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Expiry Date<span class="impColor">*</span></label>
								    <div class="col-md-6">
								    	<form:input path="expirtdate1" value="" class="form-control" readonly="true" placeholder="Expiry Date" onblur="isDate(this.id)" />
								      	<span class="hasError" id="expirydateError"></span>
								    </div>
                    			</div>
                    		</div>
                    	</div>
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Remarks</label>
								    <div class="col-md-6">
								    	<form:input path="remarks" value="" class="form-control"  placeholder="Remarks"/>
								      	<span class="hasError" id="remarksError"></span>
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
					      			<input type="reset" value="Reset" class="btn-danger btn cancel"/>
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
                            <h4>Cylinders List</h4>
                            <div class="options">   
                                <a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
                            </div>
                        </div>
                        <div class="panel-body collapse in">
                        <div class="table-responsive" id="tableId">
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
                                <thead>
                                    <tr>
                                        <th>Cylinder ID</th><th>Size</th><th>Cylinder Status</th><th>Customer ID</th><th>Location</th><th>LPO No</th>
                                        <th>Color</th><th>Expiry Date</th><th>status</th><th></th>
                                    </tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div> <!-- container -->

<script type='text/javascript' src='${baseurl }/js/jquery-ui.min.js'></script> 

<script type="text/javascript">

var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Cylinder ID</th><th>Size</th><th>Cylinder Status</th><th>Location</th><th>LPO No</th><th>Color</th><th>Expiry Date</th><th>Status</th><th style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
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
							+ "<td title='"+orderObj.cylinderid+"'>"+ orderObj.cylinderid + "</td>"
							+ "<td title='"+orderObj.size+"'>"+ orderObj.size + "</td>"
							+ "<td title='"+orderObj.cylinderstatus+"'>"+ orderObj.cylinderstatus + "</td>"
							+ "<td title='"+orderObj.location+"'>"+ orderObj.location + "</td>"
							+ "<td title='"+orderObj.lponumber+"'>"+ orderObj.lponumber+ "</td>"
							+ "<td title='"+orderObj.color+"'>"+ orderObj.color + "</td>"
							+ "<td title='"+orderObj.expirtdate1+"'>"+orderObj.expirtdate1+ "</td>"
							+ "<td title='"+orderObj.cylendersstatus+"'>"+ orderObj.cylendersstatus + "</td>"
							+ "<td style='text-align: center;white-space: nowrap;'>" + edit + "&nbsp;&nbsp;" + deleterow + "</td>" 
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


function editCylinder(id) {
	$("#id").val(serviceUnitArray[id].id);
	$("#cylinderid").val(serviceUnitArray[id].cylinderid);
	$("#size").val(serviceUnitArray[id].size);
	$("#capacity").val(serviceUnitArray[id].capacity);
	$("#location").val(serviceUnitArray[id].location);
	$("#lponumber").val(serviceUnitArray[id].lponumber);
	$("#lponumber").trigger("chosen:updated");
	$("#color").val(serviceUnitArray[id].color);
	$("#madein").val(serviceUnitArray[id].madein);
	$("#expirtdate1").val(serviceUnitArray[id].expirtdate1);
	$("#ownercompany").val(serviceUnitArray[id].ownercompany);
	$("#cylinderstatus").val(serviceUnitArray[id].cylinderstatus);
	$("#remarks").val(serviceUnitArray[id].remarks);
	$("#submit1").val("Update");
	$(window).scrollTop($('body').offset().top);
	
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
	$.fn.makeMultipartRequest('POST', 'deleteCylinder', false,
			formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		window.location.reload();
// 		var alldata = jsonobj.allOrders1;
// 		console.log(jsonobj.allOrders1);
// 		displayTable(alldata);
	});
	}
	
}

$('#size').change(function(){
	
	
    var cid = $(this).val();

    var formData = new FormData();
    formData.append('cid', cid);
    $.fn.makeMultipartRequest('POST', 'getCylinderCapacity', false,
			formData, false, 'text', function(data){
    	console.log(data);
    	$("#capacity").val(data);
    });
});


/* $('#lponumber').blur(function(){
    var lpoid = $(this).val();

    var formData = new FormData();
    formData.append('lpoid', lpoid);
    $.fn.makeMultipartRequest('POST', 'getCylinderMadein', false,
			formData, false, 'text', function(data){
    	console.log(data);
    	$("#madein").val(data);
    });
}); */




	/* $.ajax({
>>>>>>> c0fed2516fec227f765d486569babfd9045f3c29
			type : "GET",
			url : "getCylinderCapacity",
			data : {"cid":cid},
			dataType : "text",
			success : function(data) {
				console.log(data);
				$("#capacity").val(data);
			}
		});

	}); */

	$(function() {
		$("#expirtdate1").datepicker({
			dateFormat : "dd-MM-yy",
			changeDate : true,
			changeMonth : true,
			changeYear : true,
		});
	});


 $("#pageName").text("Cylinder Master");
 $(".cylinder").addClass("active"); 
 
</script>