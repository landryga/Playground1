<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class = "container">
	
<header>Select action:</header></br>
	
<div>
	<a class="btn btn-success" href = "/good-add">Add New Good</a>
</div>
	
<div>
<table class="table table-striped">

	<thead>
		<tr>
			<th>Good</th>
			<th>quantity</th>
			<th>price</th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${goods}" var="goods">
	<tr>
			<td>${goods.name}</td>
			<td>${goods.quantity} pcs</td>
			<td>${goods.price}</td>
			<td></td>
			<td><a href="/good-update?id=${goods.id}" class = "btn btn-success">Edit</a></td>
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>
	
	
</div>


<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<%@ include file="common/footer.jspf" %>