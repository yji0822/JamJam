<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<link href="${conPath }/css/noticeBoardModify.css" rel="stylesheet" type="text/css" />
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
	
	<!-- 공지사항 게시판 수정 로직 페이지  nNo, nTitle, nContent -->
	<section>
	
	<div id="content1">
	<div class="content1">
                <a href="${conPath }/main.do"><img src="${conPath }/img/school.png" class="logo" alt="logo" /></a>
            </div>
            <div id="lnb">
                <ul>
                    <li><a href="${conPath }/notice.do">공지사항</a></li>
                    <li><a href="${conPath }/lunch.do">오늘의 급식</a></li>
                    <li><a href="${conPath }/free.do">자유게시판</a></li>
                    <li><a href="${conPath }/sAllView.do">반친구들</a></li>
                </ul>
            </div>
	</div>
	
	<div id="content2">
		<form action="${conPath }/noticeModify.do" method="post" >
		<input type="hidden" name="pageNum" value="${param.pageNum }">
		<input type="hidden" name="nNo" value="${nboard.nNo }">
		<table>
		<!-- nNo, aName, nTitle, nContenet -->
			<caption>${nboard.nNo }번 글 수정</caption>
			<tr><th>작성자</th>
					<td><input type="text" required="required" size="30"
								value="${nboard.aName }(${nboard.aId })" readonly="readonly"></td>
			</tr>
			<tr><th>제목</th>
					<td><input type="text" name="nTitle" required="required" size="30"
								value="${nboard.nTitle }"></td>
			</tr>
			<tr><th>본문</th>
					<td><textarea rows="5" cols="32" 
							name="nContent">${nboard.nContent }</textarea></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<input type="submit" value="수정" class="btn">
					<input type="button" value="목록"  class="btn" onclick="location='${conPath}/notice.do?pageNum=${param.pageNum }'">
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