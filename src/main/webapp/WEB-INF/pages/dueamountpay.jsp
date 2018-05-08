<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css" />
<link rel="stylesheet" href='${baseurl }/assets/css/cylinderdeliverPrint1.css' />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.4/jspdf.min.js"></script>

 
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script> -->
<!-- <script type="text/javascript" src="https://code.jquery.com/ui/1.12.0-beta.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.1.135/jspdf.min.js"></script>
<script type="text/javascript" src="http://cdn.uriit.ru/jsPDF/libs/adler32cs.js/adler32cs.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2014-11-29/FileSaver.min.js"></script>
<script type="text/javascript" src="libs/Blob.js/BlobBuilder.js"></script>
<script type="text/javascript" src="http://cdn.immex1.com/js/jspdf/plugins/jspdf.plugin.addimage.js"></script>
<script type="text/javascript" src="http://cdn.immex1.com/js/jspdf/plugins/jspdf.plugin.standard_fonts_metrics.js"></script>
<script type="text/javascript" src="http://cdn.immex1.com/js/jspdf/plugins/jspdf.plugin.split_text_to_size.js"></script>
<script type="text/javascript" src="http://cdn.immex1.com/js/jspdf/plugins/jspdf.plugin.from_html.js"></script> -->
<!-- <script type="text/javascript" src="js/basic.js"></script> -->

<style>



table #dependent_table {
	/* 	width: 100%; */
	counter-reset: rowNumber;
}
table tbody tr.rowInc {
	counter-increment: rowNumber;
}
 table#dependent_table tbody tr td:first-child::before {
 content: counter(rowNumber);
/* 	min-width: 1em; */
/* 	margin-right: 0.5em; */
}
.addItemButton {
	cursor: pointer;
	font-size: small;
	background: green;
	color: white;
	padding: 3px 10px 3px 10px;
}
#ui-datepicker-div {
/* 	width: auto !important; */
}

</style>
<ol class="breadcrumb">
  <li><a href="#">Home</a></li>
  <li>Due Amount Pay</li>
</ol>
<div class="clearfix"></div>
<div class="container" id="cylinderdataId">
  <form:form modelAttribute="lpoForm" id="cylinderDeliverForm" action="savedueamount" class="form-horizontal" method="post">
    <table width="100%">
      <tr> 
        <td><div class="row">    
            <div class="col-md-6">
              <div class="panel panel-primary">
                <div class="panel-heading">
                  <h4>Customer Details</h4>
                </div>
                <form:hidden path="netAmount"/>
                <div class="panel-body">
                  <div class="row">
                    <div class="col-md-6">
                      
                      <div class="form-group">
                        <label for="focusedinput" class="col-md-6 control-label">Customer Type <span class="impColor">*</span></label>
                        <div class="col-md-6">
                          <form:select path="customertype" class="form-control   validate"  onfocus="removeBorder(this.id);" onchange="getCustomerIds(this.value)">
                            <form:option value="">-- Customer Type --</form:option>
                            <form:option value="COMMERCIAL">COMMERCIAL</form:option>
                            <form:option value="DOMESTIC">DOMESTIC</form:option>
                            <form:option value="INDUSTIAL">INDUSTIAL</form:option>
                             <form:option value="PRIVATE">PRIVATE</form:option>
                          </form:select>
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="focusedinput" class="col-md-6 control-label">Customer Id <span class="impColor">*</span></label>
                        <div class="col-md-6">
                          <form:select path="customerId" class="form-control  validate" onfocus="removeBorder(this.id);" onchange="getCustomerDetails(this.value)">
                            <form:option value="">-- Select Customer Id --</form:option>
                          </form:select>
                        </div>
                      </div>
                     
                      <div class="form-group">
                        <label for="focusedinput" class="col-md-6 control-label">Previous Due Amount :</label>
                        
                        <div class="col-md-6" style="margin-top:8px;">
                        <span  id="previousDueAmount"></span>
                        </div>
                      </div>
                      
                       <div class="form-group">
                        <label for="focusedinput" class="col-md-6 control-label">Paid Amount</label>
                        
                        <div class="col-md-6" style="margin-top:8px;">
                        <input type="text" id="paidAmount" class="validate" name="payedAmount" onkeyup="PaidCalculation(this.value),removeBorder(this.id)">
                        </div>
                      </div>
                      <div class="form-group">
                        <label for="focusedinput" class="col-md-6 control-label">Due Amount</label>
                        
                        <div class="col-md-6" style="margin-top:8px;">
                        <input type="text" id="dueAmount" name="dueAmount" readonly="true" >
                        </div>
                      </div>
                    
                    </div>
<!--                     vat amount -->
				<input type="hidden" name="vatamount" id="vatamount1" >
				<input type="hidden" name="grossamount" id="grossamount">  
				<input type="hidden" name ="totalNetamount" id ="totalNetamount"/>
				 <input type="hidden" id="prviousInvoiceId" name="prviousInvoiceId">
                    <div class="col-md-6">
                      <div class="form-group" style="margin-bottom:0;">
                        <label for="focusedinput" class="col-md-6 control-label" style="padding-top:4px;">Customer Name: </label>
                        <div class="col-md-6"> <span id="customername" class="form-control" style="border:none;"></span> </div>
                      </div>
                      <div class="form-group" style="margin-bottom:0;">
                        <label for="focusedinput" class="col-md-6 control-label" style="padding-top:4px;">Customer Address: </label>
                        <div class="col-md-6"> <span id="customeraddress" class="form-control"  style="border:none;"></span> </div>
                      </div>
                      <div class="form-group" style="margin-bottom:0;">
                        <label for="focusedinput" class="col-md-6 control-label" style="padding-top:4px;">Mobile: </label>
                        <div class="col-md-6"> <span id="mobile" class="form-control"  style="border:none;"></span> </div>
                      </div>
                      <div class="form-group" style="margin-bottom:0;">
                        <label for="focusedinput" class="col-md-6 control-label" style="padding-top:4px;">Land Line: </label>
                        <div class="col-md-6"> <span id="landline" class="form-control"  style="border:none;"></span> </div>
                      </div>
                      <div class="col-md-12">
                      <div class="form-group">
<!--                         <label for="focusedinput" class="col-md-5 control-label">previous Due Amount (AED)</label> -->
<!--                         <div class="col-md-5" style="padding-top:5px; color:#C00;"> -->
<!-- <!--                         <span id="lastDueAmount" style="padding-top:5px; color:#C00"><strong>0</strong></span> -->
<!--                         </div> -->
                      </div>
                    <br />

                    </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
        <div class="col-md-6">
              <div class="panel panel-primary">
                <div class="panel-heading">
                  <h4>Previous Invoice Data</h4>
                </div>
                <div class="panel-body"style="min-height:267px;">
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
<!--                         <label for="focusedinput" class="col-md-5 control-label" style="margin-top:0; padding-top:0; vertical-align:text-top;">Cylinders </label> -->
<!--                         <div class="col-md-7"> <span id="cylinders"></span> </div> -->
                      </div>
                    </div>
                   <div class="col-md-6">
                      <div class="form-group">
                        <label for="focusedinput" class="col-md-6 control-label" style="padding-top:4px;">Items :</label>
                         <div class="col-md-6"> <span id="previouesitems" class="form-control"  style="border:none;"></span> </div>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label for="focusedinput" class="col-md-6 control-label" style="padding-top:4px;">Invoice ID :</label>
                         <div class="col-md-6"> <span id="previouesInvoiceId" class="form-control"  style="border:none;"></span> </div>
                      </div>
                    </div>
                     <div class="col-md-6">
                      <div class="form-group">
                        <label for="focusedinput" class="col-md-6 control-label" style="padding-top:4px;">Total Amount :</label>
                         <div class="col-md-6"> <span id="previouesgrossamount" class="form-control"  style="border:none;"></span> </div>
                      </div>
                    </div>  
                     <div class="col-md-6">
                      <div class="form-group">
                        <label for="focusedinput" class="col-md-6 control-label" style="padding-top:4px;">Paid Amount  :</label>
                         <div class="col-md-6"> <span id="previouespaidamount" class="form-control"  style="border:none;"></span> </div>
                      </div>
                    </div>
                     <div class="col-md-6">
                      <div class="form-group">
                        <label for="focusedinput" class="col-md-6 control-label" style="padding-top:4px;">Due Amount :</label>
                         <div class="col-md-6"> <span id="previouesdueamount" class="form-control"  style="border:none;"></span> </div>
                      </div>
                    </div>
                    
                    

                    <br />
<!--                     <div id="previousDueAmount"></div> -->
                  
                    
                    <div  style="display:none" >
                      <form:select path="ownercompany" class="form-control chzn-select " onfocus="removeBorder(this.id);" >
                        <form:option value="">--Select company--</form:option>
                        <form:options items="${companys}"></form:options>
                      </form:select>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="panel-footer hideme">
              <div class="row">
                <div class="col-sm-12">
                  <div class="btn-toolbar pull-right">
                    <input class="btn-primary btn" type="submit" id="submit11" value="Submit">
                    <input class="btn-danger btn cancel" type="reset" id="clearData" value="Reset" placeholder="">
                  </div>
                </div>
              </div>
            </div>
        <div class="row"> 
                    <div class="col-md-12">
                    
               <div class="panel panel-primary">
                    
            </td>
      </tr>
    </table>
  </form:form>
</div>
<!-- container -->



<!-- print table -->


<div class="container" id="printCylinder" style="display:none; font-size: 20px !important;">
<div class="col-md-12 printTable">
 <div class="col-md-12 noPrint" style="padding: 5px;border-bottom: 2px solid;border-top: 2px solid;"  >
 <div class="col-md-4"><button class="printbtn btn-primary" onclick="PrintElem('#printCylinder');">Print</button></div><div class="col-md-4"></div>
 <div class="col-md-4" style="float:right;text-align: right;"><a style="cursor: pointer;float: right;color: red;" onclick="getBack()">
 <i class="fa fa-2x fa-close"></i></a></div></div>
<!-- <button class="printbtn btn-primary" onclick="PrintElem('#printCylinder');">Print</button> -->
<div class="col-md-6 col-xs-6">
<img height="70" src="../img/khaibarlogo.png"/></div>
<div class="col-md-6 col-xs-6" style="float:right; right:50px; text-align:right; ">

<!-- <h3 style="margin-bottom:0px;">Khaibar Gas Bottling & Distribution LLC</h3>  -->
<p style="font-size:15px; margin-top:10px;">Ajman  - U.A.E, 
 Al Jurf Industrial Area 1
 Tel. : (+971) 6 7448668
 TRN : 100027344900003</p>
</div><div class="clearfix"></div>
<div class="col-md-12">
<h3 style="text-align:center;margin-bottom:0px;">TAX INVOICE # <span class="taxinvoice"></span></h3><hr style="margin:2px;"><p class="printdateId"></p>
</div><div class="clearfix"></div>
<div class="clearfix"></div>
 <table class="table-responsive " >
    <tr><td class="det"><div class="custom">Customer Details</div></td></tr><tbody style="padding:10px;
			border:1px solid lightgray;">
    <tr><td ><label for="focusedinput "  >Customer Name :</label></td><td><span class="customerNameId"></span></td></tr>
                          
              <tr><td ><label for="focusedinput "  >Customer Type :</label></td><td><span class="customerTypeId"></span></td></tr>
              <tr><td ><label for="focusedinput "  >Customer Address :</label></td><td><span class="customerAddress"></span></td></tr>
              <tr><td ><label for="focusedinput mobile"  >Mobile :</label></td><td><span class="mobile"></span></td></tr> 
               <tr><td ><label for="focusedinput "  >Previous Due Amount :</label></td><td><span class="previousdueamount"> </span></td></tr>
                <tr><td ><label for="focusedinput "  >Paid Amount :</label></td><td><span class="paidamount"> </span></td></tr>         
                <tr><td ><label for="focusedinput "  >Due Amount :</label></td><td><span class="Dueamount"> </span></td></tr>                  
                          
                          </tbody>
    
    </table>
     <table class="table-responsive">
    <tr><td class="det"><div class="custom">Previous Invoice Data</div></td></tr><tbody style="padding:10px;
			border:1px solid lightgray;">
<!--      <tr><td ><label for="focusedinput"  >Cylinders :: </label></td><td><span class="retunCylinders"></span></td></tr> -->
              <tr><td ><label for="focusedinput"  >Items :</label></td><td><span class="previousitems"></span></td></tr>
              <tr><td ><label for="focusedinput "  >Total  Amount :</label></td><td><span class="previouesamount"> </span></td></tr>
              <tr><td ><label for="focusedinput "  >Due Amount :</label></td><td><span class="previouesdueamount1"> </span></td></tr>    
              <tr><td ><label for="focusedinput "  >Invoice ID :</label></td><td><span class="previouesInvoiceId"> </span></td></tr>     
              <tr><td ><label for="focusedinput "  >Paid Amount :</label></td><td><span class="previouespaidamount"> </span></td></tr>
               <tr height="26px"><td><label for="focusedinput"  > </label></td> </tr>
                <tr height="26px"><td><label for="focusedinput"  > </label></td> </tr>    
 </tbody>   </table>
    </div>
    <div class="clearfix"></div>
    <br>
    <div class="col-md-12 printTable">
<div class="col-md-6 col-xs-6">
<img height="70" src="../img/khaibarlogo.png" /></div>
<div class="col-md-6 col-xs-6" style="float:right; right:50px; text-align:right; ">
<!--  <h3 style="margin-bottom:0px;">Khaibar Gas Bottling & Distribution LLC</h3>  -->
<p style="font-size:15px;">Ajman  - U.A.E, 
 Al Jurf Industrial Area 1
 Tel. : (+971) 6 7448668
 TRN : 100027344900003</p>
</div><div class="clearfix"></div>
<div class="col-md-12">
<h3 style="text-align:center;margin-bottom:0px;">TAX INVOICE # <span class="taxinvoice"></span></h3><hr style="margin:2px;"><p class="printdateId"></p>
</div><div class="clearfix"></div>
<div class="clearfix"></div>
 <table class="table-responsive " >
    <tr><td class="det"><div class="custom">Customer Details</div></td></tr><tbody style="padding:10px;
			border:1px solid lightgray;">
    <tr><td ><label for="focusedinput "  >Customer Name :</label></td><td><span class="customerNameId"></span></td></tr>
                          
              <tr><td ><label for="focusedinput "  >Customer Type :</label></td><td><span class="customerTypeId"></span></td></tr>
              <tr><td ><label for="focusedinput "  >Customer Address :</label></td><td><span class="customerAddress"></span></td></tr>
              <tr><td ><label for="focusedinput mobile"  >Mobile :</label></td><td><span class="mobile"></span></td></tr> 
               <tr><td ><label for="focusedinput "  >Previous Due Amount :</label></td><td><span class="previousdueamount"> </span></td></tr>
                <tr><td ><label for="focusedinput "  >Paid Amount :</label></td><td><span class="paidamount"> </span></td></tr>         
                <tr><td ><label for="focusedinput "  >Due Amount :</label></td><td><span class="Dueamount"> </span></td></tr>                  
                          
                          </tbody>
    
    </table>
     <table class="table-responsive">
    <tr><td class="det"><div class="custom">Previous Invoice Data</div></td></tr><tbody style="padding:10px;
			border:1px solid lightgray;">
<!--      <tr><td ><label for="focusedinput"  >Cylinders :: </label></td><td><span class="retunCylinders"></span></td></tr> -->
              <tr><td ><label for="focusedinput"  >Items :</label></td><td><span class="previousitems"></span></td></tr>
              <tr><td ><label for="focusedinput "  >Total  Amount :</label></td><td><span class="previouesamount"> </span></td></tr>
              <tr><td ><label for="focusedinput "  >Due Amount :</label></td><td><span class="previouesdueamount1"> </span></td></tr>    
              <tr><td ><label for="focusedinput "  >Invoice ID :</label></td><td><span class="previouesInvoiceId"> </span></td></tr>     
              <tr><td ><label for="focusedinput "  >Paid Amount :</label></td><td><span class="previouespaidamount"> </span></td></tr>
               <tr height="26px"><td><label for="focusedinput"  > </label></td> </tr>
                <tr height="26px"><td><label for="focusedinput"  > </label></td> </tr>    
 </tbody>   </table>
    </div>
  <div id="editor"></div>
</div>


<c:choose>
<c:when test="${empty param.invoiceId}">
   <script> var invoiceId = "";</script>
</c:when>
<c:otherwise>
   <script> var invoiceId = "${param.invoiceId}";</script>
</c:otherwise>
</c:choose>


<c:choose>
<c:when test="${empty param.previousInvoice}">
   <script> var previousInvoice = "";</script>
</c:when>
<c:otherwise>
   <script> var previousInvoice = "${param.previousInvoice}";</script>
</c:otherwise>
</c:choose>



<!-- <script type="text/javascript" src="js/jquery-2.1.3.min.js"></script> -->
<script type="text/javascript">



var damageId = 0;
// var serviceUnitArray ={};
// var serviceUnitArray1 ={};
var data = {};




function dataClear(){
	
	
	$("#id").val('');
	$("#lponumber").val('');
	$("#item").val('');
	$("#remarks").val('');
	$("#amount").val('');
	$("#suppliername").val("");
	$("#suplieraddress").val("");
	$("#supliercontactno").val("");
	$("#amount").val("");
}





var dependentRowCount = 1;
var validates = true;
var rowvalidate = false;

function addMoreRowsForDependent() {
	rowvalidate =false;
	var rowid =$('#dependent_table tbody tr:last').attr('id');
	console.log(rowid);
	    var number = parseInt(rowid.match(/[0-9]+/)[0], 10);
	    var item = $('#' + number + 'item').val();
		var qty = $('#'+number+'unit').val();
		var rate =  $('#' + number + 'rate').val();
	    
		 if(item == "" || item == null || item == "undefined" )
		{
			if(item == "" || item == null || item == "undefined")
			{
				$('#'+number+'item').css('color','red');
				$('#'+number+'item').css("border-color","#e73d4a");
				$('#'+number+'item').attr("placeholder","Enter Product Description");
				$('#'+number+'item').addClass('your-class');
// 				$('#'+number+'item').focus();
				return false;
			}
			
		} 
	dependentRowCount++;
	var dependentRow1 = '<tr class="rowInc" role="row" id="'+dependentRowCount+'">'
			+ '<td class="labelCss"></td>'
			+ '<td class="inputCss"><select title="Select Item" name="item1" style="width: 100%;font-size: small;" id="'
			+ dependentRowCount
			+ 'item" class="form-control validate" onchange="removeBorder(this.id),getTarrifPrice(this.value,this.id),getTruckInCylinderCount(this.id,this.value)"><option>Select</option></select></td>'
			+ '<td class="inputCss"><input title="Unit" name="unit" id="'
			+ dependentRowCount
			+ 'unit" type="text" value="1" class="form-control numericOnly" onkeyup="allcalculate(this.id)" onkeydown="removeBorder(this.id);" onblur="getTruckInCylinderCount(this.id.this.value)"/></td>'
			+ '<td class="inputCss"><input name="rate" id="'
			+ dependentRowCount
			+ 'rate" type="text" value="0.0" class="form-control numericOnly" onkeydown="removeBorder(this.id);" onkeyup="allcalculate(this.id)" readonly="readonly"/></td>'
// 			+ '<td class="inputCss"><select title="Select Rate" name="rate" style="width: 100%;font-size: small;" id="'
// 			+ dependentRowCount
// 			+ 'rate" class="form-control" onchange="removeBorder(this.id);"></select></td>'
			+ '<td class="labelCss"><input title="Total Value" name="totalvalue" id="'
			+ dependentRowCount
			+ 'totalvalue" value="0.00" type="text"  class="form-control" onkeydown="removeBorder(this.id);" readonly="readonly"/></td>'
			+ '<td class="inputCss"><input title="Discount" name="discount" id="'
			+ dependentRowCount
			+ 'discount" value="0" type="text" class="form-control numericOnly" onkeydown="removeBorder(this.id);" onkeyup="discountCheck(this.id,this.value)" /></td>'
			+ '<td class="labelCss"><input title="Taxable Value" name="taxable" id="'
			+ dependentRowCount
			+ 'taxable" value="0.00" type="text" class="form-control numericOnly" onkeydown="removeBorder(this.id);" readonly="readonly"/></td>'
// 			+ '<td class="labelCss" ><input placeholder="Manufacturing Date" name="manufacturingdate" id="'
// 			+ dependentRowCount
// 			+ 'manufacturingdate"  type="text" class="form-control numericOnly" onkeydown="removeBorder(this.id);" readonly="readonly"/></td>'
// 			+ '<td class="labelCss" ><input placeholder="Expiry Date" name="expirydate" id="'
// 			+ dependentRowCount
// 			+ 'expirydate"  type="text" class="form-control numericOnly" onkeydown="removeBorder(this.id);" readonly="readonly"/></td>'
			+ "<th class='labelCss notPrintMe hideme' style='width: 10px;'><span><a href='javascript:void(0);' style='color: red;' onclick='removeDependentRow("
			+ dependentRowCount + ");'><i class='fa fa-trash' style='color: red;text-decoration: none;cursor: pointer;'></i></a></span></th>" +
			 + "</tr>";
	$(dependentRow1).appendTo("#dependent_table tbody");
	$("#"+dependentRowCount+"manufacturingdate").datepicker({
		dateFormat : "dd-MM-yy",
		changeDate : true,
		changeMonth : true,
		changeYear : true,
	});
	$("#"+dependentRowCount+"expirydate").datepicker({
		dateFormat : "dd-MM-yy",
		changeDate : true,
		changeMonth : true,
		changeYear : true,
	});
	var dummyItem1 = $('#item1').html();
	$('#'+dependentRowCount+'item').empty();
	$(dummyItem1).appendTo('#'+dependentRowCount+'item');
	
}

function removeDependentRow(dependentRowCount) {
	jQuery('#' + dependentRowCount).remove();
	priceCalculator();
	
	
}
function allcalculate(id){
	     var unit=0.00;
	     var rate=0.00;
	     var total1 =0.00;
	     var discount=0.00;

	
	var number = parseInt(id.match(/[0-9]+/)[0], 10);
	unit = $('#'+ number+'unit').val();
	rate = $('#' + number + 'rate').val();
	total1 =  unit * rate;

	taxable = $('#' + number + 'taxable').val();
	
	$('#' + number + 'totalvalue').val(total1.toFixed(2));
	
	total = $('#' + number + 'totalvalue').val();
	discount = $('#' + number + 'discount').val();
	if(discount =="" ){
		discount =0;
	}
	var result = parseFloat(total)*(100- parseFloat(discount))/100;
	$('#' + number + 'taxable').val(result.toFixed(2));
	
	
	
	priceCalculator();
}
var grandTotal = 0.00;
var finalAmount = 0.00;
var lastDueAmount1 = 0.00;
function priceCalculator(){
	 var globelTotalValue = 0.00;
		var globalDiscount = 0.00;
		var globalTaxable = 0.00;
		var vatAmount =0.00;
		
		
		var vat = $("#vat").val();
	var array = $.makeArray($('tbody tr[id]').map(function() {
		  return this.id;
		}));
		console.log(array);
	 for(var i=0;i<array.length;i++){
		 var discount = 0.00;
			var total = 0.00;
			var taxable = 0.00;
			
			
			
		total = $('#' + array[i] + 'totalvalue').val();
		globelTotalValue = globelTotalValue + parseFloat(total);
		
		 discount = $('#' + array[i] + 'discount').val();
		 if(discount == ""){
			 discount = 0.00;
		 }
		globalDiscount = globalDiscount + parseFloat(discount);
		
		taxable = $('#' + array[i] + 'taxable').val();
		globalTaxable = globalTaxable+parseFloat(taxable);
		
		
		
		$(".totalInvoiceValue").text(globelTotalValue.toFixed(2));
		$(".totalDiscounts").text(globalDiscount.toFixed(2));
		$(".totalTaxableValue").text(globalTaxable.toFixed(2));
		
		
	 }
	 var lstdue = $("#lastDueAmount").text();
	 
	 grandTotal = globalTaxable;
	 vatAmount = parseFloat((grandTotal)*(vat/100));
	 finalAmount = parseFloat((grandTotal))*(100+parseFloat(vat))/100;
	if(lstdue !="" && lstdue != null && lstdue != "undefined" ){
		finalAmount = parseFloat(finalAmount)+parseFloat(lstdue);
	 }	
	$("#netAmount").val(finalAmount);
	 $("#netAmount1").text(grandTotal);
	 $("#totalNetamount").val(grandTotal);
	 $("#vatAmount").text(Math.round(vatAmount));
	 $("#vatamount1").val(Math.round(vatAmount));
	 $("#grandTotal").text(Math.round(finalAmount));
	 $("#grossamount").val(Math.round(finalAmount));
// 	 var paidamount =$("#paidamount").val();
// 	 if(paidamount.trim().length == 0){
// 		 $("#dueamount").val(grandTotal);
// 	 }else{
// 		 $("#dueamount").val(parseInt(grandTotal)-parseInt(paidamount));
// 	 }
// 	 alert(grandTotal);
// 	 $(".grandTotal").text(grandTotal.toFixed(2));
// 	 $(".roundOff").text(Math.round(grandTotal).toFixed(2));
}

function viewDetails(id,value){
	id = id.replace("edit", "");
$("#dependent_table tbody").find("tr:gt(0)").remove();
dependentRowCount = 1;
	var LpoId = serviceUnitArray1[id].id;
	editLpo(LpoId);
	$('#dial1').html('');
	var formData = new FormData();
    formData.append('lponumber', id);
	$.fn.makeMultipartRequest('POST', 'viewLPOdetails', false,
			formData, false, 'text', function(data){
		var lponumbertitle=null;
		var jsonobj = $.parseJSON(data);
		var alldata = jsonobj.allOrders1;
		
			var j=1;
		$.each(alldata,	function(i, orderObj) {
			if(j == 1){
				
				$("#1item").val(orderObj.itemid);
				$("#1unit").val(orderObj.quantity);
				$("#1rate").val(orderObj.price);
				$("#1totalvalue").val(orderObj.totalprice); 
				$("#1discount").val(orderObj.discount);
				$("#1taxable").val(orderObj.grandtotal);
				$("#1manufacturingdate").val(orderObj.manufacturingdate);
				$("#1expirydate").val(orderObj.expirydate);
				if(value=="0"){
					$("#id").val(0);
					$(".hideme").hide();
				}else{
					$(".hideme").show();
				}
			}else{
				addMoreRowsForDependent();
				$("#"+j+"item").val(orderObj.itemid);
				$("#"+j+"unit").val(orderObj.quantity);

				$("#"+j+"rate").val(orderObj.price);
				$("#"+j+"totalvalue").val(orderObj.totalprice); 
				$("#"+j+"discount").val(orderObj.discount);
				$("#"+j+"taxable").val(orderObj.grandtotal);
				$("#"+j+"manufacturingdate").val(orderObj.manufacturingdate);
				$("#"+j+"expirydate").val(orderObj.expirydate);
				if(value=="0"){
					$(".hideme").hide();
				}else{
					$(".hideme").show();
				}
			}
			j++;
			
		});
		priceCalculator();
	});
}

function PaidCalculation(value){
	var amount = $("#previousDueAmount").text();
	if(amount !="" && value !=""){
		$("#dueAmount").val(parseInt(amount)-parseInt(value));
	}
}
function getCustomerIds(value){
	$("#customername").text(" ");
	$("#customeraddress").text(" ");
	$("#mobile").text(" ");
	$("#landline").text(" ");
	$("#lastDueAmount").text(" ");
	$("#previousDueAmount").text(" ");
	
	$("#previouesInvoiceId").text(" ");
	$("#previouesgrossamount").text(" ");
	$("#previouespaidamount").text(" ");
	$("#previouesdueamount").text(" ");
	$("#previouesitems").text(" ");
	$("#prviousInvoiceId").val(" ");
	
	
	var formData = new FormData();
    formData.append('customertype', value);
	$.fn.makeMultipartRequest('POST', 'getCustomerIds', false,
			formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		var alldata = jsonobj.allOrders1;
		var alldata1 = jsonobj.allOrders2;
		
		var html = "<option value=''>-- Select Customer Id --</option>";
		$.each(alldata,function(i, catObj) {
			
			 html = html + '<option value="'
				+ catObj.id + '">'
				+ catObj.customerid + '</option>';
		});
		$('#customerId').empty().append(html);
// 		$("#customerId").trigger("chosen:updated");
		
		
		
	});
}
function getCustomerDetails(value){
	var formData = new FormData();
    formData.append('customerid', value);
	$.fn.makeMultipartRequest('POST', 'getCustomerInvoiceData', false,
			formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		var alldata = jsonobj.allOrders1;
		var alldata1 = jsonobj.allOrders2;
		console.log(alldata1);
		$("#cylinders").text("");
		$.each(alldata,function(i, catObj) {
			$("#customername").text(catObj.customername);
			$("#mobile").text(catObj.mobile);
			$("#customeraddress").text(catObj.customeraddress);
			$("#landline").text(catObj.landline);
			$("#lastDueAmount").text(catObj.dueAmount);
			$("#previousDueAmount").text(catObj.dueAmount);
			
			$("#dueAmount").val(catObj.dueAmount);
			priceCalculator();
			
		
	});
		$.each(alldata1,function(i, catObj) {
			$("#previouesInvoiceId").text(catObj.invoiceid);
			$("#previouesgrossamount").text(catObj.grossamount);
			$("#previouespaidamount").text(catObj.paidamount);
			$("#previouesdueamount").text(catObj.dueamount);
			$("#previouesitems").text(catObj.itemName);
			$("#prviousInvoiceId").val(catObj.invoiceid);
			
		});
		    
	
});
}

var map = new Object(); // or var map = {};
function getTarrifPrice(value,id){
	var truckId = $("#cylinderDeliverTruck").val();
	if(truckId==""){
		alert("Please Select Truck");
		$("#"+id).val("");
		return false;
	}
	var number = parseInt(id.match(/[0-9]+/)[0], 10);
	
	var formData = new FormData();
    formData.append('itemId', value);
	$.fn.makeMultipartRequest('POST', 'getTariffPrice', false,
			formData, false, 'text', function(data){
		var jsonobj = $.parseJSON(data);
		var alldata = jsonobj.rate;
		map[number+"discount"] = jsonobj.discount;
		$("#"+number+"rate").val(jsonobj.rate);
		allcalculate(number+"rate");
	});
}

function discountCheck(id,value){
// 	alert(map[id]);
	if(value>map[id]){
		alert("Please Enter Discount Maximum : "+map[id] );
		$("#"+id).val("0");
		allcalculate(id);
		return false;
	}
	allcalculate(id);
}
var cylinderAvailable=true;
function getTruckInCylinderCount(id,value){
	
	
	var truckId = $("#cylinderDeliverTruck").val();
	if(truckId==""){
		$("#"+id).val("");
		return false;
	}
	
	var array = $.makeArray($('tbody tr[id]').map(function() {
		  return this.id;
		}));
		var item ="";
		var unit="";
		var myarray = []; 
		var myarray1 = []; 
	 for(var i=0;i<array.length;i++){
		 item = $('#' + array[i] + 'item').val();
		 unit = $('#' + array[i] + 'unit').val();
		 myarray.push(item);
		 myarray1.push(unit);
	 }
	 var existingItems = {};

	 myarray.forEach(function(value, index) {
	     existingItems[value] = index;
	 });
	 
	 var formData = new FormData();
	    formData.append('myarray', myarray);
	    formData.append('myarray1', myarray1);
	    formData.append('truckId', truckId);
		$.fn.makeMultipartRequest('POST', 'getTruckInCylinderCount', false,
				formData, false, 'text', function(data){
			var jsonobj = $.parseJSON(data);
			var msg = jsonobj.msg;
		if(msg!="ok"){
			alert(msg);
			cylinderAvailable = false;
		}else{
			cylinderAvailable = true;
		}
		});
	 
}

function selectReturnTruck(value){
	$("#cylinderReturnTruck").val(value);
}
$('#submit11').click(function(event) {
	validation = true;
	$.each(idArray, function(i, val) {
		var value = $("#" + idArray[i]).val();
		var placeholder = $("#" + idArray[i]).attr('placeholder');
		if (value == null || value == "" || value == "undefined") {
			$('style').append(styleBlock);
			$("#" + idArray[i] ).attr("placeholder", placeholder);
			$("#" + idArray[i] ).css('border-color','#e73d4a');
			$("#" + idArray[i] ).css('color','#e73d4a');
			$("#" + idArray[i] ).addClass('placeholder-style your-class');
			 var id11 = $("#" + idArray[i]+"_chosen").length;
			if ($("#" + idArray[i]+"_chosen").length)
			{
				$("#" + idArray[i]+"_chosen").children('a').css('border-color','#e73d4a');
			}
//			$("#" + idArray[i] + "Error").text("Please " + placeholder);
			validation = false;
		} 
		
	});
	
	if(validation) {
		$("#submit11").attr("disabled",true);
		$("#submit11").val("Please wait...");
		$("form").submit();											
		event.preventDefault();
	}else {
		return false;
		event.preventDefault();
	}
	
});

function PrintElem(elem)
{
	$(".noPrint").hide();
	$(".printbtn").hide();
	 $("#cylinderdataId").hide();
	 $("#printCylinder").show();
	 
	 var options = {
			 pagesplit: false,
			 useOverflow:true,
			 background:"#fff",
			 lineheight:3,
// 			line-height:"3",
			paddingtop:100,
// 			padding:10,
			 width: 1360
			 };
	 
	  var pdf = new jsPDF('p', 'pt', 'a4');
	 var margins = {
			   top: 25,
			   bottom: 60,
			   left: 20,
			   background:"#fff",
			   width: 522
			};
			// all coords and widths are in jsPDF instance's declared units
			// 'inches' in this case
			/*pdf.addHTML($(elem), margins.top, margins.left, {}, function() {
			    pdf.save('test.pdf');
			});  */
			
			 console.log(options);
	   var pdf = new jsPDF('p', 'pt', 'a4');
	  pdf.addHTML($(elem), 50, 150, options, function() {
// 		   pdf.setFontSize(40);
	    pdf.save('pageContent.pdf');
	  });  
	 
		 /*  $('#cmd').click(function() {
		    doc.fromHTML($('#target').html(), 15, 15, {
		      'width': 170,
		      'elementHandlers': specialElementHandlers
		    });
		    doc.save('sample-file.pdf');
		  });
		}); */
     
    Popup($(elem).html());
    
    
    
    
}

	 
$(function() {
	  var doc = new jsPDF();
	  var specialElementHandlers = {
	    '#editor': function(element, renderer) {
	      return true;
	    }
	  };
	  $('.printbtn').click(function() {
		  $("#printCylinder").css('display', 'block');
		  console.log($('#printCylinder').html());
	    doc.fromHTML('<div class="col-md-12 printTable"><button class="printbtn btn-primary" >Print</button><div class="clearfix"></div> <table class="table-responsive "> <tbody><tr><td class="det"><div class="custom">Customer Details</div></td></tr></tbody><tbody style="padding:10px;   border:1px solid lightgray;"> <tr><td><label for="focusedinput ">Customer Name :</label></td><td><span class="customerNameId"></span></td></tr> <tr><td><label for="focusedinput ">Customer Type :</label></td><td><span class="customerTypeId"></span></td></tr> <tr><td><label for="focusedinput ">Customer Address :</label></td><td><span class="customerAddress"></span></td></tr> <tr><td><label for="focusedinput mobile">Mobile :</label></td><td><span class="mobile"></span></td></tr> </tbody> </table>', 15, 15, {
	      'width': 170,
	      'elementHandlers': specialElementHandlers
	    });
	    doc.save('sample-file.pdf');
	  });
	});
	 










function Popup(data)
{
	var mywindow = window.open('','new div');

    var is_chrome = Boolean(mywindow.chrome);
    var isPrinting = false;
    mywindow.document.write('<html><head><title>Customer Details</title><link rel="stylesheet" href="../assets/css/cylinderdeliverPrint.css" /> <link rel="stylesheet" type="text/css" href="../assets/css/bootstrap.min.css"><style>body{font-size:normal;}</style></head><body>');
    mywindow.document.write(data);
   
    mywindow.document.write('</body></html>');
    mywindow.document.close(); // necessary for IE >= 10 and necessary before onload for chrome

$(".printbtn").show();
$(".noPrint").show();
$("#printFooter").hide();
$("#printCylinder").show();
    if (is_chrome) {
        mywindow.onload = function() { // wait until all resources loaded 
            mywindow.focus(); // necessary for IE >= 10
            mywindow.print();  // change window to mywindow
            mywindow.close();// change window to mywindow
        };
    
    
   } else {
        mywindow.document.close(); // necessary for IE >= 10
        mywindow.focus(); // necessary for IE >= 10

        mywindow.print();
        mywindow.close();
   }
	
	
	
   /* var mywindow = window.open('', 'new div');
    mywindow.document.write('<html><head><title>Donor Details</title></head><body>');
    mywindow.document.write(data);
    mywindow.document.write('</body></html>');
    mywindow.print();
    mywindow.close();
    $(".printbtn").show();*/
    return true;
}

if(invoiceId != "" && previousInvoice != ""){
	invoicePrint(invoiceId,previousInvoice);
}
function invoicePrint(invoiceId,previousInvoice){
	//alert(this.id);
	 $("#cylinderdataId").hide();
	 $("#printCylinder").show();
	 

	var formData = new FormData();
    formData.append('invoiceId', invoiceId);
    formData.append('previousInvoice', previousInvoice);
	$.fn.makeMultipartRequest('POST', 'getDueamountInvoiceData', false,
			formData, false, 'text', function(data){
		console.log(data);
		var lponumbertitle=null;
		var jsonobj = $.parseJSON(data);
		var alldata = jsonobj.allOrders1;
		var alldata1 = jsonobj.allOrders2;
// 		alert(alldata);
		var table=$('.deliverCylinders tbody').html('');
		$(".deliverCylinders tbody").html("");

		$.each(alldata,function(i, orderObj) {
		
// 			customerNameId customerTypeId customerAddress mobile paidamount previousdueamount1 Dueamount  previouesgrossamount  previouesdueamount previouesInvoiceId previouespaidamount
			$(".customerNameId").text(orderObj.customername);
			$(".customerTypeId").text(orderObj.customertype);
			$(".customerAddress").text(orderObj.customeraddress);
			$(".mobile").text(orderObj.mobile);
				$(".Dueamount").text(orderObj.dueAmount);
				$(".paidamount").text(orderObj.paidAmount);
				$(".taxinvoice").text(orderObj.invoiceId);
				
			
			
	});
		$.each(alldata1,function(i, catObj) {
			$(".previouesamount").text(catObj.grossamount);
			$(".previouespaidamount").text(catObj.paidAmount);
			$(".previouesdueamount1").text(catObj.dueAmount);
			$(".previousitems").text(catObj.itemName);
			$(".previouesInvoiceId").text(catObj.invoiceId);
			$(".previousdueamount").text(catObj.dueAmount)
		});
		
	});
	
}
function getBack(){
	$("#cylinderdataId").show();
	 $("#printCylinder").hide();
	ChangeUrl('lpoNum', 'dueamount?invoiceId=');

}
function ChangeUrl(page, url) {
    if (typeof (history.pushState) != "undefined") {
        var obj = { Page: page, Url: url };
        history.pushState(obj, obj.Page, obj.Url);
    } else {
        alert("Browser does not support HTML5.");
    }
}


$("#pageName").text("Due Amount Pay");
$(".dueamount").addClass("active");
</script>
