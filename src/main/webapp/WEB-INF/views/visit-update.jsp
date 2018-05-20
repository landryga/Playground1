
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">

New visit </br></br>

<form:form method="POST" commandName = "visit">
	
	
	<fieldset class="form-group">
	
		<form:label path="visit_description">Description</form:label>
		<form:textarea path="visit_description" type = "text" class="form-control" cols="50" rows="10" />
		<form:errors path="visit_description" cssClass="text-warning"/>
	
	</fieldset>
	
	<input class = "btn btn-success" type = "submit" value="End visit"/>
</form:form>

</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<%@ include file="common/footer.jspf" %>
