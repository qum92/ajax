<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" id="addrTable">
		<tr>
			<th>번호</th>
			<td><input type = "hidden" id= "adNum" value="${addr.ad_num}">${addr.ad_num}</td>
		</tr>
			<tr>
			<th>주소코드</th>
			<td><input type = "text" id= "adCode" value="${addr.ad_code}"></td>
		</tr>	
			<tr>
			<th>시도</th>
			<td><input type = "text" id= "adSido" value="${addr.ad_sido}"></td>
		</tr>	
			<tr>
			<th>구군</th>
			<td><input type = "text" id= "adGugun" value="${addr.ad_gugun}"></td>
		</tr>	
			<tr>
			<th>동편읍</th>
			<td><input type = "text" id= "adDong" value="${addr.ad_dong}"></td>
		</tr>	
			<tr>
			<th>리</th>
			<td><input type = "text" id= "adLee" value="${addr.ad_lee}"></td>
		</tr>
			<tr>
			<th>번지</th>
			<td><input type = "text" id= "adBunji" value="${addr.ad_bunji}"></td>
		</tr>	
			<tr>
			<th>호수</th>
			<td><input type = "text" id= "adHo" value="${addr.ad_ho}"></td>
		</tr>
		<tr>	
	<td colspan="7" align="center"><input type="button" onclick="updateAddr()" value="수정">&nbsp;<input type="button" onclick="deleteAddr()" value="삭제">&nbsp;<input type="button" onclick="closeTable()" value="닫기">&nbsp;</td>
		</tr>	
	</table>
</body>
</html>