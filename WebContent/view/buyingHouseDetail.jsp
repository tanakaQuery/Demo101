<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.BuyerInfo"%>
<%@ page import="model.SoldHouseInfo"%>
<%@ page import="java.util.ArrayList"%>
<%
BuyerInfo buyer = (BuyerInfo)session.getAttribute("buyer");
SoldHouseInfo house = (SoldHouseInfo)session.getAttribute("houseDetail");

Boolean pageMode = true;
Boolean pageModeSecond = false;

if (buyer != null) {
	pageMode = true;
	if (buyer.getBoughtHouseID() == house.getId()) {
		pageModeSecond = true;
	} else {
		pageModeSecond = false;
	}
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
	<div class="thread">
		<img alt="写真未登録" src="<%=house.getHouseImage()%>"><br>
		<div>
		<ol class="thread-text">
			<li>ID:<%=house.getId()%></li>
			<li>名称：<%=house.getHouseName()%></li>
			<li>価格：<%=house.getHousePrice()%></li>
			<li>所有者：<%=house.getOwnerName()%>様</li>
		</ol>
		</div>
		<div class="thred-button">
			<% if (pageMode == true) { %>
				<% if (pageModeSecond == true) { %>
					<h2>購入検討依頼中</h2>
				<% } else { %>
					<div>
						<form action="<%=request.getContextPath()%>/BuyerDataController"
							method="post">
							<input type="hidden" name="ACTION" value="BUY">
							<input type="submit" value="購入検討依頼">
						</form>
					</div>
				<% } %>
			<% } else { %>
			<br>
			<% } %>
			<a href="<%=request.getContextPath()%>/BuyerDataController?page=home">物件一覧へ戻る</a>
		</div>
	</div>
	<hr>
	<jsp:include page="subViews/footer.jsp"></jsp:include>
</body>
</html>