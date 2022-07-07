<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">

	<link href="${conPath }/css/freeBoardModify.css" rel="stylesheet" type="text/css" />
	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>

<body>
	
	
	<jsp:include page="../main/header.jsp"/>
	
	<!-- 다시 수정해야 함 -->
	
	<section>
		<!-- 자유게시판 글 수정 진행  -->
		<div id="content1">
        	<div class="content1">
                <a href="${conPath }/main.do"><img src="${conPath }/img/school.png" class="logo" alt="logo" /></a>
        	</div>
        </div>
        
        <div id="content2">
        
        	<form action="${conPath }/freeModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="fNo" value="${fboard.fNo }">
		<input type="hidden" name="dbFileName" value="${fboard.fFilename }">
		<table>
			<caption>${fboard.fNo }번 글 수정</caption>
			<tr><th>작성자</th>
					<td><input type="text" required="required" size="30"
								value="${fboard.sId }" readonly="readonly"></td>
			</tr> 
			<tr><th>제목</th>
					<td><input type="text" name="fTitle" required="required" size="30"
								value="${fboard.fTitle }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="10" cols="30" 
							name="fContent">${fboard.fContent }</textarea></td>
			</tr>
			<tr><th>첨부파일</th>
					<td><input type="file" name="fFilename" > 원첨부파일:
							<c:if test="${not empty fboard.fFilename }">
						 		<a href="${conPath }/fileUp/${fboard.fFilename}" target="_blank">${fboard.fFilename}</a>
						 	</c:if>
						 	<c:if test="${empty fboard.fFilename }">
						 		첨부파일없음
						 	</c:if>
					</td>
			</tr>
			<tr><td colspan="2">
						<input type="submit" value="수정" class="btn">
						<input type="button" value="목록"  class="btn"
							onclick="location='${conPath}/free.do?pageNum=${param.pageNum }'">
						<input type="reset" value="이전" class="btn" onclick="history.back()">
					</td>
			</tr>
		</table>
	</form>
        </div>
        

	
	</section>
	
	<jsp:include page="../main/footer.jsp"/>
	
	
	
	
	
	
</body>
</html>