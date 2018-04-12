<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.1.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js" ></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<sec:authorize access="isAuthenticated()">
<div class="col-md-8">
	<nav aria-label="breadcrumb">
  		<ol class="breadcrumb menu-ul-nothover">
    		<li class="breadcrumb-item active" aria-current="page">МІЙ ПРОФІЛЬ</li>
  		</ol>
	</nav>
	<div>
	     <div class="form-inline">
				<div class="form-group">
					<a href="${pageContext.request.contextPath}/profile/edit" class="btn btn-info buttonsAll">
					<span class="glyphicon glyphicon-pencil"></span> Редагувати </a>
				</div>
				<div class="form-group">
					<a href="${pageContext.request.contextPath}/profile/edit-password" class="btn btn-info buttonsAll">
					<span class="glyphicon glyphicon-pencil"></span> Змінити пароль </a>
				</div>
				<div class="form-group">
					<a href="${pageContext.request.contextPath}/profile/send-email" class="btn btn-info buttonsAll">
					<span class="glyphicon glyphicon-pencil"></span> Відправити пароль на пошту </a>
				</div>											
  </div>
  <hr></hr>	  
  	<div class="form-group row">	
		<div class="col-sm-10">
			<c:choose>
				<c:when test="${not empty profileViewModel.encodedToByteByNameFoto}">
					<img src="data:image/png;base64, ${profileViewModel.encodedToByteByNameFoto}" class="smallImg">
				</c:when>
			</c:choose>
		</div>
	</div>	
	 <div class="form-group row">
    	<label class="col-sm-2 control-label">Прізвище:</label>
    		<div class="col-sm-10">
      			<span>${profileViewModel.lastName}</span>
    		</div>
	</div>
     <div class="form-group row">
    	<label class="col-sm-2 control-label">Ім'я:</label>
    		<div class="col-sm-6">
      			<span>${profileViewModel.firstName}</span>
    		</div>
	</div>
     <div class="form-group row">
    	<label class="col-sm-2 control-label">По-батькові:</label>
    		<div class="col-sm-10">
      			<span>${profileViewModel.middleName}</span>
    		</div>
	</div>
	
	<sec:authorize access="hasRole('ROLE_TEACHER')">	
		<div class="form-group row">
    		<label class="col-sm-2 control-label">Предмет:</label>
    			<div class="col-sm-10">
      				<span>${teacherModel.subject}</span>
    			</div>
		</div>	
		<div class="form-group row">
			<label class="col-sm-2 control-label">Класне Керівництво:</label>	
				<div class="col-sm-10">
					<c:choose>
						<c:when test="${profileViewModel.classTeach != null}">${profileViewModel.classTeach}</c:when>
						<c:otherwise>----</c:otherwise>
					</c:choose>
				</div>
		</div>
	</sec:authorize>
	
	<sec:authorize access="hasRole('ROLE_STUDENT')">
		<div class="form-group row">
    		<label class="col-sm-2 control-label">Клас:</label>
    			<div class="col-sm-10">
      				<span>${profileViewModel. classInStudy}</span>
    			</div>
		</div>	
		<div class="form-group row">
    		<label class="col-sm-2 control-label">Класний керівник:</label>
    			<div class="col-sm-10">
      				<span>${profileViewModel.classTeacherFullName}</span>
    			</div>
		</div>	
	</sec:authorize>
	
	<div class="form-group row">
    	<label class="col-sm-2 control-label">Е-мейл:</label>
    		<div class="col-sm-10">
      			<span>${profileViewModel.email}</span>
    		</div>
	</div>
	<div class="form-group row">
    	<label class="col-sm-2 control-label">Логін:</label>
    		<div class="col-sm-10">
      			<span>${profileViewModel.login}</span>
    		</div>
	</div>	
	<div class="form-group row">
    	<label class="col-sm-2 control-label">Дата народження:</label>
    		<div class="col-sm-10">
      			<span>${profileViewModel.birthDate}</span>
    		</div>
	</div>
	<div class="form-group row">
    	<label class="col-sm-2 control-label">Школа:</label>
    		<div class="col-sm-10">
      			<span>${profileViewModel.numberSchool}</span>
    		</div>
	</div>
	
</div>
</div>
</sec:authorize>