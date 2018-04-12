 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.1.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js" ></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>


<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/">ДИСТАНЦІЙНЕ НАВЧАННЯ ШКОЛА №32</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
       <li><a href="/"><span class="glyphicon glyphicon-dashboard" aria-hidden="true"></span> ГОЛОВНА</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span> НОВИНИ</a></li>
            <li><a href="#"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> ВЧИТЕЛІ</a></li>
			<li><a href="#"><span class="glyphicon glyphicon-envelope" aria-hidden="true"></span> КОНТАКТИ</a></li>
      </ul>
      <sec:authorize access="isAuthenticated()">
      <sec:authentication property="principal.username" var="user"/>
      <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${user}<span class="caret"></span></a>
          <ul class="dropdown-menu">

            <li><a href="/profile"> ПРОФІЛЬ</a></li>
            <li role="separator" class="divider"></li>
			<li>
					<a>
						<form:form action="/logout" method="post">
							<input type="submit" class= "btn btn-lg-btn-default colorNone" value="ВИХІД">
						</form:form>
					</a>
			</li>
          </ul>
        </li>
      </ul>
      </sec:authorize>  
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>

