﻿
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class = "container">
	
<header>Wybierz akcję:</header></br>
	
<table class="table table-striped">	
	<tbody>	
		<tr>
			<td>Dodaj nowego użytkownika</td>
			<td><a href="/webservice/add-user" class = "btn btn-info">OK</a></td>
		</tr>
		<tr>
			<td>Zaktualizuj dane użytkownika:</td>
			<td><a href="/webservice/list-users" class = "btn btn-info">OK</a></td>
		</tr>
		<tr>
			<td>Edytuj grafik:</td>
			<td><a href="/webservice/list-shifts" class = "btn btn-info">OK</a></td>
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


<%@ include file="/WEB-INF/views/common/footer.jspf" %>