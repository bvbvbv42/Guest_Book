<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!-- c라고시작하는것은 jstl이다  -->


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<link href="/resources/css/common.css" rel="stylesheet">
<link href="/resources/css/guestbook.css" rel="stylesheet">
<style>
</style>
</head>
<body>
	<h4 class="g-top">구디 방명록</h4>
	<div class="top-img">
	</div>
	<form action="/book" method="post" id="frm"> 
		<div class="g-cont">
			<ul>
				<li class="name">
					<input name="author" type="text" placeholder="이름" class="form-control"> 
				</li>
				<li class="msg">
					<textarea name="content" placeholder="내용을 입력해주세요" class="form-control"></textarea>
				</li>
			</ul>
			<p class="btn btn-lg" onclick="document.getElementById('frm').submit();">방명록 남기기</p>
		</div>
	</form>
	<div class="guestbook list" style="display: block;">
		
		<!-- list안에 vo를 하나씩꺼내겠다 vo하나하나를 item이라고하겟다 -->
		<c:forEach var="item" items="${list}">
		<ul class="cont">
			<li>
				<p class="name">${item.author}</p>
				<p class="date">${item.reg_date}</p>
				<p class="memo">${item.content}</p>
			</li>
		</ul>
		</c:forEach>
	</div>
	<script>
	
	</script>
</body>
</html>