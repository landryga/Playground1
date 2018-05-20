<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class = "container">
	
<header>Select action:</header></br>
	
<table class="table table-striped">	
	<tbody>	
		<tr>
			<td>Add new user</td>
			<td><a href="/add-user" class = "btn btn-info">OK</a></td>
		</tr>
		<tr>
			<td>Update user</td>
			<td><a href="/list-users" class = "btn btn-info">OK</a></td>
		</tr>
		<!--  TODO - TO BE COMPLETED IN THE FUTURE
		<tr>
			<td>Manage consultations</td>
			<td><a href="/consultations" class = "btn btn-info">OK</a></td>
		</tr>
		
		-->
	</tbody>
</table>	
	
	
</div>


<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<%@ include file="common/footer.jspf" %>