
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<link href="${conPath }/css/main.css" rel="stylesheet" type="text/css" />
<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	<style>
		
		marquee{
			font-size: 2em;
			width: 1200px;
		}
	</style>
</head>

<body>
	
	<c:if test="${not empty loginErrorMsg }">
		<script>alert('${loginErrorMsg }');</script>
	</c:if>
	<c:if test="${not empty modifyResult }">
		<script>
			alert('${modifyResult}');
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
		
		<c:if test="${empty student and empty admin}">
			<div class="content1">
				<a href="${conPath }/main.do"><img src="${conPath }/img/school.png" class="logo" alt="logo" /></a>
				<div id="lnb">
             		<!-- <mark>모든 메뉴는 로그인 후 이용할 수 있습니다.</mark> -->
             		<marquee direction=left scrolldelay="1" >
             		모든 메뉴는 로그인 후 이용할 수 있습니다.
             		</marquee>
            	</div>
			</div>
		</c:if>
		
		<c:if test="${not empty student or not empty admin}">
			<div class="content1">
				<a href="${conPath }/main.do"><img src="${conPath }/img/school.png" class="logo" alt="logo" /></a>
				<div id="lnb">
                <ul>
                    <li><a href="${conPath }/notice.do">공지사항</a></li>
                    <li><a href="${conPath }/lunch.do">오늘의 급식</a></li>
                    <li><a href="${conPath }/free.do">자유게시판</a></li>
                    <li><a href="${conPath }/sAllView.do">반친구들</a></li>
                </ul>
            </div>
			</div>
		</c:if>
        </div>
        
		<div id="content2">
			<img src="https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDEyMjhfMTY3%2FMDAxNjA5MTQwOTgzMDcx.jtvKF1qL-l4ef4CgbUMCceSNCmVCgveiXcFpGRTyANUg.Nvi8RqGMvnYfsdMcoUV1Z0gqu-Tq-SdCdjjxZX6KDYcg.PNG.easily-sis%2Fimage.png&type=sc960_832" 
			id="photo" alt="photo" />
		</div>
		
		<div id="content3">
			<div class="goal1">
				JUST DO IT
			</div>
			<div class="goal2">
				강이 소리를 내지 않고 묵묵히 흘러가는 것처럼<br /> 열심히 공부해야 성공할 수 있다!
			</div>
		</div>
	</section>
	
	<jsp:include page="../main/footer.jsp" />

</body>
</html>