<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/join.css" rel="stylesheet" type="text/css" />
	<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			$('input[name="mId"]').keyup(function(){
				var mId = $('input[name="mId"]').val();
				$.ajax({
					url : '${conPath}/idConfirm.do',
					type : 'get',
					dataType : 'html',
					data : "mId="+mId,
					success : function(data){
						$('#idConfirmResult').html(data);
					}
				});//ajax
			});// mId keyup 이벤트
			$('input[name="mPw"], input[name="mPwChk"]').keyup(function(){
				var mPw = $('input[name="mPw"]').val();
				var mPwChk = $('input[name="mPwChk"]').val();
				if(mPw == mPwChk){
					$('#pwChkResult').text('비밀번호 일치');
				}else{
					$('#pwChkResult').html('<b>비밀번호 불일치</b>');
				}
			}); // pw check
			$('input[name="mEmail"]').keyup(function(){
				var patternMail = /^[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\.[a-zA-Z]+){1,2}$/; // 메일 패턴
				var mEmail = $('input[name="mEmail"]').val();
				if(patternMail.test(mEmail)){
					$.ajax({
						url : '${conPath}/emailConfirm.do',
						type : 'get',
						dataType : 'html',
						data : "mEmail="+mEmail,
						success : function(data){
							$('#emailConfirmResult').html(data);
						}
					});//ajax
				}else if(!mEmail){
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
					alert('사용가능한 ID로 가입하세요');
					$('input[name="mId"]').focus();
					return false;
				}else if(pwChkResult !='비밀번호 일치'){
					alert('비밀번호를 확인하세요');
					$('input[name="mPw"]').focus();
					return false;
				}else if(emailConfirmResult!='' && emailConfirmResult != '사용 가능한 메일입니다'){
					alert('이메일을 확인하세요');
					$('input[name="mEmail"]').focus();
					return false;
				}
			});
		});//mIdConfirm의 click이벤트		
		});
	</script>
</head>

<body>

	<jsp:include page="../main/header.jsp" />
	
	<section>
        <div id="content1">
        </div>
        <div id="content2">
            <form class="join">
                <!-- INSERT INTO STUDENT (SID, SPW, SNAME, SNO, STEL, SEMAIL, SGENDER, SBIRTH, SPHOTO)
    VALUES ('FFF', '1', '유재석', 16, '010-1234-5678',  'YYY@NAVER.COM', 'M', TO_DATE('2009-08-06', 'YYYY-MM-DD'), '15.JPG');-->
                
                <table class="join_table">
                    <caption>나는야 회원가입창!</caption>

                    <tr>
                        <th>아이디</th>
                        <td><input type="text" /></td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td><input type="password" /></td>
                    </tr>
                    <tr>
                        <th>비밀번호확인</th>
                        <td><input type="password" /></td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td><input type="text" /></td>
                    </tr>
                    <tr>
                        <th>출석번호</th>
                        <td><input type="number" /></td>
                    </tr>
                    <tr>
                        <th>핸드폰번호</th>
                        <td><input type="text" /></td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td><input type="email" /></td>
                    </tr>
                    <tr>
                        <th>성별</th>
                        <td>
                            <input type="radio" name="sGender" value="m" id="m" checked="checked" /><label for="m">남자 </label>
                            <input type="radio" name="sGender" value="w" id="w"/><label for="w">여자 </label>
                        </td>
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
	
</body>
</html>