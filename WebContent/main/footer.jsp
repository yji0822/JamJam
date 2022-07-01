<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${conPath }/css/footer.css" rel="stylesheet" type="text/css" />
	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css" rel="stylesheet">
</head>

<body>
	
	<footer>
        <div id="footer1">
            <a href="#"><img src="${conPath }/img/school.png" class="logo" alt="logo" /></a>
        </div>
        <div id="footer2">	
            	<a href="https://yongsan.sen.hs.kr/116179/subMenu.do">용산고등학교 홈페이지</a>
        </div>
        <div id="footer3">
             <div id ="address">
                [04353] 서울특별시 용산구 두텁바위로 60 (용산동2가, 용산고등학교)<br />
                TEL 02-3706-6700 | FAX 02-318-4439
            </div>
        </div>
    </footer>
	
</body>
</html>