<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/common.js"></script>
</head>
<body>
<script>
	function callback(res){
		var res = JSON.parse(res);
		document.write(res);
		for(var addr of res.list){
			document.write(addr);
		}
	}
	var au = new AjaxUtil();
	au.open('/addr2/list');
	au.setcallback(callback);
	au.send();
	/*var callback = function(res){
		console.log(res);
	}
	var xhr = new XMLHttpRequest();
	xhr.open('GET', '/addr2/list');
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				callback(xhr.response);
			}
		}
	}
	xhr.send();*/
</script>
</body>
</html>