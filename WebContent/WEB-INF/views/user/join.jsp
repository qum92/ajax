<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 align="center">회원가입</h1>
<form method="post" action="/user">
<table border="1" align="center">
	<tr align="center">
		<th>이름</th>
		<td><input type="text" name="ui_name" id="ui_name"></td>
	</tr>
	<tr align="center">
		<th>아이디</th>
		<td><input type="text" name="ui_id" id="ui_id"></td>
	</tr>
	<tr align="center">
		<th>비밀번호</th>
		<td><input type="password" name="ui_pwd" id="ui_pwd"></td>
	</tr>
	<tr align="center">
		<th>e-mail</th>
		<td><input type="email" name="ui_email" id="ui_email"></td>
	</tr>
	<tr align="center">
		<td colspan="2"><button>회원가입 완료</button></td>
	</tr>
</table>
<input type="hidden" name="cmd" value="insert">
</form>
</body>
</html>