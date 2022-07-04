<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			$('tr').click(function(){
				var nNo = Number($(this).children().eq(0).text()); // 0번째 td안의 있는 text;
				/* var nNo = Number($(this).children().eq(0).text()); */
				/* alert(nId + "번 글 접속"); */
				if(!isNaN(nNo)){
					location.href = '${conPath}/noticeContent.do?nNo='+nNo+'&pageNum=${pageNum}';
				}
			});
		});
	</script>
	<link href="${conPath }/css/noticeBoard.css" rel="stylesheet" type="text/css" />
	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	<style>
		table {
			margin-top: 35px;
			width: 85%;
		}
		
		#content2 table tr:hover {
			font-weight: bold;
			font-size: 1.2em;
		}
		#content2 table tr:first-child:hover{
			font-weight: none;
			font-size: 1em;
		}
		
		th {
		width: 20%;
		}
		
		td {
			width: 100px;
		}
	</style>
</head>

<body>
	<%-- <c:if test="${not empty adminLoginResult }">
		<script>
			alert('${adminLoginResult}');
		</script>
	</c:if>
	<c:if test="${not empty adminLoginError }">
		<script>
			history.back();
		</script>
	</c:if>
	
		<c:if test="${not empty loginErrorMsg }">
		<script>
			alert('${loginErrorMsg}');
			history.back();
		</script>
	</c:if> --%>
	<c:if test="${not empty withdrawalResult }">
		<script>
			alert('${withdrawalResult}');
		</script>
	</c:if>
	
	<c:if test="${not empty nboaredResult }">
		<script>alert('${nboaredResult}');</script>
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
        		<tr>
        			<!-- <th>글번호</th><th>작성자</th><th>글제목</th><th>조회수</th><th>날짜</th> -->
        			<th>글번호</th><th>글제목</th><th>작성자</th><th>조회수</th><th>날짜</th>
        		</tr>
		<c:if test="${totCnt == 0 }">
			<tr><td colspan="6">등록된 글이 없습니다</td></tr>
		</c:if>
		<c:if test="${totCnt!=0 }">
			<c:forEach items="${noticeList }" var="notice">
				<tr><td>${notice.nNo }</td>
						<td class="left">
							<%-- <c:forEach var="i" begin="1" end="${noticeList.fIndent }">
								<c:if test="${i==noticeList.nIndent }">└─</c:if>
								<c:if test="${i!=noticeList.nIndent }"> &nbsp; &nbsp; </c:if>
							</c:forEach> --%>
							${notice.nTitle } <!-- 글제목에 a태그를 걸지 말고 query로 tr을 클릭하면 상세보기 페이지로 가기 -->
						<td>${notice.aName }</td>
							<%-- <c:if test="${not empty noticeList.nFileName }">
								<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
							</c:if> --%>
						</td>
						<td>${notice.nHit }</td>
						<td><fmt:formatDate value="${notice.nRdate }" type="date" dateStyle="short"/></td>
				</tr>
			</c:forEach>
		</c:if>
        	</table>
        </div>
        
        <div id="content3">
        <c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/notice.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				<b> [ ${i } ] </b>
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/notice.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/notice.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
        </div>

    </section>
	
	
	<jsp:include page="../main/footer.jsp" />
	
</body>
</html>