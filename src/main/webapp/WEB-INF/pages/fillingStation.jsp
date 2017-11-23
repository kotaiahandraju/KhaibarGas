<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="page-content">
    <div id="wrap">
        <div id="page-heading" class="row">
        <div class="col-md-6">
              <h1>Filling Station</h1>
              </div>
              <div class="col-md-6">
              <div class="options">
                <div class="btn-toolbar">
                    <a href="#" class="btn btn-danger "><span>123456</span><br />Cylinders</a>
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
                    <div class="panel-body">
                     
                     <form:form class="form-horizontal" modelAttribute="fillingStationForm"  role="form" id="fillingstation-form" action="addfillingstation" method="post">
                     
                     <form:hidden path="id"/>
						  <div class="form-group">
						    <label for="focusedinput" class="col-sm-2 control-label">Opening Balance in GasTank </label>
						    <div class="col-sm-3">
						      <form:input path="gasavailability" class="form-control"  placeholder="Available Gas"/>
						    </div>
						    </div>
						    
                          <div class="form-group">
						    <label for="focusedinput" class="col-sm-2 control-label">Quantity</label>
						    <div class="col-sm-3">
						       <form:input path="quantity" type="text" class="form-control"  placeholder="quantity"/>
						    </div>
						    <div>
						    	 <label for="focusedinput" class="col-sm-2 control-label">Capacity</label>
						    </div>
                             <div class="col-sm-3">
						    <form:input path="gascapacity" class="form-control"  placeholder="capacity"/>
						    </div>
						  </div>
                          
                          <div class="form-group">
						    <label for="focusedinput" class="col-sm-2 control-label">Closing Balance in GasTank </label>
						    <div class="col-sm-3">
						       <form:input path="availablegas" class="form-control"  placeholder="LPO No"/>
						    </div>
						    </div>
						    
						          
                    <div class="panel-footer">
				      	<div class="row">
				      		<div class="col-sm-12">
				      			<div class="btn-toolbar  pull-right">
					      			<input type="submit" value="Submit" class="btn-primary btn"/>
					      			<input type="reset" value="Reset" class="btn-primary btn"/>
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
    </div> <!-- #wrap -->
</div> <!-- page-content -->
</div>
</body>

<script type="text/javascript">
var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
}
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
			+ '<thead><tr><th>Cylinder ID</th><th>Size</th><th>Cylinder Status</th><th>Customer ID</th><th>Location</th><th>LPO No</th><th>Color</th><th>Expiry Date</th><th>Status</th><th style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
	$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
					var edit = "<a  onclick='editCylinder("	+ orderObj.id+ ")'><i class='fa fa-pencil green'></i></a>"
					var deleterow = "<a  onclick='deleteCylinder("+ orderObj.id+ ")'><i class='fa fa-trash-o red'></i></a>"
					serviceUnitArray[orderObj.id] = orderObj;
					var tblRow = "<tr >"
							+ "<td title='"+orderObj.cylinderid+"'>"+ orderObj.cylinderid + "</td>"
							+ "<td title='"+orderObj.size+"'>"+ orderObj.size + "</td>"
							+ "<td title='"+orderObj.cylinderstatus+"'>"+ orderObj.cylinderstatus + "</td>"
							+ "<td title='"+orderObj.customerid+"'>"+ orderObj.customerid + "</td>"
							+ "<td title='"+orderObj.location+"'>"+ orderObj.location + "</td>"
							+ "<td title='"+orderObj.lponumber+"'>"+ orderObj.lponumber+ "</td>"
							+ "<td title='"+orderObj.color+"'>"+ orderObj.color + "</td>"
							+ "<td title='"+orderObj.expirydate+"'>"+ new Date(orderObj.expirydate) + "</td>"
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
	$("#expirydate1").val(serviceUnitArray[id].expirydate1);
	$("#ownercompany").val(serviceUnitArray[id].ownercompany);
	$("#customerid").val(serviceUnitArray[id].customerid);
	
	
	
	
	
//	$(window).scrollTop($('#addForm').offset().top);
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
</script>


</html>