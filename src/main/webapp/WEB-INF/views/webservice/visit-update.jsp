﻿
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

Edytuj wizytę </br></br>

<form:form method="POST" commandName = "visit">
	
	
	<fieldset class="form-group">
	
		<form:label path="visit_description">Opis</form:label>
		<form:textarea path="visit_description" type = "text" class="form-control" cols="50" rows="10" />
		<form:errors path="visit_description" cssClass="text-warning"/>
	
	</fieldset>
	
	<input class = "btn btn-success" type = "submit" value="End visit"/>
</form:form>

</div>


<%@ include file="/WEB-INF/views/common/footer.jspf" %>
