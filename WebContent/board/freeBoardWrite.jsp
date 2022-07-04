<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/noticeBoard.css" rel="stylesheet" type="text/css" />
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
		
		});
	</script>
</head>

<body>
	
	<jsp:include page="../main/header.jsp" />
	
	<section>
	
        <div id="content1">
        	<div class="content1">
                <a href="${conPath }/main.do"><img src="${conPath }/img/school.png" class="logo" alt="logo" /></a>
        	</div>
        </div>
        
        <div id="content2">
        	<form action="${conPath }/freeWrite.do" method="post" enctype="multipart/form-data">
			<table>
				<caption>글쓰기</caption>
				<tr><td>제목</td><td><input type="text" name="fTitle"
								required="required" size="30"></td></tr>
				<tr><td>본문</td><td><textarea name="fContent" rows="3" 
								cols="32"></textarea></td></tr>
				<tr><td>첨부파일</td><td><input type="file" name="filName"></td></tr>
				<tr><td colspan="2">
							<input type="submit" value="글쓰기" class="btn">
							<input type="reset" value="취소" class="btn">
							<input type="button" value="목록" class="btn"
								onclick="location='${conPath}/free.do'">
			</table>
		</form>
        </div>
        
        <div id="content3">
        	content3
        </div>
		        
	</section>       
	
	<jsp:include page="../main/footer.jsp" />
	
</body>
</html>