<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.SellerInfo" %>
<%@ page import="model.SoldHouseInfo" %>
<%@ page import="model.BuyerInfo" %>
<%@ page import="java.util.ArrayList" %>
<%
SellerInfo seller = (SellerInfo)session.getAttribute("seller");
SoldHouseInfo house = (SoldHouseInfo)session.getAttribute("house");
ArrayList<BuyerInfo> buyerArray = (ArrayList<BuyerInfo>)session.getAttribute("buyerArray");
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
<hr>
<h1><%=seller.getName() %>様のマイページ</h1>
<a href="<%=request.getContextPath()%>/sellerDataController?page=logout">ログアウト</a>
<hr>
<% if (pageMode) {%>
	<hr>
	<div>
		<h3>売り出し中の物件</h3>
		<img alt="写真未登録" src="<%=house.getHouseImage()%>">
		<div>
			<ol class="thread-text">
				<li>登録ID:<%=house.getId()%></li>
				<li>名称：<%=house.getHouseName()%></li>
				<li>希望売却価格：<%=house.getHousePrice()%></li>
			</ol>
		</div>
	</div>
	<hr>
	<% if (buyerArray.size() != 0) { %>
		<% for (BuyerInfo buyer : buyerArray) { %>
			<p>購入希望者名：<%=buyer.getName() %></p>
		<% } %>
	<% } else {%>
		<br>
	<% } %>
	<div>
		<form action="<%=request.getContextPath()%>/sellerDataController"
			method="post">
			<input type="hidden" name="ACTION" value="DELETE"> <input
				type="submit" value="物件削除">
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


<jsp:include page="subViews/footer.jsp"></jsp:include>
</body>
</html>