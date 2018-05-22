<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>


<div class="container">



<form:form method="POST" commandName = "list-shifts">


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


</script>



<style> body { text-align: center; font-size: 14px; font-family: “Lucida Grande”,Helvetica,Arial,Verdana,sans-serif; } #calendar { width: 1000px; margin: 0 auto; } </style>





</form:form>

</div>

<%@ include file="common/footer.jspf" %>