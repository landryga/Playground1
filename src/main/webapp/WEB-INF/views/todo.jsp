

<!-- TODO - remove this JSP completely from the project -->
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">

Add a todo

<form:form method="POST" commandName = "todo">
	
	<form:hidden path="id"/>
	<form:hidden path="user"/>
	
	<fieldset class="form-group">
		<form:label path="desc">Description</form:label>
		<form:input path="desc" type = "text" class="form-control" required = "required"/>
		<form:errors path="desc" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="targetDate">Target Date</form:label>
		<form:input path="targetDate" type = "text" class="form-control" required = "required"/>
		<form:errors path="targetDate" cssClass="text-warning"/>
	</fieldset>
	
	<input class = "btn btn-success" type = "submit" value="Submit"/>
</form:form>

</div>

<%@ include file="common/footer.jspf" %>
