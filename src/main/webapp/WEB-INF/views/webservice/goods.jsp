
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class = "container">
	
<header>Wybierz akcję</header></br>
	
<div>
	<a class="btn btn-success" href = "/webservice/good-add">Dodaj nowy produkt</a>
</div>
	
<div>
<table class="table table-striped">

	<thead>
		<tr>
			<th>Produkt</th>
			<th>Ilość</th>
			<th>Cena</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${goods}" var="goods">
	<tr>
			<td>${goods.name}</td>
			<td>${goods.quantity} szt.</td>
			<td>${goods.price}</td>
			<td></td>
			<td><a href="/webservice/good-update?id=${goods.id}" class = "btn btn-success">Edytuj</a></td>
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>
	
	
</div>



<%@ include file="/WEB-INF/views/common/footer.jspf" %>