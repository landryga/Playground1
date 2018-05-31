
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

<div>
<a class="btn btn-success" href = "/webservice/owner-add">Dodaj nowego pacjenta</a>
</div>

<table class="table table-striped">

	<thead>
		<tr>
			<th>Pacjent</th>
			<th>Gatunek</th>
			<th>Właściciel</th>
			<th>Akcje</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${patients}" var="patients">
	<tr>
			<td>${patients.patient_name}</td>
			<td>${patients.species}</td>
			<td>${patients.owner_name}</td>
			<td><a href="/webservice/list-visits?patient_id=${patients.id}" class = "btn btn-info">Wizyty</a>    <a href="/webservice/list-patient-examinations?id_patient=${patients.id}" class = "btn btn-info">Badania</a></td>
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>



</div>
<%@ include file="/WEB-INF/views/common/footer.jspf" %>

