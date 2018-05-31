
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

Wyniki badania: </br></br>


<form:form method="POST" commandName = "examination" id="form1">

	<fieldset class="form-group col-xs-5">
		<form:label path="type_id">Typ badania: ${examination.type_name}</form:label>
		
		<form:errors path="type_id" cssClass="text-warning"/>
	</fieldset>

	
	<fieldset class="form-group col-xs-5">
		<form:label path="patient_id">Pacjent: ${examination.patient_name}, ${examination.ownerName}</form:label>
		
		<form:errors path="patient_id" cssClass="text-warning"/>
	</fieldset>
	
	
		<form:label path="result"></form:label>
		<form:textarea path="result" value="${examination.result}" type = "text" class="form-control" cols="50" rows="10" />
		<form:errors path="result" cssClass="text-warning"/>
	
	</br></br>
		<input class = "btn btn-success" type = "submit" value="OK"/>


<!--Goods: <a class="btn btn-success" href = "/visitgood-add?visit_id=${visit_id}">Add</a>  -->






</form:form>

</div>

<script type = "text/javascript">
	$(document).ready(function(){
		
		$('#btn2').click(function(){
			document.getElementById("form1").submit();
		});
	});

</script>



<%@ include file="/WEB-INF/views/common/footer.jspf" %>
