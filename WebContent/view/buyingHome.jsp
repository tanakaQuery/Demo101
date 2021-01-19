<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.BuyerInfo" %>
<%
BuyerInfo buyer = (BuyerInfo)session.getAttribute("buyer");
Boolean pageMode = true;
if (buyer != null) {
	pageMode = true;
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
<% if (pageMode) {%>
 <h1><%=buyer.getName() %>様のマイページ</h1><br>
<% } else { %>
<p> ゲスト様ページ</p>
<% } %>



</body>
</html>