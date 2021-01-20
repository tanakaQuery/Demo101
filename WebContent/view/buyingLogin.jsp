<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./view/css/mainStyles.css">
<link rel="stylesheet" href="./view/css/headerFooterStyles.css">
</head>
<body>
<jsp:include page="subViews/header.jsp"></jsp:include>
<h1>ログイン画面</h1>
	<hr>
	<div>
		<form action="<%=request.getContextPath()%>/BuyerDataController"
			method="post">
			<input type="hidden" name="ACTION" value="LOGIN">
			ユーザ名  ：<input type="text" name="ID"><br><br>
			パスワード：<input type="password" name="PW"><br><br>

			<input type="submit" value="ログイン">
		</form>
	</div>
	<hr>
	<a href="<%=request.getContextPath()%>/BuyerDataController?page=start">戻る</a>
</body>
</html>