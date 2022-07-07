<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<link href="${conPath }/css/freeBoardReply.css" rel="stylesheet" type="text/css" />
	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
		
		});
	</script>
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
        	<form action="${conPath }/freeReply.do" method="post" enctype="multipart/form-data">
			<!-- reply.do시 필요한 원글 정보 : fGroup, fStep, fIndent
			                              지금 입력할 답변글 : sName, fTitle, fContent
			                       pageNum -->
			<input type="hidden" name="fRef" value="${originBoard.fRef }">
			<input type="hidden" name="fStep" value="${originBoard.fStep }">
			<input type="hidden" name="fIndent" value="${originBoard.fIndent }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<caption> ${originBoard.fNo }번 게시글 답변</caption>
				<tr><td>작성자</td><td>${student.sName }(${student.sId })</td></tr>
				<tr><td>제목</td><td><input type="text" name="fTitle"
								required="required" size="30"
								value="[RE] ${originBoard.fTitle }"></td></tr>
				<tr><td>본문</td><td><textarea name="fContent" rows="3" cols="3"></textarea></td></tr>
				<tr><td>첨부파일</td><td><input type="file" name="fFilname"></td></tr>
				<tr><td colspan="2">
							<input type="reset" value="초기화" class="btn">
							<input type="button" value="목록" class="btn"
								onclick="location.href='${conPath}/free.do'">
							<input type="submit" value="답변쓰기" class="btn">
			</table>
		</form>
        </div>
	
	</section>

	<jsp:include page="../main/footer.jsp"/>
	
</body>
</html>