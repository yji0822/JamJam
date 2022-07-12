<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<link href="${conPath }/css/noticeBoardWrite.css" rel="stylesheet" type="text/css" />
	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">

	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
	
	<jsp:include page="../main/header.jsp"/>

	<section>
		<div id="content1">
			<div class="content1">
                <a href="${conPath }/main.do"><img src="${conPath }/img/school.png" class="logo" alt="logo" /></a>
            </div>
		</div>
		
		<div id="content2">
			
			<form action="${conPath }/noticeWrite.do" method="post">
			<table>
				<caption>공지사항 글 작성</caption>
				<tr>
					<th>제목</th>
					<td><input type="text" name="nTitle" required="required" size="30"></td>
				</tr>
				<tr>
					<th>본문</th>
					<td><textarea name="nContent" rows="3" cols="32"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="reset" value="취소" class="btn">
					<input type="button" value="목록" class="btn" onclick="location.href='${conPath}/notice.do'">
					<input type="submit" value="글쓰기" class="btn">
					</td>
			</table>
			
			</form>
		</div>
	</section>	
	
	<jsp:include page="../main/footer.jsp"/>
	
</body>
</html>