﻿
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

<form:form method="POST" commandName = "good">
	
	
	
	<fieldset class="form-group">
		<form:label path="name">Nazwa </form:label>
		<form:input path="name" type = "text" class="form-control" required = "required"/>
		<form:errors path="name" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="quantity">Ilość </form:label>
		<form:input path="quantity" type = "text" class="form-control" required = "required"/>
		<form:errors path="quantity" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="price">Cena </form:label>
		<form:input path="price" type = "text" class="form-control" required = "required"/>
		<form:errors path="price" cssClass="text-warning"/>
	</fieldset>
	
	
	<input class = "btn btn-success" type = "submit" value="Zatwierdz"/>
</form:form>

</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>
