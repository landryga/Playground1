
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">

<form:form method="POST" commandName = "good">
	
	
	
	<fieldset class="form-group">
		<form:label path="name">Good name: </form:label>
		<form:input path="name" type = "text" class="form-control" required = "required"/>
		<form:errors path="name" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="quantity">Good quantity: </form:label>
		<form:input path="quantity" type = "text" class="form-control" required = "required"/>
		<form:errors path="quantity" cssClass="text-warning"/>
	</fieldset>
	
	<fieldset class="form-group">
		<form:label path="price">Good price: </form:label>
		<form:input path="price" type = "text" class="form-control" required = "required"/>
		<form:errors path="price" cssClass="text-warning"/>
	</fieldset>
	
	
	<input class = "btn btn-success" type = "submit" value="Submit"/>
</form:form>

</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<%@ include file="common/footer.jspf" %>
