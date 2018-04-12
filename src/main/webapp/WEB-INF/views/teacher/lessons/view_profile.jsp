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


	<div id="myModal" class="modal" role="dialog" data-backdrop="false">
    	<div class="modal-dialog">
            <div class="modal-content">
               	<div class="modal-body">
                    <p><span class="glyphicon glyphicon-exclamation-sign"></span> Ви дійсно впевнені що хочете видалити?</p>
                </div>
                <div class="modal-footer">
                	<div class="form-inline">
                    	<div class="form-group">
                       		<button class="btn btn-info buttonsAll" data-dismiss="modal" data-backdrop="false"><span class="glyphicon glyphicon-remove"></span> Відмінити</button>
                    	</div>
                      	<div class="form-group">
                       		<a href="${pageContext.request.contextPath}/teacher/lesson/remove/profile${lessonProfileModel.idLesson}" class="btn btn-danger"> 
                    		<span class="glyphicon glyphicon-ok"></span> Видалити </a>
                    	</div>
                    </div>	
               </div>
           </div>
       </div>
    </div>



<div class="col-md-8">
<form:form method="POST" action="${pageContext.request.contextPath}/teacher/course/profile${addLessonRequest.course.id}/lesson/add" modelAttribute="addLessonRequest">
		<nav aria-label="breadcrumb">
  			<ol class="breadcrumb menu-ul-nothover">
   			 	<li class="breadcrumb-item"><a href="/teacher/courses/1">КУРСИ</a></li>
   			 	<li class="breadcrumb-item"><a href="/teacher/course/profile${addLessonRequest.course.id}/1">ДЕТАЛІ КУРСУ</a></li>
   			 	<li class="breadcrumb-item active" aria-current="page">ДЕТАЛІ УРОКУ</li>
  			</ol>
		</nav>
		</form:form>
<div>
	     <div class="form-inline">
				<div class="form-group">
					<a href="${pageContext.request.contextPath}/teacher/lesson/edit/profile${lessonProfileModel.idLesson}" class="btn btn-info buttonsAll">
					<span class="glyphicon glyphicon-pencil"></span> Редагувати </a>
				</div>
				<div class="form-group">
					<a href="${pageContext.request.contextPath}/teacher/lesson/profile${lessonProfileModel.idLesson}/active" class="btn btn-info buttonsAll">
					<span class="glyphicon glyphicon-pencil"></span> Змінити Статус </a>
				</div>				
				<div class="form-group">
					<a data-toggle="modal" href="#myModal" class="btn btn-primary buttonsAll">
					<span class="glyphicon glyphicon-remove"></span> Видалити Урок </a>
				</div>
				<div class="form-group">
					<a href="${pageContext.request.contextPath}/teacher/lesson/profile${lessonProfileModel.idLesson}/add-tasks" class="btn btn-info buttonsAll">
					<span class="glyphicon glyphicon-plus"></span> Додати Завдання </a>
				</div>
				<div class="form-group">
					<a href="${pageContext.request.contextPath}/teacher/lesson/profile${lessonProfileModel.idLesson}/remove-tasks" class="btn btn-info buttonsAll">
					<span class="glyphicon glyphicon-remove"></span> Видалити Завдання </a>
				</div>
  		</div>
  		<hr></hr>
  	</div>
  	
  	<div class="form-group row">
    	<label class="col-sm-2 control-label">Номер:</label>
    		<div class="col-sm-10">
      			<span>${lessonProfileModel.number}</span>
    		</div>
	</div>
	 <div class="form-group row">
    	<label class="col-sm-2 control-label">Назва:</label>
    		<div class="col-sm-10">
      			<span>${lessonProfileModel.title}</span>
    		</div>
	</div>
     <div class="form-group row">
    	<label class="col-sm-2 control-label">Опис:</label>
    		<div class="col-sm-6">
      			<span>${lessonProfileModel.description}</span>
    		</div>
    </div>
     <div class="form-group row">
    	<label class="col-sm-2 control-label">Статус:</label>
    		<div class="col-sm-10">
    			<c:if test="${lessonProfileModel.visibility == true}">
						<span>Доступний</span>
				</c:if>
				<c:if test="${lessonProfileModel.visibility == false}">
						<span>Недоступний</span>
				</c:if>
    		</div>
	</div>	
    <div class="form-group row">
    	<label class="col-sm-2 control-label">Теоретичні матеріали::</label>
    		<div class="col-sm-10">
    			<c:choose>
						<c:when test="${empty lessonProfileModel.theoreticalTasks}">
							<span>----</span>
						</c:when>
						<c:otherwise>
							<c:forEach items="${lessonProfileModel.theoreticalTasks}" var="task1">
								${task1.title}
								<br>
							</c:forEach>
						</c:otherwise>					
				</c:choose>
    		</div>
	</div>	
    <div class="form-group row">
    	<label class="col-sm-2 control-label">Відео матеріали:</label>
    	    <div class="col-sm-10">
					<c:choose>
						<c:when test="${empty lessonProfileModel.videoYouTubeTasks}">
							<span>----</span>
						</c:when>
						<c:otherwise>
							<c:forEach items="${lessonProfileModel.videoYouTubeTasks}" var="task2">
								${task2.title}
								<br>
							</c:forEach>
						</c:otherwise>					
					</c:choose>
    		</div>
	</div>	
    <div class="form-group row">
    	<label class="col-sm-2 control-label">Тести:</label>
    	       	<div class="col-sm-10">
					<c:choose>
						<c:when test="${empty lessonProfileModel.testTasks}">
							<span>----</span>
						</c:when>
						<c:otherwise>
							<c:forEach items="${lessonProfileModel.testTasks}" var="task3">
								${task3.title}
								<br>
							</c:forEach>
						</c:otherwise>					
					</c:choose>
				</div>
    </div>
    </div>