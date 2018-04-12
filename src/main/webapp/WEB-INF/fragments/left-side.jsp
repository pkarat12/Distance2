<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/javascript.js" ></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

	<sec:authorize access="!isAuthenticated()"> <!-- Якщо не залогінений -->
		<div class="">
			<div class="list-group">
			<a href="/" class="list-group-item list-group-item-action menu-ul">ГОЛОВНА</a>
			<a href="#" class="list-group-item list-group-item-action menu-ul ">НАВЧАЛЬНІ ПРОГРАМИ</a>
			<a href="#" class="list-group-item list-group-item-action menu-ul">ІНФОРМАЦІЯ</a>
			<a href="#" class="list-group-item list-group-item-action menu-ul">ДОПОМОГА</a>
			<a href="#" class="list-group-item list-group-item-action menu-ul">КОНТАКТИ</a>
			</div>
		</div>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div class="list-group">
				<a href="" class="list-group-item list-group-item-action">Директора</a></li>
				<a href="${pageContext.request.contextPath}/director" class="list-group-item list-group-item-action menu-ul">Увійти як директор</a>
				<a href="${pageContext.request.contextPath}/teacher" class="list-group-item list-group-item-action menu-ul">Увійти як вчитель</a>
				<a href="${pageContext.request.contextPath}/student" class="list-group-item list-group-item-action menu-ul">Увійти як учень</a>
				<a href="${pageContext.request.contextPath}/director/news/1" class="list-group-item list-group-item-action menu-ul">Новини</a>
			</div>
		</sec:authorize>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<sec:authorize access="hasRole('ROLE_DIRECTOR')">
				<div class="list-group">
					<a href="${pageContext.request.contextPath}/director/teachers/1" class="list-group-item list-group-item-action menu-ul"> ВЧИТЕЛІ</a>
					<a href="${pageContext.request.contextPath}/director/classes/1" class="list-group-item list-group-item-action menu-ul"> КЛАСИ</a>
					<a href="${pageContext.request.contextPath}/director/students/1" class="list-group-item list-group-item-action menu-ul"> УЧНІ</a>
					<a href="${pageContext.request.contextPath}/director/news/1" class="list-group-item list-group-item-action menu-ul"> НОВИНИ</a>
				</div>
		</sec:authorize>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<sec:authorize access="hasRole('ROLE_TEACHER')">
				<div class="list-group">
					<a href="#" onclick="toggle(typeTasks)" class="list-group-item list-group-item-action menu-ul"> ЗАВДАННЯ</a>
						<div id = "typeTasks" style="display: none;">
							<div class="list-group" style="margin-bottom: 0;">
								<a href="${pageContext.request.contextPath}/teacher/theoretical-tasks/1" class="list-group-item sub-menu-ul">Теоретичний матеріал</a>
								<a href="${pageContext.request.contextPath}/teacher/video-youtube-tasks/1" class="list-group-item sub-menu-ul">Відео з Youtube</a>
								<%-- <a href="${pageContext.request.contextPath}/teacher/test-tasks/1" class="list-group-item sub-menu-ul">Тести</a> --%>
							</div>
						</div>
					<a href="${pageContext.request.contextPath}/teacher/courses/1" class="list-group-item list-group-item-action menu-ul"> КУРСИ</a>
					<a href="${pageContext.request.contextPath}/teacher/groups/1" class="list-group-item list-group-item-action menu-ul"> ГРУПИ</a>
					<a href="${pageContext.request.contextPath}/teacher/news/1" class="list-group-item list-group-item-action menu-ul"> НОВИНИ</a>
				</div>
		</sec:authorize>
	</sec:authorize>
	
	<sec:authorize access="isAuthenticated()">
		<sec:authorize access="hasRole('ROLE_STUDENT')">
				<div class="list-group">
					<c:choose>
						<c:when test="${myCoursesModel == null}">
							<a href="" class="list-group-item list-group-item-action menu-ul"> Нема доступних курсів</a>
						</c:when>
						<c:otherwise>
							<c:forEach items="${myCoursesModel}" var="course">
								<a href="/student/course${course.idCourses}/1" class="list-group-item list-group-item-action menu-ul">${course.subject}<span class="badge badge-light">${course.amountTask}</span></a>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					
				</div>
		</sec:authorize>
	</sec:authorize>
