<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Spring pagination using data tables</title>
<link rel="stylesheet" type="text/css" href="http://cdn.datatables.net/1.10.0/css/jquery.dataTables.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="http://cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>
<script type="text/javascript">
$(document).ready(function(){
var table = $('#example').dataTable( {


//     "order": [[ 1, "asc" ]],

    "aoColumnDefs": [
        { 'bSortable': false, 'aTargets': [ 1 ]},
        { 'bSearchable': true }
    ],
    "Processing": true,
    "ServerSide": true,
    "sAjaxSource": 'serversidepaginationtest',
    "bJQueryUI": true,
    "sPaginationType": "full_numbers",
    "iDisplayStart ":20,
        

    "columns": [
            { "data": "cylinderid" },
            { "data": "storename" },
            { "data": null,
            "defaultContent": '<a href="id" class="editor_edit">Edit</a> / <a href="id" class="editor_remove">Delete</a>'
            }
       
    ],  

    'fnServerData': function(sSource, aoData, fnCallback)
    {
        $.ajax
        ({
            'dataType': 'json',
            'type'    : 'POST',
            'url'     : sSource,
            'data'    : aoData,
            'success' : fnCallback
        });
    }
} );
});
</script>
</head>
<body>
<h2 >Spring MVC pagination using data tables<br><br></h2>
<table width="70%" ><tr><td>
	<table id="example" >
        <thead>
            <tr>
                <th>Name</th>
     			<th>Position</th>
            </tr>
        </thead>       
    </table>
    </td></tr></table>
</body>
</html>