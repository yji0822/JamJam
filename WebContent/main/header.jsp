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
</head>

<body>
	
	
	<header>
	
	<c:if test="${empty student and empty admin}"> <%-- 로그인 전 화면 --%>
        <div id="header1">
            header1
        </div>
        <div id="header2">
            header2
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
            header1
        </div>
        <div id="header2">
            header2
        </div>
        <div id="header3">
            <ul>
                <li><a href="${conPath }/logout.do">로그아웃</a></li>
                <li><a href="#">정보수정</a></li>
                <li><a>${student.sName } 친구! &nbsp; ▶</a></li>
            </ul>
        </div>
	</c:if>
	
	<c:if test="${empty student and not empty admin}">
	<div id="header1">
            header1
        </div>
        <div id="header2">
          	관리자 로그인 상태입니다.
        </div>
        <div id="header3">
            <ul>
                <li><a href="${conPath }/logout.do">로그아웃</a></li>
                <li><a href="#">정보수정</a></li>
                <li><a>${admin.aName } &nbsp; ▶</a></li>
            </ul>
        </div>
	</c:if>
	
	</header>
	
	
	<%-- 
	<header>
        <div id="header1">
            header1
        </div>
        <div id="header2">
            header2
        </div>
        <div id="header3">
            <ul>
                <li><a href="#">관리자모드</a></li>
                <li><a href="#">회원가입</a></li>
                <li><a href="#">로그인</a></li>
            </ul>
        </div>
    </header>
	
	
	
	<header>
	<c:if test="${empty member and empty admin}"> 로그인 전 화면
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/boardList.do">고객센터</a></li>
				<li><a href="${conPath }/joinView.do">회원가입</a></li>
				<li><a href="${conPath }/loginView.do">로그인</a></li>
			</ul>
		</div>
		<div class="logo" onclick="location.href='${conPath}/main.do'">
			LOGO
		</div>
		<div class="lnb">
			<ul>
				<li>로그인전메뉴1<ol class="subMenu">
							<li><a href="#">메뉴1-1</a></li>
							<li><a href="#">메뉴1-2</a></li>
							<li><a href="#">메뉴1-3</a></li>
						</ol>
				</li>
				<li>로그인전메뉴2<ol class="subMenu">
							<li><a href="#">메뉴2-1</a></li>
							<li><a href="#">메뉴2-2</a></li>
							<li><a href="#">메뉴2-3</a></li>
						</ol>
				</li>
				<li>로그인전메뉴3<ol class="subMenu">
							<li><a href="#">메뉴3-1</a></li>
							<li><a href="#">메뉴3-2</a></li>
							<li><a href="#">메뉴3-3</a></li>
						</ol>
				</li>
				<li>로그인전메뉴4<ol class="subMenu">
							<li><a href="#">메뉴4-1</a></li>
							<li><a href="#">메뉴4-2</a></li>
							<li><a href="#">메뉴4-3</a></li>
						</ol>
				</li>
			</ul>
		</div>
	</c:if>
	<c:if test="${not empty member and empty admin}"> 사용자 모드 로그인 화면
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/boardList.do">고객센터</a></li>
				<li><a href="${conPath }/logout.do">로그아웃</a></li>
				<li><a href="${conPath }/modifyView.do">정보수정</a></li>
				<li><a>${member.mName }님 &nbsp; ▶</a></li>	
			</ul>
		</div>
		<div class="logo" onclick="location.href='${conPath}/list.do'">
			LOGO
		</div>
		<div class="lnb">
			<ul>
				<li>로그인후메뉴1<ol class="subMenu">
							<li><a href="#">메뉴1-1</a></li>
							<li><a href="#">메뉴1-2</a></li>
							<li><a href="#">메뉴1-3</a></li>
						</ol>
				</li>
				<li>로그인후메뉴2<ol class="subMenu">
							<li><a href="#">메뉴2-1</a></li>
							<li><a href="#">메뉴2-2</a></li>
							<li><a href="#">메뉴2-3</a></li>
						</ol>
				</li>
				<li>로그인후메뉴3<ol class="subMenu">
							<li><a href="#">메뉴3-1</a></li>
							<li><a href="#">메뉴3-2</a></li>
							<li><a href="#">메뉴3-3</a></li>
						</ol>
				</li>
				<li>로그인후메뉴4<ol class="subMenu">
							<li><a href="#">메뉴4-1</a></li>
							<li><a href="#">메뉴4-2</a></li>
							<li><a href="#">메뉴4-3</a></li>
						</ol>
				</li>
			</ul>
		</div>
	</c:if>
	<c:if test="${empty member and not empty admin}"> 관리자 모드 로그인 화면
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/boardList.do">고객센터</a></li>
				<li><a href="${conPath }/logout.do">관리자모드나가기</a></li>
				<li><a>${admin.aName }님 &nbsp; ▶</a></li>	
			</ul>
		</div>
		<div class="logo" onclick="location.href='${conPath}/allView.do'">
			LOGO
		</div>
		<div class="lnb">
			<ul>
				<li>관리자의메뉴1<ol class="subMenu">
							<li><a href="#">메뉴1-1</a></li>
							<li><a href="#">메뉴1-2</a></li>
							<li><a href="#">메뉴1-3</a></li>
						</ol>
				</li>
				<li>관리자의메뉴2<ol class="subMenu">
							<li><a href="#">메뉴2-1</a></li>
							<li><a href="#">메뉴2-2</a></li>
							<li><a href="#">메뉴2-3</a></li>
						</ol>
				</li>
				<li>관리자의메뉴3<ol class="subMenu">
							<li><a href="#">메뉴3-1</a></li>
							<li><a href="#">메뉴3-2</a></li>
							<li><a href="#">메뉴3-3</a></li>
						</ol>
				</li>
				<li>관리자의메뉴4<ol class="subMenu">
							<li><a href="#">메뉴4-1</a></li>
							<li><a href="#">메뉴4-2</a></li>
							<li><a href="#">메뉴4-3</a></li>
						</ol>
				</li>
			</ul>
		</div>
	</c:if>
</header> --%>
	
	
	
	
	
	
	
	
	
	
	
</body>
</html>