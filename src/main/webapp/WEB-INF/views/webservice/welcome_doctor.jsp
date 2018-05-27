
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

<div>
Welcome doctor ${name} ! </br></br>
Your visits today:
</div>


<table class="table table-striped">

	<thead>
		<tr>
			<th>Patient name</th>
			<th>Owner name</th>
			<th>Visit date</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${visits}" var="visits">
	<tr>
			<td>${visits.patient_name}</td>
			<td>${visits.owner_name}</td>
			<td>${visits.visit_date}</td>
			<td></td>
			
			    <td>
			    <c:if test="${visits.active}">
			        <a href="/webservice/visit-add?visitId=${visits.visitId}&doctor_id=${doctor_id}&patient_id=${visits.patient_id}" class = "btn btn-success">Begin visit</a>
			    </c:if>
			    
			    <c:if test="${not visits.active &&  visits.past}">
					<a href="/webservice/visit-view?visitId=${visits.visitId}&doctor_id=${doctor_id}&patient_id=${visits.patient_id}" class = "btn btn-info"> &nbsp&nbsp&nbsp  View  &nbsp&nbsp&nbsp  </a>
			 	</c:if></td>
			
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>



</div>
<%@ include file="/WEB-INF/views/common/footer.jspf" %>