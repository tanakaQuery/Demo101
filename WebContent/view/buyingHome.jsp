<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.BuyerInfo"%>
<%@ page import="model.SoldHouseInfo"%>
<%@ page import="model.InquiryInfo"%>
<%@ page import="java.util.ArrayList"%>
<%
BuyerInfo buyer = (BuyerInfo) session.getAttribute("buyer");
ArrayList<SoldHouseInfo> houseArray = (ArrayList<SoldHouseInfo>) session.getAttribute("houseArray");
ArrayList<InquiryInfo> inquiryArray = (ArrayList<InquiryInfo>) session.getAttribute("inquiryArray");

Boolean showPage = true;
Boolean pageMode = true;

ArrayList<Integer> houseID = new ArrayList<Integer>();
int houseCounts = 0;

if (houseArray != null) {
	houseCounts = houseArray.size();
	if (buyer != null) {
		if (inquiryArray != null) {
			for ( InquiryInfo inquiry : inquiryArray) {
				houseID.add(inquiry.getHouseId());
			}
			pageMode = true;
		} else {
			houseID.add(0);
			pageMode = true;
		}

	} else {
		houseID.add(0);
		pageMode = false;
	}
} else {
	showPage = false;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./view/css/mainStyles.css">
<link rel="stylesheet" href="./view/css/headerFooterStyles.css">
<link rel="stylesheet" href="./view/css/drawer.css">
</head>
<body>
<jsp:include page="subViews/header.jsp"></jsp:include>
<jsp:include page="subViews/drawer.jsp"></jsp:include>
	<hr>
	<div id="wrapper">
	<% if (showPage == true) { %>
	<% if (pageMode == true) { %>
	<h1><%=buyer.getName()%>様のマイページ</h1>
	<a href="<%=request.getContextPath()%>/BuyerDataController?page=logout">ログアウト</a>
	<hr>
	<% } else { %>
	<p>ゲスト様ページ</p>
	<a href="<%=request.getContextPath()%>/BuyerDataController?page=logout">ログインページへ</a>
	<hr>
	<% } %>
	<% int num = 0; %>
	<h3>全 <%=houseArray.size()%>物件</h3>
	<% for (SoldHouseInfo house : houseArray) { %>
	<hr>
	<div class="thread">
		<img alt="写真未登録" src="<%=house.getHouseImage()%>"><br>
		<div>
			<% if (houseID.size() != 0) { %>
				<% for ( int id : houseID) { %>
					<% if ( id == house.getId()) { %>
						<h2 id="styleA">購入検討依頼中</h2>
					<% } else if ( id == 0) {%>
						<br>
					<% } %>
				<% } %>
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
	<% } else { %>
	<h1>売出し中の物件がありません</h1>
	<% } %>
	</div>
	<hr>
<jsp:include page="subViews/footer.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/view/js/buyingHome.js"></script>
<script src="<%=request.getContextPath()%>/view/js/drawer.js"></script>
</body>
</html>