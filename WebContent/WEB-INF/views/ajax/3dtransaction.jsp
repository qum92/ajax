<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
	var num = 2;
	
	if(num==1){
		num = '한개';
	}else{
		num = '한개는 아닙니다.';
	}
	alert(num);
	
	num = (num=1)?'한개':(num==2)?'두개':'한개는 아닙니다.';
</script>
</body>
</html>