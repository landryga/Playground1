
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

<div>
Witaj doktorze ${name} ! </br></br>
Twoje wizyty:
</div>


<table class="table table-striped">

	<thead>
		<tr>
			<th>Imię pacjenta</th>
			<th>Imię właściciela</th>
			<th>Email Właściciela</th>
			<th>Data wizyty</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${visits}" var="visits">
	<tr>
			<td>${visits.patient_name}</td>
			<td>${visits.owner_name}</td>
			<td>${visits.email}</td>
			<td>${visits.visit_date}</td>
			<td></td>
			
			    <td>
			    <c:if test="${visits.active}">
			        <a href="/webservice/visit-add?visitId=${visits.visitId}&doctor_id=${doctor_id}&patient_id=${visits.patient_id}" class = "btn btn-success">Rozpocznij wizytę</a>
			    </c:if>
			    
			    <c:if test="${not visits.active &&  visits.past}">
					<a href="/webservice/visit-view?visitId=${visits.visitId}&doctor_id=${doctor_id}&patient_id=${visits.patient_id}" class = "btn btn-info"> &nbsp&nbsp&nbsp  Przeglądaj  &nbsp&nbsp&nbsp  </a>
			 	</c:if></td>
			
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>

<script>
var s = "test"




</script>



</div>
<%@ include file="/WEB-INF/views/common/footer.jspf" %>