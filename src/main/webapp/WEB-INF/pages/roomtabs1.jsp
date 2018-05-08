<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="col" style="border:solid 1px; border-color:#CCCCCC; border-radius:5px;">
				<ul style="background-color:#F7F7F7; margin-top:5px; border:solid 1px; border-color:#CCCCCC; border-radius:5px;" id="myTab" class="nav nav-tabs" role="tablist">
					<li style="margin-left:3px; margin-top:5px;"  class="roomTypeHome"><a href="roomTypeHome" >Rooms Type</a></li>
					<li style="margin-top:5px;" role="presentation" class="roomcapacity"><a href="roomcapacity">Room Capacity</a></li>
					<li style="margin-top:5px;" role="presentation" class="occupation"><a href="occupation" >Room Occupation</a></li>
					<li style="margin-left:3px; margin-top:5px;" class="roomMasterHome"><a href="roomMasterHome" >Room Master</a></li>
					<li style="margin-top:5px;" role="presentation" class="roomPriceHome"><a href="roomPriceHome" >Room Tariff</a></li>
					<li style="margin-top:5px;" role="presentation" class="roomPhotosHome"><a href="roomPhotosHome" >Room Photos</a></li>
					<li style="margin-top:5px;" role="presentation" class="offerPriceForm"><a href="offerPriceForm" >Special Offer Price</a></li>
				</ul>
				<div id="myTabContent" class="tab-content">   </div>
    
    </div>