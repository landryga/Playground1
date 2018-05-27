
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

<div>
<a class="btn btn-success" href = "/webservice/owner-add">Add new patient</a>
</div>

<table class="table table-striped">

	<thead>
		<tr>
			<th>Patient name</th>
			<th>Species</th>
			<th>Owner</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${patients}" var="patients">
	<tr>
			<td>${patients.patient_name}</td>
			<td>${patients.species}</td>
			<td>${patients.owner_name}</td>
			<td><a href="/webservice/list-visits?patient_id=${patients.patient_id}" class = "btn btn-info">Visits</a></td>
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>



</div>
<%@ include file="/WEB-INF/views/common/footer.jspf" %>

