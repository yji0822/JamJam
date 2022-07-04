<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${conPath }/css/freeBoardContent.css" rel="stylesheet" type="text/css" />
	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	<style>
		#content1 {
			height:85px;
		}
		.subject {
			width: 50px;
		}
		
		img {
		
			height: 220px;
			width: 200px;
		}
		button {
			width: 100px;
			height: 40px;
		}
		.btn {
			width: 100px;
			height: 40px;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
		
		});
	</script>
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
		<input type="hidden" name="fNo" value="${board.fNo }">
		<input type="hidden" name="dbFileName" value="${board.fFileName }">
		<table>
			<caption>${fboard.fId }번 글 수정</caption>
			<%-- <tr><th>작성자</th>
					<td><input type="text" required="required" size="30"
								value="${fboard.mId }" readonly="readonly"></td>
			</tr> --%>
			<tr><th>제목</th>
					<td><input type="text" name="fTitle" required="required" size="30"
								value="${board.fTitle }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5" cols="32" 
							name="fContent">${board.fContent }</textarea></td>
			</tr>
			<tr><th>첨부파일</th>
					<td><input type="file" name="fFilename" class="btn"> 원첨부파일:
							<c:if test="${not empty board.fFilename }">
						 		<a href="${conPath }/fileUp/${board.fFilename}" target="_blank">${board.fFilename}</a>
						 	</c:if>
						 	<c:if test="${empty board.fFilename }">
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
        
        <div id="content3">
        	content3
        </div>
		        
	
	</section>
	
	<jsp:include page="../main/footer.jsp"/>
	
	
	
	
	
	<!-- 
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<form action="${conPath }/boradModify.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="fId" value="${board.fId }">
		<input type="hidden" name="dbFileName" value="${board.fFileName }">
		<table>
			<caption>${board.fId }번 글 수정</caption>
			<tr><th>작성자</th>
					<td><input type="text" required="required" size="30"
								value="${board.mName }(${board.mId })" readonly="readonly"></td>
			</tr>
			<tr><th>제목</th>
					<td><input type="text" name="fTitle" required="required" size="30"
								value="${board.fTitle }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5" cols="32" 
							name="fContent">${board.fContent }</textarea></td>
			</tr>
			<tr><th>첨부파일</th>
					<td><input type="file" name="fFileName" class="btn"> 원첨부파일:
							<c:if test="${not empty board.fFileName }">
						 		<a href="${conPath }/fileboardUp/${board.fFileName}" target="_blank">${board.fFileName}</a>
						 	</c:if>
						 	<c:if test="${empty board.fFileName }">
						 		첨부파일없음
						 	</c:if>
					</td>
			</tr>
			<tr><td colspan="2">
						<input type="submit" value="수정" class="btn">
						<input type="button" value="목록"  class="btn"
							onclick="location='${conPath}/boardList.do?pageNum=${param.pageNum }'">
						<input type="reset" value="이전" class="btn"
						  onclick="history.back()">
					</td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
	 -->
	
</body>
</html>