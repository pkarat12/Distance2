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
	<form:form method="POST" action="${pageContext.request.contextPath}/teacher/group/edit/profile${groupAddRequest.idGroup}/remove-students/search/1">
	<nav aria-label="breadcrumb">
  		<ol class="breadcrumb menu-ul-nothover">
   			 <li class="breadcrumb-item"><a href="/teacher/groups/1">ГРУПИ</a></li>
   			 <li class="breadcrumb-item"><a href="/teacher/group/profile${groupAddRequest.idGroup}/1">ДЕТАЛІ</a></li>
   			 <li class="breadcrumb-item active" aria-current="page">ДОДАТИ УЧНІВ</li>
  		</ol>
	</nav>
	</form:form>
</div>
<div class="form-inline justify-content-between">
		<div class="form-group pull-right">
			<div class="form-group">
					<form:form action="${pageContext.request.contextPath}/teacher/group/edit/profile${groupAddRequest.idGroup}/remove-students/search/1" method="post" class="form-inline" modelAttribute="filterModel">
					<div class="form-group">
						<form:select class="form-control selectpicker input_border" data-style="btn-primary" path="classToStudent">
							<form:option  value="">Оберіть клас</form:option>
								<c:forEach items="${classesListModel}" var="clas">
									<form:option value="${clas}">${clas.toString()}</form:option>
								</c:forEach>
						</form:select>
					</div>
					<div class="form-group">
						<form:input path="search" class="form-control mr-sm-2 input_border" type="search" placeholder="пошук..."></form:input>
						<button type="submit" class="btn btn-default buttonsAll">
          					<span class="glyphicon glyphicon-search"></span> Пошук 
        				</button>
					</div>
					</form:form>
			</div>
			<div class="form-group">
				<form:form action="${pageContext.request.contextPath}/teacher/group/edit/profile${groupAddRequest.idGroup}/remove-students/1" method="get" class="form-inline">
					<button type="submit" class="btn btn-default buttonsAll">
						<span class="glyphicon glyphicon-remove"></span> Очистити 
					</button>
				</form:form>
			</div>
		</div>
	</div>

<div>
<form:form method="POST" action="${pageContext.request.contextPath}/teacher/group/edit/profile${groupAddRequest.idGroup}/remove-students/${currentIndex}" modelAttribute="groupAddRequest">
		<div class="form-group">
			<button class="btn btn-primary buttonsAll" type="submit">
			<span class="glyphicon glyphicon-minus"></span> Видалити вибраних </button>
		</div>
	<div class="table-responsive" >
	<table class="table table-hover table-bordered table-condensed" >
	<thead class="menu-ul-nothover">
		<tr>
						<td style="width: 5%"></td>
						<td style="width: 30%">Прізвище</td>
						<td style="width: 25%">Ім'я</td>
						<td style="width: 30%">По-батькові</td>
						<td style="width: 10%">Клас</td>
		</tr>
	</thead> 
	<tbody>
		<c:forEach items="${studentsList}" var="student">
			<tr>
				<td class="borderTable"><form:checkbox path="newStudents" value="${student}"/></td>
				<td class="borderTable">${student.lastName}</td>
				<td class="borderTable">${student.firstName}</td>
				<td class="borderTable">${student.middleName}</td>
				<td class="borderTable">${student.classStudent.toString()}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
</form:form>
<c:if test="${flag and pageModel.totalPages > 1}">
<div class="text-center">
<c:url var="firstUrl" value="${pageContext.request.contextPath}/teacher/group/edit/profile${groupAddRequest.idGroup}/add-students/1" />
<c:url var="lastUrl" value="${pageContext.request.contextPath}/teacher/group/edit/profile${groupAddRequest.idGroup}/add-students/${pageModel.totalPages}" />
<c:url var="prevUrl" value="${pageContext.request.contextPath}/teacher/group/edit/profile${groupAddRequest.idGroup}/add-students/${currentIndex-1}" />
<c:url var="nextUrl" value="${pageContext.request.contextPath}/teacher/group/edit/profile${groupAddRequest.idGroup}/add-students/${currentIndex+1}" />
<ul class = "pagination pagination-sm">
	<c:choose>
		<c:when test="${currentIndex == 1}">
				<li class="page-item"><a class="menu-ul" href="#">&lt;&lt;</a></li>
				<li class="page-item"><a class="menu-ul" href="#">&lt;</a></li>
				<li class="page-item"><a class="menu-ul" href="${firstUrl}">1</a></li>
		</c:when>
		<c:otherwise>
				<li class="page-item"><a class="menu-ul" href="${firstUrl}">&lt;&lt;</a></li>
				<li class="page-item"><a class="menu-ul" href="${prevUrl}">&lt;</a></li>
		</c:otherwise>
	</c:choose>
	<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
		<c:url var="pageUrl" value="${pageContext.request.contextPath}/teacher/group/edit/profile${groupAddRequest.idGroup}/add-students/${i+1}" />
		<c:choose>
			<c:when test="${i+1 == currentIndex}">
				<li class="page-item"><a class="menu-ul" href="${pageUrl}"><c:out value="${i+1}"></c:out></a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="menu-ul" href="${pageUrl}"><c:out value="${i+1}"></c:out></a></li>
			</c:otherwise>
		</c:choose>
	</c:forEach>

	<c:choose>
		<c:when test="${currentIndex == pageModel.totalPages}">
			<li class="page-item"><a class="menu-ul" href="#">&gt;</a></li>
			<li class="page-item"><a class="menu-ul" href="#">&gt;&gt;</a></li>
		</c:when>
		<c:otherwise>
			<li class="page-item"><a class="menu-ul" href="${nextUrl}">&gt;</a></li>
			<li class="page-item"><a class="menu-ul"  href="${lastUrl}">&gt;&gt;</a></li>
		</c:otherwise>
	</c:choose>
</ul>
</div>
</c:if>
</div>