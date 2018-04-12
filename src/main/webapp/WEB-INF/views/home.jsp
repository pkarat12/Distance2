<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js" ></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

<sec:authorize access="!isAuthenticated()">

<div class="col-md-5">
<img alt="" src="${pageContext.request.contextPath}/resources/img/5.jpg" style="width: 45vw;">
</div>
<div class="col-md-3 pull-right">
		<div class="">
		
    		<div class="panel panel-default login_border">
			  	<div class="login_title">
			    	<h3 class="panel-title ">ВХІД</h3>
			 	</div>
			  		<div class="panel-body">
			    		<form:form action="/login"  method="POST">

			    	  		<div class="form-group">
			    		    	<input class="form-control input_border" placeholder="логін" name="login" type="text">
			    			</div>
			    			<div class="form-group">
			    				<input class="form-control input_border" placeholder="пароль" name="password" type="password" value="">
			    			</div>
			    			<div class="checkbox">
			    	    		<label>
			    	    			<input name="rememberMe" type="checkbox">запам'ятати мене
			    	    		</label>
			    	    	</div>
			    				<input class="btn btn-lg btn-block login_btn" type="submit" value="ВХІД">

			      		</form:form>
			      	<c:if test="${param.fail}">
						<p class="failLogin">
							Ви вказали невірний логін або пароль
						</p>
					</c:if>
			    </div>
			</div>
			</div>
			</div>
	</sec:authorize>
	<sec:authorize access="isAuthenticated()">
	<div class="col-md-1">
		<img alt="" src="${pageContext.request.contextPath}/resources/img/5.jpg">
	</div>
	</sec:authorize>
	