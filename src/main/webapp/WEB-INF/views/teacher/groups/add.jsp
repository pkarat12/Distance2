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
    		<li class="breadcrumb-item"><a href="/teacher/groups/1">ГРУПИ</a></li>
    		<li class="breadcrumb-item active" aria-current="page">ДОДАТИ</li>
  		</ol>
	</nav>
<form:form method="POST" action="${pageContext.request.contextPath}/teacher/group/add" modelAttribute="addGroupRequest">
    	<div class="form-group row required">
    		<div class="col-md-12">
     			 <label class="control-label col-sm-2" for="title">Назва:</label>
      				<form:input path="title" maxlength="50" size="500" class="form-control is-invalid" id="title" placeholder="Назва"></form:input>
					<form:errors  path="title" cssClass="error"></form:errors>
        	</div>
        </div>          
    	<div class="form-group row required">
    		<div class="col-md-12">
     		 	<label class="control-label col-sm-2" for="course">Курс:</label>
					<form:select class="form-control selectpicker input_border" data-style="btn-primary" path="course">
						<form:option value="${null}">Виберіть курс</form:option>
							<c:forEach items="${courseModel}" var="course">
								<form:option value="${course}">${course.title}</form:option>
							</c:forEach>
					</form:select>
					<form:errors  path="course" cssClass="error"></form:errors>
        	</div>
   		</div>
     	<div class="form-group row">
     		<div class="col-md-6 mb-3">
  				<button class="btn btn-primary buttonsAll" type="submit"><span class="glyphicon glyphicon-plus"></span> Додати</button>
  			</div>
  		</div>
</form:form>
</div>