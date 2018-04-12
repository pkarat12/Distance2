<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.1.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js" ></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<div class="col-md-8">
	<nav aria-label="breadcrumb">
  		<ol class="breadcrumb menu-ul-nothover">
    		<li class="breadcrumb-item"><a href="/director/students/1">УЧНІ</a></li>
    		<li class="breadcrumb-item active" aria-current="page">ДОДАТИ</li>
  		</ol>
	</nav>
	<form:form method="POST" action="${pageContext.request.contextPath}/director/add_student" modelAttribute="studentAddRequestModel">
    	<div class="form-group row required">
    		<div class="col-md-12">
     			 <label class="control-label col-sm-2" for="lastname">Прізвище:</label>
      				<form:input path="lastName" maxlength="50" size="500" class="form-control is-invalid" id="lastname" placeholder="Прізвище"></form:input>
					<form:errors  path="lastName" cssClass="error"></form:errors>
        	</div>
        </div>
    	<div class="form-group row required">
    		<div class="col-md-12">
     			 <label class="control-label col-sm-2" for="firstname">Ім'я:</label>
      				<form:input path="firstName" maxlength="50" size="500" class="form-control is-invalid" id="firstnname" placeholder="Ім'я"></form:input>
					<form:errors  path="firstName" cssClass="error"></form:errors>
       		</div>
    	</div>      
   		 <div class="form-group row">
    		<div class="col-md-12">
     			 <label class="control-label col-sm-2" for="middlename">По-батькові:</label>
      				<form:input path="middleName" maxlength="50" size="500" class="form-control is-invalid" id="middlenname" placeholder="По-батькові:"></form:input>
					<form:errors  path="middleName" cssClass="error"></form:errors>
        	</div>
   		</div>     
    	<div class="form-group row required">
    		<div class="col-md-12">
     			 <label class="control-label col-sm-2" for="login">Логін:</label>
      				<form:input path="login" maxlength="50" size="500" class="form-control is-invalid" id="login" placeholder="Логін"></form:input>
					<form:errors  path="login" cssClass="error"></form:errors>
        	</div>
    	</div>      
    	<div class="form-group row required">
    		<div class="col-md-12">
     			 <label class="control-label col-sm-2" for="password">Пароль:</label>
      				<form:password path="password" maxlength="50" size="500" class="form-control is-invalid" id="password" placeholder="Пароль"></form:password>
					<form:errors  path="password" cssClass="error"></form:errors>
        	</div>
    	</div>      
    	<div class="form-group row required">
    		<div class="col-md-12">
     		 	<label class="control-label col-sm-2" for="classStudent">Клас:</label>
					<form:select class="form-control selectpicker input_border" data-style="btn-primary" path="classStudent" id="classStudent">
						<form:option  value="">--Оберіть клас--</form:option>
						<c:forEach items="${classesListModel}" var="clas">
							<form:option value="${clas}">${clas.toString()}</form:option>
						</c:forEach>
					</form:select>
					<form:errors  path="classStudent" cssClass="error"></form:errors>
        	</div>
   		</div>
     	<div class="form-group row">
     		<div class="col-md-6 mb-3">
  				<button class="btn btn-primary buttonsAll" type="submit"><span class="glyphicon glyphicon-plus"></span> Додати</button>
  			</div>
  		</div>
</form:form>
</div>