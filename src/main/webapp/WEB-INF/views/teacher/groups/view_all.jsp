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
		<a href="${pageContext.request.contextPath}/teacher/group/add" class="btn btn-info buttonsAll">
			<span class="glyphicon glyphicon-plus"></span> Додати </a>
	</div>
	<div class="form-group pull-right">
	<div class="form-group">
			<form:form action="${pageContext.request.contextPath}/teacher/groups/search/1" method="get" class="form-inline" modelAttribute="filterModel">
				<form:input path="title" class="form-control mr-sm-2 input_border" type="search" placeholder="пошук..."></form:input>
				<button type="submit" class="btn btn-default buttonsAll">
          			<span class="glyphicon glyphicon-search"></span> Пошук 
        		</button>
			</form:form>
	</div>
	<div class="form-group">
			<form:form action="${pageContext.request.contextPath}/teacher/groups/1" method="get" class="form-inline">
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
		<td style="width:40%">Назва</td>
		<td style="width:60%">Курс</td>
	</tr>
	</thead> 
	<tbody>
		<c:forEach items="${groupList}" var="one">
			<tr onclick="openWin('${pageContext.request.contextPath}/teacher/group/profile${one.idGroup}/1')" >
				<td class="borderTable">${one.title}</td>
				<td class="borderTable">${one.course.title}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<c:if test="${flag and pageModel.totalPages > 1}">
<div class="text-center">
<c:url var="firstUrl" value="${pageContext.request.contextPath}/teacher/groups/1" />
<c:url var="lastUrl" value="${pageContext.request.contextPath}/teacher/groups/${pageModel.totalPages}" />
<c:url var="prevUrl" value="${pageContext.request.contextPath}/teacher/groups/${currentIndex-1}" />
<c:url var="nextUrl" value="${pageContext.request.contextPath}/teacher/groups/${currentIndex+1}" />
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
		<c:url var="pageUrl" value="${pageContext.request.contextPath}/teacher/groups/${i+1}" />
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