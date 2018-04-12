<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.1.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js" ></script>
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<div class="col-md-8">
<nav aria-label="breadcrumb">
  <ol class="breadcrumb menu-ul-nothover">
    <li class="breadcrumb-item"><a href="/director/news/1">НОВИНИ</a></li>
    <li class="breadcrumb-item active" aria-current="page">ДОДАТИ НОВИНУ</li>
  </ol>
</nav>
<form:form method="POST" action="${pageContext.request.contextPath}/director/add_news" modelAttribute="newsRequestModel" enctype="multipart/form-data">
    <div class="form-group row required">
    	<div class="col-md-12">
     		 <label class="control-label col-sm-2" for="title">Назва:</label>
      				<form:input path="title" maxlength="80" size="500" class="form-control is-invalid" id="title" placeholder="Назва"></form:input>
					<form:errors  path="title" cssClass="error"></form:errors>
        </div>
    </div>
      
     <div class="form-group row required">
   	 	<div class="col-md-12">
            <label class="control-label col-sm-2" for="description">Опис:</label>
      		<form:textarea path="description" maxlength="1000" size="1000" type="text" rows="10" style="resize:none" class="form-control is-invalid" id="description" placeholder="Опис"></form:textarea>
      		<form:errors  path="description" cssClass="error"></form:errors>
    	</div>
    </div>
    
    <div class="form-group row">		
    		<div class="col-md-6 mb-3">
    		<div class="input-group">
                <label class="btn btn-primary buttonsAll" for="my-file-selector">
 					<form:input path="file" id="my-file-selector" type="file" style="display:none" 
    					onchange="$('#upload-file-info').html(this.files[0].name)" accept="image/jpeg,image/png,image/gif,image/bmp,image/jpg" />Вибрати файл...
                </label>   <span  id="upload-file-info"></span>   
				<form:errors path="file" cssClass="error"></form:errors>
				              
			</div>
		</div>
</div>
     <div class="form-group row">
     	<div class="col-md-6 mb-3">
  		<button class="btn btn-primary buttonsAll" type="submit"><span class="glyphicon glyphicon-plus"></span> Додати</button>
  		</div>
  	</div>
</form:form>
</div>