
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">

New visit </br></br>


<button class = "btn btn-success" type="submit" id = "btn2">End Visit</button>
</br></br>
<form:form method="POST" commandName = "visit" id="form1">
	
	
	<fieldset class="form-group">
	
		<form:label path="visit_description">visit_description</form:label>
		<form:textarea path="visit_description" type = "text" class="form-control" cols="50" rows="10" value = "${visit_description}" />
		<form:errors path="visit_description" cssClass="text-warning"/>
	
	</fieldset>
	
	


<!--Goods: <a class="btn btn-success" href = "/visitgood-add?visit_id=${visit_id}">Add</a>  -->



<form:input path="test" id="test" type="hidden" value="0"/>


<button class = "btn btn-success" type="button" id = "btn1">Add Good</button> </br></br>

<table class="table table-striped">

	
	<tbody>
	<tr>
			<td>
				<fieldset class="form-group">
					<form:label path="qty">Good</form:label>
					<form:select path="good_name" type = "select" class="form-control" required = "required">
						<form:options items="${good_name}" />
					</form:select>
					<form:errors path="good_name" cssClass="text-warning"/>
				</fieldset>
			</td>
			<td>
				<fieldset class="form-group">
					<form:label path="qty">Quantity</form:label>
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
			<th>Good name</th>
			<th>quantity</th>
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

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<%@ include file="common/footer.jspf" %>
