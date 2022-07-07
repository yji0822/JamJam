<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/lunch.css" rel="stylesheet" type="text/css" />
<link
	href="https://hangeul.pstatic.net/hangeul_static/css/nanum-square.css"
	rel="stylesheet">
	<style>
		.btn { 
			width: 100px;
			height: 55px;
			margin-top: 5px;
		}
		
	</style>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
		$(document).ready(function(){
			$('select[name="year"], select[name="month"]').change(function(){
				$('form').submit();
			});
		});
		
		function todayMenu(day, ampm){
			month = '${month}';
			if(month<10){
				month = '0'+month;
			}
			if(day<10){
				day = '0'+day;
			}
			// alert('${year}' + '년 ' + month + '월' + day +'의 '+ampm);
			child = open('${conPath}/todayMenu.do?year=${year}&month='+month+"&day="+day+"&ampm="+ampm,
					'', 'width=500, height=600, location=no');
			 if (!child) {
			 	alert('팝업차단 해제하세요');
			 }
		}
	</script>
</head>
<body>

	<c:if test="${not empty lunchInResult }">
		<script>
			alert('${lunchInResult }');
		</script>
	</c:if>

	<jsp:include page="../main/header.jsp" />
	
	
	<section>
        <div id="content1">
            <div class="content1">
                <a href="${conPath }/main.do">
                    <img src="${conPath }/img/school.png" class="logo" alt="logo" />
                </a>
            </div>
            <div id="lnb">
                <ul>
                    <li><a href="${conPath }/notice.do">공지사항</a></li>
					<li><a href="${conPath }/lunch.do">오늘의 급식</a></li>
					<li><a href="${conPath }/free.do">자유게시판</a></li>
					<li><a href="${conPath }/sAllView.do">반친구들</a></li>
                </ul>
            </div>
        </div>

        <!-- 수정 해야할 곳-->
        <div id="content2">
            <div class="lunchcal1">
                <form action="${conPath }/lunch.do">
				<select name="year">
					<c:forEach var="i" begin="${year-10 }" end="${year+10 }">
						<c:if test="${i eq year }">
							<option selected="selected">${i }</option>
						</c:if>
						<c:if test="${i != year }">
							<option>${i }</option>
						</c:if>
					</c:forEach>
				</select> 년 &nbsp;&nbsp;
				<select name="month">
					<c:forEach var="i" begin="1" end="12">
						<c:if test="${i eq month }">
							<option selected="selected">${i }</option>
						</c:if>
						<c:if test="${i != month }">
							<option>${i }</option>
						</c:if>
					</c:forEach>
				</select> 월
			</form>
            </div>


            <div class="lunchcal2">
                <table>
				<tr>
					<c:forEach var="t" items="${calPrint.title }">
						<th>${t }</th>
					</c:forEach>
				</tr>
				<c:forEach var="i" begin="0" end="5">
					<tr>
						<c:forEach var="j" begin="0" end="6">
							<c:if test="${empty calPrint.calDate[i][j] }">
								<td></td>
							</c:if>
							<c:if test="${not empty calPrint.calDate[i][j] }">
								<td>
									<h3>${calPrint.calDate[i][j] }</h3> 
									<c:forEach var="dto" items="${dtos }">
										<c:if test="${calPrint.calDate[i][j] == dto.day }">&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
											<span
												onclick="todayMenu(${calPrint.calDate[i][j] }, '${dto.ampm }')">${dto.ampm }</span>
											<br>
										</c:if>
									</c:forEach>
								</td>
							</c:if>
						</c:forEach>
					</tr>
				</c:forEach>
			</table>
            </div>
        </div>
           
           <c:if test="${not empty admin and empty student }">
	
            <div id="content3">
				<button class="btn" onclick="location='${conPath}/lunchInView.do'">추가하기</button>
				
			</div>
			
    
    		</c:if>
        </section>
	
	<jsp:include page="../main/footer.jsp" />




</body>
</html>