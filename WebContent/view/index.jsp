<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/mainStyles.css">
<link rel="stylesheet" href="css/headerFooterStyles.css">
</head>
<body>
	<header>
		<h1 class="headline">別荘.COM</h1>
	</header>
	<hr>
	<div>
		<button class="button mainHtml" onclick="location.href='<%=request.getContextPath()%>/SellerDataController?page=start'">売りたい方</button>
		<button class="button mainHtml" onclick="location.href='<%=request.getContextPath()%>/BuyerDataController?page=start'">買いたい方</button>
	</div>
<hr>
</body>
</html>