<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.1.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js" ></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<sec:authorize access="isAuthenticated()">
<form:form method="POST" action="${pageContext.request.contextPath}/profile/edit-password"  modelAttribute="passwordEditModel">
<div class="col-md-8">
	<nav aria-label="breadcrumb">
  		<ol class="breadcrumb menu-ul-nothover">
   			 <li class="breadcrumb-item"><a href="/profile">МІЙ ПРОФІЛЬ</a></li>
   			 <li class="breadcrumb-item active" aria-current="page">ЗМІНИТИ ПАРОЛЬ</li>
  		</ol>
	</nav>
    	
    	<div class="form-group row required">
    		<div class="col-md-12">
     			 <label class="control-label col-sm-5" for="newpassword">Поточний пароль:</label>
      				<form:password path="entered" maxlength="30" size="500" class="form-control is-invalid" id="newpassword" placeholder="Новий пароль"></form:password>
					<c:if test="${errorPass != null}">
						<span class="error">${errorPass}</span>
					</c:if>
        	</div>
        </div>
    	
    	<div class="form-group row required">
    		<div class="col-md-12">
     			 <label class="control-label col-sm-5" for="newpassword">Новий пароль:</label>
      				<form:password path="newPassword" maxlength="30" size="500" class="form-control is-invalid" id="newpassword" placeholder="Новий пароль"></form:password>
					<form:errors  path="newPassword" cssClass="error"></form:errors>
        	</div>
        </div>
    	<div class="form-group row required">
    		<div class="col-md-12">
     			 <label class="control-label col-sm-5" for="confirmPassword">Повторити новий пароль:</label>
      				<form:password path="confirmPassword" maxlength="30" size="500" class="form-control is-invalid" id="confirmPassword" placeholder="Новий пароль"></form:password>
					<form:errors  path="*" cssClass="error"></form:errors>
        	</div>
        </div>
     	<div class="form-group row">
     		<div class="col-md-6 mb-3">
  				<button class="btn btn-primary buttonsAll" type="submit"><span class="glyphicon glyphicon-ok"></span> Зберегти</button>
  			</div>
  		</div>
  </div>
</form:form>
</sec:authorize>