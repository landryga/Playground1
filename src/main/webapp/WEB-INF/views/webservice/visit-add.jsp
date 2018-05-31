
<%@ include file="/WEB-INF/views/common/header.jspf" %>
<%@ include file="/WEB-INF/views/common/navigation.jspf" %>

<div class="container">

Nowa wizyta </br></br>


<button class = "btn btn-success" type="submit" id = "btn2">Zakończ wizytę</button>
</br></br>
<form:form method="POST" commandName = "visit" id="form1">
	
	
	<fieldset class="form-group">
	
		<form:label path="visit_description">Opis</form:label>
		<form:textarea path="visit_description" type = "text" class="form-control" cols="50" rows="10"  required = "required" />
		<form:errors path="visit_description" cssClass="text-warning"/>
	
	</fieldset>
	
	


<!--Goods: <a class="btn btn-success" href = "/visitgood-add?visit_id=${visit_id}">Add</a>  -->



<form:input path="test" id="test" type="hidden" value="0"/>



<button class = "btn btn-success" type="button" id = "btn1">Dodaj produkt</button> </br></br>

<table class="table table-striped">

	
	<tbody>
	<tr>
			<td>
				<fieldset class="form-group">
					<form:label path="qty">Produkt</form:label>
					<form:select path="good_name" type = "select" class="form-control" required = "required">
						<form:options items="${good_name}" />
					</form:select>
					<form:errors path="good_name" cssClass="text-warning"/>
				</fieldset>
			</td>
			<td>
				<fieldset class="form-group">
					<form:label path="qty">Ilość</form:label>
					<form:input path="qty" type = "text" class="form-control" required = "required"/>
					<form:errors path="qty" cssClass="text-warning"/>
				</fieldset>
			</td>
			
			<td></td>
			<td></td>
		</tr>
	</tbody>
</table>

<table class="table table-striped">
	<thead>
		<tr>
			<th>Nazwa produktu</th>
			<th>Ilość</th>
			<th></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${visitgoods}" var="visitgoods">
	<tr>
			<td>
				${visitgoods.name}
			</td>
			<td>
				${visitgoods.qty}
			</td>
			
			
			<td></td>
			<td></td>
		</tr>
	</c:forEach>
		
	</tbody>
	
</table>

</form:form>

</div>

<script type = "text/javascript">
	$(document).ready(function(){
		$('#btn1').click(function(){
			document.getElementById("test").value = 1;
			document.getElementById("form1").submit();
			document.getElementById("test").value = 0;
		});
		$('#btn2').click(function(){
			document.getElementById("form1").submit();
		});
	});

</script>


<%@ include file="/WEB-INF/views/common/footer.jspf" %>
