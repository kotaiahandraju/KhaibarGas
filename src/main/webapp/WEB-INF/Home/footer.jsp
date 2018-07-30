
<%
	String baseurl =  request.getScheme() + "://" + request.getServerName() +      ":" +   request.getServerPort() +  request.getContextPath();
	session.setAttribute("baseurl", baseurl);
%>

<!-- Footer Starts Here -->
		</div> <!-- #wrap -->
	</div> <!-- page-content --> <div class="clearfix"></div>
    <footer role="contentinfo">
        <div class="clearfix">
            <ul class="list-unstyled list-inline pull-left">
                <li>CHARVIKENT.COM</li>
            </ul>
            <button class="pull-right btn btn-inverse-alt btn-xs hidden-print" id="back-to-top"><i class="fa fa-arrow-up"></i></button>
        </div>
    </footer>
    <aside class="control-sidebar control-sidebar-dark">
    <!-- Create the tabs -->
    <ul class="nav nav-tabs nav-justified control-sidebar-tabs">
<!--       <li class="nav-item"><a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a></li> -->
<!--       <li class="nav-item"><a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-cog fa-spin"></i></a></li> -->
    </ul>
    <!-- Tab panes -->
    <div class="tab-content">
      <!-- Home tab content -->
      <div class="tab-pane" id="control-sidebar-home-tab">
        <h3 class="control-sidebar-heading">Recent Activity</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-birthday-cake bg-red"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Admin Birthday</h4>

                <p>Will be July 24th</p>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-user bg-yellow"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Jhone Updated His Profile</h4>

                <p>New Email : jhone_doe@demo.com</p>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-envelope-o bg-light-blue"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Disha Joined Mailing List</h4>

                <p>disha@demo.com</p>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <i class="menu-icon fa fa-file-code-o bg-green"></i>

              <div class="menu-info">
                <h4 class="control-sidebar-subheading">Code Change</h4>

                <p>Execution time 15 Days</p>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

        <h3 class="control-sidebar-heading">Tasks Progress</h3>
        <ul class="control-sidebar-menu">
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Web Design
                <span class="label label-danger pull-right">40%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-danger" style="width: 40%"></div>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Update Data
                <span class="label label-success pull-right">75%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-success" style="width: 75%"></div>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Order Process
                <span class="label label-warning pull-right">89%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-warning" style="width: 89%"></div>
              </div>
            </a>
          </li>
          <li>
            <a href="javascript:void(0)">
              <h4 class="control-sidebar-subheading">
                Development 
                <span class="label label-primary pull-right">72%</span>
              </h4>

              <div class="progress progress-xxs">
                <div class="progress-bar progress-bar-primary" style="width: 72%"></div>
              </div>
            </a>
          </li>
        </ul>
        <!-- /.control-sidebar-menu -->

      </div>
      <!-- /.tab-pane -->
      <!-- Stats tab content -->
      <div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
      <!-- /.tab-pane -->
      <!-- Settings tab content -->
      <div class="tab-pane" id="control-sidebar-settings-tab">

        <div  method="post">
          <h3 class="control-sidebar-heading">General Settings</h3>

          <div class="form-group">
            <input type="checkbox" id="report_panel" class="chk-col-grey" >
			<label for="report_panel" class="control-sidebar-subheading ">Report panel usage</label>

            <p>
              general settings information
            </p>
          </div>
          <!-- /.form-group -->

          <div class="form-group">
            <input type="checkbox" id="allow_mail" class="chk-col-grey" >
			<label for="allow_mail" class="control-sidebar-subheading ">Mail redirect</label>

            <p>
              Other sets of options are available
            </p>
          </div>
          <!-- /.form-group -->

          <div class="form-group">
            <input type="checkbox" id="expose_author" class="chk-col-grey" >
			<label for="expose_author" class="control-sidebar-subheading ">Expose author name</label>

            <p>
              Allow the user to show his name in blog posts
            </p>
          </div>
          <!-- /.form-group -->

          <h3 class="control-sidebar-heading">Chat Settings</h3>

          <div class="form-group">
            <input type="checkbox" id="show_me_online" class="chk-col-grey" >
			<label for="show_me_online" class="control-sidebar-subheading ">Show me as online</label>
          </div>
          <!-- /.form-group -->

          <div class="form-group">
            <input type="checkbox" id="off_notifications" class="chk-col-grey" >
			<label for="off_notifications" class="control-sidebar-subheading ">Turn off notifications</label>
          </div>
          <!-- /.form-group -->

          <div class="form-group">
            <label class="control-sidebar-subheading">              
              <a href="javascript:void(0)" class="text-red margin-r-5"><i class="fa fa-trash-o"></i></a>
              Delete chat history
            </label>
          </div>
          <!-- /.form-group -->
        </div>
      </div>
      <!-- /.tab-pane -->
    </div>
  </aside>
  <!-- /.control-sidebar -->
  
  <!-- Add the sidebar's background. This div must be placed immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div> <!-- page-container -->

 <!-- jQuery 3 -->
<!-- 	<script src="../assets/vendor_components/jquery/dist/jquery.js"></script> -->
	
	<!-- jQuery UI 1.11.4 -->
<!-- 	<script src="../assets/vendor_components/jquery-ui/jquery-ui.js"></script> -->
	
	<!-- Resolve conflict in jQuery UI tooltip with Bootstrap tooltip -->
	<script>
// 	  $.widget.bridge('uibutton', $.ui.button);
	</script>

	<script src="${baseurl }/newjs/popper.min.js"></script>
	<script src="${baseurl }/newjs/bootstrap.js"></script>	
	<script src="${baseurl }/newjs/raphael.min.js"></script>
	<script src="${baseurl }/newjs/morris.min.js"></script>
    <script src="${baseurl }/newjs/d3.v3.js" type="text/javascript"></script>
	<script src="${baseurl }/newjs/stream_layers.js" type="text/javascript"></script>
    <script src="${baseurl }/newjs/nv.d3.min.js" type="text/javascript"></script>
    <script src="${baseurl }/newjs/utils.js" type="text/javascript"></script>
<%-- 	<script src="${baseurl }/newjs/chartist.min.js"></script> --%>
	<script src="${baseurl }/newjs/jquery.sparkline.js"></script>
	<script src="${baseurl }/newjs/jquery.slimscroll.js"></script>
	<script src="${baseurl }/newjs/fastclick.js"></script>
	<script src="${baseurl }/newjs/template.js"></script>
	<script src="${baseurl }/newjs/horizontal-layout.js"></script>
<%-- 	<script src="${baseurl }/newjs/dashboard.js"></script> --%>
	<script src="${baseurl }/newjs/demo.js"></script>
 
 
 
 
<script type='text/javascript' src='${baseurl }/assets/js/jqueryui-1.10.3.min.js'></script> 

<script type='text/javascript' src='${baseurl }/js/MonthPicker.min.js'></script> 

<script type='text/javascript' src='${baseurl }/assets/js/bootstrap.min.js'></script>  
<script type='text/javascript' src='${baseurl }/assets/js/enquire.js'></script>  
<script type='text/javascript' src='${baseurl }/assets/js/jquery.cookie.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/js/jquery.nicescroll.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/codeprettifier/prettify.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/easypiechart/jquery.easypiechart.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/sparklines/jquery.sparklines.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/form-toggle/toggle.min.js'></script> 
<script type='text/javascript' src='${baseurl }/js/jquery.dataTables.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/datatables/dataTables.bootstrap.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/demo/demo-datatables.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/js/placeholdr.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/js/application.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/demo/demo.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/charts-morrisjs/raphael.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/plugins/charts-morrisjs/morris.min.js'></script> 
<script type='text/javascript' src='${baseurl }/assets/demo/demo-morrisjs.js'></script> 




<script type="text/javascript" src="${baseurl }/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="${baseurl }/js/jszip.min.js"></script>
<script type="text/javascript" src="${baseurl }/js/pdfmake.min.js"></script>
<script type="text/javascript" src="${baseurl }/js/vfs_fonts.js"></script>
<script type="text/javascript" src="${baseurl }/js/buttons.print.min.js"></script>
<script type="text/javascript" src="${baseurl }/js/buttons.html5.min.js"></script>



<script type='text/javascript' src='${baseurl }/js/customValidation.js'></script> 

<script type='text/javascript' src="${baseurl }/js/select2.min.js" ></script>


	
<script type="text/javascript">
var isClick = 'Yes';
$.fn.dataTableExt.sErrMode = 'console';
$(".main-sidebar").css('display','block');
// $('#example').dataTable();

</script>
</body>
</html>