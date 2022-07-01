<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${conPath }/css/studentList.css" rel="stylesheet" type="text/css" />
	<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
		
		});
	</script>
	<style>
	p{
		font-size: 1.3em;
		margin-bottom:5px;
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
                <tr>
                    <c:forEach var="dto" items="${sAllView }">
				<td>
					<p>${dto.sNo }번</p>
					<img src="${conPath }/fileUp/${dto.sPhoto}" alt="${dto.sName }등록사진" width="100" height="350"><br>
					<b id="sname">${dto.sName }</b> (${dto.sId })<br>
					${dto.sTel }<br>
					${dto.sBirth }
				</td>
			</c:forEach>
                </tr>

            </table>
        </div>
        
        <div id="content3">
            <a href="${conPath }/sAllView.do?pageNum=1">&lt;&lt;</a>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${startPage>BLOCKSIZE }">
			<a href="${conPath }/sAllView.do?pageNum=${startPage-1}">&lt;</a>
		</c:if>
		<c:if test="${startPage<=BLOCKSIZE }">
			&lt;
		</c:if>
		&nbsp; &nbsp; &nbsp;
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				[ <b>${i }</b> ]
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/sAllView.do?pageNum=${i}">${i }</a> ]
			</c:if>
		</c:forEach>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${endPage < pageCnt }">
			<a href="${conPath }/sAllView.do?pageNum=${endPage+1}">&gt;</a>
		</c:if>
		<c:if test="${endPage == pageCnt }">
			&gt;
		</c:if>
		&nbsp; &nbsp; &nbsp;
		<a href="${conPath }/sAllView.do?pageNum=${pageCnt}">&gt;&gt;</a> 
        </div>
    </section>
    
    
    <jsp:include page="../main/footer.jsp" />
	
</body>
</html>