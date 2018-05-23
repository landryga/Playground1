<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>


<div class="container">



<h2>Edycja grafika</h2>

<form:form method="POST" commandName = "shift" id="form1">
<br>
<div class="row">
<fieldset class="form-group col-xs-3">
		<form:label path="username">Pracownik: </form:label>
		<form:select path="username" type = "text" class="form-control" required = "required"/>
			<c:forEach items="${combo}" var="id">
    		<option value="${id}">${id}</option>
			</c:forEach>
		<form:errors path="username" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group col-xs-3">
		<form:label path="start_date">Poczatek dyzuru: </form:label>
		<form:input path="start_date" type = "text" class="form-control" required = "required"/>
		<form:errors path="start_date" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group col-xs-3">
		<form:label path="end_date">Koniec dyzuru: </form:label>
		<form:input path="end_date" type = "text" class="form-control" required = "required"/>
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
    $('#datetimepicker1').datetimepicker();
});


</script>

<script>

function myFunction(x) {
    alert('Dyzur usuniety');
    
    window.location = '/'+x;
}



</script>



<style> body { text-align: center; font-size: 14px; font-family: “Lucida Grande”,Helvetica,Arial,Verdana,sans-serif; } #calendar { width: 1000px; margin: 0 auto; } </style>





</form:form>

</div>

<%@ include file="common/footer.jspf" %>