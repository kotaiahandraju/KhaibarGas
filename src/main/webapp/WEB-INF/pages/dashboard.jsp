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
.kgs {
width:100px;
text-align:left;
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

<script>
$(document).ready(function(){
    if(!window.location.hash) {
        window.location = window.location + '#loaded';
        window.location.reload();
    }
});
</script>

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
 
 
 <table id="source" >
      <caption>
  Locating Element By Id
  </caption>
      <thead>
    <tr>
          <th></th>
          <th>Run 1</th>
          <th>Run 2</th>
          <th>Run 3</th>
        </tr>
  </thead>
      <tbody>
    <tr>
          <th>Chrome</th>
          <td>1595</td>
          <td>1578</td>
          <td>1584</td>
        </tr>
    <tr>
          <th>Firefox</th>
          <td>1470</td>
          <td>1430</td>
          <td>1500</td>
        </tr>
    <tr>
          <th>Internet Explorer</th>
          <td>2750</td>
          <td>3140</td>
          <td>3162</td>
        </tr>
    <tr>
          <th>HTMLUnit with JS</th>
          <td>170</td>
          <td>100</td>
          <td>90</td>
        </tr>
  </tbody>
    </table>

<br/>
<br/>


<!-- Body Ends Here -->
<script type="text/javascript">


$(".dashboard").addClass("active"); 
$(document).ready(function(){
	var formData = new FormData();
	$.fn.makeMultipartRequest('POST', 'getCylindersStatusCount', false,	formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		//console.log(data);
	$.each(jsonobj.emptylist,function(i, orderObj) {
		var list =' <span class="nch1"> <span class="kgs"> '+orderObj.name+' </span> <i class="fa fa-long-arrow-right ich"></i> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+1+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></span>';
		$("#emptylist").append(list);
	});
	$.each(jsonobj.fillingStation,function(i, orderObj) {
		var list =' <span class="nch1"> <span class="kgs"> '+orderObj.name+' </span> <i class="fa fa-long-arrow-right ich"></i> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+2+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></span>';
		$("#fillingstationlist").append(list);
			});
	$.each(jsonobj.filled,function(i, orderObj) {
		var list =' <span class="nch1"> <span class="kgs"> '+orderObj.name+' </span> <i class="fa fa-long-arrow-right ich"></i> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+3+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></span>';
		$("#filledlist").append(list);
	});
	$.each(jsonobj.qualitycheck,function(i, orderObj) {
		var list =' <span class="nch1"> <span class="kgs"> '+orderObj.name+' </span>  <i class="fa fa-long-arrow-right ich"></i> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+4+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></span>';
		$("#qualitychecklist").append(list);
	});
	$.each(jsonobj.truck,function(i, orderObj) {
		var list =' <span class="nch1"> <span class="kgs"> '+orderObj.name+' </span>  <i class="fa fa-long-arrow-right ich"></i> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+5+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></span>';
		$("#cylinderintrucklist").append(list);
	});
	$.each(jsonobj.delivered,function(i, orderObj) {
		var list =' <span class="nch1"> <span class="kgs"> '+orderObj.name+' </span> <i class="fa fa-long-arrow-right ich"></i> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+6+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></span>';
		$("#deliveredlist").append(list);
	});
	$.each(jsonobj.returned,function(i, orderObj) {
		var list =' <span class="nch1"> <span class="kgs"> '+orderObj.name+' </span>  <i class="fa fa-long-arrow-right ich"></i> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+7+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></span>';
		$("#receivedlist").append(list);
	});
	$.each(jsonobj.missedCylinder,function(i, orderObj) {
		var list =' <span class="nch1"> <span class="kgs"> '+orderObj.name+' </span>  <i class="fa fa-long-arrow-right ich"></i> <span class="amountp"><a class="white" target="blank" href="reportsHome?cylinderstatus='+8+'&cylindersize='+orderObj.id+'">'+orderObj.count+'</a></span></span>';
		$("#missedlist").append(list);
	});
		
	});
});





</script>
	<script type="text/javascript">
	var listOrders1 = ${allOrders1};
	var data = JSON.stringify(listOrders1);
	var myJSON = $.parseJSON(data);
	
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
		$('#source').tableBarChart('#target', '', false);
// 		$('#source2').tableBarChart('#target2', '', true);
	});
	
	var A=[{'name':'44kg','count':'35','label':'May, 2018'},{'name':'12kg','count':'3','label':'June, 2018'},{'name':'44kg','count':'5','label':'June, 2018'}];
	/* var data=[];
	var rows=[];
	$.each(recs, function(k,v){
		data[v.name]=[];
		data[v.name].push(v);
	});
	console.log(data); */
	var temp=[];
	for (var i=0; i<A.length; i++) {
	    temp[A[i].name] =
	        temp[A[i].name] === undefined ?
	            {'label':A[i].label,'count':A[i]} :
	            temp[A[i].count] + ',' + A[i].label;
	}

	A = [];

	for (var key in temp) {
	    A.push({a: key, b:temp[key]})
	}
	console.log(A);
	</script>
	
