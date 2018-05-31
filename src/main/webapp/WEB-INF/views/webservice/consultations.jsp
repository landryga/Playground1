
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class = "container">
	
<header>Wybierz akcję</header></br>
	
<div>
	<a class="btn btn-success" href = "/webservice/consultation-add">Dodaj nową konsultację</a>
</div>
	
<div>
<table class="table table-striped">

	<thead>
		<tr>
			<th>Nazwa</th>
			<th>Cena</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${consultations}" var="consultations">
	<tr>
			<td>${consultations.consultation_name}</td>
			<td>${consultations.price} pln</td>
			
			
			<td></td>
			<td><a href="/webservice/consultation-update?consultation_id=${consultations.consultation_id}" class = "btn btn-success">Edytuj</a></td>
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>
	
	
</div>



<%@ include file="/WEB-INF/views/common/footer.jspf" %>