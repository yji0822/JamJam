<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
		
		});
	</script>
	<link href="${conPath }/css/freeBoardContent.css" rel="stylesheet" type="text/css" />
	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
</head>

<body>
	
	<c:if test="${not empty fboardResult }">
		<script>
			alert('${fboardResult }');
		</script>
	</c:if>
	
	
	<jsp:include page="../main/header.jsp" />
	
	<section>
	
	<div id="content1">
		<div class="content1">
        	<a href="${conPath }/main.do"><img src="${conPath }/img/school.png" class="logo" alt="logo" /></a>
        </div>
	</div>
	
	<div id="content2">
		<table>		
				 <caption>${fboard.fNo }번 글 상세보기</caption>
				 <tr>
				 	<td class="subject">작성자</td>
				 	<td>${fboard.sName} (${fboard.sId})</td>
				 	<td class="subject">작성일</td>
				 	<td>${fboard.fRdate}</td>
				 </tr>
				 <tr>
				 	<td class="subject">글 조회수</td>
				 	<td>${fboard.fHit }</td>
				 	<td class="subject">아이피</td>
				 	<td>${fboard.fIp }</td>
				 </tr>
				 <tr>
				 	<td class="subject">제목</td>
				 	<td colspan="3">${fboard.fTitle }</td>
				 </tr>
				 
		
				 <tr>
				 	<td colspan="4">본문내용</td>
				 </tr>
				 	<tr><td colspan="4">
				 		<c:if test="${not empty fboard.fFilename }">
						 		<img src="${conPath }/fileUp/${fboard.fFilename}" alt="${fboard.fFilename }_사진파일">
						 	</c:if>
				 		<br><br><pre>${fboard.fContent}</pre>
				 </td>
				 </tr>
				 
				 <tr>
				 	<td colspan="4">
				 	<!-- 학생의 id이랑 fboard의 sid가 같으면 수정버튼 생성 -->
				 		<c:if test="${student.sId eq fboard.sId }">
				 			<button onclick="location='${conPath}/freeModifyView.do?fNo=${fboard.fNo }&pageNum=${param.pageNum }'">수정</button>
				 		</c:if>
				 		<c:if test="${student.sId eq fboard.sId or not empty admin}">
				 				<button onclick="location='${conPath}/freeDelete.do?fRef=${fboard.fRef }&fStep=${fboard.fStep }&fIndent=${fboard.fIndent }&pageNum=${param.pageNum }'">삭제</button>
				 			</c:if>
				 			<c:if test="${not empty student }">
				 				<button onclick="location='${conPath}/freeReplyView.do?fNo=${fboard.fNo }&pageNum=${param.pageNum }'" class="btn">답변</button>
				 			</c:if>
				 			<input type="button" value="목록" 
				 	onclick="location='${conPath}/free.do?pageNum=${param.pageNum }'">			 
			</table>
		</div>
	</section>
	
	<jsp:include page="../main/footer.jsp" />
	
</body>
</html>