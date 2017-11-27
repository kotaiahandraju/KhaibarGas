<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  


<div id="page-content">
    <div id="wrap">
        <div id="page-heading" class="row">
        <div class="col-md-6">
              <h1>Cylinder</h1>
              </div>
              <div class="col-md-6">
              <div class="options">
                <div class="btn-toolbar">
                    <a href="#" class="btn btn-danger "><span>${cylindersCount}</span><br />Cylinders</a>
                    <a href="#" class="btn btn-warning"><span>223456</span><br />Customers</a>
                    <a href="#" class="btn btn-info"><span>123456</span><br />Gas in Kgs</a>
                </div>
            </div>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="clearfix"></div>
             <ol class="breadcrumb">
              <li><a href="index.html">Home</a></li>
               <li>Cylinder</li>
            </ol>
            <div class="clearfix"></div>
        <div class="container">
            
            <div class="row">
            <div class="col-md-10 col-md-offset-1 col-sm-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Add Cylinder</h4>
                        <div class="options">
                        </div>
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
                    				<label for="focusedinput" class="col-md-4 control-label">Cylinder ID</label>
								    <div class="col-md-6">
								      <form:input path="cylinderid" class="form-control validate" placeholder="Cylinder ID"/>
								      <span class="hasError" id="cylinderidError"></span>
								    </div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Size</label>
								    <div class="col-md-6">
								    	<form:select path="size" items="${cylinderTypes}" class="form-control u1 validate"/>
                                            
										
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
								    	<form:input path="capacity" value="" class="form-control" readonly="true" placeholder="Capacity"/>
								      	<span class="hasError" id="capacityError"></span>
								    </div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Status</label>
								    <div class="col-md-6">
								    	<form:select path="cylinderstatus" id="selector1" class="form-control validate">
									        <form:option value="">Select Status</form:option>
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
                    				<label for="focusedinput" class="col-md-4 control-label">Location</label>
								    <div class="col-md-6">
								    	<form:input path="location" value="" class="form-control validate" placeholder="Location" />
								      	<span class="hasError" id="locationError"></span>
								    </div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">LPO Number</label>
								    <div class="col-md-6">
								    	<form:input path="lponumber" value="" class="form-control validate" placeholder="LPO No" />
								      	<span class="hasError" id="lponumberError"></span>
								    </div>
                    			</div>
                    		</div>
                    	</div>
                    	
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Owner Company</label>
								    <div class="col-md-6">
								    	<form:input path="ownercompany" value="" class="form-control validate" placeholder="Owner Company" />
								      	<span class="hasError" id="ownercompanyError"></span>
								    </div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Color Of Cylinder</label>
								    <div class="col-md-6">
								    	<form:input path="color" value="" class="form-control  validate"  placeholder="Color Of Cylinder"/>
								      	<span class="hasError" id="colorError"></span>
								    </div>
                    			</div>
                    		</div>
                    	</div>
                    	
                    	<div class="row">
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Made In</label>
								    <div class="col-md-6">
								    	<form:input path="madein" value="" class="form-control validate" placeholder="Made In" />
								      	<span class="hasError" id="madeinError"></span>
								    </div>
                    			</div>
                    		</div>
                    		<div class="col-md-6">
                    			<div class="form-group">
                    				<label for="focusedinput" class="col-md-4 control-label">Expiry Date</label>
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
					      			<input type="reset" value="Reset" class="btn-danger btn"/>
				      			</div>
				      		</div>
				      	</div>
				      </div>
				      
				      </form:form>
			</div>
                    
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
    </div> <!-- #wrap -->
</div> <!-- page-content -->

<script type='text/javascript' src='${baseurl }/js/jquery-ui.min.js'></script> 
<script type='text/javascript' src='${baseurl }/js/custemValidation.js'></script> 
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script> 

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
					var edit = "<a class='edit' onclick='editCylinder("	+ orderObj.id+ ")'><i class='fa fa-pencil green'></i></a>"
					var deleterow = "<a class='delete' onclick='deleteCylinder("+ orderObj.id+ ")'><i class='fa fa-trash-o red'></i></a>"
					serviceUnitArray[orderObj.id] = orderObj;
					var tblRow = "<tr >"
							+ "<td title='"+orderObj.cylinderid+"'>"+ orderObj.cylinderid + "</td>"
							+ "<td title='"+orderObj.size+"'>"+ orderObj.size + "</td>"
							+ "<td title='"+orderObj.cylinderstatus+"'>"+ orderObj.cylinderstatus + "</td>"
							+ "<td title='"+orderObj.location+"'>"+ orderObj.location + "</td>"
							+ "<td title='"+orderObj.lponumber+"'>"+ orderObj.lponumber+ "</td>"
							+ "<td title='"+orderObj.color+"'>"+ orderObj.color + "</td>"
							+ "<td title='"+orderObj.expirtdate1+"'>"+orderObj.expirtdate1+ "</td>"
							+ "<td title='"+orderObj.status+"'>"+ orderObj.status + "</td>"
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
	$("#status").val(serviceUnitArray[id].cylinderstatus);
	$("#location").val(serviceUnitArray[id].location);
	$("#lponumber").val(serviceUnitArray[id].lponumber);
	$("#color").val(serviceUnitArray[id].color);
	$("#madein").val(serviceUnitArray[id].madein);
	$("#expirtdate1").val(serviceUnitArray[id].expirtdate1);
	$("#ownercompany").val(serviceUnitArray[id].ownercompany);
	$("#submit1").val("Update");
	$(window).scrollTop($('body').offset().top);
	
	}
function deleteCylinder(id){
	var checkstr =  confirm('Are you sure you want to delete this?');
	if(checkstr == true){
	var formData = new FormData();
     formData.append('id', id);
	$.fn.makeMultipartRequest('POST', 'deleteCylinder', false,
			formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		alert(jsonobj.message);
		var alldata = jsonobj.allOrders1;
		console.log(jsonobj.allOrders1);
		displayTable(alldata);
	});
	}
	
}









  $(function () {
	$("#expirtdate1").datepicker({
	 dateFormat: "dd-MM-yy",
	 changeDate : true,
		changeMonth : true,
		changeYear : true,
	});
	});
  
 
 
 
 /* function displayVals() {    
	  var singleValues = $( "#size" ).val();  
	console.log(singleValues);  
	$(capacity).val=displayVals();
	   
	}    
	$( "select" ).change( displayVals ); 
	displayVals();  
 */
 
 $(function () {
	  $("#size").change(function () {
	    if ($(this).val() == "large")
	      $("#capacity").val("44");
	    if ($(this).val() == "medium")
	    	  $("#capacity").val("22");
	    if ($(this).val() == "small")
	    	  $("#capacity").val("11");
	    
	  });
	});
 
 
 function validate(id){
		if($('#cylinderid').val() ==  null || $('#cylinderid').val() == ""  || $('#cylinderid').val()=="undefined" ) {
			$('#nameError').css('color','red');
		    $("#nameError").text("Cylinder id   cannot be blank.");
		}else{
			$("#nameError").text("");
		}
		}
		
 
 $("#submit11").click(function()
			{			
				if($('#cylinderid').val() ==  null || $('#cylinderid').val() == ""  || $('#cylinderid').val()=="undefined")
				{
					if($('#cylinderid').val() ==  null || $('#cylinderid').val() == ""  || $('#cylinderid').val()=="undefined" ) 
					{
//	 				    
					    $('#cylinderidError').css('color','red');
					    $("#cylinderidError").text("Cylinderid cannot be blank.");
				    }
					return false;
					 $("#cylinder-form").submit();
				}
				});
				
 
 function isDate(txtDate)
 {

   var currVal1 = $("#"+txtDate+"").val();
   
   
   var currVal=$.trim(currVal1);
   if(currVal == '')
     return false;
   //Declare Regex 
   var rxDatePattern = /^(\d{1,2})(\/|-)(\d{1,2})(\/|-)(\d{4})$/;
   
   if(!currVal.match(rxDatePattern)||currVal=="")
	   console.log("Enter valid date");
	   
      
   return true;
 }
 


</script>