
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>
<div class="container">

<div>
Witaj ${name} ! </br></br>
</div>

<div id='calendar'></div>

<div>Kalendarz wizyt:</div>
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

</script>

</div>
<%@ include file="/WEB-INF/views/common/footer.jspf" %>