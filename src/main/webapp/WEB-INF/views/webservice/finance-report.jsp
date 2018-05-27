
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>


<!-- TODO - remove this JSP completely from the project -->
<div class="container">

Hi ${name} <br/>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Date</th>
			<th>Income</th>
			<th></th>
		</tr>
	</thead>
	
	<tbody>
	<c:forEach items="${finance_days}" var="finance_days">
	<tr>
			<td><fmt:formatDate pattern="dd/MM/yyyy" value = "${finance_days.date}"/></td>
			<td>${finance_days.income} PLN</td>
			<td></td>
			
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>


<%@ include file="/WEB-INF/views/common/footer.jspf" %>

