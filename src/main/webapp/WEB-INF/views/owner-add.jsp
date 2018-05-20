
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">

Add patient's owner or <a class="btn btn-info" href = "/list-owners">Choose existing</a>


<form:form method="POST" commandName = "owner">
	
	
	
	<fieldset class="form-group">
		<form:label path="name">Owner name</form:label>
		<form:input path="name" type = "text" value = "Jakub" class="form-control" required = "required"/>
		<form:errors path="name" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="surname">Owner surname</form:label>
		<form:input path="surname" type = "text" value="Walenda" class="form-control" required = "required"/>
		<form:errors path="surname" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="email">Email</form:label>
		<form:input path="email" type = "text" value = "kuba.walenda@gmail.com" class="form-control" required = "required"/>
		<form:errors path="email" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="telephone_number">Telephone number</form:label>
		<form:input path="telephone_number" type = "text" value = "664647910" class="form-control" required = "required"/>
		<form:errors path="telephone_number" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="address">Address</form:label>
		<form:input path="address" type = "text" class="form-control" value = "Opalinska 5/7/7" required = "required"/>
		<form:errors path="address" cssClass="text-warning"/>
	
	</fieldset>
	
	<input class = "btn btn-success" type = "submit" value="Submit"/>
</form:form>

</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<%@ include file="common/footer.jspf" %>
