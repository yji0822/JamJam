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
	<link href="${conPath }/css/header.css" rel="stylesheet" type="text/css" />
	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css" rel="stylesheet">
	<style>
		.header1 {
			color: white;
			font-size: 1.3em;
		}
	</style>
</head>

<body>
	
	
	<header>
	
	<c:if test="${empty student and empty admin}"> <%-- 로그인 전 화면 --%>
        <div id="header1">
        </div>
        <div id="header2">
        </div>
        <div id="header3">
            <ul>
                <li><a href="${conPath }/adminLoginView.do">관리자모드</a></li>
                <li><a href="${conPath }/joinView.do">회원가입</a></li>
                <li><a href="${conPath }/loginView.do">로그인</a></li>
            </ul>
        </div>
	</c:if>
	
	<c:if test="${not empty student and empty admin}">
	<div id="header1">
        </div>
        <div id="header2">
        </div>
        <div id="header3">
            <ul>
                <li><a href="${conPath }/logout.do">로그아웃</a></li>
                <li><a href="${conPath }/modifyView.do">정보수정</a></li>
                <li><a>${student.sName } 친구! &nbsp; ▶</a></li>
            </ul>
        </div>
	</c:if>
	
	<c:if test="${empty student and not empty admin}">
	<div id="header1">
        </div>
        <div id="header2">
          	관리자 로그인 상태입니다.
        </div>
        <div id="header3">
            <ul>
                <li><a href="${conPath }/logout.do">로그아웃</a></li>
                <li><a>${admin.aName } &nbsp; ▶</a></li>
                <li><a href="#">환영합니다!</a></li>
            </ul>
        </div>
	</c:if>
	
	</header>
	
</body>
</html>