<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.BuyerInfo"%>
<%@ page import="model.SoldHouseInfo"%>
<%@ page import="java.util.ArrayList"%>
<%
BuyerInfo buyer = (BuyerInfo) session.getAttribute("buyer");
Boolean pageMode = true;
int houseID = 0;
if (buyer != null) {
	houseID = buyer.getBoughtHouseID();
	pageMode = true;
} else {
	pageMode = false;
}

ArrayList<SoldHouseInfo> houseArray = (ArrayList<SoldHouseInfo>) session.getAttribute("houseArray");
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
	<% if (pageMode == true) { %>
	<h1><%=buyer.getName()%>様のマイページ</h1>
	<a href="<%=request.getContextPath()%>/BuyerDataController?page=logout">ログアウト</a>
	<hr>
	<% } else { %>
	<p>ゲスト様ページ</p>
	<a href="<%=request.getContextPath()%>/BuyerDataController?page=logout">ログインページへ</a>
	<hr>
	<% } %>

	<h3>全 <%=houseArray.size()%>物件</h3>

	<% for (SoldHouseInfo house : houseArray) { %>
	<hr>
	<div class="thread">
		<img alt="写真未登録" src="<%=house.getHouseImage()%>"><br>
		<div>
		<% if (houseID == house.getId()) { %>
			<h2>購入検討依頼中</h2>
		<% } %>
		<ol class="thread-text">
			<li>ID:<%=house.getId()%></li>
			<li>名称：<%=house.getHouseName()%></li>
			<li>価格：<%=house.getHousePrice()%></li>
		</ol>
		</div>
		<div class="thred-button">
		<button class="detailButton mainHtml"
			onclick="location.href='<%=request.getContextPath()%>/BuyerDataController?page=detail&id=<%=house.getId()%>'">物件詳細</button>
		</div>
	</div>
	<hr>
	<% } %>
	<hr>
	<jsp:include page="subViews/footer.jsp"></jsp:include>
</body>
</html>