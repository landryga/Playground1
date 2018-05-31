﻿
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">


<table class="table table-striped">

	<thead>
		<tr>
			<th>Imię</th>
			<th>Email</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${users}" var="users">
	<tr>
			<td>${users.username}</td>
			<td>${users.email}</td>
			<td><a href="/webservice/update-user?id=${users.id}" class = "btn btn-info">Edytuj</a>&nbsp&nbsp&nbsp<a href="/webservice/remove-user?id=${users.id}" class = "btn btn-warning">Usuń użytkownika</a></td>
			<td></td>
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>

</div>
<%@ include file="/WEB-INF/views/common/footer.jspf" %>

