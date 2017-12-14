<script>
window.onload = function() {
    if(!window.location.hash) {
        window.location = window.location + '#loaded';
        window.location.reload();
    }
}
</script>
        <div class="clearfix"></div>
             <ol class="breadcrumb">
              <li><a href="#">Dashboard</a></li>
            </ol>
            <div class="clearfix"></div>
        <div class="container">
            <div class="row">
                        <div class="col-md-2 col-xs-12 col-sm-6">
                            <a class="info-tiles tiles-toyo" href="#">
                                <div class="tiles-heading">IDLE CYLINDERS</div>
                                <div class="tiles-body-alt">
                                    <!--i class="fa fa-bar-chart-o"></i-->
                                    <div class="text-center">21854</div>
                                </div>
                                <div class="tiles-footer">more info</div>
                            </a>
                        </div>
                        <div class="col-md-2 col-xs-12 col-sm-6">
                            <a class="info-tiles tiles-success" href="#">
                                <div class="tiles-heading">DELIVERED</div>
                                <div class="tiles-body-alt">
                                    <!--i class="fa fa-money"></i-->
                                    <div class="text-center">32545</div>
                                </div>
                                <div class="tiles-footer">more info</div>
                            </a>
                        </div>
                        <div class="col-md-2 col-xs-12 col-sm-6">
                            <a class="info-tiles tiles-orange" href="#">
                                <div class="tiles-heading">EMPTY CYLINDERS</div>
                                <div class="tiles-body-alt">
                                    <div class="text-center">${Empty }</div>
                                </div>
                                <div class="tiles-footer">more info</div>
                            </a>
                        </div>
                        <div class="col-md-2 col-xs-12 col-sm-6">
                            <a class="info-tiles tiles-alizarin" href="#">
                                <div class="tiles-heading">FILLED CYLINDERS</div>
                                <div class="tiles-body-alt">
                                    <div class="text-center">${Filled }</div>
                                </div>
                                <div class="tiles-footer">more info</div>
                            </a>
                        </div>
                        <div class="col-md-2 col-xs-12 col-sm-6">
                            <a class="info-tiles tiles-warning" href="#">
                                <div class="tiles-heading">MISSID CYLINDERS</div>
                                <div class="tiles-body-alt">
                                    <div class="text-center">457</div>
                                </div>
                                <div class="tiles-footer">more info</div>
                            </a>
                        </div>
                        <div class="col-md-2 col-xs-12 col-sm-6">
                            <a class="info-tiles tiles-info" href="#">
                                <div class="tiles-heading">TOTAL CYLINDERS</div>
                                <div class="tiles-body-alt">
                                    <div class="text-center">${totalCylinderCount }</div>
                                </div>
                                <div class="tiles-footer">more info</div>
                            </a>
                        </div>
                    </div>
                    
            <div class="row">
            <div class="col-md-6 col-lg-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Cylinders Usage</h4>
                        <div class="options">
                        </div>
                    </div>
                    <div class="panel-body">
                      <div id="bar-example"></div>
                    </div>
                </div>
            </div>
            <div class="col-md-6 col-lg-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4>Gas Usage</h4>
                        <div class="options">
                        </div>
                    </div>
                    <div class="panel-body">
                        <div id="line-example"></div>
                    </div>
                </div>
            </div>
            
            
        </div>

        </div> <!-- container -->
    </div> <!-- #wrap -->
</div> <!-- page-content -->
<!-- Body Ends Here -->
<script type="text/javascript">

$(".dashboard").addClass("active"); 
</script>

