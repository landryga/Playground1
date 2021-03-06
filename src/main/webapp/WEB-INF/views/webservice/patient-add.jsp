﻿
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>
<div class="container">

Dodaj pacjenta

<form:form method="POST" commandName = "patient">
	
	<form:hidden path="owner_id"/>
	
	<fieldset class="form-group">
		<form:label path="race">Rasa</form:label>
		<form:input path="race" type = "text" class="form-control" />
		<form:errors path="race" cssClass="text-warning"/>
		
		<form:label path="species">Gatunek</form:label>
		<form:input path="species" type = "text" value="Dog" class="form-control" required = "required" />
		<form:errors path="species" cssClass="text-warning"/>
		
		<form:label path="sex">Płeć</form:label>
		<form:select path="sex" type = "select" value="true" class="form-control" required = "required">
			<form:option value="false">Męska</form:option>
			<form:option value="true">Żeńska</form:option>
		</form:select>
		<form:errors path="sex" cssClass="text-warning"/>
		
		<form:label path="patient_name">Imię</form:label>
		<form:input path="patient_name" type = "text" value = "test" class="form-control" required = "required" />
		<form:errors path="patient_name" cssClass="text-warning"/>
		
		<form:label path="birth_date">Data urodzenia</form:label>
		<form:input path="birth_date" type = "text" value="2016-06-06" class="form-control" required = "required" />
		<form:errors path="birth_date" cssClass="text-warning"/>
		
		<form:label path="microchip_id">Microchip id</form:label>
		<form:input path="microchip_id" type = "text" value = "234" class="form-control"/>
		<form:errors path="microchip_id" cssClass="text-warning"/>
		
	
	</fieldset>
	
	<input class = "btn btn-success" type = "submit" value="Submit"/>
</form:form>

</div>


<%@ include file="/WEB-INF/views/common/footer.jspf" %>
