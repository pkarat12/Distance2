<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.1.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js" ></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<div class="col-md-8">
	
	<form:form method="POST" action="${pageContext.request.contextPath}/director/edit/class-teacher${classToStudentModel.id}" modelAttribute="classToStudentModel">
    	<nav aria-label="breadcrumb">
  		<ol class="breadcrumb menu-ul-nothover">
   			 <li class="breadcrumb-item"><a href="/director/classes/1">КЛАСИ</a></li>
   				<li class="breadcrumb-item"><a href="/director/profile-class${classToStudentModel.id}">ДЕТАЛІ</a></li>
   			 <li class="breadcrumb-item active" aria-current="page">РЕДАГУВАТИ</li>
  		</ol>
	</nav>
    	<div class="form-group row">
    		<div class="col-md-12">
    			<label class="control-label col-sm-2" for="select1 select2">Класний керівник:</label>
    			<c:if test="${flag == false}">
						<form:select class="form-control selectpicker input_border" data-style="btn-primary" path="userClassTeacher" id="select1">
							<form:option value="${null}">--Оберіть класного керівника--</form:option>
							<c:forEach items="${listTeachersModel}" var="teachers">
								<form:option value="${teachers}">${teachers.firstName} ${teachers.middleName} ${teachers.lastName}</form:option>
							</c:forEach>
						</form:select>
					</c:if>
					<c:if test="${flag == true}">
						<form:select class="form-control selectpicker input_border" data-style="btn-primary" path="userClassTeacher" id="select2">
							<form:option value="${classToStudentModel.userClassTeacher}">${classToStudentModel.userClassTeacher.firstName} ${classToStudentModel.userClassTeacher.middleName} ${classToStudentModel.userClassTeacher.lastName}</form:option>
							<c:forEach items="${listTeachersModel}" var="teachers">
								<form:option value="${teachers}">${teachers.firstName} ${teachers.middleName} ${teachers.lastName}</form:option>
							</c:forEach>
							<form:option value="${null}">--нема класного керівника--</form:option>
						</form:select>
					</c:if>
        	</div>
   		</div>
    
     <div class="form-group row">
     	<div class="col-md-6 mb-3">
  			<button class="btn btn-primary buttonsAll" type="submit"><span class="glyphicon glyphicon-ok"></span> Зберегти</button>
  		</div>
  	</div>
</form:form>
</div>