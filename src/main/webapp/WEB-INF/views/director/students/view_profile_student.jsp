<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                       		<a href="${pageContext.request.contextPath}/director/remove/user${studentModel.id}" class="btn btn-danger">
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
    		<li class="breadcrumb-item"><a href="/director/students/1">УЧНІ</a></li>
    		<li class="breadcrumb-item active" aria-current="page">ДЕТАЛІ</li>
  		</ol>
	</nav>
	<div>
	     <div class="form-inline">
				<div class="form-group">
					<a href="${pageContext.request.contextPath}/director/edit/profile-user${studentModel.id}" class="btn btn-info buttonsAll">
					<span class="glyphicon glyphicon-pencil"></span> Редагувати </a>
				</div>
				<div class="form-group">
					<a href="${pageContext.request.contextPath}/director/edit/password-user${studentModel.id}" class="btn btn-info buttonsAll">
					<span class="glyphicon glyphicon-pencil"></span> Змінити пароль </a>
				</div>
				<div class="form-group">	
					<div class="dropdown">
   						 <button class="btn btn-default dropdown-toggle buttonsAll menu-ul" type="button" id="menu1" data-toggle="dropdown"><span class="glyphicon glyphicon-envelope"></span> Відправити пароль на пошту
    						<span class="caret"></span></button>
    						<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
      							<li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/director/send-password-to-user${studentModel.id}">Учня</a></li>		
            					<li role="presentation" class="divider"></li>
     							<li role="presentation"><a role="menuitem" tabindex="-1" href="${pageContext.request.contextPath}/director/send-password-to-own-email-user${studentModel.id}">Директора</a></li>
   							</ul>
  					</div>							
				</div>												
				<div class="form-group">				
					<a data-toggle="modal" href="#myModal" class="btn btn-primary buttonsAll">
					<span class="glyphicon glyphicon-remove"></span> Видалити </a>
				</div >
				</div>
  </div>
  <hr></hr>	  
  	<div class="form-group row">	
		<div class="col-sm-10">
			<c:choose>
				<c:when test="${not empty studentModel.fotoInBydeCode}">
					<img src="data:image/png;base64, ${studentModel.fotoInBydeCode}" class="smallImg">
				</c:when>
			</c:choose>
		</div>
	</div>	
	 <div class="form-group row">
    	<label class="col-sm-2 control-label">Прізвище:</label>
    		<div class="col-sm-10">
      			<span>${studentModel.lastName}</span>
    		</div>
	</div>
     <div class="form-group row">
    	<label class="col-sm-2 control-label">Ім'я:</label>
    		<div class="col-sm-6">
      			<span>${studentModel.firstName}</span>
    		</div>
	</div>
     <div class="form-group row">
    	<label class="col-sm-2 control-label">По-батькові:</label>
    		<div class="col-sm-10">
      			<span>${studentModel.middleName}</span>
    		</div>
	</div>	
	<div class="form-group row">
    	<label class="col-sm-2 control-label">Клас:</label>
    		<div class="col-sm-10">
      			<span>${studentModel.classStudent}</span>
    		</div>
	</div>	
	<div class="form-group row">
    	<label class="col-sm-2 control-label">Е-мейл:</label>
    		<div class="col-sm-10">
      			<span>${studentModel.email}</span>
    		</div>
	</div>
	<div class="form-group row">
    	<label class="col-sm-2 control-label">Логін:</label>
    		<div class="col-sm-10">
      			<span>${studentModel.login}</span>
    		</div>
	</div>	
	<div class="form-group row">
    	<label class="col-sm-2 control-label">Статус:</label>
    		<div class="col-sm-10">
      			<span>${studentModel.status}</span>
    		</div>
	</div>
	
</div>