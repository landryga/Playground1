
<%@ include file="/WEB-INF/views/common/header-external.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation-external.jspf" %>

<div class="container">



<h2>Zapisz się na wizytę</h2>

<form:form method="POST" commandName = "visit">
<br>
<div class="row">
<fieldset class="form-group col-xs-3">
		<form:label path="doctor_id">Lekarz: </form:label>
		<form:select path="doctor_id" type = "text" class="form-control" required = "required">
			<c:forEach items="${users}" var="users">
				<option value="${users.id}">${users.username}  ${users.userSurName}</option>
			</c:forEach>
		</form:select>
		<form:errors path="doctor_id" cssClass="text-warning"/>
	</fieldset>
	
		<font color="red">${message}</font>
	
	<fieldset class="form-group col-xs-3">
		<form:label path="visit_date">Czas: </form:label>
		<form:input path="visit_date" type = "text" class="form-control" id = 'datetimepicker1' required = "required"/>
		<form:errors path="visit_date" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group col-xs-3">
		<form:label path="owner_name">Imie i nazwisko </form:label>
		<form:input path="owner_name" type = "text" class="form-control" id = 'datetimepicker1' required = "required"/>
		<form:errors path="owner_name" cssClass="text-warning"/>
	</fieldset>
	
	
	<br>
	
<input class = "btn btn-success" type = "submit" value="Dodaj" label = "Zatwierdz"/>
</div>

<hr>

<br>

<div id='calendar'></div>



<script>



$(function() {
	
	var date = new Date(); var d = date.getDate(); var m = date.getMonth(); var y = date.getFullYear();
	  
	$("#calendar").fullCalendar({
		theme:true,
		themeSystem:'bootstrap3',
		
		header: { left: 'prev, next, today, prevYear, nextYear', center: 'title', right: 'month,agendaWeek,agendaDay' }
		
		${data}
		
		
	})

});

$(function () {
    $('#datetimepicker1').datetimepicker({format: 'DD/MM/YYYY HH'}).data('DateTimePicker').date();
});



</script>





<style> body { text-align: center; font-size: 14px; font-family: “Lucida Grande”,Helvetica,Arial,Verdana,sans-serif; } #calendar { width: 1000px; margin: 0 auto; } </style>





</form:form>

</div>


<%@ include file="common/footer-external.jspf" %>