<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class = "container">
	
<header>Select action:</header></br>
	
<div>
	<a class="btn btn-success" href = "/consultation-add">Add New Consultation</a>
</div>
	
<div>
<table class="table table-striped">

	<thead>
		<tr>
			<th>Consultation name</th>
			<th>price</th>
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
			<td><a href="/consultation-update?consultation_id=${consultations.consultation_id}" class = "btn btn-success">Edit</a></td>
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>
	
	
</div>


<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<%@ include file="common/footer.jspf" %>