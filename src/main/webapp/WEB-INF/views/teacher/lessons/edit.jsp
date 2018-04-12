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
	<form:form method="POST" action="${pageContext.request.contextPath}/teacher/lesson/edit/profile${lessonEditModel.idLesson}" modelAttribute="lessonEditModel">
	<nav aria-label="breadcrumb">
  		<ol class="breadcrumb menu-ul-nothover">
   			 <li class="breadcrumb-item"><a href="/teacher/courses/1">КУРСИ</a></li>
   			 <li class="breadcrumb-item"><a href="/teacher/course/profile${lessonEditModel.course.id}/1">ДЕТАЛІ КУРСУ</a></li>
   			 <li class="breadcrumb-item"><a href="/teacher/lesson/profile${lessonEditModel.idLesson}">ДЕТАЛІ УРОКУ</a></li>
   			 <li class="breadcrumb-item active" aria-current="page">РЕДАГУВАТИ</li>
  		</ol>
	</nav>
	</form:form>
	<form:form method="POST" action="${pageContext.request.contextPath}/teacher/lesson/edit/profile${lessonEditModel.idLesson}" modelAttribute="lessonEditModel">
    	<div class="form-group row required">
    		<div class="col-md-12">
     			 <label class="control-label col-sm-2" for="title">Номер:</label>
      				<form:input path="number" maxlength="3" size="500" class="form-control is-invalid" id="number" placeholder="Номер"></form:input>
					<form:errors path="number" cssClass="error"></form:errors>
        	</div>
        </div>
    	<div class="form-group row required">
    		<div class="col-md-12">
     			 <label class="control-label col-sm-2" for="title">Назва:</label>
      				<form:input path="title" maxlength="50" size="500" class="form-control is-invalid" id="title" placeholder="Назва"></form:input>
					<form:errors  path="title" cssClass="error"></form:errors>
        	</div>
        </div>
    	 <div class="form-group row">
			<div class="col-md-12">
           	 	<label class="control-label col-sm-2" for="description">Опис:</label>
      			<form:textarea path="description" maxlength="1000" size="1000" type="text" rows="10" style="resize:none" class="form-control is-invalid" id="description" placeholder="Опис"></form:textarea>
      			<form:errors  path="description" cssClass="error"></form:errors>
    		</div>
		</div>            
     	<div class="form-group row">
     		<div class="col-md-6 mb-3">
  				<button class="btn btn-primary buttonsAll" type="submit"><span class="glyphicon glyphicon-ok"></span> Зберегти</button>
  			</div>
  		</div>
	</form:form>
</div>


<%-- <form:form method="POST" action="${pageContext.request.contextPath}/teacher/lesson/edit/profile${lessonEditModel.idLesson}" modelAttribute="lessonEditModel">
	<table class="addTable">
		
		<tr>
			<td>№</td>
			<td>
				<form:input path="number" maxlength="50" size="50"/>
				<span class="error">*</span>
				<form:errors path="number" cssClass="error"></form:errors>
			</td>
		</tr>
		
			<tr>
			<td>Назва</td>
			<td>
				<form:input path="title" maxlength="50" size="50"/>
				<span class="error">*</span>
				<form:errors path="title" cssClass="error"></form:errors>
			</td>
		</tr>
		
			<tr>
			<td>Опис</td>
			<td>
				<form:textarea path="description" rows="20" cols="100" maxlength="250"/>
				<span class="error">*</span>
				<form:errors path="description" cssClass="error"></form:errors>
			</td>
		</tr>
		
	</table>
	<input class="button" type="submit" value="Редагувати групу">
</form:form>
<div class="profile"><a href="#" class="linkLikeButton" onclick="javascript: history.go(-1);">Повернутися</a></div> --%>