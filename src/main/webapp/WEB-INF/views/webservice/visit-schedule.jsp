
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

Zaplanuj wizytę </br></br>

<font color="red">${errormessage}</font>



<form:form method="POST" commandName = "visit">

<div class = "row">
	
	<fieldset class="form-group col-xs-4">
	<form:label path="visit_date">Wybierz datę </form:label>
		<form:input path="visit_date" type="text" class="form-control" required = "required" id="datetimepicker1" /></span>
		<form:errors path="visit_date" cssClass="text-warning"/>
	</fieldset>

	
	
	<fieldset class="form-group col-xs-4">
		<form:label path="doctor_id">Lekarz: </form:label>
			<form:select path="doctor_id" type = "text" class="form-control" required = "required">
				<c:forEach items="${users}" var="users">
					<option value="${users.id}">${users.username}  ${users.userSurName}</option>
				</c:forEach>
			</form:select>
		<form:errors path="doctor_id" cssClass="text-warning"/>

	
	</fieldset>
</div>	
	<input class = "btn btn-success" type = "submit" value="Zatwierdz"/>




	



  <script>
  $(function () {
	    $('#datetimepicker1').datetimepicker({format: 'DD/MM/YYYY HH', minDate: new Date()}).data('DateTimePicker').date();
	});
  </script>
  
</form:form>

</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>
