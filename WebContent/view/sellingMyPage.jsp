<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.SellerInfo" %>
<%@ page import="model.SoldHouseInfo" %>
<%
SellerInfo seller = (SellerInfo)session.getAttribute("seller");
SoldHouseInfo house = (SoldHouseInfo)session.getAttribute("house");
Boolean pageMode = true;
if (seller.getSellingHouseID() != 0) {
	if (house != null) {
		pageMode = true;
	} else {
		pageMode = false;
	}
} else {
	pageMode = false;
}
%>
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
<h1><%=seller.getName() %>様のマイページ</h1><br>
<hr>
<% if (pageMode) {%>
 <p> <%= seller.getSellingHouseID() %> 売り出し中</p>
 <p> <%= house.getId() %></p>
 <p> <%= house.getHouseName() %></p>
 <p> <%= house.getHousePrice() %></p>
 <img alt="sample101" src="<%= house.getHouseImage() %>">
<hr>
	<div>
		<form action="<%=request.getContextPath()%>/sellerDataController"
			method="post">
			<input type="hidden" name="ACTION" value="DELETE">
			<input type="submit" value="物件削除">
		</form>
	</div>
<% } else { %>
<p> 新規物件登録</p>
<hr>
	<div>
		<form action="<%=request.getContextPath()%>/sellerDataController"
			method="post">
			<input type="hidden" name="ACTION" value="SELL">
			名称  ：<input type="text" name="name"><br><br>
			売却希望価格：<input type="text" name="price"><br><br>
			画像URL：<input type="text" name="image"><br><br>

			<input type="submit" value="登録">
		</form>
	</div>
	<hr>
<% } %>

<hr>
	<a href="<%=request.getContextPath()%>/sellerDataController?page=logout">ログアウト</a>


</body>
</html>