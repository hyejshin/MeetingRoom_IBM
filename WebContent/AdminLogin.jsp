<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table border="1">
<form method="post" action="AdminLogin.do">
ID <input type="text" name="id" id="id">
PW <input type="password" name="pw" id="pw">
<input type="submit" value="전송">
</form>

</table>
${message}
</body>
</html>