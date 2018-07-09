<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
<%
	String baseurl =  request.getScheme() + "://" + request.getServerName() +      ":" +   request.getServerPort() +  request.getContextPath();
	session.setAttribute("baseurl", baseurl);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Khaibar Gas LLC</title>
    <link rel="shortcut icon" href="${baseurl }/img/logo.jpeg"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
	<link rel="stylesheet" href="${baseurl }/newcss/bootstrap.css">
	<link rel="stylesheet" href="${baseurl }/newcss/bootstrap-extend.css">
	<link rel="stylesheet" href="${baseurl }/newcss/morris.css">	
	<link rel="stylesheet" href="${baseurl }/newcss/chartist.css">
    <link href="${baseurl }/newcss/nv.d3.min.css" rel="stylesheet" type="text/css" media="screen" />
	<link rel="stylesheet" href="${baseurl }/newcss/master_style.css">
	<link rel="stylesheet" href="${baseurl }/newcss/horizontal_menu_style.css">
	<link rel="stylesheet" href="${baseurl }/newcss/_all-skins.css">
	
	
    <link rel="stylesheet" href="${baseurl }/assets/css/styles.css">
    <link rel="stylesheet" href="${baseurl }/assets/css/animate.min.css">
    <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600' rel='stylesheet' type='text/css'>
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries. Placeholdr.js enables the placeholder attribute -->
    <!--[if lt IE 9]>
        <script type="text/javascript" src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/respond.js/1.1.0/respond.min.js"></script>
        <script type="text/javascript" src="assets/plugins/charts-flot/excanvas.min.js"></script>
    <![endif]-->

    <!-- The following CSS are included as plugins and can be removed if unused-->
<script type='text/javascript' src='${baseurl }/assets/js/jquery-1.10.2.min.js'></script>
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/charts-morrisjs/morris.css' /> 
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/codeprettifier/prettify.css' /> 
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/form-toggle/toggles.css' /> 
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/charts-morrisjs/morris.css' /> 
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/codeprettifier/prettify.css' /> 
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/form-toggle/toggles.css' /> 
<link rel='stylesheet' type='text/css' href='${baseurl }/assets/plugins/datatables/dataTables.css' /> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <link rel="stylesheet" href="${baseurl }/assets/css/MonthPicker.min.css">

<link href="${baseurl }/assets/css/datepicker1.css" rel="stylesheet" type="text/css" />



<!-- <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script> -->
<script type='text/javascript' src="${baseurl }/js/jquery.blockUI.min.js" ></script>
<script type='text/javascript' src='${baseurl }/js/ajax.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.4.2/chosen.jquery.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/chosen/1.4.2/chosen.css">
<link rel="stylesheet" href="${baseurl }/assets/css/select2.css">



<script type='text/javascript' src='${baseurl }/js/canvasjs.min.js'></script> 
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>

<style type="text/css">
#page-container, #page-content{min-height: auto;}
.control-label {
	text-align: right;
	margin-bottom: 0;
    padding-top: 8px;
}
.empkgs th, td {
    font-size: 12px !important;
    width: 100px;
}
#page-content {
min-height:0px!important;
padding-bottom:0px !important;}
.impColor{color:red;}

.view, .edit, .delete, .activate, .deactivate {cursor: pointer;}
.edit {color: blue !important;}
.deactivate {color:#e83c3d !important;}
.activate {color: green !important;}

 .impFiled {color: blue;}
 .panel-success {background-color: #4CAF50 !important;}

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

.dropdown-submenu {
    position:relative;
}
.dropdown-submenu>.dropdown-menu {
    top:0;
    left:100%;
    margin-top:-6px;
    margin-left:-1px;
    -webkit-border-radius:0 6px 6px 6px;
    -moz-border-radius:0 6px 6px 6px;
    border-radius:0 6px 6px 6px;
}
.dropdown-submenu:hover>.dropdown-menu {
    display:block;
}
.dropdown-submenu>a:after {
    display:block;
    content:" ";
    float:right;
    width:0;
    height:0;
    border-color:transparent;
    border-style:solid;
    border-width:5px 0 5px 5px;
    border-left-color:#000;
    margin-top:5px;
    margin-right:-10px;
}
.dropdown-submenu:hover>a:after {
    border-left-color:#ffffff;
}
.dropdown-submenu.pull-left {
    float:none;
}
.dropdown-submenu.pull-left>.dropdown-menu {
    left:-100%;
    margin-left:10px;
    -webkit-border-radius:6px 0 6px 6px;
    -moz-border-radius:6px 0 6px 6px;
    border-radius:6px 0 6px 6px;
}
.msgcss
{
/* 	width: 50% !important; */
/* 	font-weight: bold; */
	margin: auto;
	text-align: center;
	top: 3px !important;
	left:0;
	right:0;
	position: fixed;
	font-size: 14px;
	z-index:99999;
}
.select2
{
/* 	max-width: 100% !important; */
/* 	width: 100% !important; */
}
/* .main-sidebar{
opacity: 0;
    margin-top: 25px;
    font-size: 21px;
    text-align: center;
    -webkit-transition: opacity 2s ease-in;
    -moz-transition: opacity 2s ease-in;
    -o-transition: opacity 2s ease-in;
    -ms-transition: opacity 2s ease-in;
    transition: opacity 2s ease-in;
} */
</style>
<script type="text/javascript">
$(".main-sidebar").css('display','block');
	var isClick = 'No';
		window.setTimeout(function() {
		    $(".msgcss").fadeTo(500, 0).slideUp(500, function(){
		        $(this).remove(); 
		    });
		}, 5000);
		
		$(document).ready(function(){
			tooltip();
			/* $('.view').attr('data-toggle','tooltip');
			$('.view').attr('data-original-title','View');
			$('.edit').attr('data-toggle','tooltip');
			$('.edit').attr('data-original-title','Edit');
			$('.delete').attr('data-toggle','tooltip');
			$('.delete').attr('data-original-title','Delete');
			$('.activate').attr('data-toggle','tooltip');
			$('.activate').attr('data-original-title','Activate');
			$('.printlpo').attr('data-toggle','tooltip');
			$('.printlpo').attr('data-original-title','Print');
			$('.deactivate').attr('data-toggle','tooltip');
			$('.deactivate').attr('data-original-title','Deactivate');
			$('[data-toggle="tooltip"]').tooltip(); */
			
			var formData = new FormData();
		    
			$.fn.makeMultipartRequest('POST', 'getCount', false,
					formData, false, 'text', function(data){
				var jsonobj = $.parseJSON(data);
//		 		alert(jsonobj.cylinderCount);
				$("#cylinderCount1").text(jsonobj.totalCylinderCount);
				$("#customerCount1").text(jsonobj.customerCount);
				$("#totalGas1").text(jsonobj.totalGas);
				
//		 		var alldata = jsonobj.allOrders1;
//		 		console.log(jsonobj.allOrders1);
//		 		displayTable(alldata);
			});
		});
		
$(function() {
    $(".chzn-select").chosen();
	$(".select2").select2();
});

function tooltip(){
	$('.view').attr('data-toggle','tooltip');
	$('.view').attr('data-original-title','View');
	$('.edit').attr('data-toggle','tooltip');
	$('.edit').attr('data-original-title','Edit');
	$('.delete').attr('data-toggle','tooltip');
	$('.delete').attr('data-original-title','Delete');
	$('.activate').attr('data-toggle','tooltip');
	$('.activate').attr('data-original-title','Activate');
	$('.printlpo').attr('data-toggle','tooltip');
	$('.printlpo').attr('data-original-title','Print');
	$('.deactivate').attr('data-toggle','tooltip');
	$('.deactivate').attr('data-original-title','Deactivate');
	
	$('.viewdata').attr('data-toggle','tooltip');
	$('.viewdata').attr('data-original-title','View Data');
		$('[data-toggle="tooltip"]').tooltip();
}
</script>
</head>

 <body class="hold-transition skin-blue layout-top-nav">
	<c:if test="${not empty msg}">
		<div class="msgcss row">
			<div class="col-sm-4 col-sm-offset-4">
				<div class="form-group">
					<div class="alert alert-${cssMsg} fadeIn animated">${msg}</div>
				</div>
			</div>
		</div>
	</c:if>
	<div class="msgcss row" style="visibility: hidden" >
			<div class="col-sm-4 col-sm-offset-4">
				<div class="form-group">
					<div class="alert alert-success fadeIn animated" id="msg"></div>
				</div>
			</div>
		</div>

  
<div class="wrapper">

  <header class="main-header">
    <!-- Logo -->
    <a href="#" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
	  <b class="logo-mini">
		 <center><img src="${baseurl }/assets/img/khaibar_logo.png" style="padding-top:5px;" class="img-responsive"></center>
	  </b>
      <!-- logo for regular state and mobile devices -->
     
    </a>
    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle d-block d-lg-none" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
	  
	  
		
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Master Admin &nbsp;<i class="fa fa-caret-down"></i>
              <img src="${baseurl }/assets/demo/avatar/dangerfield.png" class="user-image rounded-circle" alt="User Image">
            </a>
            <ul class="dropdown-menu scale-up">
              <!-- User image -->
              <li class="user-header">
                <img src="${baseurl }/assets/demo/avatar/dangerfield.png" class="float-left rounded-circle" alt="User Image">

                <p>
                  Master Admin!
                  <small class="mb-5">Logged in as Master</small>
<!--                   <a href="#" class="btn btn-danger btn-sm btn-rounded">View Profile</a> -->
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row no-gutters">
                 <!--  <div class="col-12 text-left">
                    <a href="#"><i class="pull-right fa fa-pencil"></i>Edit Profile </a>
                  </div> -->
                  <div class="col-12 text-left">
                    <a href="changePassword"> <i class="pull-right fa fa-cog"></i>Change Password</a>
                  </div>
                  <div class="col-12 text-left">
                    <a href="../logoutHome" class=""><i class=" pull-right fa fa-power-off"></i>Sign Out</a>
                  </div>
                <!-- /.row -->
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li id="non">
            <a  href="#" data-toggle="control-sidebar"><i class="fa fa-cog fa-spin"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar" style="display: none;">
    <!-- sidebar -->
    <section class="sidebar" >
      <!-- sidebar menu -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="dashboard">
          <a href="${baseurl }/admin/dashboard"><i class="fa fa-dashboard"></i> <span>Dashboard</span>
          </a>
        </li>
		<li class="treeview master" class="">
          <a href="#">
            <i class="fa fa-user"></i> <span>Master</span> 
            <span class="pull-right-container">
              <i class="fa fa-angle-right pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
			<li class="items"><a href="${baseurl }/admin/itemsHome"><i class="fa fa-tint"></i> <span>Items</span></a></li>
             <li class="lpo"><a href="${baseurl }/admin/lpoHome"><i class="fa fa-bar-chart-o"></i> <span>LPO</span></a></li>
              <li class="company"><a href="${baseurl }/admin/companymaster"><i class="fa fa-building"></i> <span>Company</span></a></li>
              <li class="stores"><a href="${baseurl }/admin/storeHome"><i class="fa fa-th"></i> <span>Stores</span></a></li>
            <li class="treeview">
              <a href="#"><i class="fa fa-list"></i> <span>Cylinders</span> 
                <span class="pull-right-container">
              <i class="fa fa-angle-right pull-right"></i>
            </span>
              </a>
              <ul class="treeview-menu">
                <li class="cylinder"><a href="${baseurl }/admin/CylinderHome"><i class="fa fa-fire-extinguisher" aria-hidden="true"></i> <span>Cylinder</span></a></li>
						<li class="cylinderAutoGenHome"><a href="${baseurl }/admin/cylinderAutoGenHome"><i class="fa fa-bar-chart-o"></i> <span>Cylinder AutoGeneration</span></a></li>			  
              </ul>
            </li>    
            <li class="truck"><a href="${baseurl }/admin/truckHome"><i class="fa fa-truck" aria-hidden="true"></i> <span>Trucks</span></a></li>
             <li class="fillingStation"><a href="${baseurl }/admin/fillingStationHome"><i class="fa fa-archive"></i> <span>Filling Stations</span></a></li>
             <li class="customer"><a href="${baseurl }/admin/customerHome"><i class="fa fa-group"></i> <span>Customers</span></a></li>   
              <li class="treeview">
              <a href="#"><i class="fa fa-bar-chart-o"></i> <span>Tariff</span>
                <span class="pull-right-container">
              <i class="fa fa-angle-right pull-right"></i>
            </span>
              </a>
              <ul class="treeview-menu">
 <li class="tariffMaster"><a href="${baseurl }/admin/tariffMaster"><i class="fa fa-bar-chart-o"></i> <span>Tariff Master</span></a></li>
				<li class="customerTariffMaster"><a href="${baseurl }/admin/customerTariffMaster"><i class="fa fa-bar-chart-o"></i> <span>Private Tariff Master</span></a></li>              </ul>
            </li> 
             <li class="securedeposit"><a href="${baseurl }/admin/securedeposit"><i class="fa fa-bar-chart-o"></i> <span>Security Deposit </span></a></li>  
             <li>&nbsp;</li>     <li>&nbsp;</li>    <li>&nbsp;</li>      
          </ul>
          
        </li>
        <li class="treeview users">
          <a href="#">
            <i class="fa fa-users"></i> <span>Users</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-right pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
			<li class="staff"><a href="${baseurl }/admin/staffMaster"><i class="fa fa-user"></i> <span>Staff</span></a></li>
                <c:if test="${(cacheUserBean.roleId == 1)}">
                	<li class="usermanagement"><a href="${baseurl }/admin/usermanagement"><i class="fa fa-bar-chart-o"></i> <span>User Management </span></a></li>
                </c:if>
             </ul></li>
          <li class="treeview transactions">
          <a href="#">
           <i class="fa fa-list"></i> <span>Transactions</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-right pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
			<li class="cylinderMovetofillingStation"><a href="${baseurl }/admin/cylinderMovetofillingStation"><i class="fa fa-bar-chart-o"></i> <span>Cylinder Move to FillingStation</span></a></li>
						<li class="cylinderFilledStatus"><a href="${baseurl }/admin/cylinderFilledStatus"><i class="fa fa-bar-chart-o"></i> <span>Cylinder Filled Status</span></a></li>
                		<li class="cylinderQualityCheck"><a href="${baseurl }/admin/cylinderQualityCheck"><i class="fa fa-bar-chart-o"></i> <span>Cylinder Quality Check</span></a></li>
						<li class="cylinderMovetoTruck"><a href="${baseurl }/admin/cylinderMovetoTruck"><i class="fa fa-bar-chart-o"></i> <span>Cylinder Move to Truck</span></a></li>
						<li class="cylinderDeliver"><a href="${baseurl }/admin/cylinderDeliver"><i class="fa fa-bar-chart-o"></i> <span>Cylinder Delivered to Customer</span></a></li>
						<li class="qualityCheckHome"><a href="${baseurl }/admin/qualityCheckHome"><i class="fa fa-bar-chart-o"></i> <span>Return Cylinder Quality Check And Move To Store</span></a></li>
						<li class="privateCylinderFilled"><a href="${baseurl }/admin/privateCylinderFilled"><i class="fa fa-bar-chart-o"></i> <span>Private Cylinder Filled </span></a></li>
						<li class="dueamount"><a href="${baseurl }/admin/dueamount"><i class="fa fa-bar-chart-o"></i> <span>Due Amount Pay </span></a></li>
              </ul></li>
          <li class="treeview trucktracking">
          <a href="#">
           <i class="fa fa-list"></i> <span>Truck Tracking</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-right pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
			<li class="truckTracking"><a href="${baseurl }/admin/TruckTrakingHome"><i class="fa fa-bar-chart-o"></i> <span>Truck is going out from Factory </span></a></li>
						<li class="truckTracking1"><a href="${baseurl }/admin/TruckComingintoFactory"><i class="fa fa-bar-chart-o"></i> <span>Truck Coming into Factory</span></a></li>  
     
      </ul>
      <li class="expenseTracker"><a href="${baseurl }/admin/expenseTrackerHome"><i class="fa fa-bar-chart-o"></i> <span>Expense Tracker</span></a></li>
      <li class="treeview reports">
          <a href="#">
          <i class="fa fa-list"></i> <span>Reports</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-right pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
<li class="CylinderReport"><a href="${baseurl }/admin/reportsHome"><i class="fa fa-bar-chart-o"></i> <span>Cylinder Report </span></a></li>
						<li class="expensesReport"><a href="${baseurl }/admin/expensesReport"><i class="fa fa-bar-chart-o"></i> <span>Expenses Report</span></a></li>
						<li class="gasReport"><a href="${baseurl }/admin/gasReport"><i class="fa fa-bar-chart-o"></i> <span>Gas Report</span></a></li>
						<li class="paymentReport"><a href="${baseurl }/admin/paymentreport"><i class="fa fa-bar-chart-o"></i> <span>Payment Report</span></a></li>
						 <li class="gassummary"><a href="${baseurl }/admin/gassummary"><i class="fa fa-bar-chart-o"></i> <span>Gas Summary</span></a></li>
						 <li class="dashboard"><a href="${baseurl }/admin/dashboard"><i class="fa fa-bar-chart-o"></i> <span>Dashboard</span></a></li>
						  
	             </ul></li>
    </section>
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <div id="page-container">
    	<div id="page-content" style="min-height: auto !important;">
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
		                    <a href="#" class="btn btn-info"><span id="totalGas1"></span><br />Gas in Kgs</a>
		                </div>
	            	</div>
	            </div>
	            <div class="clearfix"></div>
	        </div>
    <!-- <section class="content-header">
      <h1>
        Dashboard
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li class="breadcrumb-item"><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="breadcrumb-item active">Dashboard</li>
      </ol>
    </section> -->

    </div>
  </div>
<!-- Header ends Here -->