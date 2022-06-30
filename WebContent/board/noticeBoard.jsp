<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
		
		});
	</script>
	<link href="${conPath }/css/board.css" rel="stylesheet" type="text/css" />
	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
</head>

<body>
	<c:if test="${not empty adminLoginResult }">
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
	</c:if>
	<c:if test="${not empty withdrawalResult }">
		<script>
			alert('${withdrawalResult}');
		</script>
	</c:if>
	
	
	<jsp:include page="../main/header.jsp" />
	
	
	<section>
        <div id="content1">
            <div class="content1">
                <a href="${conPath }/main.do"><img src="${conPath }/img/school.png" class="logo" alt="logo" /></a>
            </div>
            <div id="lnb">
                <ul>
                    <li><a href="#">공지사항</a></li>
                    <li><a href="#">오늘의 급식</a></li>
                    <li><a href="#">자유게시판</a></li>
                    <li><a href="#">반친구들</a></li>
                </ul>
            </div>
        </div>
        <div id="content2">
        </div>
    </section>
	
	
	<jsp:include page="../main/footer.jsp" />
	
</body>
</html>