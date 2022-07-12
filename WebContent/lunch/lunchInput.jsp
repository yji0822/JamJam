<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	
	<link href="${conPath }/css/lunchInput.css" rel="stylesheet" type="text/css" />
<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
		
		});
	</script>
</head>

<body>
	
	<jsp:include page="../main/header.jsp" />
		
		<!-- 필요한 것 : 급식선택(Radio 버튼) 배식일 선택 / 메뉴 입력 : String / 칼로리 : double / 식단 이미지 입력 - 데이터 베이스에는 String 값으로 -->
	<section>
	<div id="content1">
            <div class="content1">
                <a href="${conPath }/main.do"><img src="${conPath }/img/school.png" class="logo" alt="logo" /></a>
            </div>
        </div>
		<div id="content2">
			<form action="${conPath }/lunchIn.do" method="post" enctype="multipart/form-data">
				<table class="join_table">
					<caption>급식 메뉴 추가</caption>
					<tr>
						<th>급식일</th>
						<td><input type="text" name="ldate" id="ldate"></td>
					</tr>
					<tr>
						<th>중식/석식</th>
						<td>
							<input type="radio" name="ampm" value="중식" id="lunch" checked="checked" /><label for="lunch">중식 </label>
                            <input type="radio" name="ampm" value="석식" id="dinner"/><label for="dinner">석식 </label>
						</td>
					</tr>
					<tr>
						<th>메뉴</th>
						<td><input type="text" name="menu" id="menu"></td>
					</tr>
					<tr>
						<th>칼로리</th>
						<td><input type="text" name="calorie" id="calorie"></td>
					</tr>
					<tr>
						<th>급식사진</th>
						<td><input type="file" name="photo"></td>
					</tr>
					
					<tr>
						<td colspan="2">
							<input type="submit" value="입력">
							<input type="button" value="이전페이지" onclick="history.back()">
						</td>
					</tr>
					
					
					
				</table>
			</form>
		</div>
	</section>
	
	<jsp:include page="../main/footer.jsp" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#ldate" ).datepicker({
    	dateFormat : 'yy-mm-dd',
    	changeMonth : true, // 월을 바꿀 수 있는 셀렉트 박스 표시
    	monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
    	showMonthAfterYear : true,
    	yearSuffix : '년', // "2020년 3월"
    	showOtherMonths : true,
    	dayNamesMin:['일','월','화','수','목','금','토'],
			changeYear : true, // 년을 바꿀 수 있는 셀렉트 박스 표시
			minDate : '-100y', // 현재 날짜로부터 100년 이전까지 표시
			maxDate : '100y', // 현재 날짜이후도 표시가능
			yearRange : 'c-100:c+100', // 년도 선택 셀렉트 
    });
  } );
  </script>
  
	
</body>
</html>
