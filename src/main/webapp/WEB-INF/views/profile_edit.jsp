<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.1.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js" ></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<sec:authorize access="isAuthenticated()">
<div class="col-md-8">
<form:form method="POST" action="${pageContext.request.contextPath}/profile/edit"  modelAttribute="userEditModel" enctype="multipart/form-data">
	<nav aria-label="breadcrumb">
  		<ol class="breadcrumb menu-ul-nothover">
   			 <li class="breadcrumb-item"><a href="/profile">МІЙ ПРОФІЛЬ</a></li>
   			 <li class="breadcrumb-item active" aria-current="page">РЕДАГУВАТИ</li>
  		</ol>
	</nav>
      <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_DIRECTOR', 'ROLE_TEACHER')">
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
    		
    		</sec:authorize>   
   		 	<div class="form-group row">
    			<div class="col-md-12">
     			 	<label class="control-label col-sm-2" for="middlename">По-батькові:</label>
      				<form:input path="middleName" maxlength="50" size="500" class="form-control is-invalid" id="middlenname" placeholder="По-батькові:"></form:input>
					<form:errors  path="middleName" cssClass="error"></form:errors>
        		</div>
   			</div> 
   			
   			<div class="form-group row ">
    			<div class="col-md-12">
     				<label class="control-label col-sm-2" for="email">E-mail:</label>
      				<form:input path="email" maxlength="50" size="500" class="form-control is-invalid" id="email" placeholder="e-mail"></form:input>
				<form:errors  path="email" cssClass="error"></form:errors>
    		 </div>
			</div>
   			
   			<div class="form-group row ">
    			<div class="col-md-12">
     				<label class="control-label col-sm-2" for="birthDate">Дата народження:</label>
      				<form:input path="birthDate" maxlength="50" size="500" class="form-control is-invalid" id="birthDate" placeholder="Дата народження"></form:input>
					<form:errors  path="birthDate" cssClass="error"></form:errors>
     			</div>
			</div>
    			<div class="form-group row">
					<div class="form-inline">
						<div class="form-group">
							<label class="col-sm-5 control-label">Файл:</label>
								<div class="col-sm-12">
									<span>${userEditModel.nameFoto}</span>
    							</div>
    					</div>
    					<div class="form-group">
							<a href="${pageContext.request.contextPath}/remove/foto" onclick="return deleteFoto()" class="btn btn-info buttonsAll">
							<span class="glyphicon glyphicon-remove"></span></a>
						</div>
					</div>
    			</div>
				<div class="form-group">
             		<label class="btn btn-primary buttonsAll" for="my-file-selector">
					<form:input path="file" id="my-file-selector" type="file" style="display:none" 
                				 onchange="$('#upload-file-info').html(this.files[0].name)" accept="image/jpeg,image/png,image/gif,image/bmp,image/jpg" />Вибрати файл...
             		</label>   
             		<span  id="upload-file-info"></span>   
					<form:errors path="file" cssClass="error"></form:errors>
				</div>
		    	
     	<div class="form-group row">
     		<div class="col-md-6 mb-3">
  				<button class="btn btn-primary buttonsAll" type="submit"><span class="glyphicon glyphicon-ok"></span> Зберегти</button>
  			</div>
  		</div>
	</form:form>
</div>
</sec:authorize>