
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>


<div class="container">



<h2>Edycja grafika</h2>

<form:form method="POST" commandName = "shift">
<br>
<div class="row">
<fieldset class="form-group col-xs-3">
		<form:label path="user_id">Pracownik: </form:label>
		<form:select path="user_id" type = "text" class="form-control" required = "required">
			<c:forEach items="${users}" var="users">
				<option value="${users.id}">${users.username}  ${users.userSurName}</option>
			</c:forEach>
		</form:select>
		<form:errors path="user_id" cssClass="text-warning"/>
	</fieldset>
	
		<font color="red">${message}</font>
	
	<fieldset class="form-group col-xs-3">
		<form:label path="start_date">Poczatek dyzuru: </form:label>
		<form:input path="start_date" type = "text" class="form-control" id = 'datetimepicker1' required = "required"/>
		<form:errors path="start_date" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group col-xs-3">
		<form:label path="end_date">Koniec dyzuru: </form:label>
		<form:input path="end_date" type = "text" class="form-control" id = 'datetimepicker2' required = "required"/>
		<form:errors path="end_date" cssClass="text-warning"/>
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
    $('#datetimepicker1').datetimepicker({format: 'DD/MM/YYYY HH:mm', minDate: new Date()}).data('DateTimePicker').date();
});

$(function () {
    $('#datetimepicker2').datetimepicker({format: 'DD/MM/YYYY HH:mm', minDate: new Date()}).data('DateTimePicker').date();
});


</script>

<script>

function myFunction(x) {
    alert('Dyzur usuniety');
    
    window.location = '/webservice/'+x;
}



</script>



<style> body { text-align: center; font-size: 14px; font-family: “Lucida Grande”,Helvetica,Arial,Verdana,sans-serif; } #calendar { width: 1000px; margin: 0 auto; } </style>





</form:form>

</div>

<%@ include file="/WEB-INF/views/common/footer.jspf" %>