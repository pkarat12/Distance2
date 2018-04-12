<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js"></script>
<link type="text/css"
	href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
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
                       		<a
								href="${pageContext.request.contextPath}/teacher/group/remove/profile${groupModel.idGroup}" class="btn btn-danger"> 
                    		<span class="glyphicon glyphicon-ok"></span> Видалити </a>
                    	</div>
                    </div>	
               </div>
           </div>
       </div>
    </div>



<div class="col-md-8">
	<nav aria-label="breadcrumb">
		<ol class="breadcrumb menu-ul-nothover">
			<li class="breadcrumb-item"><a href="/teacher/groups/1">ГРУПИ</a></li>
			<li class="breadcrumb-item active" aria-current="page">ДЕТАЛІ</li>
		</ol>
	</nav>
	<div>
		<div class="form-inline">
			<div class="form-group">
				<a
					href="${pageContext.request.contextPath}/teacher/group/edit/profile${groupModel.idGroup}"
					class="btn btn-info buttonsAll"> <span
					class="glyphicon glyphicon-pencil"></span> Редагувати
				</a>
			</div>
			<div class="form-group">
				<a data-toggle="modal" href="#myModal" class="btn btn-primary buttonsAll">
					<span class="glyphicon glyphicon-remove"></span> Видалити
				</a>
			</div>
		</div>
		<hr></hr>
	</div>

	<div class="form-group row">
		<label class="col-sm-2 control-label">Назва:</label>
		<div class="col-sm-10">
			<span>${groupModel.title}</span>
		</div>
	</div>
	<div class="form-group row">
		<label class="col-sm-2 control-label">Курс:</label>
		<div class="col-sm-10">
			<span>${groupModel.course.title}</span>
		</div>
	</div>
	<div>
		<div class="form-inline justify-content-between">
			<h5>УЧНІ</h5>
			<div class="form-group">
				<a
					href="${pageContext.request.contextPath}/teacher/group/edit/profile${groupModel.idGroup}/add-students/1"
					class="btn btn-info buttonsAll"> <span
					class="glyphicon glyphicon-plus"></span> Додати
				</a>
			</div>
			<div class="form-group">
				<a
					href="${pageContext.request.contextPath}/teacher/group/edit/profile${groupModel.idGroup}/remove-students/1"
					class="btn btn-info buttonsAll"> <span
					class="glyphicon glyphicon-minus"></span> Видалити
				</a>
			</div>
		</div>
	</div>
	<hr></hr>
	<div>
		<div class="table-responsive">
			<table class="table table-hover table-bordered table-condensed">
				<thead class="menu-ul-nothover">
					<tr>
						<td style="width: 10%">Прізвище</td>
						<td style="width: 20%">Ім'я</td>
						<td style="width: 40%">Клас</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${groupModel.students}" var="one">
						<tr>
							<td class="borderTable">${one.lastName}</td>
							<td class="borderTable">${one.firstName}</td>
							<td class="borderTable">${one.classStudent.toString()}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
<c:if test="${pageModel.totalPages > 1}">
		<div class="text-center">
			<c:url var="firstUrl" value="${pageContext.request.contextPath}/teacher/group/profile${groupModel.idGroup}/1" />
			<c:url var="lastUrl" value="${pageContext.request.contextPath}/teacher/group/profile${groupModel.idGroup}/${pageModel.totalPages}" />
			<c:url var="prevUrl" value="${pageContext.request.contextPath}/teacher/group/profile${groupModel.idGroup}/${currentIndex-1}" />
			<c:url var="nextUrl" value="${pageContext.request.contextPath}/teacher/group/profile${groupModel.idGroup}/${currentIndex+1}" />
			<ul class="pagination pagination-sm">
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
					<c:url var="pageUrl"
						value="${pageContext.request.contextPath}/teacher/group${groupModel.idGroup}/${i+1}" />
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
						<li class="page-item"><a class="menu-ul" href="${lastUrl}">&gt;&gt;</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		</c:if>
	</div>
</div>
