<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.BuyerInfo" %>
<%@ page import="model.SoldHouseInfo" %>
<%@ page import="java.util.ArrayList" %>
<%
BuyerInfo buyer = (BuyerInfo)session.getAttribute("buyer");
Boolean pageMode = true;
if (buyer != null) {
	pageMode = true;
} else {
	pageMode = false;
}

ArrayList<SoldHouseInfo> houseArray = (ArrayList<SoldHouseInfo>)session.getAttribute("houseArray");

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
<% if (pageMode == true) {%>
 <h1><%=buyer.getName() %>様のマイページ</h1><br>
 <hr>
 <a href="<%=request.getContextPath()%>/BuyerDataController?page=logout">ログアウト</a>
<% } else { %>
<p> ゲスト様ページ</p>
 <hr>
 <a href="<%=request.getContextPath()%>/BuyerDataController?page=logout">ログインページへ</a>

<% } %>

<h3><%=houseArray.size() %>物件</h3>

<% for(SoldHouseInfo house : houseArray) { %>
<p><%=house.getId() %></p>
<p><%=house.getHouseName() %></p>
<p><%=house.getHousePrice() %></p>
<p><%=house.getOwnerName() %></p>
<img alt="sample" src="<%=house.getHouseImage() %>">
<% } %>

</body>
</html>