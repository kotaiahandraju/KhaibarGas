<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  


	<div class="clearfix"></div>
	<ol class="breadcrumb">
		<li><a href="#">Home</a></li>
		<li>Cylinder Report</li>
	</ol>
	<div class="clearfix"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12 col-sm-12">
			<form:form commandName="reportsForm">
				<div class="row">
				
				<div class="panel panel-primary" style="width:100%;">
				<div class="panel-body">
				
				
				  	<div class="col-md-6">
						<div class="form-group">
							<label for="focusedinput" class="col-md-4 control-label">Company<span class="impColor">*</span></label>
							<div class="col-md-5">
				        		<form:select path="companyname" class="form-control " onchange="onChangeReports(id,name);" onfocus="removeBorder(this.id)">
				        			<form:option value="">- Select Company -</form:option>
				        			<form:options items="${companys}"></form:options>
				        		</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="focusedinput" class="col-md-4 control-label">Store<span class="impColor">*</span></label>
							<div class="col-md-5">
				        		<form:select path="storename" class="form-control " onchange="onChangeReports(id,name);" onfocus="removeBorder(this.id)">
				        		<form:option value="">-Select Store-</form:option>
				        			<form:options items="${stores}"></form:options>
				        		</form:select>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<label for="focusedinput" class="col-md-4 control-label">Cylinder Status<span class="impColor">*</span></label>
							<div class="col-md-5">
				        		<form:select path="cylendersstatus" class="form-control " onchange="onChangeReports(id,name);" onfocus="removeBorder(this.id)">
				        		<form:option value="">-- Select Cylinder Status --</form:option>
				        			<form:options items="${Cylinderstatus}"></form:options>
				        			</form:select>
							</div>
						</div>
					</div>
					
					<div class="col-md-6">
						<div class="form-group">
							<label for="focusedinput" class="col-md-4 control-label">LPO Number</label>
							<div class="col-md-5">
				        		<form:select path="lponumber" class="form-control " onchange="onChangeReports(id,name);" onfocus="removeBorder(this.id)">
				        			<form:option value="">-- Select LPO Number --</form:option>
				        			<form:options items="${LPONumbers}"></form:options>
				        		</form:select>
							</div>
						</div>
					</div>
					<br><br>
					
					<div class="col-md-6">
						<div class="form-group">
							<label for="focusedinput" class="col-md-4 control-label">Cylinder Size</label>
							<div class="col-md-5">
				        		<form:select path="size" class="form-control " onchange="onChangeReports(id,name);" onfocus="removeBorder(this.id)">
				        			<form:option value="">-- Select Cylinder Size --</form:option>
				        			<form:options items="${cylinderTypes}"></form:options>
				        		</form:select>
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
                            <h4>Cylinders List</h4>
                            <div class="options">   
                                <a href="javascript:;" class="panel-collapse"><i class="fa fa-chevron-down"></i></a>
                            </div>
                        </div>
                        <div class="panel-body collapse in">
                        <div class="table-responsive" id="tableId">
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
                               <thead><tr><th>Cylinder ID</th><th>Size</th><th>Cylinder Status</th><th>Owner Company</th><th>Location</th><th>LPO No</th><th>Color</th><th>Expiry Date</th><th>Remarks</th></tr></thead>
                                <tbody></tbody>
                            </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
				</form:form>
         	</div>
		</div>
	</div> <!-- container -->
	<c:choose>
		<c:when test="${empty param.cylinderstatus}">
			<c:set var="cylinderstatus" value="0"/> 
	    </c:when>
	    <c:otherwise>
			<c:set var="cylinderstatus" value="${param.cylinderstatus}"/> 
	    </c:otherwise>
	</c:choose>
	
	<c:choose>
		<c:when test="${empty param.store}">
			<c:set var="store" value="0"/> 
	    </c:when>
	    <c:otherwise>
			<c:set var="store" value="${param.store}"/> 
	    </c:otherwise>
	</c:choose>
	<c:choose>
		<c:when test="${empty param.cylindersize}">
			<c:set var="cylindersize" value="0"/> 
	    </c:when>
	    <c:otherwise>
			<c:set var="cylindersize" value="${param.cylindersize}"/> 
	    </c:otherwise>
	</c:choose>
	
	

<script type="text/javascript">
var documentMessage = "Cylinder Report";
var param1 = ${store};
if(param1 != "0"){
	$("#storename").val(param1);
	onChangeReports(param1,"storename");
}

var param = ${cylinderstatus};
if(param != "0"){
	$("#cylendersstatus").val(param);
	onChangeReports(param,"cylendersstatus");
}

var param2 = ${cylindersize};
if(param2 != "0"){
	$("#size").val(param2);
	searchcylinderstatussize();
}

$(document).ready(function() {
	  $("#parent").click(function() {
	    $(".child").prop("checked", this.checked);
	    var cylenderId = [];
        $('#tableId :checkbox:checked').each(function(i){
       	 cylenderId[i] = $(this).val();
        }); 
        $("#displayCylinders").text(cylenderId);
//         alert(cylenderId);
	    $('.child').click(function() {
	    	 var cylenderId = [];
	         $('#tableId :checkbox:checked').each(function(i){
	        	 cylenderId[i] = $(this).val();
	         }); 
	         $("#displayCylinders").text(cylenderId);
// 	         alert(cylenderId);
	        if ($('.child:checked').length == $('.child').length) {
	          $('#parent').prop('checked', true);
	        } else {
	          $('#parent').prop('checked', false);
	        }
	      });
	  });
});
/* var listOrders1 = ${allOrders1};
if (listOrders1 != "") {
	displayTable(listOrders1);
} */
function displayTable(listOrders) {
	$('#tableId').html('');
	var tableHead = '<table id="example" class="table table-striped table-bordered datatables">'
		+ '<thead><tr><th>Created Date</th><th>Cylinder ID</th><th>Size</th><th>Cylinder Status</th><th>Owner Company</th><th>Location</th><th>LPO No</th><th>Color</th><th>Expiry Date</th><th>Remarks</th></tr></thead><tbody></tbody></table>';
$('#tableId').html(tableHead);
	serviceUnitArray = {};
	$.each(listOrders,function(i, orderObj) {
	if(orderObj.status == "1"){
		var deleterow = "<a class='deactivate' onclick='deleteCylinder("+ orderObj.id+ ",0)'><i class='fa fa-eye'></i></a>"
	}else{  
		var deleterow = "<a class='activate' onclick='deleteCylinder("+ orderObj.id+ ",1)'><i class='fa fa-eye-slash'></i></a>"
	}
	var edit = "<a class='edit editIt' onclick='editCylinder("+ orderObj.id+ ")'><i class='fa fa-edit'></i></a>"
	serviceUnitArray[orderObj.id] = orderObj;
	var tblRow = "<tr >"
		
		+ "<td title='"+orderObj.createdOn+"'>"+ orderObj.createdOn + "</td>"
			+ "<td title='"+orderObj.cylinderid+"'>"+ orderObj.cylinderid + "</td>"
			+ "<td title='"+orderObj.sizeName+"'>"+ orderObj.sizeName + "</td>"
			+ "<td class='impFiled' title='"+orderObj.cylinderstatus+"'>"+ orderObj.cylinderstatus + "</td>"
			+ "<td title='"+orderObj.companyname+"'>"+ orderObj.companyname+ "</td>"
			+ "<td title='"+orderObj.location+"'>"+ orderObj.location + "</td>"
			+ "<td title='"+orderObj.lponumber+"'>"+ orderObj.lponumber+ "</td>"
			+ "<td title='"+orderObj.color+"'>"+ orderObj.color + "</td>"
			+ "<td title='"+orderObj.expirtdate1+"'>"+orderObj.expirtdate1+ "</td>"
			+ "<td title='"+orderObj.remarks+"'>"+orderObj.remarks+ "</td>"
			+ "</tr >";
	$(tblRow).appendTo("#tableId table tbody");
});
	if(isClick=='Yes'){
		$('.datatables').dataTable({
			 dom: 'lBfrtip',
			 
			 
			 buttons: [
			           {
			                        extend: 'pdfHtml5',
			                        messageTop : documentMessage,
// 			                        title : documentMessage,
									exportOptions: {columns: [0,1,2,3,4,5,6,7,8]},
			                        customize: function ( doc ) {
										doc.content.splice( 1, 0, {
											margin: [ 0, 0, 0, 12 ],
											alignment: 'center',
											image: 'data:image/jpeg;base64,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'
										});
									}
			                    }, {
		      extend: 'excel',
		      title: documentMessage,
		      filename: documentMessage
		    }, 
		    {
                extend: 'print',
                customize: function ( win ) {
                    $(win.document.body)
                        .css( 'font-size', '10pt' )
                        .prepend(
                        		documentMessage
                        )
                        .prepend(
                            '<img src="${baseurl }/assets/img/khaibar_logo.png" style="position:absolute; top:0; left:0;" />'
                        );
                    $(win.document.body).find( 'table' )
                        .addClass( 'compact' )
                        .css( 'font-size', 'inherit' );
                }
            }]
		
		});
		
	}
}

function QualityCheck(){
	var cylenderId = [];
		$('#tableId :checkbox:checked').each(function(i) {
			cylenderId[i] = $(this).val();
		});
		if (cylenderId.length == 0) {
			alert("Please Select Cylinder");
			return false;
		}
		var stationname = $("#stationname").val();
		var formData = new FormData();
		formData.append("fillingStation", stationname);
		formData.append("cylindetId", cylenderId);
		formData.append("cylinderStatus", 3);
		$.fn.makeMultipartRequest('POST', 'updateCylinderStatus2', false,
				formData, false, 'text', function(data) {
					var jsonobj = $.parseJSON(data);
					alert(jsonobj.msg);
					window.location.reload();
				});

	}

	function searchData() {
		var stationname = $("#stationname").val();
		var quantity = $("#quantity").val();
		var cylinderType = $("#cylinderType").val();
		if (stationname == null || stationname == "undefined" || stationname == "") {
			alert("Please Select Stationname");
			return false;
		}
		if (quantity == null || quantity == "undefined" || quantity == "") {
			alert("Please Enter Quantity");
			return false;
		}
		if (cylinderType == null || cylinderType == "undefined" || cylinderType == "") {
			alert("Please Select CylinderType");
			return false;
		}
		
		var formData = new FormData();
		formData.append('stationname', stationname);
		formData.append('quantity', quantity);
		formData.append('cylinderType', cylinderType);
		$.fn.makeMultipartRequest('POST', 'searchQualityCheck1', false,
				formData, false, 'text', function(data) {
					var jsonobj = $.parseJSON(data);
					var alldata = jsonobj.allOrders1;
					console.log(jsonobj.allOrders1);
					displayTable(alldata);
				});
	}
	function onChangeReports(id,name) {
		var companyname =null;
		var storename = null;
		var cylendersstatus=null;
		var lponumber = null;
		var size = null;
		/* if(name== "cylendersstatus"){
			documentMessage = "Cylinder Status Report";
			 $("#companyname").val("");
			 cylendersstatus = $("#cylendersstatus").val();
			 $("#storename").val("");
			 $("#lponumber").val("");
			 $("#size").val("");
		}
		if(name== "storename"){
			documentMessage = "Store Report";
			 $("#companyname").val("");
			  $("#cylendersstatus").val("");
			 storename= $("#storename").val();
			 $("#lponumber").val("");
			 $("#size").val("");
		}
		if(name== "companyname"){
			documentMessage = "Company Report";
			companyname= $("#companyname").val();
			 $("#cylendersstatus").val("");
			 $("#storename").val("");
			 $("#lponumber").val("");
			 $("#size").val("");
		}
		if(name== "lponumber"){
			documentMessage = "LPO Report";
			 $("#companyname").val("");
			 lponumber = $("#lponumber").val();
			 $("#storename").val("");
			  $("#cylendersstatus").val("");
			  $("#size").val("");
		}
		if(name== "size"){
			documentMessage = "LPO Report";
			 $("#companyname").val("");
			 size = $("#size").val();
			 $("#storename").val("");
			  $("#cylendersstatus").val("");
			  $("#lponumber").val("");
		}
		 */
		var companyname = $("#companyname").val();
		var storename = $("#storename").val();
		var cylendersstatus = $("#cylendersstatus").val(); 
		var lponumber = $("#lponumber").val();
		var size = $("#size").val();
		var formData = new FormData();
		formData.append('ownercompany', companyname);
		formData.append('storename', storename);
		formData.append('cylinderstatus', cylendersstatus);
		formData.append('lponumber', lponumber);
		formData.append('size', size);
		$.fn.makeMultipartRequest('POST', 'onChangeReports',
				false, formData, false, 'text', function(data) {
			console.log(data);
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			
		});
	}
	
	function searchcylinderstatussize() {
		
		
		var cylendersstatus = $("#cylendersstatus").val(); 
		var size = $("#size").val();
		var formData = new FormData();
		formData.append('cylinderstatus', cylendersstatus);
		formData.append('size', size);
		$.fn.makeMultipartRequest('POST', 'onChangeReports',
				false, formData, false, 'text', function(data) {
			console.log(data);
			var jsonobj = $.parseJSON(data);
			var alldata = jsonobj.allOrders1;
			displayTable(alldata);
			
		});
	}

	$("#pageName").text("Cylinder Report");
	// $(".transactions").addClass("open");
	// $(".transactions").addClass("active");
	$(".CylinderReport").addClass("active");
	$(".report").addClass("active");

	var isClick = 'Yes';
</script>
