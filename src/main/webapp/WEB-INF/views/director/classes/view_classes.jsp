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

<div >
<div class="form-inline justify-content-between">
	<div class="form-group">
		<a href="${pageContext.request.contextPath}/director/add_class" class="btn btn-info buttonsAll">
			<span class="glyphicon glyphicon-plus"></span> Додати </a>
	</div>
	<div class="form-group pull-right">
	<div class="form-group">
			<form:form action="${pageContext.request.contextPath}/director/search/classes/1" method="get" class="form-inline">
				<select name="gradeFilter" class="form-control selectpicker input_border" data-style="btn-primary">
					<option value="${null}">Всі класи</option>
						<c:forEach items="${gradeModel}" var="grade">
							<option value="${grade}">${grade.title}-і класи</option>
						</c:forEach>
				<select>
        		<button type="submit" class="btn btn-default buttonsAll">
          			<span class="glyphicon glyphicon-search"></span> Пошук 
        		</button>
			</form:form>
	</div>
	<div class="form-group">
			<form:form action="${pageContext.request.contextPath}/director/classes/remove-filter" method="get" class="form-inline">
				<button type="submit" class="btn btn-default buttonsAll">
					<span class="glyphicon glyphicon-remove"></span> Очистити 
					</button>
			</form:form>
	</div>
	</div>
	</div>
</div>
<hr></hr>
<div>
	<div class="table-responsive" >
	<table class="table table-hover table-bordered table-condensed" >
	<thead class="menu-ul-nothover">
	<tr>
		<td style="width:30%">Назва класу</td>
		<td style="width:40%">Класний керівник</td>
		<td style="width:30%">Кількість учнів в класі</td>
	</tr>
	</thead> 
	<tbody>
		<c:forEach items="${classesListModel}" var="one">
			<tr onclick="openWin('${pageContext.request.contextPath}/director/profile-class${one.id}')" >
				<td class="borderTable">${one.titleClass}</td>
				<td class="borderTable">${one.classTeacher}</td>
				<td class="borderTable">${one.listStudentsInClass.size()}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>

<div class="text-center">
<c:url var="firstUrl" value="${pageContext.request.contextPath}/director/classes/1" />
<c:url var="lastUrl" value="${pageContext.request.contextPath}/director/classes/${classesList.totalPages}" />
<c:url var="prevUrl" value="${pageContext.request.contextPath}/director/classes/${currentIndex-1}" />
<c:url var="nextUrl" value="${pageContext.request.contextPath}/director/classes/${currentIndex+1}" />
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
		<c:url var="pageUrl" value="${pageContext.request.contextPath}/director/classes/${i+1}" />
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
		<c:when test="${currentIndex == classesList.totalPages}">
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
	</div>