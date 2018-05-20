
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">


<table class="table table-striped">

	<thead>
		<tr>
			<th>Name</th>
			<th>Surname</th>
			<th>Telephone number</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${owners}" var="owners">
	<tr>
			<td>${owners.name}</td>
			<td>${owners.surname}</td>
			<td>${owners.telephone_number}</td>
			<td><a href="/patient-add?owner_id=${owners.owner_id}" class = "btn btn-info">Edit</a></td>
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>

<div>
<a class="btn btn-success" href = "/owner-add">Add</a>
</div>

</div>
<%@ include file="common/footer.jspf" %>

