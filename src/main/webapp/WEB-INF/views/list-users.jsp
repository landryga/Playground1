
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">


<table class="table table-striped">

	<thead>
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${users}" var="users">
	<tr>
			<td>${users.username}</td>
			<td>${users.email}</td>
			<td><a href="/update-user?id=${users.id}" class = "btn btn-info">Edit</a></td>
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>

</div>
<%@ include file="common/footer.jspf" %>

