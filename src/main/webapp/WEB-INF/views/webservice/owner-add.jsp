
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

Dodaj właściciela pacjenta lub <a class="btn btn-info" href = "/webservice/list-owners">wybierz istniejącego</a>


<form:form method="POST" commandName = "owner">
	
	
	
	<fieldset class="form-group">
		<form:label path="name">Imię właściciela</form:label>
		<form:input path="name" type = "text" value = "Jakub" class="form-control" required = "required"/>
		<form:errors path="name" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="surname">Nazwisko właściciela</form:label>
		<form:input path="surname" type = "text" value="Walenda" class="form-control" required = "required"/>
		<form:errors path="surname" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="email">Email</form:label>
		<form:input path="email" type = "text" value = "kuba.walenda@gmail.com" class="form-control" required = "required"/>
		<form:errors path="email" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="telephone_number">Numer telefonu</form:label>
		<form:input path="telephone_number" type = "text" value = "664647910" class="form-control" required = "required"/>
		<form:errors path="telephone_number" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="address">Adres</form:label>
		<form:input path="address" type = "text" class="form-control" value = "Opalinska 5/7/7" required = "required"/>
		<form:errors path="address" cssClass="text-warning"/>
	
	</fieldset>
	
	<input class = "btn btn-success" type = "submit" value="Submit"/>
</form:form>

</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>