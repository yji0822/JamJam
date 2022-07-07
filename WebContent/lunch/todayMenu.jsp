<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${conPath }/css/style.css" rel="stylesheet">
	<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	<style>
	h3 {
		text-align: center;
		line-height : 100px;
	}
		#exit {
			width: 35px;
			height : 35px;
		}
	</style>
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			$('button').click(function(){
				close();
			});
		});
	</script>
</head>

<body>
	
	<h3>오늘의 급식 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  <button id="exit">X</button></h3>
	<hr>
	<table>
		<tr>
			<th>급식</th>
			<td>${lunch.ampm }</td>
		</tr>
		<tr>
			<th>배식일</th>
			<td>${lunch.ldate }</td>
		</tr>
		<tr>
			<th>메뉴</th>
			<td>${lunch.menu }</td>
		</tr>
		<tr>
			<th>칼로리</th>
			<td>${lunch.calorie }</td>
		</tr>
		<tr>
			<th>식단이미지</th>
			<td>
				<c:if test="${empty lunch.photo }">
					<img src="${conPath }/img/noimg.png">
				</c:if>
				<c:if test="${not empty lunch.photo }">
					<img src="${conPath }/lunchPic/${lunch.photo}">
				</c:if>
			</td>
	</table>
	
</body>
</html>