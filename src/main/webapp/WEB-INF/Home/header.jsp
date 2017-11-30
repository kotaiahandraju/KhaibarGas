
	
	  <%
     
     
String baseurl =  request.getScheme() + "://" + request.getServerName() +      ":" +   request.getServerPort() +  request.getContextPath();
session.setAttribute("baseurl", baseurl);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Khaibar Gas LLC</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${baseurl }/assets/css/styles.css">
    <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600' rel='stylesheet' type='text/css'>
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries. Placeholdr.js enables the placeholder attribute -->
    <!--[if lt IE 9]>
        <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.1.0/respond.min.js"></script>
        <script type="text/javascript" src="assets/plugins/charts-flot/excanvas.min.js"></script>
    <![endif]-->

    <!-- The following CSS are included as plugins and can be removed if unused-->

<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/charts-morrisjs/morris.css' /> 
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/codeprettifier/prettify.css' /> 
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/form-toggle/toggles.css' /> 
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/charts-morrisjs/morris.css' /> 
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/codeprettifier/prettify.css' /> 
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/form-toggle/toggles.css' /> 
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/datatables/dataTables.css' /> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link href="${baseurl }/assets/css/datepicker1.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='${baseurl }/assets/js/jquery-1.10.2.min.js'></script>
<script type='text/javascript' src='${baseurl }/js/ajax.js'></script>

<style type="text/css">
.impColor{color:red;}

.edit, .delete, .active, .deactive {cursor: pointer;}

span.has-error,span.hasError
{
  font-weight:normal;
  border-color: #e73d4a;
  color:red;
  margin-top: -3px;
  display: block !important;
  position: absolute;
}

.error{color: red; font-weight: bold;}

.alert-success, .alert-warning, .alert-danger{color: white !important;}
.alert-success{background-color: #4CAF50 !important;}
.alert-warning{background-color: #ff6600 !important;}
.alert-danger{background-color: #d43f3a !important;}

.your-class::-webkit-input-placeholder {color: #e73d4a !important;}
.your-class::-moz-placeholder {color: #e73d4a !important;}

.default-class::-webkit-input-placeholder {color: #e73d4a !important;}
.default-class::-moz-placeholder {color: #e73d4a !important;}
</style>
	<script>
		window.setTimeout(function() {
		    $(".msgcss").fadeTo(500, 0).slideUp(500, function(){
		        $(this).remove(); 
		    });
		}, 5000);
		$(document).ready(function(){
			$('.edit').attr('data-toggle','tooltip');
			$('.edit').attr('data-original-title','Edit');
			$('.delete').attr('data-toggle','tooltip');
			$('.delete').attr('data-original-title','Delete');
			$('.active').attr('data-toggle','tooltip');
			$('.active').attr('data-original-title','Activate');
			$('.deactive').attr('data-toggle','tooltip');
			$('.deactive').attr('data-original-title','Deactivate');
			$('[data-toggle="tooltip"]').tooltip();
		});
	</script>
</head>

<body class="horizontal-nav ">
    <header class="navbar navbar-inverse navbar-fixed-top" role="banner">
        <div class="navbar-header pull-left">
            <a class="navbar-brand" href="#">KHAIBAR GAS LLC</a>
        </div>
		<div class="masters">
	        <ul class="nav navbar-nav pull-right toolbar">
	            <li class="dropdown">
	                <a href="#" class="dropdown-toggle username" data-toggle="dropdown"><span class="hidden-xs">Master Admin <i class="fa fa-caret-down"></i></span><img src="${baseurl }/assets/demo/avatar/dangerfield.png" alt="Dangerfield" /></a>
	                <ul class="dropdown-menu userinfo arrow">
	                    <li class="username">
	                        <a href="#">
	                            <div class="pull-left"><img src="${baseurl }/assets/demo/avatar/dangerfield.png" alt=""/></div>
	                            <div class="pull-right"><h5>Master Admin!</h5><small>Logged in as <span>Master</span></small></div> 
	                        </a>
	                    </li>
	                    <li class="userlinks">
	                        <ul class="dropdown-menu">
	                            <li><a href="#">Edit Profile <i class="pull-right fa fa-pencil"></i></a></li>
	                            <li><a href="#">Change Password <i class="pull-right fa fa-cog"></i></a></li>
	                            <li class="divider"></li>
	                            <li><a href="../logoutHome" class="text-right">Sign Out</a></li>
	                        </ul>
	                    </li>
	                </ul>
	            </li>
	        </ul>
        </div>
    </header>

    <nav class="navbar navbar-default yamm" role="navigation">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <i class="fa fa-bars"></i>
            </button>
        </div>
        <div class="collapse navbar-collapse navbar-ex1-collapse" id="horizontal-navbar">
            <ul class="nav navbar-nav">
                <li class="dashboard"><a href="${baseurl }/admin/dashboard"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
                <li class="cylinder"><a href="${baseurl }/admin/CylinderHome"><i class="fa fa-fire-extinguisher" aria-hidden="true"></i> <span>CYLINDERS</span></a></li>
                <li class="truck"><a href="${baseurl }/admin/truckHome"><i class="fa fa-truck" aria-hidden="true"></i> <span>TRUCKS</span></a></li>
                <li class="accessories"><a href="${baseurl }/admin/accessoriesHome"><i class="fa fa-tint"></i> <span>ACCESSORIES</span></a></li>
                <li class="stores"><a href="${baseurl }/admin/storeHome"><i class="fa fa-th"></i> <span>STORES</span></a></li>
                <li class="fillingStation"><a href="${baseurl }/admin/fillingStationHome"><i class="fa fa-archive"></i> <span>FILLING STATIONS</span></a></li>
                <li class="customer"><a href="${baseurl }/admin/customerHome"><i class="fa fa-group"></i> <span>CUSTOMERS</span></a></li>
                <li class="staff"><a href="${baseurl }/admin/staffMaster"><i class="fa fa-user"></i> <span>STAFF</span></a></li>
                <li class="company"><a href="${baseurl }/admin/companymaster"><i class="fa fa-building"></i> <span>COMPANY</span></a></li>
                <li class="lpo"><a href="${baseurl }/admin/lpoHome"><i class="fa fa-bar-chart-o"></i> <span>LPO</span></a></li>
                 <li class="tariffMaster"><a href="${baseurl }/admin/tariffMaster"><i class="fa fa-bar-chart-o"></i> <span>Tariff Master</span></a></li>
                 <li class="cylinderMovetofillingStation"><a href="${baseurl }/admin/cylinderMovetofillingStation"><i class="fa fa-bar-chart-o"></i> <span>Cylinder Move to FillingStation</span></a></li>
                <li class="checkQuality"><a href="${baseurl }/admin/checkQuality"><i class="fa fa-bar-chart-o"></i> <span>checkQuality</span></a></li>
                <li><a href="#"><i class="fa fa-list"></i> <span>REPORTS</span></a></li>
            </ul>
            
        </div>
    </nav>


    <div id="page-container">
    	<div id="page-content">
    <div id="wrap">
        <div id="page-heading" class="row">
        <div class="col-md-6">
              <h1 id="pageName"></h1>
              </div>
              <div class="col-md-6">
              <div class="options">
                <div class="btn-toolbar">
                    <a href="#" class="btn btn-danger "><span id="cylinderCount1"></span><br />Cylinders</a>
                    <a href="#" class="btn btn-warning"><span id="customerCount1"></span><br />Customers</a>
                    <a href="#" class="btn btn-info"><span>123456</span><br />Gas in Kgs</a>
                </div>
            </div>
            </div>
            <div class="clearfix"></div>
        </div>
<!-- Header ends Here -->
<script type="text/javascript">
$( document ).ready(function() {
	var formData = new FormData();
    
	$.fn.makeMultipartRequest('POST', 'getCount', false,
			formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
// 		alert(jsonobj.cylinderCount);
		$("#cylinderCount1").text(jsonobj.cylinderCount);
		$("#customerCount1").text(jsonobj.customerCount);
// 		var alldata = jsonobj.allOrders1;
// 		console.log(jsonobj.allOrders1);
// 		displayTable(alldata);
	});

});
	
</script>