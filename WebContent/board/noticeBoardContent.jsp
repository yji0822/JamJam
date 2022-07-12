<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<link href="${conPath }/css/noticeContent.css" rel="stylesheet" type="text/css" />
	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
</head>

<body>
	
	<jsp:include page="../main/header.jsp" />
	
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
		<table>
				 <caption>${nboard.nNo }번 공지사항</caption>
				 
				 <tr>
				 	<td>작성자</td>
				 	<td>${nboard.aName} (${nboard.aId})</td>
				 
				 	<td>작성일</td>
				 	<td>${nboard.nRdate }</td>
				 </tr>
				 <tr>
				 	<td>제목</td>
				 	<td colspan="3" class="nTitle">${nboard.nTitle }</td>
				 </tr>
				 
				 <tr>
				 	<td colspan="4">본문내용</td>
				 </tr>
				 	<tr><td colspan="4" class="nContent"><pre>${nboard.nContent}</pre>
				 </td>
				 </tr>
				 
				 <tr class="add">
				 	<td colspan="4" >
				 	<!-- 관리자 계정으로 들어온 경우에만 삭제 버튼 보이고 수정이 가능하도록 -->
				 		<c:if test="${not empty admin}">
				 			<button onclick="location='${conPath}/noticeModifyView.do?nNo=${nboard.nNo }&pageNum=${param.pageNum }'" class="btn">수정</button>
			 				<button onclick="location='${conPath}/noticeDelete.do?nNo=${nboard.nNo }&pageNum=${param.pageNum }'" class="btn1">삭제</button>
			 			</c:if>
				
				 			<input type="button" value="목록" class="btn"
				 	onclick="location='${conPath}/notice.do?pageNum=${param.pageNum }'">			 
			</table>
		</div>
		
	</section>
	
	<jsp:include page="../main/footer.jsp" />
	
</body>
</html>