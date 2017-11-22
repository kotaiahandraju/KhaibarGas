<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%-- <html>
   <head>
   </head>

   <body>
      <c:redirect url = "/HomePage"/>
      <jsp:forward page="LoginHome"/> 
   </body>
</html> --%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Aarna Matrimony">
<meta name="author" content="Aarna Matrimony">

<title>Aarna Matrimony</title>
<link rel="shortcut icon" href="img/aarna-fav.png"/>

<!-- Bootstrap core CSS -->
<link href="user/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

<script src="user/js/ie-emulation-modes-warning.js"></script>
<link href="user/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="user/vendor/woocommerce-FlexSlider/flexslider.css"
	type="text/css" media="screen" />
<link href="user/css/style.css" rel="stylesheet">
<link href="user/css/custom.css" rel="stylesheet">
 <link href="css/datepicker1.css" rel="stylesheet" type="text/css" />
<!-- <script src="js/jquery-ui.min.js"></script> -->
<link rel="stylesheet" type="text/css" href="user/css/component.css" />
<script src="user/js/modernizr.custom.js"></script>
<style>
.modal-dialog {
	position: relative;
	width: auto;
	max-width: 600px;
	margin: 10px;
}

.modal-sm {
	max-width: 350px;
}

.modal-lg {
	max-width: 900px;
}

@media ( min-width : 768px) {
	.modal-dialog {
		margin: 30px auto;
	}
}

@media ( min-width : 320px) {
	.modal-sm {
		margin-right: auto;
		margin-left: auto;
	}
}

@media ( min-width : 620px) {
	.modal-dialog {
		margin-right: auto;
		margin-left: auto;
	}
	.modal-lg {
		margin-right: 10px;
		margin-left: 10px;
	}
}

@media ( min-width : 920px) {
	.modal-lg {
		margin-right: auto;
		margin-left: auto;
	}
}
.your-class::-webkit-input-placeholder {color: #e73d4a !important;}
.your-class::-moz-placeholder {color: #e73d4a !important;}

.default-class::-webkit-input-placeholder {color: #e73d4a !important;}
.default-class::-moz-placeholder {color: #e73d4a !important;}
span.has-error,span.hasError
{
  font-weight: normal;
  border-color: #e73d4a;
  color:red;
  margin-top: -3px;
  display: block !important;
  position: absolute;
}
.dispnone
{
	display: none !important;
}
.dispblock
{
	display: block !important;
}
</style>
</head>

<body id="page-top">

	<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
					<span class="sr-only">Toggle navigation</span> Menu <i
						class="fa fa-bars"></i>
				</button>

				<!-- Logo Here -->
				<a class="navbar-brand page-scroll" href="#page-top"><img
					src="user/images/logo.jpg" style="height: 70px;width: auto;" alt="" class="img-responsive" /></a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#register-info" data-toggle="modal">Register</a>
					</li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div id="register-info" class="modal modal fade" data-backdrop="static"
		data-keyboard="false">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<h3 class="modal-title">Set up your Account</h3>
					<button data-dismiss="modal"
						class="btn btn-sm btn-default close-btn">&times;</button>
				</div>
				<div class="modal-body">

					<form:form commandName="createProfile" action="#"  id="registration"  method="post" class="login-form">

						<div id="firstForm">

							<div class="form-group">
								<label for="user_name">Enter Your Email-Id :</label> 
								<form:input	path="email" onkeydown="removeBorder(this.id)" class="form-control" placeholder="Enter Email"/>
								<span class="hasError" id="emailError" style="font-size: 13px;"></span>
							</div>
							<div class="form-group">
								<label for="user_password">Create Password :</label> 
								<form:password path="password" class="form-control" onkeydown="removeBorder(this.id)"  placeholder=" Enter Password"/>
							</div>
							<div class="form-group">
								<label for="user_ssword">Create Profile for :</label> 
								<select	id="createProfileFor" name="createProfileFor" onfocus="removeBorder(this.id)" class="form-control">
									<option value="">Select</option>
									<option value="Self">Self</option>
									<option value="Son">Son</option>
									<option value="Daughter">Daughter</option>
									<option value="Brother">Brother</option>
									<option value="Sister">Sister</option>
									<option value="Friend">Friend</option>
									<option value="Relative">Relative</option>
								</select>
							</div>

							<div class="form-group">
								<div>
									<label class="radio-inline" for="radios-0">
									 <input	name="gender" id="radios-0" value="Male" checked="checked" type="radio"> Male
									</label>
									<label class="radio-inline" for="radios-1"> 
									 <input name="gender" id="radios-1" value="Female" type="radio"> Female
									</label>

								</div>
							</div>

							<div class="form-group">
								<button type="submit" id="submit11" class="btn btn-info btn-block">CONTINUE...</button>
							</div>
						</div>
						
						 <!--second step form-->
                                                
                                                <div style="display:none" id="secondForm">
                                                <h4 style="margin-bottom:20px;">Great, Now some basic details </h4>
                                                 <div class="row">
                                                 <div class="form-group">
                                                    <label for="" class="col-md-12" >Your Name</label>
                           <div class="col-md-6"><input type="text" name="firstName" onkeydown="removeBorder(this.id)" class="form-control onlyCharacters" id="firstName" placeholder="First Name"></div>
                           <div class="col-md-6"><input type="text" name="lastName" onkeydown="removeBorder(this.id)" class="form-control onlyCharacters" id="lastName" placeholder="Last Name"></div>
                           <div class="clearfix"></div>
                                                </div>
                                                </div>
                                                
                                               <div class="form-group">
                                               <div  class="row">
                                                    <label for="" class="col-md-12" >Date of Birth</label>
                           <div class="col-md-12"><input type="text" name="dob" onclick="removeBorder(this.id)" class="form-control" id="dob" placeholder="Enter Date of Birth" readonly="readonly"></div>
                                                </div> 
                                                </div>
                                                
                                                
                                                 <div class="form-group">
                                                    <label for="">Religion</label>
                          				<form:select path="religion" onfocus="removeBorder(this.id)"  class="form-control" >
														<form:option value="">-- Choose Religion --</form:option>
														<form:options items="${religion}"></form:options>
										</form:select>
                                                </div> 
                                                
                                                 <div class="form-group">
                                                    <label for="">Mother tongue</label>
                        				 <form:select path="motherTongue" onfocus="removeBorder(this.id)"  class="form-control" >
														<form:option value="">-- Choose Mother Tongue --</form:option>
														<form:options items="${language}"></form:options>
										</form:select>
                                                </div> 
                                                
                                                <div class="form-group">
                                                    <label for="">Where do you live? </label>
                          				 <form:select path="currentCountry" onfocus="removeBorder(this.id)"  class="form-control" >
														<form:option value="">-- Choose Country --</form:option>
														<form:options items="${countries}"></form:options>
										</form:select>
                                                </div> 
                                                 <div class="form-group">
                                                 <input type="button" id ="secondButton" value="SIGNUP..." class="btn btn-info btn-block" />
<!--                                                     <button type="button"  id ="secondButton" onclick="submit();" class="btn btn-info btn-block">SIGNUP...</button> -->
                                                </div>
                                                
                                                </div>
                                                <!--second form end here-->
						
					</form:form>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>




	<!-- Slider -->

	<section class="slider-banner">
		<div class="container-fluid">
			<div class="row">
				<!-- Place somewhere in the <body> of your page -->
				<div class="flexslider">
					<ul class="slides">
						<li><img src="user/images/banner1.jpg" alt="Slider" /></li>
						<li><img src="user/images/banner2.jpg" alt="Slider" /></li>
					</ul>
				</div>
			</div>
		</div>
	</section>

	<section class="darkSection">
		<div class="container">
			<div class="searchbg">
				<form role="form" class="form-inline">
					<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<select class="custom-select col-md-12 form-control">
								<option selected>I am Looking for... &nbsp;&nbsp;</option>
								<option value="Female">Bride</option>
								<option value="Male">Groom</option>
							</select>
						</div>
						<div class="form-group">
							<select class="custom-select col-md-12 form-control">
								<option selected>Age from... &nbsp;&nbsp;</option>
								<option value="1">21</option>
								<option value="2">22</option>
								<option value="3">23</option>
							</select>
						</div>
						<div class="form-group">&nbsp;&nbsp; to &nbsp;&nbsp;</div>
						<div class="form-group">
							<select class="custom-select col-md-12 form-control">
								<option selected>Age upto... &nbsp;&nbsp;</option>
								<option value="1">31</option>
								<option value="2">32</option>
								<option value="3">33</option>
							</select>
						</div>
						<div class="form-group">
							<!-- <select class="custom-select col-md-12 form-control">
								<option selected>of Religion.... &nbsp;&nbsp;</option>
								<option value="1">Hindu</option>
								<option value="2">Muslim</option>
								<option value="3">Christian</option>
							</select> -->
							<form:select path="cast" class="custom-select col-md-12 form-control">
								<form:option value="">of Religion.... &nbsp;&nbsp;</form:option>
								<form:options items="${religion}"></form:options>
							</form:select>
						</div>
						<div class="form-group">
							<!-- <select class="custom-select col-md-12 form-control">
								<option selected>of Caste.... &nbsp;&nbsp;</option>
								<option value="1">Kamma</option>
								<option value="2">Kapu</option>
								<option value="3">Brahmin</option>
							</select> -->
							<form:select path="cast" class="custom-select col-md-12 form-control">
								<form:option value="">of Caste.... &nbsp;&nbsp;</form:option>
								<form:options items="${cast}"></form:options>
							</form:select>
						</div>
						<div class="form-group">
							<button type="button" id="submit12" class="btn btn-danger">Search</button>
						</div>
					</div>
					</div>
				</form>
			</div>
		</div>
	</section>


	<!-- Get a quote Section -->

	<section class="getquote" id="getquote">
		<!-- Section id-->
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-sm-5 col-md-push-8 col-sm-push-7">
					<div class="right-side dispnone">
						<div class="section-title white-section">
							<h1>Login</h1>
							<img src="user/images/line-01.jpg" alt="" />
						</div>
<script>

function validate(id, errorMessage)
{
	var styleBlock = '.placeholder-style.placeholder-style::-moz-placeholder {color: #cc0000;} .placeholder-style::-webkit-input-placeholder {color: #cc0000;}';
	if($('#'+id).val() ==  null || $('#'+id).val() == ""  || $('#'+id).val()=="undefined" ) {
		$('style').append(styleBlock);
		$('#'+id).css('border-color','#cc0000');
		$('#'+id).css('color','#cc0000');
		$('#'+id).attr('placeholder',errorMessage);
		$('#'+id).addClass('placeholder-style your-class');
//			$('#'+id).css('color','#cc0000');
//			$('#'+id+'Error').text(errorMessage);
	}else{
		$('#'+id).css('border-color','');
		$('#'+id).removeClass('placeholder-style your-class');
//			$('#'+id).css('color','');
//			$('#'+id+'Error').text("");
	}
	
}

</script>
						<div class="quote-form row">
							<!-- contact form -->
							<form class="" action="loginAction" id="quote" name="quote" method="post">
							<c:if test="${not empty msg}">
								<div class="msgcss controls fadeOut animated alert alert-danger" style="animation-delay: 5s;">
									${msg}
								</div>
							</c:if>
								<div class="form-group col-md-12">
									<input type="text" class="form-control" onblur="validate('userName','Username')" onkeydown="removeBorder(this.id)" name="userName" id="userName" placeholder="Username" required>
								</div>

								<div class="form-group col-md-12">
									<input type="password" class="form-control" onblur="validate('password','Password')" onkeydown="removeBorder(this.id)" name="password" id="password" placeholder="Password" required>
								</div>

								<div class="form-group col-md-12">
									<button name="submit" type="submit" id="userLoginButton" class="btn btn-default">Login</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>




	<!-- Services Section -->

	<section class="services" id="services">
		<!-- Section id-->
		<div class="container">

			<div class="row">

				<div class="col-md-3 col-sm-6">
					<div class="ser-block">
						<p class="ser-icon">
							<i class="flaticon-consulting-message"></i>
						</p>
						<h4>CREATE ACCOUNT</h4>
						<hr>
						<p class="content">Register for free & put up your Profile.</p>
					</div>
				</div>

				<div class="col-md-3 col-sm-6">
					<div class="ser-block">
						<p class="ser-icon">
							<i class="flaticon-shopping-support-online"></i>
						</p>
						<h4>SEARCH PROFILES</h4>
						<hr>
						<p class="content">Select & Connect with Matches you like.</p>
					</div>
				</div>

				<div class="col-md-3 col-sm-6">
					<div class="ser-block">
						<p class="ser-icon">
							<i class="flaticon-group-meeting"></i>
						</p>
						<h4>SEND INTEREST</h4>
						<hr>
						<p class="content">Become a Premium Member & Start a
							Conversation.</p>
					</div>
				</div>

				<div class="col-md-3 col-sm-6">
					<div class="ser-block">
						<p class="ser-icon">
							<i class="flaticon-people"></i>
						</p>
						<h4>GET MARRIED</h4>
						<hr>
						<p class="content">Become a Premium Member & Start a
							Conversation.</p>
					</div>
				</div>

			</div>
		</div>
	</section>



	<!-- About Section -->

	<section class="about clearfix" id="about">
		<!-- Section id-->
		<div class="container">
			<div class="row">

				<div class="col-md-12 col-sm-12">
					<div class="section-title">
						<h1>SEARCH PROFILES BY</h1>
						<img src="user/images/line-02.jpg" alt="" />
					</div>
					<div class="row">
						<div class="about-content linkss">

							<div class="col-md-3">

								<table class="table table-bordered table-hover table-condensed">
									<tr>
										<td><a href="#">Hindu<span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
									<tr>
										<td><a href="#">Muslim <span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
									<tr>
										<td><a href="#">Christian<span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
									<tr>
										<td><a href="#">Jains<span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
								</table>

							</div>
							<div class="col-md-3">
								<table class="table table-bordered table-hover table-condensed">
									<tr>
										<td><a href="#">Brahmin<span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
									<tr>
										<td><a href="#">Vasya <span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
									<tr>
										<td><a href="#">Kamma<span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
									<tr>
										<td><a href="#">Kaapu<span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
								</table>

							</div>
							<div class="col-md-3">
								<table class="table table-bordered table-hover table-condensed">
									<tr>
										<td><a href="#">Hindu<span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
									<tr>
										<td><a href="#">Muslim <span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
									<tr>
										<td><a href="#">Christian<span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
									<tr>
										<td><a href="#">Jains<span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
								</table>

							</div>
							<div class="col-md-3">
								<table class="table table-bordered table-hover table-condensed">
									<tr>
										<td><a href="#">Hindu<span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
									<tr>
										<td><a href="#">Muslim <span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
									<tr>
										<td><a href="#">Christian<span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
									<tr>
										<td><a href="#">Jains<span><i
													class="fa fa-arrow-right" aria-hidden="true"></i></span></a></td>
									</tr>
								</table>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<!-- Testimonials Section -->

	<section class="testimonials" id="testimonials">
		<!-- Section id-->
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12 col-sm-12">
					<div class="section-title white-section center-text">
						<h1>RECENT SUCCESS STORIES</h1>
						<img src="user/images/line-01.jpg" alt="" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div>
						<!---->
						<!-- Carousel items -->
						<div class="container">
							<ul class="grid cs-style-5">
								<li>
									<figure>
										<img src="user/images/portfolio/01-thumbnail.jpg"
											class="img-responsive img-thumbnail" alt="">
										<figcaption>
											<h3>Bride Name & Groom Name</h3>
											<span>June 9, 2017</span> <a href="#">read more...</a>
										</figcaption>
									</figure>
								</li>
								<li>
									<figure>
										<img src="user/images/portfolio/02-thumbnail.jpg"
											class="img-responsive img-thumbnail" alt="">
										<figcaption>
											<h3>Bride Name & Groom Name</h3>
											<span>June 9, 2017</span> <a href="#">read more...</a>
										</figcaption>
									</figure>
								</li>
								<li>
									<figure>
										<img src="user/images/portfolio/03-thumbnail.jpg"
											class="img-responsive img-thumbnail" alt="">
										<figcaption>
											<h3>Bride Name & Groom Name</h3>
											<span>June 9, 2017</span> <a href="#">read more...</a>
										</figcaption>
									</figure>
								</li>
							</ul>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section>



	<footer class="footer" id="footer">
		<!-- Section id-->
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="logo-footer">
						<img src="user/images/logo-b.png" alt="" class="img-responsive" />
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="contact-info">
						<h5>Our Office</h5>
						<p class="fs-12 address">
							<i class="fa fa-map-marker"></i>#### ###### ######,
						</p>
						<p class="fs-12">
							<i class="fa fa-phone"></i>+91 ##########
						</p>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-6 col-sm-6">
					<div class="social">
						<ul class="list-inline">
							<li><a href="#"><i class="fa fa-facebook"></i></a></li>
							<li><a href="#"><i class="fa fa-twitter"></i></a></li>
							<li><a href="#"><i class="fa fa-instagram"></i></a></li>
							<li><a href="#"><i class="fa fa-pinterest-p"></i></a></li>
						</ul>
					</div>
				</div>
				<div class="col-md-6 col-sm-6">
					<div class="contact-info">
						<p class="email-add">
							<span>Email:</span> info@############.com
						</p>
					</div>
				</div>
			</div>
		</div>
		<div class="copyright clearfix">
			<div class="">
				<p class="copy">Copyright 2017 | All Rights Reserved</p>
			</div>
		</div>
	</footer>




	<script src="user/js/ie10-viewport-bug-workaround.js"></script>
	<script src="user/vendor/jquery/jquery.min.js"></script>
	<script src="user/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="user/vendor/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
	<script src="user/vendor/scrollreveal/scrollreveal.min.js"></script>
	<script src="user/js/theme.js"></script>
	<script src="user/js/custom.js"></script>
	<script src="user/vendor/woocommerce-FlexSlider/jquery.flexslider.js"></script>
	<script src="user/js/toucheffects.js"></script>
<script src="js/custemValidation.js"></script>
<script src="js/ajax.js"></script>
<script src="js/jquery-ui.min.js"></script>
<script type="text/javascript">

var ss =new Date().getFullYear()-16;
$("#dob").datepicker({
    dateFormat: "dd-MM-yy",
    changeDate : true,
	changeMonth : true,
	changeYear : true,
// 	maxDate :0,
	yearRange: '1950:' + ss
});
  



$(function(){
	$(".right-side").removeClass("dispnone");
});

var emailExist = false;
// var validEmail = false;
var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
$('#email').blur(function() {
	var email = $('#email').val();
	if(email == "" || email == null || email == "undefined")
	{
		$("#email").css("border-color","#e73d4a");
    	$("#email").attr("placeholder","Enter Email");
    	$('#email').css('color','red');
	}
	if(email != "" && !email.match(expr)){
// 		alert("Please Enter Valid Email");
    	$("#email").css("border-color","#e73d4a");
    	$("#email").attr("title","Please Enter Email-ID");
    	$('#email').css('color','red');
    	$('#emailError').text("Please Enter Valid Email-ID.");
//     	$('#email').focus();
		return false;
	}
	else{
		$('#emailError').text("");
	}
	if(email !=null && email != "" && email !="undefined"){
	var formData = new FormData();
    formData.append('email', email);
	$.fn.makeMultipartRequest('POST', 'emailChecking', false,
			formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		if(jsonobj.msg =="exist"){
			//error message write
			$('#emailError').text("Email already in Use. Please Try Another.");
			emailExist = true;
		}else{
			$('#emailError').text("");
			emailExist = false;
		}
	});
	}
});
$('#createProfileFor').blur(function() {
	var createProfileFor = $('#createProfileFor').val();
	if(createProfileFor == "" || createProfileFor.length == 0 || createProfileFor =="undefined"){
// 		alert("enter createdby");
    $("#createProfileFor").css("border-color","#e73d4a");
    $('#createProfileFor').css('color','red');
		return false;
	}
});
$('#password').blur(function() {
	var password = $('#password').val();
	if(password == "" || password.length == 0 || password =="undefined"){
// 		alert("enter password");
    $("#password").css("border-color","#e73d4a");
    $('#password').css('color','red');
		return false;
	}
});




$("#submit11").click(function(event){		
	var email = $('#email').val();
	if($('#email').val() ==  null || $('#email').val() == "" || $('#email').val()=="undefined" || 
		$('#createProfileFor').val() ==  null || $('#createProfileFor').val() == "" || $('#createProfileFor').val()=="undefined" ||
		$('#password').val() ==  null || $('#password').val() == "" || $('#password').val()=="undefined")
	{
		if($('#email').val() ==  null || $('#email').val() == "" || $('#email').val()=="undefined") 
		{
			$("#email").css("border-color","#e73d4a");
			$("#email").attr("placeholder","Enter Mail");
			$('#email').addClass('your-class');
			$('#email').css('color','red');
		}
		if($('#createProfileFor').val() ==  null || $('#createProfileFor').val() == "" || $('#createProfileFor').val()=="undefined" ) 
		{
			$("#createProfileFor").css("border-color","#e73d4a");
// 			$("#createProfileFor").attr("placeholder","Please Enter createProfileFor");
			$('#createProfileFor').addClass('your-class');
			$('#createProfileFor').css('color','red');
		}
		if($('#password').val() ==  null || $('#password').val() == "" || $('#password').val()=="undefined" ) 
		{
			$("#password").css("border-color","#e73d4a");
			$("#password").attr("placeholder","Enter Password");
			$('#password').addClass('your-class');
			$('#password').css('color','red');
		}		
		return false;
	}
	if(email != "" && !email.match(expr)){
// 		alert("Please Enter Valid Email");
    	$("#email").css("border-color","#e73d4a");
    	$("#email").attr("title","Please Enter Email-ID");
    	$('#email').css('color','red');
    	$('#email').focus();
		return false;
	}
	if(emailExist){
		alert("exist");
		return false;
	}
 	$("#firstForm").hide();
	$('#secondForm').css({'display':'block'});
// 		$("#registration").submit();
 
	event.preventDefault();
});
$("#secondButton").click(function(event)
// 		function formSubmit()
		{		
			if($('#firstName').val() ==  null || $('#firstName').val() == "" || $('#firstName').val()=="undefined" || 
				$('#lastName').val() ==  null || $('#lastName').val() == "" || $('#lastName').val()=="undefined" ||
				$('#dob').val() ==  null || $('#dob').val() == "" || $('#dob').val()=="undefined"  || 
				$('#religion').val() ==  null || $('#religion').val() == "" || $('#religion').val()=="undefined" ||
				$('#motherTongue').val() ==  null || $('#motherTongue').val() == "" || $('#motherTongue').val()=="undefined" ||
				$('#currentCountry').val() ==  null || $('#currentCountry').val() == "" || $('#currentCountry').val()=="undefined" )
			{
				if($('#firstName').val() ==  null || $('#firstName').val() == "" || $('#firstName').val()=="undefined") 
				{
					$("#firstName").css("border-color","#e73d4a");
					$("#firstName").attr("placeholder","Please Enter FirstName");
					$('#firstName').addClass('your-class');
					$('#firstName').css('color','red');
				}
				if($('#lastName').val() ==  null || $('#lastName').val() == "" || $('#lastName').val()=="undefined" ) 
				{
					$("#lastName").css("border-color","#e73d4a");
					$("#lastName").attr("placeholder","Please Enter Last Name");
					$('#lastName').addClass('your-class');
					$('#lastName').css('color','red');
				}
				if($('#dob').val() ==  null || $('#dob').val() == "" || $('#dob').val()=="undefined" ) 
				{
					$("#dob").css("border-color","#e73d4a");
					$("#dob").attr("placeholder","Please Enter Password");
					$('#dob').addClass('your-class');
					$('#dob').css('color','red');
				}
				if($('#religion').val() ==  null || $('#religion').val() == "" || $('#religion').val()=="undefined" ) 
				{
					$("#religion").css("border-color","#e73d4a");
					$("#religion").attr("placeholder","Please Enter Password");
					$('#religion').addClass('your-class');
					$('#religion').css('color','red');
				}
				if($('#motherTongue').val() ==  null || $('#motherTongue').val() == "" || $('#motherTongue').val()=="undefined" ) 
				{
					$("#motherTongue").css("border-color","#e73d4a");
					$("#motherTongue").attr("placeholder","Please Enter Password");
					$('#motherTongue').addClass('your-class');
					$('#motherTongue').css('color','red');
				}
				if($('#currentCountry').val() ==  null || $('#currentCountry').val() == "" || $('#currentCountry').val()=="undefined" ) 
				{
					$("#currentCountry").css("border-color","#e73d4a");
					$("#currentCountry").attr("placeholder","Please Enter Password");
					$('#currentCountry').addClass('your-class');
					$('#currentCountry').css('color','red');
				}
				return false;
			}
			
// 		 	$("#firstForm").css({'display':'none'})
// 			$('#secondForm').css({'display':'block'});
//		 		$("#registration").submit();
		 $('#registration').attr('action',"userRegistration");
			$("#registration").submit();											
			event.preventDefault();	
		}
		);


		function removeBorder(el){	
			  $("#"+el).css("border", ""); 	
			  $("#"+el).css('color','black');
			  $('#'+el).addClass('default-class');
		}

</script>
</body>
</html>
