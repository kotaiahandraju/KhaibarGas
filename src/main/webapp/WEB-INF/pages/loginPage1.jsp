<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<!-- Mirrored from www.eakroko.de/flat/more-login.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 18 Sep 2017 09:51:24 GMT -->
<head>
<style>
 .error {
        color: red; font-weight: bold;
    }
</style>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<!-- Apple devices fullscreen -->
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<!-- Apple devices fullscreen -->
	<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />

	<title>Matrimony - Login</title>
	<link rel="shortcut icon" href="img/aarna-fav.png"/>

	<!-- Bootstrap -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<!-- icheck -->
	<link rel="stylesheet" href="css/plugins/icheck/all.css">
	<!-- Theme CSS -->
	<link rel="stylesheet" href="css/style.css">
	<!-- Color CSS -->
	<link rel="stylesheet" href="css/themes.css">


	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>

	<!-- Nice Scroll -->
	

	<!--[if lte IE 9]>
		<script src="js/plugins/placeholder/jquery.placeholder.min.js"></script>
		<script>
			$(document).ready(function() {
				$('input, textarea').placeholder();
			});
		</script>
	<![endif]-->


	<!-- Favicon -->
<!-- 	<link rel="shortcut icon" href="img/favicon.html" /> -->
	<!-- Apple devices Homescreen icon -->
	<link rel="apple-touch-icon-precomposed" href="img/apple-touch-icon-precomposed.png" />
<style>
span.has-error,span.hasError
{
  font-weight:normal;
  border-color: #e73d4a;
  color:red;
  margin-top: -3px;
  display: block !important;
  position: absolute;
}
</style>
</head>

<body class='login'>
	<div class="wrapper">
		<h1>
			<a href="#">
				<img src="img/aarna-fav.png" alt="" class='retina-ready' width="59" height="49">AARNA</a>
		</h1>
		<div class="login-body">
			<h2>SIGN IN</h2>
								<c:if test="${not empty msg}">
									<div class="alert alert-success fadeIn animated">${msg}</div>
								</c:if>
			<form:form commandName="loginForm" action="loginAction" method='post' class='form-validate' id="loginform">
				<div class="form-group">
					<div class="email controls">
						<form:input path="userName" placeholder="User Name" autocomplete="off" onblur="validate('userName','Please Enter Username..!');" class='form-control'/>
						<span class="hasError" id="userNameError"></span>
						<div><form:errors path="userName" cssClass="error" /></div>	
						<div></div>
					</div>
				</div>
				<div class="form-group">
					<div class="pw controls">
					<form:password path="password" placeholder="Password" autocomplete="off" onblur="validate('password','Password cannot be blank..!');" class='form-control'/>
					<span class="hasError" id="passwordError"></span>
					<div><form:errors path="password" cssClass="error" /></div>	
					</div>
				</div>
				<div class="submit">
					<!-- <div class="remember">
						<input type="checkbox" name="remember" class='icheck-me' data-skin="square" data-color="blue" id="remember">
						<label for="remember">Remember me</label>
					</div> -->
					<input type="submit" value="Sign in"  id="submit1"  class='btn btn-primary'>
				</div>
			</form:form>
			<div class="forget">
				<a href="#">
					<span>Forgot password?</span>
				</a>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function validate(id, errorMessage)
	{
		if($('#'+id).val() ==  null || $('#'+id).val() == ""  || $('#'+id).val()=="undefined" ) {
			$('#'+id+'Error').text(errorMessage);
		}else{
			$('#'+id+'Error').text("");
		}
	}
	
	/* function validate1(id){
		if($('#userName').val() ==  null || $('#userName').val() == ""  || $('#userName').val()=="undefined" ) {
			$('#userNameError').css('color','red');
		    $("#userNameError").text("UserName  cannot be blank.");
		}else{
			$("#userNameError").text("");
		}
		}
	function validate2(id){
		if($('#password').val() ==  null || $('#password').val() == ""  || $('#password').val()=="undefined" ) {
			$('#passwordError').css('color','red');
		    $("#passwordError").text("Password cannot be blank.");
		}else{
			$("#passwordError").text("");
		}
		} */
	
	
	$("#submit1").click(function()
			{			
				if($('#userName').val() ==  null || $('#userName').val() == ""  || $('#userName').val()=="undefined")
				{
					if($('#userName').val() ==  null || $('#userName').val() == ""  || $('#userName').val()=="undefined" ) 
					{
					    $('#userNameError').css('color','red');
					    $("#userNameError").text("UserName cannot be blank.");
				    }
					if($('#password').val() ==  null || $('#password').val() == ""  || $('#password').val()=="undefined" ) 
					{
					    $('#passwordError').css('color','red');
					    $("#passwordError").text("Password cannot be blank.");
				    }
					return false;
					 $("#loginform").submit();
				}
				});
	
	
	
	
	
	
	
	
	
	var _gaq = _gaq || [];
	_gaq.push(['_setAccount', 'UA-38620714-4']);
	_gaq.push(['_trackPageview']);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
	</script>
</body>


<!-- Mirrored from www.eakroko.de/flat/more-login.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 18 Sep 2017 09:51:36 GMT -->
</html>
