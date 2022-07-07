<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<link href="${conPath }/css/login.css" rel="stylesheet" type="text/css" />
<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
</head>

<body>

	<c:if test="${not empty errorMsg}">
		<script>
			alert('${errorMsg}');
			history.back();
		</script>
	</c:if>
		
	<jsp:include page="../main/header.jsp" />
	<section>
		<div id="content1">
            <div class="content1">
                <a href="${conPath }/main.do"><img src="${conPath }/img/school.png" class="logo" alt="logo" /></a>
            </div>
           
        </div>

		<div id="content2">
		
			<form action="${conPath }/login.do" method="post" class="login">

				<table class="login_table">
					<caption>로그인을 해주세요</caption>
					<tr>
						<td></td>
					</tr>
					<tr>
						<th>아이디</th>
						<td><input type="text" name="sId" value="${sId }" required="required"></td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td><input type="password" name="sPw" required="required"/></td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" value="로그인" />
							<input type="button" value="회원가입" onclick="location.href='${conPath}/joinView.do'"/>
						</td>
					</tr>
				</table>

			</form>
		</div>

	</section>
	<jsp:include page="../main/footer.jsp" />

</body>
</html>