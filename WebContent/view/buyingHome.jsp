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

	<style type="text/css">
	#imgae1 {
		transition: all 500ms 0s ease;
	}
	.on {
		transform: scale(1.5, 1.5) translate(0px, 0px);
	}
	.off {
		transform: scale(1.0, 1.0) translate(0px, 0px);
	}
	</style>
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
		<button onclick="func1()">拡大</button><br>
		<img alt="写真未登録" src="<%=house.getHouseImage()%>" id="image1" onclick="func1()" onmouseover="func1()"><br>
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

	<script type="text/javascript">
	alert();

	const image1 = document.getElementById("image1");
	let stat = false;

	function func1() {
		if(stat) {
			image1.classList.add('off');
			image1.classList.remove('on');
		} else {
			image1.classList.add('on');
			image1.classList.remove('off');
		}
		stat = !stat;
	}
	</script>

<!--
<script src="<%=request.getContextPath()%>/view/js/index.js"></script>
-->
</body>
</html>