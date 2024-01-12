<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,guest_book_02.model.vo.GuestBookVo" %>
<%
	List<GuestBookVo> list = (List<GuestBookVo>)request.getAttribute("books"); //book라는 이름으로 된 request중 어느것을 가져오겠다 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
<link href="css/common.css" rel="stylesheet">
<link href="css/guestbook.css" rel="stylesheet">
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
		<%if(list != null && list.isEmpty()==false){
			for(GuestBookVo vo : list){%>       
		<ul class="cont">
			<li>
				<p class="name"><%=vo.getAuthor() %></p>
				<p class="date"><%=vo.getReg_date() %></p>
				<p class="memo"><%=vo.getContent()%></p>
			</li>
		</ul>
		<%}
		}%>
	</div>
	<script>
	
	</script>
</body>
</html>