<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
	$(document)
			.ready(
					function() {
						$('input[name="sEmail"]')
								.keyup(
										function() {
											var patternMail = /^[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\.[a-zA-Z]+){1,2}$/; // 메일 패턴
											var sEmail = $(
													'input[name="sEmail"]').val();
											if (patternMail.test(sEmail)) {
												$.ajax({
															url : '${conPath}/emailConfirm.do',
															type : 'get',
															dataType : 'html',
															data : "sEmail="
																	+ sEmail,
															success : function(
																	data) {
																$('#emailConfirmResult').html(data);
															}
														});//ajax
											} else if (!sEmail) {
												$('#emailConfirmResult').html(
														' &nbsp; ');
											} else {
												$('#emailConfirmResult').html(
														'메일 형식을 지켜주세요');
											}//if
										});// mEmail keyup 이벤트
						$('form').submit(
										function() {
											var emailConfirmResult = $(
													'#emailConfirmResult')
													.text().trim();
											if (emailConfirmResult != ''
													&& emailConfirmResult != '사용 가능한 메일입니다') {
												alert('중복된 이메일은 수정불가합니다');
												$('input[name="sEmail"]')
														.focus();
												return false;
											}
										});
					});
</script>

</head>
<body>
	
	<c:if test="${empty student }"> <!-- 로그인 전이면 로그인 페이지로 가기 -->
		<script>location.href="loginView.do";</script>
	</c:if>
	
	
	
	<input type="hidden" name="dbsPhoto" value="${student.sPhoto }">
	
	<jsp:include page="../main/header.jsp" />
	
	<div id="content1">
		<a href="${conPath }/main.do"><img src="${conPath }/img/school.png" class="logo" alt="logo" /></a>
    </div>
    
    <div id="content2">
    	<form action="${conPath }/modify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="dbsPhoto" value="${student.sPhoto }">
			<table>
				<tr>
					<th>아이디</th>
					<td><input type="text" name="sId" value="${student.sId }"
						readonly="readonly"></td>
					<td rowspan="4">
						<img src="${conPath }/fileUp/${student.sPhoto}" width="300">
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type="password" name="sPw" value="${student.sPw }" required="required"></td>
				</tr>
				<tr>
					<th>이름</th>
					<td><input type="text" name="sName" value="${student.sName }" required="required"></td>
				</tr>
				 <tr>
                        <th>출석번호</th>
                        <td><input type="number" name="sNo" value="${student.sNo }" required="required" /></td>
                    </tr>
                 <tr>
                        <th>핸드폰번호</th>
                        <td><input type="text" name="sTel" value="${student.sTel }" required="required"/></td>
                    </tr>
				<tr>
					<th>이메일</th>
					<td><input type="email" name="sEmail" value="${student.sEmail }">
					<div id="emailConfirmResult">&nbsp;</div></td>
				</tr>
				 
				<c:if test="${student.sGender eq 'm'}">
					<tr>
					<td>
						<input type="radio" name="sGender" value="m" checked="checked" id="m"><label for="m">남자</label>
						<input type="radio" name="sGender" value="w" id="w"><label for="w">여자</label>
					</td>
					</tr>
				</c:if>
				
				<c:if test="${student.sGender eq 'w'}">
					<tr>
					<td>
						<input type="radio" name="sGender" value="m" id="m"><label for="m">남자</label>
						<input type="radio" name="sGender" value="w" id="w" checked="checked" ><label for="w">여자</label>
					</td>
					</tr>
				</c:if>
				
				
				
				<tr>
					<th>사진</th>
					<td colspan="2"><input type="file" name="sPhoto"></td>
				</tr>
				<tr>
					<th>생년월일</th>
					<td colspan="2"><input type="text" name="sBirth" id="sBirth"
						value="${student.sBirth }"></td>
				</tr>
				
				<tr>
					<td colspan="3">
						<input type="submit" value="정보수정" class="btn">
						<input type="reset" value="초기화" class="btn"> 
						<input type="button" value="이전" onclick="history.go(-1)" class="btn">
						<input type="button" value="회원탈퇴"
						onclick="location.href='${conPath}/withdrawal.do'" class="btn">
					</td>
				</tr>
			</table>
		</form>
    </div>
    
    <div id="content3">
    </div>
	
		
	
	<jsp:include page="../main/footer.jsp" />
	
	
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#sBirth").datepicker(
				{
					dateFormat : 'y-mm-dd',
					changeMonth : true, // 월을 바꿀 수 있는 셀렉트 박스 표시
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					showMonthAfterYear : true,
					yearSuffix : '년', // "2020년 3월"
					showOtherMonths : true,
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
					changeYear : true, // 년을 바꿀 수 있는 셀렉트 박스 표시
					minDate : '-100y', // 현재 날짜로부터 100년 이전까지 표시
					maxDate : 'y', // 현재 날짜이전까지만 표시
					yearRange : 'c-100:c+100', // 년도 선택 셀렉트 
				});
	});
</script>
	
</body>
</html>