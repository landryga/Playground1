
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

<form:form method="POST" commandName = "consultation">
	
	
	
	<fieldset class="form-group">
		<form:label path="consultation_name">Consultation name: </form:label>
		<form:input path="consultation_name" type = "text" value = "${consultation_name}" class="form-control" required = "required"/>
		<form:errors path="consultation_name" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="price">Consultation price: </form:label>
		<form:input path="price" type = "text" value = "${price}"  class="form-control" required = "required"/>
		<form:errors path="price" cssClass="text-warning"/>
	</fieldset>
	
	
	<input class = "btn btn-success" type = "submit" value="Submit"/>
</form:form>

</div>


<%@ include file="/WEB-INF/views/common/footer.jspf" %>
