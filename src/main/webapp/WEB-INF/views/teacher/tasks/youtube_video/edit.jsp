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
	<form:form method="POST" action="${pageContext.request.contextPath}/teacher/edit-video-youtube-task${taskModel.idTask}" modelAttribute="taskModel">
		<nav aria-label="breadcrumb">
  			<ol class="breadcrumb menu-ul-nothover">
    			<li class="breadcrumb-item"><a href="/teacher/video-youtube-tasks/1">YOUTUBE ВІДЕО</a></li>
    			<li class="breadcrumb-item"><a href="/teacher/video-youtube-task-profile${taskModel.idTask}">ДЕТАЛІ</a></li>
   			 	<li class="breadcrumb-item active" aria-current="page">РЕДАГУВАТИ</li>
  			</ol>
		</nav>
	</form:form>
	<form:form method="POST" action="${pageContext.request.contextPath}/teacher/edit-video-youtube-task${taskModel.idTask}" modelAttribute="taskModel">
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
		<div class="form-group row required">
    		<div class="col-md-12">
     			 <label class="control-label col-sm-2" for="link">URL:</label>
      				<form:input path="link" maxlength="255" size="500" class="form-control is-invalid" id="link" placeholder="Youtube URL Відео"></form:input>
					<form:errors  path="link" cssClass="error"></form:errors>
        	</div>
        </div>    
     	<div class="form-group row">
     		<div class="col-md-6 mb-3">
  				<button class="btn btn-primary buttonsAll" type="submit"><span class="glyphicon glyphicon-ok"></span> Зберегти</button>
  			</div>
  		</div>
	</form:form>
</div>



<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<form:form method="POST" action="${pageContext.request.contextPath}/teacher/edit-video-youtube-task${taskModel.idTask}" modelAttribute="taskModel">
	<table class="addTable">
		<tr>
			<td>Назва</td>
			<td>
				<form:input path="title" maxlength="50" size="50"/>
				<span class="valid">*</span>
				<form:errors  path="title" cssClass="error"></form:errors>
			</td>	
		</tr>
		
		<tr>
			<td>Опис</td>
			<td>
				<form:textarea path="description" rows="30" cols="100"/>
				<form:errors path="description" cssClass="error"></form:errors>
			</td>
		</tr>
		<tr>
			<td>Link YouTube</td>
			<td>
				<form:input path="link" maxlength="50" size="50"/>
				<span class="valid">*</span>
				<form:errors  path="link" cssClass="error"></form:errors>
			</td>
		</tr>
	
	</table>
	<input class="button" type="submit" value="Додати завдання">
</form:form>
<div class="profile"><a href="#" class="linkLikeButton" onclick="javascript: history.go(-1);">Повернутися</a></div> --%>