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
var test = function(){
	this.callback = function(f){
		f();
	}
}
var func = function(){
	alert(1);
}
t = new test();
t.callback(function(){
	alert(1);
});
</script>
</body>
</html>