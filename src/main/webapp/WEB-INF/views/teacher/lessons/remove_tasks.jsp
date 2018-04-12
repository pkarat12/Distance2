<%@ page language="java" contentType="application/pdf; charset=UTF-8"
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
<form:form method="POST" action="${pageContext.request.contextPath}/teacher/lesson/profile${lessonAddTasksRequest.idLesson}/remove-tasks" modelAttribute="lessonAddTasksRequest">
	<nav aria-label="breadcrumb">
  		<ol class="breadcrumb menu-ul-nothover">
   			 <li class="breadcrumb-item"><a href="/teacher/courses/1">КУРСИ</a></li>
   			 <li class="breadcrumb-item"><a href="/teacher/course/profile${lessonAddTasksRequest.course.id}/1">ДЕТАЛІ КУРСУ</a></li>
   			 <li class="breadcrumb-item"><a href="/teacher/lesson/profile${lessonAddTasksRequest.idLesson}">ДЕТАЛІ УРОКУ</a></li>
   			 <li class="breadcrumb-item active" aria-current="page">ВИДАЛИТИ ЗАВДАННЯ</li>
  		</ol>
	</nav>
		</form:form>

<form:form method="POST" action="${pageContext.request.contextPath}/teacher/lesson/profile${lessonAddTasksRequest.idLesson}/remove-tasks" modelAttribute="lessonAddTasksRequest">
		<div class="form-group">
			<button class="btn btn-primary buttonsAll" type="submit">
			<span class="glyphicon glyphicon-minus"></span> Видалити вибрані </button>
		</div>
		<div class="table-responsive" >
			<table class="table table-hover table-bordered table-condensed" >
				<thead class="menu-ul-nothover">
					<tr>
						<td style="width: 5%"></td>
						<td style="width: 35%">Назва</td>
						<td style="width: 40%">Опис</td>
						<td style="width: 20%">Тип</td>
					</tr>
				</thead> 
			<tbody>
				<c:forEach items="${theoreticalTasksModel}" var="task">
					<tr>
						<td class="borderTable"><form:checkbox path="newTheoreticalTasks" value="${task}"/></td>
						<td class="borderTable">${task.title}</td>
						<td class="borderTable">${task.description}</td>
						<td class="borderTable">Теоретичний Матеріал</td>
					</tr>
				</c:forEach>
				<c:forEach items="${videoTasksModel}" var="task2">
					<tr>
						<td class="borderTable"><form:checkbox path="newVideoYouTubeTasks" value="${task2}"/></td>
						<td class="borderTable">${task2.title}</td>
						<td class="borderTable">${task2.description}</td>
						<td class="borderTable">Youtube Відео</td>
					</tr>
				</c:forEach>																
			</tbody>
		</table>
	</div>
</form:form>
</div>