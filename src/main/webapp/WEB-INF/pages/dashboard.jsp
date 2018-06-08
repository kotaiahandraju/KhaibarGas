<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
<style>
#target {
			width: 600px;
			height: 400px;
		}
	#target	.caption {
		display:none;
		}
.nchange {
color:#fff!important;
font-size:24px;
cursor:pointer;
}
.nch1 {
font-size:16px;
display:block;
text-align:left;
}
.lef {
font-size:19px;
}
.info-tiles .tiles-body-alt, .info-tiles .tiles-body {
    padding: 5px 20px;
    font-weight: 300;
}
.white {
color :#fff !important;
list-style:none !important;
text-decoration:none !important;
}
.ich {
font-size: 15px !important;
color: #fff !important;
width: 100px;
    text-align: center;
}
.nch1 .kgs {
width:100px;
text-align:left;
}
.empkgs th,td{
font-size:16px;
width:100px
}
</style>
	<!-- <style type="text/css">
table { border-collapse: collapse; }

caption { background: #D3D3D3; }

th {
  background: #A7C942;
  border: 1px solid #98BF21;
  color: #ffffff;
  font-weight: bold;
  text-align: left;
}

td {
  border: 1px solid #98BF21;
  text-align: left;
  font-weight: normal;
  color: #000000;
}

tr:nth-child(odd) { background: #ffffff; }

tbody tr:nth-child(odd) th {
  background: #ffffff;
  color: #000000;
}

tr:nth-child(even) { background: #EAF2D3; }

tbody tr:nth-child(even) th {
  background: #EAF2D3;
  color: #000000;
}

#target {
  width: 600px;
  height: 400px;
}

#target2 {
  width: 800px;
  height: 400px;
}
</style> -->

<script type="text/javascript" src="${baseurl }/js/TableBarChart.js"></script>
	<link rel="stylesheet" href="${baseurl }/assets/css/TableBarChart.css" />
	
        <div class="clearfix"></div>
             <ol class="breadcrumb">
              <li><a href="#">Dashboard</a></li>
            </ol>
            <div class="clearfix"></div>
        <div class="container">
            <div class="row">
	            		<div class="col-md-3 col-xs-12 col-sm-6">
	                            <div class="info-tiles tiles-orange">
	                                <div class="tiles-heading"><span class="pull-left lef">EMPTY CYLINDERS </span><span class="pull-right nchange" id="emptycylinders"><a class="white" target='_blank' href="reportsHome?cylinderstatus=1">${Empty }</a></span> </div>
	                                <div class="tiles-body-alt">
	                                <c:if test="${not empty Empty}">
	                                   <div class="text-center" id="emptylist">
	                                   <!-- <table class="empkgs" >
	                                   <tr><td>44Kg</td><td><i class="fa fa-long-arrow-right ich"></i> </td><td>15</td></tr>  <tr><td>4Kg</td><td><i class="fa fa-long-arrow-right ich"></i> </td><td>15</td></tr></table> -->
	                                   </div>
	                                </c:if>
	                                </div>
	                            </div>
	                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6">
                            <div class="info-tiles tiles-toyo">
                                <div class="tiles-heading"><span class="pull-left lef">CYLINDER IN FILLING STATION</span><span class="pull-right nchange"  id="idleCylinders"><a class="white" target='_blank' href="reportsHome?cylinderstatus=2">${FillingStation}</a></span> </div>
                                <div class="tiles-body-alt">
                                    <!--i class="fa fa-bar-chart-o"></i-->
                                     <div class="text-center" id="fillingstationlist">
	                                 </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6">
                            <div class="info-tiles tiles-alizarin">
                                <div class="tiles-heading"><span class="pull-left lef">FILLED CYLINDERS</span><span class="pull-right nchange" id="filledcylinders"><a class="white"  target='_blank' href="reportsHome?cylinderstatus=3">${Filled }</a></span></div>
                                <div class="tiles-body-alt">
                                 <c:if test="${not empty Filled}">
                                  <div class="text-center" id="filledlist">
	                               </div>
                                  </c:if>
                                </div>
                            </div>
                        </div>
                         <div class="col-md-3 col-xs-12 col-sm-6">
                            <div class="info-tiles tiles-primary">
                                <div class="tiles-heading"><span class="pull-left lef">QUALITY CHECK CYLINDERS</span><span class="pull-right nchange" id="filledcylinders"><a class="white"  target='_blank' href="reportsHome?cylinderstatus=4">${QualityCheck }</a></span></div>
                                <div class="tiles-body-alt">
                                 <c:if test="${not empty QualityCheck}">
                                  <div class="text-center" id="qualitychecklist">
	                                 </div>
                                  </c:if>
                                   
                                </div>
                            </div>
                        </div></div>
                        <div class="row">
                        <div class="col-md-3 col-xs-12 col-sm-6">
                            <div class="info-tiles tiles-info">
                                <div class="tiles-heading"><span class="pull-left lef">CYLINDERS IN TRUCK</span><span class="pull-right nchange"><a class="white"  target='_blank' href="reportsHome?cylinderstatus=5">${Truck }</a></span></div>
                                <div class="tiles-body-alt">
                                <div class="text-center" id="cylinderintrucklist" >
	                            </div>
                                   
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6">
                            <div class="info-tiles tiles-success">
                                <div class="tiles-heading" id="delivered"><span class="pull-left lef">DELIVERED CYLINDERS</span><span class="pull-right nchange" ><a class="white"  target='_blank' href="reportsHome?cylinderstatus=6">${Delivered }</a></span></div>
                                <div class="tiles-body-alt">
                                    <!--i class="fa fa-money"></i-->
                                     <c:if test="${not empty Delivered}">
                                      <div class="text-center" id="deliveredlist">
	                                 </div>
                                    </c:if>
                                    
                                </div>
                            </div>
                        </div>
                        <div class="col-md-3 col-xs-12 col-sm-6">
                            <div class="info-tiles tiles-warning">
                                <div class="tiles-heading"><span class="pull-left lef">RECEIVED CYLINDERS</span><span class="pull-right nchange"  id="returned"><a class="white"  target='_blank' href="reportsHome?cylinderstatus=7">${Returned }</a></div>
                                <div class="tiles-body-alt">
                                 <div class="text-center" id="receivedlist">
	                                 </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="col-md-3 col-xs-12 col-sm-6">
                            <div class="info-tiles tiles-green">
                                <div class="tiles-heading"><span class="pull-left lef">MISSED CYLINDERS</span><span class="pull-right nchange" id="missidcylinders"><a class="white"  target='_blank' href="reportsHome?cylinderstatus=8">${MissedCylinder }</a></div>
                                <div class="tiles-body-alt">
                                 <div class="text-center" id="missedlist">
	                                 </div>
                                </div>
                            </div>
                        </div>
                        
                    </div>
                    
            <div class="row">
            <div class="col-md-12">
            	<div align="center"><div id="target"> </div></div>
<!--                 <div class="panel panel-primary"> -->
<!--                     <div class="panel-heading"> -->
<!--                         <h4>Cylinders Usage</h4> -->
<!--                         <div class="options"> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     <div class="panel-body"> -->
<!--                       <div id="bar-example"></div> -->
<!--                     </div> -->
<!--                 </div> -->

<!-- <div id="chartContainer" style="height: 370px; max-width: 920px; margin: 0px auto;"></div>   -->
            </div>
           <!--  <div id="chart-container">FusionCharts will render here</div>
				<div id="details">
				    <div id="header" class="textStyle">Total Revenue</div>
				    <div></div>
				    <div id="data" class="textStyle">$7.25M</div>
			</div> -->
				
			
				
            <!-- <div class="col-md-6 col-lg-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Gas Usage</h4>
                        <div class="options">
                        </div>
                    </div>
                    <div class="panel-body">
                        <div id="line-example"></div>
                    </div>
                </div>
            </div> -->
            
            
        </div>

        </div> <!-- container -->
    </div> <!-- #wrap -->
</div> <!-- page-content -->
 
 
<br/>
						<div class="table-responsive" id="tableId" style="display: none" >
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
                                <thead>
                                	<tr>
                                		<th>Business Name</th><th>Product Categeory</th><th>Product Sub Categeory </th><th>Item Code</th><th>Item Description</th><th>Quantity</th>
                                	</tr>
                                </thead>
                                <tbody></tbody>
                            </table>
                         </div>

<!-- Body Ends Here -->
<script type="text/javascript">


$(".dashboard").addClass("active"); 
$(document).ready(function(){
	var formData = new FormData();
	$.fn.makeMultipartRequest('POST', 'getCylindersStatusCount', false,	formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		//console.log(data);
	$.each(jsonobj.emptylist,function(i, orderObj) {
		var list ='  <table class="empkgs"><tr><td> <span class="kgs"> '+orderObj.name+' </span></td><td> <i class="fa fa-long-arrow-right ich"></i></td><td><span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+1+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></td></tr></table>';
		$("#emptylist").append(list);
	});
	$.each(jsonobj.fillingStation,function(i, orderObj) {
		var list =' <table class="empkgs"><tr><td>  <span class="kgs"> '+orderObj.name+' </span></td><td> <i class="fa fa-long-arrow-right ich"></i></td><td> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+2+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></td></tr></table>';
		$("#fillingstationlist").append(list);
			});
	$.each(jsonobj.filled,function(i, orderObj) {
		var list =' <table class="empkgs"><tr><td>  <span class="kgs"> '+orderObj.name+' </span></td><td> <i class="fa fa-long-arrow-right ich"></i></td><td> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+3+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></td></tr></table>';
		$("#filledlist").append(list);
	});
	$.each(jsonobj.qualitycheck,function(i, orderObj) {
		var list =' <table class="empkgs"><tr><td> <span class="kgs"> '+orderObj.name+' </span></td><td>  <i class="fa fa-long-arrow-right ich"></i></td><td> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+4+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></td></tr></table>';
		$("#qualitychecklist").append(list);
	});
	$.each(jsonobj.truck,function(i, orderObj) {
		var list =' <table class="empkgs"><tr><td>  <span class="kgs"> '+orderObj.name+' </span></td><td>  <i class="fa fa-long-arrow-right ich"></i></td><td> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+5+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></td></tr></table>';
		$("#cylinderintrucklist").append(list);
	});
	$.each(jsonobj.delivered,function(i, orderObj) {
		var list =' <table class="empkgs"><tr><td>  <span class="kgs"> '+orderObj.name+' </span></td><td> <i class="fa fa-long-arrow-right ich"></i></td><td> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+6+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></td></tr></table>';
		$("#deliveredlist").append(list);
	});
	$.each(jsonobj.returned,function(i, orderObj) {
		var list =' <table class="empkgs"><tr><td>  <span class="kgs"> '+orderObj.name+' </span> </td><td> <i class="fa fa-long-arrow-right ich"></i></td><td> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+7+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></td></tr></table>';
		$("#receivedlist").append(list);
	});
	$.each(jsonobj.missedCylinder,function(i, orderObj) {
		var list =' <table class="empkgs"><tr><td>  <span class="kgs"> '+orderObj.name+' </span></td><td>  <i class="fa fa-long-arrow-right ich"></i></td><td> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+8+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></td></tr></table>';
		$("#missedlist").append(list);
	});
		
	});
});

var branches = ${branches_map};
var lstOrders =${delivered_qty_list};
createTableHeader(branches);
//console.log(lstOrders);

if(lstOrders != ""){
	showTableData(lstOrders,branches);
}

function createTableHeader(branch_map){
	//serviceUnitArray ={};
	//serviceUnitArray1 ={};
	var table=$('#tableId').html('');
	
	
	var tableHead = '<table  id="example"><thead><tr ><th></th>';
	$.each(branch_map,function(key, value) {
		var tempStr = '<th>'+value+'</th>';
		tableHead += tempStr;
	});
	/* tableHead += '<th colspan="4"  style="max-width:80px; min-width:80px;">Overall Orders</th></tr><tr><th align="center">Category</th><th align="center">Subcategory</th><th align="center">Item Code</th>'; 
	$.each(branch_map,function(key, value) {
		var tempStr = '<th align="center"  style="max-width:80px; min-width:80px;">Ordered</th><th  style="max-width:90px; min-width:90px;">Delivered</th><th  style="max-width:80px; min-width:80px;">Nullified</th><th  style="max-width:80px; min-width:80px;">Pending</th>';
		tableHead += tempStr;
	});
	tableHead += '<th align="center" style="max-width:80px; min-width:80px;">Ordered</th><th  style="max-width:90px; min-width:90px;">Delivered</th><th  style="max-width:80px; min-width:80px;">Nullified</th><th  style="max-width:100px; min-width:100px;">Pending</th></tr></thead><tbody></tbody></table>'; */
	tableHead += '</tr></thead><tbody></tbody></table>';
	$("#tableId").html(tableHead);
	//if(isClick=='Yes') $('.datatables').dataTable();
}
function showTableData(prod_map,branch_map){
	/* var tableHead = '<table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">'+
	'<thead><tr><th>key</th><th>value</th></tr>'+
	"</thead><tbody></tbody></table>"; 
$("#tableId").html(tableHead); */
	$.each(prod_map,function(key,value) {
		var total_ordered=0,total_delivered=0,total_nullified=0,total_pending=0;
		var tblRow ="<tr><th>" + key + "</th>";
		var branch_map = value;
		$.each(branch_map,function(key2,value2) {
			
			var temp_td = "<td >" + value2 + "</td>";
			tblRow += temp_td;
		});
		tblRow += "</tr>";
		$(tblRow).appendTo("#tableId table tbody");
	});
	//if(isClick=='Yes') $('.datatables').dataTable();
}


</script>
	<script type="text/javascript">
	/* var listOrders1 = ${allOrders1};
	var data = JSON.stringify(listOrders1);
	var myJSON = $.parseJSON(data); */
	
	/* window.onload = function () {
		var chart = new CanvasJS.Chart("chartContainer", {
			title:{
				text: "Gas Usage"              
			},
			data: [              
			{
				// Change type to "doughnut", "line", "splineArea", etc.
				type: "column",
				dataPoints: myJSON
			}
			]
		});
		chart.render();
	} */
	$(function() {
		$('#example').tableBarChart('#target', '', false);
// 		$('#source2').tableBarChart('#target2', '', true);
	});
	
	$(document).ready(function(){
	    if(!window.location.hash) {
	        window.location = window.location + '#loaded';
	        window.location.reload();
	    }
	});
	</script>
	
