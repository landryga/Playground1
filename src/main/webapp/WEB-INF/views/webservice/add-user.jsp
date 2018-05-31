
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

Dodaj użytkownika

<form:form method="POST" commandName = "user">
	
	<form:hidden path="id"/>
	
	<fieldset class="form-group">
		<form:label path="username">Imię</form:label>
		<form:input path="username" type = "text" class="form-control" required = "required"/>
		<form:errors path="username" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="userSurName">Nazwisko</form:label>
		<form:input path="userSurName" type = "text" class="form-control" required = "required"/>
		<form:errors path="userSurName" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="email">Email</form:label>
		<form:input path="email" type = "text" class="form-control" required = "required"/>
		<form:errors path="email" cssClass="text-warning"/>
	</fieldset>
	
	<font color="red">${message}</font>
	
	<fieldset class="form-group">
		<form:label path="is_admin">Rola</form:label>
		<form:select path="is_admin" type = "select" class="form-control" required = "required">
			<form:option value="false">User</form:option>
			<form:option value="true">Admin</form:option>
		</form:select>
		<form:errors path="is_admin" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="is_doctor">Czy jest doktorem?</form:label>
		<form:select path="is_doctor" type = "select" class="form-control" required = "required">
			<form:option value="true">Yes</form:option>
			<form:option value="false">No</form:option>
		</form:select>
		<form:errors path="is_doctor" cssClass="text-warning"/>
	</fieldset>
	
	<button type="submit" onclick="myFunction()" value = "Submit" class="btn btn-info btn-lg" ">Zatwierdź</button>

	
	
</form:form>

<script>

</script>

</div>


<%@ include file="/WEB-INF/views/common/footer.jspf" %>
