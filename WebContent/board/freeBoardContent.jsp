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
	<link href="${conPath }/css/noticeBoard.css" rel="stylesheet" type="text/css" />
	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	<style>
		#content1 {
			height:85px;
		}
		.subject {
			width: 50px;
		}
	</style>
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
		<table>
				 <caption>${fboard.fNo }번 글 상세보기</caption>
				 <tr>
				 	<td class="subject">작성자</td>
				 	<td>${fboard.sName}(${fboard.sId}) 친구</td>
				 </tr>
				 <tr>
				 	<td class="subject">제목</td>
				 	<td>${fboard.fTitle }</td>
				 </tr>
				 <tr>
				 	<!-- <th>첨부파일</th> -->
						 
						 	<c:if test="${not empty fboard.fFilename }">
						 		<td colspan="2"><a href="${conPath }/img/${fboard.fFilename}" target="_blank">${board.fFilename}</a></td>
						 	</c:if>
						 	<c:if test="${empty fboard.fFilename }">
						 		<!-- <td colspan="2">첨부파일없음</td> -->
						 	</c:if>
				 </tr>
				 <tr>
				 	<td colspan="2">본문내용</td>
				 </tr>
				 	<tr><td colspan="2"><pre>${fboard.fContent}</pre>
				 </td>
				 </tr>
				 
				 <tr>
				 	<td colspan="2">
				 	<!-- 학생의 id이랑 fboard의 sid가 같으면 수정버튼 생성 -->
				 		<c:if test="${student.sId eq fboard.sId }">
				 			<button onclick="location='${conPath}/freeModifyView.do?fNo=${fboard.fNo }&pageNum=${param.pageNum }'">수정</button>
				 		</c:if>
				 		<c:if test="${student.sId eq fboard.sId or not empty admin}">
				 				<button onclick="location='${conPath}/freeDelete.do?fRef=${fboard.fRef }&fStep=${fboard.fStep }&fIndent=${fboard.fIndent }&pageNum=${param.pageNum }'">삭제</button>
				 			</c:if>
				 			<c:if test="${not empty student }">
				 				<button onclick="location='${conPath}/freeReplyView.do?fNo=${fboard.fNo }&pageNum=${param.pageNum }'">답변</button>
				 			</c:if>
				 			<input type="button" value="목록" class="btn"
				 	onclick="location='${conPath}/free.do?pageNum=${param.pageNum }'">			 
			</table>
		</div>
		
		<div id="content3"> content3</div>
	</section>
	
	<jsp:include page="../main/footer.jsp" />
	
</body>
</html>