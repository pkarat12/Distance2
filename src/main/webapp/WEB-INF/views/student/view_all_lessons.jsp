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
		
	</div>
	<div class="form-group pull-right">
	<div class="form-group">
			<form:form action="${pageContext.request.contextPath}/student/course${courseId}/search" method="get" class="form-inline">
				<input name="search" class="form-control mr-sm-2 input_border" type="search" placeholder="пошук...">
				<button type="submit" class="btn btn-default buttonsAll">
          			<span class="glyphicon glyphicon-search"></span> Пошук 
        		</button>
			</form:form>
	</div>
	<div class="form-group">
			<form:form action="${pageContext.request.contextPath}/student/course${courseId}/1" method="get" class="form-inline">
				<button type="submit" class="btn btn-default buttonsAll">
					<span class="glyphicon glyphicon-remove"></span> Очистити 
					</button>
			</form:form>
	</div>
	</div>
</div>
</div>
<hr>
<div>
<div class="table-responsive" >
		<table class="table table-hover table-bordered table-condensed" >
			<thead class="menu-ul-nothover">
				<tr>
					<td style="width:10%">Номер уроку</td>
					<td style="width:30%">Назва уроку</td>
					<td style="width:30%">Назва завдання</td>
					<td style="width:30%">Тип завдання</td>
				</tr>
			</thead> 
			<tbody>
				<c:forEach items="${lessonsModel}" var="lesson">
					<c:forEach items="${lesson.theoreticalTasks}" var="task1">
						<tr onclick="openWin('${pageContext.request.contextPath}/student/course${courseId}/lesson${lesson.id}/profile-theoretical-task${task1.id}')">
							<td class="borderTable">${lesson.number}</td>
							<td class="borderTable">${lesson.title}</td>
							<td class="borderTable">${task1.title}</td>
							<td class="borderTable">Теоретичне завдання</td>
						</tr>
					</c:forEach>
					<c:forEach items="${lesson.videoYouTubeTasks}" var="task2">
						<tr onclick="openWin('${pageContext.request.contextPath}/student/course${courseId}/lesson${lesson.id}/profile-video-youtube-task${task2.id}')">
							<td class="borderTable">${lesson.number}</td>
							<td class="borderTable">${lesson.title}</td>
							<td class="borderTable">${task2.title}</td>
							<td class="borderTable">Відео завдання</td>
						</tr>
					</c:forEach>
					
					<c:forEach items="${lesson.testTasks}" var="task3">
						<tr onclick="openWin('${pageContext.request.contextPath}/student/course${courseId}/lesson${lesson.id}/profile-test-task${task3.id}')">
							<td class="borderTable">${lesson.number}</td>
							<td class="borderTable">${lesson.title}</td>
							<td class="borderTable">${task3.title}</td>
							<td class="borderTable">Тест</td>
						</tr>
					</c:forEach>
					
				</c:forEach>
			</tbody>
	</table>
</div>
<c:if test="${flag and page.totalPages > 1}">
	<div class="text-center">
		<c:url var="firstUrl" value="${pageContext.request.contextPath}/student/course${courseId}/1" />
		<c:url var="lastUrl" value="${pageContext.request.contextPath}/student/course${courseId}/${page.totalPages}" />
		<c:url var="prevUrl" value="${pageContext.request.contextPath}/student/course${courseId}/${currentIndex-1}" />
		<c:url var="nextUrl" value="${pageContext.request.contextPath}/student/course${courseId}/${currentIndex+1}" />
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
						<c:url var="pageUrl" value="${pageContext.request.contextPath}/student/course${courseId}/${i+1}" />
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
					<c:when test="${currentIndex == page.totalPages}">
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
