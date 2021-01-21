<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Header</title>
<link rel="stylesheet" href="./../css/headerFooterStyles.css">
</head>
<body>
	<header>
		<h1 class="headline">別荘.COM</h1>
		<ul class="nav-list">
			<li class="nav-list-item"><a href="<%=request.getContextPath()%>/SellerDataController?page=start">売りたい</a></li>
			<li class="nav-list-item"><a href="<%=request.getContextPath()%>/BuyerDataController?page=start">買いたい</a></li>
			</ul>
	</header>
</body>
</html>