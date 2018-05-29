
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

Nowe badanie </br></br>


<button class = "btn btn-success" type="submit" id = "btn2">Zakoncz badanie</button>
</br></br>
<form:form method="POST" commandName = "examination" id="form1">

	<fieldset class="form-group col-xs-3">
		<form:label path="type_id">Typ badania: </form:label>
		<form:select path="type_id" type = "text" class="form-control" required = "required">
			<c:forEach items="${et}" var="et">
				<option value="${et.id}">${et.name}</option>
			</c:forEach>
		</form:select>
		<form:errors path="type_id" cssClass="text-warning"/>
	</fieldset>

	
	<fieldset class="form-group col-xs-3">
		<form:label path="patient_id">Pacjent: </form:label>
		<form:select path="patient_id" type = "text" class="form-control" required = "required">
			<c:forEach items="${patients}" var="patients">
				<option value="${patients.id}">${patients.patient_name}, ${patients.owner_name}</option>
			</c:forEach>
		</form:select>
		<form:errors path="patient_id" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
	
		<form:label path="result">Rezultat</form:label>
		<form:textarea path="result" type = "text" class="form-control" cols="50" rows="10"  required = "required" />
		<form:errors path="result" cssClass="text-warning"/>
	
	</fieldset>
	
	


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
