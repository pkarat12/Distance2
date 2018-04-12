<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">

<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/javascript.js" ></script>

<link type="font" href="https://fonts.googleapis.com/css?family=Amatic+SC|Caveat|Jura|Pacifico|Russo+One|Tangerine" rel="stylesheet">
<title>
	<tiles:getAsString name="title"></tiles:getAsString>
</title>
</head>
<body>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<hr></hr>
	<div class="container-fluid">
			<div class="col-sm-3">
				<tiles:insertAttribute name="leftSide"></tiles:insertAttribute>
			</div>
			<div class="col-sm">
				<tiles:insertAttribute name="body"></tiles:insertAttribute> 
			</div>
			<%-- <div class="col-sm-3">
			<tiles:insertAttribute name="rightSide"></tiles:insertAttribute> 
			</div> --%>
	</div>
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/bootstrap.min.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/javascript/jquery-1.11.1.min.js" ></script>
</body>
</html>