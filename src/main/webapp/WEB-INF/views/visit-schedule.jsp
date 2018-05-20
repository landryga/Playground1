
<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">

New visit </br></br>



<form:form method="POST" commandName = "visit">


	
	
	<fieldset class="form-group">
	Select Date: <form:input path="visit_date" type="text" class="form-control" required = "required" id="datepicker"/>
	<form:errors path="visit_date" cssClass="text-warning"/>
	</br></br>
	<form:label path="doctor_name">Choose Doctor: </form:label>
	
		<form:select path="doctor_name" type = "select" value="true" class="form-control" required = "required">
			<form:options items="${doctor_name}" />
		</form:select>
		<form:errors path="doctor_name" cssClass="text-warning"/>

	
	</fieldset>
	
	<input class = "btn btn-success" type = "submit" value="Add to list"/>
	
</form:form>

</div>

<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script>

<%@ include file="common/footer.jspf" %>
