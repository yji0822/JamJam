<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="${conPath }/css/join.css" rel="stylesheet" type="text/css" />
	<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('input[name="sId"]').keyup(function(){
			var sId = $('input[name="sId"]').val();
			$.ajax({
				url : '${conPath}/idConfirm.do',
				type : 'get',
				dataType : 'html',
				data : "sId=" + sId,
				success : function(data){
					$('#idConfirmResult').html(data);
				}
			});//ajax
		});// mId keyup 이벤트
		$('input[name="sPw"], input[name="sPwChk"]').keyup(function(){
			var sPw = $('input[name="sPw"]').val();
			var sPwChk = $('input[name="sPwChk"]').val();
			if(sPw == sPwChk){
				$('#pwChkResult').text('비밀번호 일치');
			}else{
				$('#pwChkResult').html('<b>비밀번호 불일치</b>');
			}
		}); // pw check
		$('input[name="sEmail"]').keyup(function(){
			var patternMail = /^[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\.[a-zA-Z]+){1,2}$/; // 메일 패턴
			var sEmail = $('input[name="sEmail"]').val();
			if(patternMail.test(sEmail)){
				$.ajax({
					url : '${conPath}/emailConfirm.do',
					type : 'get',
					dataType : 'html',
					data : "sEmail=" + sEmail,
					success : function(data){
						$('#emailConfirmResult').html(data);
					}
				});//ajax
			}else if(!sEmail){
				$('#emailConfirmResult').html(' &nbsp; ');
			}else{
				$('#emailConfirmResult').html('메일 형식을 지켜주세요');
			}//if
		});// mEmail keyup 이벤트
		$('form').submit(function(){
			var idConfirmResult = $('#idConfirmResult').text().trim();
			var pwChkResult     = $('#pwChkResult').text().trim();
			var emailConfirmResult = $('#emailConfirmResult').text().trim();
			if(idConfirmResult!='사용 가능한 ID'){
				alert('중복된 아이디 입니다.');
				$('input[name="sId"]').focus();
				return false;
			}else if(pwChkResult !='비밀번호 일치'){
				alert('비밀번호를 확인하세요');
				$('input[name="sPw"]').focus();
				return false;
			}else if(emailConfirmResult!='' && emailConfirmResult != '사용 가능한 메일입니다'){
				alert('이메일을 확인하세요');
				$('input[name="sEmail"]').focus();
				return false;
			}
		});
	});//mIdConfirm의 click이벤트
	</script>
	
  
  
  
</head>

<body>
	
	
	<jsp:include page="../main/header.jsp" />
	
	<section>
        <div id="content1">
        </div>
        <div id="content2">
            <form action="${conPath }/join.do" method="post" enctype="multipart/form-data" class="join" >
               
                <table class="join_table">
                    <caption>나는야 회원가입창!</caption>

                    <tr>
                        <th>아이디</th>
                        <td><input type="text" name="sId" required="required"/>
                        	<div id="idConfirmResult"> &nbsp; </div>
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td><input type="password" name="sPw" required="required"/></td>
                    </tr>
                    <tr>
                        <th>비밀번호확인</th>
                        <td>
                        	<input type="password" name="sPwChk" required="required">
							<div id="pwChkResult"> &nbsp; </div>
						</td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td><input type="text" name="sName" /></td>
                    </tr>
                    <tr>
                        <th>출석번호</th>
                        <td><input type="number" name="sNo" required="required" min="1" max="100"/></td>
                    </tr>
                    <tr>
                        <th>핸드폰번호</th>
                        <td><input type="text" name="sTel" required="required"/></td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td><input type="email" name="sEmail"><div id="emailConfirmResult"> &nbsp; </div></td>
                    </tr>
                    <tr>
                        <th>성별</th>
                        <td>
                            <input type="radio" name="sGender" value="m" id="m" checked="checked" /><label for="m">남자 </label>
                            <input type="radio" name="sGender" value="w" id="w"/><label for="w">여자 </label>
                        </td>
                    </tr>
                     <tr>
                        <th>생일</th>
                        <td><input type="text" name="sBirth" id="sBirth"/></td>
                    </tr>
                    <tr>
                        <th>사진</th>
                        <td><input type="file" name="sPhoto" /></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="button" value="이전페이지" onclick="history.back()" />
                            <input type="reset" value="초기화" />
                            <input type="submit" value="회원가입" />
                        </td>

                    </tr>
                </table>

            </form>
        </div>
        <div id="content3">
            
        </div>
    </section>
    
    
    <jsp:include page="../main/footer.jsp" />
    
    
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#sBirth" ).datepicker({
    	dateFormat : 'y-mm-dd',
    	changeMonth : true, // 월을 바꿀 수 있는 셀렉트 박스 표시
    	monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
    	showMonthAfterYear : true,
    	yearSuffix : '년', // "2020년 3월"
    	showOtherMonths : true,
    	/* appendText: "(yy-mm-dd)" */
    	dayNamesMin:['일','월','화','수','목','금','토'],
			changeYear : true, // 년을 바꿀 수 있는 셀렉트 박스 표시
			minDate : '-100y', // 현재 날짜로부터 100년 이전까지 표시
			maxDate : 'y', // 현재 날짜이전까지만 표시
			yearRange : 'c-100:c+100', // 년도 선택 셀렉트 
    });
  } );
  </script>
    
	
</body>
</html>