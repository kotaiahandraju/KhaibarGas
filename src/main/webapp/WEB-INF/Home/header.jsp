
	
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

<link href="${baseurl }/assets/css/datepicker1.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='${baseurl }/assets/js/jquery-1.10.2.min.js'></script>
<script type='text/javascript' src='${baseurl }/js/ajax.js'></script>
  
</head>

<body class="horizontal-nav ">
    <header class="navbar navbar-inverse navbar-fixed-top" role="banner">
       

        <div class="navbar-header pull-left">
            <a class="navbar-brand" href="index.html">KHAIBAR GAS LLC</a>
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
                            <li><a href="#" class="text-right">Sign Out</a></li>
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
                <li class="active"><a href="index.html"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
                <li><a href="${baseurl }/admin/CylinderHome"><i class="fa fa-fire-extinguisher" aria-hidden="true"></i> <span>CYLINDERS</span></a></li>
                <li><a href="${baseurl }/admin/truckHome"><i class="fa fa-truck" aria-hidden="true"></i> <span>TRUCKS</span></a></li>
                <li><a href="${baseurl }/admin/accessoriesHome"><i class="fa fa-tint"></i> <span>ACCESSORIES</span></a></li>
                <li><a href="#"><i class="fa fa-th"></i> <span>STORES</span></a></li>
                <li><a href="${baseurl }/admin/fillingStationHome"><i class="fa fa-archive"></i> <span>FILLING STATIONS</span></a></li>
                <li><a href="${baseurl }/admin/customerHome"><i class="fa fa-group"></i> <span>CUSTOMERS</span></a></li>
                <li><a href="#"><i class="fa fa-user"></i> <span>STAFF</span></a></li>
                <li><a href="#"><i class="fa fa-building"></i> <span>COMPANY</span></a></li>
                <li><a href="#"><i class="fa fa-bar-chart-o"></i> <span>LPO</span></a></li>
                <li><a href="#"><i class="fa fa-list"></i> <span>REPORTS</span></a></li>
            </ul>
        </div>
    </nav>


    <div id="page-container">
<!-- Header ends Here -->
