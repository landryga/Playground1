
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

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
		<form:label path="userSurname">Nazwisko</form:label>
		<form:input path="userSurname" type = "text" class="form-control" required = "required"/>
		<form:errors path="userSurname" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="email">Email</form:label>
		<form:input path="email" type = "text" class="form-control" required = "required"/>
		<form:errors path="email" cssClass="text-warning"/>
	</fieldset>
	
	<font color="red">${message}</font>
	
	<fieldset class="form-group">
		<form:label path="is_admin">Role</form:label>
		<form:select path="is_admin" type = "select" class="form-control" required = "required">
			<form:option value="false">User</form:option>
			<form:option value="true">Admin</form:option>
		</form:select>
		<form:errors path="is_admin" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="is_doctor">Is doctor?</form:label>
		<form:select path="is_doctor" type = "select" class="form-control" required = "required">
			<form:option value="true">Yes</form:option>
			<form:option value="false">No</form:option>
		</form:select>
		<form:errors path="is_doctor" cssClass="text-warning"/>
	</fieldset>
	
	<button type="submit" value = "Submit" class="btn btn-info btn-lg" ">Submit</button>

	
	
</form:form>

</div>


<%@ include file="common/footer.jspf" %>
