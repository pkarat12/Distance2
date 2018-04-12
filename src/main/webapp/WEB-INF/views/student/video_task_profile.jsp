<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
    			<li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/student/course${courseId}/1">${subjectModel}</a></li>
   			 	<li class="breadcrumb-item active" aria-current="page">${fn:toUpperCase(taskModel.title)}</li>
  		</ol>
	</nav>
  	
	 <div class="form-group row">
    	<label class="col-sm-2 control-label">Назва:</label>
    		<div class="col-sm-10">
      			<span>${taskModel.title}</span>
    		</div>
	</div>
     <div class="form-group row">
    	<label class="col-sm-2 control-label">Опис:</label>
    		<div class="col-sm-6">
      			<span>${taskModel.description}</span>
    		</div>
	</div>
	<div class="form-group row">	
		<div class="col-sm-10">
				<c:choose>
    			<c:when test="${taskModel.link != null}">
    				<iframe width="560" height="315" src="https://www.youtube.com/embed/${taskModel.link}?rel=0" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
    			</c:when>
    			<c:otherwise>
    				<span>Посилання з YouTube невірне</span>
    			</c:otherwise>
    		</c:choose>
		</div>
	</div>
</div>

