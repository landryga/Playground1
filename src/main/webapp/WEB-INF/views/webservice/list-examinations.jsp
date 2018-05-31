
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

Lista badań </br></br>

<div>
	<a class="btn btn-success" href = "/webservice/examination-add?doctor_id=${doctor_id}">Dodaj badanie</a>
	
</div>

${errormessage}

<table class="table table-striped">

	<thead>
		<tr>
			<th>Pacjent</th>
			<th>Data</th>
			<th>Rodzaj badania</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${examinations}" var="examinations">
	<tr>
			<td>${examinations.patient_name}, ${examinations.ownerName}</td>
			<td>${examinations.date}</td>
			<td>${examinations.type_name}</td>
			
			
			<td></td>
			<td>
			    <a href="/webservice/examination-view?id=${examinations.id}" class = "btn btn-info"> &nbsp&nbsp&nbsp  Zobacz wyniki  &nbsp&nbsp&nbsp  </a>
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>



</div>
<%@ include file="/WEB-INF/views/common/footer.jspf" %>

