<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<div class="row">
							<div class="col-md-12">
								<legend>Life Partner Requirements</legend>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Age From </label>
									<div class="col-sm-8">
										<form:input path="rAgeFrom" class="form-control u1" placeholder="Enter Age From" />
								  		<div><form:errors path="rAgeFrom" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Age To </label>
									<div class="col-sm-8">
										<form:input path="rAgeTo" class="form-control u1" placeholder="Enter Age To" />
								  		<div><form:errors path="rAgeTo" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Height</label>
									<div class="col-sm-8">
										<form:select path="rHeight" class="form-control u" >
											<form:option value="">-- Choose Height --</form:option>
											<form:options items="${height}"></form:options>
										</form:select>
										<div><form:errors path="rHeight" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Marital Status </label>
									<div class="col-sm-8">
										<form:select path="rMaritalStatus" class="form-control u1" onchange="hideChildren();">
											<form:option value="all">Doesn't Matter</form:option>
											<form:option value="Married">Married</form:option>
											<form:option value="Unmarried">Unmarried</form:option>
											<form:option value="Widow/Divorced">Widow/Divorced</form:option>
										</form:select>
								  		<div><form:errors path="rMaritalStatus" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Religion </label>
									<div class="col-sm-8">
										<form:select path="rReligion" class="form-control u1">
											<form:option value="">-- Choose Religion --</form:option>
											<form:options items="${religion}"></form:options>
										</form:select>
								  		<div><form:errors path="rReligion" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Community</label>
									<div class="col-sm-8">
										<form:select path="rCaste" class="form-control u1">
											<form:option value="">-- Choose Community --</form:option>
											<form:options items="${cast}"></form:options>
										</form:select>
										<div><form:errors path="rCaste" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Mother Tongue </label>
									<div class="col-sm-8">
										<form:select path="rMotherTongue" onfocus="removeBorder(this.id)" class="form-control" >
											<form:option value="">-- Choose Mother Tongue --</form:option>
											<form:options items="${language}"></form:options>
										</form:select>
								  		<div><form:errors path="rMotherTongue" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Country living in </label>
									<div class="col-sm-8">
										<form:select path="rCountry" class="form-control u">
											<form:option value="">-- Choose Country --</form:option>
											<form:options items="${countries}"></form:options>
										</form:select>
								  		<div><form:errors path="rCountry" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">State living in</label>
									<div class="col-sm-8">
										<form:select path="rState" class="form-control u">
											<form:option value="">-- Choose State --</form:option>
											<form:options items="${states }"></form:options>
										</form:select>
										<div><form:errors path="rState" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Education </label>
									<div class="col-sm-8">
										<form:select path="rEducation" class="form-control u">
											<form:option value="all">Doesn't Matter</form:option>
											<form:options items="${education}"></form:options>
										</form:select>
								  		<div><form:errors path="rEducation" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Working With </label>
									<div class="col-sm-8">
										<form:select path="rWorkingWith" class="form-control u1">
											<form:option value="all">Doesn't Matter</form:option>
											<form:option value="Private Company">Private Company</form:option>
											<form:option value="Government/Public Sector">Government/Public Sector</form:option>
											<form:option value="Defense/Civil Services">Defense/Civil Services</form:option>
											<form:option value="Not Working">Not Working</form:option>
										</form:select>
								  		<div><form:errors path="rWorkingWith" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Profession area</label>
									<div class="col-sm-8">
										<form:select path="rOccupation" class="form-control u">
											<form:option value="all">Doesn't Matter</form:option>
											<form:options items="${occupation}"></form:options>
										</form:select>
										<div><form:errors path="rOccupation" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
						</div>
<script>
$(document).ready(function(){
	$(".incomeBlock").hide();
	
    $(".incomeRange").click(function(){
    	$(".incomeBlock").show();
    });
    $(".incomeMatter").click(function(){
    	$(".incomeBlock").hide();
    });
    /* $(".more").click(function(){
        $(".ifMore").collapse('toggle');
    });
    $(".more").click(function(){
        $(".ifMore").collapse('hide');
    }); */
    
});
</script>   						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Annual Income </label>
									<div class="col-sm-8">
										<label class="radio-inline" for="ai1">
											<input name="annualIncome" class="incomeMatter" id="ai1" value="all" type="radio"> Doesn't Matter
										</label>
										<label class="radio-inline" for="ai2"> 
											<input name="annualIncome" class="incomeRange" id="ai2" value="Specify an income range" type="radio"> Specify Income
										</label>
									</div>
							  	</div>
							</div>
							<div class="col-md-4">
								<div class="form-group incomeBlock">
									<div class="col-md-offset-4 col-sm-8">
										<form:select path="rAnnualIncome" class="form-control u1">
											<form:option value="">-- Annual Income --</form:option>
											<form:option value="Upto INR 1 Lakh">Upto INR 1 Lakh</form:option>
											<form:option value="INR 2 Lakh to 4 Lakh">INR 2 Lakh to 4 Lakh</form:option>
											<form:option value="INR 5 Lakh to 7 Lakh">INR 5 Lakh to 7 Lakh</form:option>
											<form:option value="Above INR 7 Lakh">Above INR 7 Lakh</form:option>
										</form:select>
								  		<div><form:errors path="rAnnualIncome" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
						</div>
						
						<div class="row">
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Profile Created by </label>
									<div class="col-sm-8">
										<form:select path="rCreateProfileFor" onfocus="removeBorder(this.id)" class="form-control">
											<form:option value="">-- Select --</form:option>
											<form:option value="Self">Self</form:option>
											<form:option value="Son">Son</form:option>
											<form:option value="Daughter">Daughter</form:option>
											<form:option value="Brother">Brother</form:option>
											<form:option value="Sister">Sister</form:option>
											<form:option value="Friend">Friend</form:option>
											<form:option value="Relative">Relative</form:option>
										</form:select>
								  		<div><form:errors path="rCreateProfileFor" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
							<div class="col-md-4">
								<div class="form-group">
									<label class="col-sm-4 control-label required">Diet</label>
									<div class="col-sm-8">
										<form:select path="rDiet" class="form-control u" >
											<form:option value="">-- Select Diet--</form:option>
											<form:option value="Veg">Veg</form:option>
											<form:option value="Non-Veg">Non-Veg</form:option>
											<form:option value="Occasionally Non-Veg">Occasionally Non-Veg</form:option>
											<form:option value="Eggetarian">Eggetarian</form:option>
											<form:option value="Jain">Jain</form:option>
											<form:option value="Vegan">Vegan</form:option>
										</form:select>
										<div><form:errors path="rDiet" cssClass="error" /></div>
									</div>
							  	</div>
							</div>
						</div>