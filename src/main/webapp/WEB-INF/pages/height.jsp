<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<style>
 .error {
        color: red; font-weight: bold;
    }
</style>
<div id="main">
<div class="container-fluid">
	<div class="page-header">
		<div class="pull-left">
			<h1>Height</h1> 
		</div>
	</div>
	<div class="breadcrumbs">
		<ul>
			<li>
				<i class="fa fa-edit"></i>
				<a href="javascript:void(0)" style="color: blue;text-decoration: none;">Data List 1</a>
				<i class="fa fa-angle-right"></i>&nbsp;
			</li>
			<li>
				&nbsp;<i class="fa fa-file"></i>
				<span style="color: #999;cursor: auto;">Height</span>
			</li>
		</ul>
		
	</div>
	<div class="col-lg-8">
		<div>
			<div class="portlet" id="yw0">
				<div class="portlet-content w3-animate-zoom">
					<!-- Body Type Form Starts Here-->
					<form:form modelAttribute="heightForm" class="form-horizontal" role="form" id="height-form" action="addHeight" method="post">								
						<div class="form-group">
							<div class="col-sm-12">
								<div class="errorMessage" id="Height_invalid_em_" >
									<c:if test="${not empty msg}">
										<div class="alert alert-success fadeIn animated">${msg}</div>
									</c:if>
								</div>
								<form:hidden path="id"/>
							</div>
					  	</div>
						<div class="form-group">
							<label class="col-sm-4 control-label required"><spring:message code="label.inchesName" text="default text" /> <span style="color:red;">*</span></label>
							<div class="col-sm-8">
						  		<form:input path="inches" type="text" class="form-control validate" autocomplete="off" placeholder="Enter Inches" maxlength="255"/>						
						  		<span class="hasError" id="inchesError"></span>
						  		<div><form:errors path="inches" cssClass="error" /></div>										
							</div>
					  	</div>
					  	<div class="form-group">
							<label class="col-sm-4 control-label required"><spring:message code="label.cmName" text="default text" /> <span style="color:red;">*</span></label>
							<div class="col-sm-8">
						  		<form:input path="cm" type="text" class="form-control validate"  autocomplete="off" placeholder="Enter Cm" maxlength="255"/>						
						  		<span class="hasError" id="cmError"></span>
						  		<div><form:errors path="cm" cssClass="error" /></div>										
							</div>
					  	</div>
				  		<div class="form-group">
							<div class="col-sm-8 col-sm-offset-4"><input class="btn btn btn-primary" type="submit"  id="submit1" name="yt0" value="Add"></div>
					  	</div>
					</form:form>
					<!-- Body Type Form Ends Here-->
				</div>
			</div>
		</div>
	</div>


<div class="row">
	<div class="col-sm-12">
		<div class="box">
			<div class="box-title">
				<h3>
					<i class="fa fa-table"></i>
					Height's List
				</h3>
			</div>
			<div class="box-content nopadding w3-animate-zoom" id="tableId">
				<table class="table table-hover table-nomargin table-bordered dataTable dataTable-column_filter" data-column_filter_types="text,text,null">
					<thead>
					<tr>
						<th>Inches</th><th>Cm</th>
						<th></th>
					</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</div>
</div>
</div>
</div>

		
<script type="text/javascript">
function validate(id){
	if($('#inches').val() ==  null || $('#inches').val() == ""  || $('#inches').val()=="undefined" ) {
		$('#inchesError').css('color','red');
	    $("#inchesError").text("Height cannot be blank.");
	}else{
		$("#inchesError").text("");
	}
	}
function validate1(id){
	if($('#cm').val() ==  null || $('#cm').val() == ""  || $('#cm').val()=="undefined" ) {
		$('#cmError').css('color','red');
	    $("#cmError").text("Cm cannot be blank.");
	}else{
		$("#cmError").text("");
	}
	}
	





$("#submit11").click(function()
		{			
			if($('#inches').val() ==  null || $('#inches').val() == ""  || $('#inches').val()=="undefined")
			{
				if($('#inches').val() ==  null || $('#inches').val() == ""  || $('#inches').val()=="undefined" ) 
				{
// 				    $("#name").css("border-color","#e73d4a");
// 				    $("#name").attr("placeholder","Please Enter Body Type");
// 				    $('#name').addClass('your-class');
				    $('#inchesError').css('color','red');
				    $("#inchesError").text("Height  cannot be blank.");
			    }
				if($('#cm').val() ==  null || $('#cm').val() == ""  || $('#cm').val()=="undefined" ) 
				{
// 				    $("#name").css("border-color","#e73d4a");
// 				    $("#name").attr("placeholder","Please Enter Body Type");
// 				    $('#name').addClass('your-class');
				    $('#cmError').css('color','red');
				    $("#cmError").text("Cm  cannot be blank.");
			    }
				return false;
				 $("#height-form").submit();
			}
			});



/* var _gaq = _gaq || [];
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
 */
 var listOrders1 = ${allOrders1};
	if (listOrders1 != "") {
		displayTable(listOrders1);
	}
 function displayTable(listOrders) {
		$('#tableId').html('');
		var tableHead = '<table class="table table-hover table-nomargin table-bordered dataTable dataTable-column_filter" data-column_filter_types="text,text,null">'
				+ '<thead><tr><th>Inches</th><th>Cm</th><th style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
		$('#tableId').html(tableHead);
		serviceUnitArray = {};
		$.each(listOrders,function(i, orderObj) {
							var edit = "<a onclick='editHeight("	+ orderObj.id+ ")'><i style='color: green;' class='fa fa-edit'></i></a>"
							var deleterow = "<a onclick='deleteHeight("+ orderObj.id+ ")'><i style='color: red;' class='fa fa-trash'></i></a>"
							serviceUnitArray[orderObj.id] = orderObj;
							var tblRow = "<tr >"
								+ "<td title='"+orderObj.inches+"'>" + orderObj.inches + "</td>"
								+ "<td title='"+orderObj.cm+"'>" + orderObj.cm + "</td>"									
								+ "<td style='text-align: center;'>" + edit + "&nbsp;|&nbsp;" + deleterow + "</td>" 
								+ "</tr >";
							$(tblRow).appendTo("#tableId table tbody");
						});
		/* $('#DataTables_Table_0').DataTable({
			dom: 'Bfrtip',
			buttons: [{extend:"print",className:"btn default"},{extend:"pdf",className:"btn default"},{extend:"csv",className:"btn default"}]
		}); */
// 		$('.dataTable11').dataTable();
// 		$.fn.dataTable.sErrMode = 'console';
// 		$('#DataTables_Table_0').dataTable();
		 /*$('#datatable-buttons').DataTable({
		        "dom": 'C<"clear">lfrtip',
		        "colVis": {
		            "buttonText": "Change columns",
		        "buttons": [{extend:"copy",className:"btn default"},{extend:"print",className:"btn default"},{extend:"pdf",className:"btn default"},{extend:"csv",className:"btn default"}]
		        }
		    });*/
	}
 function editHeight(id) {
		$("#id").val(serviceUnitArray[id].id);
		$("#inches").val(serviceUnitArray[id].inches);
		$("#cm").val(serviceUnitArray[id].cm);
//		$(window).scrollTop($('#addForm').offset().top);
		}
 function deleteHeight(id){
		var checkstr =  confirm('Are you sure you want to delete this?');
		if(checkstr == true){
		var formData = new FormData();
	     formData.append('id', id);
		$.fn.makeMultipartRequest('POST', 'deleteHeight', false,
				formData, false, 'text', function(data){
			var jsonobj = $.parseJSON(data);
			alert(jsonobj.message);
			var alldata = jsonobj.allOrders1;
			console.log(jsonobj.allOrders1);
			displayTable(alldata);
		});
		}
		
	}
 $(".catalog1").addClass("active");
 $(".height").addClass("active"); 
</script>
</body>


<!-- Mirrored from www.eakroko.de/flat/forms-basic.html by HTTrack Website Copier/3.x [XR&CO'2014], Mon, 18 Sep 2017 09:43:06 GMT -->
</html>

