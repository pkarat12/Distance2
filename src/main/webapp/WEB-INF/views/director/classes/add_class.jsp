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
    <li class="breadcrumb-item"><a href="/director/classes/1">КЛАСИ</a></li>
    <li class="breadcrumb-item active" aria-current="page">ДОДАТИ КЛАСИ</li>
  </ol>
</nav>
<form:form method="POST" action="${pageContext.request.contextPath}/director/add_class" modelAttribute="classAddRequestModel">
   	<div class="form-group row required">
    		<div class="col-md-12">
     		 	<label class="control-label col-sm-2" for="grade">Рік:</label>
					<form:select class="form-control selectpicker input_border" data-style="btn-primary" path="grade" id="grade">
						<form:option value="${null}">Оберіть рік</form:option>
							<c:forEach items="${gradeModel}" var="grade">
								<form:option value="${grade}">${grade.title}</form:option>
							</c:forEach>
					</form:select>
					<form:errors  path="grade" cssClass="error"></form:errors>
        	</div>
   		</div>
      
    <div class="form-group row required">
    		<div class="col-md-12">
     		 	<label class="control-label col-sm-2" for="letterToGrade">Буква:</label>
					<form:select class="form-control selectpicker input_border" data-style="btn-primary" path="letterToGrade" id = "letterToGrade">
						<form:option value="${null}">Оберіть букву</form:option>
							<c:forEach items="${letterModel}" var="letter">
								<form:option value="${letter}">${letter.title}</form:option>
							</c:forEach>
					</form:select>
					<form:errors  path="*" cssClass="error"></form:errors>
        	</div>
   	</div>
    
  <div class="form-group row">
    		<div class="col-md-12">
     		 	<label class="control-label col-sm-2" for="userClassTeacher">Класний керівник:</label>
					<form:select class="form-control selectpicker input_border" data-style="btn-primary" path="userClassTeacher" id = "userClassTeacher">
						<form:option value="${null}">Оберіть класного керівника</form:option>
							<c:forEach items="${listTeachersModel}" var="teachers">
								<form:option value="${teachers}">${teachers.firstName} ${teachers.middleName} ${teachers.lastName}</form:option>
							</c:forEach>
					</form:select>
        	</div>
   	</div>

     <div class="form-group row">
     	<div class="col-md-6 mb-3">
  		<button class="btn btn-primary buttonsAll" type="submit"><span class="glyphicon glyphicon-plus"></span> Додати</button>
  		</div>
  	</div>
</form:form>
</div>