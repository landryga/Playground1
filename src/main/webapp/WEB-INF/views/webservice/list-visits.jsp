
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

Wizyty pacjenta ${patient_name} </br></br>

<div>
	<a class="btn btn-success" href = "/webservice/visit-add?patient_id=${patient_id}&doctor_id=${doctor_id}&visitId=0">Dodaj wizytę</a>
	<a class="btn btn-success" href = "/webservice/visit-schedule?patient_id=${patient_id}">Zaplanuj wizytę</a>
	
</div>
<font color="red">${message}</font>

<table class="table table-striped">

	<thead>
		<tr>
			<th>Lekarz</th>
			<th>Data wizyty</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${visits}" var="visits">
	<tr>
			<td>${visits.doctor_name}</td>
			<td>${visits.visit_date}</td>
			
			
			<td></td>
			<td>
			    <c:if test="${ visits.active}">
			        <a href="/webservice/visit-add?visitId=${visits.visitId}&doctor_id=${doctor_id}&patient_id=${patient_id}" class = "btn btn-success">Rozpocznij wizytę</a>
			    </c:if>
			    
			    <c:if test="${not visits.active &&  visits.past}">
					<a href="/webservice/visit-view?visitId=${visits.visitId}&doctor_id=${doctor_id}&patient_id=${patient_id}" class = "btn btn-info"> &nbsp&nbsp&nbsp  Przeglądaj  &nbsp&nbsp&nbsp  </a>
			 	</c:if>
			 	<c:if test="${not visits.active && not visits.past }">
					<a href="/webservice/remove-visit?id=${visits.visitId}" class = "btn btn-warning"> &nbsp&nbsp&nbsp  Usuń wizytę  &nbsp&nbsp&nbsp  </a>
			 	</c:if></td>
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>



</div>
<%@ include file="/WEB-INF/views/common/footer.jspf" %>

