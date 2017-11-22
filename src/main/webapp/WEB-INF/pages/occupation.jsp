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
			<h1>Occupation</h1> 
		</div>
	</div>
	<div class="breadcrumbs">
		<ul>
			<li>
				<i class="fa fa-edit"></i>
				<a href="javascript:void(0)" style="color: blue;text-decoration: none;">Data List 2</a>
				<i class="fa fa-angle-right"></i>&nbsp;
			</li>
			<li>
				&nbsp;<i class="fa fa-file"></i>
				<span style="color: #999;cursor: auto;">Occupation</span>
			</li>
		</ul>
	</div>
	<div class="col-lg-8">
		<div>
			<div class="portlet" id="yw0">
				<div class="portlet-content w3-animate-zoom">
					<!-- Body Type Form Starts Here-->
					<form:form modelAttribute="occupationForm" class="form-horizontal" role="form" id="occupation-form" action="addOccupation" method="post">								
						<div class="form-group">
							<div class="col-sm-12">
								<c:if test="${not empty msg}">
									<div class="alert alert-success fadeIn animated">${msg}</div>
								</c:if>
								<div class="errorMessage" id="Occupation_invalid_em_" style="display:none"></div>
							</div>
					  	</div>
						<div class="form-group">
							<label class="col-sm-4 control-label required"><spring:message code="label.occupation" text="default text" /><span style="color:red;">*</span></label>
							<div class="col-sm-8">
								<form:hidden path="id"/>
						  		<form:input path="name" type="text" class="form-control onlyCharacters validate"  autocomplete="off" placeholder="Enter Occupation" maxlength="255"/>						
						  		<span class="hasError" id="nameError"></span>
						  		<div><form:errors path="name" cssClass="error" /></div>										
							</div>
					  	</div>
				  		<div class="form-group">
							<div class="col-sm-9 col-sm-offset-4"><input class="btn btn btn-primary" type="submit" id="submit1" name="yt0" value="Add"></div>
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
					Occupation's List
				</h3>
			</div>
			<div class="box-content nopadding w3-animate-zoom" id="tableId">
				<table class="table table-hover table-nomargin table-bordered dataTable dataTable-column_filter" data-column_filter_types="text,null">
					<thead>
					<tr>
						<th>Occupation</th>
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
	if($('#name').val() ==  null || $('#name').val() == ""  || $('#name').val()=="undefined" ) {
		$('#nameError').css('color','red');
	    $("#nameError").text("Occupation  cannot be blank.");
	}else{
		$("#nameError").text("");
	}
	}
	





$("#submit11").click(function()
		{			
			if($('#name').val() ==  null || $('#name').val() == ""  || $('#name').val()=="undefined")
			{
				if($('#name').val() ==  null || $('#name').val() == ""  || $('#name').val()=="undefined" ) 
				{
// 				    $("#name").css("border-color","#e73d4a");
// 				    $("#name").attr("placeholder","Please Enter Body Type");
// 				    $('#name').addClass('your-class');
				    $('#nameError').css('color','red');
				    $("#nameError").text("Occupation cannot be blank.");
			    }
				return false;
				 $("#occupation-form").submit();
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
		var tableHead = '<table  class="table table-hover table-nomargin table-bordered dataTable dataTable-column_filter" data-column_filter_types="text,null">'
				+ '<thead><tr><th>Occupation</th><th style="text-align: center;"></th></tr></thead><tbody></tbody></table>';
		$('#tableId').html(tableHead);
		serviceUnitArray = {};
		$.each(listOrders,function(i, orderObj) {
						var edit = "<a  onclick='editOccupation("	+ orderObj.id+ ")'><i style='color: green;' class='fa fa-edit'></i></a>"
						var deleterow = "<a  onclick='deleteOccupation("+ orderObj.id+ ")'><i style='color: red;' class='fa fa-trash'></i></a>"
						serviceUnitArray[orderObj.id] = orderObj;
						var tblRow = "<tr >"
								+ "<td title='"+orderObj.name+"'>"+ orderObj.name + "</td>"
								+ "<td style='text-align: center;'>" + edit + "&nbsp;|&nbsp;" + deleterow + "</td>" 
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
 function editOccupation(id) {
		$("#id").val(serviceUnitArray[id].id);
		$("#name").val(serviceUnitArray[id].name);
//		$(window).scrollTop($('#addForm').offset().top);
		}
 function deleteOccupation(id){
		var checkstr =  confirm('Are you sure you want to delete this?');
		if(checkstr == true){
		var formData = new FormData();
	     formData.append('id', id);
		$.fn.makeMultipartRequest('POST', 'deleteOccupation', false,
				formData, false, 'text', function(data){
			var jsonobj = $.parseJSON(data);
			alert(jsonobj.message);
			var alldata = jsonobj.allOrders1;
			console.log(jsonobj.allOrders1);
			displayTable(alldata);
		});
		}
		
	}
 $(".catalog2").addClass("active");
 $(".occupation").addClass("active"); 
</script>
</body>

</html>

