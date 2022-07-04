<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/freeBoardContent.css" rel="stylesheet" type="text/css" />
	<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	
	<style>
		.write_btn {
			width: 100px;
			height: 
		}
		
		#content2 table tr:hover {
			font-weight: bold;
			font-size: 1.2em;
		}
		#content2 table tr:first-child:hover{
			font-weight: none;
			font-size: 1em;
		}
	</style>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			$('tr').click(function(){
				var fNo = Number($(this).children().eq(0).text()); // 0번째 td안의 있는 text;
				//alert(fId);
				if(!isNaN(fNo)){
					location.href = '${conPath}/freeContent.do?fNo='+fNo+'&pageNum=${pageNum}';
				}
			});
		});
	</script>
	<style>
	.file_img {
		width: 20px;
		height: 20px;
		line-height: 50px;
	}
	
	.btn {
		width: 100px;
		height: 40px;
	}
	</style>
	
</head>

<body>
	
	<c:if test="${not empty fboaredResult }">
		<script>alert('${fboaredResult}');</script>
	</c:if>
	
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
        	<%-- <tr>
        		<td></td><td></td><td></td><td></td>
        		<td class="write_btn"><a href="${conPath }/freeWriteView.do" >글쓰기</a></td>
        	</tr> --%>
        		<tr>
        			<th>번호</th><th>제목</th><th>작성자</th><th>조회수</th><th>날짜</th>
        		</tr>
        		<c:if test="${totCnt==0 }">
			<tr><td colspan="6">등록된 글이 없습니다</td></tr>
		</c:if>
		<c:if test="${totCnt!=0 }">
			<c:forEach items="${fboardList }" var="board">
				<tr>
					<td>${board.fNo }</td>
					<td class="left">
						<c:forEach var="i" begin="1" end="${board.fIndent }">
							<c:if test="${i==board.fIndent }">└─</c:if>
							<c:if test="${i!=board.fIndent }"> &nbsp; &nbsp; </c:if>
						</c:forEach>
						${board.fTitle } <!-- 글제목에 a태그를 걸지 말고 query로 tr을 클릭하면 상세보기 페이지로 가기 -->
						<c:if test="${not empty board.fFilename }">
							<img src="${conPath }/img/disk.png" width="10" class="file_img">
						</c:if>
					</td>
					<td>${board.sName }</td>
					<td>${board.fHit }</td>
					<td><fmt:formatDate value="${board.fRdate }" type="date" dateStyle="short"/></td>
						<%-- <td>${fboard.fIp }</td> --%>
				</tr>
			</c:forEach>
		</c:if>
			<tr>
				<td colspan="5"><b><a href="${conPath }/freeWriteView.do">글 쓰기</a></b></td> 
				<%-- <td colspan="5"><input type="button" value="글쓰기" class="btn"
				 	onclick="location='${conPath}/freeWriteView.do'"></td> --%>
			</tr>
        </table>
        </div>
        
        <div id="content3">
            <a href="${conPath }/free.do?pageNum=1">&lt;&lt;</a>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${startPage>BLOCKSIZE }">
			<a href="${conPath }/free.do?pageNum=${startPage-1}">&lt;</a>
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
				[ <a href="${conPath }/free.do?pageNum=${i}">${i }</a> ]
			</c:if>
		</c:forEach>
		&nbsp; &nbsp; &nbsp;
		<c:if test="${endPage < pageCnt }">
			<a href="${conPath }/free.do?pageNum=${endPage+1}">&gt;</a>
		</c:if>
		<c:if test="${endPage == pageCnt }">
			&gt;
		</c:if>
		&nbsp; &nbsp; &nbsp;
		<a href="${conPath }/free.do?pageNum=${pageCnt}">&gt;&gt;</a> 
		
			
		
        </div>
        
    </section>
    <jsp:include page="../main/footer.jsp" />
	
	
</body>
</html>